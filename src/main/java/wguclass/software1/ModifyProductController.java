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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class is the modify product menu of the application.
 * You are able to change values of a product, add/remove associated parts and save the changes.
 * @author Kevin Salazar
 */
public class ModifyProductController implements Initializable {
    /**
     * The stage used to go back to the main menu
     */
    private Stage stage;

    /**
     * The scene used to go back to the main menu
     */
    private Scene scene;

    /**
     * Initializing the index variable for the update and selection of product
     */
    int index = 0;

    /**
     * The selected product from the main menu
     */
    Product selectedProduct;

    /**
     * The button that adds associated parts to a product
     */
    @FXML
    private Button ModifyPRAddButton;

    /**
     * The button that cancels any modifications performed
     */
    @FXML
    private Button ModifyPRCancelButton;

    /**
     * The text field for the ID
     */
    @FXML
    private TextField ModifyPRIdTxtField;

    /**
     * The text field for the inventory
     */
    @FXML
    private TextField ModifyPRInvTxtField;

    /**
     * The text field for the max
     */
    @FXML
    private TextField ModifyPRMaxTxtField;

    /**
     * The text field for the min
     */
    @FXML
    private TextField ModifyPRMinTxtField;

    /**
     * The text field for the name
     */
    @FXML
    private TextField ModifyPRNameTxtField;

    /**
     * The text field for the price
     */
    @FXML
    private TextField ModifyPRPriceTxtField;

    /**
     * The button to remove an associated part
     */
    @FXML
    private Button ModifyPRRemAsPartButton;

    /**
     * The button to save changes
     */
    @FXML
    private Button ModifyPRSaveButton;

    /**
     * The text field used to search for a part
     */
    @FXML
    private TextField ModifyPRSearchByPartIDorNameTxtField;

    /**
     * Table view for the parts table
     */
    @FXML
    private TableView<Part> PartsTableMM;

    /**
     * Table column for the part inventory
     */
    @FXML
    private TableColumn<Part, Integer> MPRInvCol;

    /**
     * Table column for the part price
     */
    @FXML
    private TableColumn<Part, Double> MPRPCCol;

    /**
     * Table column for the part ID
     */
    @FXML
    private TableColumn<Part, Integer> MPRPartIDCol;

    /**
     * Table column for the part name
     */
    @FXML
    private TableColumn<Part, String> MPRPartNameCol;

    /**
     * Table for the associated parts
     */
    @FXML
    private TableView<Part> AssociatedPartsTableMPR;

    /**
     * Table column for the associated parts inventory
     */
    @FXML
    private TableColumn<Part, Integer> APMPRInvCol;

    /**
     * Table column for the associated parts price
     */
    @FXML
    private TableColumn<Part, Double> APMPRPCCol;

    /**
     * Table column for the associated parts ID
     */
    @FXML
    private TableColumn<Part, Integer> APMPRPartIDCol;

    /**
     * Table column for the associated parts name
     */
    @FXML
    private TableColumn<Part, String> APMPRPartNameCol;

    /**
     * Temporary list that will store associated parts
     */
    private ObservableList<Part> associatedPartsModify = FXCollections.observableArrayList();
    Product Mouse = new Product(5,"Mouse",10,1,2,3);

