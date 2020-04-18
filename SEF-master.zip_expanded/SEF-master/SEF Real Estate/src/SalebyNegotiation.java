import java.util.ArrayList;

public class SalebyNegotiation extends SaleProperty {
	private double minimumPrice;
	private static int count;
	private ArrayList<Offer> allOffers;
	
	public SalebyNegotiation(String creatorID, String address, String des, String surbub, int bed, int bath, int cars, String type, double price) {
		super(creatorID, address, des, surbub,bed,bath,cars,type);
		count++;
		super.setPropertyID("NEG"+String.format("%0" + 3 + "d", count));
		minimumPrice=price;
		allOffers= new ArrayList<Offer>();
	}


}
