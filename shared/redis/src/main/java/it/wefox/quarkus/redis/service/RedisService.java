package it.wefox.quarkus.redis.service;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import javax.inject.Singleton;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 20/12/22
 * @Time: 12:26
 */
@Singleton
public class RedisService<K,V> {


    public static void init(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);
    }

    public void set(K key, V value){
        RedissonClient redisson = Redisson.create();
        RMap<K,V> map = redisson.getMap("redisMap");
        map.put(key,value);
    }

    public K get(V key){
        RedissonClient redisson = Redisson.create();
        RMap<K,V> map = redisson.getMap("redisMap");
        return (K) map.get(key);
    }
}
