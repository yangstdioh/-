package com.sxxy.sportplay.service.impl;

import com.sxxy.sportplay.bean.User;
import com.sxxy.sportplay.dao.UserDao;
import com.sxxy.sportplay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User getUserByMessage(String userName, String password) {
        return userDao.getUserByMessage(userName,password);
    }

    @Override
    // 查询所有用户
    public List<User> getAllUser(String userName, int pageStart, int pageSize) {
        return userDao.getAllUser(userName,pageStart,pageSize);
    }

    @Override
    // 获取用户数量
    public int getUserCounts(String userName) {
        return userDao.getUserCounts(userName);
    }

    @Override
    public int updateState(Integer id, boolean state) {
        return userDao.updateState(id,state);
    }

    // 添加新用户
    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    @Override
    public User getUpdateUser(int id) {
        return userDao.getUpdateUser(id);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }
}
