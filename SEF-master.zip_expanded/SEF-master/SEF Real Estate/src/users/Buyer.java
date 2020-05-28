package users;

import java.io.Serializable;

public class Buyer extends Customer implements Serializable{
private static int count;

public Buyer() {
	count++;
	//super.setUserID("BUY" + String.format("%0" + 3 + "d", count));
}

}
