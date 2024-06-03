package com.exam.course.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import com.exam.course.entity.Equipe;

@Repository
public interface EquipeRepository extends JpaRepository <Equipe,Long> {
    @Query("SELECT e from Equipe e where e.loginEquipe = :loginEquipe AND e.pwdEquipe = :pwdEquipe")
    Equipe findByLoginAndPwd(String loginEquipe, String pwdEquipe);
}
