cmake_minimum_required(VERSION 3.22.1)

project("nativeconfig")

add_library(
        nativeconfig
        SHARED
        ../../nativeInterop/c/config_keys.c
        jni_bridge.cpp
)

find_library(log-lib log)

target_link_libraries(
        nativeconfig
        ${log-lib}
)

target_include_directories(
        nativeconfig
        PRIVATE
        ../../nativeInterop/include
)
