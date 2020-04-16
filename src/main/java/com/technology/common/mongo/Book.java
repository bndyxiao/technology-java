package com.technology.common.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: huangzhb
 * @Date: 2020/4/11 14:04
 * @Version: 1.0
 * @Copyright: (C) Copyright 2020-2034, 蓝海(福建)信息科技有限公司
 */
@Data
public class Book {

    @Id
    private String id;
    private Integer price;
    private String name;
    private String info;
    private Date createTime;
    private String publish;

    private List<List<double[]>> coordinates;
}
