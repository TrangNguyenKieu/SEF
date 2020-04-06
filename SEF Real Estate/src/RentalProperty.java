import java.util.ArrayList;

public class RentalProperty extends Property{
private static int rentalPropertyCount;
private double weeklyRent;
private String contractDuration;
private String tenantID;
private ArrayList<Application> allApplications=new ArrayList<Application>();

public RentalProperty(String creatorID, String address, String des, String surbub, int bed, int bath, int cars, String type,double weeklyRent, String duration) {
	super(creatorID, address, des, surbub, bed,bath,cars, type);
	rentalPropertyCount++;
	super.setPropertyID("RENT"+String.format("%0" + 3 + "d", rentalPropertyCount));
	this.weeklyRent=weeklyRent;
	this.contractDuration=duration;
	tenantID=null; //will be added if succesfully pay bond and rent
}

public String getPropertyDetails() {
	
	
	if(super.getStatus()==PropertyStatus.UnderContract) {
		return super.getPropertyDetails() +
				"\n" + "Weekly Rent:" +"\t"+ this.weeklyRent+
				"\n" + "Contract Duratioin:" +"\t"+ this.contractDuration+
				"\n" + "Tenant ID:" +"\t"+ this.tenantID;
				
	} else {
		return super.getPropertyDetails() +
				"\n" + "Weekly Rent:" +"\t"+ this.weeklyRent+
				"\n" + "Contract Duratioin:" +"\t"+ this.contractDuration;
	}
}

public boolean handleApplication(Application app) {

	if (super.getStatus()==PropertyStatus.Available) {
//		if(!allApplications.contains(app)) {
			allApplications.add(app);
			return true;
//		} else {
//			System.out.println("You have already submitted your application for this property");
//			return false;
//		}
		
	} else {
		return false;
	}
}

//accessor/mutators
public ArrayList<Application> getAllApplications(){
	return allApplications;
}
}
