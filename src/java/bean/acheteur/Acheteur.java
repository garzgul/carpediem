
package bean.acheteur;

import bean.commande.Commande;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Acheteur implements Serializable{
    private String nomAcheteur;
    private String prenomAcheteur;
    private String pseudoAcheteur;
    private String mdpAcheteur;
    private String emailAcheteur;
    private String telAcheteur;
    private boolean actifAcheteur;
    private ArrayList<Adresse> listAdresseAcheteur;
    private int idAcheteur;

    

    public Acheteur() {
    }

    public Acheteur(String nomAcheteur, String prenomAcheteur, String pseudoAcheteur) {
        this.nomAcheteur = nomAcheteur;
        this.prenomAcheteur = prenomAcheteur;
        this.pseudoAcheteur = pseudoAcheteur;
    }

    public Acheteur(int idAcheteur, String nomAcheteur, String prenomAcheteur, String pseudoAcheteur) {
        this.nomAcheteur = nomAcheteur;
        this.prenomAcheteur = prenomAcheteur;
        this.pseudoAcheteur = pseudoAcheteur;
        this.idAcheteur = idAcheteur;
    }
    

    public Acheteur(String nomAcheteur, String prenomAcheteur, String pseudoAcheteur, String mdpAcheteur, boolean actifAcheteur) {
        this.nomAcheteur = nomAcheteur;
        this.prenomAcheteur = prenomAcheteur;
        this.pseudoAcheteur = pseudoAcheteur;
        this.mdpAcheteur = mdpAcheteur;
        this.actifAcheteur = actifAcheteur;
    }

    public String getNomAcheteur() {
        return nomAcheteur;
    }

    public void setNomAcheteur(String nomAcheteur) {
        this.nomAcheteur = nomAcheteur;
    }

    public String getPrenomAcheteur() {
        return prenomAcheteur;
    }

    public void setPrenomAcheteur(String prenomAcheteur) {
        this.prenomAcheteur = prenomAcheteur;
    }

    public String getPseudoAcheteur() {
        return pseudoAcheteur;
    }

    public void setPseudoAcheteur(String pseudoAcheteur) {
        this.pseudoAcheteur = pseudoAcheteur;
    }

    public String getMdpAcheteur() {
        return mdpAcheteur;
    }

    public void setMdpAcheteur(String mdpAcheteur) {
        this.mdpAcheteur = mdpAcheteur;
    }

    public String getEmailAcheteur() {
        return emailAcheteur;
    }

    public void setEmailAcheteur(String emailAcheteur) {
        this.emailAcheteur = emailAcheteur;
    }

    public String getTelAcheteur() {
        return telAcheteur;
    }

    public void setTelAcheteur(String telAcheteur) {
        this.telAcheteur = telAcheteur;
    }

    public boolean getActifAcheteur() {
        return actifAcheteur;
    }
 
    public void setActifAcheteur(boolean actifAcheteur) {
        this.actifAcheteur = actifAcheteur;
    }
    
    public ArrayList<Adresse> getListAdresseAcheteur() {
        return listAdresseAcheteur;
    }

    public void setListAdresseAcheteur(ArrayList<Adresse> listAdresseAcheteur) {
        this.listAdresseAcheteur = listAdresseAcheteur;
    }

    public int getIdAcheteur() {
        return idAcheteur;
    }

    public void setIdAcheteur(int idAcheteur) {
        this.idAcheteur = idAcheteur;
    }
    
    
    
    /* fonction qui permet a la commande de se creer
    
    */
    
    public Commande passerCommande(){
        Commande commande = null;
        
        return commande;
    }
    
    
}
