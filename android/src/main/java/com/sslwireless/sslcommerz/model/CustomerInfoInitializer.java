package com.sslwireless.sslcommerz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerInfoInitializer {

    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("customerEmail")
    @Expose
    private String customerEmail;
    @SerializedName("customerAddress1")
    @Expose
    private String customerAddress1;
    @SerializedName("customerAddress2")
    @Expose
    private String customerAddress2;
    @SerializedName("customerCity")
    @Expose
    private String customerCity;
    @SerializedName("customerState")
    @Expose
    private Object customerState;
    @SerializedName("customerPostCode")
    @Expose
    private String customerPostCode;
    @SerializedName("customerCountry")
    @Expose
    private String customerCountry;
    @SerializedName("customerPhone")
    @Expose
    private String customerPhone;
    @SerializedName("customerFax")
    @Expose
    private Object customerFax;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress1() {
        return customerAddress1;
    }

    public void setCustomerAddress1(String customerAddress1) {
        this.customerAddress1 = customerAddress1;
    }

    public String getCustomerAddress2() {
        return customerAddress2;
    }

    public void setCustomerAddress2(String customerAddress2) {
        this.customerAddress2 = customerAddress2;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public Object getCustomerState() {
        return customerState;
    }

    public void setCustomerState(Object customerState) {
        this.customerState = customerState;
    }

    public String getCustomerPostCode() {
        return customerPostCode;
    }

    public void setCustomerPostCode(String customerPostCode) {
        this.customerPostCode = customerPostCode;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Object getCustomerFax() {
        return customerFax;
    }

    public void setCustomerFax(Object customerFax) {
        this.customerFax = customerFax;
    }

}
