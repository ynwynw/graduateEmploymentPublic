package com.stu.system.service;

import com.stu.system.entity.Majors;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface MajorsService extends IService<Majors> {

    IPage<Majors> findMajorsPage(Page<Majors> page, QueryWrapper<Majors> wrapper);
    void addMajors(Majors majors);
    List<Majors> findMajorsByCbianhao(String cbianhao);
    List<Majors> findMajorsByCname(String cname);
    void updateMajors(Majors majors);
    void deleteMajors(Integer id);
    List<Majors> getMajors();
}
