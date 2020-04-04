import java.util.ArrayList;
import java.util.Scanner;
import SystemExceptions.*;
public class RealEstate {
	
	User currentUser;
	
	private int choice;
	private Scanner scan = new Scanner(System.in);
	private boolean quit; // quit the system
	private boolean logOut;
	private int currentIndex; //index of current session user in allUsers array
	
	private String address;
	private String description;
	private String surbub;
	private int bed;
	private int bath;
	private int cars;
	private String type;
	private double weeklyRent;
	private String duration;
	
	ArrayList<User> allUsers= new ArrayList<User>();
	ArrayList<Property> allProperties=new ArrayList<Property>();
	
	public void landingPageMenu() {
		quit = false;
		while (!quit) {

			System.out.println("*******Real Estate System******");
			System.out.printf("1. Log in");
			System.out.println();
			System.out.printf("2. Register");
			System.out.println();
			System.out.printf("3. Quit");
			System.out.println();

			enterChoice();

			switch (choice) {
			case 1:
				login();
				break;
			case 2:
				register();
				break;
				
			case 3:
				quit();
				break;

			default:
				System.out.println("No such operation");
				break;
			}
		}
	}
	
	
	
	public void landLordMenu() {
		logOut = false;
		while (!logOut) {

			System.out.println("*******Landlord Menu******");
			System.out.printf("1. Display all properties");
			System.out.println();
			System.out.printf("2. Display my properties");
			System.out.println();
			System.out.printf("3. Add property");
			System.out.println();
			System.out.printf("4. Display applications");
			System.out.println();
			System.out.printf("5. Respond to application");
			System.out.println();
			System.out.printf("6. Log Out");
			System.out.println();

			enterChoice();

			switch (choice) {
			case 1:
				displayAllProperties();
				break;
			case 2:
				displayMyProperties();
				break;
			case 3:
				addRentalProperty();
				break;
			case 4:
				displayApplications();
				break;
			case 5:
				respondtoApplication();
				break;
			case 6:
				logOut();
				break;

			default:
				System.out.println("No such operation");
				break;
			}
		}
		
	}
	
	public void tenantMenu() {
		logOut = false;
		while (!logOut) {

			System.out.println("*******Tenant Menu******");
			System.out.printf("1. Display all properties");
			System.out.println();
			System.out.printf("2. Apply for a property");
			System.out.println();
			System.out.printf("3. Display my applications");
			System.out.println();
			System.out.printf("4. Make first payment");
			System.out.println();
			System.out.printf("5. Make rental payment");
			System.out.println();
			System.out.printf("6. Log Out");
			System.out.println();

			enterChoice();

			switch (choice) {
			case 1:
				displayAllProperties();
				break;
			case 2:
				applyForAProperty();
				break;
			case 3:
				displayMyApplications();
				break;
			case 4:
				makeFirstPayment();
				break;
			case 5:
				makeRentalPayment();
				break;
			case 6:
				logOut();
				break;
			default:
				System.out.println("No such operation");
				break;
			}
		}
		
	}
	
	public void branchManagerMenu() {
		logOut = false;
		while (!logOut) {

			System.out.println("*******Branch Manager Menu******");
			System.out.printf("1. Display all properties");
			System.out.println();
			System.out.printf("2.Inspect property document");
			System.out.println();
			System.out.printf("3.Assign employee to property");
			System.out.println();
			System.out.printf("4. Log Out");
			System.out.println();

			enterChoice();

			switch (choice) {
			case 1:
				displayAllProperties();
				break;
			case 2:
				System.out.println("<<tobe updated>>");
				break;
			case 3:
				System.out.println("<<tobe updated>>");
				break;
			case 4:
				logOut();
				break;
	
			default:
				System.out.println("No such operation");
				break;
			}
		}
	}
	
	public void propertyManagerMenu() {
		logOut = false;
		while (!logOut) {

			System.out.println("*******Property Manager Menu******");
			System.out.printf("1. Display all properties");
			System.out.println();
			System.out.printf("2.Schedule inspection");
			System.out.println();
			System.out.printf("3.Paying bills and fee");
			System.out.println();
			System.out.printf("4. Log Out");
			System.out.println();

			enterChoice();

			switch (choice) {
			case 1:
				displayAllProperties();
				break;
			case 2:
				System.out.println("<<tobe updated>>");
				break;
			case 3:
				System.out.println("<<tobe updated>>");
				break;
			case 4:
				logOut();
				break;
	
			default:
				System.out.println("No such operation");
				break;
			}
		}
	}
	
	
	
	public void enterChoice() {
		boolean valid = false;
		while (!valid) {
			try {
				System.out.print("Enter your choice:");
				choice = Integer.parseInt(scan.nextLine());
				valid = true;
			} catch (Exception e) {
				System.out.println("No valid choce. Re-enter");
			}
		}
	}
	public void login() {
		boolean infoOk=false;
		while(!infoOk) {
			System.out.println("*******Login Process*******");

			//require user to enter username and password
			//make sure username exists and username matches password
			//get the user ID
			//then search for user ID in allCustomers array and return the current index
	

			//demo login
			System.out.println("Enter a number from 0-3");
			System.out.println("0: login as landlord");
			System.out.println("1: login as tenant");
			System.out.println("2: login as branch manager");
			System.out.println("3: login as property manager");
			
			int demoIndex=Integer.parseInt(scan.nextLine()); 
			
			currentIndex=demoIndex; //after searching in allCustomer array this variable will store the index of current user in the array
			currentUser=allUsers.get(currentIndex); //currently hard-coded in StartUp.java
			
			if(currentUser instanceof Landlord) {
				landLordMenu(); //run menu for landlord
			} else if (currentUser instanceof Tenant) {
				tenantMenu(); //run menu for tenant
			} else if (currentUser instanceof BranchManager) {
				branchManagerMenu(); //run menu for manager
			} else if (currentUser instanceof PropertyManager) {
				propertyManagerMenu(); //run manu for property manager
			}
			infoOk=true;
		}
		
	}
	public void register() {
		System.out.println("Registration process..."+ "\n");
		//require user to specify username and password and customer type
		//assign an unique ID to each user
		//add user to allUsers array
	}
	
	public void quit() {
		System.out.println("Program stops here.");
		quit = true;
	}
	
	public void logOut() {
		System.out.println("Logging you out");
		logOut=true;
	}
	
	//tenant methods
	public void displayAllProperties() {
		System.out.println("*******All Properties******");
		for (int i = 0; i < allProperties.size(); i++) {
			System.out.println(allProperties.get(i).getPropertyDetails());
		}
	}
	
	public void applyForAProperty() {
		System.out.println("Applying process for a property");
	}
	
	public void displayMyApplications() {
		System.out.println("All of my sent applications shown here");
	}
	
	public void makeFirstPayment() {
		System.out.println("Make the first payment to secure your place");
		
	}
	
	public void makeRentalPayment() {
		System.out.println("Paying rental fee each month");
	}
	
	
	//landlord methods
	public void displayMyProperties() {
		System.out.println("*******My Properties******");
		String currentSessionID=currentUser.getUserID();
		
		for (int i = 0; i < allProperties.size(); i++) {
			if(allProperties.get(i).getCreatorID().compareTo(currentSessionID)==0) {
			System.out.println(allProperties.get(i).getPropertyDetails());
			}
		}
	}
	public void addRentalProperty() {
		boolean validProperty=false;
		while(!validProperty) {
			try {
				System.out.println("Enter details of your property below:");
				
				String title= "Address:";
				address=addPropertyTextInfo(title);
				
				title="Description:";
				description=addPropertyTextInfo(title);
				
				title="Surbub:";
				surbub=addPropertyTextInfo(title);
				
				title="Number of Bedrooms: ";
				bed=addCapacity(title);
				
				title="Number of Bathrooms: ";
				bath=addCapacity( title);
				
				title="Number of car Spaces: ";
				cars=addCapacity( title);
				
				title="Property type (house/ unit/flat/townhouse/studio):";
				type=addPropertyTextInfo(title);
				
				title= "Weekly Rent:";
				weeklyRent=addWeeklyRent( title);
				
				title= "Contract Duration: ";
				duration=addPropertyTextInfo( title);
				
				String currentSessionID=currentUser.getUserID();
				System.out.println(address+surbub);
				RentalProperty rentProp= new RentalProperty(currentSessionID, address, description, surbub, bed, bath, cars, type, weeklyRent, duration);
				
				allProperties.add(rentProp);
				System.out.println("Successfully add new Rental Property");
				
				validProperty=true;
			} catch (Exception e) {
				System.out.println("Cannot add new rental property");
			}
		}
	}
	
	
	
	public void displayApplications() {
		System.out.println("Displaying application");
	}
	public void respondtoApplication() {
		System.out.println("Accept or reject Application");
	}
	
	//accessors/mutators
	public ArrayList<User> getAllUsers(){
		return allUsers;
	}
	
	public ArrayList<Property> getAllProperty(){
		return allProperties;
	}
	
	//general methods
public String addPropertyTextInfo( String title) {
	boolean infoOk = false;
	String info=null;
	while (!infoOk) {
		try {
			System.out.print(title);
			 info= scan.nextLine();		
					
			if (info.trim().length()==0) {
				throw new FormatException("The field must not be blank");
			}

			infoOk = true;
			return info;
			
		}catch(FormatException fe) {
			System.out.println(fe.getReason());
			return null;
		}catch (Exception e) {
			System.out.println("Error. Re-enter "+ title); 
			return null;}
	}return info;
	}


public int addCapacity( String title) {
	boolean infoOk = false;
	int capa=0;
	while (!infoOk) {
		try {
			System.out.print(title);
			capa=Integer.parseInt(scan.nextLine());
			infoOk = true;
			return capa;
		} catch (Exception e) {
			System.out.println("Error. Re-enter "+ title);
			return 0;
		}
	}
	return capa;
}
public double addWeeklyRent( String title) {
	boolean infoOk = false;
	double rent=0;
	while (!infoOk) {
		try {
			System.out.print(title);
			rent=Double.parseDouble(scan.nextLine());
			infoOk = true;
			return rent;
		} catch (Exception e) {
			System.out.println("Error. Re-enter "+ title);
			return 0;
		}
	}
	return rent;
}


}
