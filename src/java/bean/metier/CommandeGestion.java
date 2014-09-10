// bean metier de gestion de la commande
package bean.metier;

import DAO.commande.CommandeDAO;
import bean.acheteur.Acheteur;
import bean.acheteur.Adresse;
import bean.commande.Commande;
import bean.commande.FraisDePort;
import bean.commande.LignePanier;
import bean.commande.ModeLivraison;
import bean.commande.SuiviLivraison;
import bean.commande.livraison;
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
    
    public boolean createCommande(HashMap<Integer,LignePanier> maliste, Acheteur ach, Adresse ad
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
        // pour le moment, il est unique
        cde.setModeLivraison(ModeLivraison.poste);
        // gestion du suivi
        SuiviLivraison sl = new SuiviLivraison(livraison.enpreparation);
        cde.setSuiviCde(sl);
        //gestion de l'adresse
        cde.setAdresseCde(ad);
        //gestion des frais de port
        FraisDePort fdp = new FraisDePort(10.0f);
        cde.setFraisCde(fdp);
        // calcul du prix HT et de la tva de la commande
        float prixHTTotal =0.0f;
        float tvaTotal =0.0f;
        for (LignePanier lp :maliste.values()){
            prixHTTotal += lp.getPrixHT()*lp.getQte();
            tvaTotal += lp.getTva()*lp.getQte();
        }
        cde.setHtCde(prixHTTotal);
        cde.setTvaCde(tvaTotal);
        cde.setTtcCde(prixHTTotal+tvaTotal);
        // paiement de la commande (simulé pour le moment)
        cde.setPayementCde(true);
        // fin du remplissage de l'objet commande (envoi a la DAO)
        
        res=cDao.createCde(cde);
        
        
        return res;
    }
    
    
// methode non necessaire, il suffit d'importer le contenu du panier lors de la
// validation de la commande.

    private LignePanier remplissageCde(HashMap<Integer, Livre> liste) {
        LignePanier lp = null;
        
        return lp;
    }
}