package startUp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import SystemExceptions.AmountException;
import SystemExceptions.IdNotFound;
import SystemExceptions.StatusException;
import SystemExceptions.TypeException;
import Utilities.ApplicationStatus;
import Utilities.AuctionStatus;
import Utilities.BidStatus;
import Utilities.DateTime;
import Utilities.PropertyStatus;
import Utilities.SaleType;
import Utilities.TimeSheet;
import Utilities.TimeSheetStatus;
import Utilities.ValidateFunction;
import properties.Application;
import properties.Auction;
import properties.Bid;
import properties.Offer;
import properties.Property;
import properties.RentalProperty;
import properties.SaleProperty;
//import properties.SalebyAuction;
import users.BranchAdmin;
import users.BranchManager;
import users.Buyer;
import users.Landlord;
import users.PropertyManager;
import users.SaleConsultant;
import users.Tenant;
import users.User;
import users.Vendor;

public class RealEstate {

	private static User currentUser;
	private static Application currentApp;
	private static Offer currentOffer;
	private static RentalProperty currentRentProp;
	private static SaleProperty currentSaleProp;
	private static Auction currentAuc;
	private static ArrayList<User> allUsers;
	private static ArrayList<Property> allProperties;
	private static Map<String, TimeSheet> timeSheets;

	private int choice;
	public static Scanner scan = new Scanner(System.in);
	private boolean quit; // quit the system
	private boolean quitToMainMenu;
	private boolean logOut;
	private int currentUserIndex; // index of current session user in allUsers array
	private int currentPropertyIndex;

	public static long time;
	public static DateTime currentDate;
	private String appID;

	private String offerID;

