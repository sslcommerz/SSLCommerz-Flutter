package com.sslwireless.sslcommerzlibrary.model.initializer;

import java.io.Serializable;

public class SSLCommerzInitialization implements Serializable {
    private String store_id = "";
    private String store_passwd = "";
    private double total_amount;
    private String currency= "";
    private String tran_id= "";
    private String product_category= "";
    private String success_url= "";
    private String fail_url= "";
    private String cancel_url= "";
    private String ipn_url= "";
    private String multi_card_name= "";
    private String allowed_bin= "";
    private String sdkType= "";

    public SSLCommerzInitialization(String store_id, String store_passwd,
                                    double total_amount, String currency,
                                    String tran_id, String product_category, String sdkType) {
        this.store_id = store_id;
        this.store_passwd = store_passwd;
        this.total_amount = total_amount;
        this.currency = currency;
        this.tran_id = tran_id;
        this.product_category = product_category;
        this.sdkType = sdkType;
    }

    public String getSdkType() {
        return sdkType;
    }

    public void setSdkType(String sdkType) {
        this.sdkType = sdkType;
    }

    public String getSuccess_url() {
        return success_url;
    }

    public void setSuccess_url(String success_url) {
        this.success_url = success_url;
    }

    public String getFail_url() {
        return fail_url;
    }

    public void setFail_url(String fail_url) {
        this.fail_url = fail_url;
    }

    public String getCancel_url() {
        return cancel_url;
    }

    public void setCancel_url(String cancel_url) {
        this.cancel_url = cancel_url;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_passwd() {
        return store_passwd;
    }

    public void setStore_passwd(String store_passwd) {
        this.store_passwd = store_passwd;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTran_id() {
        return tran_id;
    }

    public void setTran_id(String tran_id) {
        this.tran_id = tran_id;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public String getIpn_url() {
        return ipn_url;
    }

    public void setIpn_url(String ipn_url) {
        this.ipn_url = ipn_url;
    }

    public String getMulti_card_name() {
        return multi_card_name;
    }

    public void setMulti_card_name(String multi_card_name) {
        this.multi_card_name = multi_card_name;
    }

    public String getAllowed_bin() {
        return allowed_bin;
    }

    public void setAllowed_bin(String allowed_bin) {
        this.allowed_bin = allowed_bin;
    }

    public SSLCommerzInitialization addIpnUrl(String ipn_url) {
        this.ipn_url = ipn_url;
        return this;
    }

    public SSLCommerzInitialization addMultiCardName(String multi_card_name) {
        this.multi_card_name = multi_card_name;
        return this;
    }

    public SSLCommerzInitialization addSAllowedBin(String allowed_bin) {
        this.allowed_bin = allowed_bin;
        return this;
    }
}
