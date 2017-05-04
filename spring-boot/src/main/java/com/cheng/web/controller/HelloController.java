package com.cheng.web.controller;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cheng.web.entity.User;
import com.cheng.web.service.HelloService;

@Controller
public class HelloController {
	@Resource
	private HelloService helloService;
	@Resource
	private RedisTemplate<String,Object> redisTemplate;
	
	
	@RequestMapping("/")
	@ResponseBody
	public String helloWorld(){
		helloService.queryHello("qwer");
		return "helloWorld";
	}
	@RequestMapping("/f")
	public String freeMarkerHello(ModelMap map){
		map.put("hello","FreeMarKer------HelloWorld");
		return "hello";
	}
	@RequestMapping("/redis")
	public void redisHello(){
		User user = new User();
		user.setUserId("qaq");
		user.setUserName("rcz");
		user.setUserPassWord("123123");
		String json = JSON.toJSONString(user);
		redisTemplate.opsForValue().set("rcz",json);
		String object = (String) redisTemplate.opsForValue().get("rcz");
		User object2 = JSON.parseObject(object,User.class);
		System.out.println(object2.getUserId());
	}
}
