import java.util.ArrayList;

public class SalebyAuction extends SaleProperty {
	private static int count;
	private ArrayList<Auction> allAuctions;
	
	public SalebyAuction (String creatorID, String address, String des, String surbub, int bed, int bath, int cars, String type) {
		super(creatorID, address, des, surbub,bed,bath,cars,type);
		count++;
		super.setPropertyID("AUC"+String.format("%0" + 3 + "d", count));
		allAuctions= new ArrayList<Auction>();
	}
	

}
