package com.pinde.core.cache.redis;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.After;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Objects;

/**
 * @author xujunhua
 * @name RedisConfig
 * @description redis配置信息
 * @date 2022/3/8
 */
@Configuration
@DependsOn({"environment"})
public class RedisConfiguration {
    @Autowired
    private Environment env;

    /**
     * 冒号
     */
    private static final String COLON = ":";

    /**
     * 逗号
     */
    private static final String COMMA = ",";

    /**
     * 缓存默认时长【秒】
     */
    @Value("${spring.redis.expireTime:-1}")
    private Long expireTime;

    /**
     * 加锁时间【秒】
     */
    @Value("${spring.redis.lock.lockTime:30}")
    private Long lockTime;

    @Bean
    public RedisTemplate  redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 设置key的序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        // 设置value的序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public ValueOperations<String, String> valueOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    @Bean
    public ListOperations<String, String> listOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    public HashOperations<String, String, String> hashOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    public SetOperations<String, String> setOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ZSetOperations<String, String> zSetOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForZSet();
    }

    @Bean
    public RedissonClient redissonClient(RedisProperties redisProperties) {
        String schema_prefix = "redis://";
        Config config = new Config();
        config.setLockWatchdogTimeout(lockTime);

        //集群redis
        RedisProperties.Sentinel sentinel = redisProperties.getSentinel();
        RedisProperties.Cluster redisPropertiesCluster = redisProperties.getCluster();
        if (Objects.nonNull(redisPropertiesCluster)) {
            ClusterServersConfig clusterServersConfig = config.useClusterServers();
            for (String cluster : redisPropertiesCluster.getNodes()) {
                clusterServersConfig.addNodeAddress(schema_prefix + cluster);
            }

            if (StringUtils.isNotBlank(redisProperties.getPassword())) {
                clusterServersConfig.setPassword(redisProperties.getPassword());
            }

            clusterServersConfig.setTimeout(redisProperties.getTimeout());
            clusterServersConfig.setPingConnectionInterval(redisProperties.getTimeout() * 1000);
            return Redisson.create(config);
        }

        //单点redis
        if (StringUtils.isNotBlank(redisProperties.getHost())) {
            String address = schema_prefix + redisProperties.getHost() + COLON + redisProperties.getPort();
            SingleServerConfig singleServerConfig = config.useSingleServer().setAddress(address);
            if (StringUtils.isNotBlank(redisProperties.getPassword())) {
                singleServerConfig.setPassword(redisProperties.getPassword());
            }

            singleServerConfig.setTimeout(redisProperties.getTimeout());
            singleServerConfig.setPingConnectionInterval(redisProperties.getTimeout() * 1000);
            singleServerConfig.setDatabase(redisProperties.getDatabase());
            return Redisson.create(config);
        }

        //哨兵模式
        if (Objects.nonNull(sentinel)) {
            SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
            sentinelServersConfig.setMasterName(sentinel.getMaster());
            for (String node : sentinel.getNodes().split(COMMA)) {
                sentinelServersConfig.addSentinelAddress(schema_prefix + node);
            }

            if (StringUtils.isNotBlank(redisProperties.getPassword())) {
                sentinelServersConfig.setPassword(redisProperties.getPassword());
            }

            sentinelServersConfig.setTimeout(redisProperties.getTimeout());
            sentinelServersConfig.setPingConnectionInterval(redisProperties.getTimeout() * 1000);
            sentinelServersConfig.setDatabase(redisProperties.getDatabase());
            return Redisson.create(config);
        }

        return Redisson.create(config);
    }

    public Long getLockTime() {
        return lockTime;
    }

    public void setLockTime(Long lockTime) {
        this.lockTime = lockTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}
