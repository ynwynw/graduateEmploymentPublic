package com.stu.system.service.impl;


import com.stu.handler.BusinessException;
import com.stu.response.ResultCode;
import com.stu.system.entity.Instructors;
import com.stu.system.mapper.InstructorsMapper;
import com.stu.system.service.InstructorsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorsServiceImpl extends ServiceImpl<InstructorsMapper, Instructors> implements InstructorsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private InstructorsMapper userMapper;

    @Override
    public IPage<Instructors> findInstructorsPage(Page<Instructors> page, QueryWrapper<Instructors> wrapper) {
        return this.baseMapper.findInstructorsPage(page,wrapper);
    }

    @Override
    public void loginInstructors(String phone_number, String password, Integer usertype) {
      QueryWrapper<Instructors> userWrapper = new QueryWrapper<>();
        userWrapper.eq("stel",phone_number);
        userWrapper.eq("spwd",password);
        Integer count = this.baseMapper.selectCount(userWrapper);
        System.out.println("count="+count);
        if(count == 0){
            throw new BusinessException(ResultCode.USER_ACCOUNT_NOT_EXIST.getCode(),ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
        }
    }

    @Override
    public Instructors findInstructorsById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public Instructors findInstructorByTel(String tel) {
        return userMapper.findInstructorByTel(tel);
    }

    @Override
    public List<Instructors> findInstructorsByTel(String tel) {
        return userMapper.findInstructorsByTel(tel);
    }

    @Override
    public void updateInstructors(Instructors user) {
       this.baseMapper.updateById(user);
    }

    @Override
    public void updateInstructorsByClassid(Instructors bean) {
        QueryWrapper<Instructors> wrapper = new QueryWrapper<>();
        wrapper.eq("classid", bean.getClassid());
        this.baseMapper.update(bean, wrapper);
    }

    @Override
    public void updateBymarjorid(Instructors bean) {
        QueryWrapper<Instructors> wrapper = new QueryWrapper<>();
        wrapper.eq("marjorid", bean.getMarjorid());
        this.baseMapper.update(bean, wrapper);
    }

    @Override
    public void updateBycollegeid(Instructors bean) {
        QueryWrapper<Instructors> wrapper = new QueryWrapper<>();
        wrapper.eq("collegeid", bean.getCollegeid());
        this.baseMapper.update(bean, wrapper);
    }

    @Override
    public void deleteInstructors(Integer id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public void addInstructors(Instructors user) {
        String username = user.getSname();
        QueryWrapper<Instructors> wrapper = new QueryWrapper<>();
        wrapper.eq("sname",username);
        Integer count = this.baseMapper.selectCount(wrapper);
        if(count!=0){
            throw new BusinessException(ResultCode.USER_ACCOUNT_ALREADY_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_ALREADY_EXIST.getMessage());
        }

        this.baseMapper.insert(user);
    }


}
