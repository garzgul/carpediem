package DAO.acheteur;

import DAO.utils.DAO;
import DAO.utils.FournirConnectionIt;
import DAO.utils.MaConnexionBDD;
import bean.acheteur.Adresse;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

public class AdresseDAO extends DAO<Adresse> implements Serializable {

    private FournirConnectionIt fc;

    public AdresseDAO(FournirConnectionIt fc) {
        this.fc = fc;
    }

    public AdresseDAO() throws NamingException {
        fc = new MaConnexionBDD();
    }
    

    @Override
    public Adresse create(Adresse obj) throws SQLException {
        Connection cnn = null;
        PreparedStatement pStmt = null;
        int rs = 0;
        try {
            cnn = fc.fournir();
            String pReq = "INSERT INTO adresse (id_acheteur, adresse_chp1, adresse_chp2, adresse_cp, adresse_ville, adresse_pays, adresse_actif)"
                    + "VALUES (?, ?, ?, ?, ?, ?, 1)";

            pStmt = cnn.prepareCall(pReq);
            pStmt.setInt(1, obj.getIdAcheteur());
            pStmt.setString(2, obj.getAdresseAcheteur1());
            pStmt.setString(3, obj.getAdresseAcheteur2());
            pStmt.setString(4, obj.getCpAcheteur());
            pStmt.setString(5, obj.getVilleAcheteur());
            pStmt.setString(6, obj.getPaysAcheteur());
            rs = pStmt.executeUpdate();
        } finally {
            if (pStmt != null) {
                pStmt.close();
            }
            if (cnn != null) {
                cnn.close();
            }

        }
        if (rs != 0) {
            return obj;
        }

        return null;
    }

    @Override
    public boolean update(Adresse obj) throws SQLException {
        Connection cnn = null;
        PreparedStatement pStmt = null;
        boolean test = false;
        int rs = 0;

        try {
            cnn = fc.fournir();
            String pReq = "UPDATE adresse SET adresse_chp1 = ?, adresse_chp2 = ?,"
                    + " adresse_cp = ?, adresse_ville = ?, adresse_pays = ?"
                    + " WHERE id_acheteur = ?";
            pStmt = cnn.prepareCall(pReq);
            pStmt.setString(1, obj.getAdresseAcheteur1());
            pStmt.setString(2, obj.getAdresseAcheteur2());
            pStmt.setString(3, obj.getCpAcheteur());
            pStmt.setString(4, obj.getVilleAcheteur());
            pStmt.setString(5, obj.getPaysAcheteur());
            pStmt.setInt(6, obj.getIdAcheteur());
            rs = pStmt.executeUpdate();

        } finally {
            if (pStmt != null) {
                pStmt.close();
            }
            if (cnn != null) {
                cnn.close();
            }

        }
        if (rs != 0) {
            test = true;
        }
        return test;
    }

    @Override
    public boolean delete(Adresse obj) throws SQLException {
        Connection cnn = null;
        PreparedStatement pStmt = null;
        int rs = 0;
        boolean test = false;

        try {
            cnn = fc.fournir();
            String pReq = "UPDATE adresse SET adresse_actif = 0"
                    + " WHERE id_acheteur = ?";
            pStmt = cnn.prepareCall(pReq);
            pStmt.setInt(1, obj.getIdAcheteur());
            rs = pStmt.executeUpdate();

        } finally {
            if (pStmt != null) {
                pStmt.close();
            }
            if (cnn != null) {
                cnn.close();
            }

        }
        if (rs != 0) {
            test = true;
        }
        return test;
    }

    @Override
    public Adresse find(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Adresse find(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Adresse> findAll(String s) throws SQLException {
        
        
        
        
        
        
          return null;
    }
    
    public List<Adresse> findAll(int id) throws SQLException {
        
        Connection cnn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        List<Adresse> toutAdresse = null;
        boolean adresseActif = false;
        boolean adresseFav = false;
        
        
        try{
            cnn = fc.fournir();
            String pReq = "SELECT * FROM adresse"
                    +"WHERE id-acheteur = ?";
            pStmt = cnn.prepareCall(pReq);
            pStmt.setInt(1, id);
            rs = pStmt.executeQuery();
            while(rs.next()){
                int idAdresse = rs.getInt("id_adresse");
                String adresse1 = rs.getString("adresse_chp1");
                String adresse2 = rs.getString("adresse_chap2");
                String cp = rs.getString("adresse_cp");
                String ville = rs.getString("adresse_ville");
                String pays = rs.getString("adresse_pays");
                int actif = rs.getInt("adresse_actif");
                int fav = rs.getInt("adresse_fav");
                if(1 == actif){
                    adresseActif = true;
                }
                if(1 == fav){
                    adresseFav = true;
                }
                Adresse adresse = new Adresse(adresse1, adresse2, cp, ville, pays, adresseFav);
                adresse.setIdAcheteur(id);
                adresse.setIdadresse(idAdresse);
                adresse.setAdressefav(adresseFav);
                toutAdresse.add(adresse);
            }
            
        }finally{
            if(rs != null){
                rs.close();
            }
            if(pStmt != null){
                pStmt.close();
            }
            if(cnn != null){
                cnn.close();
            }
        }
      
        return toutAdresse;
    }

}
