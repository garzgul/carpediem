
package DAO.livre;

import DAO.utils.DAO;
import DAO.utils.FournirConnectionIt;
import DAO.utils.MaConnexionBDD;
import bean.produit.Theme;
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
public class ThemeDAO extends DAO<Theme> implements Serializable{
    
    private FournirConnectionIt fc;
        
    public ThemeDAO() throws NamingException {
        this.fc = new MaConnexionBDD();
    }
    
    @Override
    public Theme create(Theme obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Theme obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Theme obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Theme find(int id) throws SQLException, NamingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Theme find(String s) throws SQLException, NamingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
    
    @Override
    public List<Theme> findAll(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // liste totale des themes
    public List<Theme> listeThemes() throws SQLException, NamingException {
        Theme t;
        List<Theme> lT = new ArrayList<>();
        String req = "{call listeThemes}";
        Connection cnn = fc.fournir();
        CallableStatement cs = cnn.prepareCall(req);
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            String id_theme = rs.getString("id_theme");
            String theme_nom = rs.getString("theme_nom");
            t = new Theme(Integer.valueOf(id_theme), theme_nom);
            lT.add(t);
        }
        rs.close();
        cs.close();
        cnn.close();
        return lT;
        }
    
}
