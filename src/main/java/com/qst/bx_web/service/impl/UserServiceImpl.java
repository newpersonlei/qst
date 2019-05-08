package com.qst.bx_web.service.impl;

import com.qst.bx_web.dao.UserDao;
import com.qst.bx_web.pojo.User;
import com.qst.bx_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Cacheable(value = "user", key = "#root.methodName")
    public List<User> getAllUser() {
        List<User> list = userDao.getAllUser();
        return list;
    }

    @Override
    public User userLogin(User user) {
        user = userDao.userLogin(user.getUsername(), user.getPassword());
        return user;
    }

    @Override
    public int addUser(User user) {
        int res = 0;
        try {
            userDao.addUser(user);
            res = user.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
