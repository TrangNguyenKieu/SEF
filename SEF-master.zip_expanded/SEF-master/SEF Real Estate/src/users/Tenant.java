package users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Tenant extends Customer implements Serializable{
	private static int tenantCount;
	private String bankName;
	private int accountNumber;
	private int BSB;

	public Tenant() {
		tenantCount++;
		//super.setUserID("TEN" + String.format("%0" + 3 + "d", tenantCount));
	}
	
	
	// other methods
	
}
