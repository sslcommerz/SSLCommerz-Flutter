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
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.sslwireless.sslcommerzlibrary.R;

public class FAQActivitySSLC extends SSLCBaseActivity {
    private Context context;
    private WebView faqWebView;
    private ProgressBar bankPageProgress;
    private String url;
    private int checker = 0;

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
        setContentView(R.layout.activity_ssl_faq);
        context = this;

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-event-name"));
    }

    @Override
    public void viewRelatedTask() {
        url = getIntent().getStringExtra("url");
        checker = getIntent().getIntExtra("checker", 0);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

            if (checker == 1) {
                getSupportActionBar().setTitle("Support");
            } else if (checker == 2) {
                getSupportActionBar().setTitle("More Info");
            } else {
                getSupportActionBar().setTitle("FAQ");
            }
        }
        faqWebView = findViewById(R.id.faqWebView);
        bankPageProgress = findViewById(R.id.bankPageProgress);

        showTheWebsite(url);
    }

    private void showTheWebsite(String url) {
        WebViewClient webViewClient = new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
//                Log.e("Asif", "onPageFinished: " + url);
                bankPageProgress.setVisibility(View.GONE);
            }

            @SuppressLint("NewApi")
            @Override
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(FAQActivitySSLC.this);
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

        faqWebView.getSettings().setLoadsImagesAutomatically(true);
        faqWebView.getSettings().setJavaScriptEnabled(true);
        faqWebView.getSettings().setDomStorageEnabled(true);
        faqWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        faqWebView.setWebViewClient(webViewClient);
        faqWebView.loadUrl(url);

        faqWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                bankPageProgress.setProgress(newProgress);
            }
        });
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
