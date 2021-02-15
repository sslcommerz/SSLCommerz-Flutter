package com.sslwireless.sslcommerzlibrary.viewmodel.listener;

import com.sslwireless.sslcommerzlibrary.model.response.SSLCResendOtpModel;

public interface SSLCResendOtpListener {
    void resendOtpSuccess(SSLCResendOtpModel SSLCResendOtpModel);

    void resendOtpFail(String message);
}
