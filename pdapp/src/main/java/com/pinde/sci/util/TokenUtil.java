package com.pinde.sci.util;

/**
 * Created by Administrator on 2017/11/13.
 */

import com.danga.MemCached.SockIOPool;
import com.pinde.core.util.StringUtil;
import com.pinde.memcached.MemCached;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;


/**
 * 使用memcached的缓存实用类.
 *
 * @author 铁木箱子
 *
 */
public class TokenUtil
{
    protected static MemCached memCached = null;

    @Value("#{configProperties['memcache.server']}")
    protected static String server = "127.0.0.1:11211";
    // 设置与缓存服务器的连接池
    static {
        memCached=MemCached.getInstance();
        // 服务器列表和其权重
        String[] servers = {server};
        Integer[] weights = {3};
        // 获取socke连接池的实例对象
        SockIOPool pool = SockIOPool.getInstance();
        // 设置服务器信息
        pool.setServers( servers );
        pool.setWeights( weights );
        // 设置初始连接数、最小和最大连接数以及最大处理时间
        pool.setInitConn( 5 );
        pool.setMinConn( 5 );
        pool.setMaxConn( 250 );
        pool.setMaxIdle( 1000 * 60 * 60 * 6 );
        // 设置主线程的睡眠时间
        pool.setMaintSleep( 30 );
        // 设置TCP的参数，连接超时等
        pool.setNagle( false );
        pool.setSocketTO( 3000 );
        pool.setSocketConnectTO( 0 );
        // 初始化连接池
        pool.initialize();
    }

    //缓存中取出token返回
    public static String getToken(String userFlow) {
        if(StringUtil.isBlank(userFlow))
        {
            return null;
        }
        return (String) memCached.get(userFlow);
    }
    //生成token并放入缓存中
    public static String createToken(String userFlow) {
        String token= new SimpleHash("md5", userFlow, ByteSource.Util.bytes(userFlow), 2).toHex().toUpperCase();
        memCached.add(userFlow, token, new Date(1000 * 60 * 60 * 24 * 30));//30天
        return token;
    }

    //用户传递的token比较
    public static boolean tokenEquals(String userFlow,String pToken) {
        if(StringUtil.isBlank(userFlow))
        {
            return false;
        }
        if(StringUtil.isBlank(pToken))
        {
            return false;
        }
        String token= TokenUtil.getToken(userFlow);
        if(StringUtil.isBlank(token))
        {
            return false;
        }
        if(!token.equals(pToken))
        {
            return false;
        }
        return true;
    }
}