
package servlet;

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
        

        if ("recherche".equalsIgnoreCase(section)){ // Module Recherche (Eddy)
            try {
                
                    if(session.getAttribute("beanRecherche")==null){    
                        System.out.println("att beanrecherche null");
                        session.setAttribute("beanRecherche",new LivreGestion());
                    }
                } catch (NamingException ex) {
                    Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);            
                }
            
            try {   
                if(request.getParameter("action")!=null){
                    System.out.println("action != null");
                    if("rechercher".equalsIgnoreCase(request.getParameter("action"))){
                        System.out.println("action = rechercher");
                        LivreGestion lg=(LivreGestion)session.getAttribute("beanRecherche");
                        System.out.println("lg = "+lg);
                        String champRecherche=request.getParameter("ChampRecherche");
                        System.out.println("champrecherche = "+champRecherche);
                        List<Livre> lL=null;
                        lL=lg.findAll(champRecherche);
                        System.out.println("lL = " + lL);
                        session.setAttribute("rechercheListeLivre",lL); // place la liste des livres trouvés
                    }
                }
            }catch (SQLException ex){
                 Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);     
            }
            
            //pageJsp ="/WEB-INF/main/Main.jsp";
            pageJsp ="/WEB-INF/catalogue/recherche.jsp";
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
