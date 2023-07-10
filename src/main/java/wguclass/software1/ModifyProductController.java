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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Button ModifyPRAddButton;

    @FXML
    private Button ModifyPRCancelButton;

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
    private TableColumn<Part,Double> APMPRPCCol;

    @FXML
    private TableColumn<Part,Integer> APMPRPartIDCol;

    @FXML
    private TableColumn<Part,String> APMPRPartNameCol;

    @FXML
    void PressModifyPRAddButton(ActionEvent event) {

    }

    @FXML
    void PressModifyPRCancelButton(ActionEvent event)  throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void PressModifyPRRemAsPartButton(ActionEvent event) {

    }

    @FXML
    void PressModifyPRSaveButton(ActionEvent event) {

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PartsTableMPRM.setItems(Inventory.getAllParts());
        MPRPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        MPRPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        MPRInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        MPRPCCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //initiaizing Associated part table
        APMPRPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        APMPRPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        APMPRInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        APMPRPCCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        System.out.println("associated part table has been intialized");
    }
}
