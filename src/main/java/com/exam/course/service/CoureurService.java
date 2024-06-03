package com.exam.course.service;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.exam.course.entity.Coureur;
import com.exam.course.repository.CoureurRepository;

import jakarta.transaction.Transactional;

@Service
public class CoureurService {
    @Autowired
    CoureurRepository coureurRepository;

    public List<Coureur> getCoureurByEtape(int idEtape){
        List<Coureur> listCoureur = new ArrayList<Coureur>();
        List<Object[]> results = coureurRepository.getCoureurByEtape(idEtape);
        for (Object[] result : results) {
            Coureur coureur = coureurRepository.findByIdCoureur(((Number) result[0]).longValue());
            coureur.setDateDepart((Timestamp) result[1]);
            coureur.setDateArrivee((Timestamp) result[2]);
            listCoureur.add(coureur);
        }
        return listCoureur;
    }

    public List<Coureur> getCoureurByEtapeByEquipe(int idEtape,int idEquipe){
        List<Coureur> listCoureur = new ArrayList<Coureur>();
        List<Object[]> results = coureurRepository.getCoureurByEtapeByEquipe( idEtape, idEquipe);
        for (Object[] result : results) {
            Coureur coureur = coureurRepository.findByIdCoureur(((Number) result[0]).longValue());
            coureur.setDateDepart((Timestamp) result[1]);
            coureur.setDateArrivee((Timestamp) result[2]);
            listCoureur.add(coureur);
        }
        return listCoureur;
    };

    public Coureur findByIdCoureur(Long idCoureur){
        return coureurRepository.findByIdCoureur(idCoureur);
    }

    @Transactional
    public void insertTime(Timestamp datedepart,Timestamp datearrivee,int idEtape,int idCoureur){
        
        coureurRepository.insertTime( datedepart, datearrivee, idEtape, idCoureur);
    };
}
