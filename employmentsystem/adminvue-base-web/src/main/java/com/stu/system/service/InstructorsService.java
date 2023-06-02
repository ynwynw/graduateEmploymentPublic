package com.stu.system.service;

import com.stu.system.entity.Instructors;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface InstructorsService extends IService<Instructors> {

    IPage<Instructors> findInstructorsPage(Page<Instructors> page, QueryWrapper<Instructors> wrapper);
    void addInstructors(Instructors user);
    void loginInstructors(String phone_number, String password, Integer usertype);
    Instructors findInstructorsById(Integer id);
    Instructors findInstructorByTel(String tel);
    List<Instructors> findInstructorsByTel(String tel);
    void updateInstructors(Instructors user);
    void updateInstructorsByClassid(Instructors bean);
    void updateBymarjorid(Instructors bean);
    void updateBycollegeid(Instructors bean);
    void deleteInstructors(Integer id);
}
