package wguclass.software1;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.RadioButton;
        import javafx.scene.control.TextField;
        import javafx.scene.control.ToggleGroup;
        import javafx.scene.text.Text;
        import javafx.stage.Stage;

        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {

    public Text ModPMLabelTxtMachID;
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TextField ModPIDTxtField;

    @FXML
    private RadioButton ModPInHouseRadio;

    @FXML
    private TextField ModPNameTxtField;

    @FXML
    private RadioButton ModPOutRadio;

    @FXML
    private Button ModPartCancelButton;

//PASS THE FOOTBALL WEBINAR BY MARK TO SET UP PRESET
    @FXML
    private TextField ModPartInvTextField;

    @FXML
    private TextField ModPartMachineIDTxtField;

    @FXML
    private TextField ModPartMaxTxtField;

    @FXML
    private TextField ModPartMinTxtField;

    @FXML
    private TextField ModPartPCTxtField;

    @FXML
    private Button ModPartSaveButton;

    @FXML
    private ToggleGroup ModifyPartMenu;

    @FXML
    void PressModPInHouseRadio(ActionEvent event)  {
        System.out.println("[ModifyPartMenu::ModPMLabelTxtMachID] In-house part radio button selected, updating label");
        ModPMLabelTxtMachID.setText("Machine ID");

    }

    @FXML
    void PressModPOutRadio(ActionEvent event) {
        System.out.println("[ModifyPartMenu::ModPMLabelTxtMachID] Outsourced part radio button selected, updating label");
        ModPMLabelTxtMachID.setText("Company Name");

    }

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

    @FXML
    void PressModPartSaveButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void receiveSetData (Part part) {
        //String.valueOf retrieved the id of the p1 and converted that int into string to assign to the label
        if (part instanceof InHouse) {

            ModPMLabelTxtMachID.setText("Machine ID");
            ModPIDTxtField.setText(String.valueOf(part.getId()));
            ModPNameTxtField.setText(part.getName());
            ModPartInvTextField.setText(String.valueOf(part.getStock()));
            ModPartPCTxtField.setText(String.valueOf(part.getPrice()));
            ModPartMaxTxtField.setText(String.valueOf(part.getMax()));
            ModPartMinTxtField.setText((String.valueOf(part.getMin())));
            ModPartMachineIDTxtField.setText((String.valueOf(InHouse.getMachineId())));

        }

        if (part instanceof Outsourced) {
            ModPMLabelTxtMachID.setText("Company Name");
            ModPIDTxtField.setText(String.valueOf(part.getId()));
            ModPNameTxtField.setText(part.getName());
            ModPartInvTextField.setText(String.valueOf(part.getStock()));
            ModPartPCTxtField.setText(String.valueOf(part.getPrice()));
            ModPartMaxTxtField.setText(String.valueOf(part.getMax()));
            ModPartMinTxtField.setText((String.valueOf(part.getMin())));
            ModPartMachineIDTxtField.setText((Outsourced.getCompanyName()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


}
