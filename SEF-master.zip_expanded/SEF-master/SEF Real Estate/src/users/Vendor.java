package users;
import java.io.Serializable;

public class Vendor extends Customer implements Serializable{
private static int count;

public Vendor() {
	count++;
}
	
}
