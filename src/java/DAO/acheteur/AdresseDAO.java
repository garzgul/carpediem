package DAO.acheteur;

import DAO.utils.DAO;
import DAO.utils.FournirConnectionIt;
import bean.acheteur.Adresse;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AdresseDAO extends DAO<Adresse> implements Serializable{
    private FournirConnectionIt fc;

    public AdresseDAO(FournirConnectionIt fc) {
        this.fc = fc;
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
            if(pStmt != null){
                pStmt.close();
            }
            if(cnn != null){
                cnn.close();
            }
            
        }
        if(rs != 0){
            return obj;
        }
        
        return null;
    }

    @Override
    public boolean update(Adresse obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Adresse obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
