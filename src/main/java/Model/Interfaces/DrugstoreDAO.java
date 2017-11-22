package Model.Interfaces;

import Model.DAO.DAOException;
import Model.Entities.DrugEntity;
import Model.Entities.DrugstoreEntity;

import java.util.List;

public interface DrugstoreDAO {
    void add(DrugstoreEntity entity) throws DAOException;

    void update(DrugstoreEntity entity) throws DAOException;

    DrugstoreEntity getById(Long id) throws DAOException;

    List<DrugstoreEntity> getAll() throws DAOException;

    void delete(DrugstoreEntity entity) throws DAOException;
}
