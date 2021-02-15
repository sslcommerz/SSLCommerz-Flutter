package com.sslwireless.sslcommerzlibrary.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SSLCTransactionInfo implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    @Expose
    private Data data;

    @SerializedName("amount")
    @Expose
    private String amount;

    @SerializedName("APIConnect")
    @Expose
    private String APIConnect;

    public Data getData() {
        return data;
    }

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

    public String getAPIConnect() {
        return APIConnect;
    }

    public void setAPIConnect(String APIConnect) {
        this.APIConnect = APIConnect;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public class Data {

        @Expose
        private String status;
        @Expose
        private String type;
        @Expose
        private String url;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
