package common.apigateway;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: huangzhb
 * @Date: 2019年02月01日 17:31:29
 * @Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface APIMapping {

    String value();
    // 登录检测
    boolean checkLogin() default  false;
}
