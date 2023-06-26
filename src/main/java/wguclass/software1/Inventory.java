package wguclass.software1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    public static void addPart(Part part) {
        allParts.add(part);
    }

    public static Part lookupPart(int partId) {
        //enhanced for loop automatically doesn't need a counter
        for(Part temp : allParts) {
            if (temp.getId() == partId) ;
            return temp;
        }
        return null;
    }


    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> namedPart = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                namedPart.add(part);
            }
        }
        return namedPart;
    }


    public static void updatePart(int index, Part part) {
        allParts.set(index, part);
    }

    public static void deletePart(Part part) {
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }


    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    public static Product lookupProduct(int productId) {
    //enhanced for loop automatically doesnt need a counter
        for(Product temp : allProducts) {
            if (temp.getId() == productId) ;
            return temp;
        }
        return null;
         }

    public static  ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> namedProduct = FXCollections.observableArrayList();
//toLowercase method, toUppercase, either or, they take the name and change it to upper/lower
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                namedProduct.add(product);
            }
        }
        return namedProduct;
    }


    public static void updateProduct(int index, Product product) {
        allProducts.set(index, product);
    }

    public static void deleteProduct(Product product) {
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
