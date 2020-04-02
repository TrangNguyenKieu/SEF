import java.text.DecimalFormat;

public class Property {
	private String address;
	private String suburb;
	private int bedrooms;
	private int bathrooms;
	private int carParkingSpace;
	private String type;
	private String status;
	private double weeklyRent;
	private Customer landlord;
	public Property(String address, String suburb, int bedrooms, int bathrooms,
			int carParkingSpace, String type, String status, double weeklyRent,
			Customer landlord) {
		super();
		this.address = address;
		this.suburb = suburb;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.carParkingSpace = carParkingSpace;
		this.type = type;
		this.status = status;
		this.weeklyRent = weeklyRent;
		this.landlord = landlord;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	public void setBathrooms(int bathrooms) {
		this.bathrooms = bathrooms;
	}

	public void setCarParkingSpace(int carParkingSpace) {
		this.carParkingSpace = carParkingSpace;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setWeeklyRent(double weeklyRent) {
		this.weeklyRent = weeklyRent;
	}

	public void setLandlord(Customer landlord) {
		this.landlord = landlord;
	}
	
	public String calculateBond() {
		DecimalFormat ft = new DecimalFormat("$#,###.##");
		double totalBond = weeklyRent * 4;
		
		return ft.format(totalBond);
	}

	public String displayPropertyInfo() {
		return "-- PROPERTY DETAILS --\n" +
				"Address:\t\t" + address + "\n" +
				"Suburb:\t\t" + suburb + "\n" +
				"Bedrooms:\t\t" + bedrooms + "\n" +
				"Bathrooms:\t\t" + bathrooms + "\n" +
				"Car parking Space:\t" + carParkingSpace + "\n" +
				"Type:\t\t" + type + "\n" +
				"Status:\t\t" + status + "\n" +
				"Weekly rent:\t\t" + weeklyRent + "\n";
	}	
	
}
