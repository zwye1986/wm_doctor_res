package com.pinde.sci.config.cache.redis;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author xujunhua
 * @name RedisService
 * @description description
 * @date 2023/9/1
 */
@Component
@Order
@DependsOn("tunnelListener")
public class RedisUtil {
    @Autowired
    private RedisConfiguration redisConfiguration;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ValueOperations<String, String> valueOperations;

    @Autowired
    private HashOperations<String, String, String> hashOperations;

    @Autowired
    private ListOperations<String, String> listOperations;

    @Autowired
    private SetOperations<String, String> setOperations;

    @Autowired
    private ZSetOperations<String, String> zSetOperations;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 不区分数据类型，设置key的有效时长
     *
     * @param key key
     * @param expireTime 有效时长
     * @return 设置成功:true，设置失败:false
     */
    public boolean expire(String key, Long expireTime) {
        if (StringUtils.isBlank(key)) {
            return false;
        }

        expireTime = Objects.isNull(expireTime) || expireTime.equals(0L) ? redisConfiguration.getExpireTime() : expireTime;
        return redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 不区分数据类型，存在该key
     *
     * @param key key
     * @return 存在:true，不存在:false
     */
    public boolean hasKey(String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        }

        return redisTemplate.hasKey(key);
    }

    /**
     * 不区分数据类型，删除key及对应value
     *
     * @param key key
     * @return 删除成功:true，删除失败:false
     */
    public void delete(String key) {
        if (StringUtils.isNotBlank(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 不区分数据类型，删除多个key
     *
     * @param keyList key集合
     * @return 删除key的数量
     */
    public void delete(List<String> keyList) {
        if (CollectionUtils.isEmpty(keyList)) {
            return;
        }

        keyList = keyList.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(keyList)) {
            return;
        }

        redisTemplate.delete(keyList);
    }

    /**
     * String类型，设置当前的key以及value值
     *
     * @param key key值
     * @param value key对应的value值
     * @param expireTime 过期时长
     */
    public void setForString(String key, String value, Long expireTime) {
        boolean isAnyBlank = StringUtils.isAnyBlank(key, value);
        if (isAnyBlank) {
            return;
        }

        expireTime = Objects.isNull(expireTime) || expireTime.equals(0L) ? redisConfiguration.getExpireTime() : expireTime;
        valueOperations.set(key, value, expireTime, TimeUnit.SECONDS);
    }

    /**
     * String类型，获取key对应的value值
     *
     * @param key key值
     * @param t 返回指定对象
     * @return key对应的value值
     */
    public <T> T getForString(String key, Class<T> t) {
        if (StringUtils.isBlank(key)) {
            return null;
        }

        String value = valueOperations.get(key);
        if (StringUtils.isBlank(value)) {
            return null;
        }

        return JSONObject.parseObject(value, t);
    }

    /**
     * String类型，为多个键分别设置它们的值
     *
     * @param maps 多个键对应的值
     * @param expireTime 过期时长
     */
    public void multiSetForString(Map<String, String> maps, Long expireTime) {
        Map<String, String> newMap = removeNull(maps);
        if (newMap.isEmpty()) {
            return;
        }

        // 永不过期
        if (Objects.isNull(expireTime) || expireTime.equals(0L)) {
            valueOperations.multiSet(maps);
            return;
        }

        // 有过期时长
        maps.forEach((k, v) -> setForString(k, v, expireTime));
    }

    /**
     * String类型，取出多个键值分别对应的值
     *
     * @param keys 多个键值
     * @param t 返回指定对象
     * @return 多个键值分别对应的值
     */
    public <T> List<T> multiGetForString(List<String> keys, Class<T> t) {
        if (CollectionUtils.isEmpty(keys)) {
            return null;
        }

        keys = keys.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(keys)) {
            return null;
        }

        List<String> valueList = valueOperations.multiGet(keys);
        if (CollectionUtils.isEmpty(valueList)) {
            return null;
        }

        valueList = valueList.stream().map(String::valueOf).filter(StringUtils::isNotBlank).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(valueList)) {
            return null;
        }

        List<T> tList = new ArrayList<>();
        valueList.forEach(x -> tList.add(JSONObject.parseObject(x, t)));
        return tList;
    }

    /**
     * Hash类型，检查key下hashKey是否存在
     *
     * @param key key
     * @param hashKey hashKey
     * @return 存在:true，不存在:false
     */
    public boolean hasKeyForHash(String key, String hashKey) {
        boolean isAnyBlank = StringUtils.isAnyBlank(key, hashKey);
        if (isAnyBlank) {
            return false;
        }

        return hashOperations.hasKey(key, hashKey);
    }

    /**
     * Hash类型，设置信息
     *
     * @param key key
     * @param hashKey hashKey
     * @param hashValue hashValue
     */
    public void putForHash(String key, String hashKey, String hashValue) {
        boolean isAnyBlank = StringUtils.isAnyBlank(key, hashKey, hashValue);
        if (isAnyBlank) {
            return;
        }

        hashOperations.put(key, hashKey, hashValue);
    }

    /**
     * Hash类型，设置信息
     *
     * @param key key
     * @param map 多个键值对
     */
    public void putAllForHash(String key, Map<String, String> map) {
        if (Objects.isNull(key)) {
            return;
        }

        Map<String, String> newMap = removeNull(map);
        if (newMap.isEmpty()) {
            return;
        }

        hashOperations.putAll(key, newMap);
    }

    /**
     * Hash类型，获取key下hashKey对应的值
     *
     * @param key key
     * @param hashKey hashKey
     * @param t 返回指定对象
     * @return hashKey对应的值
     */
    public <T> T getForHash(String key, String hashKey, Class<T> t) {
        boolean isAnyBlank = StringUtils.isAnyBlank(key, hashKey);
        if (isAnyBlank) {
            return null;
        }

        String value = hashOperations.get(key, hashKey);
        return JSONObject.parseObject(value, t);
    }

    /**
     * Hash类型，获取key下hashKey对应值的集合
     *
     * @param key key
     * @param hashKeyList hashKey集合
     * @param t 返回指定对象
     * @return key下hashKey对应值的集合
     */
    public <T> List<T> multiGetForHash(String key, List<String> hashKeyList, Class<T> t) {
        if (CollectionUtils.isEmpty(hashKeyList)) {
            return null;
        }

        hashKeyList = CollectionUtils.isEmpty(hashKeyList) ? new ArrayList<>(0) :hashKeyList;
        hashKeyList = hashKeyList.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(hashKeyList)) {
            return null;
        }

        List<String> hashValueList = hashOperations.multiGet(key, hashKeyList);
        if (CollectionUtils.isEmpty(hashValueList)) {
            return null;
        }

        hashValueList = hashValueList.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(hashValueList)) {
            return null;
        }

        List<T> tList = new ArrayList<>();
        hashValueList.forEach(x -> tList.add(JSONObject.parseObject(x, t)));
        return tList;
    }

    /**
     * Hash类型，删除key下hashKey对应的键值对
     *
     * @param key key
     * @param hashKeyList hashKey集合
     * @return 删除的数量
     */
    public long deleteForHash(String key, List<String> hashKeyList) {
        if (StringUtils.isBlank(key)) {
            return 0;
        }

        hashKeyList = CollectionUtils.isEmpty(hashKeyList) ? new ArrayList<>(0) :hashKeyList;
        hashKeyList = hashKeyList.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(hashKeyList)) {
            return 0;
        }

        Object[] objects = hashKeyList.toArray(new Object[]{});
        return hashOperations.delete(key, objects);
    }

    /**
     * Hash类型，检查key下hashKey是否存在
     *
     * @param key key
     * @param hashKey hashKey
     * @return 存在:true，不存在:false
     */
    public boolean hasKeyForBoundHash(String key, String hashKey) {
        boolean isAnyBlank = StringUtils.isAnyBlank(key, hashKey);
        if (isAnyBlank) {
            return false;
        }

        BoundHashOperations<String, String, String> boundHashOperations = redisTemplate.boundHashOps(key);
        return boundHashOperations.hasKey(hashKey);
    }

    /**
     * Hash类型，设置信息
     *
     * @param key key
     * @param hashKey hashKey
     * @param hashValue hashValue
     * @param expireTime key有效时长
     */
    public void putForBoundHash(String key, String hashKey, String hashValue, Long expireTime) {
        BoundHashOperations<String, String, String> boundHashOperations = redisTemplate.boundHashOps(key);
        boundHashOperations.put(hashKey, hashValue);
        boundHashOperations.expire(expireTime, TimeUnit.SECONDS);
    }

    /**
     * Hash类型，设置信息
     *
     * @param key key
     * @param map 多个键值对
     * @param expireTime key的有效时长
     */
    public void putAllForBoundHash(String key, Map<String, String> map, Long expireTime) {
        BoundHashOperations<String, String, String> boundHashOperations = redisTemplate.boundHashOps(key);
        boundHashOperations.putAll(map);
        boundHashOperations.expire(expireTime, TimeUnit.SECONDS);
    }

    /**
     * Hash类型，获取key下hashKey对应的值
     *
     * @param key key
     * @param hashKey hashKey
     * @param t 返回指定对象
     * @return hashKey对应的值
     */
    public <T> T getForBoundHash(String key, String hashKey, Class<T> t) {
        boolean isAnyBlank = StringUtils.isAnyBlank(key, hashKey);
        if (isAnyBlank) {
            return null;
        }

        BoundHashOperations<String, String, String> boundHashOperations = redisTemplate.boundHashOps(key);
        String value = boundHashOperations.get(hashKey);
        return JSONObject.parseObject(value, t);
    }

    /**
     * Hash类型，获取key下hashKey对应值的集合
     *
     * @param key key
     * @param hashKeyList hashKey集合
     * @param t 返回指定对象
     * @return key下hashKey对应值的集合
     */
    public <T> List<T> multiGetForForBoundHash(String key, List<String> hashKeyList, Class<T> t) {
        if (CollectionUtils.isEmpty(hashKeyList)) {
            return null;
        }

        hashKeyList = CollectionUtils.isEmpty(hashKeyList) ? new ArrayList<>(0) :hashKeyList;
        hashKeyList = hashKeyList.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(hashKeyList)) {
            return null;
        }

        BoundHashOperations<String, String, String> boundHashOperations = redisTemplate.boundHashOps(key);
        List<String> hashValueList = boundHashOperations.multiGet(hashKeyList);
        if (CollectionUtils.isEmpty(hashValueList)) {
            return null;
        }

        hashValueList = hashValueList.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(hashValueList)) {
            return null;
        }

        List<T> tList = new ArrayList<>();
        hashValueList.forEach(x -> tList.add(JSONObject.parseObject(x, t)));
        return tList;
    }

    /**
     * Hash类型，删除key下hashKey对应的键值对
     *
     * @param key key
     * @param hashKeyList hashKey集合
     * @return 删除的数量
     */
    public long deleteForBoundHash(String key, List<String> hashKeyList) {
        if (StringUtils.isBlank(key)) {
            return 0;
        }

        hashKeyList = CollectionUtils.isEmpty(hashKeyList) ? new ArrayList<>(0) :hashKeyList;
        hashKeyList = hashKeyList.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(hashKeyList)) {
            return 0;
        }

        Object[] objects = hashKeyList.toArray(new Object[]{});
        BoundHashOperations<String, String, String> boundHashOperations = redisTemplate.boundHashOps(key);
        return boundHashOperations.delete(objects);
    }

    /**
     * hashValue集合转化为指定对象集合
     * 注意：单个hashValue也为集合
     *
     * @param hashValueList hashValue集合
     * @param t 指定对象
     * @param <T> 泛型
     * @return 指定对象集合
     */
    public <T> List<T> valuesToModels(List<String> hashValueList, Class<T> t) {
        if (CollectionUtils.isEmpty(hashValueList)) {
            return new ArrayList<>(0);
        }

        hashValueList = hashValueList.stream().filter(StringUtils::isNotBlank).distinct().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(hashValueList)) {
            return new ArrayList<>(0);
        }

        List<T> tList = new ArrayList<>();
        for (String hashValue : hashValueList) {
            tList.addAll(JSONArray.parseArray(hashValue, t));
        }

        return tList;
    }

    /**
     * 去掉key或value为null后的键值
     *
     * @param maps maps
     * @return 去掉key或value为null后的键值对集合
     */
    private Map<String, String> removeNull(Map<String, String> maps) {
        Map<String, String> newMap = new HashMap<>();
        if (Objects.isNull(maps) || maps.isEmpty()) {
            return newMap;
        }

        for (Map.Entry<String, String> map : maps.entrySet()) {
            if (StringUtils.isBlank(map.getKey())) {
                continue;
            }

            if (StringUtils.isBlank(map.getValue())) {
                continue;
            }

            newMap.put(map.getKey(), map.getValue());
        }

        return newMap;
    }

    /**
     * 加锁
     * 注意:得到锁和释放锁是同一个线程
     *
     * @param lockName 锁名称
     * @return 加锁成功:true；加锁失败:false
     */
    public Boolean lock(String lockName, long lockTime) {
        if (StringUtils.isBlank(lockName)) {
            return false;
        }

        RLock lock = redissonClient.getLock(lockName);
        if (Objects.isNull(lock)) {
            return false;
        }

        // 已获取到锁
        boolean isLocked = lock.isLocked();
        if (isLocked) {
            return true;
        }

        // 已配置加锁时长,加锁
        if (0L != lockTime) {
            lock.lock(lockTime, TimeUnit.SECONDS);
            return lock.isLocked();
        }

        // 未配置加锁时长,使用默认值
        lock.lock();
        return lock.isLocked();
    }

    /**
     * 释放锁
     * 注意:得到锁和释放锁是同一个线程
     *
     * @param lockName 锁名称
     * @return 释放锁成功:true；释放锁失败:false
     */
    public Boolean unlock(String lockName) {
        if (StringUtils.isBlank(lockName)) {
            return false;
        }

        RLock lock = redissonClient.getLock(lockName);
        if (Objects.isNull(lock)) {
            return false;
        }

        // 未获取到锁
        boolean isLocked = lock.isLocked();
        if (isLocked) {
            // 释放锁
            lock.unlock();
        }

        return !lock.isLocked();
    }

    /**
     * 强制释放锁
     * 注意:得到锁和释放锁可以不是同一个线程
     *
     * @param lockName 锁名称
     * @return 释放锁成功:true；释放锁失败:false
     */
    public Boolean forceUnlock(String lockName) {
        if (StringUtils.isBlank(lockName)) {
            return false;
        }

        RLock lock = redissonClient.getLock(lockName);
        if (Objects.isNull(lock)) {
            return false;
        }

        // 未获取到锁
        boolean isLocked = lock.isLocked();
        if (isLocked) {
            // 释放锁
            lock.forceUnlock();
        }

        return !lock.isLocked();
    }

}
