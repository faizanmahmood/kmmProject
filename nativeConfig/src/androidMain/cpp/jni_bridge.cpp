#include <jni.h>

extern "C" {
const char* getBaseUrl(void);
const char* getApiKey(void);
const char* getClientId(void);
const char* getSecretKey(void);
const char* getAnotherKey(void);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_sample_nativeconfig_NativeConfigJni_getBaseUrl(JNIEnv* env, jobject /* thiz */) {
    return env->NewStringUTF(getBaseUrl());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_sample_nativeconfig_NativeConfigJni_getApiKey(JNIEnv* env, jobject /* thiz */) {
    return env->NewStringUTF(getApiKey());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_sample_nativeconfig_NativeConfigJni_getClientId(JNIEnv* env, jobject /* thiz */) {
    return env->NewStringUTF(getClientId());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_sample_nativeconfig_NativeConfigJni_getSecretKey(JNIEnv* env, jobject /* thiz */) {
    return env->NewStringUTF(getSecretKey());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_sample_nativeconfig_NativeConfigJni_getAnotherKey(JNIEnv* env, jobject /* thiz */) {
    return env->NewStringUTF(getAnotherKey());
}
