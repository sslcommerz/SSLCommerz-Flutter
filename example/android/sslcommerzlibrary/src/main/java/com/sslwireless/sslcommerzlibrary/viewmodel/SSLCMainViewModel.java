package com.sslwireless.sslcommerzlibrary.viewmodel;

import androidx.lifecycle.ViewModel;
import android.content.Context;

import com.google.gson.Gson;
import com.sslwireless.sslcommerzlibrary.BuildConfig;
import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerClass;
import com.sslwireless.sslcommerzlibrary.model.datamodel.SSLCApiHandlerListener;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCAdditionalInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCCustomerInfoInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCEMITransactionInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCProductInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCShipmentInfoInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCSdkMainResponseModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCMethodIndentification;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCBaseSuccessResponseListener;

import org.json.JSONObject;

import java.util.HashMap;

public class SSLCMainViewModel extends ViewModel implements SSLCApiHandlerListener {

    private Context context;
    private SSLCApiHandlerClass SSLCApiHandlerClass;
    private SSLCBaseSuccessResponseListener SSLCBaseSuccessResponseListener;

    public SSLCMainViewModel(Context context) {
        this.context = context;
        SSLCApiHandlerClass = new SSLCApiHandlerClass(context);
    }

    public void getApiCall() {
        SSLCApiHandlerClass.sslCommerzRequest(context,
                "", "", SSLCMethodIndentification.METHOD_GET, "", new HashMap<String, Object>(),
                false, this);
    }

    public void postApiCall(SSLCommerzInitialization sslCommerzInitialization,
                            SSLCShipmentInfoInitializer SSLCShipmentInfoInitializer, SSLCProductInitializer SSLCProductInitializer,
                            SSLCEMITransactionInitializer SSLCEMITransactionInitializer,
                            SSLCCustomerInfoInitializer SSLCCustomerInfoInitializer,
                            SSLCAdditionalInitializer SSLCAdditionalInitializer,
                            SSLCBaseSuccessResponseListener SSLCBaseSuccessResponseListener) {
        this.SSLCBaseSuccessResponseListener = SSLCBaseSuccessResponseListener;

        HashMap<String, Object> mHashMap = new HashMap<>();

        if (sslCommerzInitialization != null) {
            initMainInitializer(mHashMap, sslCommerzInitialization);
        } else {
            SSLCBaseSuccessResponseListener.validationError("Initialization error. Please check your submitted value");
        }

        if (SSLCShipmentInfoInitializer != null) {
            initShipmentInitialization(mHashMap, SSLCShipmentInfoInitializer);
        }

        if (SSLCProductInitializer != null) {
            initProductInitializer(mHashMap, SSLCProductInitializer);
        }

        if (SSLCEMITransactionInitializer != null) {
            initEmiInitializer(mHashMap, SSLCEMITransactionInitializer);
        }

        if (SSLCCustomerInfoInitializer != null) {
            initCustomerInitialization(mHashMap, SSLCCustomerInfoInitializer);
        }

        if (SSLCAdditionalInitializer != null) {
            initAdditionInitializer(mHashMap, SSLCAdditionalInitializer);
        }

        SSLCShareInfo.getInstance().saveType(context, sslCommerzInitialization.getSdkType());
//        Log.e(TAG, "postApiCall: " + SSLCShareInfo.getInstance().getType(context));

        if (SSLCShareInfo.getInstance().isNetworkAvailable(context)) {
            SSLCApiHandlerClass.sslCommerzRequest(context,
                    SSLCShareInfo.getInstance().getType(context).equals(SSLCSdkType.LIVE) ? BuildConfig.BASE_URL :
                            BuildConfig.BASE_URL_SANDBOX, "sdk.php",
                    SSLCMethodIndentification.METHOD_POST, "", mHashMap,
                    false, this);
        } else {
            SSLCBaseSuccessResponseListener.failResponse(context.getResources().getString(R.string.internet_connection));
        }
    }

    private void initAdditionInitializer(HashMap<String, Object> mHashMap,
                                         SSLCAdditionalInitializer SSLCAdditionalInitializer) {
        mHashMap.put("value_a", SSLCAdditionalInitializer.getValueA());
        mHashMap.put("value_b", SSLCAdditionalInitializer.getValueB());
        mHashMap.put("value_c", SSLCAdditionalInitializer.getValueC());
        mHashMap.put("value_d", SSLCAdditionalInitializer.getValueD());
    }

