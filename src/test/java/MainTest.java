import com.google.gson.reflect.TypeToken;
import model.dao.DAOException;
import model.dao.PEffectDAOImpl;
import model.dao.TEffectDAOImpl;

import model.entities.PharmachologicEffectEntity;
import model.entities.TherapeuticEffectEntity;
import model.import_export.JsonImportManager;
import model.import_export.interfaces.ImportManager;
import model.interfaces.PEffectDAO;
import model.interfaces.TEffectDAO;
import model.import_export.JsonExportManager;
import model.import_export.interfaces.ExportManager;

import java.lang.reflect.Type;
import java.util.List;

public class MainTest {
    public static void main(String[] args) throws DAOException {
//         controller controller = new controller();
//         controller.run();
//        PharmachologicEffectEntity pe = new PharmachologicEffectEntity("asd","sds");
//        PEffectDAO pEffectDAO = new PEffectDAOImpl();
//        try {
//            List<PharmachologicEffectEntity> pp = pEffectDAO.getAll();
//            pp.forEach(System.out::println);
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }

//        ExportManager em = new JsonExportManager();
//        PEffectDAO pEffectDAO = new PEffectDAOImpl();
//        List<PharmachologicEffectEntity> therapeuticEffectEntities = pEffectDAO.getAll();
//        System.out.println(em.exportToString(therapeuticEffectEntities));

        Type type = new TypeToken<List<PharmachologicEffectEntity>>(){}.getType();
        String impStr = "[{\"id\":\"7d3e1e62-ce0d-41c1-adb7-d23ec8ae306c\",\"name\":\"1\",\"description\":\"1\"},{\"id\":\"b67cd76e-eea4-4ffe-bc24-5cfee408c792\",\"name\":\"asd\",\"description\":\"sds\"},{\"id\":\"b10e3378-307d-4b85-9df0-5033102d3d00\",\"name\":\"9\",\"description\":\"9\"}]\n";
        ImportManager im = new JsonImportManager<PharmachologicEffectEntity>(new PEffectDAOImpl(),PharmachologicEffectEntity.class);
        im.importListFromString(impStr, type);
    }
}
