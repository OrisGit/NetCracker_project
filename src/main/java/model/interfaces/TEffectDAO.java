package model.interfaces;

import model.dao.DAOException;
import model.entities.TherapeuticEffectEntity;

import java.util.List;

public interface TEffectDAO {
    void add(TherapeuticEffectEntity entity) throws DAOException;

    void update(TherapeuticEffectEntity entity) throws DAOException;

    TherapeuticEffectEntity getById(Long id) throws DAOException;

    List<TherapeuticEffectEntity> getAll() throws DAOException;

    void delete(TherapeuticEffectEntity entity) throws DAOException;
}
