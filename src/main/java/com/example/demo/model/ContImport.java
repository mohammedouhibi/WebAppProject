package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

@Entity
@Table(name="cont_import")
public class ContImport {

    @Id
    Long id;

    int PORT;
    String DD_ENTREE;
    String LIB_FAMILLE;

    public int getNBR_COLIS() {
        return NBR_COLIS;
    }

    public void setNBR_COLIS(int NBR_COLIS) {
        this.NBR_COLIS = NBR_COLIS;
    }

    int NBR_COLIS;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPORT() {
        return PORT;
    }

    public void setPORT(int PORT) {
        this.PORT = PORT;
    }

    public Date getDD_ENTREE() {
        ParsePosition parsePosition=new ParsePosition(0);
        Date date = new SimpleDateFormat("dd-mm-yy").parse(DD_ENTREE,parsePosition);
        return date;
    }

    public void setDD_ENTREE(String DD_ENTREE) {
        this.DD_ENTREE = DD_ENTREE;
    }

    public String getLIB_FAMILLE() {
        return LIB_FAMILLE;
    }

    public void setLIB_FAMILLE(String LIB_FAMILLE) {
        this.LIB_FAMILLE = LIB_FAMILLE;
    }
}
