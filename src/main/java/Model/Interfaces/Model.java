package Model.Interfaces;

import Model.Entities.DrugEntity;
import Model.Entities.DrugstoreEntity;
import Prameters.Parameters;

import java.util.List;

public interface Model {
    List<DrugEntity> getDrugs(Parameters parameters);
    List<DrugstoreEntity> getDrugstories(Parameters parameters);
}
