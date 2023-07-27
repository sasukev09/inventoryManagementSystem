package wguclass.software1;
/**
 * Supplied class Part.java
 */

/**
 * Class provided by WGU, models the part object.
 *
 * This class Models the part, including the constructor for the part, setters, getters, etc.
 */
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * The constructor for the part object.
     *
     * @param id part id
     * @param name part name
     * @param price part price
     * @param stock part inventory
     * @param min part min
     * @param max part max
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Gets the part id
     * @return the id getter
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the part id
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the partname
     * @return the name getter
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the part name
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the part price
     * @return the price getter
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the part price
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the part inventory
     * @return the stock getter
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the part inventory
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Gets the part min
     * @return the min getter
     */
    public int getMin() {
        return min;
    }

    /**
     * Sets the part min
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Gets the part max
     * @return the max getter
     */
    public int getMax() {
        return max;
    }

    /**
     * Sets the part max
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

}