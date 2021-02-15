#include <jni.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_sslwireless_sslcommerzlibrary_model_util_ShareInfo_getBaseUrl(JNIEnv *env,
                                                                       jobject instance) {
    return env->NewStringUTF("https://securepay.sslcommerz.com/gwprocess/v4/");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_sslwireless_sslcommerzlibrary_model_util_ShareInfo_getMainLiveUrl(JNIEnv *env,
                                                                        jobject instance) {
    return env->NewStringUTF("https://api-epay.sslcommerz.com/securepay/api.php/");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_sslwireless_sslcommerzlibrary_model_util_ShareInfo_getMainSandboxUrl(JNIEnv *env,
                                                                          jobject instance) {
    return env->NewStringUTF("https://sandbox.sslcommerz.com/securepay/api.php/");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_sslwireless_sslcommerzlibrary_model_util_ShareInfo_getSandboxBaseUrl(JNIEnv *env,
                                                                              jobject instance) {
    return env->NewStringUTF("https://sandbox.sslcommerz.com/gwprocess/v4/");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_sslwireless_sslcommerzlibrary_model_util_ShareInfo_getMerchantValidatorSandbox(JNIEnv *env,
                                                                                        jobject instance) {
    return env->NewStringUTF("https://sandbox.sslcommerz.com/validator/api/");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_sslwireless_sslcommerzlibrary_model_util_ShareInfo_getMerchantValidatorLive(JNIEnv *env,
                                                                                     jobject instance) {
    return env->NewStringUTF("https://securepay.sslcommerz.com/validator/api/");
}
