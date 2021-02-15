package com.sslwireless.sslcommerzlibrary.viewmodel;

import androidx.lifecycle.ViewModel;
import android.content.Context;

import com.google.gson.Gson;
import com.sslwireless.sslcommerzlibrary.BuildConfig;
import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerClass;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerListener;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCVerifyLoginSessionModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCMethodIndentification;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCPrefUtils;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCVerifyLoginSessionListener;

import org.json.JSONObject;

import java.util.HashMap;

public class SSLCVerifyLoginSessionViewModel extends ViewModel implements SSLCApiHandlerListener {

    private Context context;
    private SSLCApiHandlerClass SSLCApiHandlerClass;
    private SSLCVerifyLoginSessionListener SSLCVerifyLoginSessionListener;

    public SSLCVerifyLoginSessionViewModel(Context context) {
        this.context = context;
        SSLCApiHandlerClass = new SSLCApiHandlerClass(context);
    }

    public void verifyOtpAndLogin(String reg_id, String enc_key, String sessionKey, String cus_session,
                                  SSLCVerifyLoginSessionListener SSLCVerifyLoginSessionListener) {
        this.SSLCVerifyLoginSessionListener = SSLCVerifyLoginSessionListener;

        HashMap<String, Object> mHashMap = new HashMap<>();

        mHashMap.put("reg_id", reg_id);
        mHashMap.put("enc_key", enc_key);
        mHashMap.put("gw_session_key", sessionKey);
        mHashMap.put("cus_session", cus_session);
        mHashMap.put("need_json", 1);
        mHashMap.put("lang", SSLCPrefUtils.getPreferenceLanguageValue(context));

        if (SSLCShareInfo.getInstance().isNetworkAvailable(context)) {
        SSLCApiHandlerClass.sslCommerzRequest(context,
                SSLCShareInfo.getInstance().getType(context).equals(SSLCSdkType.LIVE) ?
                        BuildConfig.MAIN_LIVE_URL : BuildConfig.MAIN_SANDBOX_URL, "login_status",
                SSLCMethodIndentification.METHOD_POST, "", mHashMap,
                false, this);
        } else {
            SSLCVerifyLoginSessionListener.verifyLoginSessionFail(context.getResources().getString(R.string.internet_connection));
        }
    }

    @Override
    public void success(JSONObject jsonObject) {
        Gson gson = new Gson();
        SSLCVerifyLoginSessionModel SSLCVerifyLoginSessionModel = gson.fromJson(jsonObject.toString(), SSLCVerifyLoginSessionModel.class);
        //String session = jsonObject.toString();
        SSLCVerifyLoginSessionListener.verifyLoginSessionSuccess(SSLCVerifyLoginSessionModel);
    }

    @Override
    public void fail(String message) {
        SSLCVerifyLoginSessionListener.verifyLoginSessionFail(message);
    }
}
