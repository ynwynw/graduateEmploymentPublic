package com.stu.system.service;

import com.stu.system.entity.Recruits;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface RecruitsService extends IService<Recruits> {

    IPage<Recruits> findRecruitsPage(Page<Recruits> page, QueryWrapper<Recruits> wrapper);
    void addRecruits(Recruits recruits);
    List<Recruits> findRecruitsByRtitle(String rtitle);
    void updateRecruits(Recruits recruits);
    void updateByCid(Recruits bean);
    void deleteRecruits(Integer id);
}
