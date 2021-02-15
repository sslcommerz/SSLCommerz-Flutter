package com.sslwireless.sslcommerzlibrary.viewmodel.listener;

import com.sslwireless.sslcommerzlibrary.model.response.SSLCSendOtpToRegisterModel;

public interface SSLCSendOtpToRegisterListener {
    void sendOtpToRegSuccess(SSLCSendOtpToRegisterModel SSLCSendOtpToRegisterModel);

    void sendOtpToRegFail(String message);
}
