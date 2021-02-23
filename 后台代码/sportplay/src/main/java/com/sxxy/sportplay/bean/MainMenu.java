package com.sxxy.sportplay.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * 主导航栏
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainMenu {

    private int id;
    private String title;// 标题
    private String path;// 路径

    private List<SubMenu> subMenuList;// 分支导航栏

    public MainMenu(String title, String path, List<SubMenu> subMenuList) {
        this.title = title;
        this.path = path;
        this.subMenuList = subMenuList;
    }
}
