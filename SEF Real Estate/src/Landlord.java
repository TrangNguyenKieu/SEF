import java.util.ArrayList;

public class Landlord extends Customer {
private static int landlordCount;


public Landlord() {
	landlordCount++;
	super.setUserID("LAD" + String.format("%0" + 3 + "d", landlordCount));
}


}
