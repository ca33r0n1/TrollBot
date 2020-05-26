package net.projectrefresh.TrollBot.Data;

import redis.clients.jedis.Jedis;

public class RedisController {

    private Jedis Jedis;

    public RedisController() {
        start();
    }

    private void start(){
        Jedis = new Jedis("redis.projectrefresh.net");
        Jedis.auth("cWCiET8sMmSw");
        Jedis.connect();
    }

    public redis.clients.jedis.Jedis getJedis() {
        return Jedis;
    }

    public void setJedis(redis.clients.jedis.Jedis jedis) {
        Jedis = jedis;
    }
}
