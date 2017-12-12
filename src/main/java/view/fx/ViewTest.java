package view.fx;

import javafx.application.Application;
import controller.Controller;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Controller controller = new Controller();
        controller.run();
    }
}
