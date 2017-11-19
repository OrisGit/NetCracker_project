package Model;

import Model.Entities.DrugEntity;
import Model.Entities.DrugstoreEntity;
import Prameters.Parameters;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DataBaseModel implements Model {

    private Connector connector;

    public DataBaseModel() {
        connector = new Connector();
    }

    public List<DrugEntity> getDrugs(Parameters parameters) {
        List<DrugEntity> result;
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DrugEntity.class);

        if(parameters.getDrug_id()!=null){
            detachedCriteria.add(Restrictions.eq("drugId",parameters.getDrug_id()));
        }else{

            if(parameters.getDrug_name()!=null){
                detachedCriteria.add(Restrictions.eq("dname",parameters.getDrug_name()));
            }

            if(parameters.getDrug_active_ingredient()!=null){
                detachedCriteria.add(Restrictions.eq("activeingridient",parameters.getDrug_active_ingredient()));
            }

            if(parameters.getDrug_manufacturer()!=null){
                detachedCriteria.add(Restrictions.eq("manufacturer",parameters.getDrug_manufacturer()));
            }

            if(parameters.getDrug_max_cost()!=null){
                detachedCriteria.createCriteria("DrugStoreEntity")
                        .add(Restrictions.le("cost",parameters.getDrug_max_cost()));
            }


        }
        try(Session session = connector.getSesion()){
            session.beginTransaction();

            result = detachedCriteria.getExecutableCriteria(session).list();
        }
        return result;
    }

    public List<DrugstoreEntity> getDrugstories(Parameters parameters) {
        return null;
    }
}
