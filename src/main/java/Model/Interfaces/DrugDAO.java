package Model.Interfaces;

import Prameters.Parameters;
import org.hibernate.mapping.Collection;

public interface DrugDAO {
    Collection getDrugsByParameter(Parameters parameters);
}
