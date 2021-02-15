package com.sslwireless.sslcommerzlibrary.viewmodel.encryption;

import android.util.Base64;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

class SSLCEncryptionController {

    public static String encrypt(String ivValue, String secretKey, String mainText) throws Exception {
        byte[] ivBytes = ivValue.getBytes();
        byte[] keyBytes = secretKey.getBytes();
        byte[] textBytes = mainText.getBytes();
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES/CBC/PKCS5Padding");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
        return Base64.encodeToString(cipher.doFinal(textBytes), Base64.DEFAULT);
    }

    public static String decrypt(String ivValue, String secretKey, String mainText) throws Exception {
        byte[] ivBytes = ivValue.getBytes();
        byte[] keyBytes = secretKey.getBytes();
        byte[] textBytes = Base64.decode(mainText, Base64.DEFAULT);
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES/CBC/PKCS5Padding");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
        return new String(cipher.doFinal(textBytes));
    }

    public static String getIvString(int length) {
        String ivRandomChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890~!@#%^&*()_+";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < length) {
            int index = (int) (rnd.nextFloat() * ivRandomChar.length());
            salt.append(ivRandomChar.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}