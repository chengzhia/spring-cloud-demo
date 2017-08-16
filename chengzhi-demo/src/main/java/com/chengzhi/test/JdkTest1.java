package com.chengzhi.test;

import com.chengzhi.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 阮承志 on 2017/7/7.
 */
public class JdkTest1 {
    public static void main(String args[]) {
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUserId("cz"+i);
            user.setUserCode("cz"+i);
            user.setUserEmail("cz"+i);
            user.setUserName("cz"+i);
            user.setUserPassword("cz"+i);
            list.add(user);
        }
            list.forEach(str  -> System.out.println(str.getUserCode()));
    }
}
