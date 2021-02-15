package com.sslwireless.sslcommerzlibrary.model.util;

import android.Manifest;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sslwireless.sslcommerzlibrary.R;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCEMIModel;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCOfferModel;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCSdkMainResponseModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SSLCShareInfo {

    public static final String main_response = "main_response";
    private static final SSLCShareInfo ourInstance = new SSLCShareInfo();
    private static final String KURDISTAN = "ku";
    private static final String ARABIC = "ar";
    private static final String ENGLISH = "en";
    public static Map<String,Boolean> motoMap =  new HashMap<String,Boolean>();

//    static {
//        System.loadLibrary("sdklibc");
//    }

    private ProgressDialog dialogs;

    private SSLCShareInfo() {
    }

    public static SSLCShareInfo getInstance() {
        return ourInstance;
    }

    public static void goNextPage(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.activity_in_sslc, R.anim.activity_out_sslc);
    }

    public static void goPreviousPage(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.activity_in_back_sslc, R.anim.activity_out_back_sslc);
    }

    public Configuration changeLanguage(Context context) {
        String lang = SSLCPrefUtils.getPreferenceLanguageValue(context);
        if (lang.contains("bn")) {
            SSLCPrefUtils.setPreferenceLanguageValue(context, "en");
        } else {
            SSLCPrefUtils.setPreferenceLanguageValue(context, "bn");
        }
        return updateLanguage(context);
        //MyApplication.getInstance().changeLanguage(context);
        //((AppCompatActivity) context).recreate();
    }

    public Configuration updateLanguage(Context context) {
        String language = SSLCPrefUtils.getPreferenceLanguageValue(context);
        Locale myLocale = new Locale(language);
        Configuration conf = context.getResources().getConfiguration();
        conf.setLocale(myLocale);
        context.getResources().updateConfiguration(conf, context.getResources().getDisplayMetrics());
        return conf;
    }

    public native String getBaseUrl();

    public native String getSandboxBaseUrl();

    public native String getMainLiveUrl();

    public native String getMerchantValidatorSandbox();

    public native String getMerchantValidatorLive();

    public native String getMainSandboxUrl();

    public int getPxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public int spToPx(float sp) {
        return (int) (sp * Resources.getSystem().getDisplayMetrics().scaledDensity);
    }

    public String getRequestId() {
        Random random = new Random(10000);
        return String.valueOf(System.currentTimeMillis() + random.nextInt());
    }

    public void saveRegId(Context context, String token) {
        //Log.e(TAG, "saveRegId: " + context.getPackageName());
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("reg_id", token);
        editor.commit();
    }

    public void saveEncKey(Context context, String userID) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("enc_key", userID);
        editor.commit();
    }

    public void saveCustSession(Context context, String userID) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("cust_session", userID);
        editor.commit();
    }

    public void saveMobileNo(Context context, String userID) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("mobile_no", userID);
        editor.commit();
    }

    public void saveCustomerName(Context context, String name) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("customer_name", name);
        editor.commit();
    }

    public String getCustomerName(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("customer_name", "");
    }

    public void saveLoggedIn(Context context, boolean isLoggedIn) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("is_logged_in", isLoggedIn);
        editor.commit();
    }

    public boolean isLoggedIn(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean("is_logged_in", false);
    }


    public void saveLanguageChange(Context context, boolean isLoggedIn) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("is_language_change", isLoggedIn);
        editor.commit();
    }

    public boolean isLanguageChange(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean("is_language_change", false);
    }

    public String getRegKey(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("reg_id", "");
    }

    public void saveType(Context context, String sdkType) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("sdkType", sdkType);
        editor.commit();
    }

    public String getType(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("sdkType", "");
    }

    public void lastHitSDKType(Context context, String sdkType) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("lastHit", sdkType);
        editor.commit();
    }

    public String getlastHitSDKType(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("lastHit", "");
    }

    public String getEncKey(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("enc_key", "");
    }

    public String getCustSession(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("cust_session", "");
    }

    public String getMobileNo(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("mobile_no", "");
    }

    public void progressDialog(Context context, String message) {
        try {
            if (dialogs == null) {
                dialogs = new ProgressDialog(context, R.style.alertDialogStyle);
                dialogs.setProgress(ProgressDialog.STYLE_SPINNER);
            } else {
                dialogs.dismiss();
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
        } catch (Exception e) {
            //Log.e(TAG, "progressDialog: " + e.getMessage());
        }
    }

    public void hideProgressDialog() {
        if (dialogs != null && dialogs.isShowing()) {
            dialogs.dismiss();
        }
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

    public String getUserType(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("type", "");
    }

    public String getIsLogin(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("is_login", "null");
    }

    public void saveLoginStatus(String loginStatus, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("is_login", loginStatus);
        editor.commit();
    }

    public void saveFCMToken(Context applicationContext, String token) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("fcmToken", token);
        editor.commit();
    }

    public String getFCMToken(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("fcmToken", "null");
    }

    /*public String getIMEI(Context context) {
        try {
            TelephonyManager mngr = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
            String imei;
            if (android.os.Build.VERSION.SDK_INT >= 26) {
                if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

                    return null;
                }
                imei = mngr.getImei();
            } else {
                imei = mngr.getDeviceId();
            }
            return imei;
        } catch (Exception e) {
            return null;
        }
    }*/

    public String getDeviceName(Context context) {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }

    public boolean searchMotoEnabled(String searchValue){
        Iterator myVeryOwnIterator = motoMap.keySet().iterator();
        while(myVeryOwnIterator.hasNext()) {
            String key=(String)myVeryOwnIterator.next();
            //String value=(String)motoMap.get(key);
            if(searchValue.equalsIgnoreCase(key)){
                return true;
            }
            break;
        }

        return false;
    }

    public int convertToInt(String value){
        int myNum = 0;
        try {
            myNum = Integer.parseInt(value);
        } catch(NumberFormatException nfe) {
            myNum = 0;
        }

        return myNum;
    }

    public boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = false;

        String expression = "^(?:\\+?88)?01[13-9]\\d{8}$";
        CharSequence inputStr = phoneNumber;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public void startCountDown(final Context context, final TextView tvResendOTP
            , final TextView tvChangeNumber, final TextView tvChangeCard, final int color) {
        int OTP_RESEND_TIMER = 20;
        tvResendOTP.setEnabled(false);
        CountDownTimer timer = new CountDownTimer(OTP_RESEND_TIMER * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                String hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                tvResendOTP.setText(hms);
            }

            public void onFinish() {
                tvResendOTP.setEnabled(true);
                tvChangeNumber.setEnabled(true);
                tvChangeCard.setEnabled(true);

                tvChangeNumber.setTextColor(color);
                tvChangeCard.setTextColor(color);

                tvResendOTP.setText(context.getResources().getString(R.string.resend_otp));
                //alertBinding.resendBtn.setBackground(context.getResources().getDrawable(R.drawable.button_style));
                //alertBinding.resendBtn.setTextColor(context.getResources().getColor(R.color.colorWhite)); //On finish change timer text
            }
        }.start();
    }

    public void setToGray(ImageView v) {
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);  //0 means grayscale
        ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
        v.setColorFilter(cf);
        v.setImageAlpha(128);   // 128 = 0.5
    }

    public void disableGray(ImageView v) {
        v.setColorFilter(null);
        v.setImageAlpha(255);
    }

    public boolean validateCardExpiryDate(String expiryDate) {
        if (expiryDate != null) {
            if (expiryDate.matches("(?:0[1-9]|1[0-2])/[0-9]{2}")) {
                int year = Calendar.getInstance().get(Calendar.YEAR);
                int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
                String[] expire = expiryDate.split("/");
                if (expire.length > 1) {
                    if (Integer.parseInt(expire[1]) == year % 100 && Integer.parseInt(expire[0]) >= month) {
                        return true;
                    } else if (Integer.parseInt(expire[1]) > year % 100) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;
            }
        }
        // return expiryDate.matches("(?:0[1-9]|1[0-2])/[0-9]{2}");
        return false;
    }

    public String getDigitFromString(String str) {
        // str..matches(".*\\d.*")  // is string?
        String value = str.replaceAll("\\D+", "");
        if (value == null) {
            value = "";
        }

        return value;
    }

    public boolean isValid(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    public List<String> getTenures(Context context, SSLCEMIModel SSLCEMIModel, String ccNumber) {
        List<String> tenures = new ArrayList<String>();
        tenures.add(context.getResources().getString(R.string.avail_emi));
        if (SSLCEMIModel != null) {
            for (SSLCEMIModel.Emi emi : SSLCEMIModel.getData().getData().getEmi()) {
                for (String str : emi.getBinList()) {
                    if (str.contains(ccNumber)) {
                        for (SSLCEMIModel.EmiBankTenureDesc emiBankTenureDesc : emi.getEmiBankTenureDesc()) {
                            tenures.add(emiBankTenureDesc.getDesc().replace("&nbsp;", ""));
                        }
                        break;
                    }
                }
            }
        }

        return tenures;
    }

    public boolean isURLString(String url) {
        String URL_REGEX = "^((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?$";

        Pattern p = Pattern.compile(URL_REGEX);
        Matcher m = p.matcher(url);//replace with string to compare
        if (m.find()) {
            return true;
        }

        return false;
    }

    public ArrayList<String> convertListToArrayList(List<String> arrayList) {

        ArrayList<String> datas = new ArrayList<String>();
        for (String str : arrayList) {
            datas.add(str);
        }

        return datas;
    }

    public boolean isNetworkAvailable(Context ctx) {
        int networkStatePermission = ctx.checkCallingOrSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE);

        if (networkStatePermission == PackageManager.PERMISSION_GRANTED) {

            ConnectivityManager mConnectivity = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

            // Skip if no connection, or background data disabled
            NetworkInfo info = mConnectivity.getActiveNetworkInfo();
            if (info == null) {
                return false;
            }
            // Only update if WiFi
            int netType = info.getType();
            // int netSubtype = info.getSubtype();
            if ((netType == ConnectivityManager.TYPE_WIFI) || (netType == ConnectivityManager.TYPE_MOBILE)) {
                return info.isConnected();
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public void showSoftKeyboard(final EditText view) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (view.requestFocus()) {
                    InputMethodManager imm = (InputMethodManager)
                            view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
                    //imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                    view.clearFocus();
                    view.requestFocus();
                    view.setCursorVisible(true);
                }
            }
        }, 100);
    }

    public void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
       // imm.toggleSoftInputFromWindow(view.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
    }

    public String offerAvailibilityCheck(SSLCOfferModel SSLCOfferModel, String ccNum) {
        String offerInfo = "";
        if (SSLCOfferModel != null) {
            for (SSLCOfferModel.DiscountList discount : SSLCOfferModel.getData().getData().getDiscountList()) {
                for (String str : discount.getAllowedBIN()) {
                    if (str.equalsIgnoreCase(ccNum)) {
                        offerInfo = discount.getAvailDiscountId();
                        offerInfo += "--" + discount.getDiscountTitle();
                    }
                }
            }
        }

        return offerInfo;
    }

    public ValueAnimator slideAnimator(int start, int end, final View view) {

        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

//    private void expandView( Context context, final View view ) {
//
//        view.setVisibility(View.VISIBLE);
//
//        ViewGroup.LayoutParams parms = (ViewGroup.LayoutParams) view.getLayoutParams();
//        final int width = context.getWidth() - parms..leftMargin - parms.rightMargin;
//
//        view.measure( View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.AT_MOST),
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//
//        final int targetHeight = view.getMeasuredHeight();
//
//        view.getLayoutParams().height = 0;
//
//        Animation anim = new Animation() {
//            @Override
//            protected void applyTransformation( float interpolatedTime, Transformation trans ) {
//                view.getLayoutParams().height =  (int) (targetHeight * interpolatedTime);
//                view.requestLayout();
//            }
//
//            @Override
//            public boolean willChangeBounds() {
//                return true;
//            }
//        };
//
//        anim.setDuration( ANIMATION_DURATION );
//        view.startAnimation( anim );
//    }

    public void expand(final View view) {
        int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) view.getParent()).getWidth(), View.MeasureSpec.EXACTLY);
        int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
        final int targetHeight = view.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        view.getLayoutParams().height = 1;
        view.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                view.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                view.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetHeight / view.getContext().getResources().getDisplayMetrics().density));
        view.startAnimation(a);
    }

    public void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public StateListDrawable setBackgroundColor(int colorPressed, int colorNormal, int colorDisable) {
        StateListDrawable sd = new StateListDrawable();
        int[] state_pressed = new int[]{android.R.attr.state_pressed};
        int[] state_normal = new int[]{android.R.attr.state_enabled};
        int[] state_disable = new int[]{-android.R.attr.state_enabled};

        GradientDrawable pressed = new GradientDrawable();
        pressed.setColor(colorPressed);
        pressed.setCornerRadius(5);

        GradientDrawable enable = new GradientDrawable();
        enable.setColor(colorNormal);
        enable.setCornerRadius(5);

        GradientDrawable disable = new GradientDrawable();
        disable.setColor(colorDisable);
        disable.setCornerRadius(5);

        sd.addState(state_pressed, pressed);
        sd.addState(state_normal, enable);
        sd.addState(state_disable, disable);

        return sd;
    }


    public void setEditTextMaxLength(EditText editText, int length) {
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(length);
        editText.setFilters(filterArray);
    }

    public void setCursorPointerColor(EditText editText, @ColorInt int color) {
        //Log.d(TAG, "setCursorPointerColor: ........ color "+color);
        try {
            //get the pointer resource id
            Field field = EditText.class.getDeclaredField("mTextSelectHandleRes");
            field.setAccessible(true);
            int drawableResId = field.getInt(editText);

            //get the editor
            field = EditText.class.getDeclaredField("mEditor");
            field.setAccessible(true);
            Object editor = field.get(editText);

            //tint drawable
            Drawable drawable = ContextCompat.getDrawable(editText.getContext(), drawableResId);
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);

            //set the drawable
            field = editor.getClass().getDeclaredField("mSelectHandleCenter");
            field.setAccessible(true);
            field.set(editor, drawable);
        } catch (Exception e) {
            //Log.e("Exception", e.getLocalizedMessage());
        }
    }

    public static void setCursorColor(EditText view, @ColorInt int color) {
        try {
            // Get the cursor resource id
            Field field = TextView.class.getDeclaredField("mCursorDrawableRes");
            field.setAccessible(true);
            int drawableResId = field.getInt(view);

            // Get the editor
            field = TextView.class.getDeclaredField("mEditor");
            field.setAccessible(true);
            Object editor = field.get(view);

            // Get the drawable and set a color filter
            Drawable drawable = ContextCompat.getDrawable(view.getContext(), drawableResId);
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            Drawable[] drawables = {drawable, drawable};

            // Set the drawables
            field = editor.getClass().getDeclaredField("mCursorDrawable");
            field.setAccessible(true);
            field.set(editor, drawables);
        } catch (Exception ignored) {
        }
    }
    public void setCursorDrawableColor(EditText editText, int color) {
        try {
            Field fCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            fCursorDrawableRes.setAccessible(true);
            int mCursorDrawableRes = fCursorDrawableRes.getInt(editText);
            Field fEditor = TextView.class.getDeclaredField("mEditor");
            fEditor.setAccessible(true);
            Object editor = fEditor.get(editText);
            Class<?> clazz = editor.getClass();
            Field fCursorDrawable = clazz.getDeclaredField("mCursorDrawable");
            fCursorDrawable.setAccessible(true);
            Drawable[] drawables = new Drawable[2];
            drawables[0] = editText.getContext().getResources().getDrawable(mCursorDrawableRes);
            drawables[1] = editText.getContext().getResources().getDrawable(mCursorDrawableRes);
            drawables[0].setColorFilter(color, PorterDuff.Mode.SRC_IN);
            drawables[1].setColorFilter(color, PorterDuff.Mode.SRC_IN);
            fCursorDrawable.set(editor, drawables);
        } catch (Throwable ignored) {
        }
    }
    public void setCursorDrawableColor1(EditText editText, int color) {

        try {
            Field cursorDrawableResField = TextView.class.getDeclaredField("mCursorDrawableRes");
            cursorDrawableResField.setAccessible(true);
            int cursorDrawableRes = cursorDrawableResField.getInt(editText);
            Field editorField = TextView.class.getDeclaredField("mEditor");
            editorField.setAccessible(true);
            Object editor = editorField.get(editText);
            Class<?> clazz = editor.getClass();
            Resources res = editText.getContext().getResources();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                Field drawableForCursorField = clazz.getDeclaredField("mDrawableForCursor");
                drawableForCursorField.setAccessible(true);
                Drawable drawable = res.getDrawable(cursorDrawableRes);
                drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                drawableForCursorField.set(editor, drawable);
            } else {
                Field cursorDrawableField = clazz.getDeclaredField("mCursorDrawable");
                cursorDrawableField.setAccessible(true);
                Drawable[] drawables = new Drawable[2];
                drawables[0] = res.getDrawable(cursorDrawableRes);
                drawables[1] = res.getDrawable(cursorDrawableRes);
                drawables[0].setColorFilter(color, PorterDuff.Mode.SRC_IN);
                drawables[1].setColorFilter(color, PorterDuff.Mode.SRC_IN);
                cursorDrawableField.set(editor, drawables);
            }
        } catch (Throwable t) {
            //Log.w(TAG, t);
        }
    }

    public ArrayList getOtherCards(SSLCSdkMainResponseModel SSLCSdkMainResponseModel){
        ArrayList<SSLCSdkMainResponseModel.Desc> otherCards = new ArrayList<>();
        for (SSLCSdkMainResponseModel.Desc desc : SSLCSdkMainResponseModel.getDesc()) {
            if (!desc.getType().contains("mobilebanking") && !desc.getType().contains("internetbanking") &&
                    !desc.getType().contains(SSLCEnums.CardType.Visa.name().toLowerCase()) &&
                    !desc.getType().contains(SSLCEnums.CardType.Master.name().toLowerCase())&&
                    !desc.getType().contains(SSLCEnums.CardType.Amex.name().toLowerCase())) {
                otherCards.add(desc);
            }
        }

        return otherCards;
    }
}
