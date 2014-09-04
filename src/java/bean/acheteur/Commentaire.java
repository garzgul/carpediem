
package bean.acheteur;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Eddy
 */
public class Commentaire implements Serializable{
    
    private int idAcheteur;
    private int idLivre;
    private int avisRating;
    private String avisTexte;
    private boolean avisActif;
    private Date avisDate;
    
    public Commentaire(int idAcheteur,int idLivre, int avisRating, String avisTexte, boolean avisActif, Date avisDate){
        this.idAcheteur=idAcheteur;
        this.idLivre=idLivre;
        this.avisRating=avisRating;
        this.avisTexte=avisTexte;
        this.avisActif=avisActif;
        this.avisDate=avisDate;
    }

    public int getIdAcheteur() {
        return idAcheteur;
    }

    public int getIdLivre() {
        return idLivre;
    }
    
    
}
