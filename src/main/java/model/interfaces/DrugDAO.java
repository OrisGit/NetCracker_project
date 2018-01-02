package model.interfaces;

import model.dao.DAOException;
import model.entities.DrugEntity;

import java.util.List;

public interface DrugDAO{
    void add(DrugEntity entity) throws DAOException;

    void update(DrugEntity entity) throws DAOException;

    DrugEntity getById(Long id) throws DAOException;

    List<DrugEntity> getAll() throws DAOException;

    void delete(DrugEntity entity) throws DAOException;
}
