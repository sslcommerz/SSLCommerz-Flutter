package com.sslwireless.sslcommerzlibrary.model.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by zahidul.islam on 8/9/2017.
 */

public class SSLCCardNumberFormat implements TextWatcher {

    private EditText view;
    private String previousValue;

    public SSLCCardNumberFormat(EditText view){
        this.view = view;
        previousValue = new String(" ");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String text = s.toString();
        if(previousValue.length() < text.length()) {
            if(previousValue.length()>2){
                if(previousValue.substring(0,2).equals("34") || previousValue.substring(0,2).equals("37")){
                    if (text.length() == 5) {
                        view.setText(addSpace(text, 4));
                    } else if (text.length() == 12) {
                        view.setText(addSpace(text, 11));
                    }
                } else {
                    if (text.length() == 5) {
                        view.setText(addSpace(text, 4));
                    } else if (text.length() == 10) {
                        view.setText(addSpace(text, 9));
                    } else if (text.length() == 15) {
                        view.setText(addSpace(text, 14));
                    }
                }
            }
        }
        view.setSelection(view.getText().toString().length());
        previousValue = view.getText().toString();
    }

    private String addSpace(String value, int position){
        String returnData = new String();
        char[] characters = value.toCharArray();
        for (int x=0;x<characters.length;x++) {
            if(position == x) {
                returnData = returnData + " ";
            }
            returnData = returnData + characters[x];
        }
        return returnData;
    }

    private String removeSpace(String value, int limitPosition){
        String returnData = new String();
        char[] characters = value.toCharArray();
        for (int x=0;x<limitPosition;x++) {
            returnData = returnData + characters[x];
        }
        return returnData;
    }
}