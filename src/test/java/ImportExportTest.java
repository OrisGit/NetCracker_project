import com.google.gson.reflect.TypeToken;
import model.dao.*;
import model.entities.DrugEntity;
import model.entities.PharmachologicEffectEntity;
import model.entities.PriceEntity;
import model.import_export.*;
import model.interfaces.DrugDAO;
import model.interfaces.PEffectDAO;
import model.interfaces.PriceDAO;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ImportExportTest {
    public static void main(String[] args) throws DAOException, ExportException {
        XmlExport();
    }

    public static void XmlImport(){
        Type type = new TypeToken<List<PharmachologicEffectEntity>>(){}.getType();
        String path = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<entities>\n" +
                "    <entity xsi:type=\"pharmachologicEffectEntity\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "        <description>9</description>\n" +
                "        <id>b10e3378-307d-0b05-9df0-5033102d3d00</id>\n" +
                "        <name>9</name>\n" +
                "    </entity>\n" +
                "    <entity xsi:type=\"pharmachologicEffectEntity\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "        <description>1</description>\n" +
                "        <id>7d3e1e62-ce0d-41c1-adb7-d23ec8ae306c</id>\n" +
                "        <name>1</name>\n" +
                "    </entity>\n" +
                "    <entity xsi:type=\"pharmachologicEffectEntity\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "        <description>sds</description>\n" +
                "        <id>b67cd76e-eea4-0ffe-bc00-5cfee408c792</id>\n" +
                "        <name>asd</name>\n" +
                "    </entity>\n" +
                "</entities>\n";
        ImportManager im = new XmlImportManager<PharmachologicEffectEntity>(new PEffectDAOImpl(),PharmachologicEffectEntity.class);
        im.importListFromString(path, type);
    }

    public static void XmlExport() throws DAOException, ExportException {
        ExportManager<DrugEntity> em = new XmlExportManager<>(DrugEntity.class, true);
        DrugDAO drugDAO = new DrugDAOImpl();
        List<DrugEntity> therapeuticEffectEntities = drugDAO.getAll();
        em.exportToFile("E:/Temp/exit.xml",therapeuticEffectEntities);
    }

    public static void JsonImport(){
        Type type = new TypeToken<List<PharmachologicEffectEntity>>(){}.getType();
        String impStr = "[{\"id\":\"7d3e1e62-ce0d-41c1-adb7-d23ec8ae306c\",\"name\":\"1\",\"description\":\"1\"},{\"id\":\"b67cd76e-eea4-4ffe-bc24-5cfee408c792\",\"name\":\"asd\",\"description\":\"sds\"},{\"id\":\"b10e3378-307d-4b85-9df0-5033102d3d00\",\"name\":\"9\",\"description\":\"9\"}]\n";
        ImportManager im = new JsonImportManager<PharmachologicEffectEntity>(new PEffectDAOImpl(),PharmachologicEffectEntity.class);
        im.importListFromString(impStr, type);
    }

    public static void JsonExport() throws ExportException, DAOException {
        JsonExportManager<PriceEntity> em = new JsonExportManager<>(true);
        PriceDAO priceDAO = new PriceDAOImpl();
        List<PriceEntity> priceEntities = priceDAO.getAll();
        em.exportToFile("E:/Temp/exit.json", priceEntities);
        System.out.println(em.exportToString(priceEntities));
    }
}
