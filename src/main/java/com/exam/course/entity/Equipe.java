package com.exam.course.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Equipe")
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEquipe")
    private Long idEquipe;

    @Column(name = "nomEquipe")
    private String nomEquipe;

    @Column(name = "loginEquipe")
    private String loginEquipe;

    @Column(name = "pwdEquipe")
    private String pwdEquipe;

    @OneToMany(mappedBy = "equipe", fetch = FetchType.EAGER)
    List<Coureur> listCoureurs;

    public Long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Long idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public String getLoginEquipe() {
        return loginEquipe;
    }

    public void setLoginEquipe(String loginEquipe) {
        this.loginEquipe = loginEquipe;
    }

    public String getPwdEquipe() {
        return pwdEquipe;
    }

    public void setPwdEquipe(String pwdEquipe) {
        this.pwdEquipe = pwdEquipe;
    }

    public List<Coureur> getListCoureurs() {
        return listCoureurs;
    }

    public void setListCoureurs(List<Coureur> listCoureurs) {
        this.listCoureurs = listCoureurs;
    }

}
