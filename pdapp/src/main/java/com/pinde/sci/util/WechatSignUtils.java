package com.pinde.sci.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @Author
 * @Date 2022/11/21
 * @Description
 */
@Component
public class WechatSignUtils {

    private static Logger logger = LoggerFactory.getLogger(WechatSignUtils.class);

    public static String getSignature(String ticket,String noncestr,String timestamp,String url) {
        //将四个数据进行组合，传给SHA1进行加密
        String str = "jsapi_ticket=" + ticket +
                "&noncestr=" + noncestr +
                "&timestamp=" + timestamp +
                "&url=" + url;
        //sha1加密
        String signature = SHA1(str);
        return signature;
    }

    public static String SHA1(String str) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1"); //如果是SHA加密只需要将"SHA-1"改成"SHA"即可
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexStr = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取签名 md5加密(微信支付必须用MD5加密) 获取支付签名
     */
    public static String getSign(SortedMap<String, Object> params) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key).toString();
            // 拼接时，不包括最后一个&字符
            if (i == keys.size() - 1) {
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        String signature = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(prestr.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            logger.error("微信参数加密异常: {}", e.getCause());
        } catch (UnsupportedEncodingException e) {
            logger.error("微信参数加密异常: {}", e.getCause());
        }
        return signature;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws java.security.SignatureException
     */
    public static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    /**
     * @Description: 获取随机字符串
     * @return: java.lang.String
     **/
    public static String createNonceStr() {
        return UUID.randomUUID().toString();
    }

    /**
     * @Description: 获取随机时间戳
     * @return: java.lang.String
     **/
    public static String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
