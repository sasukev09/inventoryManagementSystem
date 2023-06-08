package wguclass.software1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button AddPRMAddButton;

    @FXML
    private Button AddPRMCancelButton;

    @FXML
    private Button AddPRMRemoveAPartButton;

    @FXML
    private Button AddPRMSaveButton;

    @FXML
    private TableView<?> AssociatedPartsTable;

    @FXML
    private TextField IDTextField;

    @FXML
    private TextField InvTextField;

    @FXML
    private TextField MaxTextField;

    @FXML
    private TextField MinTextField;

    @FXML
    private TextField NameTextField;

    @FXML
    private TableView<?> PartsTable;

    @FXML
    private TextField PriceTextField;

    @FXML
    private TextField SearchbyPartOrID;

    @FXML
    void PressAddPRMAddButton(ActionEvent event) {

    }

    @FXML
    void PressAddPRMCancelButton(ActionEvent event)  throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void PressAddPRMRemoveAPartButton(ActionEvent event) {

    }

    @FXML
    void PressAddPRMSaveButton(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
