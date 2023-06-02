package com.stu.system.mapper;

import com.stu.system.entity.Types;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TypesMapper extends BaseMapper<Types> {

    IPage<Types> findTypesPage(Page<Types> page, @Param(Constants.WRAPPER) QueryWrapper<Types> wrapper);
    List<Types> getTypes();
    List<Types> findTypesByTname(String tname);
}
