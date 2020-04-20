package com.hcis.ipanther.core.cache.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zhw
 * @date 2020/1/15
 **/
public class RedisTemplateService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private RedisTemplate<Object, Object> redisTemplate;

    public RedisTemplateService() {
    }

    public RedisTemplateService(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    /**
     * 指定缓存失败时间
     * @param key 键
     * @param time 失效时间
     * @return 布尔
     */
    public boolean expire(String key, long time){
        try {
            if(time > 0){
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            logger.error("redis error: ", e);
            return false;
        }
    }

    /**
     * 根据key, 获取过期时间
     * @param key 键 ，不能为null
     * @return 时间（秒） 返回0代表永久有效
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 按到key是否存在
     * @param key 键
     * @return 是否存在
     */
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            logger.error("redis error: ", e);
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key 一个或者多个值
     */
    @SuppressWarnings("unchecked")
    public void del(String... key){
        if(key != null && key.length > 0){
            if(key.length == 1){
                redisTemplate.delete(key[0]);
            }else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    //=================================================== String ===================================================================

    /**
     * 普通缓存获取
     * @param key
     * @return
     */
    public Object get(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return 是否成功
     */
    public boolean set(String key, Object value){
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        }catch (Exception e){
            logger.error("redis error: ", e);
            return false;
        }
    }

    /**
     * 普通缓存放入并且设置时间
     * @param key 键
     * @param value 值
     * @param time 时间（秒） time 要大于零 如果小于零则表示无限
     * @return 是否成功
     */
    public boolean set(String key, Object value, long time){
        try {
            redisTemplate.opsForValue().set(key, value, time);
            return true;
        }catch (Exception e){
            logger.error("redis error: ", e);
            return false;
        }
    }

    /**
     * 递增
     * @param key 键
     * @param delta 要增加几 ， 大于零
     * @return xx
     */
    public long incr(String key, long delta){
        if(delta < 0){
            throw new RuntimeException("递增因子必须大于零");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * @param key 键
     * @param delta 要减少几， 大于零
     * @return xx
     */
    public long decr(String key, long delta){
        if(delta < 0){
            throw new RuntimeException("递减因子必须大于零");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }


    //=================================================== Map ===================================================================

    /**
     * hashGet
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hashGet(String key, String item){
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有的键值
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hashMapGet(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * hashSet
     * @param key 键
     * @return x
     */
    public boolean hashMapSet(String key, Map<Object, Object> map){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        }catch (Exception e){
            logger.error("redis error: ", e);
            return false;
        }
    }

    /**
     * hashSet并且设置时间
     * @param key 键
     * @param map 值
     * @param time 时间 大于零，小于零则表示永久
     * @return x
     */
    public boolean hashMapSet(String key, Map<Object, Object> map, long time){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if(time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            logger.error("redis error: ", e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据，如果不存在则创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @return x
     */
    public boolean hashSet(String key, String item, Object value){
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        }catch (Exception e){
            logger.error("redis error: ", e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据，如果不存在则创建，并且设置时间
     * @param key 键
     * @param item 项
     * @param value 值
     * @param time 时间
     * @return 是否成功
     */
    public boolean hashSet(String key, String item, Object value, long time){
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if(time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            logger.error("redis error: ", e);
            return false;
        }
    }

    /**
     * 删除hash表中的值
     * @param key 值
     * @param item 项
     */
    public void hashDel(String key, Object... item){
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否存在该项的值
     * @param key 键
     * @param item 项
     * @return 是否存在
     */
    public boolean hashHasKey(String key, String item){
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增，如果不存在，就会创建一个，并把新增后的值返回
     * @param key 键
     * @param item 项
     * @param by 要增加几 大于0
     * @return x
     */
    public double hashIncr(String key, String item, double by){
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     * @param key 键
     * @param item 项
     * @param by 要减少几 小于0
     * @return x
     */
    public double hashDecr(String key, String item, double by){
        return redisTemplate.opsForHash().increment(key, item, -by);
    }


    //=================================================== Set ===================================================================

    /**
     * 根据key获取Set中的所有值
     * @param key 键
     * @return x
     */
    public Set<Object> setGet(String key){
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 是否存在
     * @param key 键
     * @param value 值
     * @return x
     */
    public boolean setHasKey(String key, Object value){
        try {
            redisTemplate.opsForSet().isMember(key, value);
            return true;
        }catch (Exception e){
            logger.error("redis error: ", e);
            return false;
        }
    }

    /**
     * 将数据放入set中
     * @param key 键
     * @param value 值
     * @return x
     */
    public long setSet(String key, Object... value){
        try {
            return redisTemplate.opsForSet().add(key, value);
        }catch (Exception e){
            logger.error("redis error: ", e);
            return 0;
        }
    }

    /**
     * 将数据放入set中并且设置时间
     * @param key 键
     * @param time 项
     * @param value 值
     * @return x
     */
    public long setSet(String key, long time, Object... value){
        try {
            Long count = redisTemplate.opsForSet().add(key, value);
            if(time > 0){
                expire(key, time);
            }
            return count;
        }catch (Exception e){
            logger.error("redis error: ", e);
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     * @param key 键
     * @return x
     */
    public long setGetSetSize(String key){
        try {
            return redisTemplate.opsForSet().size(key);
        }catch (Exception e){
            logger.error("redis error: ", e);
            return 0;
        }
    }

    /**
     * 移除值为value的
     * @param key 键
     * @param value 值，可多个
     * @return x
     */
    public long setRemove(String key, Object... value){
        try {
            return redisTemplate.opsForSet().remove(key, value);
        }catch (Exception e){
            logger.error("redis error: ", e);
            return 0;
        }
    }


    //=================================================== Set ===================================================================

    /**
     * 获取list缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束 （0到-1代表所有）
     * @return xx
     */
    public List<Object> listGet(String key, long start, long end){
        try {
            return redisTemplate.opsForList().range(key, start, end);
        }catch (Exception e){
            logger.error("redis error: ", e);
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     * @param key 键
     * @return x
     */
    public long listSize(String key){
        try {
            return redisTemplate.opsForList().size(key);
        }catch (Exception e){
            logger.error("redis error: ", e);
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     * @param key 键
     * @param index 索引 index>=0时， 0 表头， 1 第二元素，以此类推; index < 0时，-1 表尾， -2 倒数第二个，以此类推
     * @return 结果
     */
    public Object listGetIndex(String key, long index){
        try {
            return redisTemplate.opsForList().index(key, index);
        }catch (Exception e){
            logger.error("redis error: ", e);
            return null;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return x
     */
    public boolean listSet(String key, Object value){
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        }catch (Exception e){
            logger.error("redis error: ", e);
            return false;
        }
    }

    /**
     * 将list放入缓存并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间
     * @return x
     */
    public boolean listSet(String key, Object value, long time){
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if(time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            logger.error("redis error: ", e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param list 值
     * @return x
     */
    public boolean listSet(String key, List<Object> list){
        try {
            redisTemplate.opsForList().rightPushAll(key, list);
            return true;
        }catch (Exception e){
            logger.error("redis error: ", e);
            return false;
        }
    }

    /**
     * 将list放入缓存并设置时间
     * @param key 键
     * @param list 值
     * @param time 时间
     * @return x
     */
    public boolean listSet(String key, List<Object> list, long time){
        try {
            redisTemplate.opsForList().rightPushAll(key, list);
            if(time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            logger.error("redis error: ", e);
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     * @param key 键
     * @param value 值
     * @param index 索引
     * @return x
     */
    public boolean listUpdateIndex(String key, Object value, long index){
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        }catch (Exception e){
            logger.error("redis error: ", e);
            return false;
        }
    }

    /**
     * 移除N个值为value
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public  long listRemove(String key, long count, Object value){
        try {
            return redisTemplate.opsForList().remove(key, count, value);
        }catch (Exception e){
            logger.error("redis error: ", e);
            return 0;
        }
    }


    //=================================================== sorted Set ===================================================================

    /**
     * 向有序集合添加一个成员的
     * @param key 键
     * @param member x
     * @param score x
     * @param time x
     * @return 是否成功
     */
    public boolean zAdd(String key, Object member, double score, long time){
        try {
            redisTemplate.opsForZSet().add(key, member, score);
            if(time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            logger.error("redis error: ", e);
            return false;
        }
    }

    /**
     * 通过分数返回有序集合指定区间内的成员
     * @param key 键
     * @param minScore 最小
     * @param maxScore 最大
     * @return x
     */
    public Set<Object> zRangeByScore(String key, double minScore, double maxScore){
        try {
            return redisTemplate.opsForZSet().rangeByScore(key, minScore, maxScore);
        }catch (Exception e){
            logger.error("redis error: ", e);
            return null;
        }
    }

    /**
     * 返回有序集中，成员的分数值
     * @param key 键
     * @param member 值
     * @return x
     */
    public Double zScore(String key, Object member){
        try {
            return redisTemplate.opsForZSet().score(key, member);
        }catch (Exception e){
            logger.error("redis error: ", e);
            return null;
        }
    }

    /**
     * 返回有序集合中指定成员的索引
     * @param key 键
     * @param member 值
     * @return x
     */
    public Long zRank(String key, Object member){
        try {
            return redisTemplate.opsForZSet().rank(key, member);
        }catch (Exception e){
            logger.error("redis error: ", e);
            return null;
        }
    }

    /**
     * 迭代有序集合中的元素（包括元素成员和元素分值）
     * @param key 键
     * @return x
     */
    public Cursor<ZSetOperations.TypedTuple<Object>> zScan(String key) {
        try {
            return redisTemplate.opsForZSet().scan(key, ScanOptions.NONE);
        } catch (Exception e) {
            logger.error("redis error: ", e);
            return null;
        }
    }

}
