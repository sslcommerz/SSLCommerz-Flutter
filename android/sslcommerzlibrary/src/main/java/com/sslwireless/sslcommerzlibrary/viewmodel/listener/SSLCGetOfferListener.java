package com.sslwireless.sslcommerzlibrary.viewmodel.listener;

import com.sslwireless.sslcommerzlibrary.model.response.SSLCOfferModel;

public interface SSLCGetOfferListener {
    void resendOtpSuccess(SSLCOfferModel SSLCOfferModel);

    void resendOtpFail(String message);
}
