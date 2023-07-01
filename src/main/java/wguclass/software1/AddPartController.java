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
        String name = NameTextField.getText();
        //parseint/parsedouble converts data type from string, string goes inside parenthesis
        int inv = Integer.parseInt(InventoryTextFIeld.getText());
        double price = Double.parseDouble(PriceCostTxtField.getText());
        int max = Integer.parseInt(MaxTxtField.getText());
        int min = Integer.parseInt(MinTxtField.getText());
        int id = Inventory.generatePartId();
    //saved in variables ^

       if (APMInHouseRadioButton.isSelected()) {
           System.out.println("INHOUSE SELECTED");
           int machId = Integer.parseInt(MachIDTxtField.getText());
           Inventory.addPart(new InHouse(id, name,price,inv,min,max,machId));
       }
       else {
           System.out.println("OUTSOURCED SELECTED");
           String companyName = MachIDTxtField.getText();
           //no need to convert because machid is already a String
           Inventory.addPart(new Outsourced(id, name,price,inv,min,max,companyName));
       }
//to comment multiple lines ctrl and "/"
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //REMEMBER TO PUT TEXT ALERTS AND VALIDATIONS BEFORE THE IF STATEMENTS, PRIOR CREATING OBJECT
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        IDTextField.setText(String.valueOf(Inventory.partId));
    }

    }


