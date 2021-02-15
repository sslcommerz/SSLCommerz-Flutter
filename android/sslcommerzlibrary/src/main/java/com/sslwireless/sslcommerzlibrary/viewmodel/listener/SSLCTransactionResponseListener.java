package com.sslwireless.sslcommerzlibrary.viewmodel.listener;

import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel;

public interface SSLCTransactionResponseListener {
    void transactionSuccess(SSLCTransactionInfoModel transactionInfo);

    void transactionFail(String message);

    void merchantValidationError(String message);
}
