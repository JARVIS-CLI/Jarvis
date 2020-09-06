package com.jarvis.module.modules.redis;

import com.jarvis.module.Module;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisModule extends Module {

    private JedisPool pool;

    public void preInit() {
        pool = new JedisPool(new JedisPoolConfig(), "localhost");
    }

    public void init() {

    }

    public void postInit() {
    }

}