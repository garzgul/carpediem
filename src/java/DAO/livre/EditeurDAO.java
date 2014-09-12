
package DAO.livre;

import DAO.utils.DAO;
import DAO.utils.FournirConnectionIt;
import DAO.utils.MaConnexionBDD;
import bean.produit.Editeur;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

public class EditeurDAO extends DAO<Editeur> implements Serializable{
    private FournirConnectionIt fc;

    public EditeurDAO() throws NamingException {
        this.fc = new MaConnexionBDD();
    }
    
    
    

    

    @Override
    public boolean update(Editeur obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Editeur obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Editeur find(int id) throws SQLException, NamingException {
        Connection cnn = fc.fournir();
        Editeur e = null;
        String req ="{call findEditeurParId(?)}";
        CallableStatement cstmt = cnn.prepareCall(req);
        cstmt.setInt(1, id);
        ResultSet rs = cstmt.executeQuery();
        while (rs.next()) {
            e = new Editeur(id, rs.getString("editeur_nom"));
        }
        rs.close();
        cstmt.close();
        cnn.close();
        
        return e;
    }

    @Override
    public Editeur find(String s) throws SQLException, NamingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Editeur> findAll(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Editeur create(Editeur obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
