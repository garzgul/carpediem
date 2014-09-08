
package bean.commande;

import bean.produit.Livre;
import java.io.Serializable;

public class LignePanier implements Serializable{
    private Livre l;
    private int qte;
    private float prix;

    public LignePanier(Livre l) throws Exception {
        if (l==null){
            throw new Exception("un livre ne peut etre null");
        }
        qte=1;
        this.l=l;
        
    }

    public Livre getL() {
        return l;
    }

    public int getQte() {
        return qte;
    }

    public float getPrix() {
        return qte*l.getPrix();
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
    
    
}
