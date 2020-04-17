
public class SaleConsultant extends Employee {
private static int count;
public SaleConsultant() {
	count++;
	super.setUserID("SCON" + String.format("%0" + 3 + "d", count));
}
}
