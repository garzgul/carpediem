// bean metier de gestion de la commande
package bean.metier;

import DAO.commande.CommandeDAO;
import DAO.commande.FraisDePortDAO;
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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
     ) throws SQLException, ParseException, NamingException{
        boolean res = false;
        Commande cde = new Commande();
        LocalDate ld = LocalDate.now();
        Date d = new Date();
        // parametre de commande implicite
        cde.setDateCde(d);
        String noCde = null;
        
        
        // gestion du no de commande
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Commande lastcde = cDao.getLastCde();
        Instant instant = Instant.ofEpochMilli(lastcde.getDateCde().getTime());
        LocalDate dateLastCde = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        
        
        
        if (ld.equals(dateLastCde)){
            long noCommande = Long.valueOf(lastcde.getNumCde());
            noCommande+=1;
            noCde =""+noCommande;
        }else{
            noCde=sdf.format(d);
            noCde=noCde+"00000001";
            
        }
        System.out.println("no de commande avant le set "+noCde);
        
        cde.setNumCde(noCde);
        System.out.println("no de commande dans la gestion commande "+cde.getNumCde());
        
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
        FraisDePortDAO fraisDAO = new FraisDePortDAO();
        FraisDePort fdp = fraisDAO.find();
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
        //remplissage de la commande
        cde.setDetailCde(maliste);
        
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
            if(a.getIdAdresse()==idAdresse){
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