
package servlet;

import bean.metier.LivreGestion;
import bean.produit.Livre;
import java.io.IOException;
import java.sql.SQLException;
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
        
        if ("recherche".equalsIgnoreCase(section)){ // Module Recherche (Eddy)
            try {
                    if(session.getAttribute("beanRecherche")==null){    
                        session.setAttribute("beanRecherche",new LivreGestion());
                    }
                } catch (NamingException ex) {
                    Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);            
                }
            
            try {           
                if(request.getParameter("action")!=null){
                    if("rechercher".equalsIgnoreCase(request.getParameter("action"))){
                        LivreGestion lg=(LivreGestion)session.getAttribute("beanRecherche");
                        String champRecherche=request.getParameter("ChampRecherche");
                        List<Livre> lL=null;
                        lL=lg.findAll(champRecherche);
                        session.setAttribute("rechercheListeLivre",lL); // place la liste des livres trouvés
                    }
                }
            }catch (SQLException ex){
                 Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);     
            }
            
            pageJsp ="/WEB-INF/catalogue/recherche.jsp";
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
