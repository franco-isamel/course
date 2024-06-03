package com.exam.course.entity;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Coureur")
public class Coureur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCoureur")
    private Long idCoureur;

    @Column(name = "nomCoureur")
    private String nomCoureur;

    @Column(name = "numero")
    private int numero;

    @Column(name = "genre")
    private Character genre;

    @Column(name = "DateNaissance")
    private Date DateNaissance;
    
    @ManyToOne
    @JoinColumn(name = "idEquipe")
    private Equipe equipe;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "CoureurCategorie",
        joinColumns = @JoinColumn(name = "idCoureur"),
        inverseJoinColumns = @JoinColumn(name = "idCategorie")
    )
    List<Categorie> listCategorie;


    @Transient
    private Timestamp dateDepart;

    @Transient
    private Timestamp dateArrivee;

    public Timestamp getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Timestamp dateDepart) {
        this.dateDepart = dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateDepart, formatter);
        this.dateDepart = Timestamp.valueOf(localDateTime);
    }

    public Timestamp getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Timestamp dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public void setDateArrivee(String dateArrivee) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateArrivee, formatter);
        this.dateArrivee = Timestamp.valueOf(localDateTime);
    }


    public Long getIdCoureur() {
        return idCoureur;
    }

    public void setIdCoureur(Long idCoureur) {
        this.idCoureur = idCoureur;
    }

    public String getNomCoureur() {
        return nomCoureur;
    }

    public void setNomCoureur(String nomCoureur) {
        this.nomCoureur = nomCoureur;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Character getGenre() {
        return genre;
    }

    public void setGenre(Character genre) {
        this.genre = genre;
    }

    public Date getDateNaissance() {
        return DateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        DateNaissance = dateNaissance;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public List<Categorie> getListCategorie() {
        return listCategorie;
    }

    public void setListCategorie(List<Categorie> listCategorie) {
        this.listCategorie = listCategorie;
    }
}
