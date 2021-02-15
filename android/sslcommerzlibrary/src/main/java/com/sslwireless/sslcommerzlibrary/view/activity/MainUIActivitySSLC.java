package com.sslwireless.sslcommerzlibrary.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import androidx.annotation.Nullable;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCEMIModel;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCGetDeviceTokenModel;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCLogOutModel;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCOfferModel;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCSdkMainResponseModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCreditCardUtils;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCDownloadImageTask;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCEnums;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCPrefUtils;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCProgressBarHandler;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;
import com.sslwireless.sslcommerzlibrary.view.custom.SSLCCustomTextView;
import com.sslwireless.sslcommerzlibrary.view.fragment.SSLCCardFragment;
import com.sslwireless.sslcommerzlibrary.view.fragment.SSLCMobileBankingFragment;
import com.sslwireless.sslcommerzlibrary.view.fragment.SSLCNetBankingFragment;
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz;
import com.sslwireless.sslcommerzlibrary.view.viewpager.SSLCViewPagerAdapter;
import com.sslwireless.sslcommerzlibrary.viewmodel.SSLCEMIViewModel;
import com.sslwireless.sslcommerzlibrary.viewmodel.SSLCGetDeviceTokenViewModel;
import com.sslwireless.sslcommerzlibrary.viewmodel.SSLCGetOfferViewModel;
import com.sslwireless.sslcommerzlibrary.viewmodel.SSLCLogOutViewModel;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCDowloadImageListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCEMIListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCGetDeviceTokenListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCGetOfferListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCLogOutListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCOnBtnPayActiveListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCOnLogOutListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCOnOfferSelectListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCOnUserVerifyListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCPayNowListener;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainUIActivitySSLC extends SSLCBaseActivity implements SSLCGetDeviceTokenListener, SSLCOnUserVerifyListener,
        SSLCOnBtnPayActiveListener {

    SSLCSdkMainResponseModel SSLCSdkMainResponseModel;
    SSLCPayNowListener payClickListener;
    SSLCOnOfferSelectListener SSLCOnOfferSelectListener;
    SSLCOnLogOutListener SSLCOnLogOutListener;
    TextView tvUserName, tvBng;
    ImageView ivLanguage, merchantLogo, ivPay;
    TextView tvMerchantName, tvPay, tvLogout, tvAmountHeader, tvAmount, tvChargeHeader, tvCharge;
    LinearLayout layoutPay;
    RelativeLayout layoutAmount;
    SSLCEMIModel SSLCEMIModel;
    SSLCOfferModel SSLCOfferModel;
    SSLCProgressBarHandler SSLCProgressBarHandler;
    private SSLCommerzInitialization sslCommerzInitialization;
    private Context context;
    private SSLCViewPagerAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private long lastBackPressTime = 0;
    private boolean oneTimeFlag = false;
    private LinearLayout clickFAQ, clickSupport, badgeLayout;
    private SSLCCustomTextView badge;
    private SSLCCustomTextView tvSupport, tvFaq, tvOffer;
    private CollapsingToolbarLayout collapsingToolbar;
    private SSLCCustomTextView timerText;
    private CountDownTimer timer;
    private LinearLayout mainLyout1;
    private TabLayout.Tab tab;
    private int counterTab = -1;
    private int totalTabCount = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MyApplication.getInstance().changeLanguage(this);
        setContentView(R.layout.activity_ssl_sdk_main);

        context = this;
        sslCommerzInitialization = (SSLCommerzInitialization) getIntent().
                getSerializableExtra("sslCommerzInitialerData");

        Bundle bun = getIntent().getExtras();
        if (getIntent().hasExtra("main_response")) {
            SSLCSdkMainResponseModel = new SSLCSdkMainResponseModel();
            SSLCSdkMainResponseModel = SSLCSdkMainResponseModel.fromJSON(bun.getString(SSLCShareInfo.main_response));


        }

        SSLCProgressBarHandler = new SSLCProgressBarHandler(this, Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("");
        }

        layoutPay = findViewById(R.id.layout_pay);
        ivPay = findViewById(R.id.iv_pay);
        tvPay = findViewById(R.id.tv_pay);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabs);
        ivLanguage = findViewById(R.id.iv_language);
        tvMerchantName = findViewById(R.id.tv_merchant_name);
        tvLogout = findViewById(R.id.tv_logout);
        merchantLogo = findViewById(R.id.merchant_logo_iv);
        clickFAQ = findViewById(R.id.clickFAQ);
        clickSupport = findViewById(R.id.clickSupport);
        badgeLayout = findViewById(R.id.badgeLayout);
        badge = findViewById(R.id.badge);
        tvUserName = findViewById(R.id.tv_user_name);
        layoutAmount = findViewById(R.id.layout_amount);
        tvAmount = findViewById(R.id.tv_amount);
        tvCharge = findViewById(R.id.tv_charge);
        tvAmountHeader = findViewById(R.id.tv_amount_header);
        tvChargeHeader = findViewById(R.id.tv_charge_header);
        tvSupport = findViewById(R.id.tv_support);
        tvFaq = findViewById(R.id.tv_faq);
        tvOffer = findViewById(R.id.tv_offer);
        timerText = findViewById(R.id.timerText);
        mainLyout1 = findViewById(R.id.mainLyout1);
        collapsingToolbar = findViewById(R.id.collapsingToolbar);

        tvUserName.setVisibility(View.GONE);
        layoutAmount.setVisibility(View.GONE);

        layoutPay.setEnabled(false);
        SSLCShareInfo.getInstance().setToGray(ivPay);
        tvPay.setTextColor(Color.GRAY);

        tvSupport.setTextColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        tvFaq.setTextColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        tvOffer.setTextColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        tvLogout.setTextColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        tvChargeHeader.setTextColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        tvCharge.setTextColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        tvAmountHeader.setTextColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        tvAmount.setTextColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));

        clickFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainUIActivitySSLC.this, FAQActivitySSLC.class);
                intent.putExtra("url", SSLCSdkMainResponseModel.getFAQURL());
                startActivity(intent);
            }
        });

        clickSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainUIActivitySSLC.this, SupportActivitySSLC.class);
                intent.putExtra("mobileNumber", SSLCSdkMainResponseModel.getSupportPhone());
                intent.putExtra("email", SSLCSdkMainResponseModel.getSupportEmail());
                intent.putExtra("fbMessenger", SSLCSdkMainResponseModel.getFacebookPageURL());
                startActivity(intent);
            }
        });

        findViewById(R.id.layout_top_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvLogout.getText().toString().equalsIgnoreCase(getResources().getString(R.string.sdk_logout))) {
                    SSLCProgressBarHandler.show();
                    SSLCLogOutViewModel SSLCLogOutViewModel = new SSLCLogOutViewModel(MainUIActivitySSLC.this);
                    SSLCLogOutViewModel.submitLogout(SSLCShareInfo.getInstance().getCustSession(MainUIActivitySSLC.this),
                            SSLCShareInfo.getInstance().getRegKey(MainUIActivitySSLC.this),
                            SSLCShareInfo.getInstance().getEncKey(MainUIActivitySSLC.this), new SSLCLogOutListener() {
                                @Override
                                public void logOutSuccess(SSLCLogOutModel SSLCLogOutModel) {
                                    SSLCProgressBarHandler.hide();
                                    if (SSLCLogOutModel.getStatus().toLowerCase().contains(SSLCEnums.StatusType.SUCCESS.name().toLowerCase())) {
                                        SSLCOnLogOutListener.logOutSuccess(false);
                                        tvLogout.setText(getResources().getString(R.string.sdk_login));
                                        tabLayout.getTabAt(0).select();
                                        tvUserName.setVisibility(View.GONE);
                                        tvMerchantName.setText(SSLCSdkMainResponseModel.getStoreName());

                                        SSLCShareInfo.motoMap.clear();
                                        SSLCShareInfo.getInstance().saveCustSession(MainUIActivitySSLC.this, "");
                                        SSLCShareInfo.getInstance().saveMobileNo(MainUIActivitySSLC.this, "");
                                        SSLCPrefUtils.setLoggedIn(context, false);
                                    }
                                    SSLCShareInfo.getInstance().showToast(MainUIActivitySSLC.this, SSLCLogOutModel.getMessage());
                                }

                                @Override
                                public void logOutFail(String message) {
                                    SSLCProgressBarHandler.hide();
                                    SSLCShareInfo.getInstance().showToast(MainUIActivitySSLC.this, message);
                                }
                            });
                } else {
                    tabLayout.getTabAt(0).select();
                    tvLogout.setText(getResources().getString(R.string.sdk_login));
                    SSLCOnLogOutListener.logOutSuccess(true);
                }
            }
        });

//        if(PrefUtils.isLanguageChange()){
//            if(PrefUtils.isLoggedIn()){
//                tvLogout.setText(getResources().getString(R.string.logout));
//            }
//        }
        if (SSLCPrefUtils.getPreferenceLanguageValue(context).contains("en")) {
            ivLanguage.setImageResource(R.drawable.ic_eng);
        } else {
            ivLanguage.setImageResource(R.drawable.ic_bng);
        }
        //setLocale();
        //ShareInfo.getInstance().updateLanguage();
        onConfigurationChanged(SSLCShareInfo.getInstance().updateLanguage(MainUIActivitySSLC.this));
        ivLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lang = SSLCPrefUtils.getPreferenceLanguageValue(context);
                if (lang.contains("bn")) {
                    SSLCPrefUtils.setPreferenceLanguageValue(context, "en");
                    ivLanguage.setImageResource(R.drawable.ic_eng);
                } else {
                    SSLCPrefUtils.setPreferenceLanguageValue(context, "bn");
                    ivLanguage.setImageResource(R.drawable.ic_bng);
                }
                //setLocale();
                //PrefUtils.setLanguageChange(true);
                onConfigurationChanged(SSLCShareInfo.getInstance().updateLanguage(MainUIActivitySSLC.this));
            }
        });

        if (!SSLCShareInfo.getInstance().getlastHitSDKType(context).
                equals(sslCommerzInitialization.getSdkType())) {
            SSLCShareInfo.getInstance().saveCustSession(context, "");
            SSLCShareInfo.getInstance().saveMobileNo(context, "");
            SSLCShareInfo.getInstance().saveRegId(context, "");
            SSLCShareInfo.getInstance().saveEncKey(context, "");
        }

        if (SSLCShareInfo.getInstance().getRegKey(this).isEmpty() ||
                SSLCShareInfo.getInstance().getEncKey(this).isEmpty()) {
            String android_id = Settings.Secure.getString(getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            String manufacturer = Build.MANUFACTURER;

            SSLCShareInfo.getInstance().lastHitSDKType(context, sslCommerzInitialization.getSdkType());

            SSLCGetDeviceTokenViewModel SSLCGetDeviceTokenViewModel = new SSLCGetDeviceTokenViewModel(this);
            SSLCGetDeviceTokenViewModel.submitDeviceToken("Android", android_id,
                    "no device id",
                    SSLCShareInfo.getInstance().getDeviceName(this), manufacturer, this);
        } else {
            loadOfferModel();
            //setupViewPager(viewPager);
        }

        /*inactivityTimeout = InactivityTimeout.getInstance();
        inactivityTimeout.setInactivityTimeout();

        long res = Integer.parseInt(sdkMainResponseModel.getTimeoutinMin()) * 60 * 1000;

        inactivityTimeout.startHandler((int) res);*/

        mainLyout1.setVisibility(View.VISIBLE);
        startCountDown();
    }

    public void startCountDown() {
        timer = new CountDownTimer(Long.valueOf(SSLCSdkMainResponseModel.getTimeoutinMin()) * 60 * 1000, 1000) {
            //timer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                String hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                timerText.setText("Session Time: " + hms);
            }

            public void onFinish() {
                //editTextUpdateListener.clearText();
                Intent intent1 = new Intent("custom-event-name");
                intent1.putExtra("message", "message!");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent1);

                timerText.setText("00:00");
                IntegrateSSLCommerz.SSLCTransactionResponseListener.transactionFail("Session Time Out");
                Intent intent = getIntent();
                setResult(RESULT_OK, intent);
                finish();
            }
        }.start();
    }

    @Override
    public void viewRelatedTask() {
        SSLCDownloadImageTask SSLCDownloadImageTask = new SSLCDownloadImageTask(context,
                merchantLogo, SSLCSdkMainResponseModel.getStoreLogo(), new SSLCDowloadImageListener() {
            @Override
            public void downloadSuccess(Bitmap bitmap) {
                merchantLogo.setImageBitmap(bitmap);
            }

            @Override
            public void downloadFailed(String message) {
                SSLCShareInfo.getInstance().showToast(context, message);
            }
        });

        tvMerchantName.setText(SSLCSdkMainResponseModel.getStoreName());
        tvPay.setText(getResources().getString(R.string.pay) + " " + SSLCSdkMainResponseModel.getAmount() + " BDT");
        layoutPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payClickListener.onPayClick();
            }
        });

        findViewById(R.id.layout_offer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // editTextUpdateListener.clearText();
                Intent intent = new Intent(MainUIActivitySSLC.this, SSLOffersActivitySSLC.class);
                intent.putExtra(SSLCShareInfo.main_response, SSLCSdkMainResponseModel.toJSON());
                intent.putExtra("sslCommerzInitialerData", sslCommerzInitialization);
                intent.putExtra("offer_model", new Gson().toJson(SSLCOfferModel));
                startActivityForResult(intent, SSLCEnums.Common.Activity1.ordinal());
            }
        });
    }

    public void setupViewPager(ViewPager viewPager) {
        adapter = new SSLCViewPagerAdapter(getSupportFragmentManager());
        //CardFragment cardFragment = CardFragment.newInstance(sdkMainResponseModel.toJSON());

      //  Log.e("TAGResposne", "setupViewPager: " + new Gson().toJson(SSLCSdkMainResponseModel.getGw()));

        //Log.e("ASIF", "setupViewPager: " + SSLCSdkMainResponseModel.getDefault_tab());

        if (!SSLCSdkMainResponseModel.getGw().getAmex().equals("") || !SSLCSdkMainResponseModel.getGw().getMaster().equals("") ||
                !SSLCSdkMainResponseModel.getGw().getVisa().equals("")) {
            adapter.addFrag(SSLCCardFragment.newInstance(SSLCSdkMainResponseModel.toJSON(), sslCommerzInitialization,
                    new Gson().toJson(SSLCOfferModel), new Gson().toJson(SSLCEMIModel)),
                    getResources().getString(R.string.cards));
        } else {
            findViewById(R.id.layout_top_login).setVisibility(View.GONE);
            findViewById(R.id.layout_offer).setVisibility(View.GONE);
        }

        if (!SSLCSdkMainResponseModel.getGw().getMobilebanking().equals("")) {
            adapter.addFrag(SSLCMobileBankingFragment.newInstance(SSLCSdkMainResponseModel.toJSON(), sslCommerzInitialization),
                    getResources().getString(R.string.mobile_banking));
        }

        if (!SSLCSdkMainResponseModel.getGw().getInternetbanking().equals("")) {
            adapter.addFrag(SSLCNetBankingFragment.newInstance(SSLCSdkMainResponseModel.toJSON(), sslCommerzInitialization),
                    getResources().getString(R.string.net_banking));
        }

        if (!SSLCSdkMainResponseModel.getGw().getMobilebanking().equals("") && !SSLCSdkMainResponseModel.getGw().getInternetbanking().equals("")) {
            viewPager.setOffscreenPageLimit(3);
        } else if (SSLCSdkMainResponseModel.getGw().getMobilebanking().equals("") && !SSLCSdkMainResponseModel.getGw().getInternetbanking().equals("")) {
            viewPager.setOffscreenPageLimit(2);
        } else if (!SSLCSdkMainResponseModel.getGw().getMobilebanking().equals("") && SSLCSdkMainResponseModel.getGw().getInternetbanking().equals("")) {
            viewPager.setOffscreenPageLimit(2);
        }


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);

