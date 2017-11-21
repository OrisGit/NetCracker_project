package Model.DAO;

import Model.Entities.DrugEntity;
import Model.Interfaces.DrugDAO;
import Prameters.Parameters;
import org.hibernate.mapping.Collection;

import java.util.List;

public class DrugDAOImpl extends DAOImpl<DrugEntity> implements DrugDAO{

    public DrugEntity getById(Long id) throws DAOException {
        return super.getById(DrugEntity.class, id);
    }

    public List<DrugEntity> getAll() throws DAOException {
        return super.getAll(DrugEntity.class);
    }
}
