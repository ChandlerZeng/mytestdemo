LOCAL_PATH:= $(call my-dir) 
include $(CLEAR_VARS) 

LOCAL_MODULE_TAGS := optional 

LOCAL_SRC_FILES := $(call all-Java-files-under, src) 

LOCAL_CERTIFICATE := platform 

include $(BUILD_PACKAGE) 

# Use the following include to make our test apk. 
include $(call all-makefiles-under,$(LOCAL_PATH)) 