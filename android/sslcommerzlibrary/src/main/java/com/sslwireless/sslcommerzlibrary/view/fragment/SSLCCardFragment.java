package com.sslwireless.sslcommerzlibrary.view.fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCCardDeleteModel;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCEMIModel;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCOfferModel;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCResendOtpModel;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCSdkMainResponseModel;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCSendOtpToRegisterModel;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfo;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCVerifyLoginSessionModel;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCVerifyOtpAndLoginModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCAnimationManager;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCChangeCursorColor;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCreditCardUtils;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCEnums;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCPrefUtils;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCProgressBarHandler;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;
import com.sslwireless.sslcommerzlibrary.view.activity.FAQActivitySSLC;
import com.sslwireless.sslcommerzlibrary.view.activity.MainUIActivitySSLC;
import com.sslwireless.sslcommerzlibrary.view.activity.WebViewActivitySSLC;
import com.sslwireless.sslcommerzlibrary.view.adapter.SSLCOtherCardsAdapter;
import com.sslwireless.sslcommerzlibrary.view.adapter.SSLCSaveCardsAdapter;
import com.sslwireless.sslcommerzlibrary.view.custom.SSLCCustomEdittext;
import com.sslwireless.sslcommerzlibrary.view.custom.SSLCCustomTextView;
import com.sslwireless.sslcommerzlibrary.view.custom.SSLCCustomTextWatcher;
import com.sslwireless.sslcommerzlibrary.viewmodel.SSLCCardDeleteViewModel;
import com.sslwireless.sslcommerzlibrary.viewmodel.SSLCPayWithCardInfoViewModel;
import com.sslwireless.sslcommerzlibrary.viewmodel.SSLCPayWithStoredCardViewModel;
import com.sslwireless.sslcommerzlibrary.viewmodel.SSLCResendOtpViewModel;
import com.sslwireless.sslcommerzlibrary.viewmodel.SSLCSendOtpToRegisterViewModel;
import com.sslwireless.sslcommerzlibrary.viewmodel.SSLCVerifyLoginSessionViewModel;
import com.sslwireless.sslcommerzlibrary.viewmodel.SSLCVerifyOtpAndLoginViewModel;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCCardDeleteListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCEditTextUpdateListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCOfferToMainUIListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCOnBtnPayActiveListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCOnLogOutListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCOnOfferSelectListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCOnUserVerifyListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCPayNowListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCPayWithCardInfoListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCPayWithStoredCardListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCResendOtpListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCSendOtpToRegisterListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCVerifyLoginSessionListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCVerifyOtpAndLoginListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class SSLCCardFragment extends Fragment {
    private static SSLCommerzInitialization mobileSslCommerzInitialization;
    private static SSLCOfferToMainUIListener mSSLCOfferToMainUIListener;
    private static SSLCEditTextUpdateListener mSSLCEditTextUpdateListener;
    SSLCSaveCardsAdapter SSLCSaveCardsAdapter;
    SSLCOtherCardsAdapter SSLCOtherCardsAdapter;
    LinearLayout layoutEMI, layoutSaveCards, layoutOffer, layoutCardLogin;
    private SSLCSdkMainResponseModel SSLCSdkMainResponseModel;
    private CheckBox rememberMeCv;
    private SSLCCustomTextView tvResendOTP, tvChangeNumberOtp, tvMyCards, tvWelcome, tvAvailEmi, tvOtpHasEmi, tvOtpCardNumber, tvTermCondition,
            tvCardOfferTitle, tvSkipLogin, tvChangeNumber, tvCustomerName, tvOtherCards, tvRegistered, tvAlreadyRegistered, tvOtpHeader,
            tvOtpChangeCard;
    private SSLCCustomEdittext pin1, pin2, pin3, pin4, pin5, pin6, etCardNumber, etMobileNumber, etCardMobileNumber, etCardName, etCardExpireDate, etCardCVC;
    private LinearLayout layoutOtpExistingCard, layoutOtherCards, layoutCardInfo, layoutLogin, layoutMobileOTP, layoutCardField, layoutEMIInfo;
    private RelativeLayout layoutOTP;
    private Button btnMobileVerify, btnCardMobileVerify;
    private RecyclerView rvSaveCards, rvOtherCards; //
    private ImageView ivCardVisa, ivCardMaster, ivCardAmex, ivOtpCardType, ivOfferCancel;
    private SSLCProgressBarHandler SSLCProgressBarHandler;
    private SSLCOnUserVerifyListener SSLCOnUserVerifyListener;
    private SSLCOnBtnPayActiveListener SSLCOnBtnPayActiveListener;
    private SSLCOnLogOutListener SSLCOnLogOutListener;
    private ImageView ivEMIInfo, ivMoreInfo;
    private EditText etSavedCVV;
    LinearLayout layoutSavedCVV;
    private String selectedCardType = "", emiBankId = "0", offerId = "0", savedCardIndex = "0", savedCardEmiTenure = "0";
    boolean motoEnable = false;
    private SSLCEMIModel SSLCEMIModel;
    private List<String> tenures;
    private SSLCOfferModel SSLCOfferModel;
    private boolean isFromCardInfoRemember = false;
    private boolean backPressed = false;
    private View view1;
    private Context context;
    private Dialog dialog;
    SSLCCustomTextWatcher SSLCCustomTextWatcher;

    public static SSLCCardFragment newInstance(String param1, SSLCommerzInitialization sslCommerzInitialization,
                                               String offerModel, String emiModel) {
        mobileSslCommerzInitialization = sslCommerzInitialization;

        SSLCCardFragment fragment = new SSLCCardFragment();
        Bundle args = new Bundle();
        args.putSerializable("offer_model", offerModel);
        args.putSerializable("emi_model", emiModel);
        args.putString(SSLCShareInfo.main_response, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
        if (getArguments() != null) {
            SSLCSdkMainResponseModel = new SSLCSdkMainResponseModel();
            SSLCSdkMainResponseModel = SSLCSdkMainResponseModel.fromJSON(getArguments().getString(SSLCShareInfo.main_response));

            SSLCOfferModel = new Gson().fromJson(getArguments().getString("offer_model"), SSLCOfferModel.class);
            SSLCEMIModel = new Gson().fromJson(getArguments().getString("emi_model"), SSLCEMIModel.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_card_sslc, container, false);
        SSLCProgressBarHandler = new SSLCProgressBarHandler(getActivity(), Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViews();

        layoutOffer.setVisibility(View.GONE);
        layoutOTP.setVisibility(View.GONE);
        layoutEMIInfo.setVisibility(View.GONE);
        layoutCardLogin.setVisibility(View.GONE);
        layoutOtherCards.setVisibility(View.GONE);

        if (!SSLCSdkMainResponseModel.getCustMobile().isEmpty()) {
            etMobileNumber.setText(SSLCSdkMainResponseModel.getCustMobile());
            etMobileNumber.setEnabled(false);
            btnMobileVerify.setEnabled(true);
        } else {
            etMobileNumber.setEnabled(true);
            btnMobileVerify.setEnabled(false);
        }
        if (SSLCSdkMainResponseModel.getEmiStatus() == 1) {
            layoutEMIInfo.setVisibility(View.VISIBLE);
            layoutEMI.setEnabled(false);
        }

        //verifyOtpAndLoginViewModel.verifyOtpAndLogin(mobileNumber, ShareInfo.getInstance().getRegKey(getActivity()), ShareInfo.getInstance().getEncKey(getActivity()), sdkMainResponseModel.getSessionkey(), pinValue, new VerifyOtpAndLoginListener() {
        if (SSLCShareInfo.getInstance().getMobileNo(getActivity()).equalsIgnoreCase(SSLCSdkMainResponseModel.getCustMobile()) &&
                !SSLCShareInfo.getInstance().getCustSession(getActivity()).isEmpty()) {
            SSLCProgressBarHandler.show();
            SSLCVerifyLoginSessionViewModel SSLCVerifyLoginSessionViewModel = new SSLCVerifyLoginSessionViewModel(getActivity());
            SSLCVerifyLoginSessionViewModel.verifyOtpAndLogin(SSLCShareInfo.getInstance().getRegKey(getActivity()),
                    SSLCShareInfo.getInstance().getEncKey(getActivity()),
                    SSLCSdkMainResponseModel.getSessionkey(),
                    SSLCShareInfo.getInstance().getCustSession(getActivity()), new SSLCVerifyLoginSessionListener() {
                        @Override
                        public void verifyLoginSessionSuccess(SSLCVerifyLoginSessionModel SSLCVerifyLoginSessionModel) {
                            SSLCProgressBarHandler.hide();
                            if (SSLCVerifyLoginSessionModel.getStatus().toLowerCase().contains(SSLCEnums.StatusType.SUCCESS.name().toLowerCase())) {
                                if (SSLCSdkMainResponseModel.getExistingMobile() > 0) {
                                    if (SSLCSdkMainResponseModel.getNumberOfCardSaved() > 0) {
                                        etMobileNumber.setText(SSLCSdkMainResponseModel.getCustMobile());
                                        tvCustomerName.setText(SSLCSdkMainResponseModel.getCustomerName());
                                        btnMobileVerify.setEnabled(true);
                                        tvWelcome.setText(getActivity().getResources().getString(R.string.welcome_back_coma));
                                        layoutCardInfo.setVisibility(View.GONE);
                                        layoutMobileOTP.setVisibility(View.GONE);
                                        layoutSaveCards.setVisibility(View.VISIBLE);

                                        SSLCOnUserVerifyListener.onUserVerify(SSLCSdkMainResponseModel.getCustomerName());
                                        SSLCPrefUtils.setLoggedIn(context, true);

                                        if (SSLCVerifyLoginSessionModel.getData() != null && SSLCVerifyLoginSessionModel.getData().getData().getCardNos().size() > 0) {
                                            List<SSLCVerifyOtpAndLoginModel.CardNo> cardNos = new ArrayList<>();
                                            for (SSLCVerifyLoginSessionModel.CardNo cardNoObj : SSLCVerifyLoginSessionModel.getData().getData().getCardNos()) {
                                                SSLCVerifyOtpAndLoginModel.CardNo cardNo = new SSLCVerifyOtpAndLoginModel().new CardNo();
                                                cardNo.setCardNo(cardNoObj.getCardNo());
                                                cardNo.setType(cardNoObj.getType());
                                                cardNo.setCardindex(cardNoObj.getCardindex());
                                                cardNo.setBankName(cardNoObj.getBankName());
                                                cardNo.setWithoutOTP(cardNoObj.getWithoutOTP());
                                                cardNos.add(cardNo);
                                            }
                                            setRvSaveCards(cardNos);
                                        } else {
                                            layoutCardInfo.setVisibility(View.VISIBLE);
                                            layoutMobileOTP.setVisibility(View.GONE);
                                            layoutSaveCards.setVisibility(View.GONE);
                                        }

                                    } else {
                                        etMobileNumber.setText(SSLCSdkMainResponseModel.getCustMobile());
                                        tvCustomerName.setText(SSLCSdkMainResponseModel.getCustomerName());
                                        btnMobileVerify.setEnabled(true);
                                        tvWelcome.setText(getActivity().getResources().getString(R.string.welcome_back_coma));
                                        layoutCardInfo.setVisibility(View.GONE);
                                        layoutMobileOTP.setVisibility(View.VISIBLE);
                                        layoutOTP.setVisibility(View.GONE);
                                    }
                                } else {
                                    btnMobileVerify.setEnabled(false);
                                    tvWelcome.setText(getActivity().getResources().getString(R.string.welcome_back_coma));
                                    tvCustomerName.setText(getActivity().getResources().getString(R.string.guest));
                                    layoutCardInfo.setVisibility(View.VISIBLE);
                                    layoutMobileOTP.setVisibility(View.GONE);
                                }
                            } else {
                                //  ShareInfo.getInstance().saveCustSession(MainUIActivity.this, session);
                                SSLCShareInfo.getInstance().showToast(getActivity(), SSLCVerifyLoginSessionModel.getMessage());
                                etMobileNumber.setText(SSLCSdkMainResponseModel.getCustMobile());
                                tvCustomerName.setText(SSLCSdkMainResponseModel.getCustomerName());
                                btnMobileVerify.setEnabled(true);
                                tvWelcome.setText(getActivity().getResources().getString(R.string.welcome_back_coma));
                                layoutCardInfo.setVisibility(View.GONE);
                                layoutMobileOTP.setVisibility(View.VISIBLE);
                                SSLCShareInfo.getInstance().saveLoggedIn(getActivity(), false);
                            }
                        }

                        @Override
                        public void verifyLoginSessionFail(String message) {
                            SSLCProgressBarHandler.hide();
                            SSLCShareInfo.getInstance().saveCustSession(getActivity(), "");
                            SSLCShareInfo.getInstance().saveMobileNo(getActivity(), "");
                            tvWelcome.setText(getActivity().getResources().getString(R.string.welcome_back_coma));
                            layoutCardInfo.setVisibility(View.GONE);
                            layoutMobileOTP.setVisibility(View.VISIBLE);
                            layoutOTP.setVisibility(View.GONE);
                            SSLCShareInfo.getInstance().showToast(getActivity(), message);
                        }
                    });
        } else {
            SSLCShareInfo.getInstance().saveCustSession(getActivity(), "");
            SSLCShareInfo.getInstance().saveMobileNo(getActivity(), "");
            if (!SSLCSdkMainResponseModel.getCustMobile().isEmpty()) {
                etMobileNumber.setText(SSLCSdkMainResponseModel.getCustMobile());
                btnMobileVerify.setEnabled(true);
            } else {
                btnMobileVerify.setEnabled(false);
            }

            if (!SSLCSdkMainResponseModel.getCustomerName().isEmpty()) {
                tvCustomerName.setText(SSLCSdkMainResponseModel.getCustomerName());
            }
            tvWelcome.setText(getActivity().getResources().getString(R.string.welcome_back_coma));
            layoutCardInfo.setVisibility(View.GONE);
            layoutMobileOTP.setVisibility(View.VISIBLE);
            layoutOTP.setVisibility(View.GONE);

            if (SSLCSdkMainResponseModel.getExistingMobile() > 0) {
                if (SSLCSdkMainResponseModel.getNumberOfCardSaved() > 0) {
//                    if(PrefUtils.isLoggedIn()) {
//                        layoutSaveCards.setVisibility(View.GONE);
//                        layoutCardInfo.setVisibility(View.VISIBLE);
//                        layoutMobileOTP.setVisibility(View.GONE);
//                    }else {
                    layoutSaveCards.setVisibility(View.GONE);
                    layoutCardInfo.setVisibility(View.GONE);
                    layoutMobileOTP.setVisibility(View.VISIBLE);
                    layoutOTP.setVisibility(View.GONE);
                    // }
                } else {
                    layoutCardInfo.setVisibility(View.GONE);
                    layoutMobileOTP.setVisibility(View.VISIBLE);
                    layoutOTP.setVisibility(View.GONE);
                }
            } else if (SSLCSdkMainResponseModel.getExistingMobile() == 0) {
                layoutCardInfo.setVisibility(View.VISIBLE);
                layoutMobileOTP.setVisibility(View.GONE);
                layoutOTP.setVisibility(View.GONE);
            }
        }

        ((MainUIActivitySSLC) getActivity()).setOnPayClickListener(new SSLCPayNowListener() {
            @Override
            public void onPayClick() {
                funPay();
            }
        });

//        ((MainUIActivity) getActivity()).setEditClickListener(new EditTextUpdateListener() {
//            @Override
//            public void clearText() {
//                etCardNumber.setText("");
//            }
//        });

        ((MainUIActivitySSLC) getActivity()).setOnLogoutClickListener(new SSLCOnLogOutListener() {
            @Override
            public void logOutSuccess(boolean isForLogin) {
                if (isForLogin) {
                    if (layoutMobileOTP.getVisibility() != View.VISIBLE || layoutCardInfo.getVisibility() == View.VISIBLE) {
                        layoutMobileOTP.setVisibility(View.VISIBLE);
                        layoutLogin.setVisibility(View.VISIBLE);
                        layoutCardInfo.setVisibility(View.GONE);
                        layoutSaveCards.setVisibility(View.GONE);
                    }
                } else {
                    if (rememberMeCv.isChecked()) {
                        layoutCardInfo.setVisibility(View.VISIBLE);
                        layoutCardLogin.setVisibility(View.VISIBLE);
                        rememberMeCv.setChecked(false);
                    }
                    if (layoutSaveCards.getVisibility() == View.VISIBLE) {
                        layoutSaveCards.setVisibility(View.GONE);
                        layoutCardInfo.setVisibility(View.VISIBLE);
                    }
                    tvMyCards.setVisibility(View.GONE);
                }

                //layoutCardInfo.setVisibility(View.GONE);
                //layoutMobileOTP.setVisibility(View.VISIBLE);
                //layoutLogin.setVisibility(View.VISIBLE);
                //layoutSaveCards.setVisibility(View.GONE);
                //tvMyCards.setVisibility(View.GONE);
                //tvSkipLogin.setVisibility(View.VISIBLE);
                //layoutOtpExistingCard.setVisibility(View.GONE);

                //etCardExpireDate.setText("");
                //etCardNumber.setText("");
                //etCardName.setText("");
                // etCardCVC.setText("");
                //tvAvailEmi.setText(getActivity().getResources().getString(R.string.avail_emi));
            }
        });
        ((MainUIActivitySSLC) getActivity()).setOnOfferSelectListener(new SSLCOnOfferSelectListener() {
            @Override
            public void onOfferSelect(String id, String image, String title, String maxDiscount, ArrayList<String> offer_bins) {
                // tvOfferTitle.setText(title);
                //tvOfferDiscount.setText("Max discount: ৳" + maxDiscount);
//                new DownloadImageTask(getActivity(), ivOffer, image, new DowloadImageListener() {
//                    @Override
//                    public void downloadSuccess(Bitmap bitmap) {
//                        ivOffer.setImageBitmap(bitmap);
//                    }
//
//                    @Override
//                    public void downloadFailed(String message) {
//                        ShareInfo.getInstance().showToast(getActivity(), message);
//                    }
//                });
//                String cardNumber = etCardNumber.getText().toString();
//                if (cardNumber.length() >= 6) {
//                    String ccNum = cardNumber.substring(0, 6);
//                    tvCardOfferTitle.setVisibility(View.VISIBLE);
//                    tvCardOfferTitle.setText(getResources().getString(R.string.proceed_without_offer));
//
//                    for (OfferModel.DiscountList discount: offerModel.getData().getData().getDiscountList()) {
//                        for (String str : discount.getAllowedBIN()) {
//                            if (str.equalsIgnoreCase(ccNum)) {
//                                tvCardOfferTitle.setText(title);
//                            }
//                        }
//                    }
//
//                }
                SSLCShareInfo.getInstance().showToast(getActivity(), title + " selected");

                offerId = id;
                if (layoutCardInfo.getVisibility() == View.VISIBLE) {
                    etCardNumber.setText("");
                    etCardCVC.setText("");
                    etCardExpireDate.setText("");
                    etCardName.setText("");
                }

                layoutOffer.setVisibility(View.VISIBLE);
                tvCardOfferTitle.setText(title);

                if (layoutOtherCards.getVisibility() == View.VISIBLE) {
                    layoutCardField.setVisibility(View.VISIBLE);
                    layoutOtherCards.setVisibility(View.GONE);
                }
                //offerBins = offer_bins;
                //offerTitle = title;
//                rememberMeCv.setEnabled(false);
//                rememberMeCv.setChecked(false);
//                layoutCardLogin.setVisibility(View.GONE);
            }
        });

        etCardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // This method is invoked after user input text in EditText.
            @Override
            public void afterTextChanged(Editable editable) {
                funCheckCardField();

                String ccNum = editable.toString().
                        replaceAll("\\s+", "");

                funSetCardType(ccNum);

                if (ccNum.length() == 6) {
                    tenures = getTenures(ccNum);
                    if (tenures.size() > 1) {
                        layoutEMI.setEnabled(true);
                    } else {
                        layoutEMI.setEnabled(false);
                    }

                    if (SSLCSdkMainResponseModel.getOfferStatus() == 1) {
                        layoutOffer.setVisibility(View.GONE);
                        tvCardOfferTitle.setText(getResources().getString(R.string.proceed_without_offer));
                        if (SSLCOfferModel != null) {
                            for (SSLCOfferModel.DiscountList discount : SSLCOfferModel.getData().getData().getDiscountList()) {
                                for (String str : discount.getAllowedBIN()) {
                                    if (str.equalsIgnoreCase(ccNum)) {
                                        layoutOffer.setVisibility(View.VISIBLE);
                                        offerId = discount.getAvailDiscountId();
                                        tvCardOfferTitle.setText(discount.getDiscountTitle());
                                    }
                                }
                            }
                        }
                    }
                } else if (ccNum.length() < 6) {
                    layoutEMI.setEnabled(false);
                    tvAvailEmi.setText(getActivity().getResources().getString(R.string.avail_emi));
                    layoutOffer.setVisibility(View.GONE);
                } else if (ccNum.length() >= 15) {
                    if (SSLCCreditCardUtils.getInstance().isValid(ccNum)) {
                        etCardExpireDate.requestFocus();
                        etCardExpireDate.setCursorVisible(true);
                    } else {
                        if (selectedCardType.equalsIgnoreCase(SSLCEnums.CardType.Amex.name().toLowerCase()) && ccNum.length() == 15) {
                            SSLCShareInfo.getInstance().showToast(getActivity(), getResources().getString(R.string.invalid_card));
                        } else if (ccNum.length() == 16) {
                            SSLCShareInfo.getInstance().showToast(getActivity(), getResources().getString(R.string.invalid_card));
                        }
                    }
                }
            }
        });
        etCardExpireDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String current = s.toString();
                if (current.length() == 2 && start == 1) {
                    etCardExpireDate.setText(current + "/");
                    etCardExpireDate.setSelection(current.length() + 1);
                } else if (current.length() == 2 && before == 1) {
                    current = current.substring(0, 1);
                    etCardExpireDate.setText(current);
                    etCardExpireDate.setSelection(current.length());
                }
            }

            // This method is invoked after user input text in EditText.
            @Override
            public void afterTextChanged(Editable editable) {
                funCheckCardField();

                if (editable.length() == 5) {
                    if (!SSLCShareInfo.getInstance().validateCardExpiryDate(editable.toString())) {
                        SSLCShareInfo.getInstance().showToast(getActivity(), "Invalid expire date");
                    } else {
                        etCardCVC.requestFocus();
                        etCardCVC.setCursorVisible(true);
                    }
                }
            }
        });
        etCardCVC.addTextChangedListener(new TextWatcher() {

            // Before EditText text change.
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // This method is invoked after user input text in EditText.
            @Override
            public void afterTextChanged(Editable editable) {
                funCheckCardField();
                if (selectedCardType.toLowerCase().contains(SSLCEnums.CardType.Amex.name().toLowerCase()) && editable.length() >= 4) {
                    etCardName.requestFocus();
                    etCardName.setCursorVisible(true);
                } else if (!selectedCardType.toLowerCase().contains(SSLCEnums.CardType.Amex.name().toLowerCase()) && editable.length() >= 3) {
                    etCardName.requestFocus();
                    etCardName.setCursorVisible(true);
                }
            }
        });
        etCardName.addTextChangedListener(new TextWatcher() {

            // Before EditText text change.
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // This method is invoked after user input text in EditText.
            @Override
            public void afterTextChanged(Editable editable) {
                funCheckCardField();
            }
        });
        etMobileNumber.addTextChangedListener(new TextWatcher() {

            // Before EditText text change.
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // This method is invoked after user input text in EditText.
            @Override
            public void afterTextChanged(Editable editable) {
                if (SSLCShareInfo.getInstance().isPhoneNumberValid(etMobileNumber.getText().toString())) {
                    btnMobileVerify.setEnabled(true);
                    SSLCShareInfo.getInstance().hideKeyboardFrom(etMobileNumber);
                } else {
                    btnMobileVerify.setEnabled(false);
                }
            }
        });
        etCardMobileNumber.addTextChangedListener(new TextWatcher() {

            // Before EditText text change.
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // This method is invoked after user input text in EditText.
            @Override
            public void afterTextChanged(Editable editable) {
                if (SSLCShareInfo.getInstance().isPhoneNumberValid(etCardMobileNumber.getText().toString())) {
                    btnCardMobileVerify.setEnabled(true);
                    SSLCShareInfo.getInstance().hideKeyboardFrom(etMobileNumber);
                } else {
                    btnCardMobileVerify.setEnabled(false);
                }
            }
        });

        tvChangeNumberOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rememberMeCv.isChecked()) {
                    etCardMobileNumber.getText().clear();
                    etCardMobileNumber.requestFocus();
                    layoutOTP.setVisibility(View.GONE);
                    layoutCardInfo.setVisibility(View.VISIBLE);
                } else {
                    etMobileNumber.getText().clear();
                    etMobileNumber.setEnabled(true);
                    etMobileNumber.requestFocus();
                    layoutOTP.setVisibility(View.GONE);
                    layoutLogin.setVisibility(View.VISIBLE);
                }
            }
        });

        tvOtpChangeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutCardInfo.setVisibility(View.VISIBLE);
                layoutOTP.setVisibility(View.GONE);
            }
        });

        btnMobileVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin1.requestFocus();
                pin1.setText("");
                pin2.setText("");
                pin3.setText("");
                pin4.setText("");
                pin5.setText("");
                pin6.setText("");

                SSLCProgressBarHandler.show();
                SSLCSendOtpToRegisterViewModel SSLCSendOtpToRegisterViewModel = new SSLCSendOtpToRegisterViewModel(getActivity());
                SSLCSendOtpToRegisterViewModel.submitOtpRegistration(etMobileNumber.getText().toString(), SSLCShareInfo.getInstance().getRegKey(getActivity()), SSLCShareInfo.getInstance().getEncKey(getActivity()), new SSLCSendOtpToRegisterListener() {
                    @Override
                    public void sendOtpToRegSuccess(SSLCSendOtpToRegisterModel SSLCSendOtpToRegisterModel) {
                        SSLCProgressBarHandler.hide();
                        SSLCShareInfo.getInstance().showToast(getActivity(), SSLCSendOtpToRegisterModel.getMessage());
                        if (SSLCSendOtpToRegisterModel.getStatus().toLowerCase().contains(SSLCEnums.StatusType.SUCCESS.name().toLowerCase())) {
                            //pinFocusChangeListener();
                            pin1.requestFocus();
                            SSLCShareInfo.getInstance().showSoftKeyboard(pin1);

                            layoutOTP.setVisibility(View.VISIBLE);
                            layoutLogin.setVisibility(View.GONE);
                            SSLCShareInfo.getInstance().startCountDown(getActivity(), tvResendOTP, tvChangeNumberOtp,
                                    tvOtpChangeCard, Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
                            layoutOtpExistingCard.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void sendOtpToRegFail(String message) {
                        SSLCProgressBarHandler.hide();
                        SSLCShareInfo.getInstance().showToast(getActivity(), message);
                    }
                });

            }
        });
        btnCardMobileVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin1.requestFocus();
                pin1.setText("");
                pin2.setText("");
                pin3.setText("");
                pin4.setText("");
                pin5.setText("");
                pin6.setText("");

                tvChangeNumberOtp.setEnabled(false);
                tvOtpChangeCard.setEnabled(false);

                tvChangeNumberOtp.setTextColor(getResources().getColor(R.color.grey));
                tvOtpChangeCard.setTextColor(getResources().getColor(R.color.grey));

                SSLCProgressBarHandler.show();
                SSLCSendOtpToRegisterViewModel SSLCSendOtpToRegisterViewModel = new SSLCSendOtpToRegisterViewModel(getActivity());
                SSLCSendOtpToRegisterViewModel.submitOtpRegistration(etCardMobileNumber.getText().toString(), SSLCShareInfo.getInstance().getRegKey(getActivity()), SSLCShareInfo.getInstance().getEncKey(getActivity()), new SSLCSendOtpToRegisterListener() {
                    @Override
                    public void sendOtpToRegSuccess(SSLCSendOtpToRegisterModel SSLCSendOtpToRegisterModel) {
                        SSLCProgressBarHandler.hide();
                        SSLCShareInfo.getInstance().showToast(getActivity(), SSLCSendOtpToRegisterModel.getMessage());
                        if (SSLCSendOtpToRegisterModel.getStatus().toLowerCase().contains(SSLCEnums.StatusType.SUCCESS.name().toLowerCase())) {
                            //pinFocusChangeListener();
                            pin1.requestFocus();
                            SSLCShareInfo.getInstance().showSoftKeyboard(pin1);

                            layoutMobileOTP.setVisibility(View.VISIBLE);
                            layoutOTP.setVisibility(View.VISIBLE);
                            layoutLogin.setVisibility(View.GONE);
                            SSLCShareInfo.getInstance().startCountDown(getActivity(), tvResendOTP, tvChangeNumberOtp,
                                    tvOtpChangeCard, Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));

                            layoutCardInfo.setVisibility(View.GONE);
                            layoutOtpExistingCard.setVisibility(View.VISIBLE);
                            String ccMasking = SSLCCreditCardUtils.getInstance().setCardMasking(etCardNumber.getText().toString());
                            tvOtpCardNumber.setText(ccMasking);
                            if (SSLCSdkMainResponseModel.getEmiStatus() == 1) {
                                String emiTenure = SSLCShareInfo.getInstance().getDigitFromString(tvAvailEmi.getText().toString());
                                if (!emiTenure.isEmpty()) {
                                    tvOtpHasEmi.setText("Has EMI");
                                }
                            }
                            if (selectedCardType.contains(SSLCEnums.CardType.Visa.name().toLowerCase())) {
                                ivOtpCardType.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_visa_new));
                            } else if (selectedCardType.contains(SSLCEnums.CardType.Master.name().toLowerCase())) {
                                ivOtpCardType.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_mastercard_new));
                            } else if (selectedCardType.contains(SSLCEnums.CardType.Amex.name().toLowerCase())) {
                                ivOtpCardType.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_amex_new));
                            }
                        }
                    }

                    @Override
                    public void sendOtpToRegFail(String message) {
                        SSLCProgressBarHandler.hide();
                        SSLCShareInfo.getInstance().showToast(getActivity(), message);
                    }
                });

            }
        });

        tvResendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin1.requestFocus();
                pin1.setText("");
                pin2.setText("");
                pin3.setText("");
                pin4.setText("");
                pin5.setText("");
                pin6.setText("");

                String mobileNumber;
                if (rememberMeCv.isChecked()) {
                    mobileNumber = etCardMobileNumber.getText().toString();
                } else {
                    mobileNumber = etMobileNumber.getText().toString();
                }

                SSLCProgressBarHandler.show();
                SSLCResendOtpViewModel sendOtpToRegisterViewModel = new SSLCResendOtpViewModel(getActivity());
                sendOtpToRegisterViewModel.submitResendOtp(mobileNumber, SSLCShareInfo.getInstance().getRegKey(getActivity()), SSLCShareInfo.getInstance().getEncKey(getActivity()), new SSLCResendOtpListener() {
                    @Override
                    public void resendOtpSuccess(SSLCResendOtpModel SSLCResendOtpModel) {
                        SSLCProgressBarHandler.hide();
                        tvChangeNumberOtp.setEnabled(false);
                        tvOtpChangeCard.setEnabled(false);

                        tvChangeNumberOtp.setTextColor(getResources().getColor(R.color.grey));
                        tvOtpChangeCard.setTextColor(getResources().getColor(R.color.grey));

                        if (SSLCResendOtpModel.getStatus().toLowerCase().equalsIgnoreCase(SSLCEnums.StatusType.SUCCESS.name().toLowerCase())) {
                            SSLCShareInfo.getInstance().startCountDown(getActivity(), tvResendOTP, tvChangeNumberOtp,
                                    tvOtpChangeCard, Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
                        }
                        SSLCShareInfo.getInstance().showToast(getActivity(), SSLCResendOtpModel.getMessage());
                    }

                    @Override
                    public void resendOtpFail(String message) {
                        SSLCProgressBarHandler.hide();
                        SSLCShareInfo.getInstance().showToast(getActivity(), message);
                    }
                });
            }
        });

        rememberMeCv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ivMoreInfo.setVisibility(View.VISIBLE);
                    view1.setVisibility(View.VISIBLE);
                    if (SSLCShareInfo.getInstance().getCustSession(getActivity()).isEmpty()) {
                        SSLCOnBtnPayActiveListener.onBtnPayActive(false, "");
                        // layoutCardInfo.setVisibility(View.GONE);
                        layoutCardLogin.setVisibility(View.VISIBLE);
                        // layoutCardInfo.setVisibility(View.GONE);
                        // layoutEnterMobileNumber.animate().translationY(0);
                        // AnimationManager.getInstance().expand(layoutEnterMobileNumber);
                        //MyCustomAnimation a = new MyCustomAnimation(layoutCardLogin, 200, MyCustomAnimation.EXPAND);
                        //layoutCardLogin.startAnimation(a);


                        NestedScrollView scrollView = getActivity().findViewById(R.id.scrollView);
                        scrollView.fullScroll(View.FOCUS_DOWN);
//                        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//                        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//                        layoutCardLogin.measure(widthSpec, heightSpec);
//                        ValueAnimator mAnimator = ShareInfo.getInstance().slideAnimator(0, getView().getMeasuredHeight()+300, getView());
//                        mAnimator.start();

                        // ExpandableRelativeLayout expandableRelativeLayout = getActivity().findViewById(R.id.expandableLayout);
                        //expandableRelativeLayout.expand();
                        SSLCShareInfo.getInstance().expand(layoutCardLogin);
                        // etCardMobileNumber.requestFocus();
                        // etCardMobileNumber.setCursorVisible(true);
                        // etCardMobileNumber.setFocusable(true);
                        // etCardMobileNumber.setFocusableInTouchMode(true);
                    }
                    //  if(etCardNumber.getText().toString().)
                } else {
                    SSLCShareInfo.getInstance().collapse(layoutCardLogin);
                    SSLCOnBtnPayActiveListener.onBtnPayActive(true, etCardNumber.getText().toString());
                    ivMoreInfo.setVisibility(View.GONE);
                    view1.setVisibility(View.GONE);
                    layoutCardLogin.setVisibility(View.GONE);
                    //MyCustomAnimation a = new MyCustomAnimation(layoutEnterMobileNumber, 100, MyCustomAnimation.COLLAPSE);
                    //layoutEnterMobileNumber.startAnimation(a);
                    //   AnimationManager.getInstance().collapse(layoutEnterMobileNumber);
                }
            }
        });

        getActivity().findViewById(R.id.iv_other_cards_go_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rememberMeCv.isChecked() && SSLCPrefUtils.isLoggedIn(context)) {
                    layoutCardLogin.setVisibility(View.GONE);
                } else {
                    layoutCardLogin.setVisibility(View.VISIBLE);
                }

                layoutCardField.setVisibility(View.VISIBLE);
                layoutOtherCards.setVisibility(View.GONE);
                etCardCVC.setText("");
                SSLCOnBtnPayActiveListener.onBtnPayActive(false, "");
            }
        });

        tvSkipLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutCardInfo.setVisibility(View.VISIBLE);
                layoutCardLogin.setVisibility(View.GONE);
                layoutMobileOTP.setVisibility(View.GONE);
                rememberMeCv.setChecked(false);
            }
        });

        tvChangeNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMobileNumber.getText().clear();
                etMobileNumber.setEnabled(true);
                tvChangeNumber.setText("Ex. 01XXXXXXXXX");
            }
        });
        tvMyCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutCardInfo.setVisibility(View.GONE);
                layoutMobileOTP.setVisibility(View.GONE);
                layoutSaveCards.setVisibility(View.VISIBLE);
                SSLCOnBtnPayActiveListener.onBtnPayActive(false, "");
            }
        });
        tvOtherCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SSLCOnBtnPayActiveListener.onBtnPayActive(false, "");
                layoutCardField.setVisibility(View.GONE);
                layoutCardLogin.setVisibility(View.GONE);
                layoutOtherCards.setVisibility(View.VISIBLE);

                final ArrayList<SSLCSdkMainResponseModel.Desc> otherCards = SSLCShareInfo.getInstance().getOtherCards(SSLCSdkMainResponseModel);

//                SdkMainResponseModel.Desc descObj = new SdkMainResponseModel().new Desc();
//                descObj.setRFlag("-1");
//                otherCards.add(descObj);

                /*for (SdkMainResponseModel.Desc desc : sdkMainResponseModel.getDesc()) {
                    if (!desc.getType().contains("mobilebanking") && !desc.getType().contains("internetbanking") &&
                    !desc.getType().contains(Enums.CardType.Visa.name().toLowerCase()) &&
                            !desc.getType().contains(Enums.CardType.Master.name().toLowerCase())&&
                            !desc.getType().contains(Enums.CardType.Amex.name().toLowerCase())) {
                            otherCards.add(desc);
                    }
                }*/
                // SdkMainResponseModel.Desc desc = new SdkMainResponseModel.Desc();
                rvOtherCards.setHasFixedSize(true);
                SSLCOtherCardsAdapter = new SSLCOtherCardsAdapter(getActivity(), otherCards);
                rvOtherCards.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                rvOtherCards.setAdapter(SSLCOtherCardsAdapter);
                SSLCOtherCardsAdapter.setClickListener(new SSLCOtherCardsAdapter.ClickListener() {
                    @Override
                    public void itemClicked(View v, int position, final List<SSLCSdkMainResponseModel.Desc> mTaskInfo, boolean status) {

                        SSLCSdkMainResponseModel.Desc desc = otherCards.get(position);
                        if (desc.getRFlag().equals("0")) {
                            if (rememberMeCv.isChecked()) {
                                layoutCardLogin.setVisibility(View.VISIBLE);
                            }

                            layoutCardField.setVisibility(View.VISIBLE);
                            layoutOtherCards.setVisibility(View.GONE);

                           /* if (desc.getType().contains(Enums.CardType.Visa.name().toLowerCase())) {
                                selectedCardType = Enums.CardType.Visa.name().toLowerCase();
                                ivCardVisa.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_visa_new));
                                ivCardMaster.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_master_grey));
                                ivCardAmex.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_amex_grey));
                                //ShareInfo.getInstance().disableGray(ivCardVisa);
                                //ShareInfo.getInstance().setToGray(ivCardMaster);
                                //ShareInfo.getInstance().setToGray(ivCardAmex);
                                //onBtnPayActiveListener.onBtnPayActive(false, "");
                            } else if (desc.getType().contains(Enums.CardType.Master.name().toLowerCase())) {
                                selectedCardType = Enums.CardType.Master.name().toLowerCase();
                                ivCardVisa.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_visa_grey));
                                ivCardMaster.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_mastercard_new));
                                ivCardAmex.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_amex_grey));
                                //ShareInfo.getInstance().setToGray(ivCardVisa);
                                //ShareInfo.getInstance().disableGray(ivCardMaster);
                                // ShareInfo.getInstance().setToGray(ivCardAmex);
                                //onBtnPayActiveListener.onBtnPayActive(false, "");
                            } else if (desc.getType().contains(Enums.CardType.Amex.name().toLowerCase())) {
                                selectedCardType = Enums.CardType.Amex.name().toLowerCase();
                                ivCardVisa.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_visa_grey));
                                ivCardMaster.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_master_grey));
                                ivCardAmex.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_amex_new));
                                // ShareInfo.getInstance().setToGray(ivCardVisa);
                                // ShareInfo.getInstance().setToGray(ivCardMaster);
                                //ShareInfo.getInstance().disableGray(ivCardAmex);
                                //onBtnPayActiveListener.onBtnPayActive(false, "");
                            }*/
                        } else {
                            if (status)
                                SSLCOnBtnPayActiveListener.onBtnPayActive(true, mTaskInfo.get(position).getName());
                            else
                                SSLCOnBtnPayActiveListener.onBtnPayActive(false, "");

//                            Intent intent = new Intent(getActivity(), WebViewActivity.class);
//                            intent.putExtra("redirectUrl", desc.getRedirectGatewayURL());
//                            intent.putExtra("merchantName", desc.getName());
//                            intent.putExtra("sdkMainResponse", mobileSslCommerzInitialization);
//                            startActivity(intent);
                        }
                    }
                });
            }
        });

        layoutEMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ListPopupWindow emiPopupWindow = new ListPopupWindow(getActivity());
                emiPopupWindow.setAdapter(new ArrayAdapter(getActivity(), R.layout.layout_list_emi, tenures));
                emiPopupWindow.setModal(true);
                emiPopupWindow.setAnchorView(layoutEMI);
                emiPopupWindow.setWidth(500);
                emiPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (position > 0) {
                            String tenure = tenures.get(position);
                            String[] str = tenure.split("-");
                            if (str.length > 0) {
                                // int a = item.getItemId();
                                //Log.e(TAG, "onMenuItemClick Asif: " + a);
                                layoutOffer.setVisibility(View.GONE);
                                tvAvailEmi.setText(str[0]);
                                SSLCShareInfo.getInstance().showToast(getActivity(), "You select : " + tenure);
                                emiPopupWindow.dismiss();
                            }
                        }
                    }
                });
                layoutEMI.post(new Runnable() {
                    public void run() {
                        emiPopupWindow.show();
                    }
                });
                // LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);

                // Inflate the custom layout/view
                // View customView = inflater.inflate(R.layout.layout_list_emi,null);
                // TextView textEmi = (TextView) customView.findViewById(R.id.tv_emi);

//                PopupMenu popup = new PopupMenu(getActivity(), layoutEMI);
//                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
//                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    public boolean onMenuItemClick(MenuItem item) {
//                        String[] str = item.getTitle().toString().split("-");
//                        if (str.length > 0) {
//                            int a = item.getItemId();
//                            Log.e(TAG, "onMenuItemClick Asif: " + a);
//                            layoutOffer.setVisibility(View.GONE);
//                            tvAvailEmi.setText(str[0]);
//                            ShareInfo.getInstance().showToast(getActivity(), "You Clicked : " + item.getTitle());
//                        }
//                        return true;
//                    }
//                });
//                popup.getMenu().clear();
//                for (String str : tenures) {
//                    popup.getMenu().add(str);
//                }
//                popup.show(); //showing popup menu
            }
        });
        ivEMIInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SSLCEMIModel != null) {
                    String emiBankInfo = "";
                    for (SSLCEMIModel.Emi emi : SSLCEMIModel.getData().getData().getEmi()) {
                        emiBankInfo += "&#8226; " + emi.getBankName() + "<br>";
                    }

                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setTitle(getActivity().getResources().getString(R.string.emi_bank_list_heading));
                    alertDialog.setMessage(Html.fromHtml(emiBankInfo));
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
                    layoutParams.weight = 10;
                    btnPositive.setLayoutParams(layoutParams);
                }
            }
        });
        ivMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDialog("", "");
            }
        });
        tvTermCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebViewActivitySSLC.class);
                intent.putExtra("merchantName", getActivity().getResources().getString(R.string.term_condition));
                intent.putExtra("redirectUrl", SSLCSdkMainResponseModel.getTermsAndConditionURL());
                intent.putExtra("sdkMainResponse", mobileSslCommerzInitialization);
                startActivity(intent);
            }
        });

        pinFocusChangeListener();
    }

    public void initDialog(String title, String message) {
        dialog = new Dialog(getActivity(), R.style.customDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.setContentView(R.layout.tooltip_view_sslc);

        SSLCCustomTextView moreInfo = dialog.findViewById(R.id.moreInfo);
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(getActivity(), FAQActivitySSLC.class);
                intent.putExtra("url", SSLCSdkMainResponseModel.getMoreInfoURL());
                intent.putExtra("checker", 2);
                startActivity(intent);
            }
        });

        dialog.show();
    }

    private void setRvSaveCards(final List<SSLCVerifyOtpAndLoginModel.CardNo> cardNos) {
        //List<VerifyOtpAndLoginModel.CardNo> cardNos = verifyOtpAndLoginModel.getData().getData().getCardNos();
        SSLCVerifyOtpAndLoginModel.CardNo cardNo = new SSLCVerifyOtpAndLoginModel().new CardNo();
        cardNo.setCardNo(getActivity().getResources().getString(R.string.pay_using_another_card));
        cardNo.setType("another");
        cardNos.add(cardNo);

        rvSaveCards.setHasFixedSize(true);
        SSLCSaveCardsAdapter = new SSLCSaveCardsAdapter(getActivity(), cardNos, SSLCSdkMainResponseModel, offerId, SSLCOfferModel);
        rvSaveCards.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvSaveCards.setAdapter(SSLCSaveCardsAdapter);

        SSLCSaveCardsAdapter.setClickListener(new SSLCSaveCardsAdapter.ClickListener() {
            @Override
            public void itemClicked(View v, final int position, int previousSelectedPosition) {
                final SSLCVerifyOtpAndLoginModel.CardNo cardNo = cardNos.get(position);

                motoEnable = false;
                savedCardEmiTenure = "0";
                SSLCSaveCardsAdapter.notifyItemChanged(previousSelectedPosition);
                savedCardIndex = cardNo.getCardindex();

                etSavedCVV = (EditText) v.findViewById(R.id.et_card_cvc);
                LinearLayout layoutCardDelete = (LinearLayout) v.findViewById(R.id.layout_card_delete);
                layoutSavedCVV = (LinearLayout) v.findViewById(R.id.layout_cvc);
                RelativeLayout layoutEmiTitle = (RelativeLayout) v.findViewById(R.id.layout_emi_title);
                LinearLayout layoutMother = (LinearLayout) v.findViewById(R.id.layoutMother);
                layoutMother.setBackgroundResource(R.drawable.bg_border_lochmara_colored_sslc);
                layoutCardDelete.setVisibility(View.GONE);

                if (cardNo.getType().contains("another")) {
                    layoutCardInfo.setVisibility(View.VISIBLE);
                    layoutMother.setBackgroundResource(0);
                    layoutMobileOTP.setVisibility(View.GONE);
                    layoutSaveCards.setVisibility(View.GONE);
                    tvMyCards.setVisibility(View.VISIBLE);
                    etSavedCVV.setText("");
                    etCardCVC.setText("");
                    return;
                }

                SSLCShareInfo.getInstance().hideKeyboardFrom(getView());
                etSavedCVV.clearFocus();

                SSLCCustomTextWatcher = new SSLCCustomTextWatcher() {
                    @Override
                    public void afterTextChanged(Editable editable, boolean backSpace, boolean lastLength) {
                        //ShareInfo.getInstance().setEditTextMaxLength(etSavedCVV, 4);
                        if (cardNo.getType().toLowerCase().contains(SSLCEnums.CardType.Amex.name().toLowerCase()) && editable.length() >= 4) {
                            SSLCOnBtnPayActiveListener.onBtnPayActive(true, cardNo.getCardNo());
                            //ShareInfo.getInstance().setEditTextMaxLength(etSavedCVV, 4);
                            SSLCShareInfo.getInstance().hideKeyboardFrom(etSavedCVV);
                        } else if (!cardNo.getType().toLowerCase().contains(SSLCEnums.CardType.Amex.name().toLowerCase()) && editable.length() >= 3) {
                            SSLCOnBtnPayActiveListener.onBtnPayActive(true, cardNo.getCardNo());
                            // ShareInfo.getInstance().setEditTextMaxLength(etSavedCVV, 3);
                            SSLCShareInfo.getInstance().hideKeyboardFrom(etSavedCVV);
                        } else {
                            if (!motoEnable) {
                                SSLCOnBtnPayActiveListener.onBtnPayActive(false, "");
                            }
                        }
                    }

                    @Override
                    public void onTextChanged(boolean lastLength) {

                    }
                };
                etSavedCVV.addTextChangedListener(SSLCCustomTextWatcher);

                if ((SSLCShareInfo.getInstance().searchMotoEnabled(cardNo.getCardindex()) && SSLCShareInfo.getInstance().convertToInt(cardNo.getWithoutOTP()) == 1) ||
                        SSLCShareInfo.getInstance().convertToInt(cardNo.getWithoutOTP()) == 0) {
                    SSLCOnBtnPayActiveListener.onBtnPayActive(false, "");

                    SSLCChangeCursorColor SSLCChangeCursorColor = new SSLCChangeCursorColor(getActivity());
                    SSLCChangeCursorColor.setCursorColor(etSavedCVV, Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
                    if (previousSelectedPosition != position) {
                        SSLCShareInfo.getInstance().showSoftKeyboard(etSavedCVV);
                    }

                    // etSavedCVV.removeTextChangedListener(customTextWatcher);

                    SSLCAnimationManager.getInstance().expand(layoutSavedCVV);
                    if (SSLCSdkMainResponseModel.getEmiStatus() == 1) {
                        String ccNumber = cardNo.getCardNo().substring(0, 6);
                        tenures = getTenures(ccNumber);
                        if (tenures.size() > 1) {
                            SSLCAnimationManager.getInstance().expand(layoutEmiTitle);
                        }
                    }
                } else {
                    if (!SSLCSaveCardsAdapter.isSelected || previousSelectedPosition != position) {
                        motoEnable = true;
                        SSLCOnBtnPayActiveListener.onBtnPayActive(true, cardNo.getCardNo());
                    } else {
                        SSLCOnBtnPayActiveListener.onBtnPayActive(false, cardNo.getCardNo());
                    }
                }
            }
        });
        SSLCSaveCardsAdapter.setDeleteClickListener(new SSLCSaveCardsAdapter.DeleteListener() {
            @Override
            public void onDelete(final int position) {
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle(getActivity().getResources().getString(R.string.delete_card));
                alertDialog.setMessage(getActivity().getResources().getString(R.string.delete_message));
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getActivity().getResources().getString(R.string.delete),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                                final SSLCVerifyOtpAndLoginModel.CardNo cardNo = cardNos.get(position);
                                SSLCProgressBarHandler.show();
                                SSLCCardDeleteViewModel SSLCCardDeleteViewModel = new SSLCCardDeleteViewModel(getActivity());
                                SSLCCardDeleteViewModel.submitDelete(SSLCShareInfo.getInstance().getCustSession(getActivity()),
                                        SSLCShareInfo.getInstance().getRegKey(getActivity()), SSLCShareInfo.getInstance().getEncKey(getActivity()), cardNo.getCardindex(), new SSLCCardDeleteListener() {
                                            @Override
                                            public void cardDeleteSuccess(SSLCCardDeleteModel SSLCCardDeleteModel) {
                                                SSLCProgressBarHandler.hide();
                                                if (SSLCCardDeleteModel.getStatus().toLowerCase().equalsIgnoreCase(SSLCEnums.StatusType.SUCCESS.name().toLowerCase())) {
                                                    SSLCShareInfo.getInstance().showToast(getActivity(), SSLCCardDeleteModel.getMessage());
                                                    cardNos.remove(position);
                                                    if (cardNos.size() == 1) {
                                                        layoutCardInfo.setVisibility(View.VISIBLE);
                                                        layoutOtherCards.setVisibility(View.GONE);
                                                        layoutSaveCards.setVisibility(View.GONE);
                                                    } else {
                                                        SSLCSaveCardsAdapter.notifyDataSetChanged();
                                                    }
                                                } else {
                                                    SSLCShareInfo.getInstance().showToast(getActivity(), SSLCCardDeleteModel.getMessage());
                                                }
                                            }

                                            @Override
                                            public void cardDeleteFail(String message) {
                                                SSLCProgressBarHandler.hide();
                                                SSLCShareInfo.getInstance().showToast(getActivity(), message);
                                            }
                                        });
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getActivity().getResources().getString(R.string.no),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

                Button BUTTON_POSITIVE = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                Button BUTTON_NEGATIVE = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                BUTTON_POSITIVE.setTextColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
                BUTTON_NEGATIVE.setTextColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));

            }
        });
        SSLCSaveCardsAdapter.setEMIClickListener(new SSLCSaveCardsAdapter.EmiSelectListener() {
            @Override
            public void onEMISelect(final LinearLayout layoutEMI, final LinearLayout layoutOffer, final TextView tvAvailEmi, final String num) {
                // String ccNumber = num.substring(0, 5);
                //tenures = getTenures(ccNumber);

                final ListPopupWindow emiPopupWindow = new ListPopupWindow(getActivity());
                emiPopupWindow.setAdapter(new ArrayAdapter(getActivity(), R.layout.layout_list_emi, tenures));
                emiPopupWindow.setModal(true);
                emiPopupWindow.setAnchorView(layoutEMI);
                emiPopupWindow.setWidth(500);
                emiPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (position > 0) {
                            String tenure = tenures.get(position);
                            String[] str = tenure.split("-");
                            //String[] str = item.getTitle().toString().split("-");
                            if (str.length > 1) {
                                layoutOffer.setVisibility(View.GONE);
                                offerId = "0";
                                tvAvailEmi.setText(str[0]);
                                savedCardEmiTenure = SSLCShareInfo.getInstance().getDigitFromString(str[0]);
                                SSLCShareInfo.getInstance().showToast(getActivity(), "You select : " + tenure);
                            } else {
                                savedCardEmiTenure = "0";
                                tvAvailEmi.setText(str[0]);

                                String ccNumber = num.substring(0, 6);
                                String offerInfo = SSLCShareInfo.getInstance().offerAvailibilityCheck(SSLCOfferModel, ccNumber);
                                if (!offerInfo.isEmpty()) {
                                    String[] offer = offerInfo.split("--");
                                    if (offer.length > 1) {
                                        offerId = offer[0];
                                        layoutOffer.setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                            emiPopupWindow.dismiss();

//                            String[] str = tenure.split("-");
//                            if (str.length > 0) {
//                                // int a = item.getItemId();
//                                //Log.e(TAG, "onMenuItemClick Asif: " + a);
//                                layoutOffer.setVisibility(View.GONE);
//                                tvAvailEmi.setText(str[0]);
//                                ShareInfo.getInstance().showToast(getActivity(), "You select : " + tenure);
//                                emiPopupWindow.dismiss();
//                            }
                        }
                    }
                });
                layoutEMI.post(new Runnable() {
                    public void run() {
                        emiPopupWindow.show();
                    }
                });

//                if (tenures.size() > 1) {
//                    PopupMenu popup = new PopupMenu(getActivity(), layoutEMI);
//                    popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
//                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                        public boolean onMenuItemClick(MenuItem item) {
//                            String[] str = item.getTitle().toString().split("-");
//                            if (str.length > 1) {
//                                layoutOffer.setVisibility(View.GONE);
//                                offerId = "0";
//                                tvAvailEmi.setText(str[0]);
//                                savedCardEmiTenure = ShareInfo.getInstance().getDigitFromString(str[0]);
//                                ShareInfo.getInstance().showToast(getActivity(), item.getTitle() + "");
//                            } else {
//                                savedCardEmiTenure = "0";
//                                tvAvailEmi.setText(str[0]);
//
//                                String ccNumber = num.substring(0, 6);
//                                String offerInfo = ShareInfo.getInstance().offerAvailibilityCheck(offerModel, ccNumber);
//                                if (!offerInfo.isEmpty()) {
//                                    String[] offer = offerInfo.split("--");
//                                    if (offer.length > 1) {
//                                        offerId = offer[0];
//                                        layoutOffer.setVisibility(View.VISIBLE);
//                                    }
//                                }
//                            }
//                            return true;
//                        }
//                    });
//                    popup.getMenu().clear();
//                    for (String str : tenures) {
//                        popup.getMenu().add(str);
//                    }
//                    popup.show(); //showing popup menu
//                }
            }
        });
    }

    private void pinFocusChangeListener() {
        pin1.addTextChangedListener(new SSLCCustomTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable, boolean backSpace, boolean lastLength) {
                if (editable.length() > 0) {
                    pin1.removeTextChangedListener(this);
                    pin1.setText(editable.toString().substring(editable.length() - 1, editable.length()));
                    pin1.addTextChangedListener(this);

                    pin1.clearFocus();
                    pin2.requestFocus();
                    pin2.setCursorVisible(true);
                    funOtpVerify(false);
                }
            }

            @Override
            public void onTextChanged(boolean lastLength) {

            }
        });
        pin2.addTextChangedListener(new SSLCCustomTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable, boolean backSpace, boolean lastLength) {
                if (editable.length() > 0) {
                    pin2.removeTextChangedListener(this);
                    pin2.setText(editable.toString().substring(editable.length() - 1, editable.length()));
                    pin2.addTextChangedListener(this);
                    pin2.setSelection(pin2.getText().length());
                    pin2.clearFocus();

                    pin3.requestFocus();
                    pin3.setCursorVisible(true);
                    funOtpVerify(false);
                }
//                else {
//                    if (!backPressed) {
//                        pin2.clearFocus();
//                        pin1.requestFocus();
//                        pin1.setCursorVisible(true);
//                        pin1.setSelection(pin1.getText().length());
//                    } else
//                        backPressed = false;
//                }
            }

            @Override
            public void onTextChanged(boolean lastLength) {

            }
        });
        pin2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // You can identify which key was pressed by checking the keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    pin2.clearFocus();
                    pin1.requestFocus();
                    pin1.setCursorVisible(true);
                    pin1.setSelection(pin1.getText().length());
                }
                return false;
            }
        });

        pin3.addTextChangedListener(new SSLCCustomTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable, boolean backSpace, boolean lastLength) {
                if (editable.length() > 0) {
                    pin3.removeTextChangedListener(this);
                    pin3.setText(editable.toString().substring(editable.length() - 1, editable.length()));
                    pin3.addTextChangedListener(this);
                    pin3.setSelection(pin3.getText().length());
                    pin3.clearFocus();

                    pin4.requestFocus();
                    pin4.setCursorVisible(true);
                    funOtpVerify(false);
                }
//                else {
//                    if (!backPressed) {
//                        pin3.clearFocus();
//                        pin2.requestFocus();
//                        pin2.setCursorVisible(true);
//                        pin2.setSelection(pin2.getText().length());
//                    } else
//                        backPressed = false;
//                }
            }

            @Override
            public void onTextChanged(boolean lastLength) {

            }
        });
        pin3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // You can identify which key was pressed by checking the keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    pin3.clearFocus();
                    pin2.requestFocus();
                    pin2.setCursorVisible(true);
                    pin2.setSelection(pin2.getText().length());
                }
                return false;
            }
        });
        pin4.addTextChangedListener(new SSLCCustomTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable, boolean backSpace, boolean lastLength) {
                if (editable.length() > 0) {
                    pin4.removeTextChangedListener(this);
                    pin4.setText(editable.toString().substring(editable.length() - 1, editable.length()));
                    pin4.addTextChangedListener(this);
                    pin4.setSelection(pin4.getText().length());
                    pin4.clearFocus();

                    pin5.requestFocus();
                    pin5.setCursorVisible(true);
                    funOtpVerify(false);
                }
//                else {
//                    if (!backPressed) {
//                        pin4.clearFocus();
//                        pin3.requestFocus();
//                        pin3.setCursorVisible(true);
//                        pin3.setSelection(pin3.getText().length());
//                    } else
//                        backPressed = false;
//                }
            }

            @Override
            public void onTextChanged(boolean lastLength) {

            }
        });
        pin4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // You can identify which key was pressed by checking the keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    pin4.clearFocus();
                    pin3.requestFocus();
                    pin3.setCursorVisible(true);
                    pin3.setSelection(pin3.getText().length());
                }
                return false;
            }
        });
        pin5.addTextChangedListener(new SSLCCustomTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable, boolean backSpace, boolean lastLength) {
                if (editable.length() > 0) {
                    pin5.removeTextChangedListener(this);
                    pin5.setText(editable.toString().substring(editable.length() - 1, editable.length()));
                    pin5.addTextChangedListener(this);
                    pin5.setSelection(pin5.getText().length());
                    pin5.clearFocus();

                    pin6.requestFocus();
                    pin6.setCursorVisible(true);
                    pin6.setSelection(pin6.getText().length());

                    funOtpVerify(false);
                }
//                else {
//                    if (!backPressed) {
//                        pin5.clearFocus();
//                        pin4.requestFocus();
//                        pin4.setCursorVisible(true);
//                        pin4.setSelection(pin4.getText().length());
//                    } else
//                        backPressed = false;
//                }
            }

            @Override
            public void onTextChanged(boolean lastLength) {

            }
        });
        pin5.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // You can identify which key was pressed by checking the keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    pin5.clearFocus();
                    pin4.requestFocus();
                    pin4.setCursorVisible(true);
                    pin4.setSelection(pin4.getText().length());
                }
                return false;
            }
        });
        pin6.addTextChangedListener(new SSLCCustomTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable, boolean backSpace, boolean lastLength) {
                if (editable.length() > 0) {
                    pin6.removeTextChangedListener(this);
                    pin6.setText(editable.toString().substring(editable.length() - 1, editable.length()));
                    pin6.addTextChangedListener(this);
                    pin6.setSelection(pin6.getText().length());

                    funOtpVerify(true);
                }
//                else {
//                    if (!backPressed) {
//                        pin6.clearFocus();
//                        pin5.requestFocus();
//                        pin5.setCursorVisible(true);
//                        pin5.setSelection(pin5.getText().length());
//                    } else
//                        backPressed = false;
//                }
            }

            @Override
            public void onTextChanged(boolean lastLength) {

            }
        });
        pin6.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // You can identify which key was pressed by checking the keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    pin6.clearFocus();
                    pin5.requestFocus();
                    pin5.setCursorVisible(true);
                    pin5.setSelection(pin5.getText().length());
                }
                return false;
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isFragmentVisible_) {
        super.setUserVisibleHint(true);

        if (this.isVisible()) {
            if (isFragmentVisible_) {
                layoutOffer.setVisibility(View.GONE);
                etCardCVC.setText("");
                if (etSavedCVV != null) {
                    etSavedCVV.setText("");
                }
                if (layoutSaveCards.getVisibility() == View.VISIBLE) {
                    SSLCSaveCardsAdapter.notifyItemChanged(SSLCSaveCardsAdapter.currentlySelectedPosition);
                    SSLCSaveCardsAdapter.previousSelectedPosition = -1;
                }
                SSLCOnBtnPayActiveListener.onBtnPayActive(false, "");
                //setLocale();

                ((MainUIActivitySSLC) getActivity()).setOnPayClickListener(new SSLCPayNowListener() {
                    @Override
                    public void onPayClick() {
                        funPay();
                    }
                });
            }

//            if (isFragmentVisible_) {
//                ((MainUIActivity) getActivity()).setOnPayClickListener(new PayNowListener() {
//                    @Override
//                    public void onPayClick() {
//                        pay();
//                    }
//                });
            //           }
        }
    }

    private void funPay() {
        if (layoutOtherCards.getVisibility() == View.VISIBLE) {
            //Log.e(TAG, "onPayClick: " + mTaskInfo.get(position).getRedirectGatewayURL());
            ArrayList<SSLCSdkMainResponseModel.Desc> otherCards = SSLCShareInfo.getInstance().getOtherCards(SSLCSdkMainResponseModel);

            Intent intent = new Intent(context, WebViewActivitySSLC.class);
            intent.putExtra("redirectUrl", otherCards.get(SSLCOtherCardsAdapter.currentPosition).getRedirectGatewayURL());
            intent.putExtra("merchantName", otherCards.get(SSLCOtherCardsAdapter.currentPosition).getName());
            intent.putExtra("session_key", SSLCSdkMainResponseModel.getSessionkey());
            intent.putExtra("timeOutValue", SSLCSdkMainResponseModel.getTimeoutinMin());
            intent.putExtra("sdkMainResponse", mobileSslCommerzInitialization);
            getActivity().startActivityForResult(intent, SSLCEnums.Common.Activity2.ordinal());
        } else if (layoutCardInfo.getVisibility() == View.VISIBLE) {
            //String ShareInfo.getInstance().getRegKey(getActivity()), ShareInfo.getInstance().getEncKey(getActivity());
            String cardNumber = etCardNumber.getText().toString().replaceAll("\\s+", "");
            String cardExpireDate = etCardExpireDate.getText().toString();
            String cardCVC = etCardCVC.getText().toString();
            String cardName = etCardName.getText().toString();
            String isEMI = "0";
            String emiTenure = "0";
            //String emiBankID = "0";
            if (SSLCSdkMainResponseModel.getEmiStatus() == 1) {
                emiTenure = SSLCShareInfo.getInstance().getDigitFromString(tvAvailEmi.getText().toString());
                if (!emiTenure.isEmpty()) {
                    isEMI = "1";
                }
            }
            String isOffer = "0";
            if (!offerId.equalsIgnoreCase("0")) {
                isOffer = "1";
            }
            String isRemember = "0";
            if (rememberMeCv.isChecked()) {
                isRemember = "1";
            }
            if (cardNumber.isEmpty()) {
                SSLCShareInfo.getInstance().showToast(getActivity(), "enter card number");
                return;
            } else if (!SSLCCreditCardUtils.getInstance().isValid(cardNumber)) {
                SSLCShareInfo.getInstance().showToast(getActivity(), getResources().getString(R.string.invalid_card));
                return;
            }

            if (cardExpireDate.isEmpty()) {
                SSLCShareInfo.getInstance().showToast(getActivity(), "enter expire date");
                return;
            }
            if (cardCVC.isEmpty()) {
                SSLCShareInfo.getInstance().showToast(getActivity(), "enter cvc");
                return;
            }
            if (cardName.isEmpty()) {
                SSLCShareInfo.getInstance().showToast(getActivity(), "enter card name");
                return;
            }

            SSLCProgressBarHandler.show();
            SSLCPayWithCardInfoViewModel SSLCPayWithCardInfoViewModel = new SSLCPayWithCardInfoViewModel(getActivity());
            SSLCPayWithCardInfoViewModel.payWithCard(SSLCShareInfo.getInstance().getRegKey(getActivity()),
                    SSLCShareInfo.getInstance().getEncKey(getActivity()),
                    SSLCShareInfo.getInstance().getCustSession(getActivity()),
                    SSLCSdkMainResponseModel.getSessionkey(),
                    cardNumber,
                    cardExpireDate,
                    cardCVC,
                    cardName,
                    isRemember,
                    SSLCSdkMainResponseModel.getLoginTransSession(),
                    isEMI,
                    emiTenure,
                    emiBankId,
                    offerId,
                    isOffer, new SSLCPayWithCardInfoListener() {
                        @Override
                        public void payWithCardInfoSuccess(SSLCTransactionInfo SSLCTransactionInfo) {
                            SSLCProgressBarHandler.hide();
                            if (SSLCTransactionInfo.getStatus().toLowerCase().equalsIgnoreCase(SSLCEnums.StatusType.SUCCESS.name().toLowerCase())) {
                                if (SSLCTransactionInfo.getData().getType().toLowerCase().equalsIgnoreCase(SSLCEnums.TransactionType.otp.name().toLowerCase())
                                        || SSLCTransactionInfo.getData().getType().toLowerCase().equalsIgnoreCase(SSLCEnums.TransactionType.moto.name().toLowerCase())) {
                                    Intent intent = new Intent(getActivity(), WebViewActivitySSLC.class);
                                    intent.putExtra("redirectUrl", SSLCTransactionInfo.getData().getUrl());
                                    intent.putExtra("merchantName", "");
                                    intent.putExtra("session_key", SSLCSdkMainResponseModel.getSessionkey());
                                    intent.putExtra("sdkMainResponse", mobileSslCommerzInitialization);
                                    getActivity().startActivityForResult(intent, SSLCEnums.Common.Activity2.ordinal());
                                }
                            } else {
                                SSLCShareInfo.getInstance().showToast(getActivity(), SSLCTransactionInfo.getMessage());
                            }
                        }


                        @Override
                        public void payWithCardInfoFail(String message) {
                            SSLCProgressBarHandler.hide();
                            SSLCShareInfo.getInstance().showToast(getActivity(), message);
                        }

                        @Override
                        public void payWithCardInfoValidationError(String message) {
                            SSLCProgressBarHandler.hide();
                            SSLCShareInfo.getInstance().showToast(getActivity(), message);
                        }
                    });
        } else if (layoutSaveCards.getVisibility() == View.VISIBLE) {
            if (!motoEnable && (etSavedCVV == null || etSavedCVV.getText().toString().isEmpty())) {
                SSLCShareInfo.getInstance().showToast(getActivity(), "Enter CVV");
                return;
            }
//            else if(motoEnable && ShareInfo.getInstance().searchMotoEnabled(savedCardIndex)){
//
//            }

            String isEMI = "0";
            if (SSLCSdkMainResponseModel.getEmiStatus() == 1) {
                if (!savedCardEmiTenure.isEmpty()) {
                    isEMI = "1";
                }
            }
            String isOffer = "0";
            if (!offerId.equalsIgnoreCase("0")) {
                isOffer = "1";
            }

            //String emiBankID = "0";

            SSLCProgressBarHandler.show();
            SSLCPayWithStoredCardViewModel payWithCardInfoViewModel = new SSLCPayWithStoredCardViewModel(getActivity());
            payWithCardInfoViewModel.payWithStoredCard(SSLCShareInfo.getInstance().getRegKey(getActivity()),
                    SSLCShareInfo.getInstance().getEncKey(getActivity()),
                    SSLCShareInfo.getInstance().getCustSession(getActivity()),
                    SSLCSdkMainResponseModel.getSessionkey(),
                    motoEnable ? "xxxx" : etSavedCVV.getText().toString(),
                    savedCardIndex,
                    SSLCSdkMainResponseModel.getLoginTransSession(),
                    isEMI,
                    savedCardEmiTenure,
                    emiBankId,
                    offerId,
                    isOffer, new SSLCPayWithStoredCardListener() {
                        @Override
                        public void payWithStoredCardInfoSuccess(SSLCTransactionInfo SSLCTransactionInfo) {
                            SSLCProgressBarHandler.hide();
                            if (SSLCTransactionInfo.getStatus().toLowerCase().equalsIgnoreCase(SSLCEnums.StatusType.SUCCESS.name().toLowerCase())) {
//                                savedCardIndex = "";
//                                savedCardEmiTenure = "";
                                if (SSLCTransactionInfo.getData().getType().toLowerCase().equalsIgnoreCase(SSLCEnums.TransactionType.otp.name().toLowerCase())
                                        || SSLCTransactionInfo.getData().getType().toLowerCase().equalsIgnoreCase(SSLCEnums.TransactionType.moto.name().toLowerCase())) {
                                    Intent intent = new Intent(getActivity(), WebViewActivitySSLC.class);
                                    intent.putExtra("redirectUrl", SSLCTransactionInfo.getData().getUrl());
                                    intent.putExtra("merchantName", "");
                                    intent.putExtra("session_key", SSLCSdkMainResponseModel.getSessionkey());
                                    intent.putExtra("sdkMainResponse", mobileSslCommerzInitialization);
                                    intent.putExtra("savedCardIndex", savedCardIndex);

                                    if (motoEnable) {
                                        intent.putExtra("motoEnable", true);
                                    }
                                    getActivity().startActivityForResult(intent, SSLCEnums.Common.Activity2.ordinal());
                                }
                            } else {
                                if (SSLCTransactionInfo.getData() != null && SSLCTransactionInfo.getData().getType().toLowerCase().equalsIgnoreCase(SSLCEnums.TransactionType.moto.name().toLowerCase())) {
                                    SSLCShareInfo.motoMap.put(savedCardIndex, true);
                                    SSLCOnBtnPayActiveListener.onBtnPayActive(false, "");
                                    SSLCAnimationManager.getInstance().expand(layoutSavedCVV);
                                    motoEnable = false;
                                }
                                SSLCShareInfo.getInstance().showToast(getActivity(), SSLCTransactionInfo.getMessage());
                            }
                        }

                        @Override
                        public void payWithStoredCardInfoFail(String message) {
                            SSLCProgressBarHandler.hide();
                            SSLCShareInfo.getInstance().showToast(getActivity(), message);
                        }

                        @Override
                        public void payWithStoredCardInfoValidationError(String message) {
                            SSLCProgressBarHandler.hide();
                            SSLCShareInfo.getInstance().showToast(getActivity(), message);
                        }
                    }
            );
        }
    }

    private void funOtpVerify(boolean isMessageShouldVisible) {
        String mobileNumber;
        if (rememberMeCv.isChecked()) {
            mobileNumber = etCardMobileNumber.getText().toString();
        } else {
            mobileNumber = etMobileNumber.getText().toString();
        }

        String pinValue = pin1.getText().toString()
                + pin2.getText().toString()
                + pin3.getText().toString()
                + pin4.getText().toString()
                + pin5.getText().toString()
                + pin6.getText().toString();

        if (pinValue.length() == 6) {
            SSLCProgressBarHandler.show();
            SSLCVerifyOtpAndLoginViewModel SSLCVerifyOtpAndLoginViewModel = new SSLCVerifyOtpAndLoginViewModel(getActivity());
            SSLCVerifyOtpAndLoginViewModel.verifyOtpAndLogin(mobileNumber, SSLCShareInfo.getInstance().getRegKey(getActivity()), SSLCShareInfo.getInstance().getEncKey(getActivity()), SSLCSdkMainResponseModel.getSessionkey(), pinValue, new SSLCVerifyOtpAndLoginListener() {
                @Override
                public void verifyOtpAndLoginSuccess(final SSLCVerifyOtpAndLoginModel SSLCVerifyOtpAndLoginModel) {
                    SSLCProgressBarHandler.hide();
                    if (SSLCVerifyOtpAndLoginModel.getStatus().toLowerCase().contains(SSLCEnums.StatusType.SUCCESS.name().toLowerCase())) {
                        String customerName = SSLCVerifyOtpAndLoginModel.getData().getData().getCustFName();
                        String lastName = SSLCVerifyOtpAndLoginModel.getData().getData().getCustLName();
                        //String customerName="";
                        if (!lastName.isEmpty()) {
                            customerName += " " + lastName;
                        }
                        SSLCOnUserVerifyListener.onUserVerify(customerName);
                        SSLCPrefUtils.setLoggedIn(context, true);
                        //onBtnPayActiveListener.onBtnPayActive(false);

                        SSLCShareInfo.getInstance().saveCustomerName(getActivity(), customerName);
                        SSLCShareInfo.getInstance().saveCustSession(getActivity(), SSLCVerifyOtpAndLoginModel.getData().getData().getCustSession());
                        SSLCShareInfo.getInstance().saveMobileNo(getActivity(), SSLCVerifyOtpAndLoginModel.getData().getData().getMobileNo());
                        SSLCShareInfo.getInstance().hideKeyboardFrom(getView());

                        if (SSLCVerifyOtpAndLoginModel.getData().getData().getCardNos().size() > 0) {
                            if (rememberMeCv.isChecked()) {
                                SSLCOnBtnPayActiveListener.onBtnPayActive(true, etCardNumber.getText().toString());
                                tvMyCards.setVisibility(View.VISIBLE);
                                // isFromCardInfoRemember = false;
                                layoutCardInfo.setVisibility(View.VISIBLE);
                                layoutOTP.setVisibility(View.GONE);
                                layoutMobileOTP.setVisibility(View.GONE);
                                layoutCardLogin.setVisibility(View.GONE);
                                layoutOtherCards.setVisibility(View.GONE);
                                layoutSaveCards.setVisibility(View.GONE);
                                setRvSaveCards(SSLCVerifyOtpAndLoginModel.getData().getData().getCardNos());
                            } else {
                                layoutCardInfo.setVisibility(View.GONE);
                                layoutOTP.setVisibility(View.GONE);
                                layoutOtherCards.setVisibility(View.GONE);
                                layoutSaveCards.setVisibility(View.VISIBLE);
                                //onBtnPayActiveListener.onBtnPayActive(true);
                                setRvSaveCards(SSLCVerifyOtpAndLoginModel.getData().getData().getCardNos());
                            }
                        } else {
                            if (rememberMeCv.isChecked()) {
                                SSLCOnBtnPayActiveListener.onBtnPayActive(true, etCardNumber.getText().toString());

                                layoutCardInfo.setVisibility(View.VISIBLE);
                                layoutOTP.setVisibility(View.GONE);
                                layoutMobileOTP.setVisibility(View.GONE);
                                layoutCardLogin.setVisibility(View.GONE);
                                layoutOtherCards.setVisibility(View.GONE);
                                layoutSaveCards.setVisibility(View.GONE);
                            } else {
                                // isFromCardInfoRemember = false;
                                layoutCardInfo.setVisibility(View.VISIBLE);
                                layoutOTP.setVisibility(View.GONE);
                                layoutCardLogin.setVisibility(View.GONE);
                                layoutOtherCards.setVisibility(View.GONE);
                                layoutSaveCards.setVisibility(View.GONE);
                            }
                        }
                    } else {
                        SSLCShareInfo.getInstance().showToast(getActivity(), SSLCVerifyOtpAndLoginModel.getMessage());
                    }
                }

                @Override
                public void verifyOtpAndLoginFail(String message) {
                    SSLCProgressBarHandler.hide();
                    SSLCShareInfo.getInstance().showToast(getActivity(), message);
                }
            });
        } else {
            if (isMessageShouldVisible) {
                SSLCShareInfo.getInstance().showToast(getActivity(), getResources().getString(R.string.pin_length_must_be));
            }
        }
    }

    public List<String> getTenures(String ccNumber) {
        List<String> tenures = new ArrayList<String>();
        tenures.add(getResources().getString(R.string.avail_emi));
        if (SSLCEMIModel != null) {
            for (SSLCEMIModel.Emi emi : SSLCEMIModel.getData().getData().getEmi()) {
                for (String str : emi.getBinList()) {
                    if (str.contains(ccNumber)) {
                        for (SSLCEMIModel.EmiBankTenureDesc emiBankTenureDesc : emi.getEmiBankTenureDesc()) {
                            tenures.add(emiBankTenureDesc.getDesc().replace("&nbsp;", ""));
                        }
                        break;
                    }
                }

                if (tenures.size() > 1) {
                    emiBankId = emi.getEmiBankID();
                    break;
                }
            }
        }

        return tenures;
    }

    private void funSetCardType(String ccNum) {
        ArrayList<String> patterns = SSLCCreditCardUtils.getInstance().cardTypeValidate();
        boolean isMatched = false;
        for (int i = 0; i < patterns.size(); i++) {
            String pattern = patterns.get(i);
            if (ccNum.matches(pattern)) {
                isMatched = true;
                if (i == 0) {
                    selectedCardType = SSLCEnums.CardType.Visa.name().toLowerCase();
                    ivCardVisa.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_visa_new));
                    ivCardMaster.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_master_grey));
                    ivCardAmex.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_amex_grey));
                    SSLCShareInfo.getInstance().setEditTextMaxLength(etCardCVC, 3);
                    //  ShareInfo.getInstance().disableGray(ivCardVisa);
                    // ShareInfo.getInstance().setToGray(ivCardMaster);
                    // ShareInfo.getInstance().setToGray(ivCardAmex);
                    //ivCardVisa.setT.setImageResource(R.drawable.ic_visa);
                } else if (i == 1 || i == 3) {
                    selectedCardType = SSLCEnums.CardType.Master.name().toLowerCase();
                    ivCardVisa.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_visa_grey));
                    ivCardMaster.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_mastercard_new));
                    ivCardAmex.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_amex_grey));
                    SSLCShareInfo.getInstance().setEditTextMaxLength(etCardCVC, 3);
                    // ShareInfo.getInstance().setToGray(ivCardVisa);
                    // ShareInfo.getInstance().disableGray(ivCardMaster);
                    // ShareInfo.getInstance().setToGray(ivCardAmex);
                    //ivCardType.setImageResource(R.drawable.ic_master);
                } else if (i == 2) {
                    selectedCardType = SSLCEnums.CardType.Amex.name().toLowerCase();
                    ivCardVisa.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_visa_grey));
                    ivCardMaster.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_master_grey));
                    ivCardAmex.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_amex_new));
                    SSLCShareInfo.getInstance().setEditTextMaxLength(etCardCVC, 4);
                    // ShareInfo.getInstance().setToGray(ivCardVisa);
                    // ShareInfo.getInstance().setToGray(ivCardMaster);
                    //ShareInfo.getInstance().disableGray(ivCardAmex);
                    //ivCardType.setImageResource(R.drawable.ic_amex);
                } else {
                    selectedCardType = "";
                    ivCardVisa.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_visa_new));
                    ivCardMaster.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_mastercard_new));
                    ivCardAmex.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_amex_new));
                    SSLCShareInfo.getInstance().setEditTextMaxLength(etCardCVC, 4);
                    // ShareInfo.getInstance().disableGray(ivCardVisa);
                    // ShareInfo.getInstance().disableGray(ivCardMaster);
                    // ShareInfo.getInstance().disableGray(ivCardAmex);
                    //ivCardType.setImageResource(R.drawable.ic_card);
                }

                break;
            }
        }
        if (!isMatched) {
            selectedCardType = "";
            ivCardVisa.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_visa_new));
            ivCardMaster.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_mastercard_new));
            ivCardAmex.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_amex_new));
            //ShareInfo.getInstance().disableGray(ivCardVisa);
            // ShareInfo.getInstance().disableGray(ivCardMaster);
            // ShareInfo.getInstance().disableGray(ivCardAmex);
        }
    }

    private void funCheckCardField() {

        if (!etCardNumber.getText().toString().isEmpty() && !etCardExpireDate.getText().toString().isEmpty() && !etCardCVC.getText().toString().isEmpty() && !etCardName.getText().toString().isEmpty()) {
            if (etCardExpireDate.getText().length() == 5 && SSLCShareInfo.getInstance().validateCardExpiryDate(etCardExpireDate.getText().toString())
                    && SSLCCreditCardUtils.getInstance().isValid(etCardNumber.getText().toString())
                    && (((selectedCardType.toLowerCase().contains(SSLCEnums.CardType.Amex.name().toLowerCase())) && etCardCVC.length() >= 4)
                    || !(selectedCardType.toLowerCase().contains(SSLCEnums.CardType.Amex.name().toLowerCase()) && etCardCVC.length() >= 3))) {
                rememberMeCv.setEnabled(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    rememberMeCv.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor())));
                }
                rememberMeCv.setChecked(false);
                layoutCardLogin.setVisibility(View.GONE);
                SSLCOnBtnPayActiveListener.onBtnPayActive(true, etCardNumber.getText().toString());  //must be called after rememberMeCv.setChecked(false);
            } else {
                rememberMeCv.setEnabled(false);
                rememberMeCv.setChecked(false);
                layoutCardLogin.setVisibility(View.GONE);
                SSLCOnBtnPayActiveListener.onBtnPayActive(false, "");  //must be called after rememberMeCv.setChecked(false);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    rememberMeCv.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey)));
                }
            }
        } else {
            rememberMeCv.setEnabled(false);
            rememberMeCv.setChecked(false);
            layoutCardLogin.setVisibility(View.GONE);
            SSLCOnBtnPayActiveListener.onBtnPayActive(false, "");  //must be called after rememberMeCv.setChecked(false);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                rememberMeCv.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey)));
            }
        }
    }

    private void initViews() {
        rememberMeCv = getActivity().findViewById(R.id.remember_me_cv);
        layoutLogin = getActivity().findViewById(R.id.layout_login);
        layoutMobileOTP = getActivity().findViewById(R.id.layout_mobile_otp);
        layoutOtherCards = getActivity().findViewById(R.id.layout_other_cards);
        layoutCardInfo = getActivity().findViewById(R.id.layout_card_info);
        layoutCardField = getActivity().findViewById(R.id.layout_card_field);
        layoutOTP = getActivity().findViewById(R.id.layout_otp);
        layoutEMIInfo = getActivity().findViewById(R.id.layout_emi_info);
        layoutOtpExistingCard = getActivity().findViewById(R.id.layout_otp_existing_card);
        tvOtpCardNumber = getActivity().findViewById(R.id.tv_otp_card_number);
        ivOtpCardType = getActivity().findViewById(R.id.iv_otp_card_type);
        tvOtpHasEmi = getActivity().findViewById(R.id.tv_otp_has_emi);
        layoutEMI = getActivity().findViewById(R.id.layout_emi);
        btnMobileVerify = getActivity().findViewById(R.id.btn_verify);
        btnCardMobileVerify = getActivity().findViewById(R.id.btn_card_mobile_verify);
        tvChangeNumber = getActivity().findViewById(R.id.tv_change_number);
        tvSkipLogin = getActivity().findViewById(R.id.tv_skip_login);
        etMobileNumber = getActivity().findViewById(R.id.et_mobile_number_ssl);
        etCardMobileNumber = getActivity().findViewById(R.id.et_card_mobile_number);
        tvCustomerName = getActivity().findViewById(R.id.tv_customer_name);
        tvResendOTP = getActivity().findViewById(R.id.tv_resend_otp);
        tvMyCards = getActivity().findViewById(R.id.tv_my_cards);
        tvWelcome = getActivity().findViewById(R.id.tv_welcome);
        tvAvailEmi = getActivity().findViewById(R.id.tv_avail_emi);
        tvTermCondition = getActivity().findViewById(R.id.tv_term_condition);
        etCardNumber = getActivity().findViewById(R.id.et_card_number_ssl);
        tvAlreadyRegistered = getActivity().findViewById(R.id.tv_already_registered);
        tvRegistered = getActivity().findViewById(R.id.tv_registered);
        tvOtpHeader = getActivity().findViewById(R.id.tv_otp_header);
        ivMoreInfo = getActivity().findViewById(R.id.iv_more_info);
        tvOtpChangeCard = getActivity().findViewById(R.id.tv_otp_change_card);
        view1 = getActivity().findViewById(R.id.view1);
        etCardNumber.isInputTypeCard(true);

        etCardName = getActivity().findViewById(R.id.et_card_holder_name_ssl);
        etCardExpireDate = getActivity().findViewById(R.id.et_card_expiry_date);
        etCardCVC = getActivity().findViewById(R.id.et_card_cvc_cvv);
        ivCardVisa = getActivity().findViewById(R.id.iv_card_visa);
        ivCardAmex = getActivity().findViewById(R.id.iv_card_amex);
        ivCardMaster = getActivity().findViewById(R.id.iv_card_master);


        if (SSLCSdkMainResponseModel.getGw().getVisa().equals("")) {
            ivCardVisa.setVisibility(View.GONE);
        }

        if (SSLCSdkMainResponseModel.getGw().getMaster().equals("")) {
            ivCardMaster.setVisibility(View.GONE);
        }

        if (SSLCSdkMainResponseModel.getGw().getAmex().equals("")) {
            ivCardAmex.setVisibility(View.GONE);
        }

        rvOtherCards = getActivity().findViewById(R.id.rv_other_cards);
        rvSaveCards = getActivity().findViewById(R.id.rv_save_cards);
        layoutSaveCards = getActivity().findViewById(R.id.layout_save_cards);
        layoutOffer = getActivity().findViewById(R.id.layout_card_offer);
        tvCardOfferTitle = getActivity().findViewById(R.id.tv_offer_title);
        layoutCardLogin = getActivity().findViewById(R.id.layout_card_login);

        //tvOfferTitle = getActivity().findViewById(R.id.tv_offer_title);
        // tvOfferDiscount = getActivity().findViewById(R.id.tv_offer_discount);
        //ivOffer = getActivity().findViewById(R.id.iv_offer);
        // ivOfferCancel = getActivity().findViewById(R.id.iv_offer_cancel);
        ivEMIInfo = getActivity().findViewById(R.id.iv_emi_info);
        layoutSaveCards.setVisibility(View.GONE);
        layoutOffer.setVisibility(View.GONE);
        tvMyCards.setVisibility(View.GONE);
        rememberMeCv.setEnabled(false);

        tvOtherCards = getActivity().findViewById(R.id.tv_other_cards);

        if (SSLCSdkMainResponseModel.getGw().getOthercards().equals("")) {
            tvOtherCards.setVisibility(View.GONE);
        }

        //tvChangeNumber = getActivity().findViewById(R.id.tv_change_numeber);
        pin1 = getActivity().findViewById(R.id.pin1);
        pin2 = getActivity().findViewById(R.id.pin2);
        pin3 = getActivity().findViewById(R.id.pin3);
        pin4 = getActivity().findViewById(R.id.pin4);
        pin5 = getActivity().findViewById(R.id.pin5);
        pin6 = getActivity().findViewById(R.id.pin6);
        tvChangeNumberOtp = getActivity().findViewById(R.id.tv_change_number_otp);

        tvSkipLogin.setTextColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        tvChangeNumber.setTextColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));

        tvChangeNumberOtp.setTextColor(getResources().getColor(R.color.grey));
        tvOtpChangeCard.setTextColor(getResources().getColor(R.color.grey));

        tvResendOTP.setTextColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        tvOtherCards.setTextColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        tvMyCards.setTextColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        SSLCCustomTextView tvCardMobileFormat = getActivity().findViewById(R.id.tv_card_mobile_format);
        tvCardMobileFormat.setTextColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));

