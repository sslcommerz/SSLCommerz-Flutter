package com.sslwireless.sslcommerzlibrary.model.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;
import android.util.Log;

import java.math.BigInteger;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.security.auth.x500.X500Principal;

import static android.security.keystore.KeyProperties.KEY_ALGORITHM_RSA;

public class SSLCSecurePrefs implements SharedPreferences {
    private final String TAG = "SecurePrefs";

    //private static final SecurePrefs ourInstance = new SecurePrefs();

    // Constants
    private static final int AES_KEY_SIZE = 256;
    private static final String ANDROID_KEYSTORE_PROVIDER = "AndroidKeyStore";
    private static final String SECURE_PREF_ALIAS_KEY = "SecurePreferencesKey";
    protected static final String UTF8_ENCODING = "utf-8";
    private final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";
    // NOTE: Change to a different IV
    private final byte[] IV = "BWQGgFMtvagVqdJP".getBytes();

    // Private members
    private boolean encryptKeys = true;
    private SharedPreferences delegate;
    private Context context;


    public SSLCSecurePrefs(Context context, SharedPreferences delegate, boolean encryptKeys) {
        this.delegate = delegate;
        this.context = context;
        this.encryptKeys = encryptKeys;

        createEncryptionKey();
    }

    private Set<String> encryptSet(Set<String> values) {
        Set<String> encryptedValues = new HashSet<String>();
        for (String value : values) {
            encryptedValues.add(encrypt(value));
        }
        return encryptedValues;
    }

    private Set<String> decryptSet(Set<String> values) {
        Set<String> decryptedValues = new HashSet<String>();
        for (String value : values) {
            decryptedValues.add(decrypt(value));
        }
        return decryptedValues;
    }

    private String encryptKey(String key) {
        return encryptKeys ? encrypt(key) : key;
    }

    public Editor edit() {
        return new Editor();
    }

    @Override
    public Map<String, String> getAll() {
        Map<String, ?> all = delegate.getAll();
        Set<String> keys = all.keySet();
        HashMap<String, String> unencryptedMap = new HashMap<>(keys.size());
        for (String key : keys) {
            String decryptedKey = decryptKey(key);
            Object value = all.get(key);
            if (value != null) {
                unencryptedMap.put(decryptedKey, decrypt(value.toString()));
            }
        }
        return unencryptedMap;
    }

    private String decryptKey(String key) {
        if (encryptKeys) {
            return decrypt(key);
        }
        return key;
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        final String v = delegate.getString(encryptKey(key), null);
        return v != null ? Boolean.parseBoolean(decrypt(v)) : defValue;
    }

    @Override
    public float getFloat(String key, float defValue) {
        final String v = delegate.getString(encryptKey(key), null);
        return v != null ? Float.parseFloat(decrypt(v)) : defValue;
    }

    @Override
    public int getInt(String key, int defValue) {
        final String v = delegate.getString(encryptKey(key), null);
        return v != null ? Integer.parseInt(decrypt(v)) : defValue;
    }

    @Override
    public long getLong(String key, long defValue) {
        final String v = delegate.getString(encryptKey(key), null);
        return v != null ? Long.parseLong(decrypt(v)) : defValue;
    }

    @Override
    public String getString(String key, String defValue) {
        final String v = delegate.getString(encryptKey(key), null);
        return v != null ? decrypt(v) : defValue;
    }

    @Override
    public Set<String> getStringSet(String key, Set<String> defValues) {
        final Set<String> stringSet = delegate.getStringSet(encryptKey(key), defValues);
        return stringSet != null ? decryptSet(stringSet) : defValues;
    }

