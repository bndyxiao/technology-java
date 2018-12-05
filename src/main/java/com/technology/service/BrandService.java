package com.technology.service;

import com.technology.pojo.Brand;

import java.util.List;

/**
 * @author: huangzhb
 * @Date: 2018年12月05日 10:07:31
 * @Description:
 */
public interface BrandService {

    /**
     * 查询品牌列表
     * @return 品牌列表
     */
    List<Brand> queryBrandList();
}
