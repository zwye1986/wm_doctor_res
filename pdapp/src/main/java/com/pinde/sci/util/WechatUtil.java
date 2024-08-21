package com.pinde.sci.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author
 * @Date 2022/11/21
 * @Description
 */
public class WechatUtil {

    private static final HttpClientUtils httpClient = HttpClientUtils.getHttpsRequestSingleton();

    /**
     * 获得jsapi_ticket
     */
    public static JSONObject getJsApiTicket(String token) {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket"
                + "?access_token=" + token
                + "&type=jsapi";
        String msg = httpClient.sendGetTets(url, new HashMap<>());
        if (StringUtils.isBlank(msg)) {
            return null;
        }
        JSONObject jsonObject = JSONObject.parseObject(msg);
        return jsonObject;
    }

    /**
     * 获取token
     * @return msg
     */
    public static JSONObject getAccessToken(String appid, String secret) {
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, String> param = new HashMap<>(16);
        param.put("grant_type", "client_credential");
        param.put("appid", appid);
        param.put("secret", secret);
        String msg = httpClient.sendGetTets(url, param);
        if (StringUtils.isBlank(msg)) {
            return null;
        }
        JSONObject jsonObject = JSONObject.parseObject(msg);
        return jsonObject;
    }

    public static Map<String, String> getOauthUser(String appid,String secret, String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        Map<String, String> param = new HashMap<>(16);
        param.put("appid", appid);
        param.put("secret", secret);
        param.put("code", code);
        param.put("grant_type", "authorization_code");
        String respText = httpClient.sendGetTets(url,param);
        Map<String, String> stringToMap = JSON.parseObject(respText, HashMap.class);
        if (StringUtils.isBlank(stringToMap.get("errorcode"))) {
            return stringToMap;
        }
        return null;
    }
}
