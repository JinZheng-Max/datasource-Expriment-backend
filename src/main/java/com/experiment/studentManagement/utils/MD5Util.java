package com.experiment.studentManagement.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 */
public class MD5Util {

    /**
     * MD5加密（返回32位小写字符串）
     * @param text 明文
     * @return 加密后的32位MD5值
     */
    public static String encrypt(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            byte[] digest = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5加密失败", e);
        }
    }

    /**
     * MD5加密（返回32位大写字符串）
     * @param text 明文
     * @return 加密后的32位MD5值（大写）
     */
    public static String encryptToUpperCase(String text) {
        return encrypt(text).toUpperCase();
    }

    /**
     * MD5加密（返回16位小写字符串，取32位MD5的中间16位）
     * @param text 明文
     * @return 加密后的16位MD5值
     */
    public static String encrypt16(String text) {
        String md5 = encrypt(text);
        return md5.substring(8, 24);
    }

    /**
     * 验证密码是否正确
     * @param plainText 明文密码
     * @param md5Hash MD5哈希值
     * @return 是否匹配
     */
    public static boolean verify(String plainText, String md5Hash) {
        String encryptedText = encrypt(plainText);
        return encryptedText.equalsIgnoreCase(md5Hash);
    }

    /**
     * 加盐MD5加密
     * @param text 明文
     * @param salt 盐值
     * @return 加密后的MD5值
     */
    public static String encryptWithSalt(String text, String salt) {
        return encrypt(text + salt);
    }
}