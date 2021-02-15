package com.sslwireless.sslcommerzlibrary.model.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

public class SSLCMySharedPreferences {

    public static class SecurePreferencesException extends RuntimeException {

        public SecurePreferencesException(Throwable e) {
            super(e);
        }

    }

    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String KEY_TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String SECRET_KEY_HASH_TRANSFORMATION = "SHA-256";
    private static final String CHARSET = "UTF-8";

    private final boolean encryptKeys;
    private final Cipher writer;
    private final Cipher reader;
    private final Cipher keyWriter;
    private final SharedPreferences preferences;
    private static SSLCMySharedPreferences instance = null;

    public SSLCMySharedPreferences(Context context, String preferenceName,
                                   String secureKey, boolean encryptKeys)
            throws SecurePreferencesException {
        try {
            this.writer = Cipher.getInstance(TRANSFORMATION);
            this.reader = Cipher.getInstance(TRANSFORMATION);
            this.keyWriter = Cipher.getInstance(KEY_TRANSFORMATION);

            initCiphers(secureKey);

            this.preferences = context.getSharedPreferences(preferenceName,
                    Context.MODE_PRIVATE);

            this.encryptKeys = encryptKeys;
        } catch (GeneralSecurityException e) {
            throw new SecurePreferencesException(e);
        } catch (UnsupportedEncodingException e) {
            throw new SecurePreferencesException(e);
        }
    }

    public static SSLCMySharedPreferences getInstance(Context contxt, String prefName) {
        if (instance == null) {
            instance = new SSLCMySharedPreferences(contxt, prefName,
                    prefName+"Key", true);
        }
        return instance;
    }

    protected void initCiphers(String secureKey)
            throws UnsupportedEncodingException, NoSuchAlgorithmException,
            InvalidKeyException, InvalidAlgorithmParameterException {
        IvParameterSpec ivSpec = getIv();
        SecretKeySpec secretKey = getSecretKey(secureKey);

        writer.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        reader.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        keyWriter.init(Cipher.ENCRYPT_MODE, secretKey);
    }

    protected IvParameterSpec getIv() {
        byte[] iv = new byte[writer.getBlockSize()];
        System.arraycopy("fldsjfodasjifudslfjdsaofshaufihadsf".getBytes(), 0,
                iv, 0, writer.getBlockSize());
        return new IvParameterSpec(iv);
    }

    protected SecretKeySpec getSecretKey(String key)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] keyBytes = createKeyBytes(key);
        return new SecretKeySpec(keyBytes, TRANSFORMATION);
    }

    protected byte[] createKeyBytes(String key)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest
                .getInstance(SECRET_KEY_HASH_TRANSFORMATION);
        md.reset();
        byte[] keyBytes = md.digest(key.getBytes(CHARSET));
        return keyBytes;
    }

    public void putString(String key, String value) {
        if (value == null) {
            preferences.edit().remove(toKey(key)).commit();
        } else {
            putValue(toKey(key), value);
        }
    }

    public void putBoolean(String key, Boolean value) {
        if (value == null) {
            preferences.edit().remove(toKey(key)).commit();
        } else {
            putValue(toKey(key), Boolean.toString(value));
        }
    }

    public void putLong(String key, long value) {

        putValue(toKey(key), Long.toString(value));

    }

    public void putInt(String key, int value) {

        putValue(toKey(key), Integer.toString(value));

    }

    public boolean containsKey(String key) {
        return preferences.contains(toKey(key));
    }

    public void removeValue(String key) {
        preferences.edit().remove(toKey(key)).commit();
    }

    public String getString(String key, String value)
            throws SecurePreferencesException {
        if (preferences.contains(toKey(key))) {
            String securedEncodedValue = preferences.getString(toKey(key), "");
            return decrypt(securedEncodedValue);
        }
        return value;
    }

    public long getLong(String key, long value)
            throws SecurePreferencesException {
        if (preferences.contains(toKey(key))) {
            String securedEncodedValue = preferences.getString(toKey(key), "");
            return Long.parseLong(decrypt(securedEncodedValue));
        }
        return value;
    }

    public boolean getBoolean(String key, boolean value)
            throws SecurePreferencesException {
        if (preferences.contains(toKey(key))) {
            String securedEncodedValue = preferences.getString(toKey(key), "");
            return Boolean.parseBoolean(decrypt(securedEncodedValue));
        }
        return value;
    }

    public int getInt(String key, int value) throws SecurePreferencesException {
        if (preferences.contains(toKey(key))) {
            String securedEncodedValue = preferences.getString(toKey(key), "");
            return Integer.parseInt(decrypt(securedEncodedValue));
        }
        return value;
    }

    public void commit() {
        preferences.edit().commit();
    }

    public void clear() {
        preferences.edit().clear().commit();
    }

    private String toKey(String key) {
        if (encryptKeys)
            return encrypt(key, keyWriter);
        else
            return key;
    }

    private void putValue(String key, String value)
            throws SecurePreferencesException {
        String secureValueEncoded = encrypt(value, writer);

        preferences.edit().putString(key, secureValueEncoded).commit();
    }

    protected String encrypt(String value, Cipher writer)
            throws SecurePreferencesException {
        byte[] secureValue;
        try {
            secureValue = convert(writer, value.getBytes(CHARSET));
        } catch (UnsupportedEncodingException e) {
            throw new SecurePreferencesException(e);
        }
        String secureValueEncoded = Base64.encodeToString(secureValue,
                Base64.NO_WRAP);
        return secureValueEncoded;
    }

    protected String decrypt(String securedEncodedValue) {
        byte[] securedValue = Base64
                .decode(securedEncodedValue, Base64.NO_WRAP);
        byte[] value = convert(reader, securedValue);
        try {
            return new String(value, CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new SecurePreferencesException(e);
        }
    }

    private static byte[] convert(Cipher cipher, byte[] bs)
            throws SecurePreferencesException {
        try {
            return cipher.doFinal(bs);
        } catch (Exception e) {
            throw new SecurePreferencesException(e);
        }
    }
}
