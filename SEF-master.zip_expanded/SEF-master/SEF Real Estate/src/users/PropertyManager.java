package users;
import java.io.Serializable;
import Utilities.EmployeeType;

public class PropertyManager extends Employee implements Serializable {
    private static int count;


    public PropertyManager(String name, boolean isFulltime) {
        super(name, isFulltime);
        count++;
    }

}