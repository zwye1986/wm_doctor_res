package com.pinde.memcached;

import java.util.Date;
import com.danga.MemCached.MemCachedClient;


/**
 * 使用memcached的缓存实用类.
 *
 * @author 铁木箱子
 *
 */
public class MemCached
{
    // 创建全局的唯一实例
    protected static MemCachedClient mcc = new MemCachedClient();

    protected static MemCached memCached = new MemCached();
    /**
     * 保护型构造方法，不允许实例化！
     *
     */
    protected MemCached()
    {

    }

    /**
     * 获取唯一实例.
     * @return
     */
    public static MemCached getInstance()
    {
        return memCached;
    }

    /**
     * 添加一个指定的值到缓存中.
     * @param key
     * @param value
     * @return
     */
    public boolean add(String key, Object value)
    {
        return mcc.add(key, value);
    }

    public boolean add(String key, Object value, Date expiry)
    {
        return mcc.add(key, value, expiry);
    }

    public boolean replace(String key, Object value)
    {
        return mcc.replace(key, value);
    }

    public boolean replace(String key, Object value, Date expiry)
    {
        return mcc.replace(key, value, expiry);
    }

    /**
     * 根据指定的关键字获取对象.
     * @param key
     * @return
     */
    public Object get(String key)
    {
        return mcc.get(key);
    }

}