package properties;
import java.util.ArrayList;

import Utilities.*;

public class Auction {
private String propID;
private String ID;
private static int count;
private DateTime auctionDate;
private double reserve;
private boolean auctionSuccess;
private AuctionStatus status;
private ArrayList<Bid> allBids;

public Auction(String propID,DateTime date, double amt) {
	this.propID=propID;
	count++;
	this.ID="AF"+String.format("%0" + 3 + "d", count);
	this.auctionDate=date;
	this.reserve=amt;
	auctionSuccess=false;
	status=AuctionStatus.WAITING;
	allBids= new ArrayList<Bid>();
}


//accessors/mutators
public String getAuctionID() {
	return this.ID;
}
public double getAuctionReserve() {
	return this.reserve;
}
public AuctionStatus getAuctionStatus() {
	return status;
}

public DateTime getAuctionDate() {
	return auctionDate;
}

public void setAuctionDate(DateTime date) {
	this.auctionDate=date;
}

public boolean getAuctionResult() {
	return auctionSuccess;
}
public void openAuction() {
	this.status=AuctionStatus.OPENING;
}

public void closeAuction() {
	this.status=AuctionStatus.CLOSED;
}

public void setAuctionResultToSuccess() {
	this.auctionSuccess=true;
}

public ArrayList<Bid> getAllBids (){
	return allBids;
}

public String getPropID() {
	return this.propID;
}
}
