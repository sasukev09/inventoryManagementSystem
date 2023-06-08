package wguclass.software1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends  Application {
    @Override
    public void start(Stage primarystage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        primarystage.setScene(new Scene(root, 750,420));
        primarystage.show();
    }

    public static void main(String[] args) throws IOException {

        launch();
    }
}