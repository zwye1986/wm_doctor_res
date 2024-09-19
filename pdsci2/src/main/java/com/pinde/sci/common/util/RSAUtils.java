package com.pinde.sci.common.util;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * RSA算法加密/解密工具类。
 */
public class RSAUtils {
    /**
     * 算法名称
     */
    private static final String ALGORITHOM = "RSA";
    /**
     * 密钥大小
     */
    private static final int KEY_SIZE = 1024;
    /**
     * 默认的安全服务提供者
     */
    private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();
    private static KeyPairGenerator keyPairGen = null;
    private static KeyFactory keyFactory = null;
    /**
     * 缓存的密钥对。
     */
    private static KeyPair oneKeyPair = null;

    //密文种子, 当想更换RSA钥匙的时候,只需要修改密文种子,即可更换 随便写上数字或者英文即可
    private static final String radamKey = "nari";

    //类加载后进行初始化数据
    static {
        try {
            keyPairGen = KeyPairGenerator.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
            keyFactory = KeyFactory.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 根据指定的密文种子,生成并返回RSA密钥对。
     */
    private static synchronized KeyPair generateKeyPair() {
        try {
            keyPairGen.initialize(KEY_SIZE, new SecureRandom(radamKey.getBytes()));
            oneKeyPair = keyPairGen.generateKeyPair();
            return oneKeyPair;
        } catch (InvalidParameterException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 返回初始化时默认的公钥。
     */
    public static RSAPublicKey getDefaultPublicKey() {
        KeyPair keyPair = generateKeyPair();
        if (keyPair != null) {
            return (RSAPublicKey)keyPair.getPublic();
        }
        return null;
    }

    public static KeyPair getDefaultKeyPair() {
        return generateKeyPair();
    }

    /**
     * 使用指定的私钥解密数据。
     *
     * @param privateKey 给定的私钥。
     * @param data       要解密的数据。
     * @return 原数据。
     */
    public static byte[] decrypt(PrivateKey privateKey, byte[] data) throws Exception {
        Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        ci.init(Cipher.DECRYPT_MODE, privateKey);
        return ci.doFinal(data);
    }

    /**
     * 使用默认的私钥解密给定的字符串。
     *
     * @param encryptText 密文。
     * @return 原文字符串。
     */
    public static String decryptString(String encryptText) {
        if (StringUtils.isBlank(encryptText)) {
            return null;
        }
        KeyPair keyPair = generateKeyPair();
        try {
            byte[] en_data = Hex.decode(encryptText);
            byte[] data = decrypt((RSAPrivateKey) keyPair.getPrivate(), en_data);
            return new String(data, StandardCharsets.UTF_8);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decryptString(String encryptText,KeyPair keyPair) {
        if (StringUtils.isBlank(encryptText)) {
            return null;
        }
        try {
            byte[] en_data = Hex.decode(encryptText);
            byte[] data = decrypt( keyPair.getPrivate(), en_data);
            return new String(data, StandardCharsets.UTF_8);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 使用秘钥 - 对js端传递过来密文进行解密
     *
     * @param encryptText 密文。
     * @return {@code encryptText} 的原文字符串。
     */
    public static String decryptStringByJs(String encryptText) {
        String text = decryptString(encryptText);
        if (text == null) {
            return null;
        }
        String reverse = StringUtils.reverse(text);
        String decode = null;
        try {
            //这里需要进行编码转换.注:在前端js对明文加密前需要先进行转码-可自行百度"编码转换"
            decode = URLDecoder.decode(reverse, "UTF-8");
            System.out.println("解密后文字：" + decode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decode;
    }


    public static String decryptStringByJs(String encryptText,KeyPair keyPair) {
        String text = decryptString(encryptText,keyPair);
        if (text == null) {
            return null;
        }
        String reverse = StringUtils.reverse(text);
        String decode = null;
        try {
            //这里需要进行编码转换.注:在前端js对明文加密前需要先进行转码-可自行百度"编码转换"
            decode = URLDecoder.decode(reverse, "UTF-8");
            System.out.println("解密后文字：" + decode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decode;
    }

    //java端 - 使用公钥进行加密
    public static byte[] encrypt(String plaintext) throws Exception {
        // 获取公钥及参数e,n
        RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
        //获取公钥指数 e
        BigInteger e = publicKey.getPublicExponent();
        //获取公钥系数 n
        BigInteger n = publicKey.getModulus();
        //先将明文进行编码
        String encode = URLEncoder.encode(plaintext);
        // 获取明文字节数组 m
        BigInteger m = new BigInteger(encode.getBytes());
        // 进行明文加密 c
        BigInteger c = m.modPow(e, n);
        //返回密文字节数组
        return c.toByteArray();
    }

    //java端 - 使用私钥进行解密
    public static String decrypt(byte[] cipherText) throws Exception {
        // 读取私钥
        KeyPair keyPair = generateKeyPair();
        RSAPrivateKey prk = (RSAPrivateKey) keyPair.getPrivate();
        // 获取私钥参数-指数/系数
        BigInteger d = prk.getPrivateExponent();
        BigInteger n = prk.getModulus();
        // 读取密文
        BigInteger c = new BigInteger(cipherText);
        // 进行解密
        BigInteger m = c.modPow(d, n);
        // 解密结果-字节数组
        byte[] mt = m.toByteArray();
        //转成String,此时是乱码
        String en = new String(mt);
        //再进行编码
        String result = URLDecoder.decode(en, "UTF-8");
        //最后返回解密后得到的明文
        return result;
    }


    public static void main(String[] args) {
        /*解密js端传递过来的密文*/
        //获取公钥对象--注意:前端那边需要用到公钥系数和指数
        RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
//        //公钥-系数(n)
        System.out.println("public key modulus:" + new String(Hex.encode(publicKey.getModulus().toByteArray())));
//        //公钥-指数(e1)
        System.out.println("public key exponent:" + new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
//        //JS加密后的字符串
//        String param = "abd87309c1c01f8eb20e46008e7260d792b336505cccf6e0328a3b35f72ba6cec6f4913aa80e150f3f78529ef8259d04f8fb0cda049e1426b89e2122fae2470039556364cdde128bd1d9068ade1c828172086bc316907b77fe9551edfd0a7e427ecf310f720ee558bc1fee07714401554b0887672053ed9879f6aa895816f368";
//        //解密后的字符串
//        String param1 = RSAUtils.decryptStringByJs(param);
//
//        System.out.println(param1);

        System.out.println(decryptStringByJs("784ee3f15625bb5718b05ea7b19ac2beab778998721f1c54a6c804f48b31a8397baa85b9727f59e6d7a7abdbeba6f284449249f7cb7e7151b3cfbb6f1d0dae335011fda60d796a6f6b78ec55f7f43965d4b0275191aa6ab13e5d115521cd4f6e04895f6eb7358f7d0d592109c5b942f5a5ba5766e0a99840272ec6ee368ca507"));
        System.out.println(decryptStringByJs("229de3cec56863a381b902a1a469c1e634976b43d2017b7e0d46283ca85d6ff9653b499f530018751859b7159e3e59c0119c43cc1e1d4d193c010545dc4dee5566de97667ca410c499352ff01f13b7eb99f693d9d8198c453a55778f0ca04d0f94f80b4dcb739b9839e0cac3c3d8de0542e5c0f912fdd49ac9fc65e7041ceb14"));

    }
}
