package com.technology.common;

import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 基础接口Mapper
 * @author: huangzhb
 * @Date: 2018年12月19日 16:18:40
 * @Description:
 */
public interface BaseMapper<T, M> extends Mapper<T>, IdListMapper<T, M>, InsertListMapper<T> {
}
