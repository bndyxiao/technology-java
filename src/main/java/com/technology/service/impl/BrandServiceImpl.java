package com.technology.service.impl;

import com.technology.exception.CommonException;
import com.technology.exception.ExceptionEnum;
import com.technology.mapper.BrandMapper;
import com.technology.pojo.Brand;
import com.technology.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author: huangzhb
 * @Date: 2018年12月05日 10:07:45
 * @Description:
 */
@Slf4j
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;


    @Override
    public List<Brand> queryBrandList() {


        List<Brand> list = brandMapper.selectAll();

        if (CollectionUtils.isEmpty(list)) {
            throw new CommonException(ExceptionEnum.BRAND_NO_FOUND);
        }

        return list;
    }
}
