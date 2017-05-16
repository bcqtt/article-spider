package redis;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.lz.art.pojo.Article;
import com.lz.art.util.SerializeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

/**************************************************
 * uri方式配置的jedis  测试
 **************************************************/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-config.xml"}) 
public class ReidsUriTest extends AbstractJUnit4SpringContextTests  {
	
	@Autowired
	JedisShardInfo jedisShardInfo;
	
	//@Test
    public void testJedisShardInfoSet(){
    	Jedis jedis = new Jedis(jedisShardInfo);
    	String result = jedis.set("girl", "BaBy8");
    	System.out.println("Set方法结果：" + result);
    }
    
    @Test
    public void testSaveObject(){
    	/*******单机测试**********
    	 * 【序列化List对象，整存整取】
    	 * redis写10万条数据耗时：280
			全部获取：100000
			redis取10万条数据耗时：202
			
			redis写100万条数据耗时：4142
			全部获取：1000000
			redis取10万条数据耗时：1486
    	 */
    	Jedis jedis = new Jedis(jedisShardInfo);
    	List<Article> artList = new ArrayList<Article>();
    	for(int i=0;i<1000000;i++){
    		Article a = new Article();
    		a.setId("id-" + i);
    		a.setTitle("title-" + i);
    		artList.add(a);
    	}
    	
    	long start = System.currentTimeMillis();
	    String key = "TestSetOpt";  
        jedis.set(key.getBytes(), SerializeUtil.serialize(artList));  
        
        long stored = System.currentTimeMillis();
    	System.out.println("redis写10万条数据耗时：" + (stored - start));
        
        
        //验证  
        byte[] in = jedis.get(key.getBytes());  
        List<Article> list = SerializeUtil.unserializeForList(in);  
//        for(Article obj : list){  
//           System.out.println("测试Set操作 article title 是:" + obj.getTitle());  
//        }  
        long end = System.currentTimeMillis();
    	System.out.println("全部获取：" + list.size());
    	System.out.println("redis取10万条数据耗时：" + (end - stored));
    	
    }
    
    //@Test
    public void testSaveList(){
    	/**** 单机测试  ************
    	redis存取10万条数据测试
    	redis写10万条数据耗时：35727
    	全部获取：100000
    	redis取10万条数据耗时：251

    	redis存取100万条数据测试
    	redis写10万条数据耗时：316339
    	全部获取：1000000
    	redis取10万条数据耗时：2243
    	*************************/
    	
    	Jedis jedis = new Jedis(jedisShardInfo);
    	List<Article> artList = new ArrayList<Article>();
    	System.out.println("redis存取10万条数据测试");
    	long start = System.currentTimeMillis();
    	for(int i=0;i<100000;i++){
    		Article a = new Article();
    		a.setId("id-" + i);
    		a.setTitle("title-" + i);
    		String objData = JSON.toJSONString(a);
			jedis.rpush("article", objData);
			//System.out.println("保存对象数据：" + objData);
    	}
    	long stored = System.currentTimeMillis();
    	System.out.println("redis写10万条数据耗时：" + (stored - start));
    	
    	List<String> list = jedis.lrange("article", 0, -1);
    	for(String str:list){
    		//System.out.println("[获取对象数据]：" + str);
    		Article art = JSON.parseObject(str,Article.class);
    		//System.out.println("标题：" + art.getTitle());
    	}
    	long end = System.currentTimeMillis();
    	System.out.println("全部获取：" + list.size());
    	System.out.println("redis取10万条数据耗时：" + (end - stored));
    	
    }
    

}
