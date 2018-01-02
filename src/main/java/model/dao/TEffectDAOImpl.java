package model.dao;

import model.entities.TherapeuticEffectEntity;
import model.interfaces.TEffectDAO;

import java.util.List;

public class TEffectDAOImpl extends DAO<TherapeuticEffectEntity> implements TEffectDAO {
    @Override
    public TherapeuticEffectEntity getById(Long id) throws DAOException {
        return getById(TherapeuticEffectEntity.class,id);
    }

    @Override
    public List<TherapeuticEffectEntity> getAll() throws DAOException {
        return getAll(TherapeuticEffectEntity.class);
    }


}