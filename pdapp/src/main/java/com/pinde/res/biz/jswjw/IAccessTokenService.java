package com.pinde.res.biz.jswjw;

public interface IAccessTokenService {
    String getAccessToken(String appid,String secret) throws Exception;
}
