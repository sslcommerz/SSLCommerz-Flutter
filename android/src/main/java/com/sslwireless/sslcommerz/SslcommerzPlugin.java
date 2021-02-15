package com.sslwireless.sslcommerz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.sslwireless.sslcommerz.helper.InitializationHelper;
import com.sslwireless.sslcommerz.model.SDKDataModel;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCAdditionalInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCCustomerInfoInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCProductInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCShipmentInfoInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCurrencyType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCTransactionResponseListener;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/**
 * SslcommerzPlugin
 */
public class SslcommerzPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
    private MethodChannel channel;
    private Context context;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "sslcommerz");
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull final Result result) {
        if (call.method.equals("initiateSSLCommerz")) {
            Log.d("TAG", "onMethodCall: " + call.arguments.toString());
            SDKDataModel sdkDataModel = new Gson().fromJson(call.arguments.toString(), SDKDataModel.class);


            IntegrateSSLCommerz integrateSSLCommerz = IntegrateSSLCommerz.getInstance(context)
                    .addSSLCommerzInitialization(InitializationHelper.sslCommerzInitializatoin(sdkDataModel));

            if (sdkDataModel.getCustomerInfoInitializer() != null)
                integrateSSLCommerz.addCustomerInfoInitializer(InitializationHelper.initiateCustomerInfo(sdkDataModel));
            //                    .addEMITransactionInitializer(SSLCEMITransactionInitializer)
            if (sdkDataModel.getSslcShipmentInfoInitializer() != null)
                integrateSSLCommerz.addShipmentInfoInitializer(InitializationHelper.initiateShipmentInfo(sdkDataModel));
            if (sdkDataModel.getSslcProductInitializer() != null)
                integrateSSLCommerz.addProductInitializer(InitializationHelper.initiateProductInitializer(sdkDataModel));
            if (sdkDataModel.getSslcAdditionalInitializer() != null)
                integrateSSLCommerz.addAdditionalInitializer(InitializationHelper.getAdditionalInitializer(sdkDataModel));


            integrateSSLCommerz.buildApiCall(new SSLCTransactionResponseListener() {
                @Override
                public void transactionSuccess(SSLCTransactionInfoModel sslcTransactionInfoModel) {
                    result.success(new Gson().toJson(sslcTransactionInfoModel));
                }

                @Override
                public void transactionFail(String s) {
                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void merchantValidationError(String s) {
                    showErrorAlert("Error", s);
                }
            });
        } else {
            result.notImplemented();
        }
    }

    private void showErrorAlert(String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }


    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }


    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        context = binding.getActivity();
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {

    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
    }

    @Override
    public void onDetachedFromActivity() {

    }
}
