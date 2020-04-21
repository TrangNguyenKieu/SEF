package users;
public class Tenant extends Customer {
	private static int tenantCount;
	private String bankName;
	private int accountNumber;
	private int BSB;

	public Tenant() {
		tenantCount++;
		super.setUserID("TEN" + String.format("%0" + 3 + "d", tenantCount));
	}
	
	
	// other methods
	
}
