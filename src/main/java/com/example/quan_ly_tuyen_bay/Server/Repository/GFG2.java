package com.example.quan_ly_tuyen_bay.Server.Repository;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GFG2 {
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        //Phương thức getInstance tĩnh để gọi hằm băm SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        //Phương thức digest() được gọi
        // Trả về mảng byte

        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }


    public static String toHexString(byte[] hash){
        //Chuyển đổi amngr byte thành dạng có dấu
        BigInteger number = new BigInteger(1, hash);
        //Chuyển đổi giá trị băm sang giá trị hex
        StringBuilder hexString = new StringBuilder(number.toString(16));
        // Thêm các số không ở đầu

        while (hexString.length() < 16){
            hexString.insert(0,'0');
        }

        return  hexString.toString();
    }
}
