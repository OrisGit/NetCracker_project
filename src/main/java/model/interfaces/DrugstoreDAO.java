package model.interfaces;

import model.dao.DAOException;
import model.entities.DrugstoreEntity;

import java.util.List;

public interface DrugstoreDAO {
    void add(DrugstoreEntity entity) throws DAOException;

    void update(DrugstoreEntity entity) throws DAOException;

    DrugstoreEntity getById(Long id) throws DAOException;

    List<DrugstoreEntity> getAll() throws DAOException;

    void delete(DrugstoreEntity entity) throws DAOException;
}
