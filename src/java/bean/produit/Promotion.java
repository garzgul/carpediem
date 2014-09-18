package bean.produit;

import java.io.Serializable;

/**
 *
 * @author cdi108
 */
public class Promotion implements Serializable {

    private int id;
    private String nom;
    private String descriptif;

    public Promotion() {
    }

    public Promotion(int id, String nom, String descriptif) {
        this.id = id;
        this.nom = nom;
        this.descriptif = descriptif;
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
