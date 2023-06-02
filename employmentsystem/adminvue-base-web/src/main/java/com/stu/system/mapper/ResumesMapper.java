package com.stu.system.mapper;

import com.stu.system.entity.Resumes;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResumesMapper extends BaseMapper<Resumes> {

    IPage<Resumes> findResumesPage(Page<Resumes> page, @Param(Constants.WRAPPER) QueryWrapper<Resumes> wrapper);
    List<Resumes> findResumesByRtitle(String rtitle);
    List<Resumes> findResumesByRtitleAndUtel(Resumes resumes);
}
