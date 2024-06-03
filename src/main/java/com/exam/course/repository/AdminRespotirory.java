package com.exam.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import com.exam.course.entity.Admin;

@Repository
public interface AdminRespotirory extends JpaRepository <Admin,Long> {
    @Query("SELECT a from Admin a where a.loginAdmin = :loginAdmin AND a.pwdAdmin = :pwdAdmin")
    Admin findByLoginAndPwd(String loginAdmin, String pwdAdmin);
}
