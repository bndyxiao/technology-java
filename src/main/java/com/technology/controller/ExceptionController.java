package com.technology.controller;

import com.technology.exception.CommonException;
import com.technology.exception.ExceptionEnum;
import com.technology.pojo.Brand;
import com.technology.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: huangzhb
 * @Date: 2018年12月05日 09:34:36
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("exception")
public class ExceptionController {


    @Autowired
    private BrandService brandService;


    @GetMapping("page")
    public ResponseEntity<List<Brand>> testException(HttpServletRequest request) {
        return ResponseEntity.ok(brandService.queryBrandList());
    }

}
