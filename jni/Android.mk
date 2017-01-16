LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE    := hellojnitest
LOCAL_SRC_FILES := com_example_mytestdemo_main_MainNativeTest.c
include $(BUILD_SHARED_LIBRARY)