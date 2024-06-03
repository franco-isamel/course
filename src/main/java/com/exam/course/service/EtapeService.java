package com.exam.course.service;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.exam.course.entity.Etape;
import com.exam.course.repository.EtapeRepository;

import jakarta.transaction.Transactional;

@Service
public class EtapeService {
    @Autowired
    EtapeRepository etapeRepository;

    public List<Etape> getAllEtape(){
        return etapeRepository.getAllEtape();
    };

    public Etape findByIdEtape(int idEtape){
        return etapeRepository.findByIdEtape(idEtape);
    }

    @Transactional
    public void insertEtapeCoureur(int idEtape, int idCoureur){
        etapeRepository.insertEtapeCoureur( idEtape,  idCoureur);
    }

    public List<Object[]> getClassementParEtape(int idEtape){
        return etapeRepository.getClassementParEtape(idEtape);
    };

    public List<Object[]> getClassementGenerle(){
        return etapeRepository.getClassementGenerle();
    };
}
