package wguclass.software1;
/**
 * This Outsourced class models an outsourced part.
 */

/**
 * @author Kevin Salazar
 */
public class Outsourced extends Part{
    private  String companyName;
    /**
     * The company name for the part
     */
    public  Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);

        /**
         * The constructor for a new instance of an Outsourced part.
         *
         * @param id the ID for the part
         * @param name the name of the part
         * @param price the price of the part
         * @param stock the inventory level of the part
         * @param min the minimum level for the part
         * @param max the maximum level for the part
         * @param companyName the company name for the part
         */
        this.companyName = companyName;
    }

        public void setCompanyName(String companyName){
            /**
             * The setter for the companyName
             *
             * @param companyName sets the Company name for the part.
             */
        this.companyName = companyName;
        }

        public  String getCompanyName() {
        /**
         * The getter for the companyName
         *
         * @return Company name gets returned.
         */
        return companyName;
        }
    }