    private void initEmiInitializer(HashMap<String, Object> mHashMap,
                                    SSLCEMITransactionInitializer SSLCEMITransactionInitializer) {
        mHashMap.put("emi_option", SSLCEMITransactionInitializer.getEmi_options());
        mHashMap.put("emi_max_inst_option", SSLCEMITransactionInitializer.getEmi_max_list_options());
        mHashMap.put("emi_selected_inst", SSLCEMITransactionInitializer.getEmi_selected_inst());
    }

    private void initProductInitializer(HashMap<String, Object> mHashMap, SSLCProductInitializer SSLCProductInitializer) {
        mHashMap.put("product_name", SSLCProductInitializer.getProductName());
        mHashMap.put("product_category", SSLCProductInitializer.getProductCategory());

        if (SSLCProductInitializer.getAirlinesTicket() != null) {
            mHashMap.put("product_profile", SSLCProductInitializer.getAirlinesTicket().getProductProfile());
        } else if (SSLCProductInitializer.getGeneral() != null) {
            mHashMap.put("product_profile", SSLCProductInitializer.getGeneral().getProductProfile());
        } else if (SSLCProductInitializer.getPhysicalGoods() != null) {
            mHashMap.put("product_profile", SSLCProductInitializer.getPhysicalGoods().getProductProfile());
        } else if (SSLCProductInitializer.getNonPhysicalGoods() != null) {
            mHashMap.put("product_profile", SSLCProductInitializer.getNonPhysicalGoods().getProductProfile());
        } else if (SSLCProductInitializer.getTelecomVertical() != null) {
            mHashMap.put("product_profile", SSLCProductInitializer.getTelecomVertical().getProductProfile());
        } else if (SSLCProductInitializer.getTravelVertical() != null) {
            mHashMap.put("product_profile", SSLCProductInitializer.getTravelVertical().getProductProfile());
        }

        if (SSLCProductInitializer.getAirlinesTicket() != null) {
            mHashMap.put("hours_till_departure", SSLCProductInitializer.getAirlinesTicket().getHoursTillDeparture() != null ?
                    SSLCProductInitializer.getAirlinesTicket().getHoursTillDeparture() : "");
            mHashMap.put("flight_type", SSLCProductInitializer.getAirlinesTicket().getFlightType() != null ?
                    SSLCProductInitializer.getAirlinesTicket().getFlightType() : "");
            mHashMap.put("pnr", SSLCProductInitializer.getAirlinesTicket().getPnr() != null ?
                    SSLCProductInitializer.getAirlinesTicket().getPnr() : "");
            mHashMap.put("journey_from_to", SSLCProductInitializer.getAirlinesTicket().getJourneyFromTo() != null ?
                    SSLCProductInitializer.getAirlinesTicket().getJourneyFromTo() : "");
        }

        if (SSLCProductInitializer.getTravelVertical() != null) {
            mHashMap.put("hotel_name", SSLCProductInitializer.getTravelVertical().getHotelName() != null ?
                    SSLCProductInitializer.getTravelVertical().getHotelName() : "");
            mHashMap.put("length_of_stay", SSLCProductInitializer.getTravelVertical().getLengthOfStay() != null ?
                    SSLCProductInitializer.getTravelVertical().getLengthOfStay() : "");
            mHashMap.put("check_in_time", SSLCProductInitializer.getTravelVertical().getCheckInTime() != null ?
                    SSLCProductInitializer.getTravelVertical().getCheckInTime() : "");
            mHashMap.put("hotel_city", SSLCProductInitializer.getTravelVertical().getHotelCity() != null ?
                    SSLCProductInitializer.getTravelVertical().getHotelCity() : "");
        }

        if (SSLCProductInitializer.getTelecomVertical() != null) {
            mHashMap.put("product_type", SSLCProductInitializer.getTelecomVertical().getProductType() != null ?
                    SSLCProductInitializer.getTelecomVertical().getTopUpNumber() : "");
            mHashMap.put("topup_number", SSLCProductInitializer.getTelecomVertical().getTopUpNumber() != null ?
                    SSLCProductInitializer.getTelecomVertical().getTopUpNumber() : "");
            mHashMap.put("country_topup", SSLCProductInitializer.getTelecomVertical().getCountryTopUp() != null ?
                    SSLCProductInitializer.getTelecomVertical().getCountryTopUp() : "");
        }

        mHashMap.put("cart", SSLCProductInitializer.getCart());
        mHashMap.put("vat", SSLCProductInitializer.getVat());
        mHashMap.put("discount_amount", SSLCProductInitializer.getDiscountAmount());
        mHashMap.put("convenience_fee", SSLCProductInitializer.getConvenienceFee());
        mHashMap.put("product_amount", SSLCProductInitializer.getProductAmount());

//        Log.e(TAG, "initProductInitializer data: " + new Gson().toJson(mHashMap));
    }

