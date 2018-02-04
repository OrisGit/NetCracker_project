import controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.View;
import view.fx.ViewFX;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

public class StartClient extends Application {

    public static Logger logger = Logger.getLogger("Client");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/main_window.fxml"));
        Parent root;
        try {
            root = loader.load();
            primaryStage.setScene(new Scene(root));
            ViewFX.setStage(primaryStage);
            primaryStage.setOnCloseRequest(event -> {
                Platform.exit();
                System.exit(0);
            });
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        View view = loader.getController();
        try{
            Registry registry = LocateRegistry.getRegistry("localhost", 2099);
            Controller controller = null;
            try {
                controller = (Controller)registry.lookup("server/controller");
            } catch (NotBoundException e) {
                logger.warning("Lookup failed");
            }
            logger.info("Lookup Ok");

            Remote stub = null;
            try {
                stub = UnicastRemoteObject.exportObject(view, 0);
            } catch (RemoteException e) {
                logger.warning("Export filed");
                System.exit(0);
            }
            view.setStub(stub);
            controller.registerClient((View)stub);

        } catch (Exception e){
            view.displayError("Невозможно соединиться с сервером.\nПриложение будет закрыто.");
            primaryStage.close();
        }
    }
}
