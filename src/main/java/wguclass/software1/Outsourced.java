package wguclass.software1;
/**
 * This class models an outsourced part.
 *
 *Including the unique constructor "company name" is included in this class, along with its setters and getters.
 *
 * @author Kevin Salazar
 */
public class Outsourced extends Part{

    /**
     * The company name for the part
     */
    private  String companyName;

    /**
     * The constructor for the outsourced type of part.
     *
     * @param id part id
     * @param name part name
     * @param price part price
     * @param stock part inventory
     * @param min part min
     * @param max part max
     * @param companyName company name for part
     */
    public  Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * The setter for the companyName
     *
     * @param companyName sets the Company name for the part.
     */
        public void setCompanyName(String companyName){
        this.companyName = companyName;
        }

    /**
     * The getter for the companyName
     *
     * @return Company name gets returned.
     */
        public  String getCompanyName() {
        return companyName;
        }
    }

