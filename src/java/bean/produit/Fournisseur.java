
package bean.produit;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class Fournisseur implements Serializable{
    private String nomSupplier;
    private String adresse1Supplier;
    private String adresse2Supplier;
    private String cpSupplier;
    private String villeSupplier;
    private boolean actifSupllier;
    private int idfournisseur; // ajout eddy

    public Fournisseur() {
    }

    public Fournisseur(String nomSupplier, boolean actifSupllier) {
        this.nomSupplier = nomSupplier;
        this.actifSupllier = actifSupllier;
    }

    public Fournisseur(int id_fournisseur, String nomSupplier) { // ajout eddy pour faciliter gestions dans les combobox
        this.nomSupplier = nomSupplier;
        this.idfournisseur = id_fournisseur;
    }

    public String getNomSupplier() {
        return nomSupplier;
    }

    public void setNomSupplier(String nomSupplier) {
        this.nomSupplier = nomSupplier;
    }

    public String getAdresse1Supplier() {
        return adresse1Supplier;
    }

    public void setAdresse1Supplier(String adresse1Supplier) {
        this.adresse1Supplier = adresse1Supplier;
    }

    public String getAdresse2Supplier() {
        return adresse2Supplier;
    }

    public void setAdresse2Supplier(String adresse2Supplier) {
        this.adresse2Supplier = adresse2Supplier;
    }

    public String getCpSupplier() {
        return cpSupplier;
    }

    public void setCpSupplier(String cpSupplier) {
        this.cpSupplier = cpSupplier;
    }

    public String getVilleSupplier() {
        return villeSupplier;
    }

    public void setVilleSupplier(String villeSupplier) {
        this.villeSupplier = villeSupplier;
    }

    public boolean getActifSupllier() {
        return actifSupllier;
    }

    public void setActifSupllier(boolean actifSupllier) {
        this.actifSupllier = actifSupllier;
    }

    public int getId_fournisseur() { // ajout eddy
        return idfournisseur;
    }

    public void setId_fournisseur(int id_fournisseur) { // ajout eddy
        this.idfournisseur = id_fournisseur;
    }
  
    @Override                        // ajout eddy
    public String toString(){
        return this.getNomSupplier();
    }

    @Override // ajout eddy
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.idfournisseur;
        return hash;
    }

    @Override // ajout eddy
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fournisseur other = (Fournisseur) obj;
        if (this.idfournisseur != other.idfournisseur) {
            return false;
        }
        return true;
    }
    
}
