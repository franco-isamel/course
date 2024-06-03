package com.exam.course.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import com.exam.course.entity.Coureur;

@Repository
public interface CoureurRepository extends JpaRepository <Coureur,Long> {
    @Query(value ="SELECT idCoureur,dateDepart,dateArrivee From EtapeCoureur e WHERE e.idEtape = :idEtape",nativeQuery = true)
    List<Object[]> getCoureurByEtape(int idEtape);

    @Query(value ="SELECT idCoureur,dateDepart,dateArrivee From EtapeCoureurView e WHERE e.idEtape = :idEtape AND e.idEquipe = :idEquipe",nativeQuery = true)
    List<Object[]> getCoureurByEtapeByEquipe(int idEtape,int idEquipe);

    @Modifying
    @Query(value = "UPDATE EtapeCoureur set datedepart = :datedepart , datearrivee = :datearrivee where idEtape = :idEtape AND idCoureur = :idCoureur",nativeQuery = true)
    void insertTime(Timestamp datedepart,Timestamp datearrivee,int idEtape,int idCoureur);

    Coureur findByIdCoureur(Long idCoureur);
}
