package com.sxxy.sportplay.test;

import com.sxxy.sportplay.bean.User;

public class UserTest {

    public static void main(String[] args) {
        User user = new User();
        user.setId(12);
        System.out.println(user.getId());
    }
}
