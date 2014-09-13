
package bean.commande;

import bean.produit.Livre;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class DetailCommande implements Serializable{
    private Livre l;
    private int qte;
    private float prixHT;
    private float tva;
    private float prixTTC;
    private float poids;
    private float poidsTotal;
    private float prix;
    

    public DetailCommande() {
    }

    public DetailCommande(Livre l) throws Exception {
        if (l==null){
            throw new Exception("un livre ne peut etre null");
        }
        this.l=l;
        
        this.prixHT=l.getPrix();
        this.tva=l.getPrix()*l.getTva().getTva();
        this.prixTTC=prixHT+tva;
        this.poids = l.getPoids();
    }

    public Livre getL() {
        return l;
    }

    public int getQte() {
        return qte;
    }

    public float getPrixHT() {
        return prixHT;
    }

    public float getTva() {
        return tva;
    }

    public float getPrixTTC() {
        return prixTTC;
    }

    public float getPoids() {
        return poids;
    }

    public void setL(Livre l) {
        this.l = l;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setPrixHT(float prixHT) {
        this.prixHT = prixHT;
    }

    public void setTva(float tva) {
        this.tva = tva;
    }

    public void setPrixTTC(float prixTTC) {
        this.prixTTC = prixTTC;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }
    
    

    

    

    

    
    
    
}
