package users;

public class SaleConsultant extends Employee {
private static int count;
public SaleConsultant(String name, boolean fulltime) {
	super(name,fulltime);
	count++;
	//super.setUserID("SCON" + String.format("%0" + 3 + "d", count));
}
}
