package DAO.livre;

import DAO.panier.TvaDAO;
import DAO.utils.DAO;
import DAO.utils.FournirConnectionIt;
import DAO.utils.MaConnexionBDD;
import bean.produit.Auteur;
import bean.produit.Livre;
import bean.produit.SousTheme;
import bean.produit.TypeFormatLivre;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

public class LivreDAO extends DAO<Livre> implements Serializable {

    private FournirConnectionIt fc;

    // liste de livres
    ArrayList<Livre> listelivre = new ArrayList();

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
            TvaDAO tvaDao = new TvaDAO();
            l.setTva(tvaDao.find(id));
            l.setImage(rs01.getString("livre_photo"));
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
            
            l.setImage(rs01.getString("livre_photo"));
            l.setSoustitre(rs01.getString("livre_soustitre"));
            l.setFormat(TypeFormatLivre.valueOf(rs01.getString("livre_format")));
            String livre_nbpages = rs01.getString("livre_nbpages");
            if(livre_nbpages!=null){
                l.setNbpage(Integer.valueOf(livre_nbpages));
            }
            l.setParution(rs01.getDate("livre_parution"));
            l.setEdition(Integer.valueOf(rs01.getString("livre_edition")));
            l.setDimension(rs01.getString("livre_dimension"));
            l.setResume(rs01.getString("livre_resume"));
            l.setStock(Integer.valueOf(rs01.getString("livre_stock")));
            
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
        SousTheme st;
        List<Livre> lL = new ArrayList<>();
        String req = "{call findAllLivres(?)}";
        Connection cnn = fc.fournir();
        CallableStatement cs = cnn.prepareCall(req);
        cs.setString(1, "%" + s + "%");
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            String id_livre = rs.getString("id_livre");
            String id_soustheme = rs.getString("id_soustheme");
            String livre_photo = rs.getString("livre_photo");
            String livre_titre = rs.getString("livre_titre");
            String livre_isbn13 = rs.getString("livre_isbn13");
            String id_auteur = rs.getString("id_auteur");
            String auteur_nom = rs.getString("auteur_nom");
            a = new Auteur(Integer.valueOf(id_auteur), auteur_nom);
            ArrayList<Auteur> lA = new ArrayList<>();
            lA.add(a);
            l = new Livre(null, lA, livre_titre, 0, 0, true);
            l.setId(Integer.valueOf(id_livre));
            st=new SousTheme(Integer.valueOf(id_soustheme),"");
            l.setSousTheme(st);
            l.setImage(livre_photo);
            l.setIsbn13(livre_isbn13);
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

    
    // récupération du nom du sous-theme du livre
  //  public String getNomSousThemeIdTheme(String id_soustheme)
  //  {
    //    String nomsoustheme = null;
        //int theme = ;
        
        
        
   // }
    
    
    // liste totale des livres
    public ArrayList ListeLivre() throws SQLException, NamingException {
        try {
            PreparedStatement stmtrequete = null;

            // ouverture de la connexion
            Connection cnn = fc.fournir();
            // requête
            stmtrequete = cnn.prepareStatement("SELECT *FROM livre WHERE livre_actif = 1 ORDER BY livre.livre_titre");
            ResultSet rs = stmtrequete.executeQuery();
            // exécution de la requête
            if (rs != null) {
                // on va stocker les livres dans une liste
                //ArrayList<Livre> listelivre = new ArrayList(); mis en haut avec variables
                while (rs.next()) {
                    // on crée un objet livre
                    Livre livre = new Livre();

                    // on rempli l'objet livre avec ses accesseurs
                    if (rs.getString("id") == null) {
                        livre.setId(Integer.valueOf(""));// pas sure!
                    } else {
                        livre.setId(Integer.valueOf((rs.getString("id"))));
                    }
                    if (rs.getString("nbpage") == null) {
                        livre.setNbpage(Integer.valueOf(""));// pas sure!
                    } else {
                        livre.setNbpage(Integer.valueOf((rs.getString("nbpage"))));
                    }
                    if (rs.getString("edition") == null) {
                        livre.setEdition(Integer.valueOf(""));// pas sure!
                    } else {
                        livre.setEdition(Integer.valueOf((rs.getString("edition"))));
                    }
                    if (rs.getString("stock") == null) {
                        livre.setStock(Integer.valueOf(""));// pas sure!
                    } else {
                        livre.setStock(Integer.valueOf((rs.getString("stock"))));
                    }
                    if (rs.getString("titre") == null) {
                        livre.setTitre("");
                    } else {
                        livre.setTitre(rs.getString("titre"));
                    }
                    if (rs.getString("titre") == null) {
                        livre.setTitre("");
                    } else {
                        livre.setTitre(rs.getString("titre"));
                    }
                    if (rs.getString("soustitre") == null) {
                        livre.setSoustitre("");
                    } else {
                        livre.setSoustitre(rs.getString("soustitre"));
                    }
                    if (rs.getString("isbn10") == null) {
                        livre.setIsbn10("");
                    } else {
                        livre.setIsbn10(rs.getString("isbn10"));
                    }
                    if (rs.getString("isbn13") == null) {
                        livre.setIsbn13("");
                    } else {
                        livre.setIsbn13(rs.getString("isbn13"));
                    }
                    if (rs.getString("dimension") == null) {
                        livre.setDimension("");
                    } else {
                        livre.setDimension(rs.getString("dimension"));
                    }
                    if (rs.getString("resume") == null) {
                        livre.setResume("");
                    } else {
                        livre.setResume(rs.getString("resume"));
                    }
                    if (rs.getString("image") == null) {
                        livre.setImage("");
                    } else {
                        livre.setImage(rs.getString("image"));
                    }
                    if (rs.getString("livre_poids") == null) {
                        livre.setPoids(Float.valueOf(""));
                    } else {
                        livre.setPoids((Float.valueOf(rs.getString("livre_poids"))));
                    }
                    if (rs.getString("prix") == null) {
                        livre.setPrix(Float.valueOf(""));
                    } else {
                        livre.setPrix(Float.valueOf(rs.getString("livre_prix")));
                    }
                    if ("1".equalsIgnoreCase(rs.getString("livre_actif"))) {
                        livre.setActifLivre(true);
                    } else {
                        livre.setActifLivre(false);
                    }

                    //TvaDAO tvaDao = new TvaDAO();
                    //livre.setTva(tvaDao.find(id));
                    
                    //ed
                    
                    //ListeAu
                    
                    // fourn
                    
                    //format
                    
                    //listmc
                    
                    //soustheme
                    
                    
                    
                    
                    
                    

                    // on stocke l'objet livre dans la liste des livres
                    listelivre.add((Livre) livre);
                    
                    // on envoie l'objet livre en request (scope)
                    //request.setAttribute("listelivre", listelivre);// on ne peut pas faire cela?
                }
            }

            rs.close();
            cnn.close();

        } catch (SQLException ex) {
            Logger.getLogger(LivreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listelivre;
    }

}
