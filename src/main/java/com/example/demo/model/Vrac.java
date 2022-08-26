package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="vrac_lsdt")
public class Vrac {
    @Id
    Long id;

    String DD_ENTREE;
    String LIB_COND;
    String LIB_FAMILLE;
    Long POIDS_TOT;

    int PORT;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public void setDD_ENTREE(String DD_ENTREE) {
        this.DD_ENTREE = DD_ENTREE;
    }

    public String getLIB_COND() {
        return LIB_COND;
    }

    public void setLIB_COND(String LIB_COND) {
        this.LIB_COND = LIB_COND;
    }

    public String getLIB_FAMILLE() {
        return LIB_FAMILLE;
    }

    public void setLIB_FAMILLE(String LIB_FAMILLE) {
        this.LIB_FAMILLE = LIB_FAMILLE;
    }

    public Long getPOIDS_TOT() {
        return POIDS_TOT;
    }

    public void setPOIDS_TOT(Long POIDS_TOT) {
        this.POIDS_TOT = POIDS_TOT;
    }
}
