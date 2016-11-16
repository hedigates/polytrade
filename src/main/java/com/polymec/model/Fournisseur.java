package com.polymec.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

@Entity
@Table(name = "fsr")
public class Fournisseur implements Serializable {

    private Long id;
    private String raison;
    //private List<ArticleFrns> artFrns = new ArrayList<ArticleFrns>(); //Lists.newArrayList();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Fsr_Num")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "Fsr_nm")
    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    @Override
    public String toString() {
        return "Fournisseur - Id: " + id + ", Raison: " + raison;
    }
}