package bean.metier;

import DAO.livre.LivreDAO;
import bean.produit.Livre;
import bean.produit.SousTheme;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
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
    
    public List<Livre> findAllByTheme(int i) throws SQLException {
        return lDao.findAllByTheme(i);
    }
    
    public Livre findLivre(int id) throws SQLException, NamingException{
        return lDao.find(id);
    }
    
    public List<Livre> filtrer(List<Livre> lL, int id_soustheme) {
        List<Livre> lLfiltree=new ArrayList();
        SousTheme st=null;
        for (Livre l:lL){
            st=l.getSousTheme();
            if(st.getIdSousTheme()==id_soustheme){
                lLfiltree.add(l);
            }
        }
        return lLfiltree;
    }
    
}
