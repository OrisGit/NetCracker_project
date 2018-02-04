import controller.Controller;
import controller.ControllerImpl;
import model.HibernateUtil;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

public class StartServer {

    private static Logger logger = Logger.getLogger("StartServer");

    public static void main(String[] args) {
        logger.info("Starting registry...");
        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(2099);
        } catch (RemoteException e) {
            logger.warning("Failed");
            System.exit(0);
        }
        logger.info("OK");

        Remote stub = null;
        Controller controller = new ControllerImpl();
        try {
            stub = UnicastRemoteObject.exportObject(controller, 0);
        } catch (RemoteException e) {
            logger.warning("Export filed");
            System.exit(0);
        }

        logger.info("Binding service...");
        try {
            registry.rebind(ControllerImpl.NAME,stub);
        } catch (RemoteException e) {
            logger.warning("Failed");
            System.exit(0);
        }
        logger.info("OK");

        HibernateUtil.getSessionFactory();
        logger.info("Hibernate OK");
    }
}
