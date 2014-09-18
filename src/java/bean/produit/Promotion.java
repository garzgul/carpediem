/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.produit;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author cdi108
 */
public class Promotion implements Serializable {
    
    private int id;
    private String nom;
    private String descriptif;
    private LocalDate datedbt;
    private LocalDate datefin;

    public Promotion() {
    }

     public Promotion(int id, String nom, String descriptif) {
        this.id = id;
        this.nom = nom;
        this.descriptif = descriptif;
    }

    public Promotion(int id, String nom, String descriptif, LocalDate datedbt, LocalDate datefin) {
        this.id = id;
        this.nom = nom;
        this.descriptif = descriptif;
        this.datedbt = datedbt;
        this.datefin = datefin;
    }
     
     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", nom=" + nom + ", descriptif=" + descriptif + '}';
    }
 

}
