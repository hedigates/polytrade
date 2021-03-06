package com.polymec.domain;

import static javax.persistence.GenerationType.SEQUENCE;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import java.text.SimpleDateFormat;
import javax.persistence.Column;

@Entity
public class Credit implements Comparable<Credit>, Serializable {

    private Long id;
    private String date;
    private String dateModif = null;
    private String type;
    private String nom;
    private String numero;    
    private String reference;
    private double quantite;
    private double gain;
    private double puttc;
    private double mnt;
    private double remise;
    private double tva;    
    private String designation;

    public Credit(Date date, Date dateModif, String type, String num, String nom, double qte, double puaht, double puttc, double remise, double tva, String ref, String des) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

        this.date = dateFormat.format(date); //date.toString();
        if (dateModif != null) {
            this.dateModif = dateFormat.format(dateModif);
        } //date.toString();        

        this.type = type;
        this.numero = num;        
        this.reference = ref;
        this.nom = nom;
        this.quantite = qte;
        this.designation = des;
        this.puttc = puttc * (1 - (remise / 100));
        // calculer le montant ttc total de la ligne article * qte - remise
        this.mnt = qte * this.puttc * (1 + (tva/100));
        //calculer le gain en pourcentage
        if (puaht > 0) {
            this.gain = this.round(((this.puttc / puaht - 1) * 100), 2);
        } else {
            this.gain = 0.0;
        }
        this.remise = remise;
        this.tva = tva;
    }

    public double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(nullable = true)
    public String getDateModif() {
        return this.dateModif;
    }

    public void setDateModif(String dateModif) {
        this.dateModif = dateModif;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getGain() {
        return this.gain;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }

    public double getPuttc() {
        return this.puttc;
    }

    public void setPuttc(double puttc) {
        this.puttc = puttc;
    }
    
    public double getMnt() {
        return this.mnt;
    }

    public void setMnt(double mnt) {
        this.mnt = mnt;
    }

    public double getRemise() {
        return this.remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    public double getTva() {
        return this.tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }
    
    @Override
    public int compareTo(Credit n) {
        /*
        int lastCmp = lastName.compareTo(n.lastName);
        return (lastCmp != 0 ? lastCmp : firstName.compareTo(n.firstName));
         */
        return n.getDate().compareTo(date);
    }

    @Override
    public String toString() {
        return "Document - Ref: " + reference + ", Article: " + designation;
    }
}
