package shiro.chapter2;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * 单realm配置
 * @author: huangzhb
 * @Date: 2019年02月12日 15:01:11
 * @Description:
 */
public class MyRealm1 implements Realm {

    /**
     * 返回一个唯一的Realm名字
     * @return
     */
    @Override
    public String getName() {
        return "myrealm1";
    }

    /**
     * 判断此realm是否支持此token
     * @param authenticationToken
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    /**
     * 获取token认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        if (!"zhang".equals(username)) {
            // 如果用户名错误
            throw new UnknownAccountException();
        }

        if (!"512".equals(password)) {
            // 如果密码错误
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
