package model.import_export;

import model.dao.*;
import model.import_export.marshalling.AbstractUnmarshaller;
import model.import_export.marshalling.JsonUnmarshaller;
import model.import_export.marshalling.UnmarshallingException;
import model.import_export.marshalling.XmlUnmarshaller;

import java.util.List;
import java.util.logging.Logger;

public class Importer {

    public static Logger logger = Logger.getLogger("Importer");

    public static void _import(String str, FormatType type) throws ImportException {
        AbstractUnmarshaller unmarshaller;
        if(type.equals(FormatType.XML)){
            unmarshaller = new XmlUnmarshaller();
        }else{
            unmarshaller = new JsonUnmarshaller();
        }

        EntityWrapper entityWrapper;
        try {
            entityWrapper = unmarshaller.importFromString(str);
        } catch (UnmarshallingException e) {
            throw new ImportException(e);
        }

        if(entityWrapper!=null){
            importEntities(entityWrapper.getPharmachologicEffects(), new PEffectDAOImpl());
            importEntities(entityWrapper.getTherapeuticEffects(), new TEffectDAOImpl());
            importEntities(entityWrapper.getDrugstores(),new DrugstoreDAOImpl());
            importEntities(entityWrapper.getDrugs(),new DrugDAOImpl());
            importEntities(entityWrapper.getPrice(),new PriceDAOImpl());
        }
    }

    private static void importEntities(List entities, DAO dao){
        for(Object entity : entities){
            try {
                dao.replicate(entity);
            } catch (DAOException e) {
                logger.warning("Ошибка импорта: "+e.getMessage());
            }
        }
    }

}
