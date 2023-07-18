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
        ModPIDTxtField.setText(String.valueOf(part.getId()));

//        if (part instanceof InHouse) {
//
//            InHouse part1 = (InHouse) part;
//            InveninHouseRadio.setSelected(true);
//            companyLabel.setText("Machine ID");
//            this.name.setText(part1.getName());
//            this.id.setText((Integer.toString(part1.getId())));
//            this.count.setText((Integer.toString(part1.getStock())));
//            this.price.setText((Double.toString(part1.getPrice())));
//            this.min.setText((Integer.toString(part1.getMin())));
//            this.max.setText((Integer.toString(part1.getMax())));
//            this.company.setText((Integer.toString(part1.getMachineID())));
//
//        }
//
//        if (part instanceof OutSourced) {
//
//            OutSourced part2 = (OutSourced) part;
//            outSourcedRadio.setSelected(true);
//            companyLabel.setText("Company Name");
//            this.name.setText(part2.getName());
//            this.id.setText((Integer.toString(part2.getId())));
//            this.count.setText((Integer.toString(part2.getStock())));
//            this.price.setText((Double.toString(part2.getPrice())));
//            this.min.setText((Integer.toString(part2.getMin())));
//            this.max.setText((Integer.toString(part2.getMax())));
//            this.company.setText(part2.getCompanyName());
//        }
//    }
         }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


}
