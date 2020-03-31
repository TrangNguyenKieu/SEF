import java.util.ArrayList;
import java.util.Scanner;

public class RealEstate {
	
	Customer currentCustomer; 
	Employee currentEmployee;
	
	private int choice;
	private Scanner scan = new Scanner(System.in);
	private boolean quit; // quit the system
	private boolean quitToStudentMenu;
	private boolean logOut;
	private int currentIndex; //need to implement this in login() to locate employee in array
	
	ArrayList<Customer> allCustomers= new ArrayList<Customer>();
	ArrayList<Employee> allEmployees=new ArrayList<Employee>();
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
		System.out.println("Displaying landlord menu");
	}
	
	public void tenantMenu() {
		System.out.println("Displaying Tenant menu");
	}
	
	public void branchManager() {
		System.out.println("Displaying branch manager menu");
	}
	
	public void propertyManager() {
		System.out.println("Displaying branch manager menu");
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
			System.out.println("Login process..."+"\n");
			System.out.println("size:"+ allCustomers.size());
			//login process will need to determine from the input ID and password:
			//-whether current user is a Employee or Customer
			//-the index of the user in the related array to be stored in currentIndex.
			
			currentIndex=0; //after login this variable will store the index of current user in the array
			currentCustomer=allCustomers.get(currentIndex); //currently hard-coded in StartUp.java
			
			if(currentCustomer instanceof Landlord) {
				landLordMenu();
			} else if (currentCustomer instanceof Tenant) {
				tenantMenu();
			}
			infoOk=true;
		}
		
	}
	public void register() {
		System.out.println("Registration process..."+ "\n");
	}
	public void quit() {
		System.out.println("Program stops here.");
		quit = true;
	}
	
	public ArrayList<Customer> getAllCustomers(){
		return allCustomers;
	}
}
