import org.gradle.api.tasks.Exec
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeCompilation
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.konan.target.KonanTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
        }
        androidMain.dependencies {
        }
        iosMain.dependencies {
        }
    }

    targets.withType<KotlinNativeTarget>().configureEach {
        val nativeTarget = this
        val konan = nativeTarget.konanTarget
        val iosKonan =
            when (konan) {
                KonanTarget.IOS_ARM64,
                KonanTarget.IOS_X64,
                KonanTarget.IOS_SIMULATOR_ARM64,
                -> konan
                else -> return@configureEach
            }

        val nativeTargetName = iosKonan.name
        val sdk = if (iosKonan == KonanTarget.IOS_ARM64) "iphoneos" else "iphonesimulator"
        val arch =
            if (iosKonan == KonanTarget.IOS_X64) {
                "x86_64"
            } else {
                "arm64"
            }

        val buildStaticLib =
            tasks.register<Exec>("buildConfigKeysStaticLib$nativeTargetName") {
                group = "native"
                val outputDir = layout.buildDirectory.dir("native-static/$nativeTargetName").get().asFile
                val cFile = project.file("src/nativeInterop/c/config_keys.c")
                val includeDir = project.file("src/nativeInterop/include")
                val objectFile = outputDir.resolve("config_keys.o")
                val archiveFile = outputDir.resolve("libconfig_keys.a")

                inputs.files(cFile, includeDir.resolve("config_keys.h"))
                outputs.file(archiveFile)

                doFirst {
                    outputDir.mkdirs()
                }

                commandLine(
                    "sh",
                    "-c",
                    "xcrun --sdk $sdk clang -arch $arch -c '${cFile.absolutePath}' " +
                        "-o '${objectFile.absolutePath}' -I'${includeDir.absolutePath}' && " +
                        "xcrun --sdk $sdk ar rcs '${archiveFile.absolutePath}' '${objectFile.absolutePath}'",
                )
            }

        val mainCompilation =
            nativeTarget.compilations.getByName("main") as KotlinNativeCompilation

        mainCompilation.cinterops.create("configKeys") {
            defFile(file("src/nativeInterop/cinterop/config_keys.def"))
            packageName("com.sample.nativeconfig.interop")
            compilerOpts("-I${file("src/nativeInterop/include").absolutePath}")
        }

        mainCompilation.compileTaskProvider.configure {
            dependsOn(buildStaticLib)
        }

        nativeTarget.binaries.configureEach {
            linkerOpts(
                "-L${layout.buildDirectory.dir("native-static/$nativeTargetName").get().asFile.absolutePath}",
                "-lconfig_keys",
            )
            linkTaskProvider.configure {
                dependsOn(buildStaticLib)
            }
        }
    }
}

android {
    namespace = "com.sample.nativeconfig"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()

        externalNativeBuild {
            cmake {
                cppFlags += ""
            }
        }

        ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a", "x86_64")
        }
    }

    externalNativeBuild {
        cmake {
            path = file("src/androidMain/cpp/CMakeLists.txt")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
