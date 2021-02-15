package com.sslwireless.sslcommerzlibrary.viewmodel.listener;

import com.sslwireless.sslcommerzlibrary.model.response.SSLCVerifyOtpAndLoginModel;

public interface SSLCVerifyOtpAndLoginListener {
    void verifyOtpAndLoginSuccess(SSLCVerifyOtpAndLoginModel SSLCVerifyOtpAndLoginModel);

    void verifyOtpAndLoginFail(String message);
}
