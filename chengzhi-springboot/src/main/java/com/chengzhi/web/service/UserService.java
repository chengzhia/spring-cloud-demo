package com.chengzhi.web.service;

import com.chengzhi.web.entity.User;
import com.chengzhi.web.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ${DESCRIPTION}
 *
 * @param
 * @author RCZ
 * @create 2017-08-15 22:20
 */
public interface UserService {

    Integer insertUser(User user);
}
