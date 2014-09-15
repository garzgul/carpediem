package DAO.acheteur;

import DAO.utils.DAO;
import DAO.utils.FournirConnectionIt;
import DAO.utils.MaConnexionBDD;
import bean.acheteur.Acheteur;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

public class AcheteurDAO extends DAO<Acheteur> implements Serializable {

    private FournirConnectionIt fc;

    public AcheteurDAO() throws NamingException {
        fc = new MaConnexionBDD();
    }

    @Override
    public Acheteur create(Acheteur obj) throws SQLException {
        Connection cnn = fc.fournir();
        obj.setActifAcheteur(true);

        //Preparation de la requete d'insertion d'un nouveau acheteur
        String req = "{call insererAcheteur(?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cstmt = cnn.prepareCall(req);
        cstmt.setString(1, obj.getNomAcheteur());
        cstmt.setString(2, obj.getPrenomAcheteur());
        cstmt.setString(3, obj.getPseudoAcheteur());
        cstmt.setString(4, obj.getMdpAcheteur());
        cstmt.setString(5, obj.getEmailAcheteur());
        cstmt.setString(6, obj.getTelAcheteur());
        cstmt.setInt(7, 1);
        cstmt.registerOutParameter(8, java.sql.Types.INTEGER);
        int rs = cstmt.executeUpdate();

        if (1 == rs) {
            int id = cstmt.getInt(8);
            obj.setIdAcheteur(id);

            return obj;
        }
        cstmt.close();
        cnn.close();
        return null;
    }

    @Override
    public boolean update(Acheteur obj) throws SQLException {
        
        Connection cnn = fc.fournir();
        
        int rs = 0;
        boolean test = false;
        
        String pReq = "UPDATE acheteur"
                + "SET ach_nom = '?', ach_prenom = '?', ach_pseudo = '?',"
                + "ach_mdp = '?', ach_email = '?', ach_tel = '?',"
                + "WHERE id_acheteur = '?' AND ach_actif = 1";
        
        PreparedStatement pStmt = cnn.prepareCall(pReq);
        pStmt.setString(1, obj.getNomAcheteur());
        pStmt.setString(2, obj.getPrenomAcheteur());
        pStmt.setString(3, obj.getPseudoAcheteur());
        pStmt.setString(4, obj.getMdpAcheteur());
        pStmt.setString(5, obj.getEmailAcheteur());
        pStmt.setString(6, obj.getTelAcheteur());
        pStmt.setInt(7, obj.getIdAcheteur());
        rs = pStmt.executeUpdate();
        if(0 != rs){
            test = true;
        }
        pStmt.close();
        cnn.close();
        return test;
        
    }

    @Override
    public boolean delete(Acheteur obj) throws SQLException {
        Connection cnn = fc.fournir();
        String pReq = "UPDATE acheteur"
                + "SET ach_actif = 0"
                + "WHERE id_acheteur = '?'";
        PreparedStatement pStmt = cnn.prepareCall(pReq);
        pStmt.setInt(1, obj.getIdAcheteur());
        
        int rs = pStmt.executeUpdate();
        pStmt.close();
        cnn.close();
        
        if(0 != rs ){
            return true;
        }
         return false; 
    }

    @Override
    public Acheteur find(int id) throws SQLException {
        Connection cnn = fc.fournir();
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Acheteur find(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*Rechercher un acheteur avec son mot de passe ainsi que son email*/
    public Acheteur find(String mail, String mdp) throws SQLException{
        
        Connection cnn = fc.fournir();
        Acheteur ach = null;
        ResultSet rs = null;
        
        String req = "{call chercherAcheteur(?, ?)}";
        CallableStatement cstmt = cnn.prepareCall(req);
        cstmt.setString(1, mail);
        cstmt.setString(2, mdp);
        rs = cstmt.executeQuery();
        if(rs.next()){
            int id = rs.getInt("id_acheteur");
            String nom = rs.getString("ach_nom");
            String prenom = rs.getString("ach_prenom");
            String pseudo = rs.getString("ach_pseudo");
            String tel = rs.getString("ach_tel");
            String email = rs.getString("ach_email");
            
            ach = new Acheteur(nom, prenom, pseudo);
            ach.setTelAcheteur(tel);
            ach.setEmailAcheteur(email);
            ach.setMdpAcheteur(mdp);
            ach.setIdAcheteur(id);
            
        }
        rs.close();
        cstmt.close();
        cnn.close();
        
        
        return ach;
    }

    @Override
    public List<Acheteur> findAll(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
