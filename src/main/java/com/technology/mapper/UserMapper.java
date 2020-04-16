package com.technology.mapper;

import com.technology.common.BaseMapper;
import com.technology.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author: huangzhb
 * @Date: 2019年01月16日 11:01:52
 * @Description:
 */
public interface UserMapper extends BaseMapper<User, Integer> {

    @Select("select * from user where id = #{id}")
    User findUserById(@Param("id") Integer id);
    @Select("select * from user where id = #{id}")
    User slaveById(@Param("id")Integer id);
    @Select("select * from user where id = #{id}")
    User defaultById(@Param("id")Integer id);
}
