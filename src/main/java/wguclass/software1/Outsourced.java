package wguclass.software1;

public class Outsourced extends Part{
    private static String companyName;
    public  Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);

        this.companyName = companyName;
    }

        public void setCompanyName(String companyName){
        this.companyName = companyName;
        }
        public static String getCompanyName() {
            return companyName;
        }
    }

