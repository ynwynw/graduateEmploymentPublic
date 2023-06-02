package com.stu.system.service;

import com.stu.system.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UserService extends IService<User> {

    IPage<User> findUserPage(Page<User> page, QueryWrapper<User> wrapper);
    void addUser(User user);
    void loginUser(String phone_number, String password, Integer usertype);
    User findUserById(Integer id);
    User findUserByTel(String tel);
    List<User> findUsersByTel(String tel);
    void updateUser(User user);
    void updateUsersByClassid(User bean);
    void updateBymarjorid(User bean);
    void updateBycollegeid(User bean);
    void deleteUser(Integer id);
}
