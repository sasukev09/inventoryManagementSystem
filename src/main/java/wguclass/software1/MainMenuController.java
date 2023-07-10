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
    private TableView <Part> PartsTableMM;
    @FXML
    private TableColumn <Part,Integer> PartIDCol;
    @FXML
    private TableColumn <Part,String> PartNameCol;
    @FXML
    private TableColumn <Part, Integer> InvCol;
    @FXML
    private TableColumn <Part, Double> PCCol;

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
    private TableView <Product> ProductsTableMM;
    @FXML
    private TableColumn <Product, Integer> ProIDCol;
    @FXML
    private TableColumn <Product, String> ProNameCol;
    @FXML
    private TableColumn <Product, Integer> InvProCol;
    @FXML
    private TableColumn <Product, Double> ProPCCol;

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
        try {
            System.out.println("SPart =" + SPart);
            int partId = Integer.parseInt(SPart);
            Part part = Inventory.lookupPart(partId);
            if (part != null) {
                System.out.println("S2Part =" + SPart);
                PartsTableMM.getSelectionModel().select(part);
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


    //OG CORRECT CODE TO SEARCH BY ID OR NAME
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
        Part selectedPart = PartsTableMM.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Part");
        alert.setContentText("Are you sure you want to delete Part?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            Inventory.deletePart(selectedPart);
        }
    }


    //NEEDS MORE WORK
    @FXML
    void PressDeletePRMM(ActionEvent event) throws IOException {
        Product selectedProduct = ProductsTableMM.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Part");
        alert.setContentText("Are you sure you want to delete Part?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            Inventory.deleteProduct(selectedProduct);
        }
    }

//        Product selectedProduct = ProductsTableMM.getSelectionModel().getSelectedItem();
//        if (SearchbyPartOrIDMM.getSelectedText() == null) {
//            Alert nullalert = new Alert(Alert.AlertType.CONFIRMATION);
//            nullalert.setTitle("Delete Product");
//            nullalert.setContentText("Are you sure you want to delete Product?");
//         }
//
//        Optional<ButtonType> result = nullAlert.showAndWait();
//        if(result.isPresent() && result.get() == ButtonType.OK) {
//            Inventory.deleteProduct(selectedProduct);
//        }
//        else {
//            ProductsTableMM.setItems(Inventory.getAllProducts());
//            //ALERT PRODUCT NOT FOUND
//            Alert deletealert = new Alert(Alert.AlertType.ERROR);
//            deletealert.setTitle("Product not Found");
//            deletealert.setContentText("Product not found.");
//            Optional<ButtonType> OK = deletealert.showAndWait();
//            System.out.println("Product not found");
//        }
//    }

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

    //created a boolean variable called firstTimeAdded, and used it in an "if" statement inside the
    //myInitialData method in Inventory, this way, to avoid duplicates every time initialized
    public static boolean firstTimeAdded = true;

    //INITIALIZE
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //calling myInitialData method from Inventory class, where the first parts and products
        //where created
        Inventory.myInitialData();


        System.out.println("main menu has been initialized");

//PART TABLE GETS INITIALIZED
        PartsTableMM.setItems(Inventory.getAllParts());
        PartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        InvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PCCol.setCellValueFactory(new PropertyValueFactory<>("price"));

//PRODUCT TABLE GETS INITIALIZED
        ProductsTableMM.setItems(Inventory.getAllProducts());
        ProIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        InvProCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ProPCCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}

