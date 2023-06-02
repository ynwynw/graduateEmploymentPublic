package com.stu.system.service;

import com.stu.system.entity.Resumes;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ResumesService extends IService<Resumes> {

    IPage<Resumes> findResumesPage(Page<Resumes> page, QueryWrapper<Resumes> wrapper);
    void addResumes(Resumes resumes);
    List<Resumes> findResumesByRtitle(String rtitle);
    void updateResumes(Resumes resumes);
    void deleteResumes(Integer id);
    List<Resumes> getResumes(String utel);
    List<Resumes> getResumesByUid(Integer id);
    void updateByUid(Resumes bean);
    List<Resumes> findResumesByRtitleAndUtel(Resumes resumes);
}
