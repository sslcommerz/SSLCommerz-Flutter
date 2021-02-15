package com.sslwireless.sslcommerzlibrary.model.datamodel;

import android.content.Context;
import android.net.http.X509TrustManagerExtensions;
import android.os.Build;
import android.util.Base64;
import android.util.Log;

import com.sslwireless.sslcommerzlibrary.model.util.SSLCTls12SocketFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

import static android.content.ContentValues.TAG;

public class SSLCApiHandlerClass {

    private String mbaseUrl, mMethodType, mEndPoint, mRequestType;
    private boolean mDataEncryption;
    private Context mContext;
    private Map<String, Object> mHashMap;
    private SSLCApiHandlerListener mSSLCApiHandlerListener;
    private URL url;

    public SSLCApiHandlerClass(Context context) {
        this.mContext = context;
    }

    public void sslCommerzRequest(Context context,
                                  String baseUrl, String endPoint,
                                  String methodType, String requestType, Map<String,
            Object> hashMap, boolean dataEncryption, SSLCApiHandlerListener SSLCApiHandlerListener) {
        this.mContext = context;
        this.mbaseUrl = baseUrl;
        this.mEndPoint = endPoint;
        this.mMethodType = methodType;
        this.mRequestType = requestType;
        this.mHashMap = hashMap;
        this.mDataEncryption = dataEncryption;
        this.mSSLCApiHandlerListener = SSLCApiHandlerListener;

        new AsyncTask().execute();
    }

    private String callGetMethod(HttpURLConnection conn) {
        InputStream stream;
        BufferedReader reader;
        try {
            stream = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
                //Log.d("Response: ", "> " + line);
            }

            //Log.e("HttpCall", "Calling response message: " + conn.getResponseMessage());

            conn.disconnect();
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String callPostMethod(HttpURLConnection conn, Map<String, Object> postDataParams) {
        OutputStream os;
        try {
            os = conn.getOutputStream();
            BufferedWriter writer = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                writer = new BufferedWriter(
                        new OutputStreamWriter(os, StandardCharsets.UTF_8));
            }
            if (writer != null) {
                writer.write(getPostDataString(postDataParams));
                writer.flush();
                writer.close();
            }
            os.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;

            while ((line = in.readLine()) != null) {
                sb.append(line);
                break;
            }

            //Log.e("HttpCall", "Calling response message: " + conn.getResponseMessage());

            in.close();
            conn.disconnect();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getPostDataString(Map<String, Object> params) throws Exception {
        StringBuilder postData = new StringBuilder();

        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }

//        Log.e(TAG, "getPostDataString: " + postData.toString());
        return postData.toString();
    }

    private void validatePinning(
            X509TrustManagerExtensions trustManagerExt,
            HttpsURLConnection conn, Set<String> validPins)
            throws SSLException {
        String certChainMsg = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            List<X509Certificate> trustedChain =
                    trustedChain(trustManagerExt, conn);
            for (X509Certificate cert : trustedChain) {
                byte[] publicKey = cert.getPublicKey().getEncoded();
                md.update(publicKey, 0, publicKey.length);
                String pin = Base64.encodeToString(md.digest(),
                        Base64.NO_WRAP);
                certChainMsg += "    sha256/" + pin + " : " +
                        cert.getSubjectDN().toString() + "\n";
                if (validPins.contains(pin)) {
                    return;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            throw new SSLException(e);
        }
        throw new SSLPeerUnverifiedException("Certificate pinning " +
                "failure\n  Peer certificate chain:\n" + certChainMsg);
    }

    private List<X509Certificate> trustedChain(
            X509TrustManagerExtensions trustManagerExt,
            HttpsURLConnection conn) throws SSLException {
        Certificate[] serverCerts = conn.getServerCertificates();
        X509Certificate[] untrustedCerts = Arrays.copyOf(serverCerts,
                serverCerts.length, X509Certificate[].class);
        String host = conn.getURL().getHost();
        try {
            return trustManagerExt.checkServerTrusted(untrustedCerts,
                    "RSA", host);
        } catch (CertificateException e) {
            throw new SSLException(e);
        }
    }

    public class AsyncTask extends android.os.AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... arg0) {
            String resultValue = null;
            try {

                if (mMethodType.equals("POST")) {
                    url = new URL(mbaseUrl + mEndPoint);
                } else if (mMethodType.equals("GET")) {
                    url = new URL(mbaseUrl + mEndPoint + "?" + getPostDataString(mHashMap));
                }

                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.setReadTimeout(30000);
                conn.setConnectTimeout(30000);
                conn.setRequestMethod(mMethodType);
                conn.setDoInput(true);

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                    SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
                    sslContext.init(null, null, null);
                    SSLSocketFactory noSSLv3Factory = null;
                    noSSLv3Factory = new SSLCTls12SocketFactory(sslContext.getSocketFactory());
                    conn.setSSLSocketFactory(noSSLv3Factory);
                }
//                else {
//                    noSSLv3Factory = sslContext.getSocketFactory();
//                }

                conn.connect();

//                Log.e("HttpCall", "Calling URL: " + conn.getURL());
//                Log.e("HttpCall", "Request Method: " + conn.getRequestMethod());

                if (mMethodType.equals("POST")) {
                    Map<String, Object> encryptedHashMap = new HashMap<>();
                   // encryptedHashMap.put("data", EncryptionHandler.getInstance().encryptData("", new Gson().toJson(mHashMap)));

                    resultValue = callPostMethod(conn, mDataEncryption ? encryptedHashMap : mHashMap);
                } else if (mMethodType.equals("GET")) {
                    resultValue = callGetMethod(conn);
                }

                return resultValue;

            } catch (Exception e) {
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null && !result.isEmpty()) {
                try {
//                    Log.e("HttpCall", "response log: " + result);
                    mSSLCApiHandlerListener.success(new JSONObject(result));
                } catch (JSONException e) {
                    e.printStackTrace();
                    mSSLCApiHandlerListener.fail(e.getMessage());
                }
            } else {
                mSSLCApiHandlerListener.fail("something went wrong");
            }
        }
    }
}
