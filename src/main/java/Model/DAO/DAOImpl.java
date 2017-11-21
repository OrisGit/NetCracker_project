package Model.DAO;

import Model.Connector;
import Model.Interfaces.DAO;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.Collection;
import java.util.List;

public abstract class DAOImpl<T> implements DAO<T> {
    @Override
    public void add(T entity) throws DAOException {
        Session session = null;
        try{
            session = Connector.getSesion();
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }catch (Exception e){
            throw new DAOException("Ошибка при добавлении объекта");
        }finally {
            if(session!=null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public void update(T entity) throws DAOException {
        Session session = null;
        try{
            session = Connector.getSesion();
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        }catch (Exception e){
            throw new DAOException("Ошибка при обновлении объекта");
        }finally {
            if(session!=null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public T getById(Class<T> clazz, Long id) throws DAOException {
        Session session = null;
        T entity;
        try{
            session = Connector.getSesion();
            session.beginTransaction();
            entity = session.load(clazz,id);
            session.getTransaction().commit();
        }catch (Exception e){
            throw new DAOException("Ошибка при получении объекта");
        }finally {
            if(session!=null && session.isOpen()){
                session.close();
            }
        }
        return entity;
    }

    @Override
    public Collection<T> getAll(Class<T> clazz) throws DAOException {
        Session session = null;
        List<T> entities;
        try{
            session = Connector.getSesion();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(clazz);
            entities = criteria.list();
            session.getTransaction().commit();
        }catch (Exception e){
            throw new DAOException("Ошибка при получении объектов");
        }finally {
            if(session!=null && session.isOpen()){
                session.close();
            }
        }
        return entities;
    }

    @Override
    public void delete(T entity) throws DAOException {
        Session session = null;
        try{
            session = Connector.getSesion();
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }catch (Exception e){
            throw new DAOException("Ошибка при удалении объекта");
        }finally {
            if(session!=null && session.isOpen()){
                session.close();
            }
        }
    }
}
