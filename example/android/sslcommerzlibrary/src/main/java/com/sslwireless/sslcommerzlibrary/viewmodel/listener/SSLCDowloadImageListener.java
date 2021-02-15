package com.sslwireless.sslcommerzlibrary.viewmodel.listener;

import android.graphics.Bitmap;

public interface SSLCDowloadImageListener {
    void downloadSuccess(Bitmap bitmap);

    void downloadFailed(String message);
}
