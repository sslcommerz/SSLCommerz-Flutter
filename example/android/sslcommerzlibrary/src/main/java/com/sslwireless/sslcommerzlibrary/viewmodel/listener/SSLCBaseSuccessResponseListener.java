package com.sslwireless.sslcommerzlibrary.viewmodel.listener;

import com.sslwireless.sslcommerzlibrary.model.response.SSLCSdkMainResponseModel;

public interface SSLCBaseSuccessResponseListener {
    void successResponse(SSLCSdkMainResponseModel SSLCSdkMainResponseModel);

    void failResponse(String message);

    void validationError(String message);
}
