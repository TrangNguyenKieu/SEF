package users;
import java.io.Serializable;
import java.util.ArrayList;

public class Vendor extends Customer implements Serializable{
private static int count;

public Vendor() {
	count++;
	//super.setUserID("VEND" + String.format("%0" + 3 + "d", count));
}
	
}
