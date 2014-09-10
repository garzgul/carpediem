
package servlet;

import bean.commande.Panier;
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
        String action =request.getParameter("action");
        Panier p = null;
        Livre l = null;
        LivreGestion lg = null;
        
        String pageJsp ="/WEB-INF/main/Main.jsp";
        
        // mettre les sections ici

        // import entete de page
         if("Entete".equalsIgnoreCase(section)){
            request.setAttribute("today", new Date());
            pageJsp="/WEB-INF/bordure/Entete.jsp";
          }
         
         // import menu horizontal
          if("Menu".equalsIgnoreCase(section)){
            pageJsp="/WEB-INF/bordure/Menu.jsp";
          }
          
          // import pied de page
        if("Pied".equalsIgnoreCase(section)){
            pageJsp="/WEB-INF/bordure/Pied.jsp";
          }
<<<<<<< HEAD
=======
        
        // import liens pied de page
        if(("bordure".equalsIgnoreCase(section)) && ("carpediempresentation".equalsIgnoreCase(action))){
             request.setAttribute("pagevisee", "/WEB-INF/bordure/carpediempresentation.jsp");
            pageJsp="/WEB-INF/main/Main.jsp";
          }
        
         if(("bordure".equalsIgnoreCase(section)) && ("mentionslegales".equalsIgnoreCase(action))){
            request.setAttribute("pagevisee", "/WEB-INF/bordure/mentionslegales.jsp");
             pageJsp="/WEB-INF/main/Main.jsp";
          }
         
          if(("bordure".equalsIgnoreCase(section)) && ("cgv".equalsIgnoreCase(action))){
            request.setAttribute("pagevisee", "/WEB-INF/bordure/cgv.jsp");
              pageJsp="/WEB-INF/main/Main.jsp";
          }
          
           if(("bordure".equalsIgnoreCase(section)) && ("plansite".equalsIgnoreCase(action))){
            request.setAttribute("pagevisee", "/WEB-INF/bordure/plansite.jsp");
               pageJsp="/WEB-INF/main/Main.jsp";
          }
           
            if(("bordure".equalsIgnoreCase(section)) && ("newsletter".equalsIgnoreCase(action))){
             request.setAttribute("pagevisee", "/WEB-INF/bordure/newsletter.jsp");
                pageJsp="/WEB-INF/main/Main.jsp";
          }
            
          if(("bordure".equalsIgnoreCase(section)) && ("contact".equalsIgnoreCase(action))){
             request.setAttribute("pagevisee", "/WEB-INF/bordure/contact.jsp");
              pageJsp="/WEB-INF/main/Main.jsp";
          }
         
         
        
        
>>>>>>> master

        if("panier".equalsIgnoreCase(section)){
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
            
            if("add".equalsIgnoreCase(action)){
                p=(Panier)session.getAttribute("panier");
                
                try {
                    l = lg.findLivre(idLivre);
                    p.addlivre(l);
                    session.setAttribute("panier", p.getLignes().values());
                } catch (SQLException ex) {
                    erreurGrave=true;
                } catch (NamingException ex) {
                    erreurGrave=true;
                } catch (Exception ex) {
                    erreurGrave=true;
                }
                pageJsp = "/WEB-INF/panier/panier.jsp";
            }
            
            // suppresion d'un item du panier
            
            if("remove".equalsIgnoreCase(action)){
                p = (Panier)session.getAttribute("panier");
                try {
                    l =lg.findLivre(idLivre);
                    p.enleverLivre(l);
                    session.setAttribute("panier", p.getLignes().values());
                } catch (SQLException ex) {
                    erreurGrave=true;
                } catch (NamingException ex) {
                    erreurGrave=true;
                } catch (Exception ex) {
                    erreurGrave=true;
                }
                pageJsp = "/WEB-INF/panier/panier.jsp";
            }
            
            // diminution de la quantite commandee pour un item
            
            if("moins".equalsIgnoreCase(action)){
                p =(Panier)session.getAttribute("panier");
                
                try {
                    l =lg.findLivre(idLivre);
                    p.update(idLivre, -1);
                } catch (SQLException ex) {
                    erreurGrave=null;
                } catch (NamingException ex) {
                    erreurGrave=null;
                }
                pageJsp = "/WEB-INF/panier/panier.jsp";
            }
            
            // augmentation de la quantite commandee pour un tiem
            
            if("plus".equalsIgnoreCase(action)){
                
                    p = (Panier)session.getAttribute("panier");
                try {   
                    l = lg.findLivre(idLivre);
                    p.update(idLivre, 1);
                } catch (SQLException ex) {
                    erreurGrave=true;
                } catch (NamingException ex) {
                    erreurGrave=true;
                }
                pageJsp = "/WEB-INF/panier/panier.jsp";
            }
           
        }

        // import liens pied de page
        if(("bordure".equalsIgnoreCase(section)) && ("carpediempresentation".equalsIgnoreCase(action))){
             request.setAttribute("pagevisee", "/WEB-INF/bordure/carpediempresentation.jsp");
            pageJsp="/WEB-INF/main/Main.jsp";
          }
        
