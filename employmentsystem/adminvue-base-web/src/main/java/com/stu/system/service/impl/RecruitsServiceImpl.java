package com.stu.system.service.impl;


import com.stu.handler.BusinessException;
import com.stu.response.ResultCode;
import com.stu.system.entity.Recruits;
import com.stu.system.mapper.RecruitsMapper;
import com.stu.system.service.RecruitsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitsServiceImpl extends ServiceImpl<RecruitsMapper, Recruits> implements RecruitsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RecruitsMapper recruitsMapper;

    @Override
    public IPage<Recruits> findRecruitsPage(Page<Recruits> page, QueryWrapper<Recruits> wrapper) {
        return this.baseMapper.findRecruitsPage(page,wrapper);
    }

    @Override
    public List<Recruits> findRecruitsByRtitle(String rtitle) {
        return recruitsMapper.findRecruitsByRtitle(rtitle);
    }

    @Override
    public void updateRecruits(Recruits recruits) {
       this.baseMapper.updateById(recruits);
    }

    @Override
    public void updateByCid(Recruits bean) {
        QueryWrapper<Recruits> wrapper = new QueryWrapper<>();
        wrapper.eq("rcid", bean.getRcid());
        this.baseMapper.update(bean, wrapper);
    }

    @Override
    public void deleteRecruits(Integer id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public void addRecruits(Recruits recruits) {
        String rtitle = recruits.getRtitle();
        QueryWrapper<Recruits> wrapper = new QueryWrapper<>();
        wrapper.eq("rtitle",rtitle);
        wrapper.eq("cname",recruits.getCname());
        Integer count = this.baseMapper.selectCount(wrapper);
        if(count!=0){
            throw new BusinessException(ResultCode.USER_ACCOUNT_ALREADY_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_ALREADY_EXIST.getMessage());
        }

        this.baseMapper.insert(recruits);
    }


}
