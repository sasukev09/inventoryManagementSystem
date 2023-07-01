package wguclass.software1;
//IMPORTS FOR THE CLASS
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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class MainMenuController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    //PARTS TABLE AND BUTTONS
    @FXML
    private TableView PartsTableMM;
    @FXML
    private TableColumn PartIDCol;
    @FXML
    private TableColumn PartNameCol;
    @FXML
    private TableColumn InvCol;
    @FXML
    private TableColumn PCCol;

    @FXML
    private Button OnAddPMM;
    @FXML
    private Button OnDeletePMM;
    @FXML
    private Button OnModifyPMM;
    @FXML
    private TextField SearchbyPartOrIDMM;

    //PRODUCTS TABLE AND BUTTONS
    @FXML
    private TableView ProductsTableMM;
    @FXML
    private TableColumn ProIDCol;
    @FXML
    private TableColumn ProNameCol;
    @FXML
    private TableColumn InvProCol;
    @FXML
    private TableColumn ProPCCol;

    @FXML
    private Button OnAddPRMM;
   @FXML
    private Button OnDeletePRMM;
    @FXML
    private Button OnModifyPRMM;
    @FXML
    private TextField SearchbyProductOrIDMM;

    //EXIT BUTTON
    @FXML
    private Button OnExit;

    //ON ACTION FOR BUTTONS AND TXT FIELDS
    @FXML
    void PressSearchPartIDName(ActionEvent event) {
    String SPart = SearchbyPartOrIDMM.getText();

    ObservableList<Part> PartSearched = Inventory.lookupPart(SPart);

        PartsTableMM.setItems(PartSearched);

    SearchbyPartOrIDMM.setText("");
        System.out.println("All parts list"  + Inventory.getAllParts());
    }

    @FXML
    void PressSearchProductIDName(ActionEvent event) {
        String SProduct = SearchbyProductOrIDMM.getText();
                try {
                    System.out.println("SProduct =" + SProduct);
                    int productId = Integer.parseInt(SProduct);
                    Product product = Inventory.lookupProduct(productId);
                    if (product != null) {
                        System.out.println("S2Product =" + SProduct);
                        ProductsTableMM.getSelectionModel().select(product);
                        return;
                    }
                    else {
                        //POP UP AN ALERT, PRODUCT NOT FOUND
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Product not Found");
                        alert.setContentText("Product not found.");
                        Optional<ButtonType> OK = alert.showAndWait();
                        System.out.println("Product not found");
                    }
                } catch (NumberFormatException e) {
                    ObservableList<Product> ProductSearched = Inventory.lookupProduct(SProduct);
                    if(ProductSearched.size() != 0)
                        ProductsTableMM.setItems(ProductSearched);
                    else {
                        ProductsTableMM.setItems(Inventory.getAllProducts());
                        //ALERT PRODUCT NOT FOUND
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Product not Found");
                        alert.setContentText("Product not found.");
                        Optional<ButtonType> OK = alert.showAndWait();
                        System.out.println("Product not found");
                    }
                }

          //  else {
               // ProductsTableMM.setItems(ProductSearched);
          //  }

            //SearchbyProductOrIDMM.setText("");
            //System.out.println("All products list" + Inventory.getAllProducts());
    }

    @FXML
    void PressAddPMM(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/AddPartMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //DONE?
    @FXML
    void PressAddPRMM(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/AddProductMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //NEEDS MOERE WORK
    @FXML
    void PressDeletePMM(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Part");
        alert.setContentText("Are you sure you want to delete Part?");
        Optional<ButtonType> result = alert.showAndWait();

    }
    //NEEDS MORE WORK
    @FXML
    void PressDeletePRMM(ActionEvent event) {


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Product");
        alert.setContentText("Are you sure you want to delete Product?");
        Optional<ButtonType> result = alert.showAndWait();
    }
    //EXIT BUTTON FOR THE MAIN MENU, INCLUDING THE "ARE YOU SURE?" DIALOG BOX
    @FXML
    void PressExitMM(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/Main Menu.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.close();
        }
    }
    @FXML
    void PressModifyPMM(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/ModifyPartMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void PressModifyPRMM(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/ModifyProductMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    //PARTS AND PRODUCTS OBSERVABLE LISTS


    //INITIALIZE
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//PART TABLE GETS INITIALIZED
      PartsTableMM.setItems(Inventory.getAllParts());
      PartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
      PartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
      InvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
      PCCol.setCellValueFactory(new PropertyValueFactory<>("price"));
//PARTS GET ADDED
        Inventory.getAllParts().add(new InHouse(Inventory.generatePartId(), "Wheel",29.99,1,1,10, 1));

        Inventory.getAllParts().add(new Outsourced(Inventory.generatePartId(), "Cart",29.99,1,0,10, "Ford"));


//PRODUCT TABLE GETS INITIALIZED
      ProductsTableMM.setItems(Inventory.getAllProducts());
      ProIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
      ProNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
      InvProCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
      ProPCCol.setCellValueFactory(new PropertyValueFactory<>("price"));

 //PRODUCT GETS ADDED
            Inventory.getAllProducts().add(new Product(Inventory.generateProductId(), "Bicycle",150.99,1,1,10));

        Inventory.getAllProducts().add(new Product(Inventory.generateProductId(), "Train",125.99,1,0,10));

        Inventory.getAllProducts().add(new Product(Inventory.generateProductId(), "Plane",99.99,1,0,5));
    }



}

