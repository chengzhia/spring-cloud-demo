package com.chengzhi.web.service.impl;

import com.chengzhi.web.entity.User;
import com.chengzhi.web.mapper.UserMapper;
import com.chengzhi.web.repostitory.UserRepostitory;
import com.chengzhi.web.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ${DESCRIPTION}
 *
 * @param
 * @author RCZ
 * @create 2017-08-15 22:20
 */
@Service
public class UserServiceImpl implements UserService{

//    @Resource
//    private UserMapper userMapper;
    @Resource
    private UserRepostitory userRepostitory;

    @Override
    public Integer insertUser(User user) {
//        return userMapper.insertUser(user);
        User save = userRepostitory.save(user);
        return 1;
    }
}
