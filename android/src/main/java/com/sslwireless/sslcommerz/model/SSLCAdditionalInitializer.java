package com.sslwireless.sslcommerz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SSLCAdditionalInitializer {
    @SerializedName("valueA")
    @Expose
    String valueA;
    @SerializedName("valueB")
    @Expose
    String valueB;
    @SerializedName("valueC")
    @Expose
    String valueC;
    @SerializedName("valueD")
    @Expose
    String valueD;

    SSLCAdditionalInitializer(
            String valueA, String valueB, String valueC, String valueD
    ) {
        this.valueA = valueA;
        this.valueB = valueB;
        this.valueC = valueC;
        this.valueD = valueD;
    }

    public String getValueA() {
        return valueA;
    }

    public void setValueA(String valueA) {
        this.valueA = valueA;
    }

    public String getValueB() {
        return valueB;
    }

    public void setValueB(String valueB) {
        this.valueB = valueB;
    }

    public String getValueC() {
        return valueC;
    }

    public void setValueC(String valueC) {
        this.valueC = valueC;
    }

    public String getValueD() {
        return valueD;
    }

    public void setValueD(String valueD) {
        this.valueD = valueD;
    }
}
