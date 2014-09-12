
package DAO.panier;

import DAO.utils.DAO;
import DAO.utils.FournirConnectionIt;
import DAO.utils.MaConnexionBDD;
import bean.commande.Taxe;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

public class TvaDAO extends DAO<Taxe> implements Serializable{
    private FournirConnectionIt fc;

    public TvaDAO() throws NamingException {
        this.fc = new MaConnexionBDD();
    }
    
    

    

    @Override
    public boolean update(Taxe obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Taxe obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param id id du livre 
     * @return objet Taxe
     * @throws SQLException
     * @throws NamingException 
     */    
    @Override
    public Taxe find(int id) throws SQLException, NamingException {
        Taxe tva = null;
        Connection cnn = fc.fournir();
        String req = "Select * from tva join taxe on taxe.id_tva=tva.id_tva where taxe.id_livre=?";
        PreparedStatement pstmt = cnn.prepareStatement(req);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            tva = new Taxe(Float.valueOf(rs.getString("tva_taux")));
        }
        return tva;
    }

    @Override
    public Taxe find(String s) throws SQLException, NamingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Taxe> findAll(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Taxe create(Taxe obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