    private void initCustomerInitialization(HashMap<String, Object> mHashMap, SSLCCustomerInfoInitializer SSLCCustomerInfoInitializer) {
        mHashMap.put("cus_name", SSLCCustomerInfoInitializer.getCustomerName());
        mHashMap.put("cus_email", SSLCCustomerInfoInitializer.getCustomerEmail());
        mHashMap.put("cus_add1", SSLCCustomerInfoInitializer.getCustomerAddress1());
        mHashMap.put("cus_add2", SSLCCustomerInfoInitializer.getCustomerAddress2());
        mHashMap.put("cus_city", SSLCCustomerInfoInitializer.getCustomerCity());
        mHashMap.put("cus_state", SSLCCustomerInfoInitializer.getCustomerState());
        mHashMap.put("cus_postcode", SSLCCustomerInfoInitializer.getCustomerPostCode());
        mHashMap.put("cus_country", SSLCCustomerInfoInitializer.getCustomerCountry());
        mHashMap.put("cus_phone", SSLCCustomerInfoInitializer.getCustomerPhone());
        mHashMap.put("cus_fax", SSLCCustomerInfoInitializer.getCustomerFax());
    }

    private void initShipmentInitialization(HashMap<String, Object> mHashMap,
                                            SSLCShipmentInfoInitializer SSLCShipmentInfoInitializer) {
        mHashMap.put("ship_name", SSLCShipmentInfoInitializer.getShipmentDetails().getShipName());
        mHashMap.put("ship_add1", SSLCShipmentInfoInitializer.getShipmentDetails().getShipAddress1());
        mHashMap.put("ship_add2", SSLCShipmentInfoInitializer.getShipAddress2());
        mHashMap.put("ship_city", SSLCShipmentInfoInitializer.getShipmentDetails().getShipCity());
        mHashMap.put("ship_state", SSLCShipmentInfoInitializer.getShipState());
        mHashMap.put("ship_postcode", SSLCShipmentInfoInitializer.getShipmentDetails().getShipPostCode());
        mHashMap.put("ship_country", SSLCShipmentInfoInitializer.getShipmentDetails().getShipCountry());
        mHashMap.put("shipping_method", SSLCShipmentInfoInitializer.getShipmentMethod());
        mHashMap.put("num_of_item", String.valueOf(SSLCShipmentInfoInitializer.getNumOfItems()));
    }

    private void initMainInitializer(HashMap<String, Object> mHashMap,
                                     SSLCommerzInitialization sslCommerzInitialization) {
        sslCommerzInitialization.setSuccess_url("https://securepay.sslcommerz.com/gw/apps/result.php");

        mHashMap.put("store_id", sslCommerzInitialization.getStore_id());
        mHashMap.put("store_passwd", sslCommerzInitialization.getStore_passwd());
        mHashMap.put("total_amount", sslCommerzInitialization.getTotal_amount());
        mHashMap.put("currency", sslCommerzInitialization.getCurrency());
        mHashMap.put("tran_id", sslCommerzInitialization.getTran_id());
        mHashMap.put("product_category", sslCommerzInitialization.getProduct_category());
        mHashMap.put("ipn_url", sslCommerzInitialization.getIpn_url());
        mHashMap.put("multi_card_name", sslCommerzInitialization.getMulti_card_name());
        mHashMap.put("allowed_bin", sslCommerzInitialization.getAllowed_bin());
        mHashMap.put("success_url", "https://securepay.sslcommerz.com/gw/apps/result.php");
        mHashMap.put("fail_url", "https://securepay.sslcommerz.com/gw/apps/result.php");
        mHashMap.put("cancel_url", "https://securepay.sslcommerz.com/gw/apps/result.php");
    }

    @Override
    public void success(JSONObject jsonObject) {
//        Log.e(TAG, "success: " + jsonObject);
        Gson gson = new Gson();
        SSLCSdkMainResponseModel SSLCSdkMainResponseModel = gson.fromJson(jsonObject.toString(), SSLCSdkMainResponseModel.class);

        SSLCBaseSuccessResponseListener.successResponse(SSLCSdkMainResponseModel);
    }

    @Override
    public void fail(String message) {
        SSLCBaseSuccessResponseListener.failResponse(message);
    }
}