<<<<<<< HEAD
         if(("bordure".equalsIgnoreCase(section)) && ("mentionslegales".equalsIgnoreCase(action))){
            request.setAttribute("pagevisee", "/WEB-INF/bordure/mentionslegales.jsp");
             pageJsp="/WEB-INF/main/Main.jsp";
          }
         
          if(("bordure".equalsIgnoreCase(section)) && ("cgv".equalsIgnoreCase(action))){
            request.setAttribute("pagevisee", "/WEB-INF/bordure/cgv.jsp");
              pageJsp="/WEB-INF/main/Main.jsp";
          }
          
           if(("bordure".equalsIgnoreCase(section)) && ("plansite".equalsIgnoreCase(action))){
            request.setAttribute("pagevisee", "/WEB-INF/bordure/plansite.jsp");
               pageJsp="/WEB-INF/main/Main.jsp";
          }
           
            if(("bordure".equalsIgnoreCase(section)) && ("newsletter".equalsIgnoreCase(action))){
             request.setAttribute("pagevisee", "/WEB-INF/bordure/newsletter.jsp");
                pageJsp="/WEB-INF/main/Main.jsp";
          }
            
          if(("bordure".equalsIgnoreCase(section)) && ("contact".equalsIgnoreCase(action))){
             request.setAttribute("pagevisee", "/WEB-INF/bordure/contact.jsp");
              pageJsp="/WEB-INF/main/Main.jsp";
          }
         


