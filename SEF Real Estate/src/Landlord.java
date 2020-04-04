import java.util.ArrayList;

public class Landlord extends Customer {
private static int landlordCount;
private ArrayList<Property> allProperties= new ArrayList<Property>();

public Landlord() {
	landlordCount++;
	super.setUserID("LAD" + String.format("%0" + 3 + "d", landlordCount));
}
}
