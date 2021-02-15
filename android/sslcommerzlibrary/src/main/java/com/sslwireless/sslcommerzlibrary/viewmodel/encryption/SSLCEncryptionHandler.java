package com.sslwireless.sslcommerzlibrary.viewmodel.encryption;

import android.util.Base64;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class SSLCEncryptionHandler {

    private static final SSLCEncryptionHandler SSLC_ENCRYPTION_HANDLER = new SSLCEncryptionHandler();

    private SSLCEncryptionHandler() {}

    public static SSLCEncryptionHandler getInstance() {
        return SSLC_ENCRYPTION_HANDLER;
    }

    public String encryptData(String secretKey, String data){
        String encryptedData;
        String keyData = secretKey;
        String ivValue = SSLCEncryptionController.getIvString(16);
        try {
            String encryptionValue = SSLCEncryptionController.encrypt(ivValue, keyData, data);
            encryptedData = Base64.encodeToString((ivValue + "|||" + encryptionValue).getBytes(), Base64.DEFAULT);
//            Log.e(TAG, "encryptData: "+encryptedData );
        } catch (Exception e) {
//            Log.e("encryption_error", "" + e.getMessage()+ " "+e.getClass());
            encryptedData = null;
        }
        return encryptedData;
    }

    public String decryptData(String secretKey, String data){
        String decryptedData;
        String[] parseResponseValue = new String(Base64.decode(data, Base64.DEFAULT)).split("\\|\\|\\|");
        try {
            decryptedData = SSLCEncryptionController.decrypt(parseResponseValue[0], secretKey, parseResponseValue[1]);
            return decryptedData;
        } catch (Exception e) {
//            Log.e("exception_found", ""+e.getMessage());
            return null;
        }
    }

}
