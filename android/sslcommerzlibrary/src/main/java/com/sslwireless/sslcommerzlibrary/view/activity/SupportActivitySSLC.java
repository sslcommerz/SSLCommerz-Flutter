package com.sslwireless.sslcommerzlibrary.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.view.custom.SSLCCustomTextView;

public class SupportActivitySSLC extends SSLCBaseActivity {

    private Context context;
    private String mobileNumber, email, fbMessenger;
    private LinearLayout clickMessenger, clickEmail, clickCallSupport;
    private SSLCCustomTextView customerCareText;

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
        setContentView(R.layout.activity_ssl_support);
        context = this;
    }

    @Override
    public void viewRelatedTask() {
        mobileNumber = getIntent().getStringExtra("mobileNumber");
        email = getIntent().getStringExtra("email");
        fbMessenger = getIntent().getStringExtra("fbMessenger");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.support));
        }

        customerCareText = findViewById(R.id.customerCareText);
        clickMessenger = findViewById(R.id.clickMessenger);
        clickEmail = findViewById(R.id.clickEmail);
        clickCallSupport = findViewById(R.id.clickSupportCall);

        clickEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", email, null));
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        customerCareText.setText(getResources().getString(R.string.custumer_care_text) + " " + mobileNumber);

        clickMessenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SupportActivitySSLC.this, FAQActivitySSLC.class);
                intent.putExtra("url", fbMessenger);
                intent.putExtra("checker", 1);
                startActivity(intent);
            }
        });

        clickCallSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL,
                        Uri.fromParts("tel", mobileNumber, null));
                startActivity(intent);
            }
        });

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-event-name"));
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
