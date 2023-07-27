package wguclass.software1;

/**
 * This class models the In-house part.
 *
 * Including the unique constructor "machine id" is included in this class, along with its setters and getters.
 *
 * @author Kevin Salazar
 */
public class InHouse extends Part {
    /**
     * This is the machine ID for the part
     */
    private int machineId;

    /**
     * The constructor for a new instance of an InHouse part.
     *
     * @param id the ID for the part
     * @param name the name of the part
     * @param price the price of the part
     * @param stock the inventory level of the part
     * @param min the minimum level for the part
     * @param max the maximum level for the part
     * @param machineId the machine ID for the part
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * The setter for the machineId
     *
     * @param machineId the machineId of the part
     */
     public void setMachineId(int machineId){
        this.machineId = machineId;
      }

    /**
     * The getter for the machineId
     *
     * @return machineId of the part
     */
     public int getMachineId() {
        return machineId;
     }

}
