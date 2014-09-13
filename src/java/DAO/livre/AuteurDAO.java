
package DAO.livre;

import DAO.utils.DAO;
import DAO.utils.FournirConnectionIt;
import DAO.utils.MaConnexionBDD;
import bean.produit.Auteur;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

public class AuteurDAO extends DAO<Auteur> implements Serializable{
    private FournirConnectionIt fc;

    public AuteurDAO() throws NamingException {
        this.fc = new MaConnexionBDD();
    }
    
    

    @Override
    public Auteur create(Auteur obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Auteur obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Auteur obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Auteur find(int id) throws SQLException, NamingException {
       Connection cnn = fc.fournir();
        Auteur a = null;
        String req = "{call findAuteurParId(?)}";
        CallableStatement cstmt = cnn.prepareCall(req);
        System.out.println(">>>>>>>>>auteur id = "+id);
        cstmt.setInt(1, id);
        ResultSet rs = cstmt.executeQuery();
        while (rs.next()) {
            a = new Auteur(id, rs.getString("auteur_nom"));
            if (!"inconnu".equalsIgnoreCase(a.getNomAuteur())) {
                a.setPrenomAuteur(rs.getString("auteur_prenom"));
            }
        }
        rs.close();
        cstmt.close();
        cnn.close();
        
        return a;
    }

    @Override
    public Auteur find(String s) throws SQLException, NamingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Auteur> findAll(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
