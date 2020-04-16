import java.util.ArrayList;

public class SalebyNegotiation extends SaleProperty {
	private double minimumPrice;
	private ArrayList<Offer> allOffers= new ArrayList<Offer>();
	
	public SalebyNegotiation(String creatorID, String address, String des, String surbub, int bed, int bath, int cars, String type, double price) {
		super(creatorID, address, des, surbub,bed,bath,cars,type);
		minimumPrice=price;
	}

	public boolean assignEmployee(Employee emp) {
		return true;
	};
}
