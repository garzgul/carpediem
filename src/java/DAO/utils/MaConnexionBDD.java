
package DAO.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MaConnexionBDD implements Serializable,FournirConnectionIt{
    private DataSource ds;

    public MaConnexionBDD() throws NamingException {
        InitialContext ctt=new InitialContext();
        ds=(DataSource) ctt.lookup("jdbc/baselivre");
    }
   
    @Override
    public Connection fournir() throws SQLException{
       
            return ds.getConnection();
        
    }
    
}
