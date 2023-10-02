package com.douzone.utils;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtils {

private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final int KEY_SIZE = 256; // AES-256 사용
    private static final int IV_SIZE = 16; // 16 바이트 IV
    private static final String KEY =  System.getenv("ENCRYPTION_KEY");


    public static String encrypt(String text) throws Exception {
        // 키 생성
        byte[] keyBytes = new byte[KEY_SIZE / 8];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(keyBytes);
        System.out.println(KEY);
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");

        // IV (Initialization Vector) 생성
        byte[] ivBytes = new byte[IV_SIZE];
        secureRandom.nextBytes(ivBytes);
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        // 암호화
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));

        // IV와 암호문을 합쳐서 반환
        byte[] combined = new byte[ivBytes.length + encrypted.length];
        System.arraycopy(ivBytes, 0, combined, 0, ivBytes.length);
        System.arraycopy(encrypted, 0, combined, ivBytes.length, encrypted.length);

        return Base64.getEncoder().encodeToString(combined);
    }

    public static String decrypt(String cipherText) throws Exception {
        byte[] combined = Base64.getDecoder().decode(cipherText);

        // IV 추출
        byte[] ivBytes = new byte[IV_SIZE];
        System.arraycopy(combined, 0, ivBytes, 0, IV_SIZE);
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        // 암호문 추출
        byte[] encrypted = new byte[combined.length - IV_SIZE];
        System.arraycopy(combined, IV_SIZE, encrypted, 0, encrypted.length);

        // 복호화
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(KEY.getBytes(), "AES"), ivSpec);
        byte[] decrypted = cipher.doFinal(encrypted);

        return new String(decrypted, "UTF-8");
    }
}
