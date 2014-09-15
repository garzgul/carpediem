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
import static java.lang.ProcessBuilder.Redirect.to;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import static java.util.Date.from;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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
            System.out.println(">>>>>>>>>>>>>>>>passage dans panier");
            if (session.getAttribute("beanLivreGestion") == null) {
                try {
                    session.setAttribute("beanLivreGestion", new LivreGestion());

                } catch (NamingException ex) {
                    erreurGrave = true;
                }
            }
            lg = (LivreGestion) session.getAttribute("beanLivreGestion");
            if (session.getAttribute("panier") == null) {
                session.setAttribute("panier", new Panier());
            }
            p = (Panier) session.getAttribute("panier");
            session.setAttribute("maliste", p.getLignes().values());

            // affichage du panier
            if ("affichage".equalsIgnoreCase(action)) {
                System.out.println(">>>>>>>>>>>>passage dans affichage panier");
                request.setAttribute("affichagepanier", "Controleur?section=affichagepanier&action=affichage");
            }

            // affichage du contenu du panier
            if ("affichagepanier".equalsIgnoreCase(action)) {
                System.out.println(">>>>>>>>>>>>dans affichage panier");
                request.setAttribute("affichagepanier", "Controleur?section=affichagepanier&action=affichagedetail");
            }

            int idLivre = 0;
            if (request.getParameter("ref") != null) {
                idLivre = Integer.valueOf(request.getParameter("ref"));
                System.out.println(idLivre);
            }
            //addtion d'un item au panier
            if ("add".equalsIgnoreCase(action)) {
                p = (Panier) session.getAttribute("panier");

                try {
                    System.out.println(">>>>>>>>>dans le add panier");
                    l = lg.findLivre(idLivre);
                    System.out.println(">>>>>>>>livre = " + l.toString());
                    p.addlivre(l);
                    System.out.println(">>>>>>>>>>apres addition");
                    session.setAttribute("panier", p.getLignes().values());
                } catch (SQLException ex) {
                    System.out.println(">>>>>>>>erreur sql");
                    erreurGrave = true;
                } catch (NamingException ex) {
                    erreurGrave = true;
                    System.out.println(">>>>>>>>erreur namming");
                } catch (Exception ex) {
                    erreurGrave = true;
                    System.out.println(">>>>>>>>>>>>erreur perso");
                }
                request.setAttribute("affichagepanier", "Controleur?section=affichagepanier&action=affichage");
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
                request.setAttribute("affichagepanier", "Controleur?section=affichagepanier&action=affichage");
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
                request.setAttribute("affichagepanier", "Controleur?section=affichagepanier&action=affichage");
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
                request.setAttribute("affichagepanier", "Controleur?section=affichagepanier&action=affichage");
            }

        }
// fin module panier        

// module commande        
        if ("commande".equalsIgnoreCase(section)) {
            if (session.getAttribute("maliste") == null) {
                request.setAttribute("affichagepanier", "Controleur?section=affichagepanier&action=affichage");
                // getServletContext().getRequestDispatcher(pageJsp).forward(request, response);
            }
            if (session.getAttribute("panier") == null) {
                request.setAttribute("affichagepanier", "Controleur?section=affichagepanier&action=affichage");
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
                    request.setAttribute("rechercheResultat", lL); // place la liste des livres trouvés dans le scope request
                    request.setAttribute("recherche", "Controleur?section=rechercheaffichage&action=affichage"); // signalement
                    // qu'une liste de livres sera à afficher dynamiquement en résultat
                }
            }
        }
// fin module recherche (Eddy)
        
// module gestion fiche livre  
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
                session.setAttribute("ficheLivreCourant", l); // place le livre courant
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
                request.setAttribute("affichagecompte", "Controleur?section=affichagecompte&action=voircompte");
            }

            if ("seconnecter".equalsIgnoreCase(action)) {
                request.setAttribute("affichagecompte", "Controleur?section=affichagecompte&action=affichageconnection");
                System.out.println("=========== i l est ici ====<<<");
            }
            if ("connection".equalsIgnoreCase(action)) {

                String mail = request.getParameter("email");
                String mdp = request.getParameter("mdp");

                Acheteur ach = null;
                try {
                    ag = (AcheteurGestion) session.getAttribute("acheteurgestion");
                    System.out.println("========== la on est dans le try =====");
                    ach = ag.chercherAcheteur(mail, mdp);
                    request.setAttribute("affichagecompte", "Controleur?section=affichagecompte&action=voircompte");

                } catch (SQLException ex) {
                    erreurGrave = true;
                }
                session.setAttribute("Acheteur", ach);

            }
            if ("voircompte".equalsIgnoreCase(action)) {
                request.setAttribute("affichagecompte", "Controleur?section=affichagecompte&action=voircompte");

            }
        }

// formulaire de contact (Emma)
        if ("contactformulaire".equalsIgnoreCase(section)) {
            System.out.println("------------------------------------------>>>> contact !");
            String result;
            //from, expediteur
            String votremail = request.getParameter("votremail");
            // subject, sujet du mail
            String objetcontact = request.getParameter("objetcontact");
            String votrenom = request.getParameter("votrenom");
            String votreprenom = request.getParameter("votreprenom");
            String messagecontact = request.getParameter("messagecontact");
            //  email de retour, destinataire
            String to = "emmanuelle_esnaud@hotmail.com";
            // envoi de l'email depuis localhost
            String host = "localhost";

            // proprietes du systeme
            Properties properties = System.getProperties();
           // initialisation mail server
            properties.setProperty("mail.smtp.host", host);
            // Mise en session
            Session mailSession = Session.getDefaultInstance(properties);
            try {
                // MimeMessage par default
                MimeMessage message = new MimeMessage(mailSession);
                // Set From: header field of the header.
                message.setFrom(new InternetAddress(votremail));
                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(to));
                // Set Subject: header field
                message.setSubject("Sujet de votre mail");
                // Create the message part 
                BodyPart messageBodyPart = new MimeBodyPart();
                // Fill the message
                messageBodyPart.setText("Votre message");
                // Create a multipar message
                Multipart multipart = new MimeMultipart();
                // Set text message part
                multipart.addBodyPart(messageBodyPart);
                // Part two is attachment
                messageBodyPart = new MimeBodyPart();
                String filename = "file.txt";
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);
                // Send the complete message parts
                message.setContent(multipart);
                // Send message
                Transport.send(message);
                String title = "Envoi d'email";
                result = "Message bien envoyé!";
            } catch (MessagingException mex) {
                mex.printStackTrace();
                result = "Une erreur est survenue lors de l'envoi du message!";
            }
        }

//
//               session.setAttribute("user", m);
//          
//                HashMap<String, String> mp = ex.getMessages();
//                for (String s : mp.keySet()) {
//                    request.setAttribute(s, mp.get(s));
//                }
//                request.setAttribute("mailFourni", mail.trim());
//                request.setAttribute("nomFourni", nom.trim());
//                request.setAttribute("prenomFourni", prenom.trim());
            //        request.setAttribute("messagecontactFourni", messagecontact.trim());//faut il enlever les espaces?
//                pageJsp = "/WEB-INF/bordure/contact.jsp";
//   
//            }
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
// debut module emma
            // }}
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
            if ("affichagepanier".equalsIgnoreCase(section)) {
                System.out.println(">>>>>>>>passage dans la redirection");
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
                    case ("connection"): {
                        pageJsp = "/WEB-INF/compte/VueCompte.jsp";
                    }
                    case ("affichageconnection"): {
                        pageJsp = "/WEB-INF/compte/connectionacheteur.jsp";
                        break;
                    }
                    case ("voircompte"): {
                        pageJsp = "/WEB-INF/compte/VueCompte.jsp";
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
