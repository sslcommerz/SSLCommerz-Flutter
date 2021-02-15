package com.sslwireless.sslcommerzlibrary.viewmodel.listener;

import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfo;

public interface SSLCPayWithCardInfoListener {
    void payWithCardInfoSuccess(SSLCTransactionInfo SSLCTransactionInfo);

    void payWithCardInfoFail(String message);

    void payWithCardInfoValidationError(String message);
}
