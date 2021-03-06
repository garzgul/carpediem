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
    public List<Promotion> listeToutesPromotions() throws SQLException, NamingException {
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

    // liste des Promo actuelles
    public List<Promotion> listePromotionActuelle() throws SQLException, NamingException {
        Promotion st;
        List<Promotion> lsT = new ArrayList<>();
        String req = "{call findAllPromoEnCours}";
        Connection cnn = fc.fournir();
        CallableStatement cs = cnn.prepareCall(req);
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            String id_Promo = rs.getString("id_promotion");
            String Promo_nom = rs.getString("prom_nom");
            String Promo_descriptif = rs.getString("prom_descriptif");
            String datedbt = rs.getString("remise_datedbt");
            String datefin = rs.getString("remise_datefin");
            st = new Promotion(Integer.valueOf(id_Promo), Promo_nom, Promo_descriptif);
            lsT.add(st);
        }
        rs.close();
        cs.close();
        cnn.close();
        return lsT;
    }

    // liste des livres en Promo actuelles limités à 3
    public List<Promotion> listeLivrePromotionActuelle3() throws SQLException, NamingException {
        Promotion st;
        List<Promotion> lsT = new ArrayList<>();
        String req = "{call findAllLivresPromo3}";
        Connection cnn = fc.fournir();
        CallableStatement cs = cnn.prepareCall(req);
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            String id_Promo = rs.getString("id_promotion");
            String Promo_nom = rs.getString("prom_nom");
            String Promo_descriptif = rs.getString("prom_descriptif");
            String datedbt = rs.getString("remise_datedbt");
            String datefin = rs.getString("remise_datefin");
            String remisetaux = rs.getString("remise_taux");
            String remisemontant = rs.getString("remise_montant");
            String idLivre = rs.getString("id_livre");
            String titreLivre = rs.getString("livre_titre");
            st = new Promotion(Integer.valueOf(id_Promo), Promo_nom, Promo_descriptif);
            lsT.add(st);
        }
        rs.close();
        cs.close();
        cnn.close();
        return lsT;
    }

}
