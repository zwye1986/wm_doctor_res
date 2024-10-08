package com.pinde.app.common.cache;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("tunnelListener")
@Order(1)
public class TunnelListener {
    Logger log = LoggerFactory.getLogger(TunnelListener.class);

    @Autowired
    private Environment env;

    private Session session;

    @PostConstruct
    public void init() {
        String tunnelEnable = env.getProperty("spring.redis.tunnel.enable");
        if(!"Y".equals(tunnelEnable)) {
            log.info("[TunnelListener.initialize] SSH Tunnel not in use");
            return;
        }

        String tunnelIp = env.getProperty("spring.redis.tunnel.host");
        String sshPort = env.getProperty("spring.redis.tunnel.sshport");
        String username = env.getProperty("spring.redis.tunnel.username");
        String password = env.getProperty("spring.redis.tunnel.password");
        String localPort = env.getProperty("spring.redis.tunnel.localport");

        String redisHost = env.getProperty("spring.redis.realhost");
        String redisPort = env.getProperty("spring.redis.realport");

        JSch jsch = new JSch();

        try {
            // 建立SSH会话
            session = jsch.getSession(username, tunnelIp, Integer.valueOf(sshPort));
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            // 建立SSH隧道，从本地端口6379连接到远程Redis服务器端口6379
            session.setPortForwardingL(Integer.valueOf(localPort), redisHost, Integer.valueOf(redisPort));

            log.info("[TunnelListener.initialize] SSH Tunnel established. Redis server available through localhost: {}", localPort);

            /*// 创建Redis连接池
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setMaxTotal(10);
            poolConfig.setMaxIdle(10);

            JedisPool jedisPool = new JedisPool(poolConfig, "localhost", localPort);
            Jedis jedis = jedisPool.getResource();

            // 测试Redis命令
            jedis.set("testKey", "testValue");
            String value = jedis.get("testKey");
            System.out.println("Get key: " + value);

            // 关闭Redis连接和SSH隧道
            jedis.close();
            jedisPool.close();*/

        } catch (Exception e) {
            log.error("[TunnelListener.initialize] SSH Tunnel failed to establish. ", e);
        }
    }

    @PreDestroy
    public void destroy() {
        if(session != null) {
            log.info("[TunnelListener.initialize] SSH Tunnel disconnected.");
            session.disconnect();
        }
    }
}
