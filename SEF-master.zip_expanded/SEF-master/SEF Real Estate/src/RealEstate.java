import java.util.ArrayList;
import java.util.Scanner;
import SystemExceptions.*;
import Utilities.ApplicationStatus;
import Utilities.DateTime;
import Utilities.PropertyStatus;
public class RealEstate {

	User currentUser;
	Application currentApp;
	RentalProperty currentRentProp;

	private int choice;
	private Scanner scan = new Scanner(System.in);
	private boolean quit; // quit the system
	private boolean quitToMainMenu;
	private boolean logOut;
	private int currentUserIndex; // index of current session user in allUsers
									// array
	private int currentPropertyIndex;
	private int currentAppIndex;

	DateTime currentDate;
	String appID;

	ArrayList<User> allUsers = new ArrayList<User>();
	ArrayList<Property> allProperties = new ArrayList<Property>();

	public RealEstate() {
		currentDate = new DateTime();
	}

	public void login() {
		boolean infoOk = false;
		while (!infoOk) {
			System.out.println("*******Login Process*******");

			// require user to enter username and password
			// make sure username exists and username matches password
			// get the user ID
			// then search for user ID in allCustomers array and return the
			// current index

			// demo login
			System.out.println("Enter a number from 0-3");
			System.out.println("0: login as landlord");
			System.out.println("1: login as tenant");
			System.out.println("2: login as branch manager");
			System.out.println("3: login as property manager");

			int demoIndex = Integer.parseInt(scan.nextLine());

			currentUserIndex = demoIndex; // after searching in allCustomer
											// array this variable will store
											// the index of current user in the
											// array
			currentUser = allUsers.get(currentUserIndex); // currently
															// hard-coded in
															// StartUp.java

			if (currentUser instanceof Landlord) {
				landLordMenu(); // run menu for landlord
			} else if (currentUser instanceof Tenant) {
				tenantMenu(); // run menu for tenant
			} else if (currentUser instanceof BranchManager) {
				branchManagerMenu(); // run menu for manager
			} else if (currentUser instanceof PropertyManager) {
				propertyManagerMenu(); // run manu for property manager
			}
			infoOk = true;
		}

	}
	public void register() {
		System.out.println("Registration process..." + "\n");
		// require user to specify username and password and customer type
		// assign an unique ID to each user
		// add user to allUsers array
	}

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
				case 1 :
					login();
					break;
				case 2 :
					register();
					break;

				case 3 :
					quit();
					break;

				default :
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
				case 1 :
					displayAllProperties();
					break;
				case 2 :
					displayMyProperties();
					break;
				case 3 :
					addRentalProperty();
					break;
				case 4 :
					displayApplications();
					break;
				case 5 :
					respondtoApplication();
					break;
				case 6 :
					logOut();
					break;

				default :
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
				case 1 :
					displayAllProperties();
					break;
				case 2 :
					applyForAProperty();
					break;
				case 3 :
					displayMyApplications();
					break;
				case 4 :
					makeFirstPayment();
					break;
				case 5 :
					makeRentalPayment();
					break;
				case 6 :
					logOut();
					break;
				default :
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
				case 1 :
					displayAllProperties();
					break;
				case 2 :
					System.out.println("<<tobe updated>>");
					break;
				case 3 :
					System.out.println("<<tobe updated>>");
					break;
				case 4 :
					logOut();
					break;

				default :
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
				case 1 :
					displayAllProperties();
					break;
				case 2 :
					System.out.println("<<tobe updated>>");
					break;
				case 3 :
					System.out.println("<<tobe updated>>");
					break;
				case 4 :
					logOut();
					break;

