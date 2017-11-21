package Model.Interfaces;

import Model.DAO.DAOException;
import java.util.Collection;

public interface DAO<T> {
    void add(T entity) throws DAOException;
    void update(T entity) throws DAOException;
    T getById(Class<T> clazz,Long id) throws DAOException;
    Collection<T> getAll(Class<T> clazz) throws DAOException;
    void delete(T entity) throws DAOException;
}
