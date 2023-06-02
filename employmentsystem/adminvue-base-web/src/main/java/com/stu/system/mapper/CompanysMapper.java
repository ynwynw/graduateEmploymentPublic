package com.stu.system.mapper;

import com.stu.system.entity.Companys;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanysMapper extends BaseMapper<Companys> {

    IPage<Companys> findCompanysPage(Page<Companys> page, @Param(Constants.WRAPPER) QueryWrapper<Companys> wrapper);
    Companys findCompanyByTel(String tel);
    List<Companys> findCompanysByTel(String tel);

}
