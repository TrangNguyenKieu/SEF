import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import SystemExceptions.*;
import Utilities.ApplicationStatus;
import Utilities.DateTime;
import Utilities.PropertyStatus;

import java.sql.Date;
import java.text.SimpleDateFormat; 

public class RealEstate {
	
	User currentUser;
	Application currentApp;
	RentalProperty 	currentRentProp;
	
	static long time;
	
	private int choice;
	private Scanner scan = new Scanner(System.in);
	private boolean quit; // quit the system
	private boolean quitToMainMenu;
	private boolean logOut;
	private int currentUserIndex; //index of current session user in allUsers array
	private int currentPropertyIndex; 
	private int currentAppIndex;

	DateTime currentDate;
	String appID;

	ArrayList<User> allUsers= new ArrayList<User>();
	ArrayList<Property> allProperties=new ArrayList<Property>();
	
	public RealEstate() {
		time= System.currentTimeMillis();
		currentDate=new DateTime(time);
	}

	
	
	public void landingPageMenu() {
		quit = false;
		while (!quit) {

			System.out.println("\n*******Real Estate System******");
			System.out.printf("1. Log in");
			System.out.println();
			System.out.printf("2. Register");
			System.out.println();
			System.out.printf("3. Advance Time");
			System.out.println();
			System.out.printf("4. Quit");
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
				advanceTime();
				break;
				
			case 4:
				quit();
				break;

			default:
				System.out.println("No such operation");
				break;
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
			System.out.println("4: login as vendor");
			System.out.println("5: login as buyer");
			System.out.println("6: login as branch admin");
			
			int demoIndex=Integer.parseInt(scan.nextLine()); 
			
			currentUserIndex=demoIndex; //after searching in allCustomer array this variable will store the index of current user in the array
			currentUser=allUsers.get(currentUserIndex); //currently hard-coded in StartUp.java
			
			if(currentUser instanceof Landlord) {
				landLordMenu(); //run menu for landlord
			} else if (currentUser instanceof Tenant) {
				tenantMenu(); //run menu for tenant
			} else if (currentUser instanceof BranchManager) {
				branchManagerMenu(); //run menu for manager
			} else if (currentUser instanceof PropertyManager) {
				propertyManagerMenu(); //run manu for property manager
			}else if (currentUser instanceof Vendor) {
				vendorMenu();
			}else if (currentUser instanceof Buyer) {
				buyerMenu();
			}else if (currentUser instanceof BranchAdmin) {
				System.out.println("Run menu for Branch Admin");
			}else System.out.println("No such user");
			infoOk=true;
		}
			
	}
	
	
	public void register() {
		System.out.println("Registration process..."+ "\n");
		//require user to specify username and password and customer type
		//assign an unique ID to each user
		//add user to allUsers array
	}
	

	
	public void landLordMenu() {
		logOut = false;
		while (!logOut) {

			System.out.println("\n*******Landlord Menu******");
			System.out.printf("0. Search property");
			System.out.println();
			System.out.printf("1. Display all properties");
			System.out.println();
			System.out.printf("2. Display my properties");
			System.out.println();
			System.out.printf("3. Add Rental property");
			System.out.println();
			System.out.printf("4. Display applications");
			System.out.println();
			System.out.printf("5. Respond to application");
			System.out.println();
			System.out.printf("6. Log Out");
			System.out.println();

			enterChoice();

			switch (choice) {
			case 0:
				searchProperty();
				break;
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

			System.out.println("\n*******Tenant Menu******");
			System.out.printf("0. Search property");
			System.out.println();
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
			case 0:
				searchProperty();
				break;
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
				makeBondPayment();
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

			System.out.println("\n*******Branch Manager Menu******");
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

			System.out.println("\n*******Property Manager Menu******");
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
	
	public void vendorMenu() {
		logOut = false;
		while (!logOut) {

			System.out.println("\n*******Vendor Menu******");
			System.out.printf("1. Display all properties");
			System.out.println();
			System.out.printf("2.Display my properties");
			System.out.println();
			System.out.printf("3.Add sale property");
			System.out.println();
			System.out.printf("4.Create auction");
			System.out.println();
			
			System.out.printf("5.Display all offers");
			System.out.println();
			System.out.printf("6.Respond to offer");
			System.out.println();
			System.out.printf("7.Display all auctions");
			System.out.println();
			System.out.printf("8.Display all bids");
			System.out.println();
			System.out.printf("9. Log Out");
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
				addSaleProperty();
				break;
			case 4:
				createAuction();
				break;
			case 5:
				System.out.println("<<tobe updated>>");
				break;
			case 6:
				System.out.println("<<tobe updated>>");
				break;
			case 7:
				System.out.println("<<tobe updated>>");
				break;
			case 8:
				System.out.println("<<tobe updated>>");
				break;
			case 9:
				logOut();
				break;
	
			default:
				System.out.println("No such operation");
				break;
			}
		}
	
	}

	public void buyerMenu() {
		logOut = false;
		while (!logOut) {

			System.out.println("\n*******Buyer Menu******");
			System.out.printf("1.Display all properties");
			System.out.println();
			System.out.printf("2.Display my offers");
			System.out.println();
			System.out.printf("3.Display my bids");
			System.out.println();
			System.out.printf("4.Make new offer");
			System.out.println();
			System.out.printf("5.Make new bid");
			System.out.println();
			System.out.printf("6.Make deposit");
			System.out.println();
			System.out.printf("7.Make final payment");
			System.out.println();
			System.out.printf("8.Log Out");
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
				System.out.println("<<tobe updated>>");
				break;
			case 5:
				System.out.println("<<tobe updated>>");
				break;
			case 6:
				System.out.println("<<tobe updated>>");
				break;
			case 7:
				System.out.println("<<tobe updated>>");
				break;
			case 8:
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
				System.out.println("Not a valid choice. Re-enter");
			}
		}
	}

	public void quit() {
		System.out.println("Program stops here.");
		quit = true;
	}
	
	public void logOut() {
		System.out.println("Logging you out");
		logOut=true;
	}
	
	
	
	
	
	
	
	
	
	
	//vendor methods
	public void createAuction() {
		quitToMainMenu=false;
		while(!quitToMainMenu) {
			String title="Add Property ID:";
			String propID=addPropertyID(title);
			if(quitToMainMenu) break;
			Property currentProp= allProperties.get(currentPropertyIndex);
			if (currentProp instanceof SalebyAuction) {
				
				if(((SalebyAuction) currentProp).auctionHistoryValid()) {
					DateTime aucDate=addDate();
					title="Add minimum reserve:";
					double reserve= addMonetaryInfo(title);
					
					Auction auction = new Auction(currentProp.getPropertyID(), aucDate, reserve);
					if(((SalebyAuction) currentProp).handleAuction(auction)) {
						System.out.println("Succesfully added new auction to property:" + currentProp.getPropertyID());
					} else System.out.println("Cannot add new auction to property");
				} else {
					System.out.println("Cannot create new auction as there's currently opening/wating auctions");
				}
				
				
			}
	}
	}
	
	public DateTime addDate() {
		boolean infoOk = false;
		DateTime aucDate=new DateTime();
		while (!infoOk) {
			try {
				System.out.print("Date (dd/mm/yyyy):");
				String date = scan.nextLine();
				
				if(date.length()==0) {
					throw new FormatException("You must enter a text value");
				}
				
				if (date.trim().length()==0) {
					throw new FormatException("The field must not be blank");
				}
				
				if(!checkDateFormat(date))  {
					throw new FormatException("Date must be in correct format: dd/mm/yyyy");
				}
				
				SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy"); 
				Date d= (Date) formatter1.parse(date);
				long time = d.getTime();
				long c_time=System.currentTimeMillis();
				
				if(time<=c_time) {
					throw new DateException("Auction date cannot be prior to current date");
				} else {
					aucDate= new DateTime(time,0);
					infoOk = true;
					return aucDate;
				}
	
				
			} catch (FormatException fe) {
				System.out.println(fe.getReason());
			}catch(DateException e) {
				System.out.println(e.getReason());
			}
			catch (Exception e) {
				System.out.println("Error. Re-enter date");
				e.printStackTrace();
			}
		}
		return aucDate;
	}
	
	public static boolean checkDateFormat(String str) {
		if (str.matches("\\d{2}+/\\d{2}+/\\d{4}")) {
//			System.out.println("true");
			return true;
			
		}
		else {
//			System.out.println("false");
			return false;
		}
	}
	
	public void addSaleProperty() {
		quitToMainMenu = false;
		while (!quitToMainMenu) {

			System.out.println("Select 1 option [1-3]");
			System.out.printf("1. Add Property for Sale by Negotiation");
			System.out.println();
			System.out.printf("2. Add Property for Sale by Auction");
			System.out.println();
			System.out.printf("3. Back to Main Menu");
			System.out.println();

			enterChoice();

			switch (choice) {
			case 1:
				addSalebyNego();
				break;
			case 2:
				addSalebyAuct();
				break;
			case 3:
				quitToMainMenu=true;
				break;
			default:
			System.out.println("No such operation");
				break;
			}
		}
	}
	
	public void addSalebyNego() {
		System.out.println("adding property for sale by negotiation");
	}
	
	public void addSalebyAuct() {
		boolean validProperty=false;
		while(!validProperty) {
			try {
				System.out.println("Enter details of your property below:");
				
				String title= "Address:";
				String address=addTextInfo(title);
				
				title="Description:";
				String description=addTextInfo(title);
				
				title="Surbub:";
				String surbub=addTextInfo(title);
				
				title="Number of Bedrooms: ";
				int bed=addCapacity(title);
				
				title="Number of Bathrooms: ";
				int bath=addCapacity( title);
				
				title="Number of car Spaces: ";
				int cars=addCapacity( title);
				
				title="Property type (house/ unit/flat/townhouse/studio):";
				String type=addTextInfo(title);
				
				
				String currentSessionID=currentUser.getUserID();

				SalebyAuction aucProp= new SalebyAuction(currentSessionID, address, description, surbub, bed, bath, cars, type);
				
				allProperties.add(aucProp);
				
				System.out.println("Successfully add new Property for sale by auction");
				
				validProperty=true;
			} catch (Exception e) {
				System.out.println("Cannot add new auction property");
			}
		}
	}
	
	//tenant methods
	
	
	public void applyForAProperty() {
		try {
			quitToMainMenu=false;
			while(!quitToMainMenu) {
				String title="Add Property ID:";
				String propID=addPropertyID(title);
				if(quitToMainMenu) break;
				Property currentProp= allProperties.get(currentPropertyIndex);
				if( currentProp instanceof RentalProperty) {
					
					if (currentProp.getStatus()!=PropertyStatus.Available) {
						throw new StatusException("This property is not available");
					} else {
						title="Monthly income:";
						double income=addMonetaryInfo(title);
					
						title="Occupation: ";
						String occu=addTextInfo(title);
						
						title="Proposed Weekly Rent: ";
						double rent= addMonetaryInfo(title);
						
						title="Contract Duration: ";
						String duration= addTextInfo(title);
				
						Application newApp= new Application(propID,(Tenant) currentUser, income, occu, rent, duration);
						if (( (RentalProperty) currentProp).handleApplication(newApp)) {
						
							System.out.println("Successfully apply for the property:" + currentProp.getPropertyID());
							quitToMainMenu=true;
					}
					
					}
				} else {
					throw new TypeException("This is property is not for rent");
				}
				
				
			}
		} catch (TypeException e) {
			System.out.println(e.getReason());
		} catch (StatusException stt) {
			System.out.println(stt.getReason());
		}
		catch( Exception e) {
			System.out.println("Cannot apply for this property");
			e.printStackTrace();
		}
	}
	
	public void displayMyApplications() {
		System.out.println("\n*******My Application******");

		String currentUserID=currentUser.getUserID();
		for (int i = 0; i < allProperties.size(); i++) {
			if (allProperties.get(i) instanceof RentalProperty) {
			ArrayList<Application> allApps=((RentalProperty)allProperties.get(i)).getAllApplications();
			
			for (int j = 0; j < allApps.size(); j++) {
				String tenantID=allApps.get(j).getTenant().getUserID();
				if(currentUserID.compareTo(tenantID)==0) {
					System.out.println(allApps.get(j).getApplicationdetails());
				}
			}
			}
		}
		
	}
	
	public void makeBondPayment() {
		System.out.println("Make bond and 1st month rent to secure your place");
		
	}
	
	public void makeRentalPayment() {
		System.out.println("Paying rental fee each month");
	}
	
	
	//landlord methods
	public void displayMyProperties() {
		System.out.println("n*******My Properties******");
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
				String address=addTextInfo(title);
				
				title="Description:";
				String description=addTextInfo(title);
				
				title="Surbub:";
				String surbub=addTextInfo(title);
				
				title="Number of Bedrooms: ";
				int bed=addCapacity(title);
				
				title="Number of Bathrooms: ";
				int bath=addCapacity( title);
				
				title="Number of car Spaces: ";
				int cars=addCapacity( title);
				
				title="Property type (house/ unit/flat/townhouse/studio):";
				String type=addTextInfo(title);
				
				title= "Weekly Rent:";
				double weeklyRent=addMonetaryInfo( title);
				
				title= "Contract Duration: ";
				String duration=addTextInfo( title);
				
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
		System.out.println("\n*******All Applications******");
		//loops through All properties array of the current Landlord then
		//loops through All applications array in each property
		//then print each application details

		String currentSessionID=currentUser.getUserID();
		
		for (int i = 0; i < allProperties.size(); i++) {
			if(allProperties.get(i).getCreatorID().compareTo(currentSessionID)==0) {
				
				ArrayList<Application> allApps= ((RentalProperty)allProperties.get(i)).getAllApplications();
				for (int j = 0; j < allApps.size(); j++) {
					System.out.println(allApps.get(j).getApplicationdetails());
				}
			}
		}
	}
	
	
	public void respondtoApplication() {
		try {
			System.out.println("Accept or reject Application or  press Q to quit");
			quitToMainMenu=false;
			while(!quitToMainMenu) {
				String title="Add Application ID:";
				appID=addApplicationID(title);
				if (appID.compareTo("q")==0 || appID.compareTo("q")==0) {
					quitToMainMenu=true;
					break;
				}
				if(quitToMainMenu) break;
				
				if(currentApp.getAppStatus()==ApplicationStatus.Rejected) {
					throw new StatusException("This application status is currently Rejected. No further work needed.");
					
				} else if (currentApp.getAppStatus()==ApplicationStatus.Accepted) {
					throw new StatusException("This application status is currently Accepted. No further work needed.");
					
				}else {
					AcceptOrRejectApp();
					quitToMainMenu=true;
				}
			}
		} catch (StatusException e) {
			System.out.println(e.getReason());
		} catch (Exception e) {
			System.out.println("Cannot respond to application. Try again.");
		}
		
	}
	
	public void AcceptOrRejectApp() {
		quitToMainMenu = false;
		while (!quitToMainMenu) {

			System.out.println("Select 1 option [1-3]");
			System.out.printf("1. Accept Application");
			System.out.println();
			System.out.printf("2.Reject Application");
			System.out.println();
			System.out.printf("3. Back to Main Menu");
			System.out.println();

			enterChoice();

			switch (choice) {
			case 1:
				//accept application
				currentApp.acceptApp();
				ApplicationStatus currentappStatus=currentApp.getAppStatus();
				System.out.println("Current Application status is:"+currentappStatus);
				ArrayList<Application> allApps=currentRentProp.getAllApplications();
				
				//set property status to in process
				for (Property prop: allProperties) {
					if (prop instanceof RentalProperty) {
						if (((RentalProperty) prop).getAllApplications().contains(currentApp)) {
							prop.setStatusToInprocess();
						}
					}
				}
				
				//reject all other applications of the same property
				for (int i = 0; i < allApps.size(); i++) {
					if(appID.compareTo(allApps.get(i).getApplicationID())!=0) {
						allApps.get(i).rejectApp();
					}

				}
				
				quitToMainMenu=true;
				break;
			case 2:
				currentApp.rejectApp();
				currentappStatus=currentApp.getAppStatus();
				System.out.println("Current Application status is:"+currentappStatus);
				quitToMainMenu=true;
				break;
			
			case 3:
				quitToMainMenu=true;
				break;
	
			default:
				System.out.println("No such operation");
				break;
			}
		}
	}
	
	
	
	//accessors/mutators
	public ArrayList<User> getAllUsers(){
		return allUsers;
	}
	
	public ArrayList<Property> getAllProperty(){
		return allProperties;
	}
	
	//general methods
	
	
	public void searchProperty() {
		System.out.println("\n*******Search Properties******");
		String title= "Enter a surbub";
		String surb= addTextInfo(title);
		if (!searchSurbub(surb)) {
			System.out.println("No property found");
		}
	}
	
	public void displayAllProperties() {
		System.out.println("\n*******All Properties******");
		for (int i = 0; i < allProperties.size(); i++) {
			System.out.println(allProperties.get(i).getPropertyDetails());
		}
	}
	
	
public boolean searchSurbub(String id) {
	try {
		for(Property prop: allProperties) {
			
			if (prop.getSurbub().compareTo(id)==0) {
				System.out.println(prop.getPropertyDetails());
				return true;
			} 
		}
		return false;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("Cannot search surbub");
		return false;
	}
}
	
public String addTextInfo( String title) {
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
	
		}catch (Exception e) {
			System.out.println("Error. Re-enter "+ title); 
			}
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
		
		}
	}
	return capa;
}
public double addMonetaryInfo( String title) {
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
			
		}
	}
	return rent;
}
public String addPropertyID(String title) {
	boolean infoOk=false;
	String iD=null;
	while(!infoOk) {
		try {
			System.out.println(title);
			iD=scan.nextLine();
			if (iD.charAt(0) == 'Q' || iD.charAt(0) == 'q') {
				infoOk = true;
				quitToMainMenu = true;
				break;
			
			}
			if(!PropertyIdExists(iD)) {
				throw new IdNotFound("Property not found");
			}
			infoOk=true;
			return iD;
		} catch(IdNotFound ex) {
			System.out.println(ex.getReason());
		
		}catch (Exception e) {
			System.out.println("Invalid. Re-enter ID");
		
		}
	}
	return iD;
}

