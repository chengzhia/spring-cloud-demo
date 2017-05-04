package com.cheng.web.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cheng.web.entity.User;
import com.cheng.web.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	
	/**
	 *  @Api：修饰整个类，描述Controller的作用
	 *	@ApiOperation：描述一个类的一个方法，或者说一个接口
	 *	@ApiParam：单个参数描述
	 *	@ApiModel：用对象来接收参数
	 *	@ApiProperty：用对象接收参数时，描述对象的一个字段
	 *	@ApiResponse：HTTP响应其中1个描述
	 *	@ApiResponses：HTTP响应整体描述
	 *	@ApiIgnore：使用该注解忽略这个API 
	 * 
	 */
	@Resource
	private UserService userService;
	
	  @ApiOperation(value="创建用户", notes="根据User对象创建用户")
	    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	    @RequestMapping(value="/helloSwagger", method={RequestMethod.GET,RequestMethod.POST})
	    public String postUser(@RequestBody User user) {
	        return "success";
	    }
	  @RequestMapping("/addUser")
	 public void addUser(){
		  	User user = new User();
			user.setUserId("3");
			user.setUserName("rcz");
			user.setUserPassWord("123123");
		 this.userService.addUser(user);
	 }
}
