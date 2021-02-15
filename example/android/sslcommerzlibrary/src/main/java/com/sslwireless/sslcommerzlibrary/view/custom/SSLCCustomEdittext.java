package com.sslwireless.sslcommerzlibrary.view.custom;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCardNumberFormat;

public class SSLCCustomEdittext extends AppCompatEditText {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";
    private ColorStateList mPrefixTextColor;
    private float mPrefixTextSize;

    public SSLCCustomEdittext(Context context) {
        this(context, null);
    }

    public SSLCCustomEdittext(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
        init(context, attrs);
    }

    public SSLCCustomEdittext(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
        mPrefixTextColor = getTextColors();
        mPrefixTextSize = getTextSize();
    }

    public void setPrefix(String prefix, boolean isWhiteColor) {
        setCompoundDrawables(new TextDrawable(prefix), null, null, null);
        if (isWhiteColor) setPrefixTextColor(Color.parseColor("#969696"));
        else setPrefixTextColor(Color.parseColor("#494949"));
    }

    public void setSuffix(String prefix) {
        setCompoundDrawables(null, null, new TextDrawable(prefix), null);
    }

    public void setPrefixAndSuffix(String prefix, Drawable drawable) {
        int h = drawable.getIntrinsicHeight();
        int w = drawable.getIntrinsicWidth();
        drawable.setBounds(0, 0, w, h);

        setCompoundDrawables(new TextDrawable(prefix), null, drawable, null);
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

    public void setPrefixTextColor(int color) {
        mPrefixTextColor = ColorStateList.valueOf(color);
    }

    public void setPrefixTextColor(ColorStateList color) {
        mPrefixTextColor = color;
    }

    public void setPrefixTextSize(float size) {
        mPrefixTextSize = size;
    }

    public void isInputTypeCard(boolean isCard) {
        if (isCard) {
            this.addTextChangedListener(new SSLCCardNumberFormat(this));
        }
    }

    private class TextDrawable extends Drawable {

        private String mText = "";

        public TextDrawable(String text) {
            mText = text;
            Paint paint = new Paint(getPaint());
            paint.setTextSize(mPrefixTextSize);
            setBounds(0, 0, (int) paint.measureText(mText) + 2, (int) mPrefixTextSize);
        }

        @Override
        public void draw(Canvas canvas) {
            Paint paint = new Paint(getPaint());
            paint.setColor(mPrefixTextColor.getColorForState(getDrawableState(), 0));
            paint.setTextSize(mPrefixTextSize);
            int lineBaseline = getLineBounds(0, null);
            canvas.drawText(mText, 0, canvas.getClipBounds().top + lineBaseline, paint);
        }

        @Override
        public void setAlpha(int alpha) {/* Not supported */}

        @Override
        public void setColorFilter(ColorFilter colorFilter) {/* Not supported */}

        @Override
        public int getOpacity() {
            return PixelFormat.UNKNOWN;
        }

    }
}