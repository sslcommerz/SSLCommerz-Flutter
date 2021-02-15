
package com.sslwireless.sslcommerzlibrary.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SSLCOfferModel implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("data")
        @Expose
        private Data_ data;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Data_ getData() {
            return data;
        }

        public void setData(Data_ data) {
            this.data = data;
        }

    }

    public class Data_ {

        @SerializedName("actionStatus")
        @Expose
        private String actionStatus;
        @SerializedName("discountList")
        @Expose
        private List<DiscountList> discountList = null;
        @SerializedName("msgToDisplay")
        @Expose
        private String msgToDisplay;
        @SerializedName("systemMesg")
        @Expose
        private String systemMesg;

        public String getActionStatus() {
            return actionStatus;
        }

        public void setActionStatus(String actionStatus) {
            this.actionStatus = actionStatus;
        }

        public List<DiscountList> getDiscountList() {
            return discountList;
        }

        public void setDiscountList(List<DiscountList> discountList) {
            this.discountList = discountList;
        }

        public String getMsgToDisplay() {
            return msgToDisplay;
        }

        public void setMsgToDisplay(String msgToDisplay) {
            this.msgToDisplay = msgToDisplay;
        }

        public String getSystemMesg() {
            return systemMesg;
        }

        public void setSystemMesg(String systemMesg) {
            this.systemMesg = systemMesg;
        }

    }

    public class DiscountList {

        @SerializedName("priority")
        @Expose
        private Integer priority;
        @SerializedName("AvailDiscountId")
        @Expose
        private String availDiscountId;
        @SerializedName("discountTitle")
        @Expose
        private String discountTitle;
        @SerializedName("is_visa")
        @Expose
        private String isVisa;
        @SerializedName("is_master")
        @Expose
        private String isMaster;
        @SerializedName("is_amex")
        @Expose
        private String isAmex;
        @SerializedName("is_other_card")
        @Expose
        private String isOtherCard;
        @SerializedName("is_ib")
        @Expose
        private String isIb;
        @SerializedName("is_wallet")
        @Expose
        private String isWallet;
        @SerializedName("offerStartOnDate")
        @Expose
        private String offerStartOnDate;
        @SerializedName("offerEndOnDate")
        @Expose
        private String offerEndOnDate;
        @SerializedName("offerDesc")
        @Expose
        private String offerDesc;
        @SerializedName("gateway")
        @Expose
        private List<Object> gateway = null;
        @SerializedName("disIMGPath")
        @Expose
        private String disIMGPath;
        @SerializedName("MaxDisAmt")
        @Expose
        private String maxDisAmt;
        @SerializedName("disPercentage")
        @Expose
        private String disPercentage;
        @SerializedName("firstDigitAllowed")
        @Expose
        private List<Object> firstDigitAllowed = null;
        @SerializedName("allowedBIN")
        @Expose
        private List<String> allowedBIN = null;
        @SerializedName("isCouponEnable")
        @Expose
        private Integer isCouponEnable;
        @SerializedName("termNConditionURL")
        @Expose
        private String termNConditionURL;
        @SerializedName("redirectGWPath")
        @Expose
        private String redirectGWPath;
        @SerializedName("regularPrice")
        @Expose
        private String regularPrice;
        @SerializedName("payableAMT")
        @Expose
        private String payableAMT;

        public Integer getPriority() {
            return priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        public String getAvailDiscountId() {
            return availDiscountId;
        }

        public void setAvailDiscountId(String availDiscountId) {
            this.availDiscountId = availDiscountId;
        }

        public String getDiscountTitle() {
            return discountTitle;
        }

        public void setDiscountTitle(String discountTitle) {
            this.discountTitle = discountTitle;
        }

        public String getIsVisa() {
            return isVisa;
        }

        public void setIsVisa(String isVisa) {
            this.isVisa = isVisa;
        }

        public String getIsMaster() {
            return isMaster;
        }

        public void setIsMaster(String isMaster) {
            this.isMaster = isMaster;
        }

        public String getIsAmex() {
            return isAmex;
        }

        public void setIsAmex(String isAmex) {
            this.isAmex = isAmex;
        }

        public String getIsOtherCard() {
            return isOtherCard;
        }

        public void setIsOtherCard(String isOtherCard) {
            this.isOtherCard = isOtherCard;
        }

        public String getIsIb() {
            return isIb;
        }

        public void setIsIb(String isIb) {
            this.isIb = isIb;
        }

        public String getIsWallet() {
            return isWallet;
        }

        public void setIsWallet(String isWallet) {
            this.isWallet = isWallet;
        }

        public String getOfferStartOnDate() {
            return offerStartOnDate;
        }

        public void setOfferStartOnDate(String offerStartOnDate) {
            this.offerStartOnDate = offerStartOnDate;
        }

        public String getOfferEndOnDate() {
            return offerEndOnDate;
        }

        public void setOfferEndOnDate(String offerEndOnDate) {
            this.offerEndOnDate = offerEndOnDate;
        }

        public String getOfferDesc() {
            return offerDesc;
        }

        public void setOfferDesc(String offerDesc) {
            this.offerDesc = offerDesc;
        }

        public List<Object> getGateway() {
            return gateway;
        }

        public void setGateway(List<Object> gateway) {
            this.gateway = gateway;
        }

        public String getDisIMGPath() {
            return disIMGPath;
        }

        public void setDisIMGPath(String disIMGPath) {
            this.disIMGPath = disIMGPath;
        }

        public String getMaxDisAmt() {
            return maxDisAmt;
        }

        public void setMaxDisAmt(String maxDisAmt) {
            this.maxDisAmt = maxDisAmt;
        }

        public String getDisPercentage() {
            return disPercentage;
        }

        public void setDisPercentage(String disPercentage) {
            this.disPercentage = disPercentage;
        }

        public List<Object> getFirstDigitAllowed() {
            return firstDigitAllowed;
        }

        public void setFirstDigitAllowed(List<Object> firstDigitAllowed) {
            this.firstDigitAllowed = firstDigitAllowed;
        }

        public List<String> getAllowedBIN() {
            return allowedBIN;
        }

        public void setAllowedBIN(List<String> allowedBIN) {
            this.allowedBIN = allowedBIN;
        }

        public Integer getIsCouponEnable() {
            return isCouponEnable;
        }

        public void setIsCouponEnable(Integer isCouponEnable) {
            this.isCouponEnable = isCouponEnable;
        }

        public String getTermNConditionURL() {
            return termNConditionURL;
        }

        public void setTermNConditionURL(String termNConditionURL) {
            this.termNConditionURL = termNConditionURL;
        }

        public String getRedirectGWPath() {
            return redirectGWPath;
        }

        public void setRedirectGWPath(String redirectGWPath) {
            this.redirectGWPath = redirectGWPath;
        }

        public String getRegularPrice() {
            return regularPrice;
        }

        public void setRegularPrice(String regularPrice) {
            this.regularPrice = regularPrice;
        }

        public String getPayableAMT() {
            return payableAMT;
        }

        public void setPayableAMT(String payableAMT) {
            this.payableAMT = payableAMT;
        }

    }
}
