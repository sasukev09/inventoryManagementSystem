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

    @FXML
    private TableView<?> AssociatedPartsTable;

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

    @FXML
    void PressAddPRMRemoveAPartButton(ActionEvent event) {

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
        int inv =  Integer.parseInt(InvTextField.getText());
        double price = Double.parseDouble(PriceTextField.getText());
        int max = Integer.parseInt(MaxTextField.getText());
        int min = Integer.parseInt(MinTextField.getText());
       int productId = Inventory.generateProductId();

        Inventory.addProduct(new Product(productId, name,price,inv,min,max));

        System.out.println("Product had been added, returning to main menu.");

        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IDTextField.setText(String.valueOf(Inventory.productId));

        PartsTableAPRM.setItems(Inventory.getAllParts());
        APRPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        APRPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        APRInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        APRPCCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
