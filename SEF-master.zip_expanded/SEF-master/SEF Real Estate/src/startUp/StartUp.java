package startUp;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import Utilities.FileReadWrite;

public class StartUp implements Serializable{
	public static Scanner scan = new Scanner(System.in);
	public static String userFileName = "Users.txt";
	public static String propertyFileName = "Property.txt";
	public static RealEstate re= new RealEstate();
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		RealEstate.allUsers = FileReadWrite.readUserDetails(userFileName);	//Updating User Array List
		RealEstate.allProperties = FileReadWrite.readPropertyDetails(propertyFileName);	//Updating Property Array List

		re.landingPageMenu();
		
		// landlord.setUsername("LD");
		// landlord.setPassword("cus");

		// tenant.setUsername("TN");
		// tenant.setPassword("cus");

		// branman.setUsername("BM");
		// branman.setPassword("emp");

		// proman.setUsername("PM");
		// proman.setPassword("emp");

		// buyer.setUsername("BUY");
		// buyer.setPassword("cus");

		// vendor.setUsername("VD");
		// vendor.setPassword("cus");

		// bradmin.setUsername("BD");
		// bradmin.setPassword("emp");

		// saleConsultant.setUsername("SL");
		// saleConsultant.setPassword("emp");
	}

}
