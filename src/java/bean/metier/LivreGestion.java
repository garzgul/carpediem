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
public class LivreGestion implements Serializable {

    private LivreDAO lDao;

    public LivreGestion() throws NamingException {
        lDao = new LivreDAO();
    }

    public List<Livre> findAll(String s) throws SQLException {
        return lDao.findAll(s);
    }
    
    public Livre findLivre(int id) throws SQLException, NamingException{
        return lDao.find(id);
    }
}
