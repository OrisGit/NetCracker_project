package view.fx;

import model.entities.DrugEntity;
import model.entities.DrugstoreEntity;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Mapper {

    public static Drug from(DrugEntity drugEntity) {
        return new Drug(
                drugEntity.getName(), drugEntity.getReleaseForm(), drugEntity.getManufacturer(),
                drugEntity.getActiveIngredient(), drugEntity.getPharmachologicEffect().getName(),
                drugEntity.getTherapeuticEffect().getName(), drugEntity.getDescription());
    }

    public static Drugstore from(DrugstoreEntity drugstoreEntity) {
        return new Drugstore(drugstoreEntity.getName(), drugstoreEntity.getDistrict(), drugstoreEntity.getStreet(),
                drugstoreEntity.getBuilding(), Objects.toString(drugstoreEntity.getPhone(), ""),
                drugstoreEntity.getWorkingHours(), Boolean.valueOf(drugstoreEntity.getIsRoundTheClock().toString()));
    }

    public static LinkedList fromAll(List entities) {
        LinkedList list = new LinkedList<>();
        for (Object entity : entities) {
            if (entity instanceof DrugstoreEntity) list.add(Mapper.from((DrugstoreEntity) entity));
            if (entity instanceof DrugEntity) list.add(Mapper.from((DrugEntity) entity));
        }
        return list;
    }
}
