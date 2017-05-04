package cn.chengzi.mapper;

import cn.chengzi.entity.User;

public interface UserMapper {

	void addUser(User user);

	User queryUserByUserName(String userName);

	User Login(User user);

}
