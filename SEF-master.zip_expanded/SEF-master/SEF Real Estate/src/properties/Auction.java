package properties;
import java.util.ArrayList;

import SystemExceptions.AmountException;
import Utilities.*;
import startUp.RealEstate;

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
private double maxBidAmount;
private Bid highestBid;

public Auction(String propID,DateTime date, double amt) {
	this.propID=propID;
	count++;
	this.ID="AF"+String.format("%0" + 3 + "d", count);
	this.auctionDate=date;
	this.reserve=amt;
	auctionSuccess=false;
	status=AuctionStatus.WAITING;
	allBids= new ArrayList<Bid>();
	maxBidAmount=0;
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

public void validateBid(double amount) throws AmountException {
	if(allBids.size()>0) {
	
		if(amount<= maxBidAmount) 
			throw new AmountException("Bid must be higher than " + maxBidAmount+reserve);
			
	}else {
		if (amount<= this.reserve) {
			throw new AmountException("Bid amount must be higher than minimum reserve");
		}
	}
}

public void makeBid(String creatorID, DateTime date)  {
	boolean ok=false;
	
	System.out.println("Minimum Reserve: "+ this.reserve);
	while(!ok) {
		try {
			String title="Enter bid amount:";
			double amount= ValidateFunction.addMonetaryInfo(title);
			validateBid(amount);
			maxBidAmount=amount;
			
				Bid bid= new Bid(creatorID, date, amount);
				setLastUpdate(date);
				this.allBids.add(bid);
				
				System.out.println("Succesfully added bid");
				ok=true;
//			}
		} catch (AmountException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getReason());
		}
	}
	
	
	
}

public void checkAuctionStatus() {
	if(status==AuctionStatus.WAITING) {
		if(DateTime.diffHours( this.auctionDate ,RealEstate.currentDate) <=0) {
		//auction will be opened when auction date comes
			
			this.openAuction();
			System.out.println("Auction "+ this.ID+ " has been opened");
		}
	} else if(status==AuctionStatus.OPENING && lastUpdate!= null) {
		//if no new bid after 30 seconds, auction will be closed
		
		if(DateTime.diffSecond(RealEstate.currentDate, lastUpdate) >30) {
			this.closeAuction();
			System.out.println("Auction "+ this.ID+ " has been closed");
		}
	}
}

public void checkHighestBidStatus() {
	int i = allBids.size()-1;
	highestBid=allBids.get(i);
	
	if(highestBid.getStatus()==BidStatus.PENDING) {
		highestBid.acceptBid();
		
	} else if(highestBid.getStatus()==BidStatus.ACCEPTED) {
		if(DateTime.diffHours(RealEstate.currentDate, highestBid.getAcceptedDate()) >23) {
			//continue writing.........
			
			
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
