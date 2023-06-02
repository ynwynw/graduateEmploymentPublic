package com.stu.system.service.impl;


import com.stu.handler.BusinessException;
import com.stu.response.ResultCode;
import com.stu.system.entity.Colleges;
import com.stu.system.mapper.CollegesMapper;
import com.stu.system.service.CollegesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegesServiceImpl extends ServiceImpl<CollegesMapper, Colleges> implements CollegesService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CollegesMapper collegesMapper;


    @Override
    public IPage<Colleges> findCollegesPage(Page<Colleges> page, QueryWrapper<Colleges> wrapper) {
        return this.baseMapper.findCollegesPage(page,wrapper);
    }

    @Override
    public List<Colleges> findCollegesByCbianhao(String cbianhao) {
        return collegesMapper.findCollegesByCbianhao(cbianhao);
    }

    @Override
    public List<Colleges> findCollegesByCname(String cname) {
        QueryWrapper<Colleges> wrapper = new QueryWrapper<>();
        wrapper.eq("cname",cname);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public void updateColleges(Colleges colleges) {
       this.baseMapper.updateById(colleges);
    }

    @Override
    public void deleteColleges(Integer id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public List<Colleges> getColleges() {
        return collegesMapper.getColleges();
    }

    @Override
    public void addColleges(Colleges colleges) {
        String username = colleges.getCname();
        QueryWrapper<Colleges> wrapper = new QueryWrapper<>();
        wrapper.eq("cname",username);
        Integer count = this.baseMapper.selectCount(wrapper);
        if(count!=0){
            throw new BusinessException(ResultCode.USER_ACCOUNT_ALREADY_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_ALREADY_EXIST.getMessage());
        }
        this.baseMapper.insert(colleges);
    }


}
