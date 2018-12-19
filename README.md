# technology-java


2018-12-05: 增加统一异常处理<br/> 

## 外置tomcat打包
**1.修改pom.xml文件打包方式为：war**

```xml
<packaging>war</packaging>
```

**2.移除spring boot自带的嵌入式tomcat**

修改pom.xml文件，移除自带的tomcat插件

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <!-- 移除嵌入式tomcat插件 -->
    <exclusions>
        <exclusion>
        	<groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

**3.添加servlet-api依赖**

```xml
<dependency>
	<groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.1.0</version>
    <scope>provided</scope>
</dependency>
```

**4.修改启动类，并重写初始化方法**

在springboot中我们平常用main方法启动的方式，都有一个SpringBootApplication的启动类，类似代码如下：

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

而现在需要类似于web.xml的配置方式来启动spring应用，为此，我们在Application类的同级添加一个SpringBootStartApplication类，其代码如下：(或者直接Application直接继承SpringBootServletInitializer)

```java
// 修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法
public class SpringBootStartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里一定要指向原先用main方法执行的Application启动类
        return builder.sources(Application.class);
    }
}
```

**5.部署到外部的tomcat容器并验证**

maven打包完成后，把war包部署到tomcat的webapps目录下，启动tomcat，然后验证。































