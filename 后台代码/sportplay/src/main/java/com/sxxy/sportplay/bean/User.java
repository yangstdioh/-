package com.sxxy.sportplay.bean;

import lombok.*;

/**
 * 用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    private String userName; //用户名
    private String password; // 密码
    private String email;// 邮箱
    private String role;// 角色
    private boolean state;// 状态

    public User(String userName, String password, String email, String role, boolean state) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.state = state;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
