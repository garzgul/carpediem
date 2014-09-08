
package bean.metier;

import DAO.livre.LivreDAO;
import bean.produit.Livre;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author eddy
 */
public class livreGestion implements Serializable{
    
    private LivreDAO lDao;
    
    public livreGestion() throws NamingException{
        lDao=new LivreDAO();
    }
    
    public List<Livre> findAll (String s) throws SQLException{
        return lDao.findAll(s);
    }
}
