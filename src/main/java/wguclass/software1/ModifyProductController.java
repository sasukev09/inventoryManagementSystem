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

public class ModifyProductController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    int index = 0;
    Product selectedProduct;

    @FXML
    private Button ModifyPRAddButton;

    @FXML
    private Button ModifyPRCancelButton;

    @FXML
    private TextField ModifyPRIdTxtField;

    @FXML
    private TextField ModifyPRInvTxtField;

    @FXML
    private TextField ModifyPRMaxTxtField;

    @FXML
    private TextField ModifyPRMinTxtField;

    @FXML
    private TextField ModifyPRNameTxtField;

    @FXML
    private TextField ModifyPRPriceTxtField;

    @FXML
    private Button ModifyPRRemAsPartButton;

    @FXML
    private Button ModifyPRSaveButton;

    @FXML
    private TextField ModifyPRSearchByPartIDorNameTxtField;

    //PARTS TABLE MPRM
    @FXML
    private TableView<Part> PartsTableMM;
    @FXML
    private TableColumn<Part, Integer> MPRInvCol;

    @FXML
    private TableColumn<Part, Double> MPRPCCol;

    @FXML
    private TableColumn<Part, Integer> MPRPartIDCol;

    @FXML
    private TableColumn<Part, String> MPRPartNameCol;

    //ASSOCIATED PART TABLE MPR
    @FXML
    private TableView<Part> AssociatedPartsTableMPR;

    @FXML
    private TableColumn<Part, Integer> APMPRInvCol;

    @FXML
    private TableColumn<Part, Double> APMPRPCCol;

    @FXML
    private TableColumn<Part, Integer> APMPRPartIDCol;

    @FXML
    private TableColumn<Part, String> APMPRPartNameCol;

    //TEMPORARY LIST
    private ObservableList<Part> associatedPartsModify = FXCollections.observableArrayList();
    Product Mouse = new Product(5,"Mouse",10,1,2,3);

    //TODO ADD ASSOC PART BUTTON

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
//todo
    @FXML
    void PressModifyPRCancelButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void PressModifyPRSaveButton(ActionEvent event) throws IOException {
//save changes from the input on the textfields
        String mistakeModPr = "";
        try {
            mistakeModPr = "inv";
            int id = Integer.parseInt(ModifyPRIdTxtField.getText());
            mistakeModPr = "name";
            String name = ModifyPRNameTxtField.getText();
            mistakeModPr = "stock";
            int stock = Integer.parseInt(ModifyPRInvTxtField.getText());
            mistakeModPr = "price";
            double price = Double.parseDouble(ModifyPRPriceTxtField.getText());
            mistakeModPr = "max";
            int max = Integer.parseInt(ModifyPRMaxTxtField.getText());
            mistakeModPr = "min";
            int min = Integer.parseInt(ModifyPRMinTxtField.getText());

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
        int id = Integer.parseInt(ModifyPRIdTxtField.getText());
        int stock = Integer.parseInt(ModifyPRInvTxtField.getText());
        double price = Double.parseDouble(ModifyPRPriceTxtField.getText());
        int max = Integer.parseInt(ModifyPRMaxTxtField.getText());
        int min = Integer.parseInt(ModifyPRMinTxtField.getText());
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