public boolean PropertyIdExists(String ID) {
	for (int i = 0; i < allProperties.size(); i++) {

		if (allProperties.get(i).getPropertyID().compareTo(ID) == 0) {
			System.out.println(allProperties.get(i).getPropertyID()+ " found!");
			currentPropertyIndex= i; // record index of current post in allPost
			return true;
		}
	}
	;
	return false;
}

public String addApplicationID(String title) {
	boolean infoOk=false;
	String iD=null;
	while(!infoOk) {
		try {
			System.out.println(title);
			iD=scan.nextLine();
			if (iD.charAt(0) == 'Q' || iD.charAt(0) == 'q') {
				infoOk = true;
				quitToMainMenu = true;
				break;
			
			}
			if(!ApplicationIdExits(iD)) {
				throw new IdNotFound("Application not found");
			}
			infoOk=true;
			return iD;
		} catch(IdNotFound ex) {
			System.out.println(ex.getReason());

		}catch (Exception e) {
			System.out.println("Invalid. Re-enter ID");

		}
	}
	return iD;
}

public boolean ApplicationIdExits(String ID) {
	
	for (int i = 0; i < allProperties.size(); i++) {
			ArrayList<Application> allApps= ((RentalProperty)allProperties.get(i)).getAllApplications();
			currentRentProp=((RentalProperty)allProperties.get(i));
			
			for (int j = 0; j < allApps.size(); j++) {
				if(allApps.get(j).getApplicationID().compareTo(ID)==0) {
					System.out.println(allApps.get(j).getApplicationID() +"found!");
		
					currentApp=allApps.get(j);
					
					currentAppIndex=j;
					currentPropertyIndex=i;
					return true;
				}
			}
		
	}
	return false;
}



