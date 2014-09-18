package servlet;

import bean.acheteur.Acheteur;
import bean.acheteur.Adresse;
import bean.commande.Commande;
import bean.commande.LignePanier;
import bean.commande.ModeLivraison;
import bean.commande.Panier;
import bean.metier.AcheteurGestion;
import bean.metier.CommandeGestion;
import bean.metier.LivraisonGestion;
import bean.metier.LivreGestion;
import bean.metier.PanierGestion;
import bean.metier.PromoGestion;
import bean.metier.ThemesGestion;
import bean.produit.Livre;
import bean.produit.Promotion;
import bean.produit.SousTheme;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilitaire.MonException;
import utilitaire.SendMail;

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
            throws ServletException, IOException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();
        Boolean erreurGrave = false;

        String section = request.getParameter("section");
        String action = request.getParameter("action");
        
        String pageNumber = request.getParameter("pageNumber"); // ej

        // declaration des variables de gestion
        Panier p = null;
        Livre l = null;
        LivreGestion lg = null;
        PanierGestion pg = null;
        CommandeGestion cg = null;
        AcheteurGestion ag = null;
        ThemesGestion tg = null;
        LivraisonGestion livraisong = null;
        PromoGestion Pg = null;

        // declaration du string home
        String pageJsp = "/WEB-INF/main/Main.jsp";

// Module de traitement des données en scope application  
// Module de traitement des themes/sousthemes (Eddy) 
// la liste doit aller dans le contexte de la servlet (scope application et non scope session vu qu'il est le meme pour tous
        if (session.getAttribute("beanThemesGestion") == null) {
            try {
                session.setAttribute("beanThemesGestion", new ThemesGestion()); // instanciation bean métier
            } catch (NamingException ex) {
                erreurGrave = true; // flag boolean pour signaler qu'une erreur s'est produite
            }
        }
        tg = (ThemesGestion) session.getAttribute("beanThemesGestion");
        List<SousTheme> lsT = null;
        try {
            lsT = tg.listeSousThemes(); // appel de la méthode métier de récupération
        } catch (SQLException ex) {
            erreurGrave = true; // flag boolean pour signaler qu'une erreur remontée SQL s'est produite
        } catch (NamingException ex) {
            erreurGrave = true;
        }
        context.setAttribute("sousthemesListe", lsT); // place la liste des sous themes trouvés dans le scope
        // request.setAttribute("themes", "Controleur?section=themesaffichage&action=affichage"); non necessaire (la liste de theme sera mise en scope appli au demarage de l'appli
// Fin module de traitement Themes (Eddy)

// creation de la liste de type de livraison
        if (context.getAttribute("livraisongestion") == null) {
            context.setAttribute("livraisongestion", new LivraisonGestion());
        }
        if (context.getAttribute("typelivraison") == null) {
            livraisong = (LivraisonGestion) context.getAttribute("livraisongestion");
            context.setAttribute("typelivraison", livraisong.getListeLivraison());
        }

// partie traitement de la servlet
        // redirection pour les bordures/elements de menu...
        request.setAttribute("entete", "Controleur?section=fragement&action=entete");
        request.setAttribute("pied", "Controleur?section=fragement&action=pied");
        request.setAttribute("menu", "Controleur?section=fragement&action=menu");

        //request.setAttribute("themes", "Controleur?section=themes&action=traitementthemes"); // sup eddy temporaire
//        request.setAttribute("cgv", "Controleur?section=fragement&action=cgv");
//        request.setAttribute("plansite", "Controleur?section=fragement&action=plansite");
//        request.setAttribute("mentionslegales", "Controleur?section=fragement&action=mentionslegales");
//        request.setAttribute("carpediem", "Controleur?section=fragement&action=carpediem");
//        request.setAttribute("contact", "Controleur?section=fragement&action=contact");
//        request.setAttribute("recherchevide", "Controleur?section=rechercheaffichagevide&action=affichageboite");
        // fin redirection pour les bordures
