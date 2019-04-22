package com.qst.bx_web.dao;

import com.qst.bx_web.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    List<User> getAllUser();
}