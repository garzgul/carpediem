
package DAO;

import java.sql.Connection;
import java.sql.SQLException;


public interface FournirConnectionIt {
    public Connection fournir()throws SQLException;
}
