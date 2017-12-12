package Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/pharmacy_catalogue.fxml"));
        Parent root = loader.load();
        PharmacyCatalogueController controller = loader.getController();
        primaryStage.setScene(new Scene(root));
        controller.update();
        primaryStage.show();

    }
}
