package com.sslwireless.sslcommerzlibrary.viewmodel;

import androidx.lifecycle.ViewModel;
import android.content.Context;

import com.google.gson.Gson;
import com.sslwireless.sslcommerzlibrary.BuildConfig;
import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerClass;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerListener;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCVerifyOtpAndLoginModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCMethodIndentification;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCPrefUtils;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCVerifyOtpAndLoginListener;

import org.json.JSONObject;

import java.util.HashMap;

public class SSLCVerifyOtpAndLoginViewModel extends ViewModel implements SSLCApiHandlerListener {

    private Context context;
    private SSLCApiHandlerClass SSLCApiHandlerClass;
    private SSLCVerifyOtpAndLoginListener SSLCVerifyOtpAndLoginListener;

    public SSLCVerifyOtpAndLoginViewModel(Context context) {
        this.context = context;
        SSLCApiHandlerClass = new SSLCApiHandlerClass(context);
    }

    public void verifyOtpAndLogin(String phone, String reg_id, String enc_key, String sessionKey, String otp,
                                  SSLCVerifyOtpAndLoginListener SSLCVerifyOtpAndLoginListener) {
        this.SSLCVerifyOtpAndLoginListener = SSLCVerifyOtpAndLoginListener;

        HashMap<String, Object> mHashMap = new HashMap<>();

        mHashMap.put("number", phone);
        mHashMap.put("reg_id", reg_id);
        mHashMap.put("enc_key", enc_key);
        mHashMap.put("gw_session_key", sessionKey);
        mHashMap.put("otp", otp);
        mHashMap.put("lang", SSLCPrefUtils.getPreferenceLanguageValue(context));

        if (SSLCShareInfo.getInstance().isNetworkAvailable(context)) {
        SSLCApiHandlerClass.sslCommerzRequest(context,
                SSLCShareInfo.getInstance().getType(context).equals(SSLCSdkType.LIVE) ?
                        BuildConfig.MAIN_LIVE_URL : BuildConfig.MAIN_SANDBOX_URL, "verify_checkout_otp",
                SSLCMethodIndentification.METHOD_POST, "", mHashMap,
                false, this);
        } else {
            SSLCVerifyOtpAndLoginListener.verifyOtpAndLoginFail(context.getResources().getString(R.string.internet_connection));
        }
    }

    @Override
    public void success(JSONObject jsonObject) {
        Gson gson = new Gson();
        SSLCVerifyOtpAndLoginModel SSLCVerifyOtpAndLoginModel = gson.fromJson(jsonObject.toString(), SSLCVerifyOtpAndLoginModel.class);
        SSLCVerifyOtpAndLoginListener.verifyOtpAndLoginSuccess(SSLCVerifyOtpAndLoginModel);
    }

    @Override
    public void fail(String message) {

        SSLCVerifyOtpAndLoginListener.verifyOtpAndLoginFail(message);
    }
}
