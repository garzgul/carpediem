// bean metier de gestion de la commande
package bean.metier;

import DAO.commande.CommandeDAO;
import bean.acheteur.Acheteur;
import bean.commande.Commande;
import bean.commande.DetailCommande;
import bean.commande.LignePanier;
import bean.produit.Livre;
import java.sql.SQLException;
import java.text.ParseException;
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
    
    public boolean createCommande(HashMap<Integer,LignePanier> maliste, Acheteur ach
     ) throws SQLException, ParseException{
        boolean res = false;
        Commande cde = new Commande();
        Date d = new Date();
        // parametre de commande implicite
        cde.setDateCde(d);
        // cde.set
        
        // gestion du no de commande
        
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nouveauNoCde = null;
        
        String lastCDE = cDao.getLastNoCde();
        if (!lastCDE.isEmpty()){
            int longeur = lastCDE.length();
            String cdenb = lastCDE.substring(8, (longeur-1));
            String dateCde = lastCDE.substring(0,7);
            int longueurNoCommande = cdenb.length();
            long numeroCDE = Long.valueOf(cdenb);
            Date datelast = sdf.parse(dateCde);
            String dCDE = null;
            String no = "0";
            // construction du no de commande            
            if (datelast!=d){
                nouveauNoCde=sdf.format(d);
                nouveauNoCde= nouveauNoCde+"00000001";
            }else{
                long noCommande = Long.valueOf(lastCDE);
                noCommande += 1;
                nouveauNoCde =""+noCommande;
            }
        }
        cde.setNumCde(nouveauNoCde);
        
        // gestion du nom d'acheteur
        cde.setAcheteurCde(ach);
        // gestion du mode de livraison
        
        
        return res;
    }
    
    


    private DetailCommande remplissageCde(HashMap<Integer, Livre> liste) {
        DetailCommande dc = new DetailCommande();
        
        return dc;
    }
}