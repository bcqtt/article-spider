package com.lz.art.test;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;

/**
 * Function:连接本地的 Redis 服务和查看服务是否运行
 * @author : laizhiwen
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
		jedis = new Jedis("192.168.87.129",6379);
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
	
    public static void main(String[] args) {
        
    	strOpt();
        
    }
}
