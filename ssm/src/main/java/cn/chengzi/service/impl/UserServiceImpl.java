package cn.chengzi.service.impl;

import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.chengzi.entity.User;
import cn.chengzi.mapper.UserMapper;
import cn.chengzi.service.UserService;
import cn.chengzi.utils.FastJsonUtils;
import cn.chengzi.utils.RedisClient;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Resource
	private RedisClient<String,String> redisClient;
	
	
	public void addUser(User user) {
		this.userMapper.addUser(user);
	}

	public User queryUserByUserName(String userName) {
		return this.userMapper.queryUserByUserName(userName);
	}

	public Set<String> queryRole(String userName) {
		return null;
	}

	public Set<String> queryFunction(String userName) {
		return null;
	}

	
	
	
	public String Login(User user) {
		
		User user2 = userMapper.Login(user);
		 if (user2!=null) {
			 String token = "TOKEN";
			String json = FastJsonUtils.objectToJson(user2);
			redisClient.add(token, json);
			return token;
		}
		return null;
	}

	public User queryToken(String token) {
		String json = redisClient.get("TOKEN");
		User user = FastJsonUtils.jsonToObject(json, User.class);
		if (user==null) {
			return null;
		}
		return user;
	}
}
