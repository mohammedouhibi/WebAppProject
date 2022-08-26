package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="cont_export")
public class ContExport {

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


    public Date getDD_ENTREE() {
        ParsePosition parsePosition=new ParsePosition(0);
        Date date = new SimpleDateFormat("mm/dd/yy").parse(DD_ENTREE,parsePosition);
        return date;
    }

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
