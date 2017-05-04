package cn.chengzi.utils;

import java.io.Serializable;
import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

public class RedisClient<K extends Serializable, V extends Serializable> {

	@Resource
    protected RedisTemplate<K, V> redisTemplate;
	
	/**
	 * 添加
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(final String key, final String value) {
		boolean resultBoolean = false;
		if(redisTemplate != null) {
			resultBoolean = redisTemplate.execute(new RedisCallback<Boolean>() {  
				public Boolean doInRedis(RedisConnection connection)  
						throws DataAccessException {  
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys  = serializer.serialize(key);
					byte[] values = serializer.serialize(value);  
					return connection.setNX(keys, values);  
				}  
			});  
		}else{
			System.out.println(redisTemplate == null);
		}
		return resultBoolean;
    }
	
	/**
     * 根据key获取对象
     */
    public String get(final String key) {
    	String resultStr = null;
    	if(redisTemplate != null) {
    		resultStr = redisTemplate.execute(new RedisCallback<String>() {
    			public String doInRedis(RedisConnection connection)  
    					throws DataAccessException {  
    				RedisSerializer<String> serializer = getRedisSerializer();  
    				byte[] keys = serializer.serialize(key);  
    				byte[] values = connection.get(keys);  
    				if (values == null) {  
    					return null;  
    				}  
    				String value = serializer.deserialize(values);  
    				return value;  
    			}  
    		});  
    	}
        return resultStr;  
    }  
	
	 /** 
     * 获取 RedisSerializer 
     * 
     */  
    protected RedisSerializer<String> getRedisSerializer() {  
        return redisTemplate.getStringSerializer();  
    }  

	public RedisTemplate<K, V> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
}
