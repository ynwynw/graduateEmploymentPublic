package com.stu.system.service.impl;


import com.stu.handler.BusinessException;
import com.stu.response.ResultCode;
import com.stu.system.entity.Applys;
import com.stu.system.mapper.ApplysMapper;
import com.stu.system.service.ApplysService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplysServiceImpl extends ServiceImpl<ApplysMapper, Applys> implements ApplysService {

    @Autowired
    private ApplysMapper applysMapper;

    @Override
    public IPage<Applys> findApplysPage(Page<Applys> page, QueryWrapper<Applys> wrapper) {
        return this.baseMapper.findApplysPage(page,wrapper);
    }

    @Override
    public List<Applys> findApplysByRtitle(String rtitle) {
        QueryWrapper<Applys> wrapper = new QueryWrapper<>();
        wrapper.eq("rtitle",rtitle);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<Applys> checkUserApplys(Applys applys) {
        QueryWrapper<Applys> wrapper = new QueryWrapper<>();
        wrapper.eq("rname",applys.getRname());
        wrapper.eq("cname",applys.getCname());
        wrapper.eq("stel",applys.getStel());
        wrapper.eq("aflag", 1);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public void updateApplys(Applys Applys) {
       this.baseMapper.updateById(Applys);
    }

    @Override
    public void updateApplysByClassid(Applys bean) {
        QueryWrapper<Applys> wrapper = new QueryWrapper<>();
        wrapper.eq("classid", bean.getClassid());
        this.baseMapper.update(bean, wrapper);
    }

    @Override
    public void updateBymarjorid(Applys bean) {
        QueryWrapper<Applys> wrapper = new QueryWrapper<>();
        wrapper.eq("marjorid", bean.getMarjorid());
        this.baseMapper.update(bean, wrapper);
    }

    @Override
    public void updateBycollegeid(Applys bean) {
        QueryWrapper<Applys> wrapper = new QueryWrapper<>();
        wrapper.eq("collegeid", bean.getCollegeid());
        this.baseMapper.update(bean, wrapper);
    }

    @Override
    public void deleteApplys(Integer id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public void addApplys(Applys applys) {
        String rtitle = applys.getRtitle();
        QueryWrapper<Applys> wrapper = new QueryWrapper<>();
        wrapper.eq("rtitle",rtitle);
        wrapper.eq("sname",applys.getSname());
        wrapper.eq("rname",applys.getRname());
        Integer count = this.baseMapper.selectCount(wrapper);
        if(count!=0){
            throw new BusinessException(ResultCode.USER_ACCOUNT_ALREADY_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_ALREADY_EXIST.getMessage());
        }
        this.baseMapper.insert(applys);
    }
}
