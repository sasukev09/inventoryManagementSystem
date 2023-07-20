package wguclass.software1;

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

public class AddProductController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button AddPRMAddButton;

    @FXML
    private Button AddPRMCancelButton;

    @FXML
    private Button AddPRMRemoveAPartButton;

    @FXML
    private Button AddPRMSaveButton;

    //ASSOCIATED PARTS TABLE APR
    @FXML
    private TableView <Part> AssociatedPartsTableAPR;

    @FXML
    private TableColumn <Part, Integer> APAPRInvCol;

    @FXML
    private TableColumn <Part, Double> APAPRPCCol;

    @FXML
    private TableColumn <Part, Integer> APAPRPartIDCol;

    @FXML
    private TableColumn<?, ?> APAPRPartNameCol;




    @FXML
    private TextField IDTextField;

    @FXML
    private TextField InvTextField;

    @FXML
    private TextField MaxTextField;

    @FXML
    private TextField MinTextField;

    @FXML
    private TextField NameTextField;

    //PARTS TABLE APR
    @FXML
    private TableView PartsTableAPRM;

    @FXML
    private TableColumn APRInvCol;

    @FXML
    private TableColumn APRPCCol;

    @FXML
    private TableColumn APRPartIDCol;

    @FXML
    private TableColumn APRPartNameCol;

    @FXML
    private TextField PriceTextField;

    @FXML
    private TextField SearchbyPartOrIDAPRM;

    @FXML
    void PressAddPRMAddButton(ActionEvent event) {

    }

    @FXML
    void PressAddPRMCancelButton(ActionEvent event)  throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
//TODO ASK CI ABOUT HOW TO DEAL WITH PARTS AND ASSOCIATED PARTS FOR THE PRODUCT MENUS
    //TODO INCLUDING REMOVE BUTTON VALIDATION FOR THE PRODUCT MENUS
    @FXML
    void PressAddPRMRemoveAPartButton(ActionEvent event) throws IOException {
        /*

        Part selectedPart = (Part) PartsTableAPRM.getSelectionModel().getSelectedItem();

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
    void APRMPressSearchPartIDName(ActionEvent event) {
        String SPart = SearchbyPartOrIDAPRM.getText();
        try {
            System.out.println("SPart =" + SPart);
            int partId = Integer.parseInt(SPart);
            Part part = Inventory.lookupPart(partId);
            if (part != null) {
                System.out.println("S2Part =" + SPart);
                PartsTableAPRM.getSelectionModel().select(part);
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
                PartsTableAPRM.setItems(PartSearched);
            else {
                PartsTableAPRM.setItems(Inventory.getAllParts());
                //ALERT PRODUCT NOT FOUND
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Part not Found");
                alert.setContentText("Part not found.");
                Optional<ButtonType> OK = alert.showAndWait();
                System.out.println("Part not found");
            }
        }
    }

    @FXML
    void PressAddPRMSaveButton(ActionEvent event) throws IOException {
        //save changes from the input on the textfields
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

        Inventory.addProduct(new Product(productId, name,price,inv,min,max));

        System.out.println("Product has been added, returning to main menu.");

        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IDTextField.setText(String.valueOf(Inventory.productId));
        //initializing part table
        PartsTableAPRM.setItems(Inventory.getAllParts());
        APRPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        APRPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        APRInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        APRPCCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        //initiaizing Associated part table
        APAPRPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        APAPRPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        APAPRInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        APAPRPCCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        System.out.println("associated part table has been intialized");
    }
}