//Module panier
        if ("panier".equalsIgnoreCase(section)) {
            System.out.println(">>>>>>>>>>>>>>>>passage dans panier 0");
            if (session.getAttribute("beanLivreGestion") == null) {
                try {
                    session.setAttribute("beanLivreGestion", new LivreGestion());

                } catch (NamingException ex) {
                    erreurGrave = true;
                }
            }
            lg = (LivreGestion) session.getAttribute("beanLivreGestion");
            request.setAttribute("affichagedetailpanier", "Controleur?section=affichagepanier&action=affichagedetail");
            if (session.getAttribute("panier") == null) {
                session.setAttribute("panier", new Panier());
            }
            p = (Panier) session.getAttribute("panier");
            session.setAttribute("maliste", p.getLignes().values());

            // affichage du panier
            if ("affichage".equalsIgnoreCase(action)) {

                request.setAttribute("affichagepanier", "Controleur?section=affichagepanier&action=affichage");
            }

            // affichage du contenu du panier
//            if ("affichagedetailpanier".equalsIgnoreCase(action)) {
//                
//                request.setAttribute("affichagedetailpanier", "Controleur?section=affichagepanier&action=affichagedetail");
//            }
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
                    session.setAttribute("maliste", p.getLignes().values());
                    session.setAttribute("panier", p);
                } catch (SQLException ex) {
                    erreurGrave = true;
                } catch (NamingException ex) {
                    erreurGrave = true;
                } catch (Exception ex) {
                    erreurGrave = true;
                }
                request.setAttribute("affichagepanier", "Controleur?section=affichagepanier&action=affichage");
            }

            // suppresion d'un item du panier
            if ("remove".equalsIgnoreCase(action)) {
                p = (Panier) session.getAttribute("panier");
                try {
                    l = lg.findLivre(idLivre);
                    System.out.println(">>>>>>>>>>>>>> livre a enlever" + l);
                    p.enleverLivre(l);
                    session.setAttribute("maliste", p.getLignes().values());
                    session.setAttribute("panier", p);
                } catch (SQLException ex) {
                    erreurGrave = true;
                } catch (NamingException ex) {
                    erreurGrave = true;
                } catch (Exception ex) {
                    erreurGrave = true;
                }
                request.setAttribute("affichagepanier", "Controleur?section=affichagepanier&action=affichage");
                response.sendRedirect("Controleur?section=panier&action=affichage");
                return;
            }

            // diminution de la quantite commandee pour un item
            if ("moins".equalsIgnoreCase(action)) {
                p = (Panier) session.getAttribute("panier");

                try {
                    l = lg.findLivre(idLivre);
                    p.update(idLivre, -1);
                    session.setAttribute("maliste", p.getLignes().values());
                    session.setAttribute("panier", p);
                } catch (SQLException ex) {
                    erreurGrave = null;
                } catch (NamingException ex) {
                    erreurGrave = null;
                }
                request.setAttribute("affichagepanier", "Controleur?section=affichagepanier&action=affichage");
                response.sendRedirect("Controleur?section=panier&action=affichage");
                return;
            }

            // augmentation de la quantite commandee pour un tiem
            if ("plus".equalsIgnoreCase(action)) {

                p = (Panier) session.getAttribute("panier");
                try {
                    l = lg.findLivre(idLivre);
                    p.update(idLivre, 1);
                    session.setAttribute("maliste", p.getLignes().values());
                    session.setAttribute("panier", p);
                } catch (SQLException ex) {
                    erreurGrave = true;
                } catch (NamingException ex) {
                    erreurGrave = true;
                }
                request.setAttribute("affichagepanier", "Controleur?section=affichagepanier&action=affichage");
                response.sendRedirect("Controleur?section=panier&action=affichage");
                return;
            }

        }
// fin module panier        

