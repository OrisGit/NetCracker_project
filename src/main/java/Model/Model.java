package Model;

import Prameters.Parameters;

import java.util.List;

public interface Model {
    List<DrugEntity> getDrugs(Parameters parameters);
    List<DrugstoreEntity> getDrugstories(Parameters parameters);
}