    @Override
    public boolean contains(String s) {
        s = encryptKey(s);
        return delegate.contains(s);
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(
            OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        delegate.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(
            OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        delegate.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public class Editor implements SharedPreferences.Editor {

        protected SharedPreferences.Editor delegate;

        public Editor() {
            this.delegate = SSLCSecurePrefs.this.delegate.edit();
        }

        @Override
        public Editor putBoolean(String key, boolean value) {
            delegate.putString(encryptKey(key), encrypt(Boolean.toString(value)));
            return this;
        }

        @Override
        public Editor putFloat(String key, float value) {
            delegate.putString(encryptKey(key), encrypt(Float.toString(value)));
            return this;
        }

        @Override
        public Editor putInt(String key, int value) {
            delegate.putString(encryptKey(key), encrypt(Integer.toString(value)));
            return this;
        }

        @Override
        public Editor putLong(String key, long value) {
            delegate.putString(encryptKey(key), encrypt(Long.toString(value)));
            return this;
        }

        @Override
        public Editor putString(String key, String value) {
            delegate.putString(encryptKey(key), encrypt(value));
            return this;
        }

        @Override
        public SharedPreferences.Editor putStringSet(String key, Set<String> values) {
            delegate.putStringSet(encryptKey(key), encryptSet(values));
            return this;
        }

        @Override
        public void apply() {
            delegate.apply();
        }

        @Override
        public Editor clear() {
            delegate.clear();
            return this;
        }

        @Override
        public boolean commit() {
            return delegate.commit();
        }

        @Override
        public Editor remove(String s) {
            delegate.remove(encryptKey(s));
            return this;
        }
    }

    private void createEncryptionKey(){
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                KeyGenerator keyGenerator = KeyGenerator.getInstance(
                        KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEYSTORE_PROVIDER);
                keyGenerator.init(
                        new KeyGenParameterSpec.Builder(SECURE_PREF_ALIAS_KEY,
                                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                                .setKeySize(AES_KEY_SIZE)
                                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                                .setRandomizedEncryptionRequired(false)
                                .build());
                keyGenerator.generateKey();
            }else {
                Calendar start = Calendar.getInstance();
                Calendar end = Calendar.getInstance();
                end.add(Calendar.YEAR, 1);
                KeyPairGeneratorSpec spec = new KeyPairGeneratorSpec
                        .Builder(context)
                        .setAlias(SECURE_PREF_ALIAS_KEY)
                        .setSubject(new X500Principal("CN=Your Company ," +
                                " O=Your Organization" +
                                " C=Your Coountry"))
                        .setSerialNumber(BigInteger.ONE)
                        .setStartDate(start.getTime())
                        .setEndDate(end.getTime())
                        .build();
                KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_ALGORITHM_RSA, ANDROID_KEYSTORE_PROVIDER);

                generator.initialize(spec);
                generator.generateKeyPair();
            }
        }
        catch(Exception ex){
//            Log.e(TAG, ex.getLocalizedMessage());
        }
    }

    private SecretKey getSecretKeyFromKeyStore(){
        SecretKey secretKey = null;

        try{
            KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE_PROVIDER);
            keyStore.load(null);
            KeyStore.SecretKeyEntry secretKeyEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry(SECURE_PREF_ALIAS_KEY, null);
            if (secretKeyEntry == null) {
                createEncryptionKey();
                secretKeyEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry(SECURE_PREF_ALIAS_KEY, null);
            }

            secretKey = secretKeyEntry.getSecretKey();
        }
        catch(Exception ex){
//            Log.e(TAG, ex.getLocalizedMessage());
        }

        return secretKey;
    }

    protected String encrypt(String value) {
        String encryptedStr = "";

        try {

            // Get key from key store
            SecretKey secretKey = getSecretKeyFromKeyStore();

            // Initialize AES
            Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

            // Encrypt
            byte[] encryptedPasswordBytes = cipher.doFinal(value.getBytes(UTF8_ENCODING));

            // Convert to Base 64
            String encryptedStrTemp = Base64.encodeToString(encryptedPasswordBytes, Base64.DEFAULT);

            if (encryptedStrTemp != null)
            {
                encryptedStr = encryptedStrTemp.trim();
            }

        } catch (Exception ex) {
//            Log.e(TAG, ex.getLocalizedMessage());
        }

        return encryptedStr;
    }


    protected String decrypt(String value) {
        String decryptedStr = "";

        try {
            // Get key from key store
            SecretKey secretKey = getSecretKeyFromKeyStore();

            // Initialize AES
            Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

            // Convert from Base 64
            byte[] encryptedPasswordBytes = Base64.decode(value, Base64.DEFAULT);

            byte[] decryptedPasswordBytes = cipher.doFinal(encryptedPasswordBytes);

            // Convert Bytes to String
            decryptedStr = new String(decryptedPasswordBytes, "UTF8");

        } catch (Exception e){
            e.printStackTrace();
        }

        return decryptedStr;
    }
}