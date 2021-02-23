package com.sxxy.sportplay.service;

import com.sxxy.sportplay.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserService {

    // 根据用户名和密码查找用户
    public User getUserByMessage(String userName, String password);

    // 查询所有用户
    public List<User> getAllUser(String userName
            , int pageStart
            , int pageSize);

    // 获取用户个数
    public int getUserCounts(String userName);

    // 修改用户状态
    public int updateState(Integer id, boolean state);

    // 添加新用户
    public int addUser(User user);

    // 删除用户
    public int deleteUser(int id);

    // 获取更新用户
    public User getUpdateUser(int id);

    // 更新用户信息
    public int updateUser(User user);
}