//        if (Build.VERSION.SDK_INT < 21) {
//            CompoundButtonCompat.setButtonTintList(rememberMeCv, ColorStateList.valueOf(Color.parseColor("#" + sdkMainResponseModel.getPrimaryColor())));//Use android.support.v4.widget.CompoundButtonCompat when necessary else
//        }else {
//            rememberMeCv.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#" + sdkMainResponseModel.getPrimaryColor())));
//        }

        //btnCardMobileVerify.setCompoundDrawableTintList(ColorStateList.valueOf(Color.parseColor("#ff9708")));

        //Drawable drawable = getResources().getDrawable(R.drawable.custom_btn_clementine).mutate();
        //drawable.setColorFilter(Color.parseColor("#" + sdkMainResponseModel.getPrimaryColor()), PorterDuff.Mode.SRC_ATOP);
        //btnCardMobileVerify.setCompoundDrawables(null, drawable, null, null);

        layoutEMI.setBackground(SSLCShareInfo.getInstance().setBackgroundColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getActiveColor()), Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()), getResources().getColor(R.color.very_light_grey)));
        btnMobileVerify.setBackground(SSLCShareInfo.getInstance().setBackgroundColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getActiveColor()), Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()), getResources().getColor(R.color.very_light_grey)));
        btnCardMobileVerify.setBackground(SSLCShareInfo.getInstance().setBackgroundColor(Color.parseColor("#" + SSLCSdkMainResponseModel.getActiveColor()), Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()), getResources().getColor(R.color.very_light_grey)));
        //btnCardMobileVerify.setEnabled(false);
        ///StateListDrawable sd = (StateListDrawable) btnCardMobileVerify.getBackground().mutate();
        //sd.se(Color.parseColor("#" + sdkMainResponseModel.getPrimaryColor()));

        tvChangeNumberOtp.setEnabled(false);
        tvOtpChangeCard.setEnabled(false);

        SSLCChangeCursorColor SSLCChangeCursorColor = new SSLCChangeCursorColor(context);
        SSLCChangeCursorColor.setCursorColor(etMobileNumber, Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        SSLCChangeCursorColor.setCursorColor(etCardMobileNumber, Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        SSLCChangeCursorColor.setCursorColor(etCardNumber, Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        SSLCChangeCursorColor.setCursorColor(etCardExpireDate, Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        SSLCChangeCursorColor.setCursorColor(etCardMobileNumber, Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        SSLCChangeCursorColor.setCursorColor(etCardCVC, Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        //etCardCVC.setColorC(Color.parseColor("#" + sdkMainResponseModel.getPrimaryColor()));
        SSLCChangeCursorColor.setCursorColor(etCardName, Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        // ShareInfo.getInstance().setCursorPointerColor(etCardCVC, Color.parseColor("#" + sdkMainResponseModel.getPrimaryColor()));
        // ShareInfo.getInstance().setCursorDrawableColor1(etCardCVC, Color.parseColor("#" + sdkMainResponseModel.getPrimaryColor()));
        //etCardCVC.setTextColor(Color.parseColor("#" + sdkMainResponseModel.getPrimaryColor()));
        // ShareInfo.getInstance().setCursorDrawableColor1(etCardName, Color.parseColor("#" + sdkMainResponseModel.getPrimaryColor()));

        SSLCChangeCursorColor.setCursorColor(pin1, Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        SSLCChangeCursorColor.setCursorColor(pin2, Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        SSLCChangeCursorColor.setCursorColor(pin3, Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        SSLCChangeCursorColor.setCursorColor(pin4, Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        SSLCChangeCursorColor.setCursorColor(pin5, Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
        SSLCChangeCursorColor.setCursorColor(pin6, Color.parseColor("#" + SSLCSdkMainResponseModel.getPrimaryColor()));
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SSLCOnUserVerifyListener) {
            SSLCOnUserVerifyListener = (SSLCOnUserVerifyListener) context;
            SSLCOnBtnPayActiveListener = (SSLCOnBtnPayActiveListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        SSLCOnUserVerifyListener = null;
        SSLCOnBtnPayActiveListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
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
        if(etCardNumber!= null) {
            etCardNumber.setHint(R.string.enter_card_number);
        }
        if(etCardCVC!= null) {
            etCardCVC.setHint(R.string.cvc);
        }
        if(etCardExpireDate!= null) {
            etCardExpireDate.setHint(R.string.enter_expire_date);
        }
        if(etCardName!= null) {
            etCardName.setHint(R.string.card_holder_name);
        }
        if(rememberMeCv!= null) {
            rememberMeCv.setText(R.string.remember_me);
        }
        if(tvMyCards!= null) {
            tvMyCards.setText(R.string.my_cards);
        }
        if(tvOtherCards!= null) {
            tvOtherCards.setText(R.string.other_cards);
        }
        if(tvOtpChangeCard!= null) {
            tvOtpChangeCard.setText(R.string.change_card);
        }
        if(tvAvailEmi!= null) {
            tvAvailEmi.setText(R.string.avail_emi);
        }
        if(tvTermCondition!= null) {
            tvTermCondition.setText(R.string.remember_me_warning);
        }
        if(tvAlreadyRegistered!= null) {
            tvAlreadyRegistered.setText(R.string.already_registered_so_verify);
        }
        if(tvRegistered!= null) {
            tvRegistered.setText(R.string.registered_so_verify);
        }
        if(tvChangeNumber!= null) {
            tvChangeNumber.setText(R.string.change_number);
        }
        if(tvChangeNumberOtp!= null) {
            tvChangeNumberOtp.setText(R.string.change_number);
        }
        if(tvWelcome!= null) {
            tvWelcome.setText(R.string.welcome_back_coma);
        }
        if(tvSkipLogin!= null) {
            tvSkipLogin.setText(R.string.skip_this_step);
        }
        if(btnMobileVerify!= null) {
            btnMobileVerify.setText(R.string.verify);
        }
        if(btnCardMobileVerify!= null) {
            btnCardMobileVerify.setText(R.string.verify);
        }
        if(etMobileNumber!= null) {
            etMobileNumber.setHint(R.string.enter_mobile_number);
        }
        if(etCardMobileNumber!= null) {
            etCardMobileNumber.setHint(R.string.enter_mobile_number);
        }
        if(tvResendOTP!= null) {
            tvResendOTP.setText(R.string.resend_otp);
        }
        if(tvOtpHasEmi!= null) {
            tvOtpHasEmi.setText(R.string.no_emi);
        }
        if(tvOtpHeader!= null) {
            tvOtpHeader.setText(R.string.verification_code);
        }

        if (SSLCSaveCardsAdapter != null) {
            SSLCSaveCardsAdapter.notifyDataSetChanged();
        }
        if (SSLCOtherCardsAdapter != null) {
            SSLCOtherCardsAdapter.notifyDataSetChanged();
        }

        super.onConfigurationChanged(newConfig);
    }
}

