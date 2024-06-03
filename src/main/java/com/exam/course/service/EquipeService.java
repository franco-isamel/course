package com.exam.course.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.exam.course.entity.Equipe;
import com.exam.course.repository.EquipeRepository;

@Service
public class EquipeService {
    
    @Autowired
    EquipeRepository equipeRepository;

    public Equipe findByLoginAndPwd(String loginEquipe, String pwdEquipe){
        return equipeRepository.findByLoginAndPwd( loginEquipe,  pwdEquipe);
    }
}
