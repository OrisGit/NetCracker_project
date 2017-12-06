package model.dao;

import model.entities.DrugEntity;
import model.interfaces.DrugDAO;

import java.util.List;

public class DrugDAOImpl extends DAO<DrugEntity> implements DrugDAO {

    @Override
    public DrugEntity getById(Long id) throws DAOException {
        return super.getById(DrugEntity.class, id);
    }

    @Override
    public List<DrugEntity> getAll() throws DAOException {
        return super.getAll(DrugEntity.class);
    }

}
