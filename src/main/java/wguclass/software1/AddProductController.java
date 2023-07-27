package wguclass.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class is the add product menu of the application.
 *
 * You are able to add a product, add/remove associated parts and save the changes.
 *
 * @author Kevin Salazar
 */
public class AddProductController implements Initializable {

    /**
     * The stage that brings you back to the Main Menu
     */
    private Stage stage;

    /**
     * The stage that brings you back to the Main Menu
     */
    private Scene scene;

    /**
     * The button to finish adding the product
     */
    @FXML
    private Button AddPRMAddButton;


    /**
     * The button to cancel the add product action
     */
    @FXML
    private Button AddPRMCancelButton;


    /**
     * The button to remove a part
     */
    @FXML
    private Button AddPRMRemoveAPartButton;


    /**
     * The button to save the created product
     */
    @FXML
    private Button AddPRMSaveButton;





    /**
     * The table view that contains all Parts
     */
    @FXML
    private TableView<Part> PartsTableMM;

    /**
     * The table column for the part inventory
     */
    @FXML
    private TableColumn APRInvCol;

    /**
     * The table column for the part price
     */
    @FXML
    private TableColumn APRPCCol;

    /**
     * The table column for the part ID
     */
    @FXML
    private TableColumn APRPartIDCol;

    /**
     * The table column for the part name
     */
    @FXML
    private TableColumn APRPartNameCol;

    /**
     * The table view for the parts table
     */
    @FXML
    private TableView <Part> AssociatedPartsTableAPR;

    /**
     * The table column for the part inventory
     */
    @FXML
    private TableColumn <Part, Integer> APAPRInvCol;

    /**
     * The table column for the part price
     */
    @FXML
    private TableColumn <Part, Double> APAPRPCCol;

    /**
     * The table column for the part id
     */
    @FXML
    private TableColumn <Part, Integer> APAPRPartIDCol;

    /**
     * The table column for the part name
     */
    @FXML
    private TableColumn<Part, String> APAPRPartNameCol;



    /**
     * The text field for the id
     */
    @FXML
    private TextField IDTextField;

    /**
     * The text field for the inventory
     */
    @FXML
    private TextField InvTextField;

    /**
     * The text field for the max
     */
    @FXML
    private TextField MaxTextField;

    /**
     * The text field for the min
     */
    @FXML
    private TextField MinTextField;

    /**
     * The text field for the name
     */
    @FXML
    private TextField NameTextField;

    /**
     * The text field for the price
     */
    @FXML
    private TextField PriceTextField;

    /**
     * The text field to search a part
     */
    @FXML
    private TextField SearchbyPartOrIDAPRM;

