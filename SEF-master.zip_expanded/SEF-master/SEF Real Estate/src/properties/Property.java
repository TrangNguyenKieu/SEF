package properties;

import Utilities.PropertyStatus;
import users.Employee;

public abstract class Property {
	private String propertyID;
	private String creatorID;
	private String address;
	private String description;
	private String surbub;
	private int bedrooms;
	private int bathrooms;
	private int carSpaces;

	private String type;
	private PropertyStatus status;
	private Employee employee;

	public Property(String creatorID, String address, String des, String surbub, int bed, int bath, int cars,
			String type) {
		this.creatorID = creatorID;
		this.address = address;
		this.surbub = surbub;
		this.description = des;
		this.bedrooms = bed;
		this.bathrooms = bath;
		this.carSpaces = cars;
		this.type = type;
		this.status = PropertyStatus.Pending;
	}

	// override methods:
	public String getPropertyDetails() {
		String details = "\n" + "Property ID:" + "\t" + this.propertyID + "\n" + "Creator ID:" + "\t" + this.creatorID
				+ "\n" + "Address:" + "\t" + this.address + "\n" + "Suburb:" + "\t" + this.surbub + "\n"
				+ "Description:" + "\t" + this.description + "\n" + "Number of Bedrooms:" + "\t" + this.bedrooms + "\n"
				+ "Number of Bathrooms:" + "\t" + this.bathrooms + "\n" + "Number of car spaces:" + "\t"
				+ this.carSpaces + "\n" + "Property type:" + "\t" + this.type + "\n" + "Property Status:" + "\t"
				+ this.status;

		return details;
	}

	// abstract method
	public abstract boolean assignEmployee(Employee emp);

	// accessors/mutators
	public String getPropertyID() {
		return propertyID;
	}

	public void setPropertyID(String ID) {
		this.propertyID = ID;
	}

	public String getCreatorID() {
		return creatorID;
	}

	public String getSurbub() {
		return surbub;
	}

	public PropertyStatus getStatus() {
		return status;
	}

	public void setStatusToAvailable() {
		this.status = PropertyStatus.Available;
	}

	public void setStatusToLet() {
		this.status = PropertyStatus.Let;
	}

	public void setStatusToInprocess() {
		this.status = PropertyStatus.InProcess;
	}

	public void setStatusToSold() {
		this.status = PropertyStatus.Sold;
	}

	public void setStatusToUnderContract() {
		this.status = PropertyStatus.UnderContract;
	}

	public void setEmployee(Employee emp) {
		this.employee = emp;
	}

	public Employee getEmployee() {
		return employee;
	}

}