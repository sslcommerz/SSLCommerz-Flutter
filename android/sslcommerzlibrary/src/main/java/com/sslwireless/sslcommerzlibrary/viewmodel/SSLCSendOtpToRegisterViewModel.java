package com.sslwireless.sslcommerzlibrary.viewmodel;

import androidx.lifecycle.ViewModel;
import android.content.Context;

import com.google.gson.Gson;
import com.sslwireless.sslcommerzlibrary.BuildConfig;
import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerClass;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerListener;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCSendOtpToRegisterModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCMethodIndentification;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCPrefUtils;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCSendOtpToRegisterListener;

import org.json.JSONObject;

import java.util.HashMap;

public class SSLCSendOtpToRegisterViewModel extends ViewModel implements SSLCApiHandlerListener {

    private Context context;
    private SSLCApiHandlerClass SSLCApiHandlerClass;
    private SSLCSendOtpToRegisterListener SSLCSendOtpToRegisterListener;

    public SSLCSendOtpToRegisterViewModel(Context context) {
        this.context = context;
        SSLCApiHandlerClass = new SSLCApiHandlerClass(context);
    }

    public void submitOtpRegistration(String phone, String reg_id, String enc_key,
                                      SSLCSendOtpToRegisterListener SSLCSendOtpToRegisterListener) {
        this.SSLCSendOtpToRegisterListener = SSLCSendOtpToRegisterListener;

        HashMap<String, Object> mHashMap = new HashMap<>();

        mHashMap.put("phone", phone);
        mHashMap.put("reg_id", reg_id);
        mHashMap.put("enc_key", enc_key);
        mHashMap.put("lang", SSLCPrefUtils.getPreferenceLanguageValue(context));

        if (SSLCShareInfo.getInstance().isNetworkAvailable(context)) {
        SSLCApiHandlerClass.sslCommerzRequest(context,
                SSLCShareInfo.getInstance().getType(context).equals(SSLCSdkType.LIVE) ?
                        BuildConfig.MAIN_LIVE_URL : BuildConfig.MAIN_SANDBOX_URL, "send_checkout_otp",
                SSLCMethodIndentification.METHOD_POST, "", mHashMap,
                false, this);
        } else {
            SSLCSendOtpToRegisterListener.sendOtpToRegFail(context.getResources().getString(R.string.internet_connection));
        }
    }

    @Override
    public void success(JSONObject jsonObject) {
        Gson gson = new Gson();
        SSLCSendOtpToRegisterModel SSLCSendOtpToRegisterModel = gson.fromJson(jsonObject.toString(), SSLCSendOtpToRegisterModel.class);

        SSLCSendOtpToRegisterListener.sendOtpToRegSuccess(SSLCSendOtpToRegisterModel);
    }

    @Override
    public void fail(String message) {
        SSLCSendOtpToRegisterListener.sendOtpToRegFail(message);
    }
}
