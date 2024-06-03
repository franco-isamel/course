package com.exam.course.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import com.exam.course.entity.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository <Categorie,Long> {
    
}
