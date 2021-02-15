package com.sslwireless.sslcommerzlibrary.viewmodel.listener;

import com.sslwireless.sslcommerzlibrary.model.response.SSLCVerifyLoginSessionModel;

public interface SSLCVerifyLoginSessionListener {
    void verifyLoginSessionSuccess(SSLCVerifyLoginSessionModel SSLCVerifyLoginSessionModel);

    void verifyLoginSessionFail(String message);
}
