
package com.sslwireless.sslcommerzlibrary.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SSLCResendOtpModel {

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

}

}

}
