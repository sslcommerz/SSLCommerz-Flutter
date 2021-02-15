package com.sslwireless.sslcommerzlibrary.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SSLCSdkMainResponseModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("failedreason")
    @Expose
    private String failedreason;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("sessionkey")
    @Expose
    private String sessionkey;
    @SerializedName("gw")
    @Expose
    private Gw gw;
    @SerializedName("redirectGatewayURL")
    @Expose
    private String redirectGatewayURL;
    @SerializedName("GatewayPageURL")
    @Expose
    private String gatewayPageURL;
    @SerializedName("directPaymentURL")
    @Expose
    private String directPaymentURL;
    @SerializedName("design")
    @Expose
    private Design design;
    @SerializedName("store_name")
    @Expose
    private String storeName;
    @SerializedName("storeBanner")
    @Expose
    private String storeBanner;
    @SerializedName("storeLogo")
    @Expose
    private String storeLogo;
    @SerializedName("desc")
    @Expose
    private List<Desc> desc = null;
    @SerializedName("tran_id")
    @Expose
    private String tranId;
    @SerializedName("subscription_id")
    @Expose
    private String subscriptionId;
    @SerializedName("subscription_status")
    @Expose
    private String subscriptionStatus;
    @SerializedName("subscription_error")
    @Expose
    private String subscriptionError;
    @SerializedName("is_direct_pay_enable")
    @Expose
    private String isDirectPayEnable;
    @SerializedName("loginTransSession")
    @Expose
    private String loginTransSession;
    @SerializedName("primaryColor")
    @Expose
    private String primaryColor;
    @SerializedName("activeColor")
    @Expose
    private String activeColor;
    @SerializedName("CustomerName")
    @Expose
    private String CustomerName;
    @SerializedName("cust_mobile")
    @Expose
    private String cust_mobile;
    @SerializedName("default_tab")
    @Expose
    private String default_tab;
    @SerializedName("emi_status")
    @Expose
    private Integer emiStatus;
    @SerializedName("existingMobile")
    @Expose
    private Integer existingMobile;
    @SerializedName("numberOfCardSaved")
    @Expose
    private Integer numberOfCardSaved;
    @SerializedName("offer_status")
    @Expose
    private Integer offerStatus;
    @SerializedName("timeoutinMin")
    @Expose
    private String timeoutinMin;
    @SerializedName("termsAndConditionURL")
    @Expose
    private String termsAndConditionURL;
    @SerializedName("FAQURL")
    @Expose
    private String FAQURL;
    @SerializedName("moreInfoURL")
    @Expose
    private String moreInfoURL;
    @SerializedName("currency_type")
    @Expose
    private String currencyType;
    @SerializedName("currency_amount")
    @Expose
    private String currencyAmount;
    @SerializedName("currency_rate")
    @Expose
    private String currencyRate;
    @SerializedName("facebookPageURL")
    @Expose
    private String facebookPageURL;
    @SerializedName("supportEmail")
    @Expose
    private String supportEmail;
    @SerializedName("supportPhone")
    @Expose
    private String supportPhone;

    public SSLCSdkMainResponseModel() {
        cust_mobile = "";
        CustomerName = "";
    }

    public String getTermsAndConditionURL() {
        return termsAndConditionURL;
    }

    public void setTermsAndConditionURL(String termsAndConditionURL) {
        this.termsAndConditionURL = termsAndConditionURL;
    }

    public String getDefault_tab() {
        return default_tab;
    }

    public void setDefault_tab(String default_tab) {
        this.default_tab = default_tab;
    }

    public String getFAQURL() {
        return FAQURL;
    }

    public void setFAQURL(String FAQURL) {
        this.FAQURL = FAQURL;
    }

    public String getFacebookPageURL() {
        return facebookPageURL;
    }

    public void setFacebookPageURL(String facebookPageURL) {
        this.facebookPageURL = facebookPageURL;
    }

    public String getTimeoutinMin() {
        return timeoutinMin;
    }

    public void setTimeoutinMin(String timeoutinMin) {
        this.timeoutinMin = timeoutinMin;
    }

    public String getSupportEmail() {
        return supportEmail;
    }

    public void setSupportEmail(String supportEmail) {
        this.supportEmail = supportEmail;
    }

    public String getSupportPhone() {
        return supportPhone;
    }

    public void setSupportPhone(String supportPhone) {
        this.supportPhone = supportPhone;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustMobile() {
        return cust_mobile;
    }

    public void setCustMobile(String cust_mobile) {
        this.cust_mobile = cust_mobile;
    }

    public Integer getExistingMobile() {
        return existingMobile;
    }

    public void setExistingMobile(Integer existingMobile) {
        this.existingMobile = existingMobile;
    }

    public Integer getNumberOfCardSaved() {
        return numberOfCardSaved;
    }

    public void setNumberOfCardSaved(Integer numberOfCardSaved) {
        this.numberOfCardSaved = numberOfCardSaved;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(String currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public String getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(String currencyRate) {
        this.currencyRate = currencyRate;
    }

    public String getMoreInfoURL() {
        return moreInfoURL;
    }

    public void setMoreInfoURL(String moreInfoURL) {
        this.moreInfoURL = moreInfoURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFailedreason() {
        return failedreason;
    }

    public void setFailedreason(String failedreason) {
        this.failedreason = failedreason;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSessionkey() {
        return sessionkey;
    }

    public void setSessionkey(String sessionkey) {
        this.sessionkey = sessionkey;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Gw getGw() {
        return gw;
    }

    public void setGw(Gw gw) {
        this.gw = gw;
    }

    public String getRedirectGatewayURL() {
        return redirectGatewayURL;
    }

    public void setRedirectGatewayURL(String redirectGatewayURL) {
        this.redirectGatewayURL = redirectGatewayURL;
    }

    public String getGatewayPageURL() {
        return gatewayPageURL;
    }

    public void setGatewayPageURL(String gatewayPageURL) {
        this.gatewayPageURL = gatewayPageURL;
    }

    public String getDirectPaymentURL() {
        return directPaymentURL;
    }

    public void setDirectPaymentURL(String directPaymentURL) {
        this.directPaymentURL = directPaymentURL;
    }

    public Design getDesign() {
        return design;
    }

    public void setDesign(Design design) {
        this.design = design;
    }

    public String getStoreBanner() {
        return storeBanner;
    }

    public void setStoreBanner(String storeBanner) {
        this.storeBanner = storeBanner;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public List<Desc> getDesc() {
        return desc;
    }

    public void setDesc(List<Desc> desc) {
        this.desc = desc;
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public String getSubscriptionError() {
        return subscriptionError;
    }

    public void setSubscriptionError(String subscriptionError) {
        this.subscriptionError = subscriptionError;
    }

    public String getIsDirectPayEnable() {
        return isDirectPayEnable;
    }

    public void setIsDirectPayEnable(String isDirectPayEnable) {
        this.isDirectPayEnable = isDirectPayEnable;
    }

    public String getLoginTransSession() {
        return loginTransSession;
    }

    public void setLoginTransSession(String loginTransSession) {
        this.loginTransSession = loginTransSession;
    }

    public String getPrimaryColor() {
        if(primaryColor!= null){
            if(primaryColor.isEmpty()){
                primaryColor="00FF00";
            }
        }
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getActiveColor() {
        if(activeColor!= null){
            if(activeColor.isEmpty()){
                activeColor="00FF00";
            }
        }
        return activeColor;
    }

    public void setActiveColor(String activeColor) {
        this.activeColor = activeColor;
    }

    public Integer getEmiStatus() {
        return emiStatus;
    }

    public void setEmiStatus(Integer emiStatus) {
        this.emiStatus = emiStatus;
    }

    public Integer getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(Integer offerStatus) {
        this.offerStatus = offerStatus;
    }

    public String toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cust_mobile", getCustMobile());
            jsonObject.put("CustomerName", getCustomerName());
            jsonObject.put("sessionkey", getSessionkey());
            jsonObject.put("amount", getAmount());
            jsonObject.put("existingMobile", getExistingMobile());
            jsonObject.put("numberOfCardSaved", getNumberOfCardSaved());
            jsonObject.put("emi_status", getEmiStatus());
            jsonObject.put("offer_status", getOfferStatus());
            jsonObject.put("loginTransSession", getLoginTransSession());
            jsonObject.put("storeLogo", getStoreLogo());
            jsonObject.put("store_name", getStoreName());
            jsonObject.put("timeoutinMin", getTimeoutinMin());
            jsonObject.put("termsAndConditionURL", getTermsAndConditionURL());
            jsonObject.put("FAQURL", getFAQURL());
            jsonObject.put("moreInfoURL", getMoreInfoURL());
            jsonObject.put("facebookPageURL", getFacebookPageURL());
            jsonObject.put("currency_type", getCurrencyType());
            jsonObject.put("currency_amount", getCurrencyAmount());
            jsonObject.put("currency_rate", getCurrencyRate());
            jsonObject.put("supportEmail", getSupportEmail());
            jsonObject.put("supportPhone", getSupportPhone());
            jsonObject.put("activeColor", getActiveColor());
            jsonObject.put("primaryColor", getPrimaryColor());

            jsonObject.put("default_tab", getDefault_tab());

            Design design = getDesign();
            if (design != null) {
                JSONObject jsonObjectDesign = new JSONObject();
                jsonObjectDesign.put("main_bk_img", design.getMainBkImg());
                jsonObjectDesign.put("main_bk_color", design.getMainBkColor());
                jsonObjectDesign.put("main_font_color", design.getMainFontColor());
                jsonObjectDesign.put("title_bk_color", design.getTitleBkColor());
                jsonObjectDesign.put("title_font_color", design.getTitleFontColor());
                jsonObjectDesign.put("btn_yes_bk_color", design.getBtnYesBkColor());
                jsonObjectDesign.put("btn_yes_font_color", design.getBtnYesFontColor());
                jsonObjectDesign.put("btn_no_bk_color", design.getBtnNoBkColor());
                jsonObjectDesign.put("btn_no_font_color", design.getBtnNoFontColor());

                jsonObject.put("design", jsonObjectDesign);
            }

            Gw gw = getGw();
            if (gw != null) {
                JSONObject jsonObjectGw = new JSONObject();
                jsonObjectGw.put("visa", gw.getVisa());
                jsonObjectGw.put("master", gw.getMaster());
                jsonObjectGw.put("amex", gw.getAmex());
                jsonObjectGw.put("othercards", gw.getOthercards());
                jsonObjectGw.put("internetbanking", gw.getInternetbanking());
                jsonObjectGw.put("mobilebanking", gw.getMobilebanking());

                jsonObject.put("gw", jsonObjectGw);
            }

            JSONArray jsonArray = new JSONArray();
            List<Desc> descs = getDesc();
            if (descs != null) {
                for (Desc descObj : descs) {
                    JSONObject jsonObjectDesc = new JSONObject();
                    jsonObjectDesc.put("name", descObj.getName());
                    jsonObjectDesc.put("type", descObj.getType());
                    jsonObjectDesc.put("logo", descObj.getLogo());
                    jsonObjectDesc.put("transAmt", descObj.getTransAmt());
                    jsonObjectDesc.put("payableAmt", descObj.getPayableAmt());
                    jsonObjectDesc.put("charge", descObj.getCharge());
                    jsonObjectDesc.put("rFlag", descObj.getRFlag());
                    jsonObjectDesc.put("redirectGatewayURL", descObj.getRedirectGatewayURL());
                    jsonObjectDesc.put("autoselect", descObj.getAutoselect());

                    jsonArray.put(jsonObjectDesc);
                }
            }
            jsonObject.put("desc", jsonArray);

            return jsonObject.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }

    }

    public SSLCSdkMainResponseModel fromJSON(String jsonString) {
        try {

            SSLCSdkMainResponseModel SSLCSdkMainResponseModel = new SSLCSdkMainResponseModel();
            JSONObject jsonObject = new JSONObject(jsonString);
            if (jsonObject.has("cust_mobile") && !jsonObject.isNull("cust_mobile")) {
                SSLCSdkMainResponseModel.setCustMobile(jsonObject.getString("cust_mobile"));
            }
            if (jsonObject.has("CustomerName") && !jsonObject.isNull("CustomerName")) {
                SSLCSdkMainResponseModel.setCustomerName(jsonObject.getString("CustomerName"));
            }
            if (jsonObject.has("sessionkey") && !jsonObject.isNull("sessionkey")) {
                SSLCSdkMainResponseModel.setSessionkey(jsonObject.getString("sessionkey"));
            }
            if (jsonObject.has("amount") && !jsonObject.isNull("amount")) {
                SSLCSdkMainResponseModel.setAmount(jsonObject.getString("amount"));
            }
            if (jsonObject.has("existingMobile") && !jsonObject.isNull("existingMobile")) {
                SSLCSdkMainResponseModel.setExistingMobile(jsonObject.getInt("existingMobile"));
            }
            if (jsonObject.has("numberOfCardSaved") && !jsonObject.isNull("numberOfCardSaved")) {
                SSLCSdkMainResponseModel.setNumberOfCardSaved(jsonObject.getInt("numberOfCardSaved"));
            }
            if (jsonObject.has("emi_status") && !jsonObject.isNull("emi_status")) {
                SSLCSdkMainResponseModel.setEmiStatus(jsonObject.getInt("emi_status"));
            }

            if (jsonObject.has("default_tab") && !jsonObject.isNull("default_tab")) {
                SSLCSdkMainResponseModel.setDefault_tab(jsonObject.getString("default_tab"));
            }

            if (jsonObject.has("offer_status") && !jsonObject.isNull("offer_status")) {
                SSLCSdkMainResponseModel.setOfferStatus(jsonObject.getInt("offer_status"));
            }
            if (jsonObject.has("loginTransSession") && !jsonObject.isNull("loginTransSession")) {
                SSLCSdkMainResponseModel.setLoginTransSession(jsonObject.getString("loginTransSession"));
            }
            if (jsonObject.has("storeLogo") && !jsonObject.isNull("storeLogo")) {
                SSLCSdkMainResponseModel.setStoreLogo(jsonObject.getString("storeLogo"));
            }
            if (jsonObject.has("store_name") && !jsonObject.isNull("store_name")) {
                SSLCSdkMainResponseModel.setStoreName(jsonObject.getString("store_name"));
            }
            if (jsonObject.has("timeoutinMin") && !jsonObject.isNull("timeoutinMin")) {
                SSLCSdkMainResponseModel.setTimeoutinMin(jsonObject.getString("timeoutinMin"));
            }
            if (jsonObject.has("termsAndConditionURL") && !jsonObject.isNull("termsAndConditionURL")) {
                SSLCSdkMainResponseModel.setTermsAndConditionURL(jsonObject.getString("termsAndConditionURL"));
            }
            if (jsonObject.has("FAQURL") && !jsonObject.isNull("FAQURL")) {
                SSLCSdkMainResponseModel.setFAQURL(jsonObject.getString("FAQURL"));
            }
            if (jsonObject.has("moreInfoURL") && !jsonObject.isNull("moreInfoURL")) {
                SSLCSdkMainResponseModel.setMoreInfoURL(jsonObject.getString("moreInfoURL"));
            }
            if (jsonObject.has("facebookPageURL") && !jsonObject.isNull("facebookPageURL")) {
                SSLCSdkMainResponseModel.setFacebookPageURL(jsonObject.getString("facebookPageURL"));
            }
            if (jsonObject.has("currency_type") && !jsonObject.isNull("currency_type")) {
                SSLCSdkMainResponseModel.setCurrencyType(jsonObject.getString("currency_type"));
            }
            if (jsonObject.has("currency_amount") && !jsonObject.isNull("currency_amount")) {
                SSLCSdkMainResponseModel.setCurrencyAmount(jsonObject.getString("currency_amount"));
            }
            if (jsonObject.has("currency_rate") && !jsonObject.isNull("currency_rate")) {
                SSLCSdkMainResponseModel.setCurrencyRate(jsonObject.getString("currency_rate"));
            }
            if (jsonObject.has("supportEmail") && !jsonObject.isNull("supportEmail")) {
                SSLCSdkMainResponseModel.setSupportEmail(jsonObject.getString("supportEmail"));
            }
            if (jsonObject.has("supportPhone") && !jsonObject.isNull("supportPhone")) {
                SSLCSdkMainResponseModel.setSupportPhone(jsonObject.getString("supportPhone"));
            }
            if (jsonObject.has("primaryColor") && !jsonObject.isNull("primaryColor")) {
                SSLCSdkMainResponseModel.setPrimaryColor(jsonObject.getString("primaryColor"));
            }
            if (jsonObject.has("activeColor") && !jsonObject.isNull("activeColor")) {
                SSLCSdkMainResponseModel.setActiveColor(jsonObject.getString("activeColor"));
            }
            if (jsonObject.has("design") && !jsonObject.isNull("design")) {
                JSONObject jsonObjectDesign = jsonObject.getJSONObject("design");
                Design design = new Design();
                design.setMainBkImg(jsonObjectDesign.getString("main_bk_img"));
                design.setMainBkColor(jsonObjectDesign.getString("main_bk_color"));
                design.setMainFontColor(jsonObjectDesign.getString("main_font_color"));
                design.setTitleBkColor(jsonObjectDesign.getString("title_bk_color"));
                design.setTitleFontColor(jsonObjectDesign.getString("title_font_color"));
                design.setBtnYesBkColor(jsonObjectDesign.getString("btn_yes_bk_color"));
                design.setBtnYesFontColor(jsonObjectDesign.getString("btn_yes_font_color"));
                design.setBtnNoBkColor(jsonObjectDesign.getString("btn_no_bk_color"));
                design.setBtnNoFontColor(jsonObjectDesign.getString("btn_no_font_color"));
                SSLCSdkMainResponseModel.setDesign(design);
            }

            if (jsonObject.has("gw") && !jsonObject.isNull("gw")) {
                JSONObject jsonObjectDesign = jsonObject.getJSONObject("gw");
                Gw gw = new Gw();
                gw.setVisa(jsonObjectDesign.getString("visa"));
                gw.setOthercards(jsonObjectDesign.getString("othercards"));
                gw.setAmex(jsonObjectDesign.getString("amex"));
                gw.setInternetbanking(jsonObjectDesign.getString("internetbanking"));
                gw.setMobilebanking(jsonObjectDesign.getString("mobilebanking"));
                gw.setMaster(jsonObjectDesign.getString("master"));
                SSLCSdkMainResponseModel.setGw(gw);
            }

            if (jsonObject.has("desc") && !jsonObject.isNull("desc")) {

                ArrayList<SSLCSdkMainResponseModel.Desc> descs = new ArrayList<>();
                JSONArray arrayDesc = jsonObject.getJSONArray("desc");
                for (int i = 0; i < arrayDesc.length(); i++) {
                    SSLCSdkMainResponseModel.Desc desc = new SSLCSdkMainResponseModel.Desc();
                    JSONObject jsonObj = arrayDesc.getJSONObject(i);
                    if (jsonObj.has("name") && !jsonObj.isNull("name")) {
                        desc.setName(jsonObj.getString("name"));
                    }
                    if (jsonObj.has("type") && !jsonObj.isNull("type")) {
                        desc.setType(jsonObj.getString("type"));
                    }
                    if (jsonObj.has("logo") && !jsonObj.isNull("logo")) {
                        desc.setLogo(jsonObj.getString("logo"));
                    }
                    if (jsonObj.has("transAmt") && !jsonObj.isNull("transAmt")) {
                        desc.setTransAmt(jsonObj.getString("transAmt"));
                    }
                    if (jsonObj.has("payableAmt") && !jsonObj.isNull("payableAmt")) {
                        desc.setPayableAmt(jsonObj.getString("payableAmt"));
                    }
                    if (jsonObj.has("charge") && !jsonObj.isNull("charge")) {
                        desc.setCharge(jsonObj.getString("charge"));
                    }
                    if (jsonObj.has("rFlag") && !jsonObj.isNull("rFlag")) {
                        desc.setRFlag(jsonObj.getString("rFlag"));
                    }
                    if (jsonObj.has("redirectGatewayURL") && !jsonObj.isNull("redirectGatewayURL")) {
                        desc.setRedirectGatewayURL(jsonObj.getString("redirectGatewayURL"));
                    }
                    if (jsonObj.has("autoselect") && !jsonObj.isNull("autoselect")) {
                        desc.setAutoselect(jsonObj.getString("autoselect"));
                    }

                    descs.add(desc);
                }

                SSLCSdkMainResponseModel.setDesc(descs);
            }
            return SSLCSdkMainResponseModel;
        } catch (Throwable t) {
            //Log.e("My App", "Could not parse malformed JSON: \"" + json + "\"");
        }

        return null;
    }

    public class Gw {

        @SerializedName("visa")
        @Expose
        private String visa;
        @SerializedName("master")
        @Expose
        private String master;
        @SerializedName("amex")
        @Expose
        private String amex;
        @SerializedName("othercards")
        @Expose
        private String othercards;
        @SerializedName("internetbanking")
        @Expose
        private String internetbanking;
        @SerializedName("mobilebanking")
        @Expose
        private String mobilebanking;

        public String getVisa() {
            return visa;
        }

        public void setVisa(String visa) {
            this.visa = visa;
        }

        public String getMaster() {
            return master;
        }

        public void setMaster(String master) {
            this.master = master;
        }

        public String getAmex() {
            return amex;
        }

        public void setAmex(String amex) {
            this.amex = amex;
        }

        public String getOthercards() {
            return othercards;
        }

        public void setOthercards(String othercards) {
            this.othercards = othercards;
        }

        public String getInternetbanking() {
            return internetbanking;
        }

        public void setInternetbanking(String internetbanking) {
            this.internetbanking = internetbanking;
        }

        public String getMobilebanking() {
            return mobilebanking;
        }

        public void setMobilebanking(String mobilebanking) {
            this.mobilebanking = mobilebanking;
        }

    }

    public class Design {

        @SerializedName("main_bk_img")
        @Expose
        private String mainBkImg;
        @SerializedName("main_bk_color")
        @Expose
        private String mainBkColor;
        @SerializedName("main_font_color")
        @Expose
        private String mainFontColor;
        @SerializedName("title_bk_color")
        @Expose
        private String titleBkColor;
        @SerializedName("title_font_color")
        @Expose
        private String titleFontColor;
        @SerializedName("btn_yes_bk_color")
        @Expose
        private String btnYesBkColor;
        @SerializedName("btn_yes_font_color")
        @Expose
        private String btnYesFontColor;
        @SerializedName("btn_no_bk_color")
        @Expose
        private String btnNoBkColor;
        @SerializedName("btn_no_font_color")
        @Expose
        private String btnNoFontColor;

        public String getMainBkImg() {
            return mainBkImg;
        }

        public void setMainBkImg(String mainBkImg) {
            this.mainBkImg = mainBkImg;
        }

        public String getMainBkColor() {
            return mainBkColor;
        }

        public void setMainBkColor(String mainBkColor) {
            this.mainBkColor = mainBkColor;
        }

        public String getMainFontColor() {
            return mainFontColor;
        }

        public void setMainFontColor(String mainFontColor) {
            this.mainFontColor = mainFontColor;
        }

        public String getTitleBkColor() {
            return titleBkColor;
        }

        public void setTitleBkColor(String titleBkColor) {
            this.titleBkColor = titleBkColor;
        }

        public String getTitleFontColor() {
            return titleFontColor;
        }

        public void setTitleFontColor(String titleFontColor) {
            this.titleFontColor = titleFontColor;
        }

        public String getBtnYesBkColor() {
            return btnYesBkColor;
        }

        public void setBtnYesBkColor(String btnYesBkColor) {
            this.btnYesBkColor = btnYesBkColor;
        }

        public String getBtnYesFontColor() {
            return btnYesFontColor;
        }

        public void setBtnYesFontColor(String btnYesFontColor) {
            this.btnYesFontColor = btnYesFontColor;
        }

        public String getBtnNoBkColor() {
            return btnNoBkColor;
        }

        public void setBtnNoBkColor(String btnNoBkColor) {
            this.btnNoBkColor = btnNoBkColor;
        }

        public String getBtnNoFontColor() {
            return btnNoFontColor;
        }

        public void setBtnNoFontColor(String btnNoFontColor) {
            this.btnNoFontColor = btnNoFontColor;
        }

    }

    public class Desc {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("logo")
        @Expose
        private String logo;
        @SerializedName("gw")
        @Expose
        private String gw;
        @SerializedName("transAmt")
        @Expose
        private String transAmt;
        @SerializedName("payableAmt")
        @Expose
        private String payableAmt;
        @SerializedName("charge")
        @Expose
        private String charge;
        @SerializedName("r_flag")
        @Expose
        private String rFlag;
        @SerializedName("autoselect")
        @Expose
        private String autoselect;
        @SerializedName("redirectGatewayURL")
        @Expose
        private String redirectGatewayURL;
        private boolean setStatus;

        public String getAutoselect() {
            return autoselect;
        }

        public void setAutoselect(String autoselect) {
            this.autoselect = autoselect;
        }

        public boolean isSetStatus() {
            return setStatus;
        }

        public void setSetStatus(boolean setStatus) {
            this.setStatus = setStatus;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getGw() {
            return gw;
        }

        public void setGw(String gw) {
            this.gw = gw;
        }

        public String getTransAmt() {
            return transAmt;
        }

        public void setTransAmt(String transAmt) {
            this.transAmt = transAmt;
        }

        public String getPayableAmt() {
            return payableAmt;
        }

        public void setPayableAmt(String payableAmt) {
            this.payableAmt = payableAmt;
        }

        public String getCharge() {
            return charge;
        }

        public void setCharge(String charge) {
            this.charge = charge;
        }

        public String getRFlag() {
            return rFlag;
        }

        public void setRFlag(String rFlag) {
            this.rFlag = rFlag;
        }

        public String getRedirectGatewayURL() {
            return redirectGatewayURL;
        }

        public void setRedirectGatewayURL(String redirectGatewayURL) {
            this.redirectGatewayURL = redirectGatewayURL;
        }

        public String toJSON() {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", getName());
                jsonObject.put("type", getType());
                jsonObject.put("logo", getLogo());
                jsonObject.put("rFlag", getRFlag());

                return jsonObject.toString();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "";
            }

        }
    }
}
