
public class PropertyManager extends Employee{
private static int count;

public PropertyManager() {
	count++;
	super.setUserID("PROM" + String.format("%0" + 3 + "d", count));
}
}
