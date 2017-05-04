package com.cheng.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheng.web.entity.User;
import com.cheng.web.mapper.UserMapper;
import com.cheng.web.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper userMapper; 
	
	@Override
	public void addUser(User user) {
		userMapper.addUser(user);
	}

}
