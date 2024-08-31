package com.pinde.res.biz.jswjw.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pinde.core.pdf.utils.ObjectUtils;
import com.pinde.res.biz.jswjw.IAccessTokenService;
import com.pinde.res.ctrl.jswjw.JswjwWxController;
import com.pinde.sci.util.WechatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class AccessTokenServiceImpl implements IAccessTokenService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String ACCESS_TOKEN_KEY = "access_token";
    private static final String LOCK_KEY = "lock:access_token";
    private static final long LOCK_EXPIRATION = 5; // 锁过期时间（秒）
    private static final long EXPIRATION_TIME = 3600; // access_token过期时间（秒）

    private static Logger logger = LoggerFactory.getLogger(AccessTokenServiceImpl.class);

    public String getAccessToken(String appid,String secret) throws Exception {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        String accessToken = valueOps.get(ACCESS_TOKEN_KEY);

        if (accessToken == null || accessToken.isEmpty()) {
            // 尝试获取分布式锁
            if (tryLock(LOCK_KEY, LOCK_EXPIRATION)) {
                try {
                    // 再次检查，因为可能多个线程同时通过了第一次检查
                    accessToken = valueOps.get(ACCESS_TOKEN_KEY);
                    if (accessToken == null || accessToken.isEmpty()) {
                        // 调用接口获取新的access_token
                        accessToken = fetchNewAccessToken(appid, secret);
                        // 保存新的access_token到Redis，并设置过期时间
                        valueOps.set(ACCESS_TOKEN_KEY, accessToken, EXPIRATION_TIME, TimeUnit.SECONDS);
                    }
                } finally {
                    // 释放分布式锁
                    releaseLock(LOCK_KEY);
                }
            } else {
                // 如果获取锁失败，等待一段时间后重试
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return getAccessToken(appid, secret); // 重试
            }
        }

        return accessToken;
    }

    private boolean tryLock(String lockKey, long expiration) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        String lockValue = String.valueOf(System.currentTimeMillis() + expiration * 1000);
        Boolean isLocked = valueOps.setIfAbsent(lockKey, lockValue);
        return isLocked != null && isLocked;
    }

    private void releaseLock(String lockKey) {
        // 仅在当前锁的值与预期值匹配时删除锁
        String lockValue = redisTemplate.opsForValue().get(lockKey);
        if (lockValue != null && Long.parseLong(lockValue) > System.currentTimeMillis()) {
            redisTemplate.delete(lockKey);
        }
    }

    private String fetchNewAccessToken(String appid, String secret  ) throws Exception {
        // 调用获取access_token的接口
        JSONObject accessToken = WechatUtil.getAccessToken(appid, secret);
        if (ObjectUtils.isEmpty(accessToken) || !accessToken.containsKey("access_token")) {
            logger.error("=========获取accessToken失败，失败信息: appid = {},secret = {}", appid, secret );
            throw new Exception("获取accessToken失败");
        }
        String access_token = accessToken.getString("access_token");
        return access_token;
    }
}

