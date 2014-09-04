
package DAO.livre;

import DAO.utils.DAO;
import DAO.utils.FournirConnectionIt;
import DAO.utils.MaConnexionBDD;
import bean.produit.Auteur;
import java.io.Serializable;
import javax.naming.NamingException;

public class AuteurDAO extends DAO<Auteur> implements Serializable{
    private FournirConnectionIt fc;

    public AuteurDAO() throws NamingException {
        this.fc = new MaConnexionBDD();
    }
    
    

    @Override
    public boolean create(Auteur obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Auteur obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Auteur obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Auteur find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
