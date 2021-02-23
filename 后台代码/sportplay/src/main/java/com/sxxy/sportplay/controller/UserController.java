package com.sxxy.sportplay.controller;


import com.alibaba.fastjson.JSON;
import com.sxxy.sportplay.bean.QueryInfo;
import com.sxxy.sportplay.bean.User;
import com.sxxy.sportplay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // 查询所有用户
    @RequestMapping("/getUserList")
    public String getUserList(QueryInfo queryInfo){

        String userName = "%"+queryInfo.getQuery()+"%";

        // 获取用户数
        int userCount = userService.getUserCounts(userName);
        // 算出开始数
        int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();

        // 查询用户
        List<User> users = userService.getAllUser(userName,pageStart,queryInfo.getPageSize());

        HashMap<String,Object> data = new HashMap<>();

        data.put("userCount",userCount);

        data.put("users",users);

        String res = JSON.toJSONString(data);

        return  res;
    }

    // 更改用户状态
    @RequestMapping("/updateState")
    public String updateState(@RequestParam("id")Integer id,
                              @RequestParam("state")boolean state){

        int i = userService.updateState(id,state);

        return i > 0 ? "success" :"error";
    }

    // 添加新用户
    @RequestMapping("/addUser")
    public String addUser(@RequestBody User user){

        // 设置角色
        user.setRole("普通用户");

        // 设置状态
        user.setState(false);

        int i = userService.addUser(user);

        return i > 0 ? "success" : "error";
    }

    // 删除用户
    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") int id){
        int result = userService.deleteUser(id);
        return result > 0 ? "success" : "error";
    }

    // 查询用户
    @RequestMapping("/getUpdateUser")
    public String getUpdateUser(int id){
        User user = userService.getUpdateUser(id);
        String res = JSON.toJSONString(user);
        return res;
    }

    // 修改用户信息
    @RequestMapping("/updateUser")
    public String updateUser(@RequestBody User user){

        try{
            int i = userService.updateUser(user);
            return i > 0 ? "success" : "error";
        } catch (Exception e){
            return "error";
        }
    }
}
