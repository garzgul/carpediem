// a finir

package bean.commande;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class Promotion implements Serializable{
    
    private int promotionId;
    private String promotionNom;

    public Promotion() {
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionNom() {
        return promotionNom;
    }

    public void setPromotionNom(String promotionNom) {
        this.promotionNom = promotionNom;
    }
    
    
    
}
