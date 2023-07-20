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

    public ObservableList<Part> tempAssociatedParts = FXCollections.observableArrayList();

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
    private TableView <Part> PartsTableMPRM;
    @FXML
    private TableColumn <Part, Integer> MPRInvCol;

    @FXML
    private TableColumn <Part, Double> MPRPCCol;

    @FXML
    private TableColumn <Part, Integer> MPRPartIDCol;

    @FXML
    private TableColumn <Part, String> MPRPartNameCol;

    //ASSOCIATED PART TABLE MPR
    @FXML
    private TableView<Part> AssociatedPartsTableMPR;

    @FXML
    private TableColumn<Part, Integer> APMPRInvCol;

    @FXML
    private TableColumn <Part,Double> APMPRPCCol;

    @FXML
    private TableColumn <Part, Integer> APMPRPartIDCol;

    @FXML
    private TableColumn<Part,String> APMPRPartNameCol;

    @FXML
    void PressModifyPRAddButton(ActionEvent event) {
        Part selectedPart = PartsTableMPRM.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Part not selected");
            alert.setContentText("Part not selected, please select a part");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Associating part with product");
            alert.setContentText("Are you sure you want to associate this part?");
            Optional<ButtonType> result = alert.showAndWait();
            Part copiedPart = PartsTableMPRM.getSelectionModel().getSelectedItem();
            AssociatedPartsTableMPR.getItems().add(copiedPart);
        }
    }

    @FXML
    void PressModifyPRCancelButton(ActionEvent event)  throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    //TODO ASK CI ABOUT HOW TO DEAL WITH PARTS AND ASSOCIATED PARTS FOR THE PRODUCT MENUS
    //TODO INCLUDING REMOVE BUTTON VALIDATION FOR THE PRODUCT MENUS
    @FXML
    void PressModifyPRRemAsPartButton(ActionEvent event) throws IOException {
        /*
        Part selectedPart = (Part) tempAssociatedParts.getSelectionModel().getSelectedItem();

//if its a string you use .equals

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product not selected");
            alert.setContentText("Product not selected, please select a product");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Product");
            alert.setContentText("Are you sure you want to delete Product?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }
        */
    }

    @FXML
    void PressModifyPRSaveButton(ActionEvent event) throws IOException {
//save changes from the input on the textfields
        String name = ModifyPRNameTxtField.getText();
        int inv = 0;
        double price = 0;
        int max = 0;
        int min = 0 ;
        int productId = 0;
        String mistakeModPr = "";

        try {
            mistakeModPr = "inv";
            inv = Integer.parseInt(ModifyPRInvTxtField.getText());
            mistakeModPr = "price";
            price = Double.parseDouble(ModifyPRPriceTxtField.getText());
            mistakeModPr = "max";
            max = Integer.parseInt(ModifyPRMaxTxtField.getText());
            mistakeModPr = "min";
            min = Integer.parseInt(ModifyPRMinTxtField.getText());
            mistakeModPr = "id";
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
        catch(NumberFormatException e) {
            System.out.println("entry must be a number");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid entry");
            alert.setContentText("Please enter a number for the " + mistakeModPr + ".");
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

        Inventory.addProduct(new Product(productId, name,price,inv,min,max));

        System.out.println("Product had been added, returning to main menu.");

        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void MPRMPressSearchPartIDName(ActionEvent event)  {
        String SPart = ModifyPRSearchByPartIDorNameTxtField.getText();
        try {
            System.out.println("SPart =" + SPart);
            int partId = Integer.parseInt(SPart);
            Part part = Inventory.lookupPart(partId);
            if (part != null) {
                System.out.println("S2Part =" + SPart);
                PartsTableMPRM.getSelectionModel().select(part);
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
                PartsTableMPRM.setItems(PartSearched);
            else {
                PartsTableMPRM.setItems(Inventory.getAllParts());
                //ALERT PRODUCT NOT FOUND
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Part not Found");
                alert.setContentText("Part not found.");
                Optional<ButtonType> OK = alert.showAndWait();
                System.out.println("Part not found");
            }
        }
    }

    public void receiveProductsSetData (Product product) {
        //String.valueOf retrieved the id of the p1 and converted that int into string to assign to the label

        ModifyPRIdTxtField.setText(String.valueOf(product.getId()));
        ModifyPRNameTxtField.setText(product.getName());
        ModifyPRInvTxtField.setText(String.valueOf(product.getStock()));
        ModifyPRPriceTxtField.setText(String.valueOf(product.getPrice()));
        ModifyPRMaxTxtField.setText(String.valueOf(product.getMax()));
        ModifyPRMinTxtField.setText((String.valueOf(product.getMin())));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            PartsTableMPRM.setItems(Inventory.getAllParts());
            MPRPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            MPRPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            MPRInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
            MPRPCCol.setCellValueFactory(new PropertyValueFactory<>("price"));

            //initiaizing Associated part table
            //making a temporary list and then when ready you copy it over to the permanent
            //
//            AssociatedPartsTableMPR.setItems(Product.getAllAssociatedParts);
//            APMPRPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
//            APMPRPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
//            APMPRInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
//            APMPRPCCol.setCellValueFactory(new PropertyValueFactory<>("price"));
//            System.out.println("associated part table has been intialized");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    }