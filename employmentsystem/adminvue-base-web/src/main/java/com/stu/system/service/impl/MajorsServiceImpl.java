package com.stu.system.service.impl;


import com.stu.handler.BusinessException;
import com.stu.response.ResultCode;
import com.stu.system.entity.Majors;
import com.stu.system.mapper.MajorsMapper;
import com.stu.system.service.MajorsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorsServiceImpl extends ServiceImpl<MajorsMapper, Majors> implements MajorsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MajorsMapper majorsMapper;


    @Override
    public IPage<Majors> findMajorsPage(Page<Majors> page, QueryWrapper<Majors> wrapper) {
        return this.baseMapper.findMajorsPage(page,wrapper);
    }

    @Override
    public List<Majors> findMajorsByCbianhao(String cbianhao) {
        return majorsMapper.findMajorsByCbianhao(cbianhao);
    }

    @Override
    public List<Majors> findMajorsByCname(String cname) {
        QueryWrapper<Majors> wrapper = new QueryWrapper<>();
        wrapper.eq("cname",cname);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public void updateMajors(Majors majors) {
       this.baseMapper.updateById(majors);
    }

    @Override
    public void deleteMajors(Integer id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public List<Majors> getMajors() {
        return majorsMapper.getMajors();
    }

    @Override
    public void addMajors(Majors majors) {
        String username = majors.getCname();
        QueryWrapper<Majors> wrapper = new QueryWrapper<>();
        wrapper.eq("cname",username);
        Integer count = this.baseMapper.selectCount(wrapper);
        if(count!=0){
            throw new BusinessException(ResultCode.USER_ACCOUNT_ALREADY_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_ALREADY_EXIST.getMessage());
        }
        this.baseMapper.insert(majors);
    }


}
