package properties;
import java.util.ArrayList;

import SystemExceptions.AmountException;
import Utilities.AuctionStatus;
import Utilities.DateTime;
import Utilities.PropertyStatus;
import Utilities.SaleType;
import Utilities.ValidateFunction;
import users.Buyer;
import users.Employee;
import users.SaleConsultant;

public class SaleProperty extends Property {

	private double propertyValue;
	private double commissionRate;
	private double minimumPrice;
	
	private ArrayList<Auction> allAuctions;
	private ArrayList<Offer> allOffers;
	private static int count;
	private SaleType saleType;
	
	//constructor for sale by auction
	public SaleProperty(String creatorID, String address, String des, String surbub, int bed, int bath, int cars, String type) {
		super(creatorID, address, des, surbub,bed,bath,cars,type);
		allAuctions= new ArrayList<Auction>();

		count++;
		this.setPropertyID("SAL"+String.format("%0" + 3 + "d", count));
		saleType=SaleType.AUCTION;
	}
	
	//constructor for sale by negotiation
	public SaleProperty(String creatorID, String address, String des, String surbub, int bed, int bath, int cars, String type, double min) {
		super(creatorID, address, des, surbub,bed,bath,cars,type);

		allOffers=new ArrayList<Offer>();
		count++;
		this.setPropertyID("SAL"+String.format("%0" + 3 + "d", count));
		this.minimumPrice=min;
		saleType=SaleType.NEGOTIATION;
	}
	
	public SaleType getSaleType() {
		return saleType;
	}
	public void setSaleByAuction() {
		this.saleType=SaleType.AUCTION;
	}
	
	public void setSaleByNego() {
		this.saleType=SaleType.NEGOTIATION;
	}
	public void setPropertyValue(double value) {
		this.propertyValue=value;
	}
	
	public void setCommisionRate(double rate) {
		this.commissionRate=rate;
	}
	
	public boolean assignEmployee(Employee emp) {
		if (super.getStatus()==PropertyStatus.Pending) {
			if(emp instanceof SaleConsultant) {
				
				super.setEmployee(emp);
				super.setStatusToAvailable();
				System.out.println("Successfully assign employee to the property");
						
				return true;
			} else {
				System.out.println("Cannot assign someone who is not a sale consultant to a sale property");
				return false;
			}
			
		}else return false;
		
	}
	
	//override
	public String getPropertyDetails() {
		return super.getPropertyDetails()+ "\n" + "Sale Type:" +"\t"+ this.saleType+"\n" + "Minimum Price:" +"\t"+ this.minimumPrice;
	}
	
	
	//sale by nego function
	public boolean createOffer(String prop, Buyer buyer, double offer) throws AmountException {
		if(offer<this.minimumPrice) throw new AmountException("Offer must be higher than minimum price of:" + this.minimumPrice);
		
		else {
			Offer newOffer = new Offer(prop, buyer, offer);
			allOffers.add(newOffer);
			System.out.println("Successfully added new offer:" + newOffer.getOfferID()+ " to property:"+ prop);
			return true;
			
		}

	}
	
	
	
	
	//sale by auction function 
	public ArrayList<Auction> getAllAuctions(){
		return allAuctions;
	}

	//can only create new auction if there's no currently opening or waiting auction
	public boolean auctionHistoryValid() {
		if(allAuctions.size()>0) {
			for(Auction auc: allAuctions) {
				//if there is any opening or waiting auction, cannot create new auction
				if (auc.getAuctionStatus()==AuctionStatus.OPENING
						|| auc.getAuctionStatus()==AuctionStatus.WAITING) {
					return false;
				}
//				 else if(auc.getAuctionStatus()==AuctionStatus.CLOSED && auc.getAuctionResult()==true) {
//					return false;
//				}
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
			DateTime aucDate = ValidateFunction.addDate();
			String title = "Add minimum reserve:";
			double reserve = ValidateFunction.addMonetaryInfo(title);

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
