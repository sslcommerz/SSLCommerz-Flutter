package com.sslwireless.sslcommerz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Initializer {

    @SerializedName("store_id")
    @Expose
    private String storeId;
    @SerializedName("store_passwd")
    @Expose
    private String storePasswd;
    @SerializedName("total_amount")
    @Expose
    private Double totalAmount;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("tran_id")
    @Expose
    private String tranId;
    @SerializedName("product_category")
    @Expose
    private String productCategory;
    @SerializedName("success_url")
    @Expose
    private String successUrl;
    @SerializedName("fail_url")
    @Expose
    private String failUrl;
    @SerializedName("cancel_url")
    @Expose
    private String cancelUrl;
    @SerializedName("ipn_url")
    @Expose
    private String ipnUrl;
    @SerializedName("multi_card_name")
    @Expose
    private String multiCardName;
    @SerializedName("allowed_bin")
    @Expose
    private String allowedBin;
    @SerializedName("sdkType")
    @Expose
    private String sdkType;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStorePasswd() {
        return storePasswd;
    }

    public void setStorePasswd(String storePasswd) {
        this.storePasswd = storePasswd;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getFailUrl() {
        return failUrl;
    }

    public void setFailUrl(String failUrl) {
        this.failUrl = failUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public String getIpnUrl() {
        return ipnUrl;
    }

    public void setIpnUrl(String ipnUrl) {
        this.ipnUrl = ipnUrl;
    }

    public String getMultiCardName() {
        return multiCardName;
    }

    public void setMultiCardName(String multiCardName) {
        this.multiCardName = multiCardName;
    }

    public String getAllowedBin() {
        return allowedBin;
    }

    public void setAllowedBin(String allowedBin) {
        this.allowedBin = allowedBin;
    }

    public String getSdkType() {
        return sdkType;
    }

    public void setSdkType(String sdkType) {
        this.sdkType = sdkType;
    }

}
