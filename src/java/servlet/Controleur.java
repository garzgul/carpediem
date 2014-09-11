package servlet;

import bean.commande.Panier;
import bean.acheteur.Acheteur;
import bean.metier.AcheteurGestion;
import bean.metier.LivreGestion;
import bean.produit.Livre;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilitaire.MouradException;

/**
 *
 * @author cdi116
 */
@WebServlet(name = "Controleur", urlPatterns = {"/Controleur"})
public class Controleur extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Boolean erreurGrave = false;

        String section = request.getParameter("section");

        String action = request.getParameter("action");
        Panier p = null;
        Livre l = null;
        LivreGestion lg = null;

        String pageJsp = "/WEB-INF/main/Main.jsp";

        action = request.getParameter("action");
        AcheteurGestion ag = null;

        pageJsp = "/WEB-INF/main/Main.jsp";
        System.out.println("-------------------------------------->>>> passage controleur");

        // import entete de page
        if ("Entete".equalsIgnoreCase(section)) {
            request.setAttribute("today", new Date());
            pageJsp = "/WEB-INF/bordure/Entete.jsp";
        }

        // import menu horizontal
        if ("Menu".equalsIgnoreCase(section)) {
            pageJsp = "/WEB-INF/bordure/Menu.jsp";
        }

        // import pied de page
        if ("Pied".equalsIgnoreCase(section)) {
            pageJsp = "/WEB-INF/bordure/Pied.jsp";
        }
        // import liens pied de page
        if (("bordure".equalsIgnoreCase(section)) && ("carpediempresentation".equalsIgnoreCase(action))) {
            request.setAttribute("pagevisee", "/WEB-INF/bordure/carpediempresentation.jsp");
            pageJsp = "/WEB-INF/main/Main.jsp";
        }

        if (("bordure".equalsIgnoreCase(section)) && ("mentionslegales".equalsIgnoreCase(action))) {
            request.setAttribute("pagevisee", "/WEB-INF/bordure/mentionslegales.jsp");
            pageJsp = "/WEB-INF/main/Main.jsp";
        }

        if (("bordure".equalsIgnoreCase(section)) && ("cgv".equalsIgnoreCase(action))) {
            request.setAttribute("pagevisee", "/WEB-INF/bordure/cgv.jsp");
            pageJsp = "/WEB-INF/main/Main.jsp";
        }

        if (("bordure".equalsIgnoreCase(section)) && ("plansite".equalsIgnoreCase(action))) {
            request.setAttribute("pagevisee", "/WEB-INF/bordure/plansite.jsp");
            pageJsp = "/WEB-INF/main/Main.jsp";
        }

        if (("bordure".equalsIgnoreCase(section)) && ("newsletter".equalsIgnoreCase(action))) {
            request.setAttribute("pagevisee", "/WEB-INF/bordure/newsletter.jsp");
            pageJsp = "/WEB-INF/main/Main.jsp";
        }

        if (("bordure".equalsIgnoreCase(section)) && ("contact".equalsIgnoreCase(action))) {
            request.setAttribute("pagevisee", "/WEB-INF/bordure/contact.jsp");
            pageJsp = "/WEB-INF/main/Main.jsp";
        }

        // mettre les sections ici
        if ("panier".equalsIgnoreCase(section)) {
            int idLivre = Integer.valueOf(request.getParameter("ref"));

            // affichage du panier
            if ("affichage".equalsIgnoreCase(action)) {
                if (session.getAttribute("panier") == null) {
                    session.setAttribute("panier", new Panier());
                }
                p = (Panier) session.getAttribute("panier");
                session.setAttribute("maliste", p.getLignes().values());
                pageJsp = "/WEB-INF/panier/panier.jsp";
            }

            //addtion d'un item au panier
            if ("add".equalsIgnoreCase(action)) {
                p = (Panier) session.getAttribute("panier");

                try {
                    l = lg.findLivre(idLivre);
                    p.addlivre(l);
                    session.setAttribute("panier", p.getLignes().values());
                } catch (SQLException ex) {
                    erreurGrave = true;
                } catch (NamingException ex) {
                    erreurGrave = true;
                } catch (Exception ex) {
                    erreurGrave = true;
                }
                pageJsp = "/WEB-INF/panier/panier.jsp";
            }

            // suppresion d'un item du panier
            if ("remove".equalsIgnoreCase(action)) {
                p = (Panier) session.getAttribute("panier");
                try {
                    l = lg.findLivre(idLivre);
                    p.enleverLivre(l);
                    session.setAttribute("panier", p.getLignes().values());
                } catch (SQLException ex) {
                    erreurGrave = true;
                } catch (NamingException ex) {
                    erreurGrave = true;
                } catch (Exception ex) {
                    erreurGrave = true;
                }
                pageJsp = "/WEB-INF/panier/panier.jsp";
            }

            // diminution de la quantite commandee pour un item
            if ("moins".equalsIgnoreCase(action)) {
                p = (Panier) session.getAttribute("panier");

                try {
                    l = lg.findLivre(idLivre);
                    p.update(idLivre, -1);
                } catch (SQLException ex) {
                    erreurGrave = null;
                } catch (NamingException ex) {
                    erreurGrave = null;
                }
                pageJsp = "/WEB-INF/panier/panier.jsp";
            }

            // augmentation de la quantite commandee pour un tiem
            if ("plus".equalsIgnoreCase(action)) {

                p = (Panier) session.getAttribute("panier");
                try {
                    l = lg.findLivre(idLivre);
                    p.update(idLivre, 1);
                } catch (SQLException ex) {
                    erreurGrave = true;
                } catch (NamingException ex) {
                    erreurGrave = true;
                }
                pageJsp = "/WEB-INF/panier/panier.jsp";
            }

        }