// module commande        
        if ("commande".equalsIgnoreCase(section)) {

            if (session.getAttribute("maliste") == null) {
                request.setAttribute("affichagepanier", "Controleur?section=affichagepanier&action=affichage");

            }
            if (session.getAttribute("panier") == null) {
                request.setAttribute("affichagepanier", "Controleur?section=affichagepanier&action=affichage");

            }
            p = (Panier) session.getAttribute("panier");

            if (session.getAttribute("commande") == null) {
                try {
                    session.setAttribute("commande", new CommandeGestion());
                } catch (NamingException ex) {
                    erreurGrave = true;
                }
            }
            cg = (CommandeGestion) session.getAttribute("commande");
            Acheteur ach = (Acheteur) session.getAttribute("Acheteur");
            System.out.println("acheteur id = " + ach.getIdAcheteur());
            System.out.println("acheteur liste d'adresse = " + ach.getListAdresseAcheteur());

            request.setAttribute("affichagecommande", "Controleur?section=affichagecommande&action=affichagedetail");

            if ("affichage".equalsIgnoreCase(action)) {

                request.setAttribute("affichagecommande", "Controleur?section=affichagecommande&action=affichagedetail");

                Adresse ad = ach.getAdfav();
                try {
                    session.setAttribute("commandeDetail", cg.createCommande(p.getLignes(), ach));
                } catch (SQLException ex) {
                    erreurGrave = true;
                    System.out.println("erreur SQL =>>>>>> " + ex.getMessage());
                } catch (ParseException ex) {
                    System.out.println("coucou erreur parsing");
                    erreurGrave = true;
                }
            }

            if ("detailcommande".equalsIgnoreCase(action)) {
                request.setAttribute("affichagecommande", "Controleur?section=affichagecommande&action=affichage");
            }

            if ("validercommande".equalsIgnoreCase(action)) {

                try {
                    System.out.println("avant le crea commande");
                    p = (Panier) session.getAttribute("panier");
                    
                    HashMap<Integer,LignePanier> maliste = p.getLignes();
                    session.setAttribute("commandeDetail", cg.createCommande(maliste, ach));
                } catch (SQLException ex) {
                    System.out.println("erreur SQL =>>>>>> " + ex.getMessage());
                    erreurGrave = true;
                } catch (ParseException ex) {
                    System.out.println("coucou erreur parsing sur la date");
                    erreurGrave = true;
                }

                request.setAttribute("commande", "Controleur?section=affichagecommande&action=affichage");
                request.setAttribute("affichagecommande", "Controleur?section=affichagecommande&action=affichagedetail");

            }
            if ("confirmer".equalsIgnoreCase(action)) {
                p = (Panier) session.getAttribute("panier");
                if (session.getAttribute("commande") == null) {
                    try {
                        session.setAttribute("commande", new CommandeGestion());
                    } catch (NamingException ex) {
                        erreurGrave = true;
                    }
                }
                cg = (CommandeGestion) session.getAttribute("commande");

                // ArrayList<Adresse> listead = ach.getListAdresseAcheteur();
                try {
                    //creation de l'objet commande et mise en session
                    session.setAttribute("commandeDetail", cg.createCommande(p.getLignes(), ach));
                } catch (SQLException ex) {
                    erreurGrave = true;
                } catch (ParseException ex) {
                    erreurGrave = true;
                }

                request.setAttribute("affichagecommande", "Controleur?section=affichagecommande&action=affichage");

            }
//            if ("confirmer".equalsIgnoreCase(action)) {
//                p = (Panier) session.getAttribute("panier");
//                if (session.getAttribute("commande") == null) {
//                    try {
//                        session.setAttribute("commande", new CommandeGestion());
//                    } catch (NamingException ex) {
//                        erreurGrave = true;
//                    }
//                }
//                cg = (CommandeGestion) session.getAttribute("commande");
//                if (session.getAttribute("commande") == null) {
//                    erreurGrave = true;
//                    return;
//                }
//                Commande cde = (Commande) session.getAttribute("commandeDetail");
//                cde = cg.setDate(cde);
//                
//                ArrayList<Adresse> listead = ach.getListAdresseAcheteur();
//                //creation de l'objet commande
//
//            }
=======
            if ("confirmer".equalsIgnoreCase(action)) {
                p = (Panier) session.getAttribute("panier");
                if (session.getAttribute("commande") == null) {
                    try {
                        session.setAttribute("commande", new CommandeGestion());
                    } catch (NamingException ex) {
                        erreurGrave = true;
                    }
                }
                cg = (CommandeGestion) session.getAttribute("commande");
                if (session.getAttribute("commande") == null) {
                    erreurGrave = true;
                    return;
                }
                Commande cde = (Commande) session.getAttribute("commandeDetail");
                cde = cg.setDate(cde);

                ArrayList<Adresse> listead = ach.getListAdresseAcheteur();
                //creation de l'objet commande

            }
>>>>>>> origin/Emmanuelle

            if ("confirmertypelivraison".equalsIgnoreCase(action)) {
                Commande cde = (Commande) session.getAttribute("commandeDetail");
                String typeLivraison = request.getParameter("typelivraison");

                System.out.println(typeLivraison);

                // pour le moment cette ligne n'est pas necessaire car un seul type de livraison existe
                //cde.setModeLivraison(ModeLivraison.valueOf(typeLivraison));
                cde.setModeLivraison(ModeLivraison.poste);
                request.setAttribute("", l);

            }
            if ("choixlivraison".equalsIgnoreCase(action)) {
                request.setAttribute("affichagecommande", "Controleur?section=affichagecommande&action=affichagechoixlivraison");
                
            }

        }

