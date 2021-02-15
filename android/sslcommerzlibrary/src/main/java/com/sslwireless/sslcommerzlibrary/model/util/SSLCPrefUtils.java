package com.sslwireless.sslcommerzlibrary.model.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SSLCPrefUtils {

    public static final String FIRST_OPEN = "FIRST_OPEN";
    public static final String PREFERENCE_DEFAULT_VALUE_LANGUAGE = "en";
    public static final String IS_LOGGED_IN = "IS_LOGIN";
    public static final String TOKEN = "token";
    public static final String USER_ID = "user_id";
    public static final String NAME = "name";
    public static final String KEY_PREF_LANGUAGE = "pref_key_language";

    public static void setLoggedIn(Context context, boolean value) {
        SharedPreferences.Editor mEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();

        mEditor.putBoolean(IS_LOGGED_IN, value);
        mEditor.commit();
    }

    public static boolean isLoggedIn(Context context) {
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return mSharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }
    public static void setLanguageChange(Context context, boolean value) {
        SharedPreferences.Editor mEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();

        mEditor.putBoolean("is_language_change", value);
        mEditor.commit();
    }

    public static boolean isLanguageChange(Context context) {
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return mSharedPreferences.getBoolean("is_language_change", false);
    }
    public static void setPreferenceToken(Context context, String value) {
        SharedPreferences.Editor mEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();

        mEditor.putString(TOKEN, value);
        mEditor.commit();
    }

    public static String getPreferenceToken(Context context) {
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return mSharedPreferences.getString(TOKEN, "");
    }
    public static void setPreferenceUserId(Context context, int value) {
        SharedPreferences.Editor mEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();

        mEditor.putInt(USER_ID, value);
        mEditor.commit();
    }

    public static int getPreferenceUserId(Context context) {
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return mSharedPreferences.getInt(USER_ID, 0);
    }

    public static void setPreferenceName(Context context, String value) {
        SharedPreferences.Editor mEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();

        mEditor.putString(NAME, value);
        mEditor.commit();
    }

    public static String getPreferenceName(Context context) {
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return mSharedPreferences.getString(NAME, "");
    }

    public static void setPreferenceLanguageValue(Context context, String language) {
        SharedPreferences.Editor mEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        mEditor.putString(KEY_PREF_LANGUAGE, language);
        mEditor.commit();
    }

    public static String getPreferenceLanguageValue(Context context) {
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return mSharedPreferences.getString(KEY_PREF_LANGUAGE, PREFERENCE_DEFAULT_VALUE_LANGUAGE);
    }


}
