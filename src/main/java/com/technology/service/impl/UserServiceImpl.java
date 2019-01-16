package com.technology.service.impl;

import com.technology.exception.CommonException;
import com.technology.exception.ExceptionEnum;
import com.technology.mapper.UserMapper;
import com.technology.pojo.User;
import com.technology.service.UserService;
import com.technology.util.StringUtils;
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

    @Override
    public User findUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User findUserByUsername(String username) {

        if (StringUtils.isBlank(username)) {
            throw new CommonException(ExceptionEnum.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(username);
        return userMapper.selectOne(user);
    }
}
