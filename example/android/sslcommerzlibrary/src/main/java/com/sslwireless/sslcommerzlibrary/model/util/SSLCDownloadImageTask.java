package com.sslwireless.sslcommerzlibrary.model.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCDowloadImageListener;

import java.io.InputStream;

public class SSLCDownloadImageTask {
    private ImageView bmImage;
    private Context context;
    private String imageUrl;
    private SSLCDowloadImageListener SSLCDowloadImageListener;

    public SSLCDownloadImageTask(Context context, ImageView bmImage, String imageUrl, SSLCDowloadImageListener SSLCDowloadImageListener) {
        this.context = context;
        this.bmImage = bmImage;
        this.imageUrl = imageUrl;
        this.SSLCDowloadImageListener = SSLCDowloadImageListener;

        new DownloadImage(bmImage).execute(imageUrl);
    }

    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        private ImageView bmImage;

        public DownloadImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                //Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);

            if (result != null) {
                SSLCDowloadImageListener.downloadSuccess(result);
            } else {
                SSLCDowloadImageListener.downloadFailed("Failed to load image");
            }
        }
    }
}
