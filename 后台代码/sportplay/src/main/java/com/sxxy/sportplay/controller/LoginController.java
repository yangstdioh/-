package com.sxxy.sportplay.controller;

import com.alibaba.fastjson.JSON;
import com.sxxy.sportplay.bean.User;
import com.sxxy.sportplay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 登录控制类
 */
@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user){

        String flag = "error";
        User us = userService.getUserByMessage(user.getUserName(),user.getPassword());
        HashMap<String,Object> res = new HashMap<>();
        if (us != null){
            flag = "ok";
        }
        res.put("flag",flag);
        res.put("user",us);

        System.out.println("user:"+us);

        String res_json = JSON.toJSONString(res);
        return res_json;
    }
}
