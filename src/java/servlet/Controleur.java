package servlet;

import bean.acheteur.Acheteur;
import bean.acheteur.Adresse;
import bean.commande.Panier;
import bean.metier.AcheteurGestion;
import bean.metier.CommandeGestion;
import bean.metier.LivreGestion;
import bean.metier.PanierGestion;
import bean.produit.Livre;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
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

        // declaration des variables de gestion
        Panier p = null;
        Livre l = null;
        LivreGestion lg = null;
        PanierGestion pg = null;
        CommandeGestion cg = null;
        AcheteurGestion ag = null;

        // declaration du string home
        String pageJsp = "/WEB-INF/main/Main.jsp";

        System.out.println("-------------------------------------->>>> passage controleur");

// partie traitement de la servlet
        // redirection pour les bordures/elements de menu...
        request.setAttribute("entete", "Controleur?section=fragement&action=entete");
        request.setAttribute("pied", "Controleur?section=fragement&action=pied");
        request.setAttribute("menu", "Controleur?section=fragement&action=menu");
        request.setAttribute("cgv", "Controleur?section=fragement&action=cgv");
        request.setAttribute("plansite", "Controleur?section=fragement&action=plansite");
        request.setAttribute("mentionslegales", "Controleur?section=fragement&action=mentionslegales");
        request.setAttribute("carpediem", "Controleur?section=fragement&action=carpediem");
        request.setAttribute("contact", "Controleur?section=fragement&action=contact");
//        request.setAttribute("recherchevide", "Controleur?section=rechercheaffichagevide&action=affichageboite");

        // fin redirection pour les bordures
//Module panier
        if ("panier".equalsIgnoreCase(section)) {

            // affichage du panier
            if ("affichage".equalsIgnoreCase(action)) {
                if (session.getAttribute("panier") == null) {
                    session.setAttribute("panier", new Panier());
                }
                p = (Panier) session.getAttribute("panier");
                session.setAttribute("maliste", p.getLignes().values());
                request.setAttribute("panier", "Controleur?section=affichagepanier&action=affichage");

            }

            // affichage du contenu du panier
            if ("affichagepanier".equalsIgnoreCase(action)) {
                request.setAttribute("panier", "Controleur?section=affichagepanier&action=affichagedetail");
            }

            int idLivre = 0;
            if (request.getParameter("ref") != null) {
                idLivre = Integer.valueOf(request.getParameter("ref"));
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
                request.setAttribute("panier", "Controleur?section=panieraffichage&action=affichage");
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
                request.setAttribute("panier", "Controleur?section=panieraffichage&action=affichage");
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
                request.setAttribute("panier", "Controleur?section=panieraffichage&action=affichage");
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
                request.setAttribute("panier", "Controleur?section=panieraffichage&action=affichage");
            }

        }
// fin module panier        

// module commande        
        if ("commande".equalsIgnoreCase(section)) {
            if (session.getAttribute("maliste") == null) {
                request.setAttribute("panier", "Controleur?section=panieraffichage&action=affichage");
                // getServletContext().getRequestDispatcher(pageJsp).forward(request, response);
            }
            if (session.getAttribute("panier") == null) {
                request.setAttribute("panier", "Controleur?section=panieraffichage&action=affichage");
                // getServletContext().getRequestDispatcher(pageJsp).forward(request, response);
            }
            p = (Panier) session.getAttribute("panier");

            if ("affichage".equalsIgnoreCase(action)) {
                request.setAttribute("commande", "Controleur?section=affichagecommande&action=affichagedetail");
                Acheteur ach = (Acheteur) session.getAttribute("acheteur");
                Adresse ad = ach.getAdfav();
                try {
                    cg.createCommande(p.getLignes(), ach, ad);
                } catch (SQLException ex) {
                    erreurGrave = true;
                } catch (ParseException ex) {
                    erreurGrave = true;
                }
            }

            if ("validercommande".equalsIgnoreCase(action)) {
                request.setAttribute("commande", "Controleur?section=affichagecommande&action=affichage");

            }
        }

// fin module commande        
// Module Recherche (Eddy)        
        if ("recherche".equalsIgnoreCase(section)) {
            if (session.getAttribute("beanRecherche") == null) {
                try {
                    session.setAttribute("beanRecherche", new LivreGestion());
                } catch (NamingException ex) {
                    erreurGrave = true;
                }
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>passage dans recherche");
            if (request.getParameter("action") != null) {
                if ("rechercher".equalsIgnoreCase(action)) {
                    System.out.println(">>>>>>>>>>>>>>>>>>>passage dans rechercher");
                    lg = (LivreGestion) session.getAttribute("beanRecherche");
                    String champRecherche = request.getParameter("ChampRecherche");
                    //System.out.println("champrecherche = " + champRecherche);
                    List<Livre> lL = null;
                    try {
                        lL = lg.findAll(champRecherche);
                    } catch (SQLException ex) {
                        erreurGrave = true;
                    }
                    System.out.println(">>>>>>>>>>>>>>>>>>> lL = " + lL);
                    //session.setAttribute("rechercheListeLivre",lL); // place la liste des livres trouvés
                    request.setAttribute("rechercheResultat", lL); // place la liste des livres trouvés
                    //request.setAttribute("resultat", "/WEB-INF/catalogue/resultat.jsp");
                    //request.setAttribute("pagevisee", "/WEB-INF/catalogue/resultat.jsp"); // definit le lien où le resultat doit s'afficher
                    request.setAttribute("recherche", "Controleur?section=rechercheaffichage&action=affichage");
                    //pageJsp = "/WEB-INF/catalogue/resultat.jsp";
                    // pageJsp = "/WEB-INF/main/Main.jsp";
                    System.out.println("passage action rechercher => vers resultat.jsp");

                }

            }

            if (request.getParameter("action") == null) {
                System.out.println("passage action null recherche.jsp");
                //pageJsp = "/WEB-INF/catalogue/recherche.jsp";
            }

        }

// fin module recherche (Eddy)        
// module gestion de compte acheteur (Mourad)
        if ("connexion".equalsIgnoreCase(section)) {
            if ("inscriptionacheteur".equalsIgnoreCase(action)) {
                if (session.getAttribute("acheteurgestion") == null) {
                    try {
                        session.setAttribute("acheteurgestion", new AcheteurGestion());
                    } catch (NamingException ex) {
                        erreurGrave = true;
                    }
//                if (request.getParameter("action") != null) {
//                    if ("rechercher".equalsIgnoreCase(request.getParameter("action"))) {
//                        lg = (LivreGestion) session.getAttribute("beanRecherche");
//                        String champRecherche = request.getParameter("ChampRecherche");
//                        List<Livre> lL = null;
//                        try {
//                            lL = lg.findAll(champRecherche);
//                        } catch (SQLException ex1) {
//                            erreurGrave = true;
//                        }
//                        session.setAttribute("rechercheListeLivre", lL); // place la liste des livres trouvés
//                    }
//                }

                    request.setAttribute("affichagecompte", "Controleur?section=affichagecompte&action=affichageinscription");
                    //request.setAttribute("pagevisee", "/WEB-INF/compte/inscriptionacheteur.jsp");
                    // pageJsp = "/WEB-INF/main/Main.jsp";

                    request.setAttribute("affichagecompte", "Controleur?section=affichagecompte&action=affichageinscription");
                    //request.setAttribute("pagevisee", "/WEB-INF/compte/inscriptionacheteur.jsp");
                    // pageJsp = "/WEB-INF/main/Main.jsp";

                }
            }

            if ("inscription".equalsIgnoreCase(action)) {
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String pseudo = request.getParameter("pseudo");
                String mdp = request.getParameter("mdp");
                String confirmmdp = request.getParameter("confirmdp");
                String email = request.getParameter("email");
                String tel = request.getParameter("tel");
                Boolean actif = true;

                ag = (AcheteurGestion) session.getAttribute("acheteurgestion");

                try {
                    Acheteur ach = new Acheteur(nom, prenom, pseudo, mdp, true);
                    ach.setEmailAcheteur(email);
                    ach.setTelAcheteur(tel);
                    if (ach != null) {
                        session.setAttribute("acheteur", ach);
                        ag.ajoutAcheteur(ach, confirmmdp);
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

                    request.setAttribute("affichagecompte", "Controleur?section=affichagecompte&action=affichageinscription");
                    //request.setAttribute("pagevisee", "/WEB-INF/compte/inscriptionacheteur.jsp");
                    //pageJsp = "/WEB-INF/main/Main.jsp";
                    System.out.println(">>>>>>>>>>>>>>>>>passage par le catch");
                } catch (SQLException ex) {
                    erreurGrave = true;
                }

            }
        }

        if ("seconnecter".equalsIgnoreCase(action)) {
            request.setAttribute("affichagecompte", "Controleur?section=affichagecompte&action=affichageconnection");
            System.out.println("=========== i l est ici ====<<<");
        }
        if ("connection".equalsIgnoreCase(action)) {

            String mail = request.getParameter("email");
            String mdp = request.getParameter("mdp");
            System.out.println("============" + mail);
            System.out.println("============" + mdp);
            Acheteur ach = new Acheteur();
            try {
                System.out.println("========== la on est dans le try =====");
                ach = ag.chercherAcheteur(mail, mdp);

            } catch (SQLException ex) {
                erreurGrave = true;
            }
            session.setAttribute("Acheteur", ach);

        }

// debut module emma
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
// fin module emma

        // renvoi vers la jsp de gestion des erreurs
        if (erreurGrave) {
            pageJsp = "/WEB-INF/erreurs/warning.jsp";
        }
// fin traitement        

// debut affichage via urls dynamiques
        if ("defaut".equalsIgnoreCase(section)) {
            pageJsp = "/WEB-INF/main/Main.jsp";
        }
        //redirection pour les bordures
        if ("fragement".equalsIgnoreCase(section)) {
            switch (action) {
                case "entete": {
                    request.setAttribute("today", new Date());
                    pageJsp = "/WEB-INF/bordure/Entete.jsp";
                    break;
                }
                case "pied": {
                    pageJsp = "/WEB-INF/bordure/Pied.jsp";
                    break;
                }
                case "menu": {
                    pageJsp = "/WEB-INF/bordure/Menu.jsp";
                    break;

                }
                case "cgv": {
                    pageJsp = "/WEB-INF/bordure/cgv.jsp";
                    break;
                }
                case "plansite": {
                    pageJsp = "/WEB-INF/bordure/plansite.jsp";
                    break;
                }
                case "mentionslegales": {
                    pageJsp = "/WEB-INF/bordure/mentionslegales.jsp";
                    break;
                }
                case "carpediem": {
                    pageJsp = "/WEB-INF/bordure/carpediempresentation.jsp";
                    break;
                }
                case "contact": {
                    pageJsp = "/WEB-INF/bordure/contact.jsp";
                    break;
                }
                case "newsletter": {
                    pageJsp = "/WEB-INF/bordure/newsletter.jsp";
                    break;
                }
//                case "rubrique":{
//                    pageJsp="/WEB-INF/catalogue/Rubrique.jsp";
//                    break;
//                }
            }
        }
        //redirection pour le panier
        if ("panieraffichage".equalsIgnoreCase(section)) {
            switch (action) {
                case "affichage": {
                    pageJsp = "/WEB-INF/panier/Panier.jsp";
                    break;
                }
                case "affichagedetail": {
                    pageJsp = "/WEB-INF/panier/DetailPanier.jsp";
                    break;
                }
            }
        }
        //redirection pour la commande
        if ("commandeaffichage".equalsIgnoreCase(section)) {
            switch (action) {
                case "affichage": {
                    pageJsp = "/WEB-INF/commande/Commande.jsp";
                    break;
                }
                case "affichagedetail": {
                    pageJsp = "/WEB-INF/panier/DetailPanier.jsp";
                    break;
                }
                case "affichagevalidationAcheteur": {
                    pageJsp = "/WEB-INF/commande/validationAcheteur.jsp";
                    break;
                }
                case "affichagevalidationcommande": {
                    pageJsp = "/WEB-INF/commande/Commande.jsp";
                    break;
                }
                case "affichagepaiement": {
                    pageJsp = "/WEB-INF/commande/paiement.jsp";
                    break;
                }
            }
        }
//        //redirection pour la recherche vide (dans l'entete)
//        if("rechercheaffichagevide".equalsIgnoreCase(section)){
//            if("affichage".equalsIgnoreCase(action)){
//                pageJsp="/WEB-INF/catalogue/recherche.jsp";
//            }
//        }

        //redirection pour la recherche
        if ("rechercheaffichage".equalsIgnoreCase(section)) {
            if ("affichage".equalsIgnoreCase(action)) {
                pageJsp = "/WEB-INF/catalogue/resultat.jsp";
            }
            if ("affichageboite".equalsIgnoreCase(action)) {
                pageJsp = "/WEB-INF/catalogue/recherche.jsp";
            }
        }
        //redirection pour la gestion de compte
        if ("affichagecompte".equalsIgnoreCase(section)) {
            switch (action) {
                case ("affichageinscription"): {
                    pageJsp = "/WEB-INF/compte/inscriptionacheteur.jsp";
                    break;
                }
                case ("affichageconnection"): {
                    pageJsp = "/WEB-INF/compte/connectionacheteur.jsp";
                    break;
                }

            }
        }

// fin affichage via urls dynamiques        
        System.out.println("00-------------------------------0>>>" + pageJsp);
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
