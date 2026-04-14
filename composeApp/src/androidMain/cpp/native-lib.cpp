#include <jni.h>

extern "C" {
const char* getBaseUrl();
const char* getApiKey();
const char* getClientId();
const char* getSecretKey();
const char* getAnotherKey();
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_sample_project_utils_NativeConfig_getBaseUrl(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF(getBaseUrl());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_sample_project_utils_NativeConfig_getApiKey(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF(getApiKey());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_sample_project_utils_NativeConfig_getClientId(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF(getClientId());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_sample_project_utils_NativeConfig_getSecretKey(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF(getSecretKey());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_sample_project_utils_NativeConfig_getAnotherKey(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF(getAnotherKey());
}