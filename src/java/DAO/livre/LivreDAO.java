
package DAO.livre;

import DAO.utils.DAO;
import DAO.utils.FournirConnectionIt;
import DAO.utils.MaConnexionBDD;
import bean.produit.Livre;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class LivreDAO extends DAO<Livre> implements Serializable{
    private FournirConnectionIt fc;

    public LivreDAO() throws NamingException {
        fc = new MaConnexionBDD();
    }


    @Override
    public boolean create(Livre obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Livre obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Livre obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Livre find(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Livre find(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Livre> findAll (String s) throws SQLException{
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        //recherche par (partie) du titre de livre
        Livre l=null;
        List<Livre> lL = new ArrayList<>();
        String req = "select * from livre where livre_titre like '%?%'"; // remplacer par un appel a une PROCEDURE STOCKEE !!!!
        //String req = "{call findAllLivres(?)}";
        Connection cnn = fc.fournir();
        PreparedStatement pStm = cnn.prepareStatement(req);
        //CallableStatement cs=cnn.prepareCall(req);
        //cs.setString(1,s);
        pStm.setString(1,s);
        ResultSet rs = pStm.executeQuery(req);
        while (rs.next()) {
            //rs.getString("id_livre");
            String titre = rs.getString("livre_titre");
            
            l=new Livre(null,null,titre,0,0,true);
            lL.add(l);
        }
        rs.close();
        pStm.close();
        cnn.close();
        
        return lL;
    }    
    
    
    
}
