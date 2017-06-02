package com.lz.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import com.lz.art.pojo.Article;

import redis.clients.util.SafeEncoder;

@Repository("articleRedisDao")
public class ArticleRedisDao extends BaseRedisDao<String, Article> {
	
	public boolean saveArticle(final Article article){
		boolean res = (boolean) redisTemplate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection conn) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] key = serializer.serialize("article." + article.getId());
                key = SafeEncoder.encode("article." + article.getId());
                byte[] value = serializer.serialize(article.getTitle());
                //set not exits
                return conn.setNX(key, value);
			}
		});
		return res;
	}
	
	public boolean updateArticle(final Article article){
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection conn) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] key = serializer.serialize("article." + article.getId());
                byte[] value = serializer.serialize(article.getTitle());
                conn.set(key, value);
				return true;
			}
		});
		return result;
	}
	
	public Article findArticle(final String id){
		Article article = redisTemplate.execute(new RedisCallback<Article>() {
			@Override
			public Article doInRedis(RedisConnection conn) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] key = serializer.serialize("article." + id);
				byte[] value = conn.get(key);
				if (value == null) { 
                    return null; 
                }
				String title = serializer.deserialize(value);
				Article article = new Article();
				article.setId(id);
				article.setTitle(title);
				return article;
			}
		});
		return article;
	}
	
	public boolean deleteArticle(final String id) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() { 
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException { 
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer(); 
                byte[] key  = serializer.serialize("article." + id); 
                //delete
                connection.del(key);
                return true; 
            } 
        }); 
        return result;
    }
	

}
