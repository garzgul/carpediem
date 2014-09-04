
package bean.produit;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class MotClef implements Serializable{
    private String motcle;
    private int idMotCle;

    public MotClef() {
    }

    public MotClef(String motcle) {
        this.motcle = motcle;
    }

    public String getMotcle() {
        return motcle;
    }

    public void setMotcle(String motcle) {
        this.motcle = motcle;
    }

    public int getIdMotCle() {
        return idMotCle;
    }

    public void setIdMotCle(int idMotCle) {
        this.idMotCle = idMotCle;
    }
    
    
    
}
