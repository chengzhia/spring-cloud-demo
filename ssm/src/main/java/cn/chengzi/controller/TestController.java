package cn.chengzi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.chengzi.entity.User;

import com.alibaba.fastjson.JSONObject;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
@Api(value="qaq")
@RequestMapping("test")
@Controller
public class TestController {

	/**
	 * consumes:方法仅处理application/json格式数据 
	 * produces:返回application/json格式数据
	 */
	@ApiOperation(value="qaq",notes="qq")
	@RequestMapping(value = "demoOne", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User demoOne(HttpServletRequest request, HttpServletResponse response) {
			User user = new User();
			user.setName("你是我的眼");
			user.setPassword("回忆再美好也只是曾经");
		return user;
	}
}
