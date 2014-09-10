
package bean.commande;

import bean.acheteur.Acheteur;
import bean.acheteur.Adresse;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class Commande implements Serializable{
    private String numCde;
    private Date dateCde;
    private float htCde;
    private float tvaCde;
    private float ttcCde;
    private boolean payementCde;
    private ModeLivraison modeLivraison;
    private HashMap<Integer,LignePanier> detailCde;
    private Acheteur acheteurCde;
    private SuiviLivraison suiviCde;
    private Adresse adresseCde;
    private FraisDePort fraisCde;
    private float poidsCde;

    public Commande() {
    }

    
    
    public Commande(String numCde, HashMap<Integer, LignePanier> detailCde, Acheteur acheteurCde, Adresse adresseCde) {
        this.numCde = numCde;
        this.detailCde = detailCde;
        this.acheteurCde = acheteurCde;
        this.adresseCde = adresseCde;
        dateCde = new Date();
    }

    
    
    

    public Commande(String numCde, Date dateCde, float htCde, float tvaCde, Acheteur acheteurCde, Adresse adresseCde) {
        this.numCde = numCde;
        this.dateCde = dateCde;
        this.htCde = htCde;
        this.tvaCde = tvaCde;
        this.acheteurCde = acheteurCde;
        this.adresseCde = adresseCde;
    }

    public String getNumCde() {
        return numCde;
    }

    public void setNumCde(String numCde) {
        this.numCde = numCde;
    }

    public Date getDateCde() {
        return dateCde;
    }

    public void setDateCde(Date dateCde) {
        this.dateCde = dateCde;
    }

    public float getHtCde() {
        return htCde;
    }

    public void setHtCde(float htCde) {
        this.htCde = htCde;
    }

    public float getTvaCde() {
        return tvaCde;
    }

    public void setTvaCde(float tvaCde) {
        this.tvaCde = tvaCde;
    }

    public boolean isPayementCde() {
        return payementCde;
    }

    public void setPayementCde(boolean payementCde) {
        this.payementCde = payementCde;
    }

    public ModeLivraison getModeLivraison() {
        return modeLivraison;
    }

    public void setModeLivraison(ModeLivraison modeLivraison) {
        this.modeLivraison = modeLivraison;
    }

    

    public Acheteur getAcheteurCde() {
        return acheteurCde;
    }

    public void setAcheteurCde(Acheteur acheteurCde) {
        this.acheteurCde = acheteurCde;
    }

    public SuiviLivraison getSuiviCde() {
        return suiviCde;
    }

    public void setSuiviCde(SuiviLivraison suiviCde) {
        this.suiviCde = suiviCde;
    }

    public Adresse getAdresseCde() {
        return adresseCde;
    }

    public void setAdresseCde(Adresse adresseCde) {
        this.adresseCde = adresseCde;
    }

    public FraisDePort getFraisCde() {
        return fraisCde;
    }

    public void setFraisCde(FraisDePort fraisCde) {
        this.fraisCde = fraisCde;
    }

    public HashMap<Integer, LignePanier> getDetailCde() {
        return detailCde;
    }

    public float getPoidsCde() {
        return poidsCde;
    }

    public float getTtcCde() {
        return ttcCde;
    }

    public void setTtcCde(float ttcCde) {
        this.ttcCde = ttcCde;
    }
    
    
    
    


}
