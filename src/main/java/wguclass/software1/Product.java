package wguclass.software1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class models a product and associated parts.
 * Contains the constructors for the product along with its setters and getters, and also setters, getters for the associated parts.
 * @author Kevin Salazar
 *
 */
public class Product {
     /**
      * An observable list for associated parts
      */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

     /**
      * The ID for the product
      */
    private int id;

     /**
      * The name for the product
      */
    private String name;

     /**
      * The price for the product
      */
    private double price;

     /**
      * The stock for the product
      */
    private int stock;

     /**
      * The min for the product
      */
    private int min;

     /**
      * The max for the product
      */
    private int max;


     /**
      * A constructor for a new instance of a product
      *
      * @param id the ID for the product
      * @param name the name of the product
      * @param price the price of the product
      * @param stock the inventory level of the product
      * @param min the minimum level for the product
      * @param max the maximum level for the product
      */
    public Product (int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Gets product id
     * @return getter that returns the id of the product
     */
    public int getId() {
        return id;
    }

    /**
     * Sets product id
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets product name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets product name
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets product price
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets product price
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets product inventory
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets product inventory
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Gets product min
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * Sets product min
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Gets product max
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * Sets product max
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Adds a part to the associated parts list.
     * @return addAssociatedPart deletes associated part
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Deletes an associated part from the assocaited part list.
     * @param selectedAssociatedPart The part selected for deletion.
     * @return If a part is correctly selected, it will be deleted.
     */
     public boolean deleteAssociatedPart(Part selectedAssociatedPart)  {
         if (associatedParts.contains(selectedAssociatedPart)) {
             associatedParts.remove(selectedAssociatedPart);
             return true;
         }
         else
             return false;
     }

    /**
     * List that gets all associated parts
     * @return the associatedParts list
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;}
}
