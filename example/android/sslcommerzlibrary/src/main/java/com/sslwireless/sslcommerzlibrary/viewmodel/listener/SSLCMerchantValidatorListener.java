package com.sslwireless.sslcommerzlibrary.viewmodel.listener;

import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel;

public interface SSLCMerchantValidatorListener {
    void merchantValidatorSuccess(SSLCTransactionInfoModel transactionInfo);

    void merchantValidatorFail(String message);
}
