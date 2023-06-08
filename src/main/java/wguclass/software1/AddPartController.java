package wguclass.software1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private Button APMCancelButton;

    @FXML
    private RadioButton APMInHouseRadioButton;

    @FXML
    private RadioButton APMOutSourcedRadioButton;

    @FXML
    private Button APMSaveButton;

    @FXML
    private ToggleGroup APMToggleGroup;

    @FXML
    private TextField IDTextField;

    @FXML
    private TextField InventoryTextFIeld;

    @FXML
    private TextField MachIDTxtField;

    @FXML
    private TextField MaxTxtField;

    @FXML
    private TextField MinTxtField;

    @FXML
    private TextField NameTextField;

    @FXML
    private TextField PriceCostTxtField;

    @FXML
    void APMOutSourcedRadioButton(ActionEvent event) {

    }

    @FXML
    void PressAPMCancelButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void PressAPMInHouseRadioButton(ActionEvent event) {

    }

    @FXML
    void PressAPMSaveButton(ActionEvent event) {

    }

}
