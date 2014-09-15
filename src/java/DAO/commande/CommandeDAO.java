
package DAO.commande;

import DAO.utils.DAO;
import DAO.utils.FournirConnectionIt;
import DAO.utils.MaConnexionBDD;
import bean.commande.Commande;
import bean.commande.LignePanier;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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
        
        // creation de la commande en table commande
        Connection cnn = fc.fournir();
        String proc ="{call createCDE(?,?,?,?,?,?,?,?)}";
        CallableStatement cstmt = cnn.prepareCall(proc);
        cstmt.setString(1, cde.getNumCde());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cstmt.setString(2, sdf.format(cde.getDateCde()));
        cstmt.setFloat(3, cde.getHtCde());
        cstmt.setFloat(4, cde.getTvaCde());
        // parametre de paiement toujours a true (simulation de paiement)
        cstmt.setInt(5, 1);
        cstmt.setString(6, cde.getModeLivraison().toString());
        cstmt.setString(7, cde.getModePaiement());
        cstmt.registerOutParameter(8, java.sql.Types.BIGINT);
        cstmt.execute();
        Long idCde = cstmt.getLong(8);
        
        // remplissage des lignes de commandes dans la table detailcommande
        proc ="{call createDetailCDE(?,?,?,?,?)}";
        cstmt= cnn.prepareCall(proc);
        HashMap<Integer,LignePanier> listeCde = cde.getDetailCde();
        for (LignePanier lp : listeCde.values()){
            //stockage des valeurs de la ligne dans les parametres
            cstmt.setLong(1, idCde);
            cstmt.setInt(2, lp.getL().getId());
            cstmt.setInt(3, lp.getQte());
            cstmt.setFloat(4,lp.getPrixHT());
            cstmt.setFloat(5, lp.getTva());
            //appel de la procedure stockée pour la creation de chaque ligne
            cstmt.execute();
        }
        res =true;
        cstmt.close();
        cnn.close();
        
        return res;
    }
}
