package view.fx;

import controller.Controller;
import javafx.application.Application;
import controller.ControllerImpl;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import view.View;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Optional;
import java.util.logging.Logger;

public class ViewTest extends Application {

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
            View view = loader.getController();
            try {
                stub = UnicastRemoteObject.exportObject(view, 0);
            } catch (RemoteException e) {
                logger.warning("Export filed");
                System.exit(0);
            }
            view.setStub(stub);

            controller.registerClient((View)stub);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Не возможно соедениться с сервером. Приложение будет закрыто...");
            alert.showAndWait();
            Platform.exit();
            System.exit(0);
        }
    }
}
