package com.stu.system.service.impl;


import com.stu.handler.BusinessException;
import com.stu.response.ResultCode;
import com.stu.system.entity.Companys;
import com.stu.system.mapper.CompanysMapper;
import com.stu.system.service.CompanysService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanysServiceImpl extends ServiceImpl<CompanysMapper, Companys> implements CompanysService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CompanysMapper companysMapper;


    @Override
    public IPage<Companys> findCompanysPage(Page<Companys> page, QueryWrapper<Companys> wrapper) {
        return this.baseMapper.findCompanysPage(page,wrapper);
    }

    @Override
    public void loginCompanys(String phone_number, String password) {
      QueryWrapper<Companys> userWrapper = new QueryWrapper<>();
        userWrapper.eq("ctel",phone_number);
        userWrapper.eq("cpwd",password);
        userWrapper.eq("cflag",1);
        Integer count = this.baseMapper.selectCount(userWrapper);
        if(count == 0){
            throw new BusinessException(ResultCode.USER_ACCOUNT_NOT_EXIST.getCode(),ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
        }
    }

    @Override
    public Companys findCompanysById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public Companys findCompanyByTel(String tel) {
        return companysMapper.findCompanyByTel(tel);
    }

    @Override
    public List<Companys> findCompanysByTel(String tel) {
        return companysMapper.findCompanysByTel(tel);
    }

    @Override
    public void updateCompanys(Companys companys) {
       this.baseMapper.updateById(companys);
    }

    @Override
    public void deleteCompanys(Integer id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public void addCompanys(Companys companys) {
        String username = companys.getCname();
        QueryWrapper<Companys> wrapper = new QueryWrapper<>();
        wrapper.eq("cname",username);
        Integer count = this.baseMapper.selectCount(wrapper);
        if(count!=0){
            throw new BusinessException(ResultCode.USER_ACCOUNT_ALREADY_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_ALREADY_EXIST.getMessage());
        }
        this.baseMapper.insert(companys);
    }


}
