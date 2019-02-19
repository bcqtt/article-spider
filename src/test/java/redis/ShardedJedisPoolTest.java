package redis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lz.util.RedisUtil;

/**************************************************
 * shardedJedisPool 测试
 **************************************************/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-config.xml"}) 
public class ShardedJedisPoolTest extends AbstractJUnit4SpringContextTests  {
	
	@Autowired
	RedisUtil redisUtil;
	
    @Test
    public void testSet(){
		boolean result = redisUtil.set("article.2","redis数据插入");
		Assert.assertTrue(result); 
    }
    
    @Test
    public void testGet(){
    	String result = (String) redisUtil.get("article.2","");
    	System.out.println("redis中取值：" + result);
    }

}
