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
 * This class is the Modify product menu of the application.
 *
 * The user is able to modify the product
 * Also able to add/remove associated parts to the  product
 *
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

    //TODO ADD ASSOC PART BUTTON

    /**
     * Add a part from the parts list into the associated part list
     *
     * Alerts user if button was pressed with no selection
     *
     * @param event Adding a part to the product in modification
     * @throws IOException
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


    //TODO ASK CI ABOUT HOW TO DEAL WITH PARTS AND ASSOCIATED PARTS FOR THE PRODUCT MENUS
    //TODO INCLUDING REMOVE BUTTON VALIDATION FOR THE PRODUCT
    //TODO REMOVE ASSOC PART BUTTON

    /**
     * Removes an associated part from the product
     *
     * Alerts user if button was pressed with no selection
     *
     * Confirmation message to remove the associated part
     *
     * @param event Removing an associated part from the temporary list
     * @throws IOException
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
//remove method
                associatedPartsModify.remove(selectedPart);
                AssociatedPartsTableMPR.setItems(associatedPartsModify);
//            AssociatedPartsTableAPR.setItems(associatedParts);
                  }
                }
    }

    /**
     * Cancels the modification process and redirects to main menu
     *
     * @param event Cancels without any changes
     * @throws IOException
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
     *
     * Includes validations for product values
     *
     * Stores part into the permanent associated parts list
     *
     * A future enhancement would be to save changes in a database after the program closes
     *
     * @param event Saves modify product
     * @throws IOException
     */
    @FXML
    void PressModifyPRSaveButton(ActionEvent event) throws IOException {
//save changes from the input on the textfields
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
            //grabbing parts from temp list and assign to permanent one
        //temp is associatedPartsModify and perm is getAll
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
     *
     * Alerts user if a part was not found
     *
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
                //POP UP AN ALERT, PRODUCT NOT FOUND
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
     * Receive data method for the product
     *
     * It receives and populates all values from the selected product
     *
     * @param product Brings selected product data from the modify product menu
     */
    public void receiveProductsSetData(Product product) {
        selectedProduct = product;

        index = Inventory.getAllProducts().indexOf(product);

        System.out.println(product.getAllAssociatedParts().size()+ "*");

        //places old parts in the new list and will allow to add more
        for(Part p: product.getAllAssociatedParts()) {
            associatedPartsModify.add(p);
        }
        //String.valueOf retrieved the id of the p1 and converted that int into string to assign to the label
//        index = Inventory.getAllProducts().indexOf(product);
        ModifyPRIdTxtField.setText(String.valueOf(selectedProduct.getId()));
        ModifyPRNameTxtField.setText(selectedProduct.getName());
        ModifyPRInvTxtField.setText(String.valueOf(selectedProduct.getStock()));
        ModifyPRPriceTxtField.setText(String.valueOf(selectedProduct.getPrice()));
        ModifyPRMaxTxtField.setText(String.valueOf(selectedProduct.getMax()));
        ModifyPRMinTxtField.setText(String.valueOf(selectedProduct.getMin()));

        AssociatedPartsTableMPR.setItems(associatedPartsModify);
        //added cols to populate table
        APMPRPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        APMPRPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        APMPRInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        APMPRPCCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * Intializes the Modify product menu
     *
     * Populates the data in the parts and associated parts tables
     * @param url  Locates the relative paths for the root object, or null if not found
     * @param resourceBundle Resources used to localize the root object, or null if not found
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        ModifyPRIdTxtField.setText(String.valueOf(Inventory.productId));

        PartsTableMM.setItems(Inventory.getAllParts());
        MPRPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        MPRPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        MPRInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        MPRPCCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //initiaizing Associated part table
        //making a temporary list and then when ready you copy it over to the permanent
//        APMPRPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
//        APMPRPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
//        APMPRInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
//        APMPRPCCol.setCellValueFactory(new PropertyValueFactory<>("price"));
//
//
//        System.out.println("associated part table has been intialized");
//
       }
}

