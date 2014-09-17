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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        
        
        // gestion du no de commande
        
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nouveauNoCde = null;
        System.out.println("avant le test du no de commande");
        String lastCDE = cDao.getLastNoCde();
        System.out.println("apres le test du no de commande");
        if (lastCDE.isEmpty() || lastCDE == null){
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
        System.out.println("apres la creation du no de commande");
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
        // l'adresse incluse par defaut est l'adresse de livraison par defaut
        cde.setAdresseCde(ach.getAdfav());
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
        // mise en place du mode de paiement (defini par defaut a CB
        cde.setModePaiement("CB");
        // fin du remplissage de l'objet commande (envoi a la DAO)
        
        // creation de la commande
        System.out.println(" creation de la commande en base");
        res =cDao.createCde(cde);
        System.out.println("apres la creation de la commande en base");
        return res;
    }
    
    public Commande setAdresse(Commande c, int idAdresse){
        Acheteur ach = c.getAcheteurCde();
        ArrayList<Adresse> listeAd = ach.getListAdresseAcheteur();
        Adresse adchoisi= new Adresse();
        for (Adresse a:listeAd){
            if(a.getIdadresse()==idAdresse){
                adchoisi=a;
            }
        }
        c.setAdresseCde(adchoisi);
        
        return c;
    }
    
    public Commande setDate(Commande c){
        c.setDateCde(new Date());
        return c;
    }
    public Commande updateCdeFraisDePort(Commande c){
        
        return c; 
    }
    

}