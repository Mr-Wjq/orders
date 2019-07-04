package com.yzy.system.security.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.yzy.utils.SystemConstant;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author 王俊强
 * @Description Cache   redis实现
 */
public class RedisCache<K, V> implements Cache<K, V>, Serializable {
    private static  long timeout ;
    private transient static Logger log = LoggerFactory.getLogger(RedisCache.class);
    static{
    	Properties prop = new Properties();   
        InputStream in = RedisSessionDao.class.getResourceAsStream("/env.properties");   
        try {   
            prop.load(in);   
            timeout = Integer.parseInt(prop.getProperty("shiro.sessionTimeout"))/1000;   
        } catch (Exception e) {
        	 log.error("RedisSessionDao获取properties文件内容出错"+e);
         }    
    }
    private transient RedisTemplate<K, V> redisTemplate;

    public RedisCache(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public RedisCache() {
    }

    @Override
    public V get(K key) throws CacheException {
        log.debug("根据key:{}从redis获取对象", key);
        log.debug("redisTemplate : {}", redisTemplate);
        return redisTemplate.opsForValue().get(SystemConstant.SHIRO_CACHE_PREFIX + key);
    }

    @Override
    public V put(K key, V value) throws CacheException {
        log.debug("根据key:{}从redis添加对象", key);
        redisTemplate.opsForValue().set((K) (SystemConstant.SHIRO_CACHE_PREFIX + key), value, timeout, TimeUnit.SECONDS);
        return value;
    }

    /**
     * 执行set操作并且设置生存时间，单位为：秒
     * 
     * @param key
     * @param value
     * @return
     */
    public void setValueTime(final String key, final String value, final Integer timeOut) {
    	redisTemplate.opsForValue().set((K) (SystemConstant.SHIRO_CACHE_PREFIX + key), (V) value, timeOut, TimeUnit.SECONDS);
    }

    @Override
    public V remove(K key) throws CacheException {
        log.debug("redis cache remove :{}", key.toString());
        V value = redisTemplate.opsForValue().get(SystemConstant.SHIRO_CACHE_PREFIX + key);
        redisTemplate.delete(key);
        return value;
    }

    @Override
    public void clear() throws CacheException {
        log.debug("清除redis所有缓存对象");
        Set<K> keys = redisTemplate.keys((K) SystemConstant.SHIRO_CACHE_PREFIX_KEYS);
        redisTemplate.delete(keys);
    }

    @Override
    public int size() {
        Set<K> keys = redisTemplate.keys((K) SystemConstant.SHIRO_CACHE_PREFIX_KEYS);
        log.debug("获取redis缓存对象数量:{}", keys.size());
        return keys.size();
    }

    @Override
    public Set<K> keys() {
        Set<K> keys = redisTemplate.keys((K)SystemConstant.SHIRO_CACHE_PREFIX_KEYS);
        log.debug("获取所有缓存对象的key");
        if (keys.size() == 0) {
            return Collections.emptySet();
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        Set<K> keys = redisTemplate.keys((K) SystemConstant.SHIRO_CACHE_PREFIX_KEYS);
        log.debug("获取所有缓存对象的value");
        if (keys.size() == 0) {
            return Collections.emptySet();
        }
        List<V> vs = redisTemplate.opsForValue().multiGet(keys);

        return Collections.unmodifiableCollection(vs);
    }

    public RedisTemplate<K, V> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
