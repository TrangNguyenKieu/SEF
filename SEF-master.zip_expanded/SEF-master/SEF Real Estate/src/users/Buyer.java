package users;
import java.io.Serializable;

public class Buyer extends Customer implements Serializable{
private static int count;

public Buyer() {
	count++;
}

}
