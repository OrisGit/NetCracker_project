import model.dao.*;
import model.import_export.*;
import model.import_export.marshalling.*;
import model.interfaces.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class ImportExportTest {
    public static void main(String[] args) throws DAOException, MarshallingException, UnmarshallingException, ExportException, IOException, ImportException {
        File file = new File("E:/Temp/exit.xml");
//        String str = Exporter.export(FormatType.XML,true);
//        FileWriter fileWriter = new FileWriter(file);
//        fileWriter.write(str);
//        fileWriter.flush();
//        fileWriter.close();

        FileReader fileReader = new FileReader(file);
        byte[] bytes = Files.readAllBytes(file.toPath());
        Importer._import(new String(bytes,"UTF-8"),FormatType.XML);
    }

    public static void XmlImport() throws UnmarshallingException {
        AbstractUnmarshaller xmlImportManager = new XmlUnmarshaller();
        EntityWrapper entityWrapper = xmlImportManager.importFromFile("E:/Temp/exit.xml");
    }

    public static void XmlExport() throws DAOException, MarshallingException {
        AbstractMarshaller exporter = new XmlMarshaller(true);

        exporter.exportToFile("E:/Temp/exit.xml",getWrapper());
    }

    private static EntityWrapper getWrapper() throws DAOException, MarshallingException {
        DrugDAO drugDAO = new DrugDAOImpl();
        DrugstoreDAO drugstoreDAO = new DrugstoreDAOImpl();
        TEffectDAO tEffectDAO = new TEffectDAOImpl();
        PEffectDAO pEffectDAO = new PEffectDAOImpl();
        PriceDAO priceDAO = new PriceDAOImpl();

        EntityWrapper wrapper = new EntityWrapper(pEffectDAO.getAll(),tEffectDAO.getAll(),drugstoreDAO.getAll(),drugDAO.getAll(),priceDAO.getAll());
        return wrapper;
    }

    public static void JsonImport() throws UnmarshallingException {
        AbstractUnmarshaller im = new JsonUnmarshaller();
        EntityWrapper entityWrapper = im.importFromFile("E:/Temp/exit.json");
        entityWrapper.getDrugs();
    }

    public static void JsonExport() throws MarshallingException, DAOException {
        AbstractMarshaller exporter = new JsonMarshaller(true);
        exporter.exportToFile("E:/Temp/exit.json", getWrapper());
    }
}
