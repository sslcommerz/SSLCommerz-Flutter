package com.sslwireless.sslcommerzlibrary.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.sslwireless.sslcommerzlibrary.R;

public class SSLCCustomTextView extends AppCompatTextView {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public SSLCCustomTextView(Context context) {
        super(context, null);
    }

    public SSLCCustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public SSLCCustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (!this.isInEditMode()) { // used for preview while designing.
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.custom);
            int type = a.getInteger(R.styleable.custom_textStyle, 0);
            if (type == 0) {
                SSLCUserTypeFace.SetNormal(this); //Set Default Font if font is not defined in xml
                return;
            }
            setStyle(type);
        } else {
            setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
        }
    }


    private void setStyle(int type) {
        switch (type) {
            case 0:
                SSLCUserTypeFace.SetNormal(this);
                break;
            case 1:
                SSLCUserTypeFace.SetBold(this);
                break;
            case 2:
                SSLCUserTypeFace.SetItalic(this);
                break;
            case 3:
                SSLCUserTypeFace.SetLight(this);
                break;
            case 4:
                SSLCUserTypeFace.SetMedium(this);
                break;
            default:
                SSLCUserTypeFace.SetNormal(this);
        }

    }
}