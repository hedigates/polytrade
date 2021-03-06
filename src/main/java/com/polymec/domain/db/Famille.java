package com.polymec.domain.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.OneToMany;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "fml")
public class Famille implements Serializable {

    private Long id;
    private String designation;
    private Date date;
    @JsonIgnore
    private List<Article> articles = new ArrayList<Article>();

    public Famille() {

    }

    public Famille(String des) {
        this.designation = des;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Fml_Num")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "Fml_le")
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    //@JsonFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "Fml_de")
    public Date getDate() { 
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    @OneToMany(mappedBy = "famille")
    public List<Article> getArticles() {
        return this.articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Famille - Id: " + id + ", Designation: " + designation;
    }
}
