package cn.chengzi.service;

import java.util.Set;

import cn.chengzi.entity.User;

public interface UserService {

	void addUser(User user);

	User queryUserByUserName(String userName);

	Set<String> queryRole(String userName);

	Set<String> queryFunction(String userName);

	String Login(User user);

	User queryToken(String token);

}
