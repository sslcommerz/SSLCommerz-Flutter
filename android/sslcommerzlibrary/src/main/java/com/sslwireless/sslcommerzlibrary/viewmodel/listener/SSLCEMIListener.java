package com.sslwireless.sslcommerzlibrary.viewmodel.listener;

import com.sslwireless.sslcommerzlibrary.model.response.SSLCEMIModel;

public interface SSLCEMIListener {
    void emiSuccess(SSLCEMIModel SSLCEMIModel);

    void emiFail(String message);
}
