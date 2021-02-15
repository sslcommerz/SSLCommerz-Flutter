package com.sslwireless.sslcommerzlibrary.viewmodel.listener;

import com.sslwireless.sslcommerzlibrary.model.response.SSLCGetDeviceTokenModel;

public interface SSLCGetDeviceTokenListener {
    void deviceTokenSuccess(SSLCGetDeviceTokenModel SSLCGetDeviceTokenModel);

    void deviceTokenFail(String message);
}
