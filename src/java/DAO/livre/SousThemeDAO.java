
package DAO.livre;

import DAO.utils.DAO;
import DAO.utils.FournirConnectionIt;
import DAO.utils.MaConnexionBDD;
import bean.produit.SousTheme;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Eddy
 */
public class SousThemeDAO extends DAO<SousTheme> implements Serializable{
    
    private FournirConnectionIt fc;
        
    public SousThemeDAO() throws NamingException {
        this.fc = new MaConnexionBDD();
    }
    
    @Override
    public SousTheme create(SousTheme obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(SousTheme obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(SousTheme obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SousTheme find(int id) throws SQLException, NamingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public SousTheme find(String s) throws SQLException, NamingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
    
    @Override
    public List<SousTheme> findAll(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // liste totale des sous themes
    public List<SousTheme> listeSousThemes() throws SQLException, NamingException {
        SousTheme st;
        List<SousTheme> lsT = new ArrayList<>();
        String req = "{call listeSousThemes}";
        Connection cnn = fc.fournir();
        CallableStatement cs = cnn.prepareCall(req);
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            String id_soustheme = rs.getString("id_soustheme");
            String id_theme = rs.getString("id_theme");
            String soustheme_nom = rs.getString("soustheme_nom");
            st = new SousTheme(Integer.valueOf(id_soustheme), soustheme_nom);
            lsT.add(st);
        }
        rs.close();
        cs.close();
        cnn.close();
        return lsT;
        }
    
}
