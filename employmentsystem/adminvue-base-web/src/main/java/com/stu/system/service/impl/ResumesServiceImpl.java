package com.stu.system.service.impl;


import com.stu.handler.BusinessException;
import com.stu.response.ResultCode;
import com.stu.system.entity.Resumes;
import com.stu.system.mapper.ResumesMapper;
import com.stu.system.service.ResumesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumesServiceImpl extends ServiceImpl<ResumesMapper, Resumes> implements ResumesService {

    @Autowired
    private ResumesMapper resumesMapper;

    @Override
    public IPage<Resumes> findResumesPage(Page<Resumes> page, QueryWrapper<Resumes> wrapper) {
        return this.baseMapper.findResumesPage(page,wrapper);
    }

    @Override
    public List<Resumes> findResumesByRtitle(String rtitle) {
        return resumesMapper.findResumesByRtitle(rtitle);
    }

    @Override
    public void updateResumes(Resumes Resumes) {
       this.baseMapper.updateById(Resumes);
    }

    @Override
    public void deleteResumes(Integer id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public List<Resumes> getResumes(String utel) {
        QueryWrapper<Resumes> wrapper = new QueryWrapper<>();
        wrapper.eq("stel",utel);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<Resumes> getResumesByUid(Integer id) {
        QueryWrapper<Resumes> wrapper = new QueryWrapper<>();
        wrapper.eq("ruid",id);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public void updateByUid(Resumes bean) {
        QueryWrapper<Resumes> wrapper = new QueryWrapper<>();
        wrapper.eq("ruid", bean.getRuid());
        this.baseMapper.update(bean, wrapper);
    }

    @Override
    public List<Resumes> findResumesByRtitleAndUtel(Resumes resumes) {
        return resumesMapper.findResumesByRtitleAndUtel(resumes);
    }

    @Override
    public void addResumes(Resumes resumes) {
        String rtitle = resumes.getRtitle();
        QueryWrapper<Resumes> wrapper = new QueryWrapper<>();
        wrapper.eq("rtitle",rtitle);
        wrapper.eq("sname",resumes.getSname());
        wrapper.eq("stel",resumes.getStel());
        Integer count = this.baseMapper.selectCount(wrapper);
        if(count!=0){
            throw new BusinessException(ResultCode.USER_ACCOUNT_ALREADY_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_ALREADY_EXIST.getMessage());
        }
        this.baseMapper.insert(resumes);
    }
}
