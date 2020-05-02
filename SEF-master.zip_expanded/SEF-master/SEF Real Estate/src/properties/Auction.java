package properties;
import java.util.ArrayList;

import SystemExceptions.AmountException;
import Utilities.*;

public class Auction {
private String propID;
private String ID;
private static int count;
private DateTime auctionDate;
private double reserve;
private boolean auctionSuccess;
private AuctionStatus status;
private DateTime lastUpdate;
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
//other methods
public String getAuctionDetails() {
	String details= "\n" + "Auction ID:" +"\t"+ this.ID+
			"\n" + "Property ID:" +"\t"+ this.propID+
			"\n" + "Auction Date:" +"\t"+ this.auctionDate+
			"\n" + "Reserve amount:" +"\t"+ this.reserve+
			"\n" + "Status:" +"\t"+ this.status+
			"\n" + "Auction Success? " +"\t"+ this.auctionSuccess+"\n";
	return details;
}


public void makeBid(String creatorID, DateTime date)  {
	boolean ok=false;
	
	System.out.println("Minimum Reserve: "+ this.reserve);
	while(!ok) {
		try {
			String title="Enter bid amount:";
			double amount= ValidateFunction.addMonetaryInfo(title);
			
			if (amount<= this.reserve) {
				throw new AmountException("Bid amount must be higher than minimum reserve");
			}
			else {
				Bid bid= new Bid(creatorID, date, amount);
				setLastUpdate(date);
				this.allBids.add(bid);
				
				System.out.println("Succesfully added bid");
				ok=true;
			}
		} catch (AmountException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getReason());
		}
	}
	
	
	
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

public DateTime getLastUpdate() {
	return lastUpdate;
}

public void setLastUpdate(DateTime date) {
	this.lastUpdate=date;
}
}
