package com.stu.system.service;

import com.stu.system.entity.Types;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TypesService extends IService<Types> {

    IPage<Types> findTypesPage(Page<Types> page, QueryWrapper<Types> wrapper);
    void addTypes(Types types);
    void updateTypes(Types types);
    List<Types> findTypesByTname(String tname);
    void deleteTypes(Integer id);
    List<Types> getTypes();
}
