import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlin.serialization)
}

evaluationDependsOn(":nativeConfig")

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
        }
    }

    targets.withType<KotlinNativeTarget>().configureEach {
        val konanName = konanTarget.name
        val nc = project(":nativeConfig")
        val staticTaskName = "buildConfigKeysStaticLib$konanName"
        if (nc.tasks.findByName(staticTaskName) == null) return@configureEach
        val archive =
            nc.layout.buildDirectory.file("native-static/$konanName/libconfig_keys.a").get().asFile.absolutePath
        binaries.withType<Framework>().configureEach {
            linkerOpts(archive)
            linkTaskProvider.configure {
                dependsOn(nc.tasks.named(staticTaskName))
            }
        }
    }

    sourceSets {
        val commonMain by getting

        val iosMain by creating {
            dependsOn(commonMain)
        }
        listOf("iosArm64Main", "iosX64Main", "iosSimulatorArm64Main").forEach { leaf ->
            named(leaf) {
                dependsOn(iosMain)
            }
        }

        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.kotlin)
            implementation(libs.ktor.client.cio)
        }
        commonMain.dependencies {
            implementation(project(":nativeConfig"))
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // navigation
            implementation(libs.androidx.nav3.ui)
            implementation(libs.androidx.material3.adaptive.nav3)

            // serialization
            implementation(libs.kotlinx.serialization.core)
            implementation(libs.kotlinx.serialization.json)

            //Koin
            implementation(libs.koin.core)

            // ktor (engines are per-platform: CIO on Android, Darwin on iOS)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.serialization.kotlinx.json)
        }
        iosMain.dependencies {
            implementation(libs.ktor.darwin)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.sample.project"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.sample.project"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

    }

    buildFeatures {
        prefab = false
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(libs.compose.uiTooling)
}

