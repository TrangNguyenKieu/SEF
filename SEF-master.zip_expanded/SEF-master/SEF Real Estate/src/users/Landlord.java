package users;

import java.io.Serializable;
import java.util.ArrayList;

public class Landlord extends Customer implements Serializable {
private static int landlordCount;
 

public Landlord() {
	landlordCount++;
	// super.setUserID("LAD" + String.format("%0" + 3 + "d", landlordCount));
}


}
