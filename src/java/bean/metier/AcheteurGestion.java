package bean.metier;

import DAO.acheteur.AcheteurDAO;
import DAO.acheteur.AdresseDAO;
import bean.acheteur.Acheteur;
import bean.acheteur.Adresse;
import java.io.Serializable;
import java.sql.SQLException;
import static java.time.Instant.now;
import static java.time.OffsetTime.now;
import java.util.ArrayList;
import java.util.HashMap;
import javax.naming.NamingException;
import utilitaire.MonException;

public class AcheteurGestion implements Serializable {

    private AcheteurDAO achDAO;
    private AdresseDAO adrDAO;

    public AcheteurGestion() throws NamingException {
        achDAO = new AcheteurDAO();
        adrDAO = new AdresseDAO();
    }
    // Ajout d'un acheteur tout en vérifiant les champs obligatoir

    public Acheteur ajoutAcheteur(Acheteur ach, String confirMDP) throws SQLException, MonException {

        Boolean erreur = false;
        HashMap<String, String> hm = new HashMap<>();
        Acheteur acheteur = achDAO.find(ach.getEmailAcheteur(), ach.getMdpAcheteur());

        if (acheteur != null) {
            erreur = true;
            hm.put("errCompte", "Ce compte existe déjà");
        }

        if (ach.getEmailAcheteur() == null || ach.getEmailAcheteur().isEmpty()) {

            //  || ach.getEmailAcheteur().matches("[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})")
            erreur = true;
            hm.put("errEmail", "Veuillez saisir un mail valide !");
        }
        if (ach.getNomAcheteur() == null || ach.getNomAcheteur().isEmpty()) {
            erreur = true;
            hm.put("errNom", "Ces informations sont obligatoires !");
        }
        if (ach.getPrenomAcheteur() == null || ach.getPrenomAcheteur().isEmpty()) {
            erreur = true;
            hm.put("errPrenom", "Ces informations sont obligatoires !");
        }
        if (ach.getPseudoAcheteur() == null || ach.getPseudoAcheteur().isEmpty()) {
            erreur = true;
            hm.put("errPseudo", "Ces informations sont obligatoires !");
        }
        /* Le mot de passe contient les lettres magicule et miniscule, 
         au moins un chiffre, au moins un caractère spécial,
         au moins ne contient pas de '|'
         */

        if (!ach.getMdpAcheteur().equals(confirMDP)) {
            erreur = true;
            hm.put("errConfMDP", "Veuillez vérifier votre mot de passe !");
        }

        if (ach.getMdpAcheteur() == null || ach.getMdpAcheteur().isEmpty()) {
            //|| ach.getMdpAcheteur().matches("[a-zA-Z(?=(.*[0-9]){1,})(?=(.*\\\\W)+})(?!.*\\\\|)]")
            erreur = true;
            hm.put("errMDP", "Vérifiez entrer un mot de passe !");
        }

        if (ach.getTelAcheteur() == null || ach.getTelAcheteur().isEmpty()
                || ach.getTelAcheteur().matches("[0]{1}[1-7]{1}([-/. ][0-9]{2}){4}")) {
            erreur = true;
            hm.put("errTel", "Ce numéro de téléphone n'est pas valide !");
        }
        if (erreur) {
            throw new MonException(hm, "Echec à la création du compte");
        }

        achDAO.create(ach);
        return ach;

    }

    //Connection d'un acheteur
    public Acheteur chercherAcheteur(String mail, String mdp) throws SQLException, MonException {
        Boolean erreur = false;
        HashMap<String, String> hm = new HashMap<>();

        Acheteur ach = null;
        if (mail.isEmpty() || mail == null
                || mdp == null || mdp.isEmpty()) {
            erreur = true;
            hm.put("errConnection", "Veuillez saisir l'email et le mot de passe svp !");
            return null;
        } else {
            ach = achDAO.find(mail.trim(), mdp);
        }
        if (ach != null) {
            return ach;
       }

        return null;
    }
    
    public Acheteur chercherAcheteur(String mail) throws SQLException, MonException{
        Boolean erreur = false;
        HashMap<String, String> hm = new HashMap<>();

        Acheteur ach = null;
        if(mail.isEmpty() && mail == null){
            erreur = true;
            hm.put("errConnection", "Veuillez saisir l'email et le mot de passe svp !");
            return null;
        }
        ach = achDAO.find(mail);
            if (ach != null) {
                erreur = true;
                hm.put("errDeMDP", "Mot de passe incorrect");
                return ach;
            }
            
       
        return null;
    }

    // Modifier les données d'un acheteur.
    public Acheteur modifierAcheteur(Acheteur ach) throws SQLException, MonException {
        boolean test = achDAO.update(ach);
        if (true == test) {
            return ach;
        }
        return null;
    }

    public String supprimerAcheteur(Acheteur ach) throws SQLException {
        String reponse = "Votre compte n'a pas été supprimer";
        if (achDAO.find(ach.getEmailAcheteur(), ach.getMdpAcheteur()) != null) {
            boolean test = achDAO.delete(ach);
            if (test) {
                reponse = "Votre compte a été supprimé avec succé !";
            }
        }
        return reponse;
    }
    
    //=========== Ajout d'une adresse pour un acheteur
    public Adresse addAdresse (Acheteur ach, Adresse adr) throws SQLException{
        Adresse adresse = null;
        if(adr != null && ach != null){
            adr.setIdAcheteur(ach.getIdAcheteur());
            adresse = adrDAO.create(adr);
        }
        return adresse;        
    }
    
    public Adresse getAdressefav(Acheteur ach){
        
        return ach.getAdfav();
    }
}
