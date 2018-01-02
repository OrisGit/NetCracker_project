package model.dao;

import model.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;

import java.util.List;

public abstract class DAO<T> {

    public void replicate(T entity) throws DAOException{
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.replicate(entity, ReplicationMode.EXCEPTION);
            session.getTransaction().commit();
        }catch (Exception e){
            throw new DAOException("Ошибка при добавлении объекта"+ e.getMessage());
        }finally {
            if(session!=null && session.isOpen()){
                session.close();
            }
        }
    }

    public void add(T entity) throws DAOException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }catch (Exception e){
            throw new DAOException("Ошибка при добавлении объекта"+ e.getMessage());
        }finally {
            if(session!=null && session.isOpen()){
                session.close();
            }
        }
    }

    public void update(T entity) throws DAOException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
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

    public T getById(Class<T> clazz, Long id) throws DAOException {
        Session session = null;
        T entity;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
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

    public List<T> getAll(Class<T> clazz) throws DAOException {
        Session session = null;
        List<T> entities;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
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

    public void delete(T entity) throws DAOException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
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
