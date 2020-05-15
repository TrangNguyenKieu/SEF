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
	
	public String getOfferDetails() {
		return "Offer Id:" + "\t"+ this.offerID+"\n"+
				"Buyer Id:"+"\t"+this.buyer.getUserID()+"\n"+
				"Offer Price:"+ "\t"+this.offerPrice+ "\n"+
				"Status:" +"\t"+this.status+"\n"+
				"Accepted Date:" +"\t"+this.acceptedDate+"\n"+
				"Deposit Paid? "+ "\t"+this.depositPaid;
	}
	//accessor/mutators
	public String getOfferID() {
		return this.offerID;
	}
	
	public ApplicationStatus getOfferStatus() {
		return status;
	}
	
	public void rejectOffer() {
		this.status=ApplicationStatus.Rejected;
	}
	
	public void acceptOffer() {
		this.status=ApplicationStatus.Accepted;
	}
}
