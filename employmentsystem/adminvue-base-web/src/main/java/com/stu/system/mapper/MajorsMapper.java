package com.stu.system.mapper;

import com.stu.system.entity.Majors;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MajorsMapper extends BaseMapper<Majors> {

    IPage<Majors> findMajorsPage(Page<Majors> page, @Param(Constants.WRAPPER) QueryWrapper<Majors> wrapper);
    List<Majors> findMajorsByCbianhao(String cbianhao);
    List<Majors> getMajors();
}
