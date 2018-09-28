package com.lz.study.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BaseRedisDao<K,V> {
	
	@Autowired(required=true) 
    protected RedisTemplate<K, V> redisTemplate;

}
