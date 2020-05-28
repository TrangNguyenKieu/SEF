package users;
import java.io.Serializable;
import java.util.ArrayList;

import Utilities.EmployeeType;
import properties.Property;

public class BranchManager extends Employee implements Serializable{

	public BranchManager(String name, boolean isFulltime) {
		super(name, isFulltime);
		// TODO Auto-generated constructor stub
	}

	public Property inspect(String id, ArrayList<Property> properties) throws Exception {
		boolean idExist = false;
		for (Property property : properties) {
			if (property.getPropertyID().equalsIgnoreCase(id)) {
				System.out.println(property.getPropertyID()+" has been inspected");
				property.setStatusToAvailable();
				return property;
			} 
		}
		if(!idExist)
		{
			throw new Exception("Property not Found");
		}
		return null;
	}
	
	public void assign(String employeeId,ArrayList<User> users,Property property) throws Exception
	{
		boolean idExist = false;
		for (User user:users)
		{
			if(user instanceof PropertyManager && user.getUserID().equalsIgnoreCase(employeeId))
			{
				 idExist = true;
				 property.setEmployee((Employee)user);
				 System.out.println("Employee has been set to the property "+property.getPropertyID());
				 break;
			}
		}
		if(!idExist)
		{
			throw new Exception("Employee does not exist");
		}
		
	}

}
