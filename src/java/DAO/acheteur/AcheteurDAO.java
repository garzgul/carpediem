package DAO.acheteur;

import DAO.utils.DAO;
import DAO.utils.FournirConnectionIt;
import DAO.utils.MaConnexionBDD;
import bean.acheteur.Acheteur;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
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
        return null;
    }

    @Override
    public boolean update(Acheteur obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Acheteur obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Acheteur find(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Acheteur find(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Acheteur> findAll(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
