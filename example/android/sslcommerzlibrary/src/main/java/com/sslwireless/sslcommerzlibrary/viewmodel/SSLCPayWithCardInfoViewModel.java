package com.sslwireless.sslcommerzlibrary.viewmodel;

import androidx.lifecycle.ViewModel;
import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.sslwireless.sslcommerzlibrary.BuildConfig;
import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerClass;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerListener;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfo;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCMethodIndentification;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCPrefUtils;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCPayWithCardInfoListener;

import org.json.JSONObject;

import java.util.HashMap;

public class SSLCPayWithCardInfoViewModel extends ViewModel implements SSLCApiHandlerListener {

    private Context context;
    private SSLCApiHandlerClass SSLCApiHandlerClass;
    private SSLCPayWithCardInfoListener SSLCPayWithCardInfoListener;

    public SSLCPayWithCardInfoViewModel(Context context) {
        this.context = context;
        SSLCApiHandlerClass = new SSLCApiHandlerClass(context);
    }

    public void payWithCard(String reg_id, String enc_key, String cus_session, String sessionKey,
                            String card_no,
                            String expiry,
                            String card_cvv, String name, String save,
                            String login_session_key, String is_emi, String emi_tenure,
                            String emi_bank_id, String offer_id, String is_offer,
                            SSLCPayWithCardInfoListener SSLCPayWithCardInfoListener) {
        this.SSLCPayWithCardInfoListener = SSLCPayWithCardInfoListener;

        if (validateFields(card_no, expiry, card_cvv, name)) {
            HashMap<String, Object> mHashMap = new HashMap<>();

            mHashMap.put("reg_id", reg_id);
            mHashMap.put("enc_key", enc_key);
            mHashMap.put("token", cus_session);
            mHashMap.put("gw_session_key", sessionKey);
            mHashMap.put("card_no", card_no);
            mHashMap.put("expiry", expiry);
            mHashMap.put("card_cvv", card_cvv);
            mHashMap.put("name", name);
            mHashMap.put("save", save);
            mHashMap.put("session_key", login_session_key);
            mHashMap.put("is_emi", is_emi);
            mHashMap.put("emi_tenure", emi_tenure);
            mHashMap.put("emi_bank_id", emi_bank_id);
            mHashMap.put("offer_id", offer_id);
            mHashMap.put("is_offer", is_offer);
            mHashMap.put("need_json", 1);
            mHashMap.put("lang", SSLCPrefUtils.getPreferenceLanguageValue(context));

            if (SSLCShareInfo.getInstance().isNetworkAvailable(context)) {
                SSLCApiHandlerClass.sslCommerzRequest(context,
                        SSLCShareInfo.getInstance().getType(context).equals(SSLCSdkType.LIVE) ?
                                BuildConfig.MAIN_LIVE_URL : BuildConfig.MAIN_SANDBOX_URL, "transact",
                        SSLCMethodIndentification.METHOD_POST, "", mHashMap,
                        false, this);
            } else {
                SSLCPayWithCardInfoListener.payWithCardInfoFail(context.getResources().getString(R.string.internet_connection));
            }
        }
    }

    private boolean validateFields(String card_no, String expiry, String card_cvv, String name) {
        if (TextUtils.isEmpty(card_no)) {
            SSLCPayWithCardInfoListener.payWithCardInfoValidationError("Please enter card number");
            return false;
        } else if (TextUtils.isEmpty(expiry)) {
            SSLCPayWithCardInfoListener.payWithCardInfoValidationError("Please enter expiry date");
            return false;
        } else if (TextUtils.isEmpty(card_cvv)) {
            SSLCPayWithCardInfoListener.payWithCardInfoValidationError("Please enter card cvv");
            return false;
        } else if (TextUtils.isEmpty(name)) {
            SSLCPayWithCardInfoListener.payWithCardInfoValidationError("Please enter your name");
            return false;
        }
        return true;
    }

    @Override
    public void success(JSONObject jsonObject) {
        Gson gson = new Gson();
        SSLCTransactionInfo SSLCTransactionInfo = gson.fromJson(jsonObject.toString(), SSLCTransactionInfo.class);

        SSLCPayWithCardInfoListener.payWithCardInfoSuccess(SSLCTransactionInfo);
    }

    @Override
    public void fail(String message) {
        SSLCPayWithCardInfoListener.payWithCardInfoFail(message);
    }
}
