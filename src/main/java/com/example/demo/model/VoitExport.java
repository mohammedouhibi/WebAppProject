package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="voit_export")
public class VoitExport {

    @Id
    Long id;
    int ESCALE;
    int PORT;
    int ANNEE;
    int CONDITIONNEMENT;
    int FAMILLE_NSH;
    int POIDS;
    int TARE;
    int POIDS_SANS_TARE;
    int TAILLE;
    String DESIGNATION_MDISE;
    String NOM_DESTINATAIRE;
    int CODE_NSH;
    int NUM_RUB;
    int NBR_COLIS;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getESCALE() {
        return ESCALE;
    }

    public void setESCALE(int ESCALE) {
        this.ESCALE = ESCALE;
    }

    public int getPORT() {
        return PORT;
    }

    public void setPORT(int PORT) {
        this.PORT = PORT;
    }

    public int getANNEE() {
        return ANNEE;
    }

    public void setANNEE(int ANNEE) {
        this.ANNEE = ANNEE;
    }

    public int getCONDITIONNEMENT() {
        return CONDITIONNEMENT;
    }

    public void setCONDITIONNEMENT(int CONDITIONNEMENT) {
        this.CONDITIONNEMENT = CONDITIONNEMENT;
    }

    public int getFAMILLE_NSH() {
        return FAMILLE_NSH;
    }

    public void setFAMILLE_NSH(int FAMILLE_NSH) {
        this.FAMILLE_NSH = FAMILLE_NSH;
    }

    public int getPOIDS() {
        return POIDS;
    }

    public void setPOIDS(int POIDS) {
        this.POIDS = POIDS;
    }

    public int getTARE() {
        return TARE;
    }

    public void setTARE(int TARE) {
        this.TARE = TARE;
    }

    public int getPOIDS_SANS_TARE() {
        return POIDS_SANS_TARE;
    }

    public void setPOIDS_SANS_TARE(int POIDS_SANS_TARE) {
        this.POIDS_SANS_TARE = POIDS_SANS_TARE;
    }

    public int getTAILLE() {
        return TAILLE;
    }

    public void setTAILLE(int TAILLE) {
        this.TAILLE = TAILLE;
    }

    public String getDESIGNATION_MDISE() {
        return DESIGNATION_MDISE;
    }

    public void setDESIGNATION_MDISE(String DESIGNATION_MDISE) {
        this.DESIGNATION_MDISE = DESIGNATION_MDISE;
    }

    public String getNOM_DESTINATAIRE() {
        return NOM_DESTINATAIRE;
    }

    public void setNOM_DESTINATAIRE(String NOM_DESTINATAIRE) {
        this.NOM_DESTINATAIRE = NOM_DESTINATAIRE;
    }

    public int getCODE_NSH() {
        return CODE_NSH;
    }

    public void setCODE_NSH(int CODE_NSH) {
        this.CODE_NSH = CODE_NSH;
    }

    public int getNUM_RUB() {
        return NUM_RUB;
    }

    public void setNUM_RUB(int NUM_RUB) {
        this.NUM_RUB = NUM_RUB;
    }

    public int getNBR_COLIS() {
        return NBR_COLIS;
    }

    public void setNBR_COLIS(int NBR_COLIS) {
        this.NBR_COLIS = NBR_COLIS;
    }


}
