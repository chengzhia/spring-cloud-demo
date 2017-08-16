package com.chengzhi.web.controller;

import com.chengzhi.web.entity.User;
import com.chengzhi.web.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ${DESCRIPTION}
 *
 * @param
 * @author RCZ
 * @create 2017-08-15 22:16
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    /**
     *
     * @return
     */
    @RequestMapping("/insertUser")
    public Integer insertUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        return this.userService.insertUser(user);
    }
}
