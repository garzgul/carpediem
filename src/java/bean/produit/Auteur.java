
package bean.produit;

import java.util.Date;

/** @author Gael
 *
 * 
 */
public class Auteur {
    private String nomAuteur;
    private String prenomAuteur;
    private Date dateNAuteur;
    private Date dateDAuteur;
    private String bioAuteur;
    private boolean actifAuteur;
    private int idAuteur;

    public Auteur() {
    }

    
    
    public Auteur(String nomAuteur, String prenomAuteur, Date dateNAuteur) {
        this.nomAuteur = nomAuteur;
        this.prenomAuteur = prenomAuteur;
        this.dateNAuteur = dateNAuteur;
    }

    public Auteur(String nomAuteur, String prenomAuteur, boolean actifAuteur) {
        this.nomAuteur = nomAuteur;
        this.prenomAuteur = prenomAuteur;
        this.actifAuteur = actifAuteur;
    }
    public Auteur(int idAuteur, String nomAuteur) { // ajout eddy
        this.idAuteur = idAuteur;
        this.nomAuteur = nomAuteur;
    }

    public String getNomAuteur() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    public String getPrenomAuteur() {
        return prenomAuteur;
    }

    public void setPrenomAuteur(String prenomAuteur) {
        this.prenomAuteur = prenomAuteur;
    }

    public Date getDateNAuteur() {
        return dateNAuteur;
    }

    public void setDateNAuteur(Date dateNAuteur) {
        this.dateNAuteur = dateNAuteur;
    }

    public Date getDateDAuteur() {
        return dateDAuteur;
    }

    public void setDateDAuteur(Date dateDAuteur) {
        this.dateDAuteur = dateDAuteur;
    }

    public String getBioAuteur() {
        return bioAuteur;
    }

    public void setBioAuteur(String bioAuteur) {
        this.bioAuteur = bioAuteur;
    }

    public boolean getActifAuteur() {
        return actifAuteur;
    }

    public void setActifAuteur(boolean actifAuteur) {
        this.actifAuteur = actifAuteur;
    }
    
    public int getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }

    @Override
    public String toString() {
        return nomAuteur;
    }
    @Override // ajout eddy
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.idAuteur;
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
        final Auteur other = (Auteur) obj;
        if (this.idAuteur != other.idAuteur) {
            return false;
        }
        return true;
    }
    
}