// fin module commande  
// Module Recherche (Eddy)        
        if ("recherche".equalsIgnoreCase(section)) { // section recherche concernée
            if (session.getAttribute("beanLivreGestion") == null) {
                try {
                    session.setAttribute("beanLivreGestion", new LivreGestion()); // instanciation bean métier
                } catch (NamingException ex) {
                    erreurGrave = true; // flag boolean pour signaler qu'une erreur s'est produite
                }
            }
            if (request.getParameter("action") != null) {
                if ("rechercher".equalsIgnoreCase(action)) { // action rechercher provoquée par le formulaire user
                    lg = (LivreGestion) session.getAttribute("beanLivreGestion");
                    String champRecherche = request.getParameter("ChampRecherche"); // récup de la saisie à rechercher
                    List<Livre> lL = null;
                    try {
                        lL = lg.findAll(champRecherche); // appel de la méthode métier de recherche
                    } catch (SQLException ex) {
                        erreurGrave = true; // flag boolean pour signaler qu'une erreur remontée SQL s'est produite
                    }
                    if (session.getAttribute("filtrageapplique") != null) { // s'il y a déjà eut un filtre soustheme appliqué
                        String id_soustheme = (String) session.getAttribute("filtrageapplique");
                        lL = lg.filtrer(lL, Integer.valueOf(id_soustheme)); // appel de la méthode métier de filtrage en fonction des livres trouvés
                    } else { // pas de jeux de résultats précédemment affichés
                        // rien a restreindre
                    }
                    //request.setAttribute("rechercheResultat", lL); // place la liste des livres trouvés dans le scope
                    session.setAttribute("rechercheResultat", lL); // place la liste des livres trouvés dans le scope
                    session.setAttribute("rechercheappliquee", "oui"); // flag pour se souvenir qu'un résultat de recherche a été appliqué
                    request.setAttribute("recherche", "Controleur?section=rechercheaffichage&action=affichage"); // signalement
                    // qu'une liste de livres sera à afficher dynamiquement en résultat
                }
                if ("filtrer".equalsIgnoreCase(action)) { // action filtrer provoquée par un clic sur un theme particulier
                    lg = (LivreGestion) session.getAttribute("beanLivreGestion");
                    String id_soustheme = request.getParameter("ref"); // récup de l'id_soustheme à filtrer
                    List<Livre> lLfiltree = null;
                    if ((session.getAttribute("rechercheResultat") != null) && (session.getAttribute("rechercheappliquee") != null)) { // s'il y a déjà eut une recherche de résultats
                        List<Livre> lL = (List<Livre>) session.getAttribute("rechercheResultat");
                        lLfiltree = lg.filtrer(lL, Integer.valueOf(id_soustheme)); // appel de la méthode métier de filtrage en fonction des resultats préalables des livres
                    } else { // pas de résultats en cours, clic sur sous theme la première fois par exp...
                        try {
                            lLfiltree = lg.findAllByTheme(Integer.valueOf(id_soustheme));
                        } catch (SQLException ex) {
                            erreurGrave = true;
                        }
                    }
                    session.setAttribute("rechercheResultat", lLfiltree); // place la liste filtrée des livres trouvés dans le scope
                    session.setAttribute("filtrageapplique", id_soustheme); // flag pour se souvenir qu'un filtre a été appliqué
                    request.setAttribute("recherche", "Controleur?section=rechercheaffichage&action=affichage"); // signalement
                    // qu'une liste de livres sera à afficher dynamiquement en résultat
                }
                if ("annulerfiltre".equalsIgnoreCase(action)) { // clic sur entete des themes pour annuler tout filtre de theme
                    session.removeAttribute("filtrageapplique");
                    //A TESTER la ligne suivante !!!!!!!!!!!!!!!!!!!!!!
                    request.setAttribute("recherche", "Controleur?section=rechercheaffichage&action=affichage&pageNumber=1"); // signalement
                                                // qu'une liste de livres sera à afficher en résultat à une page donnée
                }
                if("pagination".equalsIgnoreCase(action)) {
                    //System.out.println(pageNumber);
                    request.setAttribute("recherche", "Controleur?section=rechercheaffichage&action=affichage&pageNumber="+pageNumber); // signalement
                                                // qu'une liste de livres sera à afficher en résultat à une page donnée
                }
            }
        }
