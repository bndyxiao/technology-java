package com.technology.service.impl;

import com.technology.mapper.UserMapper;
import com.technology.pojo.User;
import com.technology.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: huangzhb
 * @Date: 2019年01月16日 11:00:57
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


    @com.technology.dynamic.DataSource(name = "master")
    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @com.technology.dynamic.DataSource(name = "slave")
    @Override
    public User slaveById(Integer id) {
        return userMapper.slaveById(id);
    }

    @Override
    public User defaultById(Integer id) {
        return userMapper.defaultById(id);
    }
}
