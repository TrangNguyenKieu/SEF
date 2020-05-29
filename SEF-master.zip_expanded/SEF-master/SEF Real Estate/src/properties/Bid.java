package properties;

import java.util.ArrayList;

import Utilities.BidStatus;
import Utilities.DateTime;
import startUp.RealEstate;
import startUp.StartUp;
import users.User;

public class Bid {
private String iD;
private String creatorID;
private DateTime bidDate;
private double bidAmount;
private BidStatus status;
private static int count;
private boolean depositedPayment;
private DateTime acceptedDate;

public Bid(String creatorID,DateTime date,double amount) {
	count++;
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
	return depositedPayment;
}

public double getAmount() {
	return this.bidAmount;
}

public BidStatus getStatus() {
	return status;
}

public void acceptBid(DateTime date) {
	this.status=BidStatus.ACCEPTED;
	this.acceptedDate=date;
}

public void rejectBid() {
	this.status=BidStatus.REJECTED;
}

public String getBidID() {
	return this.iD;
}

public void receivedDeposit() {
	this.depositedPayment=true;
	System.out.println("Deposit has been received for bid:" + getBidID()+".Deposited Payment: "+ depositedPayment);
}

public String getCreatorID() {
	return this.creatorID;
}

public String getBidDetails() {
	return "Bid ID:" + "\t"+ this.iD+ "\n"+
			"Bidder name:"+ "\t"+ getCreatorName()+ "\n"+
			"Bid amount:"+ "\t"+ this.bidAmount+ "\n"+
			"Accepted date:"+"\t"+ this.acceptedDate+ "\n"+
			"Deposit Status"+"\t"+ this.depositedPayment+ "\n";
}

public String getCreatorName() {
	String creatorName=null;
	ArrayList<User> allUsers= StartUp.re.getAllUsers();
	
	for(User user: allUsers) {
		if(user.getUserID().compareTo(creatorID)==0) {
			creatorName=user.getUsername();
		}
	}
	return creatorName;
}
}
