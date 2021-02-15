package com.sslwireless.sslcommerzlibrary.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCShareInfo;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCEditTextUpdateListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class SSLCBaseActivity extends AppCompatActivity {

    public DisplayMetrics displayMetrics = new DisplayMetrics();
    private ProgressDialog dialogs;
    private Context context;
    protected SSLCEditTextUpdateListener SSLCEditTextUpdateListener;

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        context = this;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        viewRelatedTask();
    }

    public abstract void viewRelatedTask();

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        SSLCShareInfo.goNextPage(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SSLCShareInfo.goPreviousPage(this);
    }

    public void showToast(Context context, String message) {
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_toast_layout_sslc, null);

        TextView toastText = view.findViewById(R.id.toastText);
        toastText.setText(message);

        toast.setView(view);
        toast.show();
    }

    public void hideKeyboard() {
        // Check if no view has focus:
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context
                    .INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager
                    .HIDE_NOT_ALWAYS);
        }
    }

    public void progressDialog(String message) {
        if (dialogs == null) {
            dialogs = new ProgressDialog(context, R.style.alertDialogStyle);
            dialogs.setProgress(ProgressDialog.STYLE_SPINNER);
        }

        if (TextUtils.isEmpty(message)) {
            dialogs.setMessage("");
        } else {
            dialogs.setMessage(message);
        }
        if (dialogs != null && !dialogs.isShowing()) {
            dialogs.setCancelable(false);
            dialogs.show();
        }
    }

    public void hideProgressDialog() {
        if (!this.isFinishing() && dialogs != null && dialogs.isShowing()) {
            dialogs.dismiss();
        }
    }

    public void makeClipboard(String key, String value) {
        int sdk = Build.VERSION.SDK_INT;
        if (sdk < Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(value);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText(key, value);
            clipboard.setPrimaryClip(clip);
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public boolean isAppInstalled(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return false;
    }

    public void saveUserType(String type, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("type", type);
        editor.commit();
    }

    public String getUserType(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("type", "");
    }

    public void setEditClickListener(SSLCEditTextUpdateListener SSLCEditTextUpdateListener) {
        this.SSLCEditTextUpdateListener = SSLCEditTextUpdateListener;
    }
}
