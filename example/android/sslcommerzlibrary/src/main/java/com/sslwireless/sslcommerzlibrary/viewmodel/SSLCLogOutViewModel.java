package com.sslwireless.sslcommerzlibrary.viewmodel;

import androidx.lifecycle.ViewModel;
import android.content.Context;

import com.google.gson.Gson;
import com.sslwireless.sslcommerzlibrary.BuildConfig;
import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerClass;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerListener;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCLogOutModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCMethodIndentification;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCPrefUtils;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCLogOutListener;

import org.json.JSONObject;

import java.util.HashMap;

public class SSLCLogOutViewModel extends ViewModel implements SSLCApiHandlerListener {

    private Context context;
    private SSLCApiHandlerClass SSLCApiHandlerClass;
    private SSLCLogOutListener SSLCLogOutListener;

    public SSLCLogOutViewModel(Context context) {
        this.context = context;
        SSLCApiHandlerClass = new SSLCApiHandlerClass(context);
    }

    public void submitLogout(String cus_session, String reg_id, String enc_key,
                                      SSLCLogOutListener SSLCLogOutListener) {
        this.SSLCLogOutListener = SSLCLogOutListener;

        HashMap<String, Object> mHashMap = new HashMap<>();

        mHashMap.put("cus_session", cus_session);
        mHashMap.put("reg_id", reg_id);
        mHashMap.put("enc_key", enc_key);
        mHashMap.put("lang", SSLCPrefUtils.getPreferenceLanguageValue(context));

        if (SSLCShareInfo.getInstance().isNetworkAvailable(context)) {
        SSLCApiHandlerClass.sslCommerzRequest(context,
                SSLCShareInfo.getInstance().getType(context).equals(SSLCSdkType.LIVE) ?
                        BuildConfig.MAIN_LIVE_URL : BuildConfig.MAIN_SANDBOX_URL, "logoff",
                SSLCMethodIndentification.METHOD_POST, "", mHashMap,
                false, this);
        } else {
            SSLCLogOutListener.logOutFail(context.getResources().getString(R.string.internet_connection));
        }
    }

    @Override
    public void success(JSONObject jsonObject) {
        Gson gson = new Gson();
        SSLCLogOutModel SSLCLogOutModel = gson.fromJson(jsonObject.toString(), SSLCLogOutModel.class);

        SSLCLogOutListener.logOutSuccess(SSLCLogOutModel);
    }

    @Override
    public void fail(String message) {
        SSLCLogOutListener.logOutFail(message);
    }
}
