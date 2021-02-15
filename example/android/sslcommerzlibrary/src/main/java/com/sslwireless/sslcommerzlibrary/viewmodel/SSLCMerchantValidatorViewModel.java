package com.sslwireless.sslcommerzlibrary.viewmodel;

import androidx.lifecycle.ViewModel;
import android.content.Context;
import com.google.gson.Gson;
import com.sslwireless.sslcommerzlibrary.BuildConfig;
import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerClass;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerListener;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCMethodIndentification;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCPrefUtils;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCMerchantValidatorListener;

import org.json.JSONObject;

import java.util.HashMap;

public class SSLCMerchantValidatorViewModel extends ViewModel implements SSLCApiHandlerListener {

    private Context context;
    private SSLCApiHandlerClass SSLCApiHandlerClass;
    private SSLCMerchantValidatorListener SSLCMerchantValidatorListener;

    public SSLCMerchantValidatorViewModel(Context context) {
        this.context = context;
        SSLCApiHandlerClass = new SSLCApiHandlerClass(context);
    }

    public void merchantValidator(String sessionkey, String store_id, String store_passwd,
                                  SSLCMerchantValidatorListener SSLCMerchantValidatorListener) {
        this.SSLCMerchantValidatorListener = SSLCMerchantValidatorListener;

        HashMap<String, Object> mHashMap = new HashMap<>();

        mHashMap.put("sessionkey", sessionkey);
        mHashMap.put("store_id", store_id);
        mHashMap.put("store_passwd", store_passwd);
        mHashMap.put("lang", SSLCPrefUtils.getPreferenceLanguageValue(context));

        if (SSLCShareInfo.getInstance().isNetworkAvailable(context)) {
        SSLCApiHandlerClass.sslCommerzRequest(context,
                SSLCShareInfo.getInstance().getType(context).equals(SSLCSdkType.LIVE) ?
                        BuildConfig.MERCHANT_VALIDATOR_LIVE_URL :
                        BuildConfig.MERCHANT_VALIDATOR_SANDBOX_URL, "merchantTransIDvalidationAPI.php",
                SSLCMethodIndentification.METHOD_GET, "", mHashMap,
                false, this);
        } else {
            SSLCMerchantValidatorListener.merchantValidatorFail(context.getResources().getString(R.string.internet_connection));
        }
    }

    @Override
    public void success(JSONObject jsonObject) {
        Gson gson = new Gson();
        SSLCTransactionInfoModel transactionInfo = gson.fromJson(jsonObject.toString(),
                SSLCTransactionInfoModel.class);

        SSLCMerchantValidatorListener.merchantValidatorSuccess(transactionInfo);
    }

    @Override
    public void fail(String message) {

    }
}
