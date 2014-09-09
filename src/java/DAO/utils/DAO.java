
package DAO.utils;

import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {

    public DAO() {
    }
    
    public abstract T create(T obj) throws SQLException;
    
    public abstract boolean update(T obj) throws SQLException;
    
    public abstract boolean delete(T obj) throws SQLException;
    
    public abstract T find(int id) throws SQLException;
    
    public abstract T find(String s) throws SQLException;
    
    public abstract List<T> findAll(String s) throws SQLException;
}
