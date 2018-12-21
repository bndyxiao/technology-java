package com.technology.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: huangzhb
 * @Date: 2018年12月21日 09:01:17
 * @Description:
 *
 * 引入swagger2步骤：
 * 1.已入依赖
 * 2.配置swagger2
 * 3.在controller的方法上添加相应注解
 * 4.测试http://localhost:端口号[/项目名称]/swagger-ui.html
 *
 * swagger2用法：
 *      @ApiOperation：用在方法上，说明方法的作用
 *          value: 表示接口名称
 *          notes: 表示接口详细描述
 *
 *      @ApiImplicitParams：用在方法上包含一组参数说明
 *
 *      @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
 *
 *          paramType：参数位置
 *          header 对应注解：@RequestHeader
 *          query 对应注解：@RequestParam
 *          path  对应注解: @PathVariable
 *          body 对应注解: @RequestBody
 *          name：参数名
 *          dataType：参数类型
 *          required：参数是否必须传
 *          value：参数的描述
 *          defaultValue：参数的默认值
 *      @ApiResponses：用于表示一组响应
 *
 *      @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
 *
 *          code：状态码
 *          message：返回自定义信息
 *          response：抛出异常的类
 *
 *      @ApiIgnore: 表示该接口函数不对swagger2开放展示
 */
// 开启swagger2功能
@EnableSwagger2
@Configuration
public class SwaggerConfig {


    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) // 调用apiInfo方法，创建一个ApiInfo实例，里面是展示文档页面信息内容
                .select()
                // 控制暴露出去的路径下的实例
                // 如果某个接口不想暴露，可以使用以下注解 @ApiIgnore 这样，该接口就不会暴露在swagger2的页面下
                .apis(RequestHandlerSelectors.basePackage("com.technology"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("spring boot swagger2 构建RESTful API") // 页面标题
                .contact(new Contact("bndy", "", ""))
                .version("1.0")
                .description("API 描述")
                .build();
    }

}
