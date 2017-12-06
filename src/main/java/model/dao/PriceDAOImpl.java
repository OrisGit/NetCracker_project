package model.dao;

import model.entities.PriceEntity;
import model.interfaces.PriceDAO;

import java.util.List;

public class PriceDAOImpl extends DAO<PriceEntity> implements PriceDAO {
@Override
public PriceEntity getById(Long id) throws DAOException {
        return getById(PriceEntity.class,id);
        }

@Override
public List<PriceEntity> getAll() throws DAOException {
        return getAll(PriceEntity.class);
        }
}
