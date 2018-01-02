import com.google.gson.reflect.TypeToken;
import model.dao.*;
import model.entities.DrugEntity;
import model.entities.PharmachologicEffectEntity;
import model.entities.PriceEntity;
import model.entities.TherapeuticEffectEntity;
import model.import_export.*;
import model.interfaces.DrugDAO;
import model.interfaces.PEffectDAO;
import model.interfaces.PriceDAO;
import model.interfaces.TEffectDAO;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ImportExportTest {
    public static void main(String[] args) throws DAOException, ExportException, ImportException {
        XmlImport();
    }

    public static void XmlImport() throws ImportException {
        XmlImportManager<DrugEntity> xmlImportManager = new XmlImportManager<>(new DrugDAOImpl(),DrugEntity.class);
        xmlImportManager.importFromFile("E:/Temp/exit.xml");
    }

    public static void XmlExport() throws DAOException, ExportException {
        ExportManager<DrugEntity> em = new XmlExportManager<>(DrugEntity.class, true);
        DrugDAO drugDAO = new DrugDAOImpl();
        List<DrugEntity> therapeuticEffectEntities = drugDAO.getAll();
        em.exportToFile("E:/Temp/exit.xml",therapeuticEffectEntities);
    }

    public static void JsonImport() throws ImportException {
        ImportManager<TherapeuticEffectEntity> im = new JsonImportManager<>(new TEffectDAOImpl(), TherapeuticEffectEntity[].class);
        im.importFromFile("E:/Temp/exit.json");
    }

    public static void JsonExport() throws ExportException, DAOException {
        JsonExportManager<TherapeuticEffectEntity> em = new JsonExportManager<>(true);
        TEffectDAO tEffectDAO = new TEffectDAOImpl();
        List<TherapeuticEffectEntity> priceEntities = tEffectDAO.getAll();
        em.exportToFile("E:/Temp/exit.json", priceEntities);
        System.out.println(em.exportToString(priceEntities));
    }
}
