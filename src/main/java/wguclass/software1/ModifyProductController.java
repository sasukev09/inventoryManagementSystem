package wguclass.software1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
