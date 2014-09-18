package DAO.livre;

import DAO.utils.DAO;
import DAO.utils.FournirConnectionIt;
import DAO.utils.MaConnexionBDD;
import bean.produit.Promotion;
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
 * @author cdi108
 */
public class PromotionDAO extends DAO<Promotion> implements Serializable {

    private FournirConnectionIt fc;

    public PromotionDAO() throws NamingException {
        this.fc = new MaConnexionBDD();
    }

    @Override
    public Promotion create(Promotion obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Promotion obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Promotion obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Promotion find(int id) throws SQLException, NamingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Promotion find(String s) throws SQLException, NamingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Promotion> findAll(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // liste totale des Promo
    public List<Promotion> listePromotion() throws SQLException, NamingException {
        Promotion st;
        List<Promotion> lsT = new ArrayList<>();
        String req = "{call findAllPromo}";
        Connection cnn = fc.fournir();
        CallableStatement cs = cnn.prepareCall(req);
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            String id_Promo = rs.getString("id_promotion");
            String Promo_nom = rs.getString("prom_nom");
            String Promo_descriptif = rs.getString("prom_descriptif");
            st = new Promotion(Integer.valueOf(id_Promo), Promo_nom, Promo_descriptif);
            lsT.add(st);
        }
        rs.close();
        cs.close();
        cnn.close();
        return lsT;
    }

}
