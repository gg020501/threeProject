package com.fh.utils;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;


public class RedisUtils {
     //某某池对象
    private static JedisPool jedisPool;


    static {
        // 某某池  最大  最小  初始化大小  空闲时间
        JedisPoolConfig poolConfig=new JedisPoolConfig();
        poolConfig.setMinIdle(5);
        poolConfig.setMaxIdle(10);
        jedisPool=new JedisPool(poolConfig,"192.168.131.131",6379,0,"123");
    }
    /*string  get*/
     public static String get(String key){
         Jedis jedis = jedisPool.getResource();
         String value = jedis.get(key);
        jedisPool.returnResource(jedis);
        return value;
    }
    /*string   set*/
    public static void set(String key,Object data){
        String jsonData = JSONObject.toJSONString(data);
        Jedis jedis = jedisPool.getResource();
        jedis.set(key,jsonData);
        jedisPool.returnResource(jedis);
    }
    /*string   set2*/
    public static void set(String key,String data){
        Jedis jedis = jedisPool.getResource();
        jedis.set(key,data);
        jedisPool.returnResource(jedis);
    }

    /*判断key是否存在*/
    public static Boolean exists(String key){
        Jedis jedis = jedisPool.getResource();
        Boolean exists = jedis.exists(key);
        jedisPool.returnResource(jedis);
        return exists;
    }

    /*删除key*/
    public static void del(String key){
        Jedis jedis = jedisPool.getResource();
        jedis.del(key);
        jedisPool.returnResource(jedis);

    }
    /*哈希保存*/
    public static  void hset(String key, String field, String value){
        Jedis jedis = jedisPool.getResource();
        jedis.hset(key,field,value);
        jedisPool.returnResource(jedis);
    }
    /*哈希获取*/
     public static  String hget(String key,String field){
        Jedis jedis = jedisPool.getResource();
        String set = jedis.hget(key,field);
        jedisPool.returnResource(jedis);
        return set;
    }
    /*zset保存*/
    public static  void zadd(int id, String value){
        Jedis jedis = jedisPool.getResource();
        jedis.zadd("logData",id,value);
        jedisPool.returnResource(jedis);
    }
    /*list保存*/
    public static  void rpush (String value){
        Jedis jedis = jedisPool.getResource();
        jedis.rpush("logData",value);
        jedisPool.returnResource(jedis);
    }

    /*list查询集合*/
    public static  List<String> lrange  (Integer start, Integer end){
        Jedis jedis = jedisPool.getResource();
        List<String> logData = jedis.lrange("logData", start, end);
        return logData;
    }

    /*list查询长度*/
    public static  Long llen  (){
        Jedis jedis = jedisPool.getResource();
        Long logData = jedis.llen("logData");
        return logData;
    }



}
