
package bean.commande;

import bean.acheteur.Acheteur;
import bean.acheteur.Adresse;
import java.util.Date;

/**
 *
 * @author user
 */
public class Commande {
    private String numCde;
    private Date dateCde;
    private float htCde;
    private float tvaCde;
    private boolean payementCde;
    private ModeLivraison modeLivraison;
    private DetailCommande detailCde;
    private Acheteur acheteurCde;
    private SuiviLivraison suiviCde;
    private Adresse adresseCde;
    private FraisDePort fraisCde;

    public Commande() {
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

    public DetailCommande getDetailCde() {
        return detailCde;
    }

    public void setDetailCde(DetailCommande detailCde) {
        this.detailCde = detailCde;
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
    
    
    
    


}
