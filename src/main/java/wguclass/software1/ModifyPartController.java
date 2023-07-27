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
 * This class is the Main Menu of the application.
 *
 * Displaying parts and products, and the buttons to interact with the application and manage the inventory.
 *
 * @author Kevin Salazar
 */
public class ModifyPartController implements Initializable {
    /**
     * Text label for the machine id
     */
    public Text ModPMLabelTxtMachID;

    /**
     * The stage used to return to the main menu
     */
    private Stage stage;

    /**
     * The scene used to return to the main menu
     */
    private Scene scene;

    /**
     * Assigning an initial value for the index
     */
    int index = 0;

    /**
     * Text field for modify
     */
    @FXML
    private TextField ModPIDTxtField;

    /**
     * Radio button for the In-House part selection
     */
    @FXML
    private RadioButton ModPInHouseRadio;

    /**
     * Text field for the name
     */
    @FXML
    private TextField ModPNameTxtField;

    /**
     * Radio button for the Outsourced part selection
     */

    @FXML
    private RadioButton ModPOutRadio;

    /**
     * Cancel button to cancel the modify action
     */
    @FXML
    private Button ModPartCancelButton;

    /**
     * Text field for the inventory
     */
    @FXML
    private TextField ModPartInvTextField;

    /**
     * Text field for the machine ID
     */
    @FXML
    private TextField ModPartMachineIDTxtField;

    /**
     * Text field for the max
     */
    @FXML
    private TextField ModPartMaxTxtField;

    /**
     * Text field for the min
     */
    @FXML
    private TextField ModPartMinTxtField;

    /**
     * Text field for the price
     */
    @FXML
    private TextField ModPartPCTxtField;

    /**
     * Button for the save action
     */
    @FXML
    private Button ModPartSaveButton;

    /**
     * Toggle group for the radio buttons
     */
    @FXML
    private ToggleGroup ModifyPartMenu;


    /**
     * When the In-house radio button is selected, the text label changes to Machine ID
     * @param event Text label text changes to Machine ID
     */
    @FXML
    void PressModPInHouseRadio(ActionEvent event)  {
        System.out.println("[ModifyPartMenu::ModPMLabelTxtMachID] In-house part radio button selected, updating label");
        ModPMLabelTxtMachID.setText("Machine ID");
    }

    /**
     * When the Outsourced radio button is selected, the text label changes to Company Name
     * @param event Text label text changes to Company Name
     */
    @FXML
    void PressModPOutRadio(ActionEvent event) {
        System.out.println("[ModifyPartMenu::ModPMLabelTxtMachID] Outsourced part radio button selected, updating label");
        ModPMLabelTxtMachID.setText("Company Name");

    }

    /**
     * Cancel button action redirects to main menu
     * @param event The change of menu after cancelling
     * @throws IOException
     */
    @FXML
    void PressModPartCancelButton(ActionEvent event)  throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
//standard name convention for java is lower case at the beginning, google.
    //packages lowercase
    //variables and methods lower case
    //constants (for example "PI") would all be CAPITAL and "_" to separate words

    /**
     * Validates all values and saves changes for the modified part
     * Redirects to main menu once everything is saved
     * @param event Pressing the button saves the changes made to the part
     * @throws IOException
     */
    @FXML
    void PressModPartSaveButton(ActionEvent event) throws IOException {
        String name = ModPNameTxtField.getText();

        int inv = 0;
        double price = 0;
        int max = 0;
        int min = 0;
        int id = 0;
        String mistakeModPart = "";
        //todo VALIDATIONS FROM TASK, ONCE VALIDATED
        try {
            mistakeModPart = "inv";
            inv = Integer.parseInt(ModPartInvTextField.getText());
            mistakeModPart = "price";
            price = Double.parseDouble(ModPartPCTxtField.getText());
            mistakeModPart = "max";
            max = Integer.parseInt(ModPartMaxTxtField.getText());
            mistakeModPart = "min";
            min = Integer.parseInt(ModPartMinTxtField.getText());
            mistakeModPart = "id";
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
        } catch (NumberFormatException e ){
            System.out.println("entry must be a number");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid entry");
            alert.setContentText("Please enter a number for the " + mistakeModPart + ".");
            alert.showAndWait();
            return;
        }

        if(name.isBlank()) {
            System.out.println("mod part name is blank");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Part not renamed");
            alert.setContentText("Part name is blank, please rename the Part");
            alert.showAndWait();
            return;
        }

        System.out.println("index =" + index);

            if (ModPInHouseRadio.isSelected()) {

                System.out.println("INHOUSE SELECTED");
                int machId = 0;
                try {
                    machId = Integer.parseInt(ModPartMachineIDTxtField.getText());
                }
                catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid entry");
                    alert.setContentText("Please input the machine id for the part.");
                    alert.showAndWait();
                    return;
                }

                Part pInhouse = new InHouse(id, name, price, inv, min, max, machId);
                Inventory.updatePart(index, pInhouse);

            } else {
                System.out.println("OUTSOURCED SELECTED");
                String companyName = ModPartMachineIDTxtField.getText();
                if(companyName.isBlank()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid entry");
                    alert.setContentText("Please input the company name for the part.");
                    alert.showAndWait();
                    return;
                }
                Part pOutsource = new Outsourced(id, name, price, inv, min, max, companyName);
                Inventory.updatePart(index, pOutsource);
            }
            Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    /**
     * The send method that brings the part data that originates from the main menu
     * @param part The part that is selected in the main menu for modification
     */
    public void receivePartsSetData (Part part) {
        index = Inventory.getAllParts().indexOf(part);
        //String.valueOf retrieved the id of the p1 and converted that int into string to assign to the label
        if (part instanceof InHouse) {
            ModPInHouseRadio.setSelected(true);
            ModPMLabelTxtMachID.setText("Machine ID");
            ModPIDTxtField.setText(String.valueOf(part.getId()));
            ModPNameTxtField.setText(part.getName());
            ModPartInvTextField.setText(String.valueOf(part.getStock()));
            ModPartPCTxtField.setText(String.valueOf(part.getPrice()));
            ModPartMaxTxtField.setText(String.valueOf(part.getMax()));
            ModPartMinTxtField.setText((String.valueOf(part.getMin())));
            ModPartMachineIDTxtField.setText(String.valueOf(((InHouse)part).getMachineId()));

        }

        if (part instanceof Outsourced) {
            ModPOutRadio.setSelected(true);
            ModPMLabelTxtMachID.setText("Company Name");
            ModPIDTxtField.setText(String.valueOf(part.getId()));
            ModPNameTxtField.setText(part.getName());
            ModPartInvTextField.setText(String.valueOf(part.getStock()));
            ModPartPCTxtField.setText(String.valueOf(part.getPrice()));
            ModPartMaxTxtField.setText(String.valueOf(part.getMax()));
            ModPartMinTxtField.setText((String.valueOf(part.getMin())));
            //took the part and casted, sorrounded by parenthesis
            //casted from regular part into outsourced
            ModPartMachineIDTxtField.setText(((Outsourced)part).getCompanyName());
        }
    }

    /**
     * Initializing the modify part menu
     * @param url  Locates the relative paths for the root object, or null if not found
     * @param resourceBundle Resources used to localize the root object, or null if not found
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


}
