package com.sslwireless.sslcommerzlibrary.viewmodel;

import androidx.lifecycle.ViewModel;
import android.content.Context;

import com.google.gson.Gson;
import com.sslwireless.sslcommerzlibrary.BuildConfig;
import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerClass;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerListener;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCCardDeleteModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCMethodIndentification;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCPrefUtils;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCCardDeleteListener;

import org.json.JSONObject;

import java.util.HashMap;

public class SSLCCardDeleteViewModel extends ViewModel implements SSLCApiHandlerListener {

    private Context context;
    private SSLCApiHandlerClass SSLCApiHandlerClass;
    private SSLCCardDeleteListener deleteListener;

    public SSLCCardDeleteViewModel(Context context) {
        this.context = context;
        SSLCApiHandlerClass = new SSLCApiHandlerClass(context);
    }

    public void submitDelete(String cus_session, String reg_id, String enc_key, String card_index,
                                      SSLCCardDeleteListener SSLCCardDeleteListener) {
        this.deleteListener = SSLCCardDeleteListener;

        HashMap<String, Object> mHashMap = new HashMap<>();

        mHashMap.put("token", cus_session);
        mHashMap.put("card_index", card_index);
        mHashMap.put("reg_id", reg_id);
        mHashMap.put("enc_key", enc_key);
        mHashMap.put("lang", SSLCPrefUtils.getPreferenceLanguageValue(context));

        if (SSLCShareInfo.getInstance().isNetworkAvailable(context)) {
        SSLCApiHandlerClass.sslCommerzRequest(context,
                SSLCShareInfo.getInstance().getType(context).equals(SSLCSdkType.LIVE) ?
                        BuildConfig.MAIN_LIVE_URL : BuildConfig.MAIN_SANDBOX_URL, "delete_card",
                SSLCMethodIndentification.METHOD_POST, "", mHashMap,
                false, this);
        } else {
            deleteListener.cardDeleteFail(context.getResources().getString(R.string.internet_connection));
        }
    }

    @Override
    public void success(JSONObject jsonObject) {
        Gson gson = new Gson();
        SSLCCardDeleteModel SSLCCardDeleteModel = gson.fromJson(jsonObject.toString(), SSLCCardDeleteModel.class);

        deleteListener.cardDeleteSuccess(SSLCCardDeleteModel);
    }

    @Override
    public void fail(String message) {
        deleteListener.cardDeleteFail(message);

    }
}
