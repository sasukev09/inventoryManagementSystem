package wguclass.software1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static wguclass.software1.MainMenuController.firstTimeAdded;
public class Inventory {
    /**
     *
     It also contains the data of the initial set of parts and products.
     */

    /**
     @author Kevin Salazar
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    /**
     * An observable list of all parts in inventory.
     */
//    private static boolean firstTimeAdded = true;
//          if(!firstTimeAdded){
//        return;
//    }
//    firstTimeAdded = false;
    public static int partId = 1;
    /**
     * Initializing the id for parts, with a variable used for uniqueness in ids.
     */
    public static int generatePartId(){
        /**
         * Gives whatever partid that is next and increments it, whenever used.
         * @return The part id that generates is returned.
         */
        return partId ++;
    }

    public static void addPart(Part newPart) {
        /**
         * Adds a new part to the inventory when called.
         *
         * @param newPart A new part is generated.
         */
        allParts.add(newPart);
    }

    public static Part lookupPart(int partId) {
        /**
         * Looks up a part by id.
         *
         * @param partId The id of the part that will be looked for.
         * @return The part object if found, null if not found.
         */
        //enhanced for loop automatically doesn't need a counter
        for (Part temp : allParts) {
            if (temp.getId() == partId)
            return temp;
        }
        return null;
    }




    public static ObservableList<Part> lookupPart(String partName) {
        /**
         * Looks up a part by name.
         *
         * @param partName Name of the part that will be looked for.
         * @return The part object if found, null if not found.
         */
        ObservableList<Part> namedPart = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                namedPart.add(part);
            }
        }
        return namedPart;
    }


    public static void updatePart(int index, Part selectedPart) {
        /**
         * Replaces a part in the inventory. To update the selected part.
         *
         * @param index The index of the part that will be replaced.
         * @param selectedPart The part used in the replacement action.
         */
        allParts.set(index, selectedPart);
    }

    //ask CI about where to implement if statement and how to implement it to the txt alert
    public static boolean deletePart (Part selectedPart) {
        /**
         * Deletes a part in the parts list.
         *
         * @param selectedPart The part used in the replacement action.
         * @return A boolean to indicate if the part can or cannot be removed.
         */
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }
    }

    public static ObservableList<Part> getAllParts() {
        /**
         * Retrieves all parts from the parts list.
         *
         * @return The list allParts is returned.
         */
        return allParts;
    }


    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    /**
     * An observable list of all products in inventory.
     */
    public static int productId = 1;
    /**
     * Initializing the id for products, with a variable used for uniqueness in ids.
     */
    public static int generateProductId() {
        /**
         * Gives whatever productId that is next and increments it, whenever used.
         * @return The product id that was generated is returned.
         */
        //gives whatever partid and increments, whenever used, used everywhere you use partid
        return productId++ ;
    }

    public static void addProduct(Product newProduct) {
        /**
         * Adds a new product to the inventory when called.
         *
         * @param newProduct A new product is generated.
         */
        allProducts.add(newProduct);
    }

    public static Product lookupProduct(int productId) {
        //enhanced for loop automatically doesnt need a counter
        /**
         * Searches a product by id.
         *
         * @param productId The id of the product that will be looked for.
         * @return The product object if found, null if not found.
         */
        for (Product temp : allProducts) {
            if (temp.getId() == productId) {
            return temp;}
        }
        return null;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        /**
         * Searches a product by name.
         *
         * @param productName The name of the product that will be looked for.
         * @return The product object if found, null if not found.
         */
        ObservableList<Product> namedProduct = FXCollections.observableArrayList();
//toLowercase method, toUppercase, either or, they take the name and change it to upper/lower
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                namedProduct.add(product);
            }
        }
        return namedProduct;
    }


    public static void updateProduct(int index, Product selectedProduct) {
        /**
         * Replaces a part in the inventory. To update the selected product.
         *
         * @param index The index of the product that will be replaced.
         * @param selectedProduct The product used in the replacement action.
         */
        allProducts.set(index, selectedProduct);
    }

//ask CI about where to implement if statement and how to implement it to the txt alert
    public static boolean deleteProduct(Product selectedProduct) {
        /**
         * Deletes a product from the products list.
         *
         * @param selectedProduct The product used in the replacement action.
         * @return A boolean to indicate if the product can or cannot be removed.
         */
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }


    public static ObservableList<Product> getAllProducts () {
        /**
         * Retrieves all products from the products list.
         *
         * @return The list allProducts is returned.
         */
            return allProducts;
        }

//myInitialData method created to add the first parts and products that will appear in main menu
    //made it public, so it could be called inside the main menu controller
    public static void myInitialData(){
        /**
         * The creation of initial data for parts and products.
         *
         * @return If the object is added for the first time, no return will occur.
         */
        if(!firstTimeAdded){
            return;
        }
        firstTimeAdded = false;
        //PART GETS ADDED
        Part Wheel = new InHouse(Inventory.generatePartId(), "Wheel",29.99,1,1,10, 1);
        Inventory.getAllParts().add(Wheel);

        Part Cart = new Outsourced(Inventory.generatePartId(), "Cart",29.99,1,0,10, "Ford");
        Inventory.getAllParts().add(Cart);
        //PRODUCT GETS ADDED

        Product television = new Product(Inventory.generateProductId(), "Smart TV", 499.99, 20, 20, 100);
        Inventory.getAllProducts().add(television);

        Product Bicycle = new Product(Inventory.generateProductId(), "Bicycle",150.99,1,1,10);
        Inventory.getAllProducts().add(Bicycle);

        Product Train = new Product(Inventory.generateProductId(), "Train",125.99,1,0,10);
        Inventory.getAllProducts().add(Train);
//needed a reference variable "p" for plane
        Product Plane = new Product(Inventory.generateProductId(), "Plane",99.99,1,0,5);
        Inventory.getAllProducts().add(Plane);
        //added associated part "Wheel"
        Plane.addAssociatedPart(Wheel);
        }

    }
//GET MODIFY, ADD, VERIFICATIONS OF TXT FIELDS, DELETE, ADD AND FINALLY MOVE OVER TO PRODUCT
//ALL VARIABLES ARE LOWER CASE