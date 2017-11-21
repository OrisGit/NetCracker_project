package Model.DAO;

import Model.Entities.DrugstoreEntity;
import Model.Interfaces.DrugstoreDAO;

import java.util.List;

public class DrugstoreDAOImpl extends DAOImpl<DrugstoreEntity> implements DrugstoreDAO{

    public DrugstoreEntity getById(Long id) throws DAOException {
        return super.getById(DrugstoreEntity.class, id);
    }


    public List<DrugstoreEntity> getAll() throws DAOException {
        return super.getAll(DrugstoreEntity.class);
    }
}
