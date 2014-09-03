
package bean.commande;

import bean.produit.Livre;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class DetailCommande {
    // on devrait probablement faire un tableau 4d avec afin de pouvoir s'y retrouver
    // arraylist pour le moment pas au point a moins de modifier l'objet livre
    //
    private ArrayList<Livre> detailCde;
    private int qteLivreCde;
    private float htLivreCde;
    private float tvaLivreCde;

    public DetailCommande() {
    }

    public DetailCommande(ArrayList<Livre> detailCde, int qteLivreCde, float htLivreCde, float tvaLivreCde) {
        this.detailCde = detailCde;
        this.qteLivreCde = qteLivreCde;
        this.htLivreCde = htLivreCde;
        this.tvaLivreCde = tvaLivreCde;
    }

    public ArrayList<Livre> getDetailCde() {
        return detailCde;
    }

    public void setDetailCde(ArrayList<Livre> detailCde) {
        this.detailCde = detailCde;
    }

    public int getQteLivreCde() {
        return qteLivreCde;
    }

    public void setQteLivreCde(int qteLivreCde) {
        this.qteLivreCde = qteLivreCde;
    }

    public float getHtLivreCde() {
        return htLivreCde;
    }

    public void setHtLivreCde(float htLivreCde) {
        this.htLivreCde = htLivreCde;
    }

    public float getTvaLivreCde() {
        return tvaLivreCde;
    }

    public void setTvaLivreCde(float tvaLivreCde) {
        this.tvaLivreCde = tvaLivreCde;
    }

    
    
    
}
