package com.chengzhi.web.repostitory;

import com.chengzhi.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ${DESCRIPTION}
 *
 * @param
 * @author RCZ
 * @create 2017-08-16 21:21
 */
public interface UserRepostitory extends JpaRepository<User,Long>{
}
