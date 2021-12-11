package JedisTest;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.MultiKeyPipelineBase;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.CheckedOutputStream;

public class TEST {

    /**
     * Sting 数据结构操作
     *  set  get
     */
    @Test
    public void testString() {
        // 1. 获取连接
        Jedis jedis = new Jedis(); // 空参构造默认值为 localhost  6379端口号

        // 2. 操作
        // 存储
        jedis.set("username", "Paul");
        // 获取
        String username = jedis.get("username");
        System.out.println(username);
        // 可以使用setex()方法存储, 可以指定过期时间
        jedis.setex("activeCode", 10, "hello");  // 10s后自动删除该键值对

        // 3. 关闭连接
        jedis.close();
    }

    /**
     * Hash 数据结构操作
     *  hset  hget  hgetAll
     */
    @Test
    public void testHash() {
        // 1. 获取连接
        Jedis jedis = new Jedis(); // 空参构造默认值为 localhost  6379端口号

        // 2. 操作
        // 存储
        jedis.hset("User", "Gender", "female");
        jedis.hset("User", "Age", "17");
        jedis.hset("User", "name", "Mike");
        // 获取hash中的所有数据
        Map<String, String> user = jedis.hgetAll("User");
        System.out.println(user);

        // 3. 关闭连接
        jedis.close();
    }

    /**
     * list 数据结构操作
     *  lpush rpush lpop rpop lrange
     */
    @Test
    public void testList() {
        // 1. 获取连接
        Jedis jedis = new Jedis(); // 空参构造默认值为 localhost  6379端口号

        // 2. 操作
        // 存储
        jedis.del("myList");
        jedis.lpush("myList", "a", "b", "c");
        jedis.rpush("myList", "d", "e", "f");
        // 弹出
        jedis.lpop("myList");
        jedis.rpop("myList");

        // list范围获取
        List<String> myList = jedis.lrange("myList", 0, -1);
        System.out.println(myList);

        // 3. 关闭连接
        jedis.close();
    }

    /**
     * set 数据结构操作
     *  sadd smembers srem
     */
    @Test
    public void testSet() {
        // 1. 获取连接
        Jedis jedis = new Jedis(); // 空参构造默认值为 localhost  6379端口号

        // 2. 操作
        // 存储
        jedis.sadd("mySet", "java", "c++", "PHP", "Go");
        jedis.srem("mySet", "c++");
        Set<String> mySet = jedis.smembers("mySet");
        System.out.println(mySet);

        // 3. 关闭连接
        jedis.close();
    }

    /**
     * sortedset 数据结构操作
     *  zadd
     */
    @Test
    public void testSortedset() {
        // 1. 获取连接
        Jedis jedis = new Jedis(); // 空参构造默认值为 localhost  6379端口号

        // 2. 操作
        // 存储
        jedis.zadd("sortset", 1, "Windows");
        jedis.zadd("sortset", 3, "linux");
        jedis.zadd("sortset", 2, "Mac");
        // 获取
        Set<String> sortset = jedis.zrange("sortset", 0, -1);
        System.out.println(sortset);

        // 3. 关闭连接
        jedis.close();
    }

    /**
     * Jedis连接池
     */
    @Test
    public void testPool() {
        // 1.创建Jedis连接池对象
        JedisPool jedisPool = new JedisPool();

        // 2.获取
        Jedis jedis = jedisPool.getResource();

        // 3.使用
        jedis.set("username", "Mike");

        // 4.关闭, 归还到连接池中
        jedis.close();
    }

}


