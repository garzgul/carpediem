
package bean.commande;

import bean.produit.Livre;
import java.io.Serializable;

public class LignePanier implements Serializable{
    private Livre l;
    private int qte;
    private float prix;
}
