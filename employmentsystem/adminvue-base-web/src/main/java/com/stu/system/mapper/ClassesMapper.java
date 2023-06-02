package com.stu.system.mapper;

import com.stu.system.entity.Classes;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassesMapper extends BaseMapper<Classes> {

    IPage<Classes> findClassesPage(Page<Classes> page, @Param(Constants.WRAPPER) QueryWrapper<Classes> wrapper);
    List<Classes> findClassesByCtel(String ctel);
    List<Classes> getClasses();
}
