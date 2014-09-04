
package bean.commande;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class Rubrique implements Serializable{
    
    private int rubriqueId;
    private String rubriqueNom;

    public Rubrique() {
    }

    public int getRubriqueId() {
        return rubriqueId;
    }

    public void setRubriqueId(int rubriqueId) {
        this.rubriqueId = rubriqueId;
    }

    public String getRubriqueNom() {
        return rubriqueNom;
    }

    public void setRubriqueNom(String rubriqueNom) {
        this.rubriqueNom = rubriqueNom;
    }
    
    
    
}
