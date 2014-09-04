
package bean.commande;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class Taxe implements Serializable{
    private float tva;
    private int idTva; // ajout eddy

    public Taxe() {
    }

    public Taxe(float tva) {
        this.tva = tva;
    }

    public Taxe(int idTva, float tva) { // ajout eddy
        this.idTva = idTva;
        this.tva = tva;
    }    
    
    public float getTva() {
        return tva;
    }

    public void setTva(float tva) {
        this.tva = tva;
    }

    public int getIdTva() { // ajout eddy
        return idTva;
    }

    public void setIdTva(int idTva) {  // ajout eddy
        this.idTva = idTva;
    }
    
    @Override                       // ajout eddy
    public String toString() {
        return Float.toString(tva);
    }
    
}