//        if (SSLCSdkMainResponseModel.getGw().getMobilebanking().equals("") && SSLCSdkMainResponseModel.getGw().getInternetbanking().equals("")) {
//            tabLayout.setVisibility(View.GONE);
//        }

       // Log.e("TAG", "setupViewPager: " + SSLCSdkMainResponseModel.getDefault_tab());

        viewPager.setAdapter(adapter);

        if (!SSLCSdkMainResponseModel.getGw().getMobilebanking().equals("")) {
            if (SSLCSdkMainResponseModel.getDefault_tab().equals("m")) {
                viewPager.setCurrentItem(1);
            }
        }

        if (!SSLCSdkMainResponseModel.getGw().getInternetbanking().equals("")) {
            if (SSLCSdkMainResponseModel.getDefault_tab().equals("i")) {
                viewPager.setCurrentItem(2);
            }
        }

        if(SSLCSdkMainResponseModel.getGw().getAmex().equals("") && SSLCSdkMainResponseModel.getGw().getMaster().equals("") &&
                SSLCSdkMainResponseModel.getGw().getVisa().equals("")){
            if (SSLCSdkMainResponseModel.getDesc().size() == 1) {
                tabLayout.setVisibility(View.GONE);

                Intent intent = new Intent(context, WebViewActivitySSLC.class);
                intent.putExtra("redirectUrl", SSLCSdkMainResponseModel.getDesc().get(0).getRedirectGatewayURL());
                intent.putExtra("merchantName", SSLCSdkMainResponseModel.getDesc().get(0).getName());
                intent.putExtra("session_key", SSLCSdkMainResponseModel.getSessionkey());
                intent.putExtra("sdkMainResponse", sslCommerzInitialization);
                intent.putExtra("timeOutValue", SSLCSdkMainResponseModel.getTimeoutinMin());
                startActivityForResult(intent, SSLCEnums.Common.Activity2.ordinal());
            }
        }

        tabLayout.setBackgroundColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        tabLayout.setTabTextColors(Color.parseColor("#" + SSLCSdkMainResponseModel.getDesign().getTitleFontColor()),
                Color.parseColor("#" + SSLCSdkMainResponseModel.getDesign().getTitleFontColor()));
        tabLayout.setSelectedTabIndicatorHeight(140);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getActiveColor()));
    }

    @Override
    public void deviceTokenSuccess(SSLCGetDeviceTokenModel SSLCGetDeviceTokenModel) {
        if (SSLCGetDeviceTokenModel.getStatus().toLowerCase().contains(SSLCEnums.StatusType.SUCCESS.toString().toLowerCase())) {
            SSLCShareInfo.getInstance().saveRegId(this, SSLCGetDeviceTokenModel.getData().getRegId());
            SSLCShareInfo.getInstance().saveEncKey(this, SSLCGetDeviceTokenModel.getData().getEncKey());

            loadOfferModel();

        } else {
            finish();
        }
    }

    private void loadOfferModel() {
        if (SSLCSdkMainResponseModel.getOfferStatus() == 1) {
            SSLCProgressBarHandler.show();
            SSLCGetOfferViewModel SSLCGetOfferViewModel = new SSLCGetOfferViewModel(this);
            SSLCGetOfferViewModel.getGetOffer(SSLCSdkMainResponseModel.getLoginTransSession(),
                    SSLCShareInfo.getInstance().getRegKey(this),
                    SSLCShareInfo.getInstance().getEncKey(this), new SSLCGetOfferListener() {
                        @Override
                        public void resendOtpSuccess(final SSLCOfferModel SSLCOfferModelObj) {
                            SSLCProgressBarHandler.hide();
//                            Log.e("offerModelObj", "resendOtpSuccess: " + new Gson().toJson(SSLCOfferModelObj));
                            if (SSLCOfferModelObj.getStatus().toLowerCase().contains(SSLCEnums.StatusType.SUCCESS.toString().toLowerCase())) {
                                SSLCOfferModel = SSLCOfferModelObj;
                                if (SSLCOfferModel.getData().getData().getDiscountList().size() > 0) {
                                    badgeLayout.setVisibility(View.VISIBLE);
                                    badge.setText(String.valueOf(SSLCOfferModel.getData().getData().getDiscountList().size()));

                                    Drawable background = badgeLayout.getBackground();
                                    int color = Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor());

                                    if (background instanceof GradientDrawable) {
                                        ((GradientDrawable) background).setColor(color);
                                    }
                                } else {
                                    badgeLayout.setVisibility(View.GONE);
                                }

                                loadEmiModel();
                                //mOfferToMainUIListener.getOfferToMainUi(offerModelObj);

                            } else {
                                finish();
                                SSLCShareInfo.getInstance().showToast(MainUIActivitySSLC.this, SSLCOfferModelObj.getMessage());
                            }
                        }

                        @Override
                        public void resendOtpFail(String message) {
                            SSLCProgressBarHandler.hide();
                            SSLCShareInfo.getInstance().showToast(MainUIActivitySSLC.this, message);
                            finish();
                        }
                    });
        } else {
            loadEmiModel();
        }
    }

    private void loadEmiModel() {
        if (SSLCSdkMainResponseModel.getEmiStatus() == 1) {
            SSLCProgressBarHandler.show();
            SSLCEMIViewModel SSLCEMIViewModel = new SSLCEMIViewModel(this);
            SSLCEMIViewModel.getEMI(SSLCSdkMainResponseModel.getLoginTransSession(), SSLCShareInfo.getInstance().getRegKey(this), SSLCShareInfo.getInstance().getEncKey(this), new SSLCEMIListener() {
                @Override
                public void emiSuccess(SSLCEMIModel SSLCEMIModelObj) {
                    SSLCProgressBarHandler.hide();
                    if (SSLCEMIModelObj.getStatus().toLowerCase().contains(SSLCEnums.StatusType.SUCCESS.name().toLowerCase())) {
                        SSLCEMIModel = SSLCEMIModelObj;

                        setupViewPager(viewPager);
                    } else {
                        SSLCShareInfo.getInstance().showToast(MainUIActivitySSLC.this, SSLCEMIModelObj.getMessage());
                        finish();
                    }
                }

                @Override
                public void emiFail(String message) {
                    SSLCProgressBarHandler.hide();
                    SSLCShareInfo.getInstance().showToast(MainUIActivitySSLC.this, message);
                    finish();
                }
            });
        } else {
            setupViewPager(viewPager);
        }
    }

    @Override
    public void deviceTokenFail(String message) {
        SSLCShareInfo.getInstance().showToast(this, "Transaction Failed");
        finish();
    }

    public void setOnPayClickListener(SSLCPayNowListener payClickListener) {
        this.payClickListener = payClickListener;
    }

    public void setOnOfferSelectListener(SSLCOnOfferSelectListener SSLCOnOfferSelectListener) {
        this.SSLCOnOfferSelectListener = SSLCOnOfferSelectListener;
    }

    public void setOnLogoutClickListener(SSLCOnLogOutListener onLogoutClickListener) {
        this.SSLCOnLogOutListener = onLogoutClickListener;
    }

    @Override
    public void onUserVerify(String name) {
        tvUserName.setText(name);
        tvLogout.setText(R.string.sdk_logout);
        //tvLogout.setVisibility(View.VISIBLE);
        tvUserName.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBtnPayActive(Boolean isActive, String value) {
        if (isActive) {
            //initColor(false, sdkMainResponseModel);
            layoutPay.setEnabled(true);
            layoutPay.setBackgroundColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
            tvPay.setTextColor(Color.WHITE);
//            layoutAmount.setVisibility(View.GONE);
            SSLCShareInfo.getInstance().disableGray(ivPay);
//            Log.e("pay color", "White");
            tvAmount.setText("0.00 BDT");
            tvCharge.setText("0.00 BDT");
            tvPay.setText(getResources().getString(R.string.pay) + " " + "0.00 BDT");

            //value = value.replace(" ", "");
            if (value.matches("[0-9 X]+")) {
                for (SSLCSdkMainResponseModel.Desc desc : SSLCSdkMainResponseModel.getDesc()) {
                    if (desc.getName().toLowerCase().contains(SSLCCreditCardUtils.getInstance().cardType(value).toLowerCase())) {
                        tvAmount.setText(desc.getTransAmt() + " BDT");
                        tvCharge.setText(desc.getCharge() + " BDT");
                        tvPay.setText(getResources().getString(R.string.pay) + " " + desc.getPayableAmt() + " BDT");
                    }
                }
            } else {
                for (SSLCSdkMainResponseModel.Desc desc : SSLCSdkMainResponseModel.getDesc()) {
                    if (desc.getName().contains(value)) {
                        tvAmount.setText(desc.getTransAmt() + " BDT");
                        tvCharge.setText(desc.getCharge() + " BDT");
                        tvPay.setText(getResources().getString(R.string.pay) + " " + desc.getPayableAmt() + " BDT");
                    }
                }
            }

            if (tvCharge.getText().equals("0 BDT") || tvCharge.getText().equals("0.00 BDT")) {
                layoutAmount.setVisibility(View.GONE);
            } else {
                layoutAmount.setVisibility(View.VISIBLE);
            }
        } else {
            SSLCShareInfo.getInstance().setToGray(ivPay);
            tvPay.setTextColor(Color.GRAY);
            layoutPay.setBackgroundColor(getResources().getColor(R.color.white_off));
            layoutPay.setEnabled(false);
            layoutAmount.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
                    SSLCShareInfo.getInstance().showToast(MainUIActivitySSLC.this, "Press once again to exit");
                    this.lastBackPressTime = System.currentTimeMillis();
                } else {
                    timer.cancel();
                    finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            SSLCShareInfo.getInstance().showToast(MainUIActivitySSLC.this, "Press once again to exit");
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
            timer.cancel();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SSLCEnums.Common.Activity1.ordinal() && resultCode == RESULT_OK) {
            viewPager.setCurrentItem(0);
            String idd = data.getStringExtra("id");
            String image = data.getStringExtra("image");
            String title = data.getStringExtra("title");
            String max_discount = data.getStringExtra("max_discount");
            ArrayList<String> offer_bins = data.getStringArrayListExtra("offer_bins");
            SSLCOnOfferSelectListener.onOfferSelect(idd, image, title, max_discount, offer_bins);
        } else if (requestCode == SSLCEnums.Common.Activity2.ordinal() && resultCode == RESULT_OK) {
            finish();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!SSLCShareInfo.getInstance().isNetworkAvailable(this)) {
            SSLCShareInfo.getInstance().showToast(this, getResources().getString(R.string.internet_connection));
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        SSLCPrefUtils.setLoggedIn(context, false);
        SSLCShareInfo.motoMap.clear();
    }

    public void setLocale() {
        String language = SSLCPrefUtils.getPreferenceLanguageValue(context);
        Locale myLocale = new Locale(language);
        Configuration conf = getResources().getConfiguration();
        conf.setLocale(myLocale);
        getResources().updateConfiguration(conf, getResources().getDisplayMetrics());
        onConfigurationChanged(conf);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        counterTab = -1;

        tvSupport.setText(R.string.support);
        tvFaq.setText(R.string.faq);
        tvOffer.setText(R.string.offers);
        tvAmountHeader.setText(R.string.amount_colon);
        tvChargeHeader.setText(R.string.additional_fees_colon);

        //String logValue = tvLogout.getText().toString();
        // if (logValue.equalsIgnoreCase("লগ আউট") || logValue.equalsIgnoreCase("Log Out")) {
        if (SSLCPrefUtils.isLoggedIn(context)) {
            tvLogout.setText(R.string.sdk_logout);
        } else {
            tvLogout.setText(R.string.sdk_login);
        }

        tvMerchantName.setText(SSLCSdkMainResponseModel.getStoreName());
        tvPay.setText(getResources().getString(R.string.pay) + " " + SSLCSdkMainResponseModel.getAmount() + " BDT");

        totalTabCount = tabLayout.getTabCount();

        //if (tab != null) {
        if (totalTabCount == 1) {
            tab = tabLayout.getTabAt(0);
            if (!SSLCSdkMainResponseModel.getGw().getAmex().equals("") || !SSLCSdkMainResponseModel.getGw().getMaster().equals("") ||
                    !SSLCSdkMainResponseModel.getGw().getVisa().equals("")) {
                tab.setText(R.string.cards);
            } else if (!SSLCSdkMainResponseModel.getGw().getMobilebanking().equals("")) {
                tab.setText(R.string.mobile_banking);
            } else if (!SSLCSdkMainResponseModel.getGw().getInternetbanking().equals("")) {
                tab.setText(R.string.net_banking);
            }
        } else if (totalTabCount == 2) {
//            tab = tabLayout.getTabAt(0);
//            tab = tabLayout.getTabAt(1);
            if (!SSLCSdkMainResponseModel.getGw().getAmex().equals("") || !SSLCSdkMainResponseModel.getGw().getMaster().equals("") ||
                    !SSLCSdkMainResponseModel.getGw().getVisa().equals("")) {
                tab = tabLayout.getTabAt(++counterTab);
                tab.setText(R.string.cards);
            }

            if (!SSLCSdkMainResponseModel.getGw().getMobilebanking().equals("")) {
                tab = tabLayout.getTabAt(++counterTab);
                tab.setText(R.string.mobile_banking);
            }

            if (!SSLCSdkMainResponseModel.getGw().getInternetbanking().equals("")) {
                tab = tabLayout.getTabAt(++counterTab);
                tab.setText(R.string.net_banking);
            }
        } else if (totalTabCount == 3) {
            tab = tabLayout.getTabAt(0);
            tab.setText(R.string.cards);

            tab = tabLayout.getTabAt(1);
            tab.setText(R.string.mobile_banking);

            tab = tabLayout.getTabAt(2);
            tab.setText(R.string.net_banking);
        }
        //}
    }
}
