package properties;

import Utilities.BidStatus;
import Utilities.DateTime;
import startUp.RealEstate;

public class Bid {
private String iD;
private String creatorID;
private DateTime bidDate;
private double bidAmount;
private BidStatus status;
private int count;
private boolean depositedPayment;
private DateTime acceptedDate;

public Bid(String creatorID,DateTime date,double amount) {
	this.iD="BID"+String.format("%0" + 3 + "d", count);
	this.creatorID=creatorID;
	this.bidDate=date;
	this.bidAmount=amount;
	this.status=BidStatus.PENDING;
	depositedPayment=false;
}

public DateTime getAcceptedDate() {
	return acceptedDate;
}

public boolean getDepositedPaymentStatus() {
	return false;
}

public double getAmount() {
	return this.bidAmount;
}

public BidStatus getStatus() {
	return status;
}

public void acceptBid() {
	this.status=BidStatus.ACCEPTED;
}

public void rejectBid() {
	this.status=BidStatus.REJECTED;
}

}
