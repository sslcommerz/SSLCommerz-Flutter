package com.sslwireless.sslcommerzlibrary.viewmodel;

import androidx.lifecycle.ViewModel;
import android.content.Context;

import com.google.gson.Gson;
import com.sslwireless.sslcommerzlibrary.BuildConfig;
import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerClass;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerListener;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCEMIModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCMethodIndentification;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCPrefUtils;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCEMIListener;

import org.json.JSONObject;

import java.util.HashMap;

public class SSLCEMIViewModel extends ViewModel implements SSLCApiHandlerListener {

    private Context context;
    private SSLCApiHandlerClass SSLCApiHandlerClass;
    private SSLCEMIListener SSLCEMIListener;

    public SSLCEMIViewModel(Context context) {
        this.context = context;
        SSLCApiHandlerClass = new SSLCApiHandlerClass(context);
    }

    public void getEMI(String cus_session, String reg_id, String enc_key, SSLCEMIListener SSLCEMIListener) {
        this.SSLCEMIListener = SSLCEMIListener;

        HashMap<String, Object> mHashMap = new HashMap<>();

        mHashMap.put("session_id", cus_session);
        mHashMap.put("reg_id", reg_id);
        mHashMap.put("enc_key", enc_key);
        mHashMap.put("lang", SSLCPrefUtils.getPreferenceLanguageValue(context));

        if (SSLCShareInfo.getInstance().isNetworkAvailable(context)) {
        SSLCApiHandlerClass.sslCommerzRequest(context,
                SSLCShareInfo.getInstance().getType(context).equals(SSLCSdkType.LIVE) ?
                        BuildConfig.MAIN_LIVE_URL : BuildConfig.MAIN_SANDBOX_URL, "get_emi",
                SSLCMethodIndentification.METHOD_POST, "", mHashMap,
                false, this);
        } else {
            SSLCEMIListener.emiFail(context.getResources().getString(R.string.internet_connection));
        }
    }

    @Override
    public void success(JSONObject jsonObject) {
        Gson gson = new Gson();
        SSLCEMIModel SSLCEMIModel = gson.fromJson(jsonObject.toString(), SSLCEMIModel.class);
        SSLCEMIListener.emiSuccess(SSLCEMIModel);
    }

    @Override
    public void fail(String message) {
        SSLCEMIListener.emiFail(message);
    }
}
