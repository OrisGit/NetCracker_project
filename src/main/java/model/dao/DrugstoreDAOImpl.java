package model.dao;

import model.entities.DrugstoreEntity;
import model.interfaces.DrugstoreDAO;

import java.util.List;

public class DrugstoreDAOImpl extends DAO<DrugstoreEntity> implements DrugstoreDAO {

    @Override
    public DrugstoreEntity getById(Long id) throws DAOException {
        return super.getById(DrugstoreEntity.class,id);
    }

    @Override
    public List<DrugstoreEntity> getAll() throws DAOException {
        return super.getAll(DrugstoreEntity.class);
    }
}