    /**
     * Add a part from the parts list into the associated part list
     * Alerts user if button was pressed with no selection
     * @param event Adding a part to the product in modification
     * @throws IOException an exception that is thrown when an I/O error occurs
     */
    @FXML
    void PressModifyPRAddButton(ActionEvent event) throws IOException {
        System.out.println("pressed add part to assoc parts");
        Part selectedPart = PartsTableMM.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Part not Found");
            alert.setContentText("Please select a part to add.");
            Optional<ButtonType> OK = alert.showAndWait();
            System.out.println("Part not found");
        } else {
            associatedPartsModify.add(selectedPart);
            AssociatedPartsTableMPR.setItems(associatedPartsModify);

            APMPRPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            APMPRPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            APMPRInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
            APMPRPCCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        }
    }

    /**
     * Removes an associated part from the product
     * Alerts user if button was pressed with no selection
     * Confirmation message to remove the associated part
     *
     * @param event Removing an associated part from the temporary list
     * @throws IOException an exception that is thrown when an I/O error occurs
     */
    @FXML
    void PressModifyPRRemAsPartButton(ActionEvent event) throws IOException {
        System.out.println("pressed add part to assoc parts");
        Part selectedPart = AssociatedPartsTableMPR.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
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
            if (result.isPresent() && result.get() == ButtonType.OK) {
                associatedPartsModify.remove(selectedPart);
                AssociatedPartsTableMPR.setItems(associatedPartsModify);
                      }
                }
    }

    /**
     * Cancels the modification process and redirects to main menu
     * Fixed a previous issue where whenever cancelled, the changes would still save.
     * This was solved by creating the if statement for the result in the confirmation alert, after this, changes are not saved after confirmation is declined.
     * @param event Cancels without any changes
     * @throws IOException an exception that is thrown when an I/O error occurs
     */
    @FXML
    void PressModifyPRCancelButton(ActionEvent event) throws IOException {
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
     * Saves changes to the product modification
     * Includes validations for product values
     * Stores part into the permanent associated parts list
     * A future enhancement would be to save changes in a database after the program closes
     * Had a runtime error from not initializing the values at the beginning of the action event, making it unable to validate correctly.
     *  @param event Saves modify product
     * @throws IOException an exception that is thrown when an I/O error occurs
     */
    @FXML
    void PressModifyPRSaveButton(ActionEvent event) throws IOException {
        int stock = 0;
        double price = 0;
        int max = 0;
        int min = 0;
        int id = 0;
        String mistakeModPr = "";
        try {
            mistakeModPr = "inv";
             id = Integer.parseInt(ModifyPRIdTxtField.getText());
            mistakeModPr = "name";
            String name = ModifyPRNameTxtField.getText();
            mistakeModPr = "stock";
             stock = Integer.parseInt(ModifyPRInvTxtField.getText());
            mistakeModPr = "price";
             price = Double.parseDouble(ModifyPRPriceTxtField.getText());
            mistakeModPr = "max";
             max = Integer.parseInt(ModifyPRMaxTxtField.getText());
            mistakeModPr = "min";
             min = Integer.parseInt(ModifyPRMinTxtField.getText());

            if (min > stock || stock > max) {
                System.out.println("Make sure that min <= inv and inv <= max ");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid min, max or inv entry.");
                alert.setContentText("Ensure that Min >= Inv, and that Inv >= Max");
                alert.showAndWait();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("entry must be a number");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid entry");
            alert.setContentText("Please enter a number for the " + mistakeModPr + ".");
            alert.showAndWait();
            return;
        }
        String name = ModifyPRNameTxtField.getText();
        if (name.isBlank()) {
            System.out.println("product name is blank");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product not named");
            alert.setContentText("Product name is blank, please name the Product");
            alert.showAndWait();
            return;
        }
         id = Integer.parseInt(ModifyPRIdTxtField.getText());
         stock = Integer.parseInt(ModifyPRInvTxtField.getText());
         price = Double.parseDouble(ModifyPRPriceTxtField.getText());
         max = Integer.parseInt(ModifyPRMaxTxtField.getText());
         min = Integer.parseInt(ModifyPRMinTxtField.getText());

        Product updatedProduct = new Product(id, name, price, stock, min, max);
            Inventory.updateProduct(index, updatedProduct);

        for(Part p: associatedPartsModify){
            updatedProduct.addAssociatedPart(p);
        }

        System.out.println(updatedProduct.getAllAssociatedParts().size());

        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Searches a part by id or name
     * Alerts user if a part was not found
     * @param event Searches part by giving input in text field
     */
    @FXML
    void MPRMPressSearchPartIDName(ActionEvent event) {
        String SPart = ModifyPRSearchByPartIDorNameTxtField.getText();
        try {
            System.out.println("SPart =" + SPart);
            int partId = Integer.parseInt(SPart);
            Part part = Inventory.lookupPart(partId);
            if (part != null) {
                System.out.println("S2Part =" + SPart);
                PartsTableMM.getSelectionModel().select(part);
                return;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Part not Found");
                alert.setContentText("Part not found.");
                Optional<ButtonType> OK = alert.showAndWait();
                System.out.println("Part not found");
            }
        } catch (NumberFormatException e) {
            ObservableList<Part> PartSearched = Inventory.lookupPart(SPart);
            if (PartSearched.size() != 0)
                PartsTableMM.setItems(PartSearched);
            else {
                PartsTableMM.setItems(Inventory.getAllParts());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Part not Found");
                alert.setContentText("Part not found.");
                Optional<ButtonType> OK = alert.showAndWait();
                System.out.println("Part not found");
            }
        }
    }

    /**
     * Receive product data from the main screen.
     *
     * Populating all values of the selected product from the main menu.
     * Fixed a NullPointerException error from not adding the correct fx ids from the associated parts table, this was solved by correcting the fxml document, and importing the correct table view objects.
     * Another fix was setting the items prior setting the CellValueFactory.
     * @param product The selected product.
     */
    public void receiveProductsSetData(Product product) {
        selectedProduct = product;
        index = Inventory.getAllProducts().indexOf(product);

        System.out.println(product.getAllAssociatedParts().size()+ "*");

        for(Part p: product.getAllAssociatedParts()) {
            associatedPartsModify.add(p);
        }
        ModifyPRIdTxtField.setText(String.valueOf(selectedProduct.getId()));
        ModifyPRNameTxtField.setText(selectedProduct.getName());
        ModifyPRInvTxtField.setText(String.valueOf(selectedProduct.getStock()));
        ModifyPRPriceTxtField.setText(String.valueOf(selectedProduct.getPrice()));
        ModifyPRMaxTxtField.setText(String.valueOf(selectedProduct.getMax()));
        ModifyPRMinTxtField.setText(String.valueOf(selectedProduct.getMin()));

        AssociatedPartsTableMPR.setItems(associatedPartsModify);
        APMPRPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        APMPRPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        APMPRInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        APMPRPCCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * Intializes the modify product menu.
     *
     * Populates the data in the parts and associated parts tables.
     * Fixed a previous runtime error from initializing a null associated parts table view and its attributes in here.
     * Solved by initializing it in the send data method for the products above, and populating the required fx ids.
     *
     * @param url  Locates the relative paths for the root object, or null if not found.
     * @param resourceBundle Resources used to localize the root object, or null if not found.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PartsTableMM.setItems(Inventory.getAllParts());
        MPRPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        MPRPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        MPRInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        MPRPCCol.setCellValueFactory(new PropertyValueFactory<>("price"));
       }
}

