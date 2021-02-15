package com.sslwireless.sslcommerzlibrary.viewmodel.listener;

import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfo;

public interface SSLCPayWithStoredCardListener {
    void payWithStoredCardInfoSuccess(SSLCTransactionInfo SSLCTransactionInfo);

    void payWithStoredCardInfoFail(String message);

    void payWithStoredCardInfoValidationError(String message);
}
