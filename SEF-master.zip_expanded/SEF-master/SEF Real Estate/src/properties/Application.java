package properties;
import Utilities.ApplicationStatus;
import Utilities.DateTime;
import startUp.RealEstate;
import users.Tenant;

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
	boolean bondPaid;

	private DateTime submittedDate;
	private DateTime acceptedDate;

	public Application(String propID, Tenant tenant, double income, String occu,
			double rent, String duration) {
		idCount++;
		this.applicationID = "APP" + String.format("%0" + 3 + "d", idCount);
		this.propertyID = propID;
		this.tenant = tenant;
		this.income = income;
		this.occupation = occu;
		this.weeklyRent = rent;
		this.contractDuration = duration;
		appStatus = ApplicationStatus.Pending;
		submittedDate = new DateTime(RealEstate.time);
		bondPaid=false;
	}
	// general methods
	public String getApplicationdetails() {
		String details = null;
		details = "\n" + "Application ID: " + "\t" + this.applicationID + "\n"
				+ "Property ID: " + "\t" + "\t" + this.propertyID + "\n"
				+ "Tenant ID: " + "\t" + "\t" + this.tenant.getUserID() + "\n"
				+ "Monthly Income: " + "\t" + this.income + "\n"
				+ "Occupation: " + "\t" + "\t" + this.occupation + "\n"
				+ "Weekly Rent: " + "\t" + "\t" + this.weeklyRent + "\n"
				+ "Contract Duration: " + "\t" + this.contractDuration + "\n"
				+ "Application Status " + "\t" + this.appStatus + "\n"
				+ "Submitted Date " + "\t" + "\t" + this.submittedDate;

		if (this.appStatus == ApplicationStatus.Accepted) {
			details = "\n" + "Application ID: " + "\t" + this.applicationID
					+ "\n" + "Property ID: " + "\t" + "\t" + this.propertyID
					+ "\n" + "Tenant ID: " + "\t" + "\t"
					+ this.tenant.getUserID() + "\n" + "Monthly Income: " + "\t"
					+ this.income + "\n" + "Occupation: " + "\t" + "\t"
					+ this.occupation + "\n" + "Weekly Rent: " + "\t" + "\t"
					+ this.weeklyRent + "\n" + "Contract Duration: " + "\t"
					+ this.contractDuration + "\n" + "Application Status "
					+ "\t" + this.appStatus + "\n" + "Submitted Date " + "\t"
					+ "\t" + this.submittedDate + "\n" + "Accepted Date " + "\t"
					+ "\t" + this.acceptedDate;
		}
		return details;

	}

	
	public void checkAppStatus(Property prop) {
		// application must be responded within 3 days after submitted date
		if (this.getAppStatus() == ApplicationStatus.Pending) {
			if (DateTime.diffHours(RealEstate.currentDate, this.getSummittedDate()) > 71) {
				this.rejectApp();
				System.out.println("Application: " + this.getApplicationID()
						+ " has been rejected due to non-response from landlord for more than 3 days");
			}
			// bond payment must be made within 24 hours after accepted date
		} else if (this.getAppStatus() == ApplicationStatus.Accepted) {
			if (DateTime.diffHours(RealEstate.currentDate, this.getAcceptedDate()) > 23
					&& this.getBondPaymentStatus() == false) {
				this.rejectApp();
				System.out.println("Application: " + this.getApplicationID()
						+ " has been rejected due to failing to pay bond within 24 hours");

				// set property status back to available
				prop.setStatusToAvailable();
				System.out.println("Property:"+ prop.getPropertyID()+ " status is back to available");
			}
		}
		
		
	}
	// accessor/mutator
	public Tenant getTenant() {
		return this.tenant;
	}
	public DateTime getSummittedDate() {
		return submittedDate;
	}

	public DateTime getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(DateTime date) {
		this.acceptedDate = date;
	}

	public void rejectApp() {
		this.appStatus = ApplicationStatus.Rejected;
	}

	public void acceptApp() {
		this.appStatus = ApplicationStatus.Accepted;
		this.acceptedDate = new DateTime(RealEstate.time);
	}
	public ApplicationStatus getAppStatus() {
		return this.appStatus;
	}
	public String getApplicationID() {
		return this.applicationID;
	}

	public String getPropertyID() {
		return this.propertyID;
	}
	
	public boolean getBondPaymentStatus() {
		return bondPaid;
	}
}
