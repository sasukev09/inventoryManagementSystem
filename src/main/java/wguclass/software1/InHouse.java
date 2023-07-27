package wguclass.software1;
/**
 * This class models an In-house part.
 */

/**
 * @author Kevin Salazar
 */
public class InHouse extends Part {
    /**
     * This class models an In-house part.
     *
     * @author Kevin Salazar
     */
    private int machineId;
    /**
     * This is the machine ID for the part
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {

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
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
     public void setMachineId(int machineId){
         /**
          * The setter for the machineId
          *
          * @param machineId the machineId of the part
          */

        this.machineId = machineId;
      }
     public int getMachineId() {
         /**
          * The getter for the machineId
          *
          * @return machineId of the part
          */
        return machineId;
     }

}
