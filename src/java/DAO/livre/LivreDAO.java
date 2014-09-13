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
        System.out.println("dans la DAO");
        Livre l = null;
        Connection cnn = fc.fournir();
        String req = "{call findLivreParId(?)}";
        CallableStatement cstmt = cnn.prepareCall(req);
        cstmt.setInt(1, id);
        ResultSet rs01 = cstmt.executeQuery();
        System.out.println(">>>>>>>>>dans find livre");
        l = new Livre();
        while (rs01.next()) {
            l.setTitre(rs01.getString("livre_titre"));
            l.setId(id);
            System.out.println("dans le while apres le titre");
            if ("1".equalsIgnoreCase(rs01.getString("livre_actif"))) {
                l.setActifLivre(true);
            } else {
                l.setActifLivre(false);
            }
            l.setIsbn13(rs01.getString("livre_isbn13"));
            l.setIsbn10(rs01.getString("livre_isbn10"));
            l.setPoids(Float.valueOf(rs01.getString("livre_poids")));
            l.setPrix(Float.valueOf(rs01.getString("livre_prix")));
            System.out.println(">>>>>>>>>avant TVA");
            TvaDAO tvaDao = new TvaDAO();
            l.setTva(tvaDao.find(id));
            System.out.println(">>>>>>>>>>>>>>>apres TVA");
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
            System.out.println(">>>>>>>>>>>>>apres auteur");
            //recherche de l'editeur via la cle etrangere dans la table livre
            int idEditeur = Integer.valueOf(rs01.getString("id_editeur"));
            EditeurDAO eDAO = new EditeurDAO();
            l.setEd(eDAO.find(idEditeur));
            System.out.println(">>>>>>>>>>>>>apres Editeur");
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
        // recherche par (partie) du titre de livre AUSSI BIEN que le nom de l'auteur
        Livre l;
        Auteur a;
        List<Livre> lL = new ArrayList<>();
        String req = "{call findAllLivres(?)}";
        Connection cnn = fc.fournir();
        CallableStatement cs = cnn.prepareCall(req);
        cs.setString(1, "%" + s + "%");
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            String id_livre = rs.getString("id_livre");
            String livre_photo = rs.getString("livre_photo");
            String livre_titre = rs.getString("livre_titre");
            String id_auteur = rs.getString("id_auteur");
            String auteur_nom = rs.getString("auteur_nom");
            a = new Auteur(Integer.valueOf(id_auteur), auteur_nom);
            ArrayList<Auteur> lA = new ArrayList<>();
            lA.add(a);
            l = new Livre(null, lA, livre_titre, 0, 0, true);
            l.setId(Integer.valueOf(id_livre));
            l.setImage(livre_photo);
            l.setPrix(Float.valueOf(rs.getString("livre_prix")));
            //TvaDAO tvaDao = new TvaDAO();
            //l.setTva(tvaDao.find(Integer.valueOf(id_livre)));
            lL.add(l);
        }
        rs.close();
        cs.close();
        cnn.close();
        return lL;
    }
}


