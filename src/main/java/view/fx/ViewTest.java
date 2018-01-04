package view.fx;

import javafx.application.Application;
import controller.Controller;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewTest extends Application {

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
        Controller controller = new Controller(loader.getController());
        controller.run();
    }
}
