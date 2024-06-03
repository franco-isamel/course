package com.exam.course.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "AdminCourse")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAdmin")
    private Long idAdmin;

    @Column(name = "loginAdmin")
    private String loginAdmin;

    @Column(name = "pwdLogin")
    private String pwdAdmin;
}
