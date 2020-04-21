package users;

public class Vendor extends Customer{
private static int count;

public Vendor() {
	count++;
	super.setUserID("VEND" + String.format("%0" + 3 + "d", count));
}
	
}
