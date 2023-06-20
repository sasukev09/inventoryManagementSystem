package wguclass.software1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    public static void addPart(Part part){
        allParts.add(part);
    }
    public static void lookupPart(int partId) {

    }
    //FIX THIS
    public static void updatePart(int part) {

    }
    public static void deletePart(Part part) {

    }
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    public static void addProduct(Product product){
        allProducts.add(product);
    }
    public static void lookupProduct(int productId) {

    }
    //FIX THIS
    public static void updateProduct(int product) {

    }
    public static void deleteProduct(Product product) {

    }
   // ObservableList<Product>;

    }

