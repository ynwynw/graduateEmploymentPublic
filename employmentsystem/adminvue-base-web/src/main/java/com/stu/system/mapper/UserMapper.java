package com.stu.system.mapper;

import com.stu.system.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    IPage<User> findUserPage(Page<User> page, @Param(Constants.WRAPPER) QueryWrapper<User> wrapper);
    User findUserByTel(String tel);
    List<User> findUsersByTel(String tel);

}
