import model.dao.*;
import model.entities.*;
import model.interfaces.*;

public class DAOTest {
    public static void main(String[] args) throws DAOException {
        addNewPrice(createTestPriceEntity());
    }

    public static void addNewDrug(DrugEntity drugEntity) throws DAOException {

        DrugDAO drugDAO = new DrugDAOImpl();
        drugDAO.add(drugEntity);
    }

    public static void addNewDrugStore(DrugstoreEntity drugstoreEntity) throws DAOException {
        DrugstoreDAO drugstoreDAO = new DrugstoreDAOImpl();
        drugstoreDAO.add(drugstoreEntity);
    }

    public static void addNewTEffect(TherapeuticEffectEntity therapeuticEffectEntity) throws DAOException {
        TEffectDAO tEffectDAO = new TEffectDAOImpl();
        tEffectDAO.add(therapeuticEffectEntity);
    }

    public static void addNewPeffect(PharmachologicEffectEntity pharmachologicEffectEntity) throws DAOException {
        PEffectDAO pEffectDAO = new PEffectDAOImpl();
        pEffectDAO.add(pharmachologicEffectEntity);
    }

    public static void addNewPrice(PriceEntity priceEntity) throws DAOException {
        PriceDAO priceDAO = new PriceDAOImpl();
        priceDAO.add(priceEntity);
    }


    public static DrugEntity createTestDrugEntity() throws DAOException {
        TherapeuticEffectEntity te = createTestTEffectEntity();
        PharmachologicEffectEntity pe = createTestPEffectEntity();
        addNewTEffect(te);
        addNewPeffect(pe);
        return new DrugEntity("name", "releaseForm", "manufacturer", "activeIngredient", pe, te, "description");
    }

    public static PharmachologicEffectEntity createTestPEffectEntity() {
        return new PharmachologicEffectEntity("name","description");
    }

    public static TherapeuticEffectEntity createTestTEffectEntity(){
        return new TherapeuticEffectEntity("name","description");
    }

    public static DrugstoreEntity createTestDrugstoreEntity(){
        return new DrugstoreEntity("name", "district", "street", "building", 12L, "workingHours", (short)1);
    }

    public static PriceEntity createTestPriceEntity() throws DAOException {
        DrugEntity drugEntity = createTestDrugEntity();
        DrugstoreEntity drugstoreEntity = createTestDrugstoreEntity();
        addNewDrug(drugEntity);
        addNewDrugStore(drugstoreEntity);
        return new PriceEntity(drugEntity, drugstoreEntity, 12L);
    }

}
