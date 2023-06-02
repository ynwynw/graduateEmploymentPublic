package com.stu.system.service;

import com.stu.system.entity.Applys;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ApplysService extends IService<Applys> {

    IPage<Applys> findApplysPage(Page<Applys> page, QueryWrapper<Applys> wrapper);
    void addApplys(Applys bean);
    List<Applys> findApplysByRtitle(String rtitle);
    List<Applys> checkUserApplys(Applys applys);
    void updateApplys(Applys bean);
    void updateApplysByClassid(Applys bean);
    void updateBymarjorid(Applys bean);
    void updateBycollegeid(Applys bean);
    void deleteApplys(Integer id);
}
