package com.sslwireless.sslcommerzlibrary.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SSLCTransactionInfoModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("sessionkey")
    @Expose
    private String sessionkey;
    @SerializedName("tran_date")
    @Expose
    private String tranDate;
    @SerializedName("tran_id")
    @Expose
    private String tranId;
    @SerializedName("val_id")
    @Expose
    private String valId;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("store_amount")
    @Expose
    private String storeAmount;
    @SerializedName("bank_tran_id")
    @Expose
    private String bankTranId;
    @SerializedName("card_type")
    @Expose
    private String cardType;
    @SerializedName("card_no")
    @Expose
    private String cardNo;
    @SerializedName("card_issuer")
    @Expose
    private String cardIssuer;
    @SerializedName("card_brand")
    @Expose
    private String cardBrand;
    @SerializedName("card_issuer_country")
    @Expose
    private String cardIssuerCountry;
    @SerializedName("card_issuer_country_code")
    @Expose
    private String cardIssuerCountryCode;
    @SerializedName("currency_type")
    @Expose
    private String currencyType;
    @SerializedName("currency_amount")
    @Expose
    private String currencyAmount;
    @SerializedName("currency_rate")
    @Expose
    private String currencyRate;
    @SerializedName("base_fair")
    @Expose
    private String baseFair;
    @SerializedName("value_a")
    @Expose
    private String valueA;
    @SerializedName("value_b")
    @Expose
    private String valueB;
    @SerializedName("value_c")
    @Expose
    private String valueC;
    @SerializedName("value_d")
    @Expose
    private String valueD;
    @SerializedName("risk_title")
    @Expose
    private String riskTitle;
    @SerializedName("risk_level")
    @Expose
    private String riskLevel;
    @SerializedName("APIConnect")
    @Expose
    private String aPIConnect;
    @SerializedName("validated_on")
    @Expose
    private String validatedOn;
    @SerializedName("gw_version")
    @Expose
    private String gwVersion;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSessionkey() {
        return sessionkey;
    }

    public void setSessionkey(String sessionkey) {
        this.sessionkey = sessionkey;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getValId() {
        return valId;
    }

    public void setValId(String valId) {
        this.valId = valId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStoreAmount() {
        return storeAmount;
    }

    public void setStoreAmount(String storeAmount) {
        this.storeAmount = storeAmount;
    }

    public String getBankTranId() {
        return bankTranId;
    }

    public void setBankTranId(String bankTranId) {
        this.bankTranId = bankTranId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardIssuer() {
        return cardIssuer;
    }

    public void setCardIssuer(String cardIssuer) {
        this.cardIssuer = cardIssuer;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
    }

    public String getCardIssuerCountry() {
        return cardIssuerCountry;
    }

    public void setCardIssuerCountry(String cardIssuerCountry) {
        this.cardIssuerCountry = cardIssuerCountry;
    }

    public String getCardIssuerCountryCode() {
        return cardIssuerCountryCode;
    }

    public void setCardIssuerCountryCode(String cardIssuerCountryCode) {
        this.cardIssuerCountryCode = cardIssuerCountryCode;
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

    public String getBaseFair() {
        return baseFair;
    }

    public void setBaseFair(String baseFair) {
        this.baseFair = baseFair;
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

    public String getRiskTitle() {
        return riskTitle;
    }

    public void setRiskTitle(String riskTitle) {
        this.riskTitle = riskTitle;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getAPIConnect() {
        return aPIConnect;
    }

    public void setAPIConnect(String aPIConnect) {
        this.aPIConnect = aPIConnect;
    }

    public String getValidatedOn() {
        return validatedOn;
    }

    public void setValidatedOn(String validatedOn) {
        this.validatedOn = validatedOn;
    }

    public String getGwVersion() {
        return gwVersion;
    }

    public void setGwVersion(String gwVersion) {
        this.gwVersion = gwVersion;
    }
}
