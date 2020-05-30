package users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import properties.Property;
import properties.RentalProperty;
import properties.SaleProperty;



public class BranchManager extends Employee implements Serializable{
    public BranchManager(String name, boolean isFulltime) {
        super(name, isFulltime);
    }

    public Property inspect(String id, ArrayList<Property> properties) throws Exception {
        boolean idExist = false;
        Iterator var5 = properties.iterator();

        while(var5.hasNext()) {
            Property property = (Property)var5.next();
            if (property.getPropertyID().equalsIgnoreCase(id)) {
                System.out.println(property.getPropertyID() + " has been inspected");
                property.setStatusToAvailable();
                return property;
            }
        }

        if (!idExist) {
            throw new Exception("Property not Found");
        } else {
            return null;
        }
    }

    public void assign(String employeeId, ArrayList<User> users, Property property) throws Exception {
        boolean idExist = false;
        Iterator var7 = users.iterator();

        while(var7.hasNext()) {
            User user = (User)var7.next();
            if (user instanceof Employee) {
                Employee emp = (Employee)user;
                if (((emp instanceof PropertyManager && property instanceof RentalProperty )||( emp instanceof SaleConsultant && property instanceof SaleProperty)) && emp.getId().equals(employeeId)) {
                    idExist = true;
                    property.setEmployee(emp);
                    System.out.println("Employee has been set to the property " + property.getPropertyID());
                    break;
                }
            }
        }

        if (!idExist) {
            throw new Exception("Employee does not exist or You are using the wrong employee for wrong property");
        }
    }
}
