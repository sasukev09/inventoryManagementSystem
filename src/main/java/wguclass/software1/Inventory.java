package wguclass.software1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    public static int partId = 1;
    public static int generatePartId(){
        //gives whatever partid and increments, whenever used, used everywhere you use partid
        return partId ++;
    }

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static Part lookupPart(int partId) {
        //enhanced for loop automatically doesn't need a counter
        for (Part temp : allParts) {
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


    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }
//check with CI
    public static boolean deletePart (Part selectedPart) {
        return allParts.remove(selectedPart);

    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }




    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static int productId = 1;
    public static int generateProductId() {
        //gives whatever partid and increments, whenever used, used everywhere you use partid
        return productId++ ;
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Product lookupProduct(int productId) {
        //enhanced for loop automatically doesnt need a counter
        // semicolon on if statement is wrong, basically cuts
        for (Product temp : allProducts) {
            if (temp.getId() == productId) {
            return temp;}
        }
        return null;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> namedProduct = FXCollections.observableArrayList();
//toLowercase method, toUppercase, either or, they take the name and change it to upper/lower
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                namedProduct.add(product);
            }
        }
        return namedProduct;
    }


    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    //check with CI boolean as UML with return statements for values true/false
    public static boolean deleteProduct(Product selectedProduct) {
        if (selectedProduct != null) {
            allProducts.add(selectedProduct);
            return true;
        }
        else {
            return false;
        }
    }


        public static ObservableList<Product> getAllProducts () {
            return allProducts;
        }
    }

