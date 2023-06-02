package com.stu.system.mapper;

import com.stu.system.entity.Instructors;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InstructorsMapper extends BaseMapper<Instructors> {

    IPage<Instructors> findInstructorsPage(Page<Instructors> page, @Param(Constants.WRAPPER) QueryWrapper<Instructors> wrapper);
    Instructors findInstructorByTel(String tel);
    List<Instructors> findInstructorsByTel(String tel);

}
