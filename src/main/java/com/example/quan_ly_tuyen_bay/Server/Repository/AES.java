package com.example.quan_ly_tuyen_bay.Server.Repository;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AES {

    // Method to generate a new AES key
//    public static SecretKey generateKey(int n) throws Exception {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(n); // Key size
//        SecretKey key = keyGenerator.generateKey();
//        return key;
//    }

    public static SecretKey getStaticKey() {
        String key = "1234567890123456"; // 16-byte key for AES-128
        return new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
    }
    // Method to convert a byte array to a SecretKey

    // Method to encrypt a string using AES
    public static String encrypt(String plainText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(cipherText);
    }

    // Method to decrypt a string using AES
    public static String decrypt(String cipherText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] plainText = cipher.doFinal(decodedBytes); // Có thể xảy ra ngoại lệ tại đây
        return new String(plainText, StandardCharsets.UTF_8);
    }


}
