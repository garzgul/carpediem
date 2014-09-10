
package DAO.commande;

import DAO.utils.DAO;
import DAO.utils.FournirConnectionIt;
import DAO.utils.MaConnexionBDD;
import bean.commande.Commande;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

public class CommandeDAO extends DAO<Commande> implements Serializable{
    
    private FournirConnectionIt fc;

    public CommandeDAO() throws NamingException {
        fc = new MaConnexionBDD();
    }

    @Override
    public Commande create(Commande obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Commande obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Commande obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Commande find(int id) throws SQLException, NamingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Commande find(String s) throws SQLException, NamingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commande> findAll(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public String getLastNoCde() throws SQLException{
        String res = null;
        Connection cnn = fc.fournir();
        String req = "select * from commande where cde_date=(select max(c.cde_date) from commande c where c.id_commande=commande.id_commande)";
        PreparedStatement pstmt = cnn.prepareStatement(req);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            res = rs.getString("cde_numcommande");
        }
        rs.close();
        pstmt.close();
        cnn.close();
        return res;
    }
    
    public boolean createCde(Commande cde) throws SQLException{
        boolean res = false;
        Connection cnn = fc.fournir();
        String proc ="{call createCDE(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        CallableStatement cstmt = cnn.prepareCall(proc);
        
        
        
        return res;
    }
}