=======
>>>>>>> master
        if("panier".equalsIgnoreCase(section)){
            int idLivre = Integer.valueOf(request.getParameter("ref"));
            
            // affichage du panier
            if ("affichage".equalsIgnoreCase(action)) {
                if (session.getAttribute("panier") == null) {
                    session.setAttribute("panier", new Panier());
                }
                p = (Panier) session.getAttribute("panier");
                session.setAttribute("maliste", p.getLignes().values());
<<<<<<< HEAD
                pageJsp = "/WEB-INF/panier/panier.jsp";
=======
                
                
>>>>>>> master
            }
            
            //addtion d'un item au panier
            
            if("add".equalsIgnoreCase(action)){
                p=(Panier)session.getAttribute("panier");
                
                try {
                    l = lg.findLivre(idLivre);
                    p.addlivre(l);
                    session.setAttribute("panier", p.getLignes().values());
                } catch (SQLException ex) {
                    erreurGrave=true;
                } catch (NamingException ex) {
                    erreurGrave=true;
                } catch (Exception ex) {
                    erreurGrave=true;
                }
<<<<<<< HEAD
                pageJsp = "/WEB-INF/panier/panier.jsp";
=======

>>>>>>> master
            }
            
            // suppresion d'un item du panier
            
            if("remove".equalsIgnoreCase(action)){
                p = (Panier)session.getAttribute("panier");
                try {
                    l =lg.findLivre(idLivre);
                    p.enleverLivre(l);
                    session.setAttribute("panier", p.getLignes().values());
                } catch (SQLException ex) {
                    erreurGrave=true;
                } catch (NamingException ex) {
                    erreurGrave=true;
                } catch (Exception ex) {
                    erreurGrave=true;
                }
<<<<<<< HEAD
                pageJsp = "/WEB-INF/panier/panier.jsp";
=======

>>>>>>> master
            }
            
            // diminution de la quantite commandee pour un item
            
            if("moins".equalsIgnoreCase(action)){
                p =(Panier)session.getAttribute("panier");
                
                try {
                    l =lg.findLivre(idLivre);
                    p.update(idLivre, -1);
                } catch (SQLException ex) {
                    erreurGrave=null;
                } catch (NamingException ex) {
                    erreurGrave=null;
                }
<<<<<<< HEAD
                pageJsp = "/WEB-INF/panier/panier.jsp";
=======

>>>>>>> master
            }
            
            // augmentation de la quantite commandee pour un tiem
            
            if("plus".equalsIgnoreCase(action)){
                
                    p = (Panier)session.getAttribute("panier");
                try {   
                    l = lg.findLivre(idLivre);
                    p.update(idLivre, 1);
                } catch (SQLException ex) {
                    erreurGrave=true;
                } catch (NamingException ex) {
                    erreurGrave=true;
                }
<<<<<<< HEAD
                pageJsp = "/WEB-INF/panier/panier.jsp";
            }
           
        }

        if ("recherche".equalsIgnoreCase(section)){ // *** Module Recherche (Eddy) ***
=======

            }
            request.setAttribute("pagevisee", "/WEB-INF/panier/panier.jsp");
           
        }
        
        if ("recherche".equalsIgnoreCase(section)){ // Module Recherche (Eddy)
>>>>>>> master
            try {
                    if(session.getAttribute("beanRecherche")==null){    
                        session.setAttribute("beanRecherche",new LivreGestion());
                    }
                } catch (NamingException ex) {
                    erreurGrave=true;            
                }
<<<<<<< HEAD
  
            if(request.getParameter("action")!=null){

                if("rechercher".equalsIgnoreCase(request.getParameter("action"))){
                        
                    try { 
                        System.out.println("action = rechercher");
                        lg=(LivreGestion)session.getAttribute("beanRecherche");
                        System.out.println("lg = "+lg);
=======
            
            try {           
                if(request.getParameter("action")!=null){
                    if("rechercher".equalsIgnoreCase(request.getParameter("action"))){
                        lg=(LivreGestion)session.getAttribute("beanRecherche");
>>>>>>> master
                        String champRecherche=request.getParameter("ChampRecherche");
                        System.out.println("champrecherche = "+champRecherche);
                        List<Livre> lL=null;
                        lL=lg.findAll(champRecherche);
                        System.out.println("lL = " + lL);
                        //session.setAttribute("rechercheListeLivre",lL); // place la liste des livres trouvés
                        request.setAttribute("rechercheResultat",lL); // place la liste des livres trouvés
                        request.setAttribute("pagevisee","/WEB-INF/catalogue/resultat.jsp"); // definit le lien où le resultat doit s'afficher
                        pageJsp ="/WEB-INF/catalogue/resultat.jsp"; //pk main deconne ?
                    } catch (SQLException ex){
                         erreurGrave=true;     
                    }
                }
<<<<<<< HEAD
=======
            }catch (SQLException ex){
                 erreurGrave=true;     
>>>>>>> master
            }

            if(request.getParameter("action")==null){
                pageJsp ="/WEB-INF/catalogue/recherche.jsp";
            }
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
        
        
        
        

        

        //Tous ce qui concerne l'acheteur "Connection, inscription, déconnection
        

        
        

        //Tous ce qui concerne l'acheteur "Connection, inscription, déconnection
        
<<<<<<< HEAD

=======
        
        
        

        //Tous ce qui concerne l'acheteur "Connection, inscription, déconnection
        
>>>>>>> master
        if("inscription".equalsIgnoreCase(action)){
            
            
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
