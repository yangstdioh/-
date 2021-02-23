package com.sxxy.sportplay.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 分支导航
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubMenu {

    private int id;
    private String title;// 标题
    private String path;// 路径

    public SubMenu(String title, String path) {
        this.title = title;
        this.path = path;
    }
}
