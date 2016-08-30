package sun.cat.utils.redis;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import sun.cat.utils.spring.SpringContextUtils;

/**
 * redis工具类
 *
 * @author 小小工作者
 */
public class RedisUtil {
    private static Logger logger = LogManager.getLogger();
    public static void set(String key, String value) {
        logger.info("set redis :" + key + ":" + value);
        ApplicationContext ac = SpringContextUtils.getApplicationContext();
        ShardedJedisPool shardedJedisPool = (ShardedJedisPool) ac.getBean("shardedJedisPool");
        // 从redis缓存存储区中获取资源
        ShardedJedis jedis_temp = shardedJedisPool.getResource();
        logger.info("set redis 2:" + key + ":" + value);
        jedis_temp.set(key, value);
        shardedJedisPool.returnResource(jedis_temp);
        logger.info("set redis 3:" + key + ":" + value);
    }

    public static String get(String key) {
        ApplicationContext ac = SpringContextUtils.getApplicationContext();
        ShardedJedisPool shardedJedisPool = (ShardedJedisPool) ac.getBean("shardedJedisPool");
        // 从redis缓存存储区中获取资源
        String str = null ;
        ShardedJedis jedis_temp = shardedJedisPool.getResource();
        if (!jedis_temp.exists(key)) {
            str = null;
        }else{
            str = jedis_temp.get(key);
        }
        shardedJedisPool.returnResource(jedis_temp);
        return str;
    }

    public static <T> T get(String key, Class<T> className) {
        ApplicationContext ac = SpringContextUtils.getApplicationContext();
        ShardedJedisPool shardedJedisPool = (ShardedJedisPool) ac.getBean("shardedJedisPool");
        // 从redis缓存存储区中获取资源
        ShardedJedis jedis_temp = shardedJedisPool.getResource();
        if (!jedis_temp.exists(key)) {
            shardedJedisPool.returnResource(jedis_temp);
            return null;
        }
        String str = "";
        try {
            str = jedis_temp.get(key);
        } catch (Exception exc) {
        } finally {
            shardedJedisPool.returnResource(jedis_temp);
        }
        return JSONObject.parseObject(str, className);
    }

    public static void delete(String key) {
        ApplicationContext ac = SpringContextUtils.getApplicationContext();
        ShardedJedisPool shardedJedisPool = (ShardedJedisPool) ac.getBean("shardedJedisPool");
        // 从redis缓存存储区中获取资源
        ShardedJedis jedis_temp = shardedJedisPool.getResource();
        try {
            if (jedis_temp.exists(key)) {
                jedis_temp.del(key);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            shardedJedisPool.returnResource(jedis_temp);
        }
    }
    public static void set(String key, int seconds, String value) {
        ApplicationContext ac = SpringContextUtils.getApplicationContext();
        ShardedJedisPool shardedJedisPool = (ShardedJedisPool) ac.getBean("shardedJedisPool");
        // 从redis缓存存储区中获取资源
        ShardedJedis jedis_temp = shardedJedisPool.getResource();
        jedis_temp.setex(key, seconds, value);
        shardedJedisPool.returnResource(jedis_temp);
    }
}
