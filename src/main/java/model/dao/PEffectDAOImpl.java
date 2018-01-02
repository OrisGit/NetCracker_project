package model.dao;

import model.entities.PharmachologicEffectEntity;
import model.interfaces.PEffectDAO;

import java.util.List;

public class PEffectDAOImpl  extends DAO<PharmachologicEffectEntity> implements PEffectDAO {
    @Override
    public PharmachologicEffectEntity getById(Long id) throws DAOException {
        return getById(PharmachologicEffectEntity.class,id);
    }

    @Override
    public List<PharmachologicEffectEntity> getAll() throws DAOException {
        return getAll(PharmachologicEffectEntity.class);
    }
}
