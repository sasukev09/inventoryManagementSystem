package wguclass.software1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static wguclass.software1.MainMenuController.firstTimeAdded;
/**
 * This class models the Inventory of the program and adds test data.
 *
 * Includes lists, setters, getters, lookup methods for all parts and products
 *
 * @author Kevin Salazar
 */
public class Inventory {

    /**
     * Observable list for all parts
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * Initializing the id for parts, with a variable used for uniqueness in ids.
     */
    public static int partId = 1;

    /**
     * Gives whatever partid that is next and increments it, whenever used.
     * @return The part id that generates is returned.
     */
    public static int generatePartId(){
        return partId ++;
    }

    /**
     * Adds a new part to the inventory when called.
     *
     * @param newPart A new part is generated.
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Looks up a part by id.
     *
     * @param partId The id of the part that will be looked for.
     * @return The part object if found, null if not found.
     */
    public static Part lookupPart(int partId) {
        for (Part temp : allParts) {
            if (temp.getId() == partId)
            return temp;
        }
        return null;
    }

    /**
     * Looks up a part by name.
     *
     * @param partName Name of the part that will be looked for.
     * @return The part object if found, null if not found.
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> namedPart = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                namedPart.add(part);
            }
        }
        return namedPart;
    }

    /**
     * Replaces a part in the inventory. To update the selected part.
     *
     * @param index The index of the part that will be replaced.
     * @param selectedPart The part used in the replacement action.
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }


    /**
     * Deletes a part in the parts list.
     *
     * @param selectedPart The part used in the replacement action.
     * @return A boolean to indicate if the part can or cannot be removed.
     */
    public static boolean deletePart (Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Retrieves all parts from the parts list.
     *
     * @return The list allParts is returned.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * An observable list of all products in inventory.
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Initializing the id for products, with a variable used for uniqueness in ids.
     */
    public static int productId = 1;

    /**
     * Gives whatever productId that is next and increments it, whenever used.
     * @return The product id that was generated is returned.
     */
    public static int generateProductId() {
        return productId++ ;
    }

    /**
     * Adds a new product to the inventory when called.
     *
     * @param newProduct A new product is generated.
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Searches a product by id.
     *
     * @param productId The id of the product that will be looked for.
     * @return The product object if found, null if not found.
     */
    public static Product lookupProduct(int productId) {

        for (Product temp : allProducts) {
            if (temp.getId() == productId) {
            return temp;}
        }
        return null;
    }

    /**
     * Searches a product by name.
     *
     * @param productName The name of the product that will be looked for.
     * @return The product object if found, null if not found.
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> namedProduct = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                namedProduct.add(product);
            }
        }
        return namedProduct;
    }

    /**
     * Replaces a product in the inventory. To update the selected product.
     *
     * @param index The index of the product that will be replaced.
     * @param selectedProduct The product used in the replacement action.
     */
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }


    /**
     * Deletes a product from the product list.
     *
     * @param selectedProduct The product used in the replacement action.
     * @return A boolean to indicate if the product can or cannot be removed.
     */
    public static boolean deleteProduct(Product selectedProduct) {

        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retrieves all products from the product list.
     *
     * @return The list allProducts is returned.
     */
    public static ObservableList<Product> getAllProducts () {
            return allProducts;
        }

    /**
     * The creation of initial data for parts and products.
     * @return If the object is added for the first time, no return will occur.
     */
    public static void myInitialData(){

        if(!firstTimeAdded){
            return;
        }
        firstTimeAdded = false;

        Part Wheel = new InHouse(Inventory.generatePartId(), "Wheel",29.99,1,1,10, 1);
        Inventory.getAllParts().add(Wheel);

        Part Cart = new Outsourced(Inventory.generatePartId(), "Cart",29.99,1,0,10, "Ford");
        Inventory.getAllParts().add(Cart);

        Product television = new Product(Inventory.generateProductId(), "Smart TV", 499.99, 20, 20, 100);
        Inventory.getAllProducts().add(television);

        Product Bicycle = new Product(Inventory.generateProductId(), "Bicycle",150.99,1,1,10);
        Inventory.getAllProducts().add(Bicycle);

        Product Train = new Product(Inventory.generateProductId(), "Train",125.99,1,0,10);
        Inventory.getAllProducts().add(Train);

        Product Plane = new Product(Inventory.generateProductId(), "Plane",99.99,1,0,5);
        Inventory.getAllProducts().add(Plane);
        Plane.addAssociatedPart(Wheel);
        }

    }
