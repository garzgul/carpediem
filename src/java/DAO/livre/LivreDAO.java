package DAO.livre;

import DAO.panier.TvaDAO;
import DAO.utils.DAO;
import DAO.utils.FournirConnectionIt;
import DAO.utils.MaConnexionBDD;
import bean.produit.Auteur;
import bean.produit.Livre;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class LivreDAO extends DAO<Livre> implements Serializable {

    private FournirConnectionIt fc;

    public LivreDAO() throws NamingException {
        fc = new MaConnexionBDD();
    }

    @Override
    public Livre create(Livre obj) throws SQLException {
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
    public Livre find(int id) throws SQLException, NamingException {
        Livre l = null;
        Connection cnn = fc.fournir();
        String req = "{call findLivreParId(?)}";
        CallableStatement cstmt = cnn.prepareCall(req);
        cstmt.setInt(1, id);
        ResultSet rs01 = cstmt.executeQuery();
        l = new Livre();
        while (rs01.next()) {
            l.setTitre(rs01.getString("livre_titre"));
            l.setId(id);
            if ("1".equalsIgnoreCase(rs01.getString("livre_actif"))) {
                l.setActifLivre(true);
            } else {
                l.setActifLivre(false);
            }
            l.setIsbn13(rs01.getString("livre_isbn13"));
            l.setIsbn10(rs01.getString("livre_isbn10"));
            l.setPoids(Float.valueOf(rs01.getString("livre_poids")));
            l.setPrix(Float.valueOf(rs01.getString("livre_prix")));
            TvaDAO tvaDao= new TvaDAO();
            l.setTva(tvaDao.find(id));
            
            // recherche des id auteur via la table index ecriture
            req = "Select * from ecriture where id_livre = ?";
            PreparedStatement pstmt = cnn.prepareStatement(req);
            pstmt.setInt(1, id);
            ResultSet rs02 = pstmt.executeQuery();
            ArrayList<Auteur> listeAuteur = new ArrayList<Auteur>();
            while (rs02.next()) {
                AuteurDAO aDAO = new AuteurDAO();
                listeAuteur.add(aDAO.find(Integer.valueOf(rs02.getString("id_auteur"))));
            }
            l.setListeAu(listeAuteur);
            
            //recherche de l'editeur via la cle etrangere dans la table livre
            int idEditeur = Integer.valueOf(rs01.getString("id_editeur"));
            EditeurDAO eDAO = new EditeurDAO();
            l.setEd(eDAO.find(idEditeur));
            rs02.close();
            pstmt.close();
        }
        
        rs01.close();
        cstmt.close();
        cnn.close();

        return l;
    }

    @Override
    public Livre find(String s) throws SQLException, NamingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Livre> findAll(String s) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        //recherche par (partie) du titre de livre
        Livre l = null;
        List<Livre> lL = new ArrayList<>();
        String req = "select * from livre where livre_titre like '%?%'"; // remplacer par un appel a une PROCEDURE STOCKEE !!!!
        //String req = "{call findAllLivres(?)}";
        Connection cnn = fc.fournir();
        PreparedStatement pStm = cnn.prepareStatement(req);
        //CallableStatement cs=cnn.prepareCall(req);
        //cs.setString(1,s);
        pStm.setString(1, s);
        ResultSet rs = pStm.executeQuery(req);
        while (rs.next()) {
            //rs.getString("id_livre");
            String titre = rs.getString("livre_titre");

            l = new Livre(null, null, titre, 0, 0, true);
            lL.add(l);
        }
        rs.close();
        pStm.close();
        cnn.close();

        return lL;
    }

}
