package com.sslwireless.sslcommerzlibrary.view.singleton;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCAdditionalInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCCustomerInfoInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCEMITransactionInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCProductInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCShipmentInfoInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCSdkMainResponseModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCEnums;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCProgressBarHandler;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;
import com.sslwireless.sslcommerzlibrary.view.activity.MainUIActivitySSLC;
import com.sslwireless.sslcommerzlibrary.viewmodel.SSLCMainViewModel;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCBaseSuccessResponseListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCTransactionResponseListener;

import static android.content.ContentValues.TAG;

public class IntegrateSSLCommerz implements SSLCBaseSuccessResponseListener {

    public static SSLCTransactionResponseListener SSLCTransactionResponseListener;
    private static IntegrateSSLCommerz integrateSSLCommerz = new IntegrateSSLCommerz();
    private static Context mContext;
    private static SSLCommerzInitialization mSslCommerzInitialization = null;
    private static SSLCShipmentInfoInitializer SSLCShipmentInfoInitializer = null;
    private static SSLCProductInitializer SSLCProductInitializer = null;
    private static SSLCEMITransactionInitializer SSLCEMITransactionInitializer = null;
    private static SSLCCustomerInfoInitializer SSLCCustomerInfoInitializer = null;
    private static SSLCAdditionalInitializer SSLCAdditionalInitializer = null;
    private SSLCProgressBarHandler SSLCProgressBarHandler;

    private IntegrateSSLCommerz() {
    }

    /**
     * Get singleton instant
     *
     * @param context
     * @return
     */
    public static IntegrateSSLCommerz getInstance(Context context) {
        mContext = context;
        return integrateSSLCommerz;
    }

    public static IntegrateSSLCommerz getInstance(Context context, SSLCommerzInitialization sslCommerzInitialization) {
        mContext = context;
        mSslCommerzInitialization = sslCommerzInitialization;
        return integrateSSLCommerz;
    }

    public IntegrateSSLCommerz addSSLCommerzInitialization(SSLCommerzInitialization sslCommerzInitialization) {
        this.mSslCommerzInitialization = sslCommerzInitialization;
        return this;
    }

    public IntegrateSSLCommerz addShipmentInfoInitializer(SSLCShipmentInfoInitializer SSLCShipmentInfoInitializer) {
        this.SSLCShipmentInfoInitializer = SSLCShipmentInfoInitializer;
        return this;
    }

    public IntegrateSSLCommerz addProductInitializer(SSLCProductInitializer SSLCProductInitializer) {
        this.SSLCProductInitializer = SSLCProductInitializer;
        return this;
    }

    public IntegrateSSLCommerz addEMITransactionInitializer(SSLCEMITransactionInitializer SSLCEMITransactionInitializer) {
        this.SSLCEMITransactionInitializer = SSLCEMITransactionInitializer;
        return this;
    }

    public IntegrateSSLCommerz addCustomerInfoInitializer(SSLCCustomerInfoInitializer SSLCCustomerInfoInitializer) {
        this.SSLCCustomerInfoInitializer = SSLCCustomerInfoInitializer;
        return this;
    }

    public IntegrateSSLCommerz addAdditionalInitializer(SSLCAdditionalInitializer SSLCAdditionalInitializer) {
        this.SSLCAdditionalInitializer = SSLCAdditionalInitializer;
        return this;
    }

    private boolean isValidateIntegrateSSLCommerzData() {
        //Do some basic validations to check
        return true;
    }

    public void buildApiCall(SSLCTransactionResponseListener SSLCTransactionResponseListener) {
        IntegrateSSLCommerz.SSLCTransactionResponseListener = SSLCTransactionResponseListener;
        SSLCProgressBarHandler = new SSLCProgressBarHandler(mContext);

        if (isValidateIntegrateSSLCommerzData()) {
            SSLCProgressBarHandler.show();
            //ShareInfo.getInstance().progressDialog(mContext, "Please wait...");
            SSLCMainViewModel SSLCMainViewModel = new SSLCMainViewModel(mContext);
            SSLCMainViewModel.postApiCall(mSslCommerzInitialization, SSLCShipmentInfoInitializer, SSLCProductInitializer, SSLCEMITransactionInitializer,
                    SSLCCustomerInfoInitializer, SSLCAdditionalInitializer, this);
        }
    }

    @Override
    public void successResponse(SSLCSdkMainResponseModel SSLCSdkMainResponseModel) {
        //ShareInfo.getInstance().hideProgressDialog();
        SSLCProgressBarHandler.hide();

        if (SSLCSdkMainResponseModel.getStatus().equalsIgnoreCase(SSLCEnums.StatusType.SUCCESS.name())) {
            //Log.e(TAG, "successResponse: " + new Gson().toJson(SSLCSdkMainResponseModel));

            Intent intent = new Intent(mContext, MainUIActivitySSLC.class);
            intent.putExtra(SSLCShareInfo.main_response, SSLCSdkMainResponseModel.toJSON());
            intent.putExtra("sslCommerzInitialerData", mSslCommerzInitialization);
            mContext.startActivity(intent);
        } else {
            SSLCTransactionResponseListener.transactionFail(SSLCSdkMainResponseModel.getFailedreason());
        }
    }

    @Override
    public void failResponse(String message) {
        SSLCProgressBarHandler.hide();
        //ShareInfo.getInstance().hideProgressDialog();
        //Log.e(TAG, "failed response: " + message);
        SSLCTransactionResponseListener.transactionFail(message);
    }

    @Override
    public void validationError(String message) {
        SSLCTransactionResponseListener.merchantValidationError(message);
    }
}