public void advanceTime() {

	try {
		System.out.println("Enter number of hours to advance");
		int hours = Integer.parseInt(scan.nextLine());
		System.out.println("Enter number of mins to advance");
		int mins=Integer.parseInt(scan.nextLine());
		System.out.println("Enter number of seconds to advance");
		int sec=Integer.parseInt(scan.nextLine());
		
		DateTime previousDate=currentDate;
		time=currentDate.getTime();
		System.out.println("Previous date:" + previousDate);
		DateTime.setAdvance(0, hours,mins,sec);
		
		currentDate=new DateTime(time);
		
		System.out.println("Current date:"+ currentDate);
		int diff= DateTime.diffMins(currentDate, previousDate);
		System.out.println(diff + " minutes "+ "has passed since the previous date.");
		System.out.println(DateTime.diffSecond(currentDate, previousDate) + " seconds "+ "has passed");
		
		
		checkApplicationStatus();
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("Error with input. Try again");
	}
	
	
}

	public void checkApplicationStatus() {
	for (Property prop: allProperties) {
		if (prop instanceof RentalProperty) {
			for (Application app: ((RentalProperty) prop).getAllApplications()) {
				
				//application must be responded within 3 days after submitted date
				if (app.getAppStatus()==ApplicationStatus.Pending) {
					if (DateTime.diffHours(currentDate, app.getSummittedDate())>71) {
						app.rejectApp();
						System.out.println("Application: "+ app.getApplicationID() + " has been rejected due to non-response from landlord for more than 3 days");
					}
				//bond payment must be made within 24 hours after accepted date
				} else if (app.getAppStatus()==ApplicationStatus.Accepted) {
					if (DateTime.diffHours(currentDate, app.getAcceptedDate())>23 && app.getBondPaymentStatus()==false) {
						app.rejectApp();
						System.out.println("Application: "+ app.getApplicationID() + " has been rejected due to failing to pay bond within 24 hours");
						
						//set property status back to available
						prop.setStatusToAvailable();
						
						
					}
				}
			}
		}
	}
	}
}
