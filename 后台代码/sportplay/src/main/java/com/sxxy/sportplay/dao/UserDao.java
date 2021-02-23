package com.sxxy.sportplay.dao;

import com.sxxy.sportplay.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao {

    // 根据用户名和密码查找用户
    public User getUserByMessage(@Param("userName") String userName, @Param("password") String password);

    // 查询所有用户
    public List<User> getAllUser(@Param("userName")String userName
                                 ,@Param("pageStart") int pageStart
                                 ,@Param("pageSize") int pageSize);

    // 获取用户个数
    public int getUserCounts(@Param("userName")String userName);

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
