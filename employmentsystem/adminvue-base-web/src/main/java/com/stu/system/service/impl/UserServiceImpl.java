package com.stu.system.service.impl;


import com.stu.handler.BusinessException;
import com.stu.response.ResultCode;
import com.stu.system.entity.User;
import com.stu.system.mapper.UserMapper;
import com.stu.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;


    @Override
    public IPage<User> findUserPage(Page<User> page, QueryWrapper<User> wrapper) {
        return this.baseMapper.findUserPage(page,wrapper);
    }

    @Override
    public void loginUser(String phone_number, String password, Integer usertype) {
      QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper.eq("stel",phone_number);
        userWrapper.eq("spwd",password);
        Integer count = this.baseMapper.selectCount(userWrapper);
        System.out.println("count="+count);
        if(count == 0){
            throw new BusinessException(ResultCode.USER_ACCOUNT_NOT_EXIST.getCode(),ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
        }
    }

    @Override
    public User findUserById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public User findUserByTel(String tel) {
        return userMapper.findUserByTel(tel);
    }

    @Override
    public List<User> findUsersByTel(String tel) {
        return userMapper.findUsersByTel(tel);
    }

    @Override
    public void updateUser(User user) {
       this.baseMapper.updateById(user);
    }

    @Override
    public void updateUsersByClassid(User bean) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("classid", bean.getClassid());
        this.baseMapper.update(bean, wrapper);
    }

    @Override
    public void updateBymarjorid(User bean) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("marjorid", bean.getMarjorid());
        this.baseMapper.update(bean, wrapper);
    }

    @Override
    public void updateBycollegeid(User bean) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("collegeid", bean.getCollegeid());
        this.baseMapper.update(bean, wrapper);
    }

    @Override
    public void deleteUser(Integer id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public void addUser(User user) {
        String username = user.getSname();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("sname",username);
        Integer count = this.baseMapper.selectCount(wrapper);
        if(count!=0){
            throw new BusinessException(ResultCode.USER_ACCOUNT_ALREADY_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_ALREADY_EXIST.getMessage());
        }

        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.baseMapper.insert(user);
    }


}
