package wguclass.software1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private TableColumn<?, ?> InvCol;

    @FXML
    private TableColumn<?, ?> InvProCol;

    @FXML
    private Button OnAddPMM;

    @FXML
    private Button OnAddPRMM;

    @FXML
    private Button OnDeletePMM;

    @FXML
    private Button OnDeletePRMM;

    @FXML
    private Button OnExit;

    @FXML
    private Button OnModifyPMM;

    @FXML
    private Button OnModifyPRMM;

    @FXML
    private TableColumn<?, ?> PCCol;

    @FXML
    private TableColumn<?, ?> PartIDCol;

    @FXML
    private TableColumn<?, ?> PartNameCol;

    @FXML
    private TableView<?> PartsTableMM;

    @FXML
    private TableColumn<?, ?> ProIDCol;

    @FXML
    private TableColumn<?, ?> ProNameCol;

    @FXML
    private TableColumn<?, ?> ProPCCol;

    @FXML
    private TableView<?> ProductsTableMM;

    @FXML
    private TextField SearchbyPartOrIDMM;

    @FXML
    private TextField SearchbyProductOrIDMM;

    //PRESSING THIS BUTTON DIRECTS YOU TO THE ADD PART MENU
    @FXML
    void PressAddPMM(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/AddPartMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void PressAddPRMM(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/AddProductMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void PressDeletePMM(ActionEvent event) {

    }

    @FXML
    void PressDeletePRMM(ActionEvent event) {

    }

    @FXML
    void PressExitMM(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.close();

    }

    @FXML
    void PressModifyPMM(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/ModifyPartMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    void PressModifyPRMM(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/ModifyProductMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


}

