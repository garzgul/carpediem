
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

    

    

    

    
    
    
}
