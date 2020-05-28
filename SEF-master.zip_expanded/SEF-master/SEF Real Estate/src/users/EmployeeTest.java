package users;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import properties.Property;
import properties.RentalProperty;

class EmployeeTest implements Serializable{
	
	

	@Test
	void assignEmployeeToProperty() throws Exception {
		ArrayList<User> users=new ArrayList<User>();

		List<Property> props=new ArrayList<Property>(); 
		RentalProperty prop01= new RentalProperty("LAN001", "120 Abbey road", "This house is not for sale", "Corney Island", 4, 2, 2, "house", 180, "12 months or more");
		props.add(prop01);
		
		BranchManager branman= new BranchManager("BranchManager",true);
		PropertyManager proman= new PropertyManager("PropertyManager",true);
		
		users.add(branman);
		users.add(proman); 
		
		branman.assign("EMP002", users, prop01);
		assertEquals("EMP002",prop01.getEmployee().getUserID());// positive case
		
		branman.assign("EMP002", users, prop01);
		assertNotEquals("EMP001",prop01.getEmployee().getUserID());// negative case
		
		try {
			branman.assign("EMP009", users, prop01);
		}
		catch(Exception e)
		{
			assertEquals("Employee does not exist",e.getMessage());// exceptional case 
		}
		
		
	}

}
