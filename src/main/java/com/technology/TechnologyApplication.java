package com.technology;

import com.github.pagehelper.PageHelper;
import com.technology.dynamic.DynamicDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Properties;

/**
 * @author: huangzhb
 * @Date: 2018年11月13日 18:00:15
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan(basePackages="com.technology.mapper")
@Import({DynamicDataSourceConfig.class})
public class TechnologyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TechnologyApplication.class);
    }

    //配置mybatis的分页插件pageHelper
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
        pageHelper.setProperties(properties);
        return pageHelper;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TechnologyApplication.class);
    }





    /*@Bean
    Redisson redissonSentinel() {

        // 支持单机，主从，哨兵，集群等模式
        // 此为哨兵模式
        Config config = new Config();
//        config.useSentinelServers()
//                .setMasterName("mymaster")
//                .addSentinelAddress("redis://127.0.0.1:6379")
//                .setPassword("123456");
        // 使用单节点
        config.useSingleServer().setAddress("127.0.0.1:6379");

        return (Redisson) Redisson.create(config);
    }*/
}
