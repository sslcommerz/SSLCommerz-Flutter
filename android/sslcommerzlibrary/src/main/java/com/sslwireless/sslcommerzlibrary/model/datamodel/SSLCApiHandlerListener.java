package com.sslwireless.sslcommerzlibrary.model.datamodel;

import org.json.JSONObject;

public interface SSLCApiHandlerListener {
    void success(JSONObject jsonObject);

    void fail(String message);
}
