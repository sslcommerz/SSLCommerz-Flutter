package com.sslwireless.sslcommerzlibrary.view.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;
import com.sslwireless.sslcommerzlibrary.view.custom.SSLCCustomTextView;
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz;
import com.sslwireless.sslcommerzlibrary.viewmodel.SSLCMerchantValidatorViewModel;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCMerchantValidatorListener;

public class WebViewActivitySSLC extends SSLCBaseActivity implements SSLCMerchantValidatorListener {

    private WebView bankPage;
    private ProgressBar bankPageProgress;
    private String redirectUrl, merchantName, sessionKey, timeOutValue;
    private SSLCMerchantValidatorViewModel SSLCMerchantValidatorViewModel;
    private SSLCommerzInitialization mobileSslCommerzInitialization;
    private int OTP_RESEND_TIMER = 30;
    private CountDownTimer timer;
    private SSLCCustomTextView timerText;
    private LinearLayout mainLyout1;
    boolean motoEnable = false;
    private String savedCardIndex="";

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssl_web_view);

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-event-name"));
    }

    @Override
    public void viewRelatedTask() {
        timerText = findViewById(R.id.timerText);
        mainLyout1 = findViewById(R.id.mainLyout1);

        redirectUrl = getIntent().getStringExtra("redirectUrl");
        merchantName = getIntent().getStringExtra("merchantName");
        sessionKey = getIntent().getStringExtra("session_key");
        timeOutValue = getIntent().getStringExtra("timeOutValue");

        if(getIntent().hasExtra("motoEnable")){
            motoEnable = getIntent().getBooleanExtra("motoEnable", false);
            savedCardIndex = getIntent().getStringExtra("savedCardIndex");
        }

        mobileSslCommerzInitialization = (SSLCommerzInitialization) getIntent().
                getSerializableExtra("sdkMainResponse");

        bankPage = findViewById(R.id.bankPage);
        bankPageProgress = findViewById(R.id.bankPageProgress);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(merchantName);
        }

        SSLCMerchantValidatorViewModel = new SSLCMerchantValidatorViewModel(this);
        showTheWebsite(redirectUrl);
    }

    private void showTheWebsite(String url) {
        WebViewClient webViewClient = new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
//                Log.e("log", "onPageFinished: " + url);
                if (url.equals(mobileSslCommerzInitialization.getSuccess_url())) {
//                    Log.e("log", "onPageFinished entered: " + url);
                    SSLCMerchantValidatorViewModel.merchantValidator(sessionKey,
                            mobileSslCommerzInitialization.getStore_id(), mobileSslCommerzInitialization.getStore_passwd(),
                            WebViewActivitySSLC.this);
                }
            }

            @SuppressLint("NewApi")
            @Override
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(WebViewActivitySSLC.this);
                builder.setMessage(R.string.notification_error_ssl_cert_invalid);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.proceed();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.cancel();
                    }
                });
                final AlertDialog dialog = builder.create();
                dialog.show();
            }
        };

        bankPage.getSettings().setLoadsImagesAutomatically(true);
        bankPage.getSettings().setJavaScriptEnabled(true);
        bankPage.getSettings().setDomStorageEnabled(true);
        bankPage.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        bankPage.setWebViewClient(webViewClient);
        if (SSLCShareInfo.getInstance().isURLString(redirectUrl)) {
            bankPage.loadUrl(url);
        } else {
            bankPage.loadData(url, "text/html;", "UTF-8");
        }

        bankPage.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                bankPageProgress.setProgress(newProgress);
            }
        });
    }

    @Override
    public void merchantValidatorSuccess(SSLCTransactionInfoModel transactionInfo) {
//        Log.e("log", "merchantValidatorSuccess: entered " + new Gson().toJson(transactionInfo));

        if (transactionInfo.getStatus().toLowerCase().equalsIgnoreCase("valid") ||
                transactionInfo.getStatus().toLowerCase().equalsIgnoreCase("validated")) {
            SSLCShareInfo.getInstance().showToast(this, "Transaction successful");

            IntegrateSSLCommerz.SSLCTransactionResponseListener.transactionSuccess(transactionInfo);
            Intent intent = getIntent();
            setResult(RESULT_OK, intent);
        } else {
            IntegrateSSLCommerz.SSLCTransactionResponseListener.transactionFail("Transaction Failed");
            if(motoEnable){
                SSLCShareInfo.motoMap.put(savedCardIndex, true);
            }
        }
        finish();
    }

    @Override
    public void merchantValidatorFail(String message) {
//        Log.e("Asif", "merchantValidatorFail: ");
        IntegrateSSLCommerz.SSLCTransactionResponseListener.transactionFail(message);

        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
