package com.sslwireless.sslcommerzlibrary.viewmodel.listener;

import java.util.ArrayList;

public interface SSLCOnOfferSelectListener {
    void onOfferSelect(String id, String image, String title, String maxDiscount, ArrayList<String> offer_bins);
}
