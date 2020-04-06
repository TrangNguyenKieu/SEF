import java.util.ArrayList;

public class Tenant extends Customer {
private static int tenantCount;


public Tenant() {
	tenantCount++;
	super.setUserID("TEN" + String.format("%0" + 3 + "d", tenantCount));
}
//other methods


//accesors/mutators

}
