package com.exam.course.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.exam.course.entity.Admin;
import com.exam.course.repository.AdminRespotirory;

@Service
public class AdminService {
    @Autowired
    AdminRespotirory adminRespotirory;

    public Admin findByLoginAndPwd(String loginAdmin, String pwdAdmin){
        return adminRespotirory.findByLoginAndPwd( loginAdmin,  pwdAdmin);
    }
}
