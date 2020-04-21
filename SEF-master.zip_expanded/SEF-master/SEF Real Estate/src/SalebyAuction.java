import java.util.ArrayList;

import Utilities.AuctionStatus;
import Utilities.DateTime;

public class SalebyAuction extends SaleProperty {
	private static int count;
	private ArrayList<Auction> allAuctions;
	
	public SalebyAuction (String creatorID, String address, String des, String surbub, int bed, int bath, int cars, String type) {
		super(creatorID, address, des, surbub,bed,bath,cars,type);
		count++;
		super.setPropertyID("AUC"+String.format("%0" + 3 + "d", count));
		allAuctions= new ArrayList<Auction>();
	}
	
public ArrayList<Auction> getAllAuctions(){
	return allAuctions;
}

//can only create new auction if there's no currently opening or waiting auction
public boolean auctionHistoryValid() {
	if(allAuctions.size()>0) {
		for(Auction auc: allAuctions) {
			if (auc.getAuctionStatus()==AuctionStatus.OPENING
					|| auc.getAuctionStatus()==AuctionStatus.WAITING) return false;
		}
		return true;
	} else return true;
}

public boolean handleAuction(Auction auc) {
	if(super.getPropertyID().compareTo(auc.getPropID())==0) {
		allAuctions.add(auc);
		return true;
	} else return false;
}


public boolean createAuction() {
	
	boolean auctionOk=false;
	if(auctionHistoryValid()) {
		DateTime aucDate = RealEstate.addDate();
		String title = "Add minimum reserve:";
		double reserve = RealEstate.addMonetaryInfo(title);

		Auction auction = new Auction(this.getPropertyID(), aucDate, reserve);
		if(handleAuction(auction)) {
			System.out.println("Succesfully added new auction to property:" + this.getPropertyID());
			auctionOk=true;
			return auctionOk;
		} else
			System.out.println("Cannot add new auction to property");
			return auctionOk;
		} else {
			System.out.println("there's currently opening/wating auctions");
			return auctionOk;
		}
		
	}


	
}
