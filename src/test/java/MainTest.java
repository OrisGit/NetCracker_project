import Model.DAO.DAOException;

import Model.DAO.PEffectDAOImpl;

import Model.Entities.PharmachologicEffectEntity;

import Model.Interfaces.PEffectDAO;

import java.util.List;

public class MainTest {
    public static void main(String[] args) {
//         Controller controller = new Controller();
//         controller.run();
        PharmachologicEffectEntity pe = new PharmachologicEffectEntity("asd","sds");
        PEffectDAO pEffectDAO = new PEffectDAOImpl();
        try {
            List<PharmachologicEffectEntity> pp = pEffectDAO.getAll();
            pp.forEach(System.out::println);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
