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
/**
 * This class is the add part menu controller of the application.
 *
 *
 * Allows the user to add a part and save the changes in the program.
 *
 * @author Kevin Salazar
 */


public class AddPartController implements Initializable {

    /**
     * The machine ID/Company name text label for the part in creation.
     */
    public Text APMLabelTxtMachID;

    /**
     * The stage that brings you back to the Main Menu
     */
    private Stage stage;

    /**
     * The scene that brings you back to the Main Menu
     */
    private Scene scene;

    /**
     * The parent of the scene graph
     */
    private Parent root;

    /**
     * The button to cancel the add part action
     */
    @FXML
    private Button APMCancelButton;

    /**
     * The button to create an In-House type of part
     */
    @FXML
    private RadioButton APMInHouseRadioButton;

      /**
     * The button to create an Outsourced type of part
     */
    @FXML
    private RadioButton APMOutSourcedRadioButton;

    /**
     * The button to create the part and save changes
     */
    @FXML
    private Button APMSaveButton;

    /**
     * The toggle group for the radio buttons
     */
    @FXML
    private ToggleGroup APMToggleGroup;

    /**
     * The ID text field for the part in creation, auto generated.
     */
    @FXML
    private TextField IDTextField;

    /**
     * The Inventory text field for the part in creation.
     */
    @FXML
    private TextField InventoryTextFIeld;

    /**
     * The MachineId label for the In-house part in creation.
     */
    @FXML
    private TextField MachIDTxtField;

    /**
     * The Max text field for the part in creation.
     */
    @FXML
    private TextField MaxTxtField;

    /**
     * The Min text field for the part in creation.
     */
    @FXML
    private TextField MinTxtField;

    /**
     * The Name text field for the part in creation.
     */
    @FXML
    private TextField NameTextField;

    /**
     * The Price text field for the part in creation.
     */
    @FXML
    private TextField PriceCostTxtField;

    /**
     * Sets machine ID/company name label to "Company Name"
     *
     * @param event Outsourced radio button action.
     */
    @FXML
    void APMOutSourcedRadioButton(ActionEvent event) {
        System.out.println("[AddPartMenu::APMOutSourcedRadioButton] Outsourced part radio button selected, updating label");
        APMLabelTxtMachID.setText("Company Name");
    }

    /**
     * Cancels the creation of the part.
     *
     * Had a runtime error from not selecting the right path file to the Main Menu fxml.
     * Solved by correcting the path file in "getResource()".
     *
     * @param event Cancel button that returns to main menu.
     * @throws IOException  an exception that is thrown when an I/O error occurs
     */
    @FXML
    void PressAPMCancelButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets machine ID/company name label to "Machine ID".
     *
     * @param event In-house radio button action.
     */
    @FXML
    void PressAPMInHouseRadioButton(ActionEvent event) {
        System.out.println("[AddModifyPartMenu::APMInHouseRadioButton] In-House part radio button selected, updating label");
        APMLabelTxtMachID.setText("Machine ID");
    }

    /**
     * Adds new part to inventory and loads the Main Menu.
     *
     * All text fields are validated with error messages displayed to prevent empty and
     * invalid values.
     *
     * A future enhancement would be to save changes into a database after the program is terminated
     *
     * @param event Save button action.
     * @throws IOException From the FXMLLoader.
     */
    @FXML
    void PressAPMSaveButton(ActionEvent event) throws IOException {
        String name = NameTextField.getText();

        double price = 0;
        int max = 0;
        int inv = 0;
        int min = 0;
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
            System.out.println("entry must be a number");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid entry");
            alert.setContentText("Please enter a number for the " + mistake + ".");
            alert.showAndWait();
            return;
        }
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
            Inventory.addPart(new Outsourced(id, name, price, inv, min, max, companyName));
        }
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Initializes the controller and sets the automatic generation of the ID
     * @param url  Locates the relative paths for the root object, or null if not found
     * @param resourceBundle Resources used to localize the root object, or null if not found
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IDTextField.setText(String.valueOf(Inventory.partId));
     }
    }


