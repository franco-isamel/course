package com.exam.course.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.exam.course.entity.Equipe;
import com.exam.course.service.EquipeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class authController {
    @Autowired
    EquipeService equipeService;

    

    @GetMapping("/")
    public String EquipeLoginPage(){
        return "equipe_login";
    }

}
