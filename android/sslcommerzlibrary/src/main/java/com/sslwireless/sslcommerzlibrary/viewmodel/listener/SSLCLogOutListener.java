package com.sslwireless.sslcommerzlibrary.viewmodel.listener;

import com.sslwireless.sslcommerzlibrary.model.response.SSLCLogOutModel;

public interface SSLCLogOutListener {
    void logOutSuccess(SSLCLogOutModel SSLCLogOutModel);

    void logOutFail(String message);
}
