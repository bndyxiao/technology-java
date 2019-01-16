package com.technology.controller;

import com.alibaba.fastjson.JSONObject;
import com.technology.common.jwt.UserLoginToken;
import com.technology.exception.CommonException;
import com.technology.exception.ExceptionEnum;
import com.technology.pojo.User;
import com.technology.service.UserService;
import com.technology.util.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: huangzhb
 * @Date: 2019年01月16日 11:03:40
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<JSONObject> login(@RequestBody User u) {

        User user = userService.findUserByUsername(u.getUsername());

        if (user == null) {
            throw new CommonException(ExceptionEnum.BAD_REQUEST);
        } else {

            if (!user.getPassword().equals(u.getPassword())) {
                throw new CommonException(ExceptionEnum.BAD_REQUEST);
            } else {
                String token = JwtUtil.getToken(user);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("token", token);
                return ResponseEntity.ok(jsonObject);
            }

        }
    }


    @UserLoginToken
    @GetMapping("getMessage")
    public String getMessage() {
        return "您已通过验证";
    }

}
