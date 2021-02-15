package com.sslwireless.sslcommerzlibrary.viewmodel.listener;

import com.sslwireless.sslcommerzlibrary.model.response.SSLCCardDeleteModel;

public interface SSLCCardDeleteListener {
    void cardDeleteSuccess(SSLCCardDeleteModel SSLCCardDeleteModel);

    void cardDeleteFail(String message);
}
