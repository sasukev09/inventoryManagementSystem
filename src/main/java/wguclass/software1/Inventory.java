package wguclass.software1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    public static void addPart(Part part){
        allParts.add(part);
    }
/*
    public static ObservableList<Part> lookupPart(int partId) {
        ObservableList<Part> namedPart = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();

        for(Part part: allParts) {
            if(part.getId().contains(partId)){

            }

        }

        return namedPart;
    }*/


    public static  ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> namedPart = FXCollections.observableArrayList();

        ObservableList<Part> allParts = Inventory.getAllParts();

        for(Part part: allParts) {
            if(part.getName().contains(partName)){
                namedPart.add(part);
            }
        }

        return namedPart;
    }


    public static void updatePart(int index, Part part) {
    }
    public static void deletePart(Part part) {
    }

    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    public static void addProduct(Product product){
        allProducts.add(product);
    }
    public static void lookupProduct(int productId) {
    }
    public static void updateProduct(int index, Product product) {
    }
    public static void deleteProduct(Product product) {
    }

    private static ObservableList<Product> getAllProducts = FXCollections.observableArrayList();
    }

