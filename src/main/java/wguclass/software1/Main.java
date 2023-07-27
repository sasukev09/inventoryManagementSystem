package wguclass.software1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This Inventory Management program uses an application to manage
 * an inventory of parts and products consisting of parts.
 */

/**
 * @author Kevin Salazar
 */

public class Main extends  Application {
    /**
     * The start method creates the FXML stage and loads the initial scene.
     *
     * @param  primarystage The primary stage
     * @throws Exception
     */
    @Override
    public void start(Stage primarystage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        primarystage.setScene(new Scene(root, 750,420));
        primarystage.show();
    }

    public static void main(String[] args) throws IOException {
        /**
         * The main method is the initial access of the application.
         *
         * The main method launches the application.
         *
         * @param args
         */
        launch();
    }
}