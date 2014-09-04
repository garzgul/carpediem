
package bean.commande;

import java.io.Serializable;

/**
 * Enumeration pour le suivi de la livraison
 * cf regles de gestion
 * @author Gael
 */
public class SuiviLivraison implements Serializable{
    private livraison suiviL;

    public SuiviLivraison() {
    }
    
    public SuiviLivraison(livraison suiviL) {
        this.suiviL = suiviL;
    }
    
    public livraison getSuiviL() {
        return suiviL;
    }

    public void setSuiviL(livraison suiviL) {
        this.suiviL = suiviL;
    }

    

    
    
    
    
}
