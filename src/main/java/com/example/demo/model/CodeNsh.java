package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "code_nsh")
public class CodeNsh {

    @Id
    private int CODE;

    private String LIBELLE;

    private int REFCATEGORIE;

}
