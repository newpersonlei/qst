package com.qst.bx_web.service.impl;

import com.qst.bx_web.dao.UserDao;
import com.qst.bx_web.pojo.AdminUser;
import com.qst.bx_web.pojo.User;
import com.qst.bx_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAllUser(int pageNo, int pageSize) {
        Map<String, Integer> map = new HashMap<>();
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);
        List<User> list = userDao.getAllUser(map);
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
            User u = userDao.getUserByName(user.getUsername());
            if (u == null) {
                userDao.addUser(user);
                res = user.getUserid();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public AdminUser adminLogin(AdminUser user) {
        user = userDao.adminLogin(user.getUsername(), user.getPassword());
        return user;
    }

    @Override
    public User getUserByName(String username) {
        User user = null;
        try {
            user = userDao.getUserByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean modifyUser(User user) {
        int res = userDao.modifyUser(user);
        return res > 0 ? true : false;
    }
}
