package com.exam.course.service;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.exam.course.repository.CategorieRepository;

@Service
public class CategorieService {
    @Autowired
    CategorieRepository categorieRepository;
}
