package com.sslwireless.sslcommerzlibrary.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SSLCLogOutModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    //@SerializedName("data")
    //@Expose
    //private List<Object> data = null;

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

//    public List<Object> getData() {
//        return data;
//    }
//
//    public void setData(List<Object> data) {
//        this.data = data;
//    }

}
