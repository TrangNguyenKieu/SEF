package startUp;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import Utilities.EmployeeType;
import properties.RentalProperty;
import properties.SaleProperty;
import users.BranchAdmin;
import users.BranchManager;
import users.Buyer;
import users.Landlord;
import users.PropertyManager;
import users.Tenant;
import users.Vendor;
import Utilities.FileReadWrite;

public class StartUp implements Serializable{

	public static Scanner scan = new Scanner(System.in);
	public static String userFileName = "Users.txt";
	public static String propertyFileName = "Property.txt";
	public static String bidsFileName = "Bids.txt";
	public static String applicationFileName = "Applications.txt";
	public static String AuctionsFileName = "Auctions.txt";
	public static String OffersFileName = "Offers.txt";


	public static void main(String[] args) throws IOException, ClassNotFoundException {
		RealEstate re = new RealEstate();

		RealEstate.allUsers = FileReadWrite.readUserDetails(userFileName);	//Updating User Array List
		RealEstate.allProperties = FileReadWrite.readPropertyDetails(propertyFileName);	//Updating Property Array List
		
		re.landingPageMenu();

		//	Login Info 	
		// // landlord.setUsername("LD");
		// // landlord.setPassword("cus");


		// // tenant.setUsername("TN");
		// // tenant.setPassword("cus");


		// // branman.setUsername("BM");
		// // branman.setPassword("emp");


		// // proman.setUsername("PM");
		// // proman.setPassword("emp");

		// // buyer.setUsername("BUY");
		// // buyer.setPassword("cus");


		// // vendor.setUsername("VD");
		// // vendor.setPassword("cus");


		// // bradmin.setUsername("BD");
		// // bradmin.setPassword("emp");

	}

}
