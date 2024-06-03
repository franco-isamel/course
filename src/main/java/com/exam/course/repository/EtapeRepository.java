package com.exam.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import com.exam.course.entity.Etape;

@Repository
public interface EtapeRepository  extends JpaRepository <Etape,Long> {
    @Query("SELECT e from Etape e ORDER BY e.rangEtape ASC")
    List<Etape> getAllEtape();

    Etape findByIdEtape(int idEtape);   

    @Modifying
    @Query(value = "INSERT INTO EtapeCoureur VALUES (:idEtape,:idCoureur)",nativeQuery = true)
    void insertEtapeCoureur(int idEtape, int idCoureur);

    @Query(value = "SELECT * FROM Classement Where idEtape = :idEtape",nativeQuery = true)
    List<Object[]> getClassementParEtape(int idEtape);

    @Query(value = "SELECT * FROM classementGeneral",nativeQuery = true)
    List<Object[]> getClassementGenerle();
}