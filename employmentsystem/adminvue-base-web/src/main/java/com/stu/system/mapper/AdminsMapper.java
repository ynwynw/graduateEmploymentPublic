package com.stu.system.mapper;

import com.stu.system.entity.Admins;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface AdminsMapper extends BaseMapper<Admins> {

    List<Admins> findAdminsPage();
}
