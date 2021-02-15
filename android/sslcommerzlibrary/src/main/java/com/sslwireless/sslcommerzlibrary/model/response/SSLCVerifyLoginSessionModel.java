package com.sslwireless.sslcommerzlibrary.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SSLCVerifyLoginSessionModel {

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

    public class CardNo {

        @SerializedName("cardindex")
        @Expose
        private String cardindex;
        @SerializedName("cardNo")
        @Expose
        private String cardNo;
        @SerializedName("bankName")
        @Expose
        private String bankName;
        @SerializedName("friendlyName")
        @Expose
        private String friendlyName;
        @SerializedName("withoutOTP")
        @Expose
        private String withoutOTP;
        @SerializedName("MOTOEnable")
        @Expose
        private String MOTOEnable;
        @SerializedName("type")
        @Expose
        private String type;

        public String getCardindex() {
            return cardindex;
        }

        public void setCardindex(String cardindex) {
            this.cardindex = cardindex;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getFriendlyName() {
            return friendlyName;
        }

        public void setFriendlyName(String friendlyName) {
            this.friendlyName = friendlyName;
        }

        public String getWithoutOTP() {
            return withoutOTP;
        }

        public void setWithoutOTP(String withoutOTP) {
            this.withoutOTP = withoutOTP;
        }

        public String getMOTOEnable() {
            return MOTOEnable;
        }

        public void setMOTOEnable(String MOTOEnable) {
            this.MOTOEnable = MOTOEnable;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

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
        @SerializedName("msgToDisplay")
        @Expose
        private String msgToDisplay;
        @SerializedName("systemMesg")
        @Expose
        private String systemMesg;
        @SerializedName("custSession")
        @Expose
        private String custSession;
        @SerializedName("custFName")
        @Expose
        private String custFName;
        @SerializedName("custLName")
        @Expose
        private String custLName;
        @SerializedName("isMobileNoVerified")
        @Expose
        private String isMobileNoVerified;
        @SerializedName("mobileNo")
        @Expose
        private String mobileNo;
        @SerializedName("cardNos")
        @Expose
        private List<CardNo> cardNos = null;

        public String getActionStatus() {
            return actionStatus;
        }

        public void setActionStatus(String actionStatus) {
            this.actionStatus = actionStatus;
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

        public String getCustSession() {
            return custSession;
        }

        public void setCustSession(String custSession) {
            this.custSession = custSession;
        }

        public String getCustFName() {
            return custFName;
        }

        public void setCustFName(String custFName) {
            this.custFName = custFName;
        }

        public String getCustLName() {
            return custLName;
        }

        public void setCustLName(String custLName) {
            this.custLName = custLName;
        }

        public String getIsMobileNoVerified() {
            return isMobileNoVerified;
        }

        public void setIsMobileNoVerified(String isMobileNoVerified) {
            this.isMobileNoVerified = isMobileNoVerified;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public List<CardNo> getCardNos() {
            return cardNos;
        }

        public void setCardNos(List<CardNo> cardNos) {
            this.cardNos = cardNos;
        }

    }

}
