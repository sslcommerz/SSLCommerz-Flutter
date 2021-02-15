package com.sslwireless.sslcommerzlibrary.model.util;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.sslwireless.sslcommerzlibrary.R;

/**
 * Created by Screenshot on 8/17/16.
 */
public class SSLCProgressBarHandler {
    private ProgressBar mProgressBar;
    private Context mContext;
    int color;

    public SSLCProgressBarHandler(Context context) {
        mContext = context;

        initialize("");
    }
    public SSLCProgressBarHandler(Context context, int color) {
        mContext = context;
        this.color = color;

        initialize("");
    }

    public SSLCProgressBarHandler(Context context, String text) {
        mContext = context;

        initialize(text);
    }

    private void initialize(String text) {
        ViewGroup layout = (ViewGroup) ((Activity) mContext).findViewById(android.R.id.content).getRootView();

        mProgressBar = new ProgressBar(mContext, null, android.R.attr.progressBarStyleLarge);
        mProgressBar.setIndeterminate(true);

       // if (!text.isEmpty()) {
         //   mProgressBar.setText(text);
       // }
        //mProgressBar.setProgressDrawable(context.getDrawable(R.mipmap.p));
        int colorr = color !=0? color: mContext.getResources().getColor(R.color.colorPrimary);
        mProgressBar.getIndeterminateDrawable().setColorFilter(colorr, android.graphics.PorterDuff.Mode.SRC_IN);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        RelativeLayout rl = new RelativeLayout(mContext);
        rl.setGravity(Gravity.CENTER);
        rl.addView(mProgressBar);

        layout.addView(rl, params);

        hide();
    }

    public void show() {
        mProgressBar.setVisibility(View.VISIBLE);
        ((Activity) mContext).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void hide() {
        mProgressBar.setVisibility(View.INVISIBLE);
        ((Activity) mContext).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}
