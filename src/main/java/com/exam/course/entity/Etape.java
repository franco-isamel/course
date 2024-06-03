package com.exam.course.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Etape")
public class Etape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEtape")
    private Long idEtape;

    @Column(name = "nomEtape")
    private String nomEtape;

    @Column(name = "longueur")
    private Double longueur;

    @Column(name = "nbCoureur")
    private int nbCoureur;

    @Column(name = "rangEtape")
    private int rangEtape;

    public Long getIdEtape() {
        return idEtape;
    }

    public void setIdEtape(Long idEtape) {
        this.idEtape = idEtape;
    }

    public String getNomEtape() {
        return nomEtape;
    }

    public void setNomEtape(String nomEtape) {
        this.nomEtape = nomEtape;
    }

    public Double getLongueur() {
        return longueur;
    }

    public void setLongueur(Double longueur) {
        this.longueur = longueur;
    }

    public int getNbCoureur() {
        return nbCoureur;
    }

    public void setNbCoureur(int nbCoureur) {
        this.nbCoureur = nbCoureur;
    }

    public int getRangEtape() {
        return rangEtape;
    }

    public void setRangEtape(int rangEtape) {
        this.rangEtape = rangEtape;
    }

    
}