				default :
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
		logOut = true;
	}

	// tenant methods
	public void displayAllProperties() {
		System.out.println("*******All Properties******");
		for (int i = 0; i < allProperties.size(); i++) {
			System.out.println(allProperties.get(i).getPropertyDetails());
		}
	}

	public void applyForAProperty() {
		try {
			quitToMainMenu = false;
			while (!quitToMainMenu) {
				String title = "Add Property ID:";
				String propID = addPropertyID(title);
				if (quitToMainMenu)
					break;
				Property currentProp = allProperties.get(currentPropertyIndex);
				if (currentProp instanceof RentalProperty) {

					if (currentProp.getStatus() != PropertyStatus.Available) {
						throw new StatusException(
								"This property is not available");
					} else {
						title = "Monthly income:";
						double income = addMonetaryInfo(title);

						title = "Occupation: ";
						String occu = addTextInfo(title);

						title = "Proposed Weekly Rent: ";
						double rent = addMonetaryInfo(title);

						title = "Contract Duration: ";
						String duration = addTextInfo(title);

						Application newApp = new Application(propID,
								(Tenant) currentUser, income, occu, rent,
								duration);
						if (((RentalProperty) currentProp)
								.handleApplication(newApp)) {

							System.out.println(
									"Successfully apply for the property:"
											+ currentProp.getPropertyID());
							quitToMainMenu = true;
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
		} catch (Exception e) {
			System.out.println("Cannot apply for this property");
			e.printStackTrace();
		}
	}

	public void displayMyApplications() {
		System.out.println("*** My Application ***");

		String currentUserID = currentUser.getUserID();
		for (int i = 0; i < allProperties.size(); i++) {
			ArrayList<Application> allApps = ((RentalProperty) allProperties
					.get(i)).getAllApplications();

			for (int j = 0; j < allApps.size(); j++) {
				String tenantID = allApps.get(j).getTenant().getUserID();
				if (currentUserID.compareTo(tenantID) == 0) {
					System.out.println(allApps.get(j).getApplicationdetails());
				}
			}
		}

	}

	public void makeFirstPayment() {
		boolean flag = true;

		while (flag) {
			System.out.println("Enter Bank name: ");
			String bankName = StartUp.scan.nextLine();
			System.out.println("Enter account number: ");
			int accountNumber = StartUp.scan.nextInt();
			System.out.println("Enter BSB number: ");
			int BSB = StartUp.scan.nextInt();

			boolean innerFlag = true;

			while (innerFlag) {
				System.out.printf("\nBank name: %s" + "\nAccount Number: %d"
						+ "\nBSB: %d", bankName, accountNumber, BSB);
				System.out.println("Are the following details correct? [y/n]");
				String input = scan.next();

				switch (input) {
					case "y" :
						flag = false;
						innerFlag = false;
						break;
					case "n" :
						innerFlag = false;
						break;
					default :
						System.out.println("Please provide a valid input");
				}
			}
		}

	}

	public void makeRentalPayment() {
		System.out.println("Paying rental fee each month");
	}

	// landlord methods
	public void displayMyProperties() {
		System.out.println("*******My Properties******");
		String currentSessionID = currentUser.getUserID();

		for (int i = 0; i < allProperties.size(); i++) {
			if (allProperties.get(i).getCreatorID()
					.compareTo(currentSessionID) == 0) {
				System.out.println(allProperties.get(i).getPropertyDetails());
			}
		}
	}

	public void addRentalProperty() {
		boolean validProperty = false;
		while (!validProperty) {
			try {
				System.out.println("Enter details of your property below:");

				String title = "Address:";
				String address = addTextInfo(title);

				title = "Description:";
				String description = addTextInfo(title);

				title = "Surbub:";
				String surbub = addTextInfo(title);

				title = "Number of Bedrooms: ";
				int bed = addCapacity(title);

				title = "Number of Bathrooms: ";
				int bath = addCapacity(title);

				title = "Number of car Spaces: ";
				int cars = addCapacity(title);

				title = "Property type (house/ unit/flat/townhouse/studio):";
				String type = addTextInfo(title);

				title = "Weekly Rent:";
				double weeklyRent = addMonetaryInfo(title);

				title = "Contract Duration: ";
				String duration = addTextInfo(title);

				String currentSessionID = currentUser.getUserID();
				System.out.println(address + surbub);
				RentalProperty rentProp = new RentalProperty(currentSessionID,
						address, description, surbub, bed, bath, cars, type,
						weeklyRent, duration);

				allProperties.add(rentProp);

				System.out.println("Successfully add new Rental Property");

				validProperty = true;
			} catch (Exception e) {
				System.out.println("Cannot add new rental property");
			}
		}
	}

	public void displayApplications() {
		System.out.println("*******All Applications******");
		// loops through All properties array of the current Landlord then
		// loops through All applications array in each property
		// then print each application details

		String currentSessionID = currentUser.getUserID();

		for (int i = 0; i < allProperties.size(); i++) {
			if (allProperties.get(i).getCreatorID()
					.compareTo(currentSessionID) == 0) {
				ArrayList<Application> allApps = ((RentalProperty) allProperties
						.get(i)).getAllApplications();
				for (int j = 0; j < allApps.size(); j++) {
					System.out.println(allApps.get(j).getApplicationdetails());
				}
			}
		}
	}

	public void respondtoApplication() {
		try {
			System.out.println("Accept or reject Application");
			quitToMainMenu = false;
			while (!quitToMainMenu) {
				String title = "Add Application ID:";
				appID = addApplicationID(title);
				if (quitToMainMenu)
					break;

				if (currentApp.getAppStatus() == ApplicationStatus.Rejected) {
					throw new StatusException(
							"This application status is currently Rejected. No further work needed.");

				} else if (currentApp
						.getAppStatus() == ApplicationStatus.Accepted) {
					throw new StatusException(
							"This application status is currently Accepted. No further work needed.");

				} else {
					AcceptOrRejectApp();
					quitToMainMenu = true;
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
				case 1 :

					currentApp.acceptApp();
					ApplicationStatus currentappStatus = currentApp
							.getAppStatus();
					System.out.println("Current Application status is:"
							+ currentappStatus);
					ArrayList<Application> allApps = currentRentProp
							.getAllApplications();
					for (int i = 0; i < allApps.size(); i++) {
						if (appID.compareTo(
								allApps.get(i).getApplicationID()) != 0) {
							allApps.get(i).rejectApp();
						}

					}

					quitToMainMenu = true;
					break;
				case 2 :
					currentApp.rejectApp();
					currentappStatus = currentApp.getAppStatus();
					System.out.println("Current Application status is:"
							+ currentappStatus);
					quitToMainMenu = true;
					break;

				case 3 :
					quitToMainMenu = true;
					break;

				default :
					System.out.println("No such operation");
					break;
			}
		}
	}

	// accessors/mutators
	public ArrayList<User> getAllUsers() {
		return allUsers;
	}

	public ArrayList<Property> getAllProperty() {
		return allProperties;
	}

	// general methods
	public String addTextInfo(String title) {
		boolean infoOk = false;
		String info = null;
		while (!infoOk) {
			try {
				System.out.print(title);
				info = scan.nextLine();

				if (info.trim().length() == 0) {
					throw new FormatException("The field must not be blank");
				}

				infoOk = true;
				return info;

			} catch (FormatException fe) {
				System.out.println(fe.getReason());
				return null;
			} catch (Exception e) {
				System.out.println("Error. Re-enter " + title);
				return null;
			}
		}
		return info;
	}

	public int addCapacity(String title) {
		boolean infoOk = false;
		int capa = 0;
		while (!infoOk) {
			try {
				System.out.print(title);
				capa = Integer.parseInt(scan.nextLine());
				infoOk = true;
				return capa;
			} catch (Exception e) {
				System.out.println("Error. Re-enter " + title);
				return 0;
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
				return 0;
			}
		}
		return rent;
	}
	public String addPropertyID(String title) {
		boolean infoOk = false;
		String iD = null;
		while (!infoOk) {
			try {
				System.out.println(title);
				iD = scan.nextLine();
				if (iD.charAt(0) == 'Q' || iD.charAt(0) == 'q') {
					infoOk = true;
					quitToMainMenu = true;

				}
				if (!PropertyIdExists(iD)) {
					throw new IdNotFound("Property not found");
				}
				infoOk = true;
				return iD;
			} catch (IdNotFound ex) {
				System.out.println(ex.getReason());
				return null;
			} catch (Exception e) {
				System.out.println("Invalid. Re-enter ID");
				return null;
			}
		}
		return iD;
	}

	public boolean PropertyIdExists(String ID) {
		for (int i = 0; i < allProperties.size(); i++) {

			if (allProperties.get(i).getPropertyID().compareTo(ID) == 0) {
				System.out.println(
						allProperties.get(i).getPropertyID() + " found!");
				currentPropertyIndex = i; // record index of current post in
											// allPost
				return true;
			}
		} ;
		return false;
	}

	public String addApplicationID(String title) {
		boolean infoOk = false;
		String iD = null;
		while (!infoOk) {
			try {
				System.out.println(title);
				iD = scan.nextLine();
				if (iD.charAt(0) == 'Q' || iD.charAt(0) == 'q') {
					infoOk = true;
					quitToMainMenu = true;

				}
				if (!ApplicationIdExits(iD)) {
					throw new IdNotFound("Application not found");
				}
				infoOk = true;
				return iD;
			} catch (IdNotFound ex) {
				System.out.println(ex.getReason());
				return null;
			} catch (Exception e) {
				System.out.println("Invalid. Re-enter ID");
				return null;
			}
		}
		return iD;
	}

	public boolean ApplicationIdExits(String ID) {

		for (int i = 0; i < allProperties.size(); i++) {
			ArrayList<Application> allApps = ((RentalProperty) allProperties
					.get(i)).getAllApplications();
			currentRentProp = ((RentalProperty) allProperties.get(i));

			for (int j = 0; j < allApps.size(); j++) {
				if (allApps.get(j).getApplicationID().compareTo(ID) == 0) {
					System.out.println(
							allApps.get(j).getApplicationID() + "found!");

					currentApp = allApps.get(j);

					currentAppIndex = j;
					currentPropertyIndex = i;
					return true;
				}
			}

		}
		return false;
	}

}
