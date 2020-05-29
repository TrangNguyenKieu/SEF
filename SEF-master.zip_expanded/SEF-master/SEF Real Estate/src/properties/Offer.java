package properties;
import Utilities.ApplicationStatus;
import Utilities.DateTime;
import startUp.RealEstate;
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
	private DateTime submittedDate;
	
	public Offer(String prop,Buyer buyer,double offer) {
		idCount++;
		this.offerID= "OFF"+ String.format("%0" + 3 + "d", idCount);
		this.propertyID=prop;
		this.buyer=buyer;
		this.offerPrice=offer;
		status=ApplicationStatus.Pending;
		submittedDate = new DateTime(RealEstate.time);
		
	}
	
	public void checkOfferStatus(Property prop) {
		// offer must be responded within 3 days after submitted date
				if (this.status == ApplicationStatus.Pending) {
					if (DateTime.diffHours(RealEstate.currentDate, this.submittedDate) > 71) {
						this.rejectOffer();
						System.out.println("Offer: " + this.offerID
								+ " has been rejected due to non-response from vendor for more than 3 days");
					}
					// deposit payment must be made within 24 hours after accepted date
				} else if (this.status == ApplicationStatus.Accepted) {
					if (DateTime.diffHours(RealEstate.currentDate, this.acceptedDate) > 23
							&& this.depositPaid == false) {
						this.rejectOffer();
						System.out.println("Offer " + this.offerID
								+ " has been rejected due to failing to pay deposit within 24 hours");

						// set property status back to available
						prop.setStatusToAvailable();
						System.out.println("Property:"+ prop.getPropertyID()+ " status is back to available");
					}
				}
	}
	
	public String getOfferDetails() {
		return "Offer Id:" + "\t"+ this.offerID+"\n"+
				"PropertyID" + "\t"+ this.propertyID+ "\n"+
				"Buyer name:"+"\t"+this.buyer.getUsername()+"\n"+
				"Offer Price:"+ "\t"+this.offerPrice+ "\n"+
				"Status:" +"\t"+this.status+"\n"+
				"Submitted Date:" +"\t"+this.submittedDate+"\n"+
				"Accepted Date:" +"\t"+this.acceptedDate+"\n"+
				"Deposit Paid? "+ "\t"+this.depositPaid+"\n";
	}
	//accessor/mutators
	public String getOfferID() {
		return this.offerID;
	}
	
	public String getBuyerID() {
		return this.buyer.getUserID();
	}
	
	public ApplicationStatus getOfferStatus() {
		return status;
	}
	
	public void rejectOffer() {
		this.status=ApplicationStatus.Rejected;
	}
	
	public void acceptOffer(DateTime date) {
		this.status=ApplicationStatus.Accepted;
		this.acceptedDate=date;
	}
	
	public double getOfferAmount() {
		return this.offerPrice;
	}
	
	public void setOfferAmount(double amnt) {
		offerPrice=amnt;
	}
	
	public void receivedDeposit() {
		this.depositPaid=true;
	}
}