	public RealEstate() {
		time = System.currentTimeMillis();
		currentDate = new DateTime(time);
		allUsers = new ArrayList<User>();
		allProperties = new ArrayList<Property>();
		timeSheets = new HashMap<String, TimeSheet>();
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
		boolean infoOk = false;

		try {
			outer: while (!infoOk) {
				System.out.println("*******Login Process*******");

				// require user to enter username and password
				System.out.println("Enter username :");
				String username = scan.nextLine();

				System.out.println("Enter password :");
				String password = scan.nextLine();

				// make sure username exists and username matches password
				for (User user : allUsers) {
					if (user.getUsername().equals(username)) {
						if (user.getPassword().equals(password)) {
							System.out.println("Login Successful \n");
							currentUserIndex = allUsers.indexOf(user);
							currentUser = user;
							break;
						} else {
							System.out.println("Username/Password not found \n");
							continue outer;
						}
					}
				}

				// get the user ID
				// then search for user ID in allCustomers array and return the current index

				// demo login
				// System.out.println("Enter a number from 0-6");
				// System.out.println("0: login as landlord");
				// System.out.println("1: login as tenant");
				// System.out.println("2: login as branch manager");
				// System.out.println("3: login as property manager");
				// System.out.println("4: login as vendor");
				// System.out.println("5: login as buyer");
				// System.out.println("6: login as branch admin");

				// int demoIndex = Integer.parseInt(scan.nextLine());

				// currentUserIndex = demoIndex; // after searching in allCustomer array this
				// variable will store the index of
				// // current user in the array
				// currentUser = allUsers.get(currentUserIndex); // currently hard-coded in
				// StartUp.java

				if (currentUser instanceof Landlord) {
					landLordMenu(); // run menu for landlord
				} else if (currentUser instanceof Tenant) {
					tenantMenu(); // run menu for tenant
				} else if (currentUser instanceof BranchManager) {
					branchManagerMenu(); // run menu for manager
				} else if (currentUser instanceof PropertyManager) {
					propertyManagerMenu(); // run manu for property manager
				} else if (currentUser instanceof Vendor) {
					vendorMenu();
				} else if (currentUser instanceof Buyer) {
					buyerMenu();
				} else if (currentUser instanceof BranchAdmin) {
					branchAdminMenu();
				}else if (currentUser instanceof SaleConsultant) {
					saleConsultantMenu();
				}
				// else
				// System.out.println("No such user");
				infoOk = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			System.out.println("Please log in again");
			e.printStackTrace();
		}

	}

	public void register() {
		String username = null;
		boolean isUsernameExists = false;
		System.out.println("Registration process..." + "\n");
		// require user to specify username and password and customer type
		do {
			isUsernameExists = false;
			System.out.println("Enter username :");
			username = scan.nextLine();

			for (User user : allUsers) {
				if (user.getUsername().equals(username)) {
					System.out.println("Same username already exists. Please enter a diffrent username.");
					isUsernameExists = true;
					break;
				}
			}
		} while (isUsernameExists);

		System.out.println("Enter password :");
		String password = scan.nextLine();

		System.out.println("Are you are:- " + "\n");
		System.out.println("0:- Landlord");
		System.out.println("1:- Tenant");
		System.out.println("2:- Vendor");
		System.out.println("3:- Buyer");

		int demoIndex = Integer.parseInt(scan.nextLine());

		User user = null;
		switch (demoIndex) {
		case 0:
			user = new Landlord();
			break;
		case 1:
			user = new Tenant();
			break;
		case 2:
			user = new Vendor();
			break;
		case 3:
			user = new Buyer();
			break;
		}

		user.setUsername(username);
		user.setPassword(password);

		// add user to allUsers array
		if (user != null) {
			allUsers.add(user);
		}

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
			System.out.printf("4. Make bond payment");
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
//				makeBondPayment();
				makeDeposit();
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
		BranchManager caller = (BranchManager) currentUser;
		Property property = null;
		while (!logOut) {

			System.out.println("\n*******Branch Manager Menu******");
			System.out.printf("1. Display all properties");
			System.out.println();
			System.out.printf("2.Inspect property document");
			System.out.println();
			System.out.printf("3.Assign employee to property");
			System.out.println();
			System.out.printf("4.View pending Time sheets");
			System.out.println();
			System.out.printf("5. Log Out");
			System.out.println();

			enterChoice();

			switch (choice) {
			case 1:
				displayAllProperties();
				break;
			case 2:
				try {
					System.out.println("Enter the PropertyId");
					String id = scan.nextLine();

					property = caller.inspect(id, allProperties);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				try {

					System.out.println("Enter the employeeId");
					String employeeId = scan.nextLine();
					caller.assign(employeeId, allUsers, property);

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				try {
					if (timeSheets.size() > 0) {
						for (TimeSheet timeSheet : timeSheets.values()) {
							System.out.printf("TimeSheet Id  : " + timeSheet.getId());
							System.out.printf(" Employee Id   : " + timeSheet.getEmployeeId());
							System.out.println("");
						}
						while (true) {
							String id = (String) customChoice("String",
									"Enter the timeSheet id to Approve/Reject or 0 to go back to main menu");
							if (id.equals("0"))
								break;
							else if (timeSheets.containsKey(id)
									&& timeSheets.get(id).getStatus().equals(TimeSheetStatus.NEW)) {
								System.out.println("Hour worked   : " + timeSheets.get(id).getHours());
								System.out.println("Week          : " + timeSheets.get(id).getWeek());
								System.out.println("Status        : " + timeSheets.get(id).getStatus());
								Integer timeSheetChoice = (Integer) customChoice("int",
										"Enter 1 to approve or 0 to reject");
								if (timeSheetChoice == 1) {
									timeSheets.get(id).setStatus(TimeSheetStatus.APPROVED);
									System.out.println("Time sheet Approved.");
								} else if (timeSheetChoice == 0) {
									timeSheets.get(id).setStatus(TimeSheetStatus.REJECTED);
									System.out.println("Time sheet Rejected.");
								} else
									timeSheetChoice = (Integer) customChoice("Integer", "Please enter a valid choice");
							} else {
								System.out.println("Timesheet doesn't exist or invalid timesheet id");
							}
						}
						// enterChoice();

					} else {
						System.out.println("No time sheets to approve");
					}
				} catch (Exception e) {
					System.out.println("Error while processing timesheets");
				}
				break;
			case 5:
				logOut();
				break;

			default:
				System.out.println("No such operation");
				break;
			}
		}
	}

	public void saleConsultantMenu() {
		SaleConsultant caller = (SaleConsultant) currentUser;
		logOut = false;
		while (!logOut) {

			System.out.println("\n*******Sale Consultant Menu******");
			System.out.printf("1.Schedule inspection");
			System.out.println();
			System.out.printf("2.Submit Timesheet");
			System.out.println();
			System.out.printf("3. Log Out");
			System.out.println();

			enterChoice();

			switch (choice) {

			case 1:
				while (true) {
					System.out.println("List of properties which have been assigned to you");
					Set<String> props = new HashSet<String>();
					for (Property property : allProperties) {
						if (property.getEmployee() != null && property.getEmployee().getId().equals(caller.getId())) {
							System.out.println(property.getPropertyID());
							props.add(property.getPropertyID());
						}
					}
					if (props.size() == 0)
						break;
					else if (caller.inspectProperty(allProperties, props).equals("success")) {
						System.out.println("Property is set to be inspected");
						break;
					}

				}
				break;

			case 2:
				TimeSheet timeSheet = caller.submitTimeSheet();
				if (timeSheet == null) {
					System.out.println("Full time employees can't submit time sheets");
				} else {
					timeSheets.put(timeSheet.getId(), timeSheet);
					System.out.println("Time sheet has been submitted successfully");
				}
				break;
			case 3:
				logOut();
				break;

			default:
				System.out.println("No such operation");
				break;
			}
		}
	}

	
	public void propertyManagerMenu() {
		PropertyManager caller = (PropertyManager) currentUser;
		logOut = false;
		while (!logOut) {

			System.out.println("\n*******Property Manager Menu******");
			System.out.printf("1.Display all properties");
			System.out.println();
			System.out.printf("2.Schedule inspection");
			System.out.println();
			System.out.printf("3.Paying bills and fee");
			System.out.println();
			System.out.printf("4.Submit Timesheet");
			System.out.println();
			System.out.printf("5. Log Out");
			System.out.println();

			enterChoice();

			switch (choice) {
			case 1:
				displayAllProperties();
				break;
			case 2:
				while (true) {
					System.out.println("List of properties which have been assigned to you");
					Set<String> props = new HashSet<String>();
					for (Property property : allProperties) {
						if (property.getEmployee() != null && property.getEmployee().getId().equals(caller.getId())) {
							System.out.println(property.getPropertyID());
							props.add(property.getPropertyID());
						}
					}
					if (props.size() == 0)
						break;
					else if (caller.inspectProperty(allProperties, props).equals("success")) {
						System.out.println("Property is set to be inspected");
						break;
					}

				}
				break;
			case 3:
				System.out.println("<<tobe updated>>");
				break;

			case 4:
				TimeSheet timeSheet = caller.submitTimeSheet();
				if (timeSheet == null) {
					System.out.println("Full time employees can't submit time sheets");
				} else {
					timeSheets.put(timeSheet.getId(), timeSheet);
					System.out.println("Time sheet has been submitted successfully");
				}
				break;

			case 5:
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
				viewAllOffer();
				break;
			case 6:
				reviewOffer();
				break;
			case 7:
				viewAllAuctions();
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
				makeOffer();
				break;
			case 5:
				makeBid();
				break;
			case 6:
//				makeSaleDeposit();
				makeDeposit();
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

	private void branchAdminMenu() {
		BranchAdmin caller = (BranchAdmin) currentUser;
		logOut = false;
		while (!logOut) {
			System.out.println("\n*******Branch Admin Menu******");
			System.out.printf("1.Run Payroll");
			System.out.println();
			System.out.printf("2.Submit timeSheet");
			System.out.println();
			enterChoice();
			switch (choice) {
			case 1:
				try {
					caller.paySalary();
					System.out.println("Payrole ran");
				} catch (Exception e) {
					System.out.println("There was problem running payrole");
				}
				break;
			case 2:
				caller.submitTimeSheet();
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

	// buyer methods

	public void makeOffer() {
		try {
			quitToMainMenu = false;
			while (!quitToMainMenu) {
				String title = "Add Property ID:";
				String propID = addPropertyID(title);
				if (quitToMainMenu)
					break;
				Property currentProp = allProperties.get(currentPropertyIndex);
				if (currentProp instanceof SaleProperty
						&& ((SaleProperty) currentProp).getSaleType() == SaleType.NEGOTIATION) {

					if (currentProp.getStatus() != PropertyStatus.Available) {
						throw new StatusException("This property is not available");
					} else {
						title = "Offer amount:";
						double offer = ValidateFunction.addMonetaryInfo(title);

						if (((SaleProperty) currentProp).createOffer(propID, (Buyer) currentUser, offer)) {
							quitToMainMenu = true;
						}

					}
				} else {
					throw new TypeException(
							"Cannot make offer.This is property is not a sale by negotiation property.");
				}

			}
		} catch (TypeException e) {
			System.out.println(e.getReason());
		} catch (StatusException stt) {
			System.out.println(stt.getReason());
		} catch (AmountException e) {
			System.out.println(e.getReason());
		} catch (Exception e) {
			System.out.println("Cannot apply for this property");
			e.printStackTrace();
		}
	}

	public void makeBid() {
		String userId = currentUser.getUserID();
		quitToMainMenu = false;
		while (!quitToMainMenu) {
			String title = "Add Auction ID or Q to quit:";
			addAuctionID(title); // validate input
			if (quitToMainMenu)
				break;

			if (currentAuc.getAuctionStatus() != AuctionStatus.OPENING) {
				System.out.println("This Auction is currently not opened");
			} else {
				currentAuc.makeBid(userId, currentDate);
				quitToMainMenu = true;

			}

		}
	}

//	public void makeSaleDeposit() {
//		String userId= currentUser.getUserID();
//		quitToMainMenu = false;
//		while (!quitToMainMenu) {
//			String title = "Add Auction ID or Q to quit:";
//			addAuctionID(title); //validate input
//			if (quitToMainMenu)
//				break;
//			
//			if(currentAuc.getAuctionStatus()==AuctionStatus.CLOSED) {
//				ArrayList<Bid> allBids= currentAuc.getAllBids();
//				
//				for(Bid bid:allBids) {
//					if(userId.compareTo(bid.getCreatorID())==0 && bid.getStatus()==BidStatus.ACCEPTED) {
//						bid.receivedDeposit(); //set bid deposit status to true
//						currentAuc.checkHighestBidStatus();//update auction status after payment
//						currentSaleProp.setStatusToUnderContract(); //update property status
//						
//						//perform banking transactions here....
//						
//					}
//				}
//			}
//			else {
//					System.out.println("Cannot make deposit for this auction. It is either "
//							+ "not yet Closed or your bid is not yet Accepted by the vendor.");
//
//			}
//			
//								
//		}
//	}

	// vendor methods

	public void viewAllOffer() {
		for (Property prop : allProperties) {
			if (prop instanceof SaleProperty && ((SaleProperty) prop).getSaleType() == SaleType.NEGOTIATION) {
				ArrayList<Offer> allOffers = ((SaleProperty) prop).getAllOffers();

				for (Offer offer : allOffers) {
					System.out.println(offer.getOfferDetails());
				}
			}
		}
	}

	public void reviewOffer() {
		try {
			System.out.println("Accept or reject Offer or  press Q to quit");
			quitToMainMenu = false;
			while (!quitToMainMenu) {
				String title = "Add Offer ID:";
				offerID = addOfferID(title);
				if (offerID.compareTo("q") == 0 || offerID.compareTo("q") == 0) {
					quitToMainMenu = true;
					break;
				}
				if (quitToMainMenu) {
					break;
				}

				if (currentSaleProp.getCreatorID().compareTo(currentUser.getUserID()) != 0) {
					System.out.println("This is not an offer made to your property");
					break;
				}

				if (currentOffer.getOfferStatus() == ApplicationStatus.Rejected) {
					throw new StatusException("This offer has been previously rejected. This cannot be undone");

				} else if (currentOffer.getOfferStatus() == ApplicationStatus.Accepted) {
					throw new StatusException("This offer has been previously accepted. This cannot be undone");
				} else {
					acceptOrRejectOffer();
					quitToMainMenu = true;
				}
			}
		} catch (StatusException e) {
			System.out.println(e.getReason());
		} catch (Exception e) {
			System.out.println("Cannot respond to offer. Try again.");
		}
	}

	public void acceptOrRejectOffer() {
		quitToMainMenu = false;
		while (!quitToMainMenu) {

			System.out.println("Select 1 option [1-3]");
			System.out.printf("1. Accept Offer");
			System.out.println();
			System.out.printf("2.Reject Offer");
			System.out.println();
			System.out.printf("3. Back to Main Menu");
			System.out.println();

			enterChoice();

			try {
				switch (choice) {
				case 1:
					// accept application

					acceptOffer();

					quitToMainMenu = true;
					break;
				case 2:
					rejectOffer();
					quitToMainMenu = true;
					break;

				case 3:
					quitToMainMenu = true;
					break;

				default:
					System.out.println("No such operation");
					break;
				}
			} catch (StatusException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getReason());
			}
		}
	}

	public void acceptOffer() throws StatusException {
		// accept offer
		if (currentOffer.getOfferStatus() == ApplicationStatus.Rejected) {
			throw new StatusException("This offer status is currently Rejected. This cannot be undone.");

		} else if (currentOffer.getOfferStatus() == ApplicationStatus.Accepted) {
			throw new StatusException("This offer status is currently Accepted. No further work needed.");
		} else {
			currentOffer.acceptOffer(currentDate);

			System.out.println("Current Offer status is:" + currentOffer.getOfferStatus());

			// set property status to in process
			currentSaleProp.setStatusToInprocess();
			System.out.println("Property:" + currentSaleProp.getPropertyID() + " status is now InProcess");

			// reject all other applications of the same property
			ArrayList<Offer> allOffers = currentSaleProp.getAllOffers();
			for (Offer offer : allOffers) {
				if (offer.getOfferID().compareTo(currentOffer.getOfferID()) != 0) {
					offer.rejectOffer();
				}
			}
		}
	}

	public void rejectOffer() throws StatusException {
		if (currentOffer.getOfferStatus() == ApplicationStatus.Rejected) {
			throw new StatusException("This offer has been previously rejected. No further work needed");

		} else if (currentOffer.getOfferStatus() == ApplicationStatus.Accepted) {
			throw new StatusException("This offer has been previously accepted. This cannot be undone");
		} else {
			currentOffer.rejectOffer();

			System.out.println("Current Offer status is:" + currentOffer.getOfferStatus());
		}
	}

	public void viewAllAuctions() {
		String userID = currentUser.getUserID();

		for (Property prop : allProperties) {
			if (prop instanceof SaleProperty && ((SaleProperty) prop).getSaleType() == SaleType.AUCTION) {

				if (prop.getCreatorID().compareTo(userID) == 0) {

					ArrayList<Auction> allAucs = ((SaleProperty) prop).getAllAuctions();

					for (Auction auc : allAucs) {
						System.out.println(auc.getAuctionDetails());

					}
				}

			}
		}
	}

	public void createAuction() {
		quitToMainMenu = false;
		while (!quitToMainMenu) {
			String title = "Add Property ID or Q to quit:";
			addPropertyID(title);
			if (quitToMainMenu)
				break;
			Property currentProp = allProperties.get(currentPropertyIndex);

			if (currentProp.getCreatorID().compareTo(currentUser.getUserID()) != 0) {
				System.out.println("Cannot create auction as this is not your property");
				break;
			}
			if (currentProp instanceof SaleProperty && ((SaleProperty) currentProp).getSaleType() == SaleType.AUCTION) {
				if (currentProp.getStatus() == PropertyStatus.Available) {
					if (((SaleProperty) currentProp).createAuction()) {
						quitToMainMenu = true;
						break;
					} else
						System.out.println("Cannot add new auction to property");
				} else
					System.out.println("This property is not available. Cannot add new auction.");

			} else
				System.out.println("This is not a sale by auction property");
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
				quitToMainMenu = true;
				break;
			default:
				System.out.println("No such operation");
				break;
			}
		}
	}

	public void addSalebyNego() {
		System.out.println("adding property for sale by negotiation");
		boolean validProperty = false;
		while (!validProperty) {
			try {
				System.out.println("Enter details of your property below:");

				String title = "Address:";
				String address = ValidateFunction.addTextInfo(title);

				title = "Description:";
				String description = ValidateFunction.addTextInfo(title);

				title = "Surbub:";
				String surbub = ValidateFunction.addTextInfo(title);

				title = "Number of Bedrooms: ";
				int bed = ValidateFunction.addCapacity(title);

				title = "Number of Bathrooms: ";
				int bath = ValidateFunction.addCapacity(title);

				title = "Number of car Spaces: ";
				int cars = ValidateFunction.addCapacity(title);

				title = "Property type (house/ unit/flat/townhouse/studio):";
				String type = ValidateFunction.addTextInfo(title);

				title = "Minimum price:";
				double min = ValidateFunction.addMonetaryInfo(title);

				String currentSessionID = currentUser.getUserID();

				SaleProperty negoProp = new SaleProperty(currentSessionID, address, description, surbub, bed, bath,
						cars, type, min);

				allProperties.add(negoProp);

				System.out.println("Successfully add new Property for sale by negotiation");

				validProperty = true;
			} catch (Exception e) {
				System.out.println("Cannot add new property");
			}
		}
	}

	public void addSalebyAuct() {
		boolean validProperty = false;
		while (!validProperty) {
			try {
				System.out.println("Enter details of your property below:");

				String title = "Address:";
				String address = ValidateFunction.addTextInfo(title);

				title = "Description:";
				String description = ValidateFunction.addTextInfo(title);

				title = "Surbub:";
				String surbub = ValidateFunction.addTextInfo(title);

				title = "Number of Bedrooms: ";
				int bed = ValidateFunction.addCapacity(title);

				title = "Number of Bathrooms: ";
				int bath = ValidateFunction.addCapacity(title);

				title = "Number of car Spaces: ";
				int cars = ValidateFunction.addCapacity(title);

				title = "Property type (house/ unit/flat/townhouse/studio):";
				String type = ValidateFunction.addTextInfo(title);

				String currentSessionID = currentUser.getUserID();

				SaleProperty aucProp = new SaleProperty(currentSessionID, address, description, surbub, bed, bath, cars,
						type);
				aucProp.setSaleByAuction();

				allProperties.add(aucProp);

				System.out.println("Successfully add new Property for sale by auction");

				validProperty = true;
			} catch (Exception e) {
				System.out.println("Cannot add new auction property");
			}
		}
	}

	// tenant methods

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
						throw new StatusException("This property is not available");
					} else {
						title = "Monthly income:";
						double income = ValidateFunction.addMonetaryInfo(title);

						title = "Occupation: ";
						String occu = ValidateFunction.addTextInfo(title);

						title = "Proposed Weekly Rent: ";
						double rent = ValidateFunction.addMonetaryInfo(title);

						title = "Contract Duration: ";
						String duration = ValidateFunction.addTextInfo(title);

						Application newApp = new Application(propID, (Tenant) currentUser, income, occu, rent,
								duration);
						if (((RentalProperty) currentProp).handleApplication(newApp)) {

							System.out.println("Successfully apply for the property:" + currentProp.getPropertyID());
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
		System.out.println("\n*******My Application******");

		String currentUserID = currentUser.getUserID();
		for (int i = 0; i < allProperties.size(); i++) {
			if (allProperties.get(i) instanceof RentalProperty) {
				ArrayList<Application> allApps = ((RentalProperty) allProperties.get(i)).getAllApplications();

				for (int j = 0; j < allApps.size(); j++) {
					String tenantID = allApps.get(j).getTenant().getUserID();
					if (currentUserID.compareTo(tenantID) == 0) {
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

	// landlord methods
	public void displayMyProperties() {
		System.out.println("n*******My Properties******");
		String currentSessionID = currentUser.getUserID();

		for (int i = 0; i < allProperties.size(); i++) {
			if (allProperties.get(i).getCreatorID().compareTo(currentSessionID) == 0) {
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
				String address = ValidateFunction.addTextInfo(title);

				title = "Description:";
				String description = ValidateFunction.addTextInfo(title);

				title = "Surbub:";
				String surbub = ValidateFunction.addTextInfo(title);

				title = "Number of Bedrooms: ";
				int bed = ValidateFunction.addCapacity(title);

				title = "Number of Bathrooms: ";
				int bath = ValidateFunction.addCapacity(title);

				title = "Number of car Spaces: ";
				int cars = ValidateFunction.addCapacity(title);

				title = "Property type (house/ unit/flat/townhouse/studio):";
				String type = ValidateFunction.addTextInfo(title);

				title = "Weekly Rent:";
				double weeklyRent = ValidateFunction.addMonetaryInfo(title);

				title = "Contract Duration: ";
				String duration = ValidateFunction.addTextInfo(title);

				String currentSessionID = currentUser.getUserID();
				System.out.println(address + surbub);
				RentalProperty rentProp = new RentalProperty(currentSessionID, address, description, surbub, bed, bath,
						cars, type, weeklyRent, duration);

				allProperties.add(rentProp);

				System.out.println("Successfully add new Rental Property");

				validProperty = true;
			} catch (Exception e) {
				System.out.println("Cannot add new rental property");
			}
		}
	}

	public void displayApplications() {
		System.out.println("\n*******All Applications******");
		// loops through All properties array of the current Landlord then
		// loops through All applications array in each property
		// then print each application details

		String currentSessionID = currentUser.getUserID();

		for (int i = 0; i < allProperties.size(); i++) {
			if (allProperties.get(i).getCreatorID().compareTo(currentSessionID) == 0) {

				ArrayList<Application> allApps = ((RentalProperty) allProperties.get(i)).getAllApplications();
				for (int j = 0; j < allApps.size(); j++) {
					System.out.println(allApps.get(j).getApplicationdetails());
				}
			}
		}
	}

	public void respondtoApplication() {
		try {
			System.out.println("Accept or reject Application or  press Q to quit");
			quitToMainMenu = false;
			while (!quitToMainMenu) {
				String title = "Add Application ID:";
				appID = addApplicationID(title);
				if (appID.compareTo("q") == 0 || appID.compareTo("q") == 0) {
					quitToMainMenu = true;
					break;
				}
				if (quitToMainMenu) {
					break;
				}

				if (currentRentProp.getCreatorID().compareTo(currentUser.getUserID()) != 0) {
					System.out.println("This is not an application made to your property");
					break;
				}

				if (currentApp.getAppStatus() == ApplicationStatus.Rejected) {
					throw new StatusException("This application has been previously rejected. This cannot be undone");

				} else if (currentApp.getAppStatus() == ApplicationStatus.Accepted) {
					throw new StatusException("This application has been previously accepted. This cannot be undone");
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

			try {
				switch (choice) {
				case 1:
					// accept application

					acceptApplication();

					quitToMainMenu = true;
					break;
				case 2:
					rejectApplication();
					quitToMainMenu = true;
					break;

				case 3:
					quitToMainMenu = true;
					break;

				default:
					System.out.println("No such operation");
					break;
				}
			} catch (StatusException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getReason());
			}
		}
	}

	public void rejectApplication() throws StatusException {
		if (currentApp.getAppStatus() == ApplicationStatus.Rejected) {
			throw new StatusException("This application has been previously rejected. This cannot be undo");

		} else if (currentApp.getAppStatus() == ApplicationStatus.Accepted) {
			throw new StatusException("This application has been previously accepted. This cannot be undo");
		} else {
			currentApp.rejectApp();
			ApplicationStatus currentappStatus = currentApp.getAppStatus();
			System.out.println("Current Application status is:" + currentappStatus);
		}
	}

	public void acceptApplication() throws StatusException {
		// accept application
		if (currentApp.getAppStatus() == ApplicationStatus.Rejected) {
			throw new StatusException("This application status is currently Rejected. No further work needed.");

		} else if (currentApp.getAppStatus() == ApplicationStatus.Accepted) {
			throw new StatusException("This application status is currently Accepted. No further work needed.");
		} else {
			currentApp.acceptApp();
			ApplicationStatus currentappStatus = currentApp.getAppStatus();
			System.out.println("Current Application status is:" + currentappStatus);
			ArrayList<Application> allApps = currentRentProp.getAllApplications();

			// set property status to in process
			for (Property prop : allProperties) {
				if (prop instanceof RentalProperty) {
					if (((RentalProperty) prop).getAllApplications().contains(currentApp)) {
						prop.setStatusToInprocess();
						System.out.println("Property:" + prop.getPropertyID() + " status is now InProcess");
					}
				}

			}

			// reject all other applications of the same property
			for (int i = 0; i < allApps.size(); i++) {
				if (appID.compareTo(allApps.get(i).getApplicationID()) != 0) {
					allApps.get(i).rejectApp();
				}

			}
		}
	}

	public Object customChoice(String type, String pringtingPhrase) {
		if (type.equals("String")) {
			while (true) {
				System.out.println(pringtingPhrase);
				return scan.nextLine();
			}
		} else if (type.equals("int")) {
			boolean valid = false;
			while (!valid) {
				try {
					System.out.println(pringtingPhrase);
					Integer ch = Integer.parseInt(scan.nextLine());
					valid = true;
					return ch;
				} catch (Exception e) {
					System.out.println("Not a valid choice. Re-enter");
				}
			}
		}
		return null;
	}

	// accessors/mutators
	public ArrayList<User> getAllUsers() {
		return allUsers;
	}

	public ArrayList<Property> getAllProperty() {
		return allProperties;
	}

	public Map<String, TimeSheet> getTimeSheets() {
		return timeSheets;
	}

	public void setCurrentApp(Application app) {
		// for JUnit Application Testing only
		currentApp = app;
	}

	public void setCurrenProp(RentalProperty rp) {
		// for JUnit Application Testing only
		currentRentProp = rp;
	}

	public void setAppID(String ID) {
		// for JUntApplication Testing only
		this.appID = ID;
	}
	// general methods

	public static void searchProperty() {
		System.out.println("\n*******Search Properties******");
		String title = "Enter a surbub";
		String surb = ValidateFunction.addTextInfo(title);
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

	public static boolean searchSurbub(String id) {
		try {
			for (Property prop : allProperties) {

				if (prop.getSurbub().compareTo(id) == 0) {
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
					break;

				}
				if (!PropertyIdExists(iD)) {
					throw new IdNotFound("Property not found");
				}
				infoOk = true;
				return iD;
			} catch (IdNotFound ex) {
				System.out.println(ex.getReason());

			} catch (Exception e) {
				System.out.println("Invalid. Re-enter ID");

			}
		}
		return iD;
	}

	public boolean PropertyIdExists(String ID) {
		for (int i = 0; i < allProperties.size(); i++) {

			if (allProperties.get(i).getPropertyID().compareTo(ID) == 0) {
				System.out.println(allProperties.get(i).getPropertyID() + " found!");
				currentPropertyIndex = i; // record index of current post in allPost
				return true;
			}
		}
		;
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
					break;

				}
				if (!ApplicationIdExits(iD)) {
					throw new IdNotFound("Application not found");
				}
				infoOk = true;
				return iD;
			} catch (IdNotFound ex) {
				System.out.println(ex.getReason());

			} catch (Exception e) {
				System.out.println("Invalid. Re-enter ID");

			}
		}
		return iD;
	}

	public boolean ApplicationIdExits(String ID) {

		for (int i = 0; i < allProperties.size(); i++) {
			if (allProperties.get(i) instanceof RentalProperty) {
				ArrayList<Application> allApps = ((RentalProperty) allProperties.get(i)).getAllApplications();
				currentRentProp = ((RentalProperty) allProperties.get(i));

				for (int j = 0; j < allApps.size(); j++) {
					if (allApps.get(j).getApplicationID().compareTo(ID) == 0) {
						System.out.println(allApps.get(j).getApplicationID() + "found!");

						currentApp = allApps.get(j);

						currentPropertyIndex = i;
						return true;
					}
				}
			}

		}
		return false;
	}

	public String addOfferID(String title) {
		boolean infoOk = false;
		String iD = null;
		while (!infoOk) {
			try {
				System.out.println(title);
				iD = scan.nextLine();
				if (iD.charAt(0) == 'Q' || iD.charAt(0) == 'q') {
					infoOk = true;
					quitToMainMenu = true;
					break;

				}
				if (!offerIdExists(iD)) {
					throw new IdNotFound("Offer not found");
				}
				infoOk = true;
				return iD;
			} catch (IdNotFound ex) {
				System.out.println(ex.getReason());

			} catch (Exception e) {
				System.out.println("Invalid. Re-enter ID");
				e.printStackTrace();

			}
		}
		return iD;
	}

	public boolean offerIdExists(String ID) {
		for (Property prop : allProperties) {
			if (prop instanceof SaleProperty && ((SaleProperty) prop).getSaleType() == SaleType.NEGOTIATION) {
				ArrayList<Offer> allOffers = ((SaleProperty) prop).getAllOffers();
				for (Offer offer : allOffers) {
					if (offer.getOfferID().compareTo(ID) == 0) {
						System.out.println(offer.getOfferID() + " found!");
						currentSaleProp = (SaleProperty) prop;
						currentOffer = offer;
						return true;
					}
				}
			}

		}
		return false;
	}

	public String addAuctionID(String title) {
		boolean infoOk = false;
		String iD = null;
		while (!infoOk) {
			try {
				System.out.println(title);
				iD = scan.nextLine();
				if (iD.charAt(0) == 'Q' || iD.charAt(0) == 'q') {
					infoOk = true;
					quitToMainMenu = true;
					break;

				}
				if (!auctionIdExist(iD)) {
					throw new IdNotFound("Auction not found");
				}
				infoOk = true;
				return iD;
			} catch (IdNotFound ex) {
				System.out.println(ex.getReason());

			} catch (Exception e) {
				System.out.println("Invalid. Re-enter ID");

			}
		}
		return iD;
	}

	public boolean auctionIdExist(String iD) {

		for (Property property : allProperties) {
			if (property instanceof SaleProperty && ((SaleProperty) property).getSaleType() == SaleType.AUCTION) {
				currentSaleProp = (SaleProperty) property;
				ArrayList<Auction> allAucs = ((SaleProperty) property).getAllAuctions();

				for (Auction auction : allAucs) {
					if (auction.getAuctionID().compareTo(iD) == 0) {
						System.out.println(auction.getAuctionID() + " found!");

						currentAuc = auction;
						return true;
					}
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
			int mins = Integer.parseInt(scan.nextLine());
			System.out.println("Enter number of seconds to advance");
			int sec = Integer.parseInt(scan.nextLine());

			DateTime previousDate = currentDate;
			time = currentDate.getTime();
			System.out.println("Previous date:" + previousDate);
			DateTime.setAdvance(0, hours, mins, sec);

			currentDate = new DateTime(time);

			System.out.println("Current date:" + currentDate);
//			int diff = DateTime.diffMins(currentDate, previousDate);
//			System.out.println(diff + " minutes " + "has passed since the previous date.");
			System.out.println(DateTime.diffSecond(currentDate, previousDate) + " seconds " + "has passed");

			checkApplicationStatus(); // update application status after time passes
			checkAuctionStatus(); // update auction status after time passes
			checkOfferStatus(); // update offer status after time passes

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error with input. Try again");
			e.printStackTrace();
		}

	}

	public void checkAuctionStatus() {
		for (Property prop : allProperties) {
			if (prop instanceof SaleProperty && ((SaleProperty) prop).getSaleType() == SaleType.AUCTION) {
				for (Auction auc : ((SaleProperty) prop).getAllAuctions()) {
					//auc.checkAuctionStatus();
				}
			}
		}
	}

	public void checkOfferStatus() {
		for (Property prop : allProperties) {
			if (prop instanceof SaleProperty && ((SaleProperty) prop).getSaleType() == SaleType.NEGOTIATION) {
				for (Offer offer : ((SaleProperty) prop).getAllOffers()) {
					offer.checkOfferStatus(prop);
				}
			}
		}
	}

	public void checkApplicationStatus() {
		for (Property prop : allProperties) {
			if (prop instanceof RentalProperty) {
				for (Application app : ((RentalProperty) prop).getAllApplications()) {
					app.checkAppStatus(prop);

				}
			}
		}
	}

	public void makeFinalPayment() {

		try {
			quitToMainMenu = false;
			while (!quitToMainMenu) {
				String title = "Add Property ID or Q to quit:";
				addPropertyID(title);
				if (quitToMainMenu)
					break;
				Property currentProp = allProperties.get(currentPropertyIndex);

				if (currentProp instanceof SaleProperty) {

					// if current user is also the one who paid deposit
					if (currentProp.getStatus() == PropertyStatus.UnderContract
							&& ((SaleProperty) currentProp).getDepositor().compareTo(currentUser.getUserID()) == 0) {

						currentProp.setStatusToSold();
						((SaleProperty) currentProp).setBuyer((Buyer) currentUser); // set buyer
						System.out.println("Sale Property: " + currentProp.getPropertyID() + " has been sold.");

						// setup property value
						// using variables: acceptedBidAmount and acceptedOfferamount

					}

				} else
					throw new Exception("This is not a sale property.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void makeDeposit() {
		try {
			quitToMainMenu = false;
			while (!quitToMainMenu) {
				String title = "Add Property ID or Q to quit:";
				addPropertyID(title);
				if (quitToMainMenu)
					break;
				Property currentProp = allProperties.get(currentPropertyIndex);

				// deposit for rental property
				if (currentProp instanceof RentalProperty) {

					ArrayList<Application> allApps = ((RentalProperty) currentProp).getAllApplications();

					if (allApps.size() > 0) {
						for (Application app : allApps) {

							// if user has accepted application
							if (app.getAppStatus() == ApplicationStatus.Accepted
									&& app.getTenant().getUserID().compareTo(currentUser.getUserID()) == 0) {

								currentProp.setStatusToLet(); // set property status to Let
								((RentalProperty) currentProp).setTenantID(currentUser.getUserID()); // set tenant ID to
																										// rent property

								System.out.println(
										"Deposit has been received for rental property:" + currentProp.getPropertyID());
								System.out.println("Status of rental property:" + currentProp.getPropertyID()
										+ " is now:" + currentProp.getStatus());

								// perform any banking transactions here....

							} else if (allApps.indexOf(app) == allApps.size() - 1) {
								throw new Exception("Cannot make deposit to this property."
										+ "You don't have any accepted Application for it.");
							}
						}
					} else
						throw new Exception("There's not yet any application for this property");

					// deposit for sale by negotiation property
				} else if (currentProp instanceof SaleProperty
						&& ((SaleProperty) currentProp).getSaleType() == SaleType.NEGOTIATION) {

					ArrayList<Offer> allOffers = ((SaleProperty) currentProp).getAllOffers();

					if (allOffers.size() > 0) {

						for (Offer offer : allOffers) {

							// if user has accepted offer
							if (offer.getOfferStatus() == ApplicationStatus.Accepted
									&& offer.getBuyerID().compareTo(currentUser.getUserID()) == 0) {

								currentProp.setStatusToInprocess(); // set sale prop status to in process
								((SaleProperty) currentProp).setDepositor(currentUser.getUserID()); // set user id for
																									// depositor
								System.out.println("Deposit has been received for sale property by negotiation:"
										+ currentProp.getPropertyID());
								System.out.println("Status of sale property by nego:" + currentProp.getPropertyID()
										+ " is now:" + currentProp.getStatus());

								// perform any banking transactions here....

							} else if (allOffers.indexOf(offer) == allOffers.size() - 1) {
								throw new Exception("Cannot make deposit to this property."
										+ "You don't have any accepted offer for it.");
							}
						}
					} else
						throw new Exception("There's not yet any offer for this property");

					// deposit for sale by auction
				} else if (currentProp instanceof SaleProperty
						&& ((SaleProperty) currentProp).getSaleType() == SaleType.AUCTION) {

					ArrayList<Auction> allAucs = ((SaleProperty) currentProp).getAllAuctions();

					if (allAucs.size() > 0) {

						for (Auction auc : ((SaleProperty) currentProp).getAllAuctions()) {
							ArrayList<Bid> allBids = auc.getAllBids();

							if (allBids.size() > 0) {

								for (Bid bid : allBids) {
									if (currentUser.getUserID().compareTo(bid.getCreatorID()) == 0
											&& bid.getStatus() == BidStatus.ACCEPTED) {
										bid.receivedDeposit(); // set bid deposit status to true
										//auc.checkHighestBidStatus();// update auction status after payment
										currentProp.setStatusToUnderContract(); // update property status
										((SaleProperty) currentProp).setDepositor(currentUser.getUserID()); // set user
																											// id for
																											// depositor

										// perform any banking transactions here....

									} else {
										throw new Exception("Cannot make deposit to this property."
												+ "You don't have any accepted bid for it.");
									}
								}

							} else
								throw new Exception("There's not yet any bid for this property");

						}

					} else
						throw new Exception("There's not yet any auction for this property");

				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