// fin module recherche (Eddy)

// Module gestion fiche livre (Eddy)
        if ("ficheLivre".equalsIgnoreCase(section)) {
            if (session.getAttribute("beanLivreGestion") == null) {
                try {
                    session.setAttribute("beanLivreGestion", new LivreGestion());
                } catch (NamingException ex) {
                    erreurGrave = true;
                }
            }

            if ("afficher".equalsIgnoreCase(action)) { // mise en mémoire / request objet livre 
                lg = (LivreGestion) session.getAttribute("beanLivreGestion");
                String id_livre = request.getParameter("ref");
                try {
                    l = lg.findLivre(Integer.valueOf(id_livre));
                } catch (SQLException ex) {
                    erreurGrave = true;
                } catch (NamingException ex) {
                    erreurGrave = true;
                }
                session.setAttribute("ficheLivreCourant", l); // place le livre courant en session
                request.setAttribute("affichageLivre", "Controleur?section=livreaffichage&action=afficher");
            }
        }
// fin module gestion fiche livre

// module gestion de compte acheteur (Mourad)
        if ("connexion".equalsIgnoreCase(section)) {

            if (session.getAttribute("acheteurgestion") == null) {
                try {
                    session.setAttribute("acheteurgestion", new AcheteurGestion());
                } catch (NamingException ex) {
                    erreurGrave = true;
                }
                //request.setAttribute("affichagecompte", "Controleur?section=affichagecompte&action=affichageinscription");
            }

            if ("inscriptionacheteur".equalsIgnoreCase(action)) {
                request.setAttribute("affichagecompte", "Controleur?section=affichagecompte&action=affichageinscription");
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
                    request.setAttribute("affichagecompte", "Controleur?section=affichagecompte&action=voircompte");
                    if (ach != null) {
                        session.setAttribute("Acheteur", ach);
                        ag.ajoutAcheteur(ach, confirmmdp);
                    }
                    request.setAttribute("affichagecompte", "Controleur?section=affichagecompte&action=voircompte");
                } catch (MonException ex) {

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
                } catch (SQLException ex) {
                    erreurGrave = true;
                } catch (NamingException ex) {
                    erreurGrave = true;
                }

            }

            if ("seconnecter".equalsIgnoreCase(action)) {
                request.setAttribute("affichagecompte", "Controleur?section=affichagecompte&action=affichageconnection");
            }

            if ("connection".equalsIgnoreCase(action)) {

                String mail = request.getParameter("email");
                String mdp = request.getParameter("mdp");
                Acheteur ach = null;
                request.setAttribute("ajouteradresse", "Controleur?section=affichagecompte&action=ajouteradresse");
                request.setAttribute("vueadresse", "Controleur?section=affichagecompte&action=vueadresse");
                try {
                    ag = (AcheteurGestion) session.getAttribute("acheteurgestion");

                    ach = ag.chercherAcheteur(mail, mdp);

                    if (ach != null) {
                        session.setAttribute("Acheteur", ach);
                        // request.setAttribute("affichagecompte", "Controleur?section=affichagecompte&action=voircompte");
                    } else {
                        ach = ag.chercherAcheteur(mail);
                        if (ach != null) {
                            session.setAttribute("Acheteur", ach);
                            request.setAttribute("mailFourni", mail.trim());
                            request.setAttribute("errDeMDP", "Mot de passe incorrect");
                            request.setAttribute("affichagecompte", "Controleur?section=affichagecompte&action=erreurConnection");
                        } else {
                            request.setAttribute("errConnection", "Veuillez saisir l'email et le mot de passe svp !");
                            request.setAttribute("affichagecompte", "Controleur?section=affichagecompte&action=erreurConnection");
                        }
                    }

                } catch (MonException ex) {
                    HashMap<String, String> mp = ex.getMessages();
                    for (String s : mp.keySet()) {
                        request.setAttribute(s, mp.get(s));
                    }
                    request.setAttribute("emailFourni", mail.trim());
                    request.setAttribute("affichagecompte", "Controleur?section=affichagecompte&action=erreurConnection");

                } catch (SQLException ex) {
                    erreurGrave = true;
                } catch (NamingException ex) {
                    erreurGrave = true;
                }
                if (mail.isEmpty() || mail == null
                        && mdp.isEmpty() || mdp == null) {
                    request.setAttribute("errConnection", "Veuillez saisir l'email et le mot de passe svp !");
                    request.setAttribute("affichagecompte", "Controleur?section=affichagecompte&action=erreurConnection");

                }

            }
            if ("voircompte".equalsIgnoreCase(action)) {
                if (session.getAttribute("Acheteur") != null) {
                    Acheteur ach = (Acheteur) session.getAttribute("Acheteur");
                }

                request.setAttribute("affichagecompte", "Controleur?section=affichagecompte&action=voircompte");
                request.setAttribute("ajouteradresse", "Controleur?section=affichagecompte&action=ajouteradresse");
                request.setAttribute("vueadresse", "Controleur?section=affichagecompte&action=vueadresse");

            }

            //Ajouter une adresse
            if ("voiradresse".equalsIgnoreCase(action)) {
                System.out.println("===========Voir adresse");
                Acheteur ach = (Acheteur) session.getAttribute("Acheteur");
                request.setAttribute("ajouteradresse", "Controleur?section=affichagecompte&action=ajouteradresse");
                request.setAttribute("vueadresse", "Controleur?section=affichagecompte&action=vueadresse");
            }

            if ("ajoutAdresse".equalsIgnoreCase(action)) {
                System.out.println("===========ladans ajout adresse");
                boolean adresseFav = false;
                Acheteur ach = (Acheteur) session.getAttribute("Acheteur");
                int idAcheteur = ach.getIdAcheteur();
                String adr1 = request.getParameter("adresse");
                String complement = request.getParameter("complement");
                String cPostal = request.getParameter("cp");
                String ville = request.getParameter("ville");
                String pays = request.getParameter("pays");
                String fav = request.getParameter("selectFav");
                if ("Favori".equalsIgnoreCase(fav)) {
                    adresseFav = true;
                }
                Adresse adr = new Adresse(adr1, complement, cPostal, ville, pays, true);
                adr.setIdAcheteur(idAcheteur);
                adr.setAdresseFav(adresseFav);
                ag = (AcheteurGestion) session.getAttribute("acheteurgestion");
                try {
                    System.out.println("=========ici on est dans le try");
                    ag.addAdresse(ach, adr);
                    session.setAttribute("Acheteur", ach);
                } catch (SQLException ex) {
                    erreurGrave = true;
                }
                request.setAttribute("voircompte", "Controleur?section=affichagecompte&action=voircompte");
//                response.sendRedirect("Controleur?section=connexion&action=voircompte");
            }

        }
// affichage mentions legales (Emma)
        if ("fragement".equalsIgnoreCase(section)) {
            if ("carpediem".equalsIgnoreCase(action)) {
                request.setAttribute("fragement", "Controleur?section=fragement&action=affichagecarpediem");
            }
            if ("mentionslegales".equalsIgnoreCase(action)) {
                request.setAttribute("fragement", "Controleur?section=fragement&action=affichagementionslegales");
            }
            if ("cgv".equalsIgnoreCase(action)) {
                request.setAttribute("fragement", "Controleur?section=fragement&action=affichagecgv");
            }
            if ("plansite".equalsIgnoreCase(action)) {
                request.setAttribute("fragement", "Controleur?section=fragement&action=affichageplansite");
            }
            if ("newsletter".equalsIgnoreCase(action)) {
                request.setAttribute("fragement", "Controleur?section=fragement&action=affichagenewsletter");
            }
            if ("contact".equalsIgnoreCase(action)) {
                request.setAttribute("fragement", "Controleur?section=fragement&action=affichagecontact");
            }
        }

// formulaire de contact (Emma)
        if ("contactformulaire".equalsIgnoreCase(section)) {
            System.out.println("------------------------------------------>>>> contact !");
            try {

                String adrMail = request.getParameter("votremail");
                // subject, sujet du mail
                String subject = request.getParameter("objetcontact");
                String votrenom = request.getParameter("votrenom");
                String votreprenom = request.getParameter("votreprenom");
                String texte = request.getParameter("messagecontact");

                // test en dur
                //SendMail.sendMail("phamduca@gmail.com", "test", "message du mail", request);
                SendMail.sendMail(adrMail, subject, texte, request);
            } catch (MessagingException ex) {
                System.out.println("erreur mail : " + ex.getLocalizedMessage());
                erreurGrave = true;
            }

            System.out.println("------------------------------------------>>>> fin envoi mail");
        }

        // formulaire de contact piece jointe (Emma)
        if ("contactformulairepj".equalsIgnoreCase(section)) {
            System.out.println("------------------------------------------>>>> contact !");
            try {

                String adrMailpj = request.getParameter("votremailpj");
                // subject, sujet du mail
                String subjectpj = request.getParameter("objetcontactpj");
                String votrenompj = request.getParameter("votrenompj");
                String votreprenompj = request.getParameter("votreprenompj");
                String fichierpj = request.getParameter("fichierpj");
                String textepj = request.getParameter("messagecontactpj");

                // test en dur
                //SendMail.sendMail("phamduca@gmail.com", "test", "message du mail", request);
                SendMail.sendMailPJ(adrMailpj, subjectpj, textepj, fichierpj, request);
            } catch (MessagingException ex) {
                System.out.println("erreur mail : " + ex.getLocalizedMessage());
                erreurGrave = true;
            }

            System.out.println("------------------------------------------>>>> fin envoi mail");
        }

   // Module de traitement des promotions Emma 
        // la liste doit aller dans le contexte de la servlet (scope application et non scope session vu qu'il est le meme pour tous
        if (session.getAttribute("PromoGestion") == null) {
            try {
                session.setAttribute("PromoGestion", new PromoGestion()); // instanciation bean métier
            } catch (NamingException ex) {
                erreurGrave = true; // flag boolean pour signaler qu'une erreur s'est produite
            }
        }
        Pg = (PromoGestion) session.getAttribute("PromoGestion");
        List<Promotion> lPg = null;
        try {
            lPg = Pg.listeLivrePromotionActuelle3(); // appel de la méthode métier de récupération
        } catch (SQLException ex) {
            erreurGrave = true; // flag boolean pour signaler qu'une erreur remontée SQL s'est produite
        } catch (NamingException ex) {
            erreurGrave = true;
        }
        context.setAttribute("Liste3dernierslivrespromo", lPg); // place la liste des promo trouvés dans le scope

