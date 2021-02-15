package com.sslwireless.sslcommerzlibrary.viewmodel;

import androidx.lifecycle.ViewModel;
import android.content.Context;

import com.google.gson.Gson;
import com.sslwireless.sslcommerzlibrary.BuildConfig;
import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerClass;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerListener;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCOfferModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCMethodIndentification;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCPrefUtils;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCGetOfferListener;

import org.json.JSONObject;

import java.util.HashMap;

public class SSLCGetOfferViewModel extends ViewModel implements SSLCApiHandlerListener {

    private Context context;
    private SSLCApiHandlerClass SSLCApiHandlerClass;
    private SSLCGetOfferListener SSLCGetOfferListener;

    public SSLCGetOfferViewModel(Context context) {
        this.context = context;
        SSLCApiHandlerClass = new SSLCApiHandlerClass(context);
    }

    public void getGetOffer(String session_id, String reg_id, String enc_key,
                            SSLCGetOfferListener SSLCGetOfferListener) {
        this.SSLCGetOfferListener = SSLCGetOfferListener;

        HashMap<String, Object> mHashMap = new HashMap<>();

        mHashMap.put("session_id", session_id);
        mHashMap.put("reg_id", reg_id);
        mHashMap.put("enc_key", enc_key);
        mHashMap.put("lang", SSLCPrefUtils.getPreferenceLanguageValue(context));

        if (SSLCShareInfo.getInstance().isNetworkAvailable(context)) {
            SSLCApiHandlerClass.sslCommerzRequest(context,
                    SSLCShareInfo.getInstance().getType(context).equals(SSLCSdkType.LIVE) ?
                            BuildConfig.MAIN_LIVE_URL : BuildConfig.MAIN_SANDBOX_URL, "get_offer",
                    SSLCMethodIndentification.METHOD_POST, "", mHashMap,
                    false, this);
        } else {
            SSLCGetOfferListener.resendOtpFail(context.getResources().getString(R.string.internet_connection));
        }
    }

    @Override
    public void success(JSONObject jsonObject) {
        Gson gson = new Gson();
        SSLCOfferModel SSLCOfferModel = gson.fromJson(jsonObject.toString(), SSLCOfferModel.class);

        SSLCGetOfferListener.resendOtpSuccess(SSLCOfferModel);
    }

    @Override
    public void fail(String message) {
        SSLCGetOfferListener.resendOtpFail(message);

    }
}
