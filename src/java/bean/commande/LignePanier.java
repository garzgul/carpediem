
package bean.commande;

import bean.produit.Livre;
import java.io.Serializable;

public class LignePanier implements Serializable{
    private Livre l;
    private int qte;
    private float prixTTC;
    private float tva; // montant de la tva
    private float prixHT;
    private float prix;
    private float poids;
    private float poidsTotal;

    public LignePanier(Livre l) throws Exception {
        if (l==null){
            throw new Exception("un livre ne peut etre null");
        }
        this.l=l;
        qte=1;
        this.prixHT=l.getPrix();
        this.tva=l.getPrix()*(l.getTva().getTva()/100);
        this.prixTTC=prixHT+tva;
        this.poids = l.getPoids();
        
        
        
    }

    public Livre getL() {
        return l;
    }

    public int getQte() {
        return qte;
    }

    public float getPrix() {
        return qte*prixTTC;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public float getPrixTTC() {
        return prixTTC;
    }

    public float getTva() {
        return tva;
    }

    public float getPrixHT() {
        return prixHT;
    }
    
    public float getPoidsTotal(){
        return poids*qte;
    }

    @Override
    public String toString() {
        return l+" -> "+qte;
    }
    
}
