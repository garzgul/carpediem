package bean.metier;

import DAO.acheteur.AcheteurDAO;
import bean.acheteur.Acheteur;
import java.io.Serializable;
import java.sql.SQLException;
import static java.time.Instant.now;
import static java.time.OffsetTime.now;
import java.util.HashMap;
import javax.naming.NamingException;
import utilitaire.MouradException;

public class AcheteurGestion implements Serializable{
    
    private AcheteurDAO achDAO;

    public AcheteurGestion() throws NamingException{
        achDAO = new AcheteurDAO();
    }
    // Ajout d'un acheteur tout en vérifiant les champs obligatoir


    
    public Acheteur ajoutAcheteur(Acheteur ach, String confirMDP) throws SQLException, MouradException{

        Boolean erreur = false;
        HashMap<String, String> hm = new HashMap<>();
        Acheteur acheteur = achDAO.find(ach.getEmailAcheteur(), ach.getMdpAcheteur());
        
        if(acheteur != null){
            erreur = true;
            hm.put("errCompte", "Ce compte existe déjà");
        }
        
        if(ach.getEmailAcheteur() == null || ach.getEmailAcheteur().isEmpty()){
           
              //  || ach.getEmailAcheteur().matches("[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})")
            erreur = true;
            hm.put("errEmail", "Veuillez saisir un mail valide !");
        }
        if(ach.getNomAcheteur()==null || ach.getNomAcheteur().isEmpty()){
            erreur = true;
            hm.put("errNom", "Ces informations sont obligatoires !");
        }
        if(ach.getPrenomAcheteur()==null || ach.getPrenomAcheteur().isEmpty()){
            erreur = true;
            hm.put("errPrenom", "Ces informations sont obligatoires !");
        }
        if(ach.getPseudoAcheteur() == null || ach.getPseudoAcheteur().isEmpty()){
            erreur = true;
            hm.put("errPseudo", "Ces informations sont obligatoires !");
        }
        /* Le mot de passe contient les lettres magicule et miniscule, 
               au moins un chiffre, au moins un caractère spécial,
               au moins ne contient pas de '|'
                    */
        
        if(!ach.getMdpAcheteur().equals(confirMDP)){
            erreur = true;
            hm.put("errConfMDP", "Veuillez vérifier votre mot de passe !");
        }

        if(ach.getMdpAcheteur()== null || ach.getMdpAcheteur().isEmpty()){
            //|| ach.getMdpAcheteur().matches("[a-zA-Z(?=(.*[0-9]){1,})(?=(.*\\\\W)+})(?!.*\\\\|)]")
            erreur = true;
            hm.put("errMDP", "Vérifiez entrer un mot de passe !");
        }
        

        if(ach.getTelAcheteur()== null || ach.getTelAcheteur().isEmpty()
                || ach.getTelAcheteur().matches("[0]{1}[1-7]{1}([-/. ][0-9]{2}){4}")){
            erreur= true;
            hm.put("errTel", "Ce numéro de téléphone n'est pas valide !");
        }
        if(erreur){
            throw new MouradException(hm, "Echec à la création du compte");
        }
            
       achDAO.create(ach);
        return ach;
        
    }
    
     //Connection d'un acheteur
    public Acheteur chercherAcheteur(String mail, String mdp) throws SQLException{
         Acheteur ach = null;
        if(mail.isEmpty() || mail == null
                || mdp == null || mdp.isEmpty()){
            return null;
        }else{
           ach = achDAO.find(mail.trim(), mdp);
           
        }
        if(ach != null){
            return ach;
        }
        return null;
    }
    
   
   
    
}
