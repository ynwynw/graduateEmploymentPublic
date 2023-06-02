package com.stu.system.service.impl;


import com.stu.handler.BusinessException;
import com.stu.response.ResultCode;
import com.stu.system.entity.Classes;
import com.stu.system.mapper.ClassesMapper;
import com.stu.system.service.ClassesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements ClassesService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClassesMapper classesMapper;

    @Override
    public IPage<Classes> findClassesPage(Page<Classes> page, QueryWrapper<Classes> wrapper) {
        return this.baseMapper.findClassesPage(page,wrapper);
    }

    @Override
    public List<Classes> findClassesByCtel(String ctel) {
        return classesMapper.findClassesByCtel(ctel);
    }

    @Override
    public List<Classes> findClassesByCname(String cname) {
        QueryWrapper<Classes> wrapper = new QueryWrapper<>();
        wrapper.eq("cname",cname);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public void updateClasses(Classes classes) {
       this.baseMapper.updateById(classes);
    }

    @Override
    public void deleteClasses(Integer id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public List<Classes> getClasses() {
        return classesMapper.getClasses();
    }

    @Override
    public void addClasses(Classes classes) {
        String username = classes.getCname();
        QueryWrapper<Classes> wrapper = new QueryWrapper<>();
        wrapper.eq("cname",username);
        Integer count = this.baseMapper.selectCount(wrapper);
        if(count!=0){
            throw new BusinessException(ResultCode.USER_ACCOUNT_ALREADY_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_ALREADY_EXIST.getMessage());
        }
        this.baseMapper.insert(classes);
    }


}
