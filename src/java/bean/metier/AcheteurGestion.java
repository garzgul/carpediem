package bean.metier;

import DAO.acheteur.AcheteurDAO;
import bean.acheteur.Acheteur;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import javax.naming.NamingException;

public class AcheteurGestion implements Serializable{
    
    private AcheteurDAO achDAO;

    public AcheteurGestion() throws NamingException{
        achDAO = new AcheteurDAO();
    }
    // Ajout d'un acheteur tout en vérifiant les champs obligatoir
    public Acheteur ajoutAcheteur(Acheteur ach, String confirMDP) throws SQLException{
        Boolean erreur = false;
        HashMap<String, String> hm = new HashMap<>();
        
        if(ach.getEmailAcheteur() == null || ach.getEmailAcheteur().isEmpty()
                || ach.getEmailAcheteur().matches("[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})")){
            erreur = true;
            hm.put("errEmail", "Veuillez saisir un mail valide !");
        }
        if(ach.getNomAcheteur()==null || ach.getNomAcheteur().isEmpty()){
            erreur = true;
            hm.put("errNom", "Le champ Nom est obligatoir !");
        }
        if(ach.getPrenomAcheteur()==null || ach.getPrenomAcheteur().isEmpty()){
            erreur = true;
            hm.put("errPrenom", "Le champ Prenom est obligatoir !");
        }
        if(ach.getPseudoAcheteur() == null || ach.getPseudoAcheteur().isEmpty()){
            erreur = true;
            hm.put("errPseudo", "Veuillez saisir un pseudonyme");
        }
        /* Le mot de passe contient les lettres magicule et miniscule, 
               au moins un chiffre, au moins un caractère spécial,
               au moins ne contient pas de '|'
                    */
        if(ach.getMdpAcheteur()== null || ach.getMdpAcheteur().isEmpty()
                || ach.getMdpAcheteur().matches("[a-zA-Z(?=(.*[0-9]){1,})(?=(.*\\\\W)+})(?!.*\\\\|)]")){
            
            erreur = true;
            hm.put("errMPD", "Vérifiez votre mot de passe !");
        }
        
        if(!ach.getMdpAcheteur().equals(confirMDP)){
            erreur = true;
            hm.put("errConfMDP", "Veillez vérifier votre mot de passe !");
        }
        if(ach.getTelAcheteur()== null || ach.getTelAcheteur().isEmpty()
                || ach.getTelAcheteur().matches("[0]{1}[1-7|9]{1}([-/. ][0-9]{2}){4}")){
            erreur= true;
            hm.put("errTel", "Votre numéro de téléphone n'est pas vlide !");
        }
            
       achDAO.create(ach);
        return ach;
        
    }
    
}