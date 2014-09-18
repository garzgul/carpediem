
package DAO.commande;

import DAO.utils.DAO;
import DAO.utils.FournirConnectionIt;
import DAO.utils.MaConnexionBDD;
import bean.commande.FraisDePort;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

public class FraisDePortDAO extends DAO<FraisDePort> implements Serializable{
    private FournirConnectionIt fc;

    public FraisDePortDAO() throws NamingException {
        fc = new MaConnexionBDD();
    }
    
    
    
    
    

    @Override
    public FraisDePort create(FraisDePort obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(FraisDePort obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(FraisDePort obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FraisDePort find(int id) throws SQLException, NamingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FraisDePort find(String s) throws SQLException, NamingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public FraisDePort find() throws SQLException, NamingException {
        FraisDePort frais = null;
        Connection cnn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            cnn = fc.fournir();
            String req="Select * from fraisdeport";
             pstmt = cnn.prepareStatement(req);
            rs = pstmt.executeQuery();
            while(rs.next()){
                frais = new FraisDePort(Integer.valueOf(rs.getString("id_fraisdeport")), Float.valueOf(rs.getString("fraisdeport_cout")));
            }
        }finally{
            rs.close();
            pstmt.close();
            cnn.close();
        }
        
        return frais;
    }

    @Override
    public List<FraisDePort> findAll(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
