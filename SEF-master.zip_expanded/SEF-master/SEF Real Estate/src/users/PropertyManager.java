package users;
import Utilities.EmployeeType;

public class PropertyManager extends Employee{
private static int count;


	public PropertyManager(String name, boolean isFulltime) {
		super(name, isFulltime);
  count++;
	//super.setUserID("PROM" + String.format("%0" + 3 + "d", count));
		// TODO Auto-generated constructor stub
	}

}
