package com.pinde.core.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class DESUtil {
    private final static String DES = "DES";
    private final static String ENCODE = "UTF-8";
    private final static String defaultKey = "pdkj666@key";//key为8位以上

    public static void main(String[] args){
        String data = "测试abc123";
//        String zdyKey = "";//自定义key
//        System.out.println(encrypt(data, zdyKey));
//        System.out.println(decrypt(encrypt(data, zdyKey), zdyKey));
        System.out.println(encrypt(data));
        System.out.println(decrypt(encrypt(data)));

    }

    /**
     * 使用 默认key 加密
     */
    public static String encrypt(String data){
        try {
            byte[] bt = new byte[0];
            bt = encrypt(data.getBytes(ENCODE), defaultKey.getBytes(ENCODE));
            return new BASE64Encoder().encode(bt);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    /**
     * 使用 默认key 解密
     */
    public static String decrypt(String data){
        if (null != data){
            try {
                BASE64Decoder decoder = new BASE64Decoder();
                byte[] buf = decoder.decodeBuffer(data);
                byte[] bt = decrypt(buf, defaultKey.getBytes(ENCODE));
                return new String(bt, ENCODE);
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return null;
    }

    /**
     * 根据键值进行加密
     */
    public static String encrypt(String data, String key){
        try {
            byte[] bt = encrypt(data.getBytes(ENCODE), defaultKey.getBytes(ENCODE));
            String strs = new BASE64Encoder().encode(bt);
            return strs;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    /**
     * 根据键值进行解密
     */
    public static String decrypt(String data, String key){
        if (null != data){
            try {
                BASE64Decoder decoder = new BASE64Decoder();
                byte[] buf = decoder.decodeBuffer(data);
                byte[] bt = decrypt(buf, key.getBytes(ENCODE));
                return new String(bt, ENCODE);
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return null;
    }

    /**
     * 根据键值进行加密
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        return cipher.doFinal(data);
    }

    /**
     * 根据键值进行解密
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        return cipher.doFinal(data);
    }



    private final static String IV = "1234567890123456";//需要前端与后端配置一致
    private final static String KEY = "1234567890123456";

    /**
     * 加密算法，使用默认的IV、KEY
     * @param content
     * @return
     */
    public static String encryptWx(String content){
        return encrypt(content,KEY,IV);
    }

    /**
     * 解密算法，使用默认的IV、KEY
     * @param content
     * @return
     */
    public static String decryptWx(String content){
        return decrypt(content,KEY,IV);
    }
    /**
     * 加密方法
     * @param content
     * @param key
     * @param iv
     * @return
     */
    public static String encrypt(String content, String key, String iv){
        try{
            // "算法/模式/补码方式"NoPadding PkcsPadding
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = content.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes("UTF-8"));
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            return Base64.getEncoder().encodeToString(encrypted);
        }catch (Exception e) {
            throw new RuntimeException("加密算法异常 CryptoUtil encrypt()加密方法，异常信息：" + e.getMessage());
        }
    }

    /**
     * 解密方法
     * @param content
     * @param key
     * @param iv
     * @return
     */
    public static String decrypt(String content, String key, String iv){
        try {
            byte[] encrypted1 = Base64.getDecoder().decode(content);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original).trim();
        } catch (Exception e) {
            throw new RuntimeException("加密算法异常 CryptoUtil decrypt()解密方法，异常信息：" + e.getMessage());
        }
    }


}
