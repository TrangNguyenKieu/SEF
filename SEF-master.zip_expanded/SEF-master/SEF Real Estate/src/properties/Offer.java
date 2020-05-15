package properties;
import Utilities.ApplicationStatus;
import Utilities.DateTime;
import users.Buyer;

public class Offer {
	private String offerID;
	private static int idCount;
	private String propertyID;
	private Buyer buyer;
	private double offerPrice;
	private boolean depositPaid;
	private ApplicationStatus status;
	private DateTime acceptedDate;
	
	public Offer(String prop,Buyer buyer,double offer) {
		idCount++;
		this.offerID= "OFF"+ String.format("%0" + 3 + "d", idCount);
		this.propertyID=prop;
		this.buyer=buyer;
		this.offerPrice=offer;
		status=ApplicationStatus.Pending;
		
	}
	
	//accessor/mutators
	public String getOfferID() {
		return this.offerID;
	}
}
