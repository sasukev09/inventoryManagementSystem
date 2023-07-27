package wguclass.software1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This Main class creates an application for the program.
 *
 * The main menu is initialized here.
 *
 * @author Kevin Salazar
 */

public class Main extends  Application {
    /**
     * The start method creates the FXML stage and loads the initial scene.
     *
     * @param  primarystage The primary stage of the program
     * @throws IOException an exception that is thrown when an I/O error occurs
     */
    @Override
    public void start(Stage primarystage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        primarystage.setScene(new Scene(root, 750,420));
        primarystage.show();
    }


    /**
     * The main method is the initial access of the application.
     *
     * The main method launches the application.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        launch();
    }
}