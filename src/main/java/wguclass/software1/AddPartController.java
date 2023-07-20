package wguclass.software1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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

        double price = 0;
        int max = 0; //          min <= inv , inv <= max
        int inv = 0; // LESS OR EQUAL TO max
        int min = 0; // LESS OR EQUAL TO inv
        int id = 0;
        String mistake = "";

        try {
            mistake = "inventory";
            inv = Integer.parseInt(InventoryTextFIeld.getText());
            mistake = "price";
            price = Double.parseDouble(PriceCostTxtField.getText());
            mistake = "max";
            max = Integer.parseInt(MaxTxtField.getText());
            mistake = "min";
            min = Integer.parseInt(MinTxtField.getText());
            mistake = "id";
            id = Inventory.generatePartId();

            if(min > inv || inv > max){
                System.out.println("Make sure that min <= inv and inv <= max ");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid min, max or inv entry.");
                alert.setContentText("Ensure that Min >= Inv, and that Inv >= Max");
                alert.showAndWait();
                return;
            }


        }
        catch(NumberFormatException e) {
          //todo set inv must be a number
            System.out.println("entry must be a number");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid entry");
            alert.setContentText("Please enter a number for the " + mistake + ".");
            alert.showAndWait();
            return;
        }

        //todo insert validations, basically queries CHECK TASK FOR PROJECT
        //todo TEST IT THROUGHLY
        if (name.isBlank()) {
            System.out.println("part name is blank");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid entry");
            alert.setContentText("Part name is blank, please name the Part");
            alert.showAndWait();
            return;
        }


        if (APMInHouseRadioButton.isSelected()) {
            System.out.println("INHOUSE SELECTED");
            int machId = 0;
            try {
                machId = Integer.parseInt(MachIDTxtField.getText());
            }
            catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid entry");
                alert.setContentText("Please input the machine id for the part");
                alert.showAndWait();
                return;
            }
            Inventory.addPart(new InHouse(id, name, price, inv, min, max, machId));
        } else {
            System.out.println("OUTSOURCED SELECTED");
            String companyName = MachIDTxtField.getText();
            if (companyName.isBlank()) {
                System.out.println("Company name is blank");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid entry");
                alert.setContentText("Please input the company name for the part.");
                alert.showAndWait();
                return;
            }
            //no need to convert because machid is already a String
            Inventory.addPart(new Outsourced(id, name, price, inv, min, max, companyName));
        }

//to comment multiple lines ctrl and "/"
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    //REMEMBER TO PUT TEXT ALERTS AND VALIDATIONS BEFORE THE IF STATEMENTS, PRIOR CREATING OBJECT


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        IDTextField.setText(String.valueOf(Inventory.partId));
    }

    }


