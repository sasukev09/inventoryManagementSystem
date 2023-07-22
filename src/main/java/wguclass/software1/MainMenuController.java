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

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Part not selected");
            alert.setContentText("Part not selected, please select a part");
            alert.showAndWait();
          } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Part");
            alert.setContentText("Are you sure you want to delete Part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }
    }


    //NEEDS MORE WORK
    @FXML
    void PressDeletePRMM(ActionEvent event) throws IOException {
        Product selectedProduct = ProductsTableMM.getSelectionModel().getSelectedItem();

//if its a string you use .equals
//todo add alert that says that ALL PARTS MUST BE REMOVED BEFORE PRODUCT DELETION
        if (selectedProduct == null) {
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
                Inventory.deleteProduct(selectedProduct);
            }
        }
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
    void PressModifyPMM(ActionEvent event) {
        Part part = PartsTableMM.getSelectionModel().getSelectedItem();
        if (part == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Part not selected");
            alert.setContentText("Part not selected, please select a part");
            alert.showAndWait();
            System.out.println("...part was null");
            return;
        }
       //Select a part from the table, store it in a part variable,
        //would call a static method from the modify part, created in the modify part controller
//        ModifyPartController.setData();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/wguclass/Screens/ModifyPartMenu.fxml"));
            Parent scene= loader.load();

            //NOW the getController method, allows you to reference another controller so that you can access that controller public members
            //getting access to the instance of the receiveSetData method located in the ModifyPart menu
            //once we do this we will be able to use the receiveSetData method
            System.out.println("...after loader.load");
            //Creating a reference variable for the ModifyPartController
            ModifyPartController ModPartController = loader.getController();
            // we created the fxmlloader object and let it know which fxmlview to use
            // and now we let it know which controller is associated to this fxmlview
            System.out.println("...after load gets controller");
            ModPartController.receivePartsSetData(PartsTableMM.getSelectionModel().getSelectedItem());
            //by using getselectionmodel & getselecteditem, now we have our selected part sent
            //to the receiveSetData method,to pass info from MainMenu to ModifyPart
            System.out.println("...after receive set part");

            //stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage = (Stage) PartsTableMM.getScene().getWindow();
            //we need a reference to the container of this fxml doc
       //     Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            }
    }



    @FXML
    void PressModifyPRMM(ActionEvent event) throws IOException {
        //todo change everything to product related
        //todo create the static method in the modify product controller
        System.out.println("Modify Product button was pressed");
        Product product = ProductsTableMM.getSelectionModel().getSelectedItem();
        if (product == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product not selected");
            alert.setContentText("Product not selected, please select a product");
            alert.showAndWait();
            System.out.println("...product was null");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/wguclass/Screens/ModifyProductMenu.fxml"));
            Parent scene = loader.load();
            System.out.println("...after loader.load");
            ModifyProductController ModProductController = loader.getController();

            System.out.println("...after load gets controller");
            ModProductController.receiveProductsSetData(ProductsTableMM.getSelectionModel().getSelectedItem());

            System.out.println("...after receive set part");
            stage = (Stage) ProductsTableMM.getScene().getWindow();

            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

