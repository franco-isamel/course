package com.exam.course.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.exam.course.entity.Equipe;
import com.exam.course.entity.Etape;
import com.exam.course.service.EquipeService;
import com.exam.course.service.EtapeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EtapeController {
    @Autowired 
    EquipeService equipeService;

    @Autowired
    EtapeService etapeService;
}
