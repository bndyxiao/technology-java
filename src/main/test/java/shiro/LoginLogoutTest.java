package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: huangzhb
 * @Date: 2019年02月12日 14:35:46
 * @Description:
 */
public class LoginLogoutTest {


    @Test
    public void testHelloworld() {

        // 1.获取SecurityManager工厂,此处使用ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        // 2.将securityManage工厂绑定到SecurityUtils
        SecurityUtils.setSecurityManager(factory.getInstance());

        // 3.得到Subject及创建用户名/密码身份验证Token(用户身份/凭证)
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("linyuxing", "512");

        try {
            // 4.登录,即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            // 身份认证失败
            e.printStackTrace();
        }

        // 断言用户已经登录
        Assert.assertEquals(true, subject.isAuthenticated());

        // 6.退出
        subject.logout();
    }

}
