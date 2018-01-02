package model.interfaces;

import model.dao.DAOException;
import model.entities.PriceEntity;

import java.util.List;

public interface PriceDAO {
    void add(PriceEntity entity) throws DAOException;

    void update(PriceEntity entity) throws DAOException;

    PriceEntity getById(Long id) throws DAOException;

    List<PriceEntity> getAll() throws DAOException;

    void delete(PriceEntity entity) throws DAOException;
}