// Module Recherche (Eddy)        
        if ("recherche".equalsIgnoreCase(section)) {
            try {
                if (request.getParameter("action") != null) {
                    if ("rechercher".equalsIgnoreCase(request.getParameter("action"))) {
                        lg = (LivreGestion) session.getAttribute("beanRecherche");

                        String champRecherche = request.getParameter("ChampRecherche");
                        System.out.println("champrecherche = " + champRecherche);
                        List<Livre> lL = null;
                        lL = lg.findAll(champRecherche);
                        System.out.println("lL = " + lL);
                        //session.setAttribute("rechercheListeLivre",lL); // place la liste des livres trouvés
                        request.setAttribute("rechercheResultat", lL); // place la liste des livres trouvés
                        request.setAttribute("pagevisee", "/WEB-INF/catalogue/resultat.jsp"); // definit le lien où le resultat doit s'afficher
                        pageJsp = "/WEB-INF/catalogue/resultat.jsp"; //pk main deconne ?

                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }

            pageJsp = "/WEB-INF/catalogue/recherche.jsp";
        }

//Tous ce qui concerne l'acheteur "Connection, inscription, déconnection
        if ("inscriptionacheteur".equalsIgnoreCase(section)) {
            System.out.println(">>>>>>>>>>>>acheteur");
            if (session.getAttribute("acheteurgestion") == null) {
                try {
                    session.setAttribute("acheteurgestion", new AcheteurGestion());
                } catch (NamingException ex) {
                    erreurGrave = true;
                }
                if (request.getParameter("action") != null) {
                    if ("rechercher".equalsIgnoreCase(request.getParameter("action"))) {
                        lg = (LivreGestion) session.getAttribute("beanRecherche");
                        String champRecherche = request.getParameter("ChampRecherche");
                        List<Livre> lL = null;
                        try {
                            lL = lg.findAll(champRecherche);
                        } catch (SQLException ex1) {
                            erreurGrave = true;
                        }
                        session.setAttribute("rechercheListeLivre", lL); // place la liste des livres trouvés
                    }
                }

                request.setAttribute("pagevisee", "/WEB-INF/compte/inscriptionacheteur.jsp");
                pageJsp = "/WEB-INF/main/Main.jsp";

            }
        }

        if ("inscription".equalsIgnoreCase(section)) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>inscription");
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String pseudo = request.getParameter("pseudo");
            String mdp = request.getParameter("mdp");
            String email = request.getParameter("email");
            String tel = request.getParameter("tel");
            Boolean actif = true;

            ag = (AcheteurGestion) session.getAttribute("acheteurgestion");

            try {
                Acheteur ach = new Acheteur(nom, prenom, pseudo, mdp, true);
                ach.setTelAcheteur(tel);
                if (ach != null) {
                    session.setAttribute("acheteur", ach);
                    ag.ajoutAcheteur(ach, mdp);
                }
            } catch (MouradException ex) {

                HashMap<String, String> mp = ex.getMessages();
                for (String s : mp.keySet()) {
                    request.setAttribute(s, mp.get(s));
                }
                request.setAttribute("emailFourni", email.trim());
                request.setAttribute("nomFourni", nom.trim());
                request.setAttribute("prenomFourni", prenom.trim());
                request.setAttribute("pseudoFourni", pseudo.trim());
                request.setAttribute("telFourni", tel);

                request.setAttribute("pagevisee", "/WEB-INF/compte/inscriptionacheteur.jsp");
                pageJsp = "/WEB-INF/main/Main.jsp";
                System.out.println(">>>>>>>>>>>>>>>>>passage par le catch");
            } catch (SQLException ex) {
                erreurGrave = true;
            }

        }
        if (erreurGrave) {
            pageJsp = "WEB-INF/erreurs/warning.jsp";
        }

// formulaire de contact (Emma)
        if ("contactformulaire".equalsIgnoreCase(section)) {
            System.out.println("------------------------------------------>>>> contact !");
            String votremail = request.getParameter("votremail");
            String objetcontact = request.getParameter("objetcontact");
            String votrenom = request.getParameter("votrenom");
            String votreprenom = request.getParameter("votreprenom");
            String messagecontact = request.getParameter("messagecontact");
//            try {
//
//                Membre m = gm.ajouterMembre(mail, mdp, nom, prenom);
//                session.setAttribute("user", m);
//            } catch (MonException ex) {
//                System.out.println("----------------->>>> " + ex.getMessage());
//
//                HashMap<String, String> mp = ex.getMessages();
//                for (String s : mp.keySet()) {
//                    request.setAttribute(s, mp.get(s));
//                }
//                request.setAttribute("mailFourni", mail.trim());
//                request.setAttribute("nomFourni", nom.trim());
//                request.setAttribute("prenomFourni", prenom.trim());
//                pageJsp = "/WEB-INF/connexion/formulaireinscription.jsp";
//            } catch (SQLException ex) {
//                erreurGrave = true;
//                System.out.println("--------->>> " + ex.getMessage());
//
//            }
        }
        
        
        // Consultation d'un livre - fiche produit (Emma)
        if(action.equalsIgnoreCase("consulter"))
        {
            // récupération de l'id du livre (id) sur lequel le client a cliqué, au travers de la requête
            String id_articlelivre = (String)request.getParameter("id_articlelivre");
            if(id_articlelivre != null && !id_articlelivre.equalsIgnoreCase("")){
                // récupération de toutes les informations sur le livre sélectionné à partir de la bdd
                Livre articlelivre = Livre.get(id);
                // on stocke le livre dans la requête
                request.setAttribute("articlelivre", articlelivre);
                // puis on va sur la page du livre passé en requête et la jsp pourra le récupérer avec request.gatAttribute("articlelivre")
                getServletContext().getRequestDispatcher("/WEB-INF/affichagelivre/AffichageLivre.jsp").forward(request, response);
        }
        }
       

        pageJsp = response.encodeURL(pageJsp);
        getServletContext().getRequestDispatcher(pageJsp).include(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
