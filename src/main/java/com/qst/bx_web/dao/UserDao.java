package com.qst.bx_web.dao;

import com.qst.bx_web.pojo.AdminUser;
import com.qst.bx_web.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    List<User> getAllUser(Map<String,Integer> map);

    User userLogin(String username,String password);

    void addUser(User user);

    User getUserByName(String username);

    AdminUser adminLogin(String username,String password);

    int modifyUser(User user);
}