package com.pinde.sci.util;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class RedisUtil {

    public static JedisPool pool;

    static {

        ResourceBundle bundle = ResourceBundle.getBundle("redisCfg");
        if (bundle == null) {
            throw new IllegalArgumentException("[redis.properties] is not found!");
        }
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(bundle.getString("redis.pool.maxTotal")));
        config.setMaxIdle(Integer.parseInt(bundle.getString("redis.pool.maxIdle")));
        config.setMaxWaitMillis(Long.parseLong(bundle.getString("redis.pool.maxWait")));
        config.setTestOnBorrow(Boolean.parseBoolean(bundle.getString("redis.pool.testOnBorrow")));
        config.setTestOnReturn(Boolean.parseBoolean(bundle.getString("redis.pool.testOnReturn")));

        pool = new JedisPool(config, bundle.getString("redis.host"),Integer.parseInt(bundle.getString("redis.port")));
    }

}
