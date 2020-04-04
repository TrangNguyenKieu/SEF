
public class Application {
private String applicationID;
private static int idCount;
private String propertyID;
private Tenant tenant;
private double income;
private String occupation;
private double weeklyRent;
private String contractDuration;
private ApplicationStatus appStatus;

private DateTime submittedDate;
private DateTime acceptedDate;

public Application(String propID, Tenant tenant, double income, String occu, double rent, String duration) {
	this.applicationID= "APP" +String.format("%0" + 3 + "d", idCount);
	this.propertyID=propID;
	this.tenant=tenant;
	this.income=income;
	this.occupation=occu;
	this.weeklyRent=rent;
	this.contractDuration=duration;
	appStatus=ApplicationStatus.Pending;
	submittedDate=new DateTime();
}

}
