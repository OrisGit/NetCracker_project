package model.import_export;

import model.dao.*;
import model.import_export.marshalling.AbstractMarshaller;
import model.import_export.marshalling.JsonMarshaller;
import model.import_export.marshalling.MarshallingException;
import model.import_export.marshalling.XmlMarshaller;
import model.interfaces.*;

public class Exporter {
    public static String export(FormatType type, boolean format) throws ExportException {
        AbstractMarshaller marshaller;

        DrugDAO drugDAO = new DrugDAOImpl();
        DrugstoreDAO drugstoreDAO = new DrugstoreDAOImpl();
        TEffectDAO tEffectDAO = new TEffectDAOImpl();
        PEffectDAO pEffectDAO = new PEffectDAOImpl();
        PriceDAO priceDAO = new PriceDAOImpl();
        EntityWrapper wrapper;

        try{
            wrapper = new EntityWrapper(pEffectDAO.getAll(),tEffectDAO.getAll(),
                    drugstoreDAO.getAll(),drugDAO.getAll(),priceDAO.getAll());
        } catch (DAOException e) {
            throw new ExportException(e);
        }


        if(type.equals(FormatType.XML)){
            marshaller = new XmlMarshaller(format);
        }else{
            marshaller = new JsonMarshaller(format);
        }

        String result;
        try {
            result = marshaller.exportToString(wrapper);
        } catch (MarshallingException e) {
            throw new ExportException(e);
        }

        return result;
    }
}
