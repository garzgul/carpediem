
package DAO.utils;

public abstract class DAO<T> {

    public DAO() {
    }
    
    public abstract boolean create(T obj);
    
    public abstract boolean update(T obj);
    
    public abstract boolean delete(T obj);
    
    public abstract T find(int id);
    
    
}
