package redis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lz.art.pojo.Article;
import com.lz.study.redis.ArticleRedisDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-config.xml"}) 
public class RedisTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	ArticleRedisDao articleRedisDao;
	
	//@Test 
    public void testSaveArticle() { 
        Article art = new Article(); 
        art.setId("1"); 
        art.setTitle("今天天气非常好！你吃饭了吗！"); 
        boolean res = articleRedisDao.saveArticle(art);
        Assert.assertTrue(res); 
    } 
    
    @Test 
    public void testUpdateArticle() { 
    	Article art = new Article(); 
    	art.setId("1"); 
    	art.setTitle("天气好，去春游！"); 
        boolean res = articleRedisDao.updateArticle(art);
        Assert.assertTrue(res); 
    }
    
    //@Test 
    public void testFindArticle() { 
    	Article art  = articleRedisDao.findArticle("1");
    	if(art==null){
    		System.out.println("结果：" + art);
    	}else{
    		System.out.println(art.getId() + "-" + art.getTitle() ); 
    	}
    }
    
    //@Test 
    public void testDelArticle() { 
    	boolean res = articleRedisDao.deleteArticle("1");
    	Assert.assertTrue(res); 
    }
    
    
}
