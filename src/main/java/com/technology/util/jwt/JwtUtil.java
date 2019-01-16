package com.technology.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import com.technology.pojo.User;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author: huangzhb
 * @Date: 2019年01月16日 09:53:49
 * @Description:
 */
public class JwtUtil {
    /**
     * Algorithm.HMAC256():使用HS256生成token,密钥则是用户的密码，唯一密钥的话可以保存在服务端
     * withAudience()存入需要保存在token的信息，这里我把用户ID存入token中
     * @param user
     * @return
     */
    public static String getToken(User user) {

        //String token = "";
        //token = JWT.create().withAudience(user.getId().toString()).sign(Algorithm.HMAC256(user.getPassword()));
        return createToken(user);
    }


    private static String createToken(User user) {

        String token = "";
        // 签发时间
        Date iatDate = new Date();

        // 设置过期时间
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 10);
        Date expiresDate = nowTime.getTime();

        Map<String, Object> map = Maps.newHashMap();
        map.put("alg", "Hs256");
        map.put("typ", "JWT");

        token = JWT.create()
                          .withHeader(map)
                          .withAudience(user.getId().toString())
                          .withExpiresAt(expiresDate)
                          .withIssuedAt(iatDate)
                          .sign(Algorithm.HMAC256(user.getPassword()));

        return token;
    }

    public static Map<String, Claim> verifyToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("123456")).build();

        DecodedJWT jwt = null;

        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException("登录凭证已失效,请重新登录");
        }

        return jwt.getClaims();
    }

    public static void main(String[] args) {
        Map<String, Claim> stringClaimMap = verifyToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiI4IiwiZXhwIjoxNTQ3NjMyMDc4LCJpYXQiOjE1NDc2MzE0Nzh9.BZdO5x1WowXoMLCIG3RaPku_rI0kZ4TiEF5hOltXC8E");

        for (Map.Entry<String, Claim> item : stringClaimMap.entrySet()) {

            System.out.println(item.getKey() + "===" + item.getValue().asDate());
        }
    }
}
