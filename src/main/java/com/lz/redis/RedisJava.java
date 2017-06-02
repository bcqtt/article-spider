package com.lz.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

/**
 * Function:连接本地的 Redis 服务和查看服务是否运行
 *          练习各种数据类型的简单操作
 * @author : gionee
 * Date: 2016年10月20日
 */
public class RedisJava {
    public static Jedis jedis = null;

    /**
     * 连接redis并返回redis对象
     * @return
     */
    static{
        //虚拟机ip:192.168.87.129
        jedis = new Jedis("192.168.87.132",6379);
        jedis.auth("123456");
        System.out.println("连接本地的  Redis 服务成功！");
        // 查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
    }

    /**
     * java操作redis之字符串操作
     */
    public static void strOpt(){
        jedis.set("phonebrand", "金立手机");  
        String phonebrand = jedis.get("phonebrand");
        System.out.println("从redis中获取数据：" + phonebrand);

        //向尾部追加值
        jedis.append("phonebrand", "M6升天了");
        System.out.println("从redis中获取数据：" + jedis.get("phonebrand"));

        //获取字符串长度
        System.out.println("phonebrand值的长度为：" + jedis.strlen("phonebrand"));

        //同时获得多个key的值
        jedis.set("star", "安卓啦宝宝");  
        List<String> str = jedis.mget("phonebrand","star");
        System.out.println("输出得到的多值：" + str);

        //设置多个键-值
        jedis.mset("phone","苹果手机","car","宝马汽车");
        System.out.println("输出得到的多值：" + jedis.mget("phonebrand","star","phone","car"));
    }

    public static void hashOpt(){
        //保存对象的单个 "属性:值" 形式的值
        jedis.hset("star:1", "name", "Angelbaby");

        //保存多个对象的 "属性:值" 形式的值,用map封装
        Map<String,String> starmap =new HashMap<String,String>();
        starmap.put("stature", "163");
        starmap.put("bustgirth", "81");
        jedis.hmset("star:1",starmap);

        //获取单个属性的值
        String name = jedis.hget("star:1", "name");
        String bustgirth = jedis.hget("star:1", "bustgirth");
        System.out.println(name + " 的胸围是：" + bustgirth);

        //获取多个信息
        List<String> infoList = jedis.hmget("star:1", "name","stature","bustgirth");
        System.out.println("姓名，身高，胸围 分别是：" + infoList);

        //获取全部信息
        Map<String,String> infoMap = jedis.hgetAll("star:1");
        System.out.println("姓名，身高，胸围 分别是：" + infoMap);
    }

    /**
     * java操作redis之列表类型操作
     */
    public static void listOpt(){
        //向列表左端添加元素
        long n = jedis.lpush("girlList", "林志玲","苍老师","波多野老师","Angelababy");
        System.out.println("向girlList添加元素：" + n + " 个");
        //取列表元素
        System.out.println("girlList列表为：" + jedis.lrange("girlList", 0, -1));

        //从两端弹出元素
        System.out.println("从左边弹出元素:" + jedis.lpop("girlList"));
        System.out.println("从右边弹出元素:" + jedis.rpop("girlList"));
        System.out.println("弹出元素后结果为：" + jedis.lrange("girlList", 0, -1));

        //删除前n个元素中值为“苍老师”的元素，返回值是实际删除元素的个数
        System.out.println("成功删除元素个数：" + jedis.lrem("girlList", n, "波多野老师"));
        System.out.println("结果为：" + jedis.lrange("girlList", 0, -1));

        //通过索引取值
        System.out.println("获得元素：" + jedis.lindex("girlList", 0));
        //通过索引赋值
        System.out.println("赋值元素：" + jedis.lset("girlList", 0, "Angelababy"));
        System.out.println("结果为：" + jedis.lrange("girlList", 0, -1));

        //只保留指定范围内的元素
        System.out.println("保留指定范围内的元素：" + jedis.ltrim("girlList", 0, 1));
        System.out.println("结果为：" + jedis.lrange("girlList", 0, -1));

        //向列表中插入元素
        //jedis.linsert("girlList", BinaryClient.LIST_POSITION.BEFORE, "杨幂", "苍老师");
        System.out.println("结果为：" + jedis.lrange("girlList", 0, -1));

        //元素转移列表
        jedis.rpoplpush("girlList", "girlList2");
        System.out.println("结果为：" + jedis.lrange("girlList2", 0, -1));
    }

    /**
     * 剩下的类型不做演示了，都差不多
     * @param args
     */

    public static void main(String[] args) {

        strOpt();
        hashOpt();

    }
}