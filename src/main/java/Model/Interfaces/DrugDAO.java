package Model.Interfaces;

import Model.DAO.DAOException;
import Model.Entities.DrugEntity;

import java.util.List;

public interface DrugDAO{
    void add(DrugEntity entity) throws DAOException;

    void update(DrugEntity entity) throws DAOException;

    DrugEntity getById(Long id) throws DAOException;

    List<DrugEntity> getAll() throws DAOException;

    void delete(DrugEntity entity) throws DAOException;
}
