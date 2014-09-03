
package bean.commande;

/**
 *
 * @author user
 */
public class FraisDePort {
    private float fraisDePort;
    private int idfraisdeport; // ajout eddy

    public FraisDePort() {
    }

    public FraisDePort(float fraisDePort) {
        this.fraisDePort = fraisDePort;
    }
    
public FraisDePort(int idfraisdeport, float fraisDePort) { // ajout eddy
        this.idfraisdeport = idfraisdeport;
        this.fraisDePort = fraisDePort;
    }      

    public float getFraisDePort() {
        return fraisDePort;
    }

    public void setFraisDePort(float fraisDePort) {
        this.fraisDePort = fraisDePort;
    }

    public int getIdfraisdeport() { // ajout eddy
        return idfraisdeport;
    }

    public void setIdfraisdeport(int idfraisdeport) { // ajout eddy
        this.idfraisdeport = idfraisdeport;
    }

    @Override                       // ajout eddy
    public String toString() {
        return Float.toString(fraisDePort);
    }    
    
}
