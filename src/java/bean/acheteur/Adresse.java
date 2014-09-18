
package bean.acheteur;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class Adresse implements Serializable{
    private int idAcheteur;
    private String adresseAcheteur1;
    private String adresseAcheteur2;
    private String cpAcheteur;
    private String villeAcheteur;
    private String paysAcheteur;
    private boolean actifAdresse;
    private int idAdresse;
    private boolean adresseFav;

    public Adresse() {
    }

    public Adresse(String adresseAcheteur1, String adresseAcheteur2, String cpAcheteur, String villeAcheteur, String paysAcheteur, boolean actifAdresse) {
        this.adresseAcheteur1 = adresseAcheteur1;
        this.adresseAcheteur2 = adresseAcheteur2;
        this.cpAcheteur = cpAcheteur;
        this.villeAcheteur = villeAcheteur;
        this.paysAcheteur = paysAcheteur;
        this.actifAdresse = actifAdresse;
    }

    public int getIdAcheteur() {
        return idAcheteur;
    }

    public void setIdAcheteur(int idAcheteur) {
        this.idAcheteur = idAcheteur;
    }
    
    public int getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(int idadresse) {
        this.idAdresse = idadresse;
    }
    public String getAdresseAcheteur1() {
        return adresseAcheteur1;
    }

    public void setAdresseAcheteur1(String adresseAcheteur1) {
        this.adresseAcheteur1 = adresseAcheteur1;
    }

    public String getAdresseAcheteur2() {
        return adresseAcheteur2;
    }

    public void setAdresseAcheteur2(String adresseAcheteur2) {
        this.adresseAcheteur2 = adresseAcheteur2;
    }

    public String getCpAcheteur() {
        return cpAcheteur;
    }

    public void setCpAcheteur(String cpAcheteur) {
        this.cpAcheteur = cpAcheteur;
    }

    public String getVilleAcheteur() {
        return villeAcheteur;
    }

    public void setVilleAcheteur(String villeAcheteur) {
        this.villeAcheteur = villeAcheteur;
    }

    public String getPaysAcheteur() {
        return paysAcheteur;
    }

    public void setPaysAcheteur(String paysAcheteur) {
        this.paysAcheteur = paysAcheteur;
    }

    public boolean getActifAdresse() {
        return actifAdresse;
    }

    public void setActifAdresse (boolean actifAdresse) {
        this.actifAdresse = actifAdresse;
    }

    public boolean getAdresseFav() {
        return adresseFav;
    }

    public void setAdresseFav(boolean adressefav) {
        this.adresseFav = adressefav;
    }
    
    
    
}
