package com.sslwireless.sslcommerzlibrary.viewmodel;

import androidx.lifecycle.ViewModel;
import android.content.Context;

import com.google.gson.Gson;
import com.sslwireless.sslcommerzlibrary.BuildConfig;
import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerClass;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerListener;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCGetDeviceTokenModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCMethodIndentification;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCPrefUtils;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCGetDeviceTokenListener;

import org.json.JSONObject;

import java.util.HashMap;

public class SSLCGetDeviceTokenViewModel extends ViewModel implements SSLCApiHandlerListener {

    private Context context;
    private SSLCApiHandlerClass SSLCApiHandlerClass;
    private SSLCGetDeviceTokenListener SSLCGetDeviceTokenListener;
    private SSLCommerzInitialization sslCommerzInitialization;

    public SSLCGetDeviceTokenViewModel(Context context) {
        this.context = context;
        SSLCApiHandlerClass = new SSLCApiHandlerClass(context);
    }

    public void submitDeviceToken(String osType, String deviceId, String deviceImei,
                                  String model, String deviceManufa,
                                  SSLCGetDeviceTokenListener SSLCGetDeviceTokenListener) {
        this.SSLCGetDeviceTokenListener = SSLCGetDeviceTokenListener;

        HashMap<String, Object> mHashMap = new HashMap<>();

        mHashMap.put("osType", osType);
        mHashMap.put("deviceId", deviceId);
        mHashMap.put("deviceImei", deviceImei);
        mHashMap.put("Model", model);
        mHashMap.put("deviceManufa", deviceManufa);
        mHashMap.put("lang", SSLCPrefUtils.getPreferenceLanguageValue(context));

        if (SSLCShareInfo.getInstance().isNetworkAvailable(context)) {
        SSLCApiHandlerClass.sslCommerzRequest(context,
                SSLCShareInfo.getInstance().getType(context).equals(SSLCSdkType.LIVE) ?
                        BuildConfig.MAIN_LIVE_URL : BuildConfig.MAIN_SANDBOX_URL, "get_token",
                SSLCMethodIndentification.METHOD_POST, "", mHashMap,
                false, this);
        } else {
            SSLCGetDeviceTokenListener.deviceTokenFail(context.getResources().getString(R.string.internet_connection));
        }
    }

    @Override
    public void success(JSONObject jsonObject) {
        Gson gson = new Gson();
        SSLCGetDeviceTokenModel SSLCGetDeviceTokenModel = gson.fromJson(jsonObject.toString(), SSLCGetDeviceTokenModel.class);

        SSLCGetDeviceTokenListener.deviceTokenSuccess(SSLCGetDeviceTokenModel);
    }

    @Override
    public void fail(String message) {
        SSLCGetDeviceTokenListener.deviceTokenFail(message);
    }
}
