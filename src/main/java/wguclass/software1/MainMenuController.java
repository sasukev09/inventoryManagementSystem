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

/**
 * This class is the Main Menu of the application.
 *
 * Displaying parts and products, and the buttons to interact with the application and manage the inventory.
 *
 * @author Kevin Salazar
 */

public class MainMenuController implements Initializable {

    /**
     * The stage for the Main Menu
     */
    private Stage stage;

    /**
     *The scene for the Main Menu
     */
    private Scene scene;


    /**
     * Table view for the parts.
     */
    @FXML
    private TableView <Part> PartsTableMM;

    /**
     * Table column for the part id.
     */
    @FXML
    private TableColumn <Part,Integer> PartIDCol;

    /**
     *  Table column for the part name.
     */
    @FXML
    private TableColumn <Part,String> PartNameCol;

    /**
     *  Table column for the part inventory.
     */
    @FXML
    private TableColumn <Part, Integer> InvCol;

    /**
     *  Table column for the part price.
     */
    @FXML
    private TableColumn <Part, Double> PCCol;

    /**
     *  Button to add a part, opening the add part menu
     */
    @FXML
    private Button OnAddPMM;

    /**
     *  Button to delete a part
     */
    @FXML
    private Button OnDeletePMM;

    /**
     *  Button to modify a part, opening the modify part menu
     */
    @FXML
    private Button OnModifyPMM;

    /**
     *  Text field to search a part.
     */
    @FXML
    private TextField SearchbyPartOrIDMM;

    /**
     *  Tableview for the products
     */
    @FXML
    private TableView <Product> ProductsTableMM;

    /**
     *  Table column for the product id
     */
    @FXML
    private TableColumn <Product, Integer> ProIDCol;

    /**
     * Table column for the product name
     */
    @FXML
    private TableColumn <Product, String> ProNameCol;

    /**
     *  Table column for the product inventory
     */
    @FXML
    private TableColumn <Product, Integer> InvProCol;

    /**
     *  Table column for the product price
     */
    @FXML
    private TableColumn <Product, Double> ProPCCol;

    /**
     *  Button to add a product, opening the add product menu
     */
    @FXML
    private Button OnAddPRMM;

    /**
     *  Button to delete a product
     */
   @FXML
    private Button OnDeletePRMM;

    /**
     *  Button to modify a product, opening the modify product menu
     */
    @FXML
    private Button OnModifyPRMM;

    /**
     *  Text field to search a product
     */
    @FXML
    private TextField SearchbyProductOrIDMM;

    /**
     *  Button to exit, which closes the application
     */
    @FXML
    private Button OnExit;

    /**
     * Looks up a part by ID or name, not case-sensitive
     * @param event Search event action
     */
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


    /**
     * Looks up a product by ID or name, not case-sensitive
     * @param event Search event action
     */
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

    /**
     * Opens the add part menu
     * @param event Opening add part menu action
     * @throws IOException
     */
    @FXML
    void PressAddPMM(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/AddPartMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens the add product menu
     * @param event Opening add product menu
     * @throws IOException
     */
    @FXML
    void PressAddPRMM(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/wguclass/Screens/AddProductMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Deletes a part from the table view
     *
     * Deletion confirmation pop up for the user
     *
     * @param event Event of deleting a part
     */
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


    /**
     * Deletes a product from the product table view
     *
     * Error pop up if the product still contains any parts
     *
     * @param event Event of deleting a product
     * @throws IOException
     */
    @FXML
    void PressDeletePRMM(ActionEvent event) throws IOException {
        Product selectedProduct = ProductsTableMM.getSelectionModel().getSelectedItem();
        //todo make validation for associated parts linked to product
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
                Product selectedProductDelete = ProductsTableMM.getSelectionModel().getSelectedItem();
                if(selectedProductDelete.getAllAssociatedParts().size() > 0) {
                    //todo alert for delete all parts before deleting product
                    Alert alertDeleteAP = new Alert(Alert.AlertType.ERROR);
                    alertDeleteAP.setTitle("Unable to delete Product");
                    alertDeleteAP.setContentText("Please delete all parts from the product");
                    alertDeleteAP.showAndWait();
                    return;
                }
                Inventory.deleteProduct(selectedProduct);
            }
        }
    }

    /**
     * Exiting the application, with a confirmation alert
     * @param event Event of exiting the application if confirmation is given
     * @throws IOException
     */
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

    /**
     * Opening the modify part menu, by pressing the modify button
     * @param event Event that leads to the modify part menu
     */
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
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/wguclass/Screens/ModifyPartMenu.fxml"));
            Parent scene= loader.load();
            System.out.println("...after loader.load");
            ModifyPartController ModPartController = loader.getController();
            System.out.println("...after load gets controller");
            ModPartController.receivePartsSetData(PartsTableMM.getSelectionModel().getSelectedItem());
            System.out.println("...after receive set part");

            stage = (Stage) PartsTableMM.getScene().getWindow();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            }
    }


    /**
     * The product object selected from the table view by the user
     */
//    private static Product productToModify;

    /**
     * Gets the product object from the table, selected by the user
     *
     * @return A product object, null if no product selected.
     */
//    public static Product getProductToModify() {
//        return productToModify;
//    }

    /**
     * Opens the modify product menu, by pressing the modify button
     * @param event If a product is selected correctly, it loads the modify product menu
     * @throws IOException
     */
    @FXML
    void PressModifyPRMM(ActionEvent event) throws IOException {
        //todo change everything to product related
        //todo create the static method in the modify product controller
        System.out.println("Modify Product button was pressed");
        Product productToModify = ProductsTableMM.getSelectionModel().getSelectedItem();

        if (productToModify == null) {
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
            ModProductController.receiveProductsSetData(productToModify);

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
    /**
     * Boolean variable that will help avoid any duplication after repeated initializing
     */
    public static boolean firstTimeAdded = true;


    /**
     * This initializes the initial data that the application will begin with
     *
     * It also populates the part and product tables
     *
     * @param url  Locates the relative paths for the root object, or null if not found
     * @param resourceBundle Resources used to localize the root object, or null if not found
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //calling myInitialData method from Inventory class, where the first parts and products
        //where created
        Inventory.myInitialData();
        System.out.println("main menu has been initialized");

        PartsTableMM.setItems(Inventory.getAllParts());
        PartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        InvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PCCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        ProductsTableMM.setItems(Inventory.getAllProducts());
        ProIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        InvProCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ProPCCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}

