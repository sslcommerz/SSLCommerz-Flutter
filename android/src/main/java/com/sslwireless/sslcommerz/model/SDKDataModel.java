package com.sslwireless.sslcommerz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SDKDataModel {

    @SerializedName("initializer")
    @Expose
    private Initializer initializer;
    @SerializedName("customerInfoInitializer")
    @Expose
    private CustomerInfoInitializer customerInfoInitializer;
    @SerializedName("sslcemiTransactionInitializer")
    @Expose
    private SslcemiTransactionInitializer sslcemiTransactionInitializer;
    @SerializedName("sslcShipmentInfoInitializer")
    @Expose
    private SslcShipmentInfoInitializer sslcShipmentInfoInitializer;
    @SerializedName("sslcProductInitializer")
    @Expose
    private SslcProductInitializer sslcProductInitializer;

    @SerializedName("sslcAdditionalInitializer")
    @Expose
    private SSLCAdditionalInitializer sslcAdditionalInitializer;

    public Initializer getInitializer() {
        return initializer;
    }

    public void setInitializer(Initializer initializer) {
        this.initializer = initializer;
    }

    public SSLCAdditionalInitializer getSslcAdditionalInitializer() {
        return sslcAdditionalInitializer;
    }

    public void setSslcAdditionalInitializer(SSLCAdditionalInitializer additionalInitializer) {
        this.sslcAdditionalInitializer = additionalInitializer;
    }

    public CustomerInfoInitializer getCustomerInfoInitializer() {
        return customerInfoInitializer;
    }

    public void setCustomerInfoInitializer(CustomerInfoInitializer customerInfoInitializer) {
        this.customerInfoInitializer = customerInfoInitializer;
    }

    public SslcemiTransactionInitializer getSslcemiTransactionInitializer() {
        return sslcemiTransactionInitializer;
    }

    public void setSslcemiTransactionInitializer(SslcemiTransactionInitializer sslcemiTransactionInitializer) {
        this.sslcemiTransactionInitializer = sslcemiTransactionInitializer;
    }

    public SslcShipmentInfoInitializer getSslcShipmentInfoInitializer() {
        return sslcShipmentInfoInitializer;
    }

    public void setSslcShipmentInfoInitializer(SslcShipmentInfoInitializer sslcShipmentInfoInitializer) {
        this.sslcShipmentInfoInitializer = sslcShipmentInfoInitializer;
    }

    public SslcProductInitializer getSslcProductInitializer() {
        return sslcProductInitializer;
    }

    public void setSslcProductInitializer(SslcProductInitializer sslcProductInitializer) {
        this.sslcProductInitializer = sslcProductInitializer;
    }

}