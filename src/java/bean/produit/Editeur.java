
package bean.produit;

/**
 *
 * @author user
 */
public class Editeur {
    private String nomEditeur;
    private String paysEditeur;
    private String adresseEditeur;
    private String cpEditeur;
    private String villeEditeur;
    private String telEditeur;
    private String emailEditeur;
    private boolean actifEditeur;
    private int id_editeur; // ajout eddy

    public Editeur() {
    }

    public Editeur(String nomEditeur, String paysEditeur, boolean actifEditeur) {
        this.nomEditeur = nomEditeur;
        this.paysEditeur = paysEditeur;
        this.actifEditeur = actifEditeur;
    }

    public Editeur(int id_editeur, String editeur_nom) { // ajout eddy pour faciliter gestions dans les combobox
        this.id_editeur = id_editeur;
        this.nomEditeur = editeur_nom;
    }
    
    public String getNomEditeur() {
        return nomEditeur;
    }

    public void setNomEditeur(String nomEditeur) {
        this.nomEditeur = nomEditeur;
    }

    public String getPaysEditeur() {
        return paysEditeur;
    }

    public void setPaysEditeur(String paysEditeur) {
        this.paysEditeur = paysEditeur;
    }

    public String getAdresseEditeur() {
        return adresseEditeur;
    }

    public void setAdresseEditeur(String adresseEditeur) {
        this.adresseEditeur = adresseEditeur;
    }

    public String getCpEditeur() {
        return cpEditeur;
    }

    public void setCpEditeur(String cpEditeur) {
        this.cpEditeur = cpEditeur;
    }

    public String getVilleEditeur() {
        return villeEditeur;
    }

    public void setVilleEditeur(String villeEditeur) {
        this.villeEditeur = villeEditeur;
    }

    public String getTelEditeur() {
        return telEditeur;
    }

    public void setTelEditeur(String telEditeur) {
        this.telEditeur = telEditeur;
    }

    public String getEmailEditeur() {
        return emailEditeur;
    }

    public void setEmailEditeur(String emailEditeur) {
        this.emailEditeur = emailEditeur;
    }

    public boolean isActifEditeur() {
        return actifEditeur;
    }

    public void setActifEditeur(boolean actifEditeur) {
        this.actifEditeur = actifEditeur;
    }

    public int getId_editeur() { // ajout eddy
        return id_editeur;
    }

    public void setId_editeur(int id_editeur) { // ajout eddy
        this.id_editeur = id_editeur;
    }
    
    @Override                        // ajout eddy
    public String toString(){
        return this.getNomEditeur();
    }

    @Override                       // ajout eddy
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id_editeur;
        return hash;
    }

    @Override                       // ajout eddy
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Editeur other = (Editeur) obj;
        if (this.id_editeur != other.id_editeur) {
            return false;
        }
        return true;
    }
    

}
