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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {
    public Text APMLabelTxtMachID;
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
    //if button is selected, then an inhouse part is created
    @FXML
    private RadioButton APMOutSourcedRadioButton;
    //if button is selected, then an inhouse part is created
    @FXML
    private Button APMSaveButton;
    //after button is selected all changes get saved
    @FXML
    private ToggleGroup APMToggleGroup;

    @FXML
    private TextField IDTextField;
    //auto generated
    @FXML
    private TextField InventoryTextFIeld;
    //
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
        System.out.println("[AddPartMenu::APMOutSourcedRadioButton] Outsourced part radio button selected, updating label");
        APMLabelTxtMachID.setText("Company Name");
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
        System.out.println("[AddModifyPartMenu::APMInHouseRadioButton] In-House part radio button selected, updating label");
        APMLabelTxtMachID.setText("Machine ID");
    }

    @FXML
    void PressAPMSaveButton(ActionEvent event) throws IOException {
        //save changes from the input on the textfields
        NameTextField.getText();
        InventoryTextFIeld.getText();


       /* Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
    }

    }


