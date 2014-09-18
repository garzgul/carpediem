package bean.metier;

import DAO.livre.LivreDAO;
import DAO.livre.PromotionDAO;
import bean.produit.Livre;
import bean.produit.Promotion;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author cdi108
 */
public class PromoGestion implements Serializable{
     private PromotionDAO PDao;

    public PromoGestion() throws NamingException {
        PDao = new PromotionDAO();
    }
    
        public List<Promotion> listeLivrePromotionActuelle3() throws SQLException, NamingException {
        return PDao.listeLivrePromotionActuelle3();
    }
    
    
    
    
    
    
    
}
