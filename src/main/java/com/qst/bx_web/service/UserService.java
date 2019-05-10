package com.qst.bx_web.service;


import com.qst.bx_web.pojo.AdminUser;
import com.qst.bx_web.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser(int pageNo, int pageSize);

    User userLogin(User user);

    int addUser(User user);

    AdminUser adminLogin(AdminUser user);

    User getUserByName(String username);

    boolean modifyUser(User user);
}
