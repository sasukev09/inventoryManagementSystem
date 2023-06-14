package wguclass.software1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part part){
        allParts.add(part);
    }
    public static void addProduct(Product product){
        allProducts.add(product);
    }
    public static void lookupPart(int partid) {

    }
    }
