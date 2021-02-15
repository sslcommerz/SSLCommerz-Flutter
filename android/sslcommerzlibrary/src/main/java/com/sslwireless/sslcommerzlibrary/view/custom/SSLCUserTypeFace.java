package com.sslwireless.sslcommerzlibrary.view.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Hashtable;

/**
 * Created by ZAHID
 */

public class SSLCUserTypeFace {

    public static final String BOLD;
    public static final String LIGHT;
    public static final String NORMAL;
    public static final String ITALIC;
    public static final String MEDIUM;

    static {
        NORMAL ="fonts/customfont/Roboto-Regular.ttf";
        LIGHT="fonts/customfont/Roboto-Light.ttf";
        BOLD="fonts/customfont/Roboto-Bold.ttf";
        MEDIUM ="fonts/customfont/Roboto-Medium.ttf";
        ITALIC ="fonts/customfont/Roboto-Italic.ttf";
    }

    private static final Hashtable<String, Typeface> cache = new Hashtable<String, Typeface>();

    private static Typeface getTypeFace(Context context, String assetPath) {
        synchronized (cache) {
            if (!cache.containsKey(assetPath)) {
                try {
                    Typeface typeFace = Typeface.createFromAsset(
                            context.getAssets(), assetPath);
                    cache.put(assetPath, typeFace);
                } catch (Exception e) {
                    //Log.e("TypeFaces", "Typeface not loaded.");
                    return null;
                }
            }
            return cache.get(assetPath);
        }
    }

    public static void SetLight(TextView obj){
        obj.setTypeface(getTypeFace(obj.getContext(),LIGHT), Typeface.NORMAL);
    }

    public static void SetBold(TextView obj){
        obj.setTypeface(getTypeFace(obj.getContext(),BOLD), Typeface.BOLD);
    }

    public static void SetItalic(TextView obj){
        obj.setTypeface(getTypeFace(obj.getContext(), ITALIC), Typeface.ITALIC);
    }
    public static void SetNormal(TextView obj){
        obj.setTypeface(getTypeFace(obj.getContext(), NORMAL), Typeface.NORMAL);
    }

    public static void SetMedium(TextView obj){
        obj.setTypeface(getTypeFace(obj.getContext(), MEDIUM), Typeface.BOLD);
    }

    public static Typeface getNormal(View obj){
        return getTypeFace(obj.getContext(), NORMAL);
    }

}