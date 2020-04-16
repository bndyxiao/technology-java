package com.technology.dynamic;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

/**
 * @Description:
 * @Author: huangzhb
 * @Date: 2020/4/16 10:37
 * @Version: 1.0
 * @Copyright: (C) Copyright 2020-2034, 蓝海(福建)信息科技有限公司
 */
@Configuration
@Component
public class DynamicDataSourceConfig {


    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource xiaobinMasterDataSource(){
        //return DruidDataSourceBuilder.create().build();
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource  xiaobinSlaveDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource xiaobinMasterDataSource, DataSource xiaobinSlaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("master",xiaobinMasterDataSource);
        targetDataSources.put("slave", xiaobinSlaveDataSource);
        return new DynamicDataSource(xiaobinMasterDataSource, targetDataSources);
    }



}
