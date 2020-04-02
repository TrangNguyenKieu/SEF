import java.util.ArrayList;
import java.util.Scanner;

public class RealEstate {
	
	User currentUser;
	
	private int choice;
	private Scanner scan = new Scanner(System.in);
	private boolean quit; // quit the system
	private boolean quitToStudentMenu;
	private boolean logOut;
	private int currentIndex; //need to implement this in login() to locate employee in array
	
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
			System.out.printf("1. Display my properties");
			System.out.println();
			System.out.printf("2. Add property");
			System.out.println();
			System.out.printf("3. Display applications");
			System.out.println();
			System.out.printf("4. Respond to application");
			System.out.println();
			System.out.printf("5. Log Out");
			System.out.println();

			enterChoice();

			switch (choice) {
			case 1:
				System.out.println("<<tobe updated>>");
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
				System.out.println("<<tobe updated>>");
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
				System.out.println("<<tobe updated>>");
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
				System.out.println("Not a valid choice. Re-enter");
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
		System.out.println("All properties listing shown here");
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
	
	
	//accessors/mutators
	public ArrayList<User> getAllUsers(){
		return allUsers;
	}
}
