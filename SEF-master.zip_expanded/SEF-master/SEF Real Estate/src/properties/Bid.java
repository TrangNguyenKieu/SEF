package properties;

import Utilities.BidStatus;
import Utilities.DateTime;

public class Bid {
String iD;
String creatorID;
DateTime bidDate;
double bidAmount;
BidStatus status;
int count;

public Bid(String creatorID,DateTime date,double amount) {
	this.iD="BID"+String.format("%0" + 3 + "d", count);
	this.creatorID=creatorID;
	this.bidDate=date;
	this.bidAmount=amount;
	this.status=BidStatus.PENDING;
}
}