    /**
     * The list of associated Parts
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    //to overwrite test data
    Product Mouse = new Product(5,"Mouse",10,1,2,3);

    //TODO ASK CI ABOUT HOW TO DEAL WITH PARTS AND ASSOCIATED PARTS FOR THE PRODUCT MENUS
    //TODO INCLUDING REMOVE BUTTON VALIDATION FOR THE PRODUCT MENUS

    /**
     * Adds a new associated part to the product
     * @param event By pressing the button a new associated part is added
     * @throws IOException
     */
    @FXML
    void PressAddPRMAddButton(ActionEvent event) throws IOException {
        System.out.println("pressed add part to assoc parts");
        Part selectedPart = PartsTableMM.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Part not Found");
            alert.setContentText("Please select a part to add.");
            Optional<ButtonType> OK = alert.showAndWait();
            System.out.println("Part not found");
        } else {
            Mouse.addAssociatedPart(selectedPart);
           AssociatedPartsTableAPR.setItems(Mouse.getAllAssociatedParts());
        }


    }
    //TODO REMOVE PART FROM ASSOCP
    //todo figure out a way to set items only when saved, so do not set anything unless you save

    /**
     * Removes an associated part from the product
     * @param event After pressing the button, an associated part is removed
     * @throws IOException
     */
    @FXML
    void PressAddPRMRemoveAPartButton(ActionEvent event) throws IOException {
        {

            Part selectedPart = AssociatedPartsTableAPR.getSelectionModel().getSelectedItem();

            if (selectedPart == null) {
               //todo alert please select a part
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Part not Found");
                alert.setContentText("Please select a part to remove.");
                Optional<ButtonType> OK = alert.showAndWait();
                System.out.println("Part not found");
            } else {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Do you want to remove the selected part?");
                Optional<ButtonType> result = alert.showAndWait();
                //todo ???? With mouse selected part???
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    associatedParts.remove(selectedPart);
                    AssociatedPartsTableAPR.setItems(associatedParts);
                }
            }
        }
    }

    /**
     * Cancels the action of adding a product and returns to main menu
     * @param event Pressing button cancels action
     * @throws IOException
     */
    @FXML
    void PressAddPRMCancelButton(ActionEvent event)  throws IOException {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want cancel changes and return to the main screen?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
    }

    /**
     * Searching a part inside the product menu
     * @param event Search text field to input a name or id of the part of choice
     */
    @FXML
    void APRMPressSearchPartIDName(ActionEvent event) {
        String SPart = SearchbyPartOrIDAPRM.getText();
        try {
            System.out.println("SPart =" + SPart);
            int partId = Integer.parseInt(SPart);
            Part part = Inventory.lookupPart(partId);
            if (part != null) {
                PartsTableMM.getSelectionModel().select(part);
                return;
            }
            else {
                //POP UP AN ALERT, PRODUCT NOT FOUND
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Part not Found");
                alert.setContentText("Part not found.");
                Optional<ButtonType> OK = alert.showAndWait();
                System.out.println("Part not found");
            }
        } catch (NumberFormatException e) {
            ObservableList<Part> PartSearched = Inventory.lookupPart(SPart);
            if(PartSearched.size() != 0)
                PartsTableMM.setItems(PartSearched);
            else {
                PartsTableMM.setItems(Inventory.getAllParts());
                //ALERT PRODUCT NOT FOUND
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Part not Found");
                alert.setContentText("Part not found.");
                Optional<ButtonType> OK = alert.showAndWait();
                System.out.println("Part not found");
            }
        }
    }

    /**
     * Saves the product and associated part
     * @param event Pressing the save button saves adding a product and its associated parts
     * @throws IOException
     */
    @FXML
    void PressAddPRMSaveButton(ActionEvent event) throws IOException {
        String name = NameTextField.getText();
        int inv = 0 ;
        double price = 0;
        int max = 0;
        int min = 0;
        int productId = 0 ;
        String mistakeAddPr = "";
       try {
           mistakeAddPr = "inv";
           inv = Integer.parseInt(InvTextField.getText());
           mistakeAddPr = "price";
           price = Double.parseDouble(PriceTextField.getText());
           mistakeAddPr = "max";
           max = Integer.parseInt(MaxTextField.getText());
           mistakeAddPr = "min";
           min = Integer.parseInt(MinTextField.getText());
           mistakeAddPr = "product id";
           productId = Inventory.generateProductId();

           if (min > inv || inv > max) {
               System.out.println("Make sure that min <= inv and inv <= max ");
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText("Invalid min, max or inv entry.");
               alert.setContentText("Ensure that Min >= Inv, and that Inv >= Max");
               alert.showAndWait();
               return;
           }
       }
       catch (NumberFormatException e) {
           System.out.println("entry must be a number");
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Invalid entry");
           alert.setContentText("Please enter a number for the " + mistakeAddPr + ".");
           alert.showAndWait();
           return;
       }

        if(name.isBlank()) {
            System.out.println("product name is blank");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product not named");
            alert.setContentText("Product name is blank, please name the Product");
            alert.showAndWait();
            return;
        }
        Inventory.addProduct(Mouse);
//        AssociatedPartsTableAPR.setItems(associatedParts);

        System.out.println("Product has been added, returning to main menu.");
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //fix delete button, Mouse.deleteAssociated
    }

    /**
     * Initializing and populating the table views inside the add product menu
     * @param url  Locates the relative paths for the root object, or null if not found
     * @param resourceBundle Resources used to localize the root object, or null if not found
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IDTextField.setText(String.valueOf(Inventory.productId));
        //initializing part table
        APRPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        APRPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        APRInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        APRPCCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        PartsTableMM.setItems(Inventory.getAllParts());

        //initiaizing Associated part table
        APAPRPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        APAPRPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        APAPRInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        APAPRPCCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        System.out.println("associated part table has been intialized");
    }
}
