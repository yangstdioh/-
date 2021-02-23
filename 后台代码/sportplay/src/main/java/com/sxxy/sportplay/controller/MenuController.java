package com.sxxy.sportplay.controller;

import com.alibaba.fastjson.JSON;
import com.sxxy.sportplay.bean.MainMenu;
import com.sxxy.sportplay.service.MainMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 导航栏控制类
 */
@RestController
public class MenuController {

    @Autowired
    private MainMenuService mainMenuService;

    // 获取所有menu菜单
    @RequestMapping("/getAllMenus")
    public String getAllMenus(){
        HashMap<String,Object> date = new HashMap<>();

        // 返回状态
        int state = 400;

        // 查询所有菜单栏
        List<MainMenu> mainMenus = mainMenuService.getMainMenus();

        if (mainMenus != null){
            date.put("mainMenus",mainMenus);
            date.put("state",200);
        } else {
            date.put("state",400);
        }

        String s = JSON.toJSONString(date);

        return s;
    }
}
