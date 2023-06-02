package com.stu.system.service;

import com.stu.system.entity.Admins;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface AdminsService extends IService<Admins> {

    List<Admins> findAdminsPage();
    void loginUser(String utel, String password);
    List<Admins> findAdminByutel(String utel);
    void updateAdmins(Admins admins);
}
