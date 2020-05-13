package users;

public class Buyer extends Customer {
private static int count;

public Buyer() {
	count++;
	//super.setUserID("BUY" + String.format("%0" + 3 + "d", count));
}

}