// Fin module de traitement promotion (Emma) 
        
        // Consultation d'un livre - fiche produit (Emma)
//        if(action.equalsIgnoreCase("consulter"))
        //    {
        // récupération de l'id du livre (id) sur lequel le client a cliqué, au travers de la requête
//           String id_articlelivre = (String)request.getParameter("id_articlelivre");
//            if(id_articlelivre != null && !id_articlelivre.equalsIgnoreCase("")){
        // récupération de toutes les informations sur le livre sélectionné à partir de la bdd
        //Livre articlelivre = Livre.get(id);
        // on stocke le livre dans la requête
        // request.setAttribute("articlelivre", articlelivre);
        // puis on va sur la page du livre passé en requête et la jsp pourra le récupérer avec request.gatAttribute("articlelivre")
//                getServletContext().getRequestDispatcher("/WEB-INF/affichagelivre/AffichageLivre.jsp").forward(request, response);

        // }}

        // renvoi vers la jsp de gestion des erreurs
        if (erreurGrave) {
            pageJsp = "/WEB-INF/erreurs/warning.jsp";
        }

// fin traitement        
// debut affichage via urls dynamiques
        if ("defaut".equalsIgnoreCase(section) || section.isEmpty()|| section == null ) {
            pageJsp = "/WEB-INF/main/Main.jsp";
            request.setAttribute("pagehome", "coucou");
            
        }
        
        if("affichagerubrique".equalsIgnoreCase(section)){
            pageJsp="/WEB-INF/catalogue/Promo.jsp";
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

                //  case "cgv": {
                //    pageJsp = "/WEB-INF/bordure/cgv.jsp";
                //    break;
                // }
                case "affichagecgv": {
                    pageJsp = "/WEB-INF/bordure/cgv.jsp";
                    break;
                }
                //case "plansite": {
                //    pageJsp = "/WEB-INF/bordure/plansite.jsp";
                //     break;
                // }
                case "affichageplansite": {
                    pageJsp = "/WEB-INF/bordure/plansite.jsp";
                    break;
                }
//                case "mentionslegales": {
//                    pageJsp = "/WEB-INF/bordure/mentionslegales.jsp";
//                    break;
//                }
                case "affichagementionslegales": {
                    pageJsp = "/WEB-INF/bordure/mentionslegales.jsp";
                    break;
                }
                // case "carpediem": {
                //    pageJsp = "/WEB-INF/bordure/carpediempresentation.jsp";
                //    break;
                // }
                case "affichagecarpediem": {
                    pageJsp = "/WEB-INF/bordure/carpediempresentation.jsp";
                    break;
                }
                //case "contact": {
                //    pageJsp = "/WEB-INF/bordure/contact.jsp";
                //    break;
                // }
                case "affichagecontact": {
                    pageJsp = "/WEB-INF/bordure/contact.jsp";
                    break;
                }
                //case "newsletter": {
                //    pageJsp = "/WEB-INF/bordure/newsletter.jsp";
                //    break;
                // }
                case "affichagenewsletter": {
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
        if ("affichagepanier".equalsIgnoreCase(section)) {
            //System.out.println(">>>>>>>>passage dans la redirection");
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
        if ("affichagecommande".equalsIgnoreCase(section)) {
            System.out.println(">>>>>>>>>>>>>>>dans affichage commande");
            switch (action) {
                case "affichage": {
                    System.out.println(request.getAttribute("affichagecommande"));
                    pageJsp = "/WEB-INF/commande/Commande.jsp";
                    break;
                }
                case "affichagedetail": {
                    pageJsp = "/WEB-INF/commande/DetailCommande.jsp";
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
                case "affichagechoixlivraison": {
                    pageJsp = "/WEB-INF/commande/choixmodelivraison.jsp";
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

        //redirection pour les themes
        if ("themesaffichage".equalsIgnoreCase(section)) {
            if ("affichage".equalsIgnoreCase(action)) {
                pageJsp = "/WEB-INF/bordure/AffichageTheme.jsp";
            }
        }

        //redirection pour la recherche
        if ("rechercheaffichage".equalsIgnoreCase(section)) {
            if ("affichage".equalsIgnoreCase(action)) {
                if(request.getParameter("pageNumber")!=null){ // un num de page transmis
                    pageJsp = "/WEB-INF/catalogue/resultat.jsp?pageNumber="+request.getParameter("pageNumber");
                }else{
                    //pageJsp = "/WEB-INF/catalogue/resultat.jsp";
                    pageJsp = "/WEB-INF/catalogue/resultat.jsp?pageNumber=1";
                }
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
                case ("connection"): {
                    System.out.println("connexion" + request.getAttribute("connexion"));
                    System.out.println("connection" + request.getAttribute("vueadresse"));
                    pageJsp = "/WEB-INF/compte/VueCompte.jsp";
                    break;
                }
                case ("affichageconnection"): {
                    pageJsp = "/WEB-INF/compte/connectionacheteur.jsp";
                    break;
                }
                case ("voircompte"): {
                    System.out.println("=============dans le case voircompte");
                    System.out.println("entete :" + request.getAttribute("entete"));
                    System.out.println("menu: " + request.getAttribute("menu"));
                    System.out.println("pied: " + request.getAttribute("pied"));
                    System.out.println("vueadresse: " + request.getAttribute("vueadresse"));
                    System.out.println("ajouteradresse: " + request.getAttribute("ajouteradresse"));
                    System.out.println("id adresse fav=====>> " + ((Acheteur) session.getAttribute("Acheteur")).getAdfav().getIdAdresse());
                    pageJsp = "/WEB-INF/compte/VueCompte.jsp";
                    break;
                }
                case ("erreurConnection"): {
                    pageJsp = "/WEB-INF/compte/connectionacheteur.jsp";
                    break;
                }
                case ("ajouteradresse"): {
                    System.out.println("=>>>>>>>>>>>==== Dans le case ajouteradresse");
                    pageJsp = "/WEB-INF/compte/AdresseAcheteur.jsp";
                    break;
                }
                case ("vueadresse"): {
                    pageJsp = "/WEB-INF/compte/vueadresse.jsp";
                    break;
                }
                case ("modifieradresse"): {
                    pageJsp = "/WEB-INF/compte/AdresseAcheteur.jsp";
                    break;
                }

            }
        }
        
           //redirection pour la page promotion Emma
        if ("promosaffichage".equalsIgnoreCase(section)) {
            switch (action) {
                case ("affichagepromo"): {
                    pageJsp = "/WEB-INF/catalogue/Promo.jsp";
                    break;
                }
            }
        }
        
        

        //redirection pour la fiche livre
        if ("livreaffichage".equalsIgnoreCase(section)) {
            switch (action) {
                case ("afficher"): {
                    pageJsp = "/WEB-INF/affichagelivre/AffichageLivre.jsp";
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
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
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
