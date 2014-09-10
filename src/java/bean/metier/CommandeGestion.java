// bean metier de gestion de la commande
package bean.metier;

import DAO.commande.CommandeDAO;
import bean.commande.Commande;
import bean.commande.LignePanier;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.naming.NamingException;

/**
 *
 * @author cdi116
 */
public class CommandeGestion {
    private CommandeDAO cDao;

    public CommandeGestion() throws NamingException {
        cDao=new CommandeDAO();
    }
    
    public boolean createCommande(HashMap<Integer,LignePanier> maliste){
        boolean res = false;
        Commande cde = new Commande();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        StringBuilder noCde = new StringBuilder();
        noCde.append(sdf.format(d));
        System.out.println(noCde);
        
        
        
        return res;
    }
    
    
}
