package com.technology.service;

import com.technology.pojo.User;

/**
 * @author: huangzhb
 * @Date: 2019年01月16日 11:00:07
 * @Description:
 */
public interface UserService {

    User findUserById(Integer id);

    User findUserByUsername(String username);
}
