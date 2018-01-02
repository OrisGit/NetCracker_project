package view.fx;

import model.entities.DrugEntity;
import model.entities.DrugstoreEntity;
import model.entities.PriceEntity;

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
                drugstoreEntity.getWorkingHours(), drugstoreEntity.getIsRoundTheClock()!=0);
    }

    public static Price from(PriceEntity priceEntity) {
        return new Price(priceEntity.getDrug(), priceEntity.getDrugstore(), priceEntity.getCost());
    }

    public static LinkedList fromAll(List entities) {
        LinkedList list = new LinkedList<>();
        for (Object entity : entities) {
            if (entity instanceof DrugstoreEntity) list.add(Mapper.from((DrugstoreEntity) entity));
            if (entity instanceof DrugEntity) list.add(Mapper.from((DrugEntity) entity));
            if (entity instanceof PriceEntity) list.add(Mapper.from((PriceEntity) entity));
        }
        return list;
    }
}
