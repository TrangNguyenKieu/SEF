import java.util.ArrayList;

public class SalebyAuction extends SaleProperty {

	private ArrayList<Auction> allAuctions;
	
	public SalebyAuction (String creatorID, String address, String des, String surbub, int bed, int bath, int cars, String type) {
		super(creatorID, address, des, surbub,bed,bath,cars,type);
		allAuctions= new ArrayList<Auction>();
	}
	
	public boolean assignEmployee(Employee emp) {
		return true;
	};
}
