package model.interfaces;

import model.dao.DAOException;
import model.entities.PharmachologicEffectEntity;

import java.util.List;

public interface PEffectDAO {
    void add(PharmachologicEffectEntity entity) throws DAOException;

    void update(PharmachologicEffectEntity entity) throws DAOException;

    PharmachologicEffectEntity getById(Long id) throws DAOException;

    List<PharmachologicEffectEntity> getAll() throws DAOException;

    void delete(PharmachologicEffectEntity entity) throws DAOException;
}
