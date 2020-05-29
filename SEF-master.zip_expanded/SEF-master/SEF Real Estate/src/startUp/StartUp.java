package startUp;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

import Utilities.EmployeeType;
import properties.RentalProperty;
import properties.SaleProperty;
//import properties.SalebyAuction;
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

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		RealEstate re = new RealEstate();

		RealEstate.allUsers = FileReadWrite.readUserDetails(userFileName);//Updating User Array List

		// hard-coded data
		Landlord landlord = new Landlord();
		landlord.setUsername("LD");
		landlord.setPassword("cus");

		Tenant tenant = new Tenant();
		tenant.setUsername("TN");
		tenant.setPassword("cus");

		BranchManager branman = new BranchManager("Kriti", true);
		branman.setUsername("BM");
		branman.setPassword("emp");

		PropertyManager proman = new PropertyManager("PropertyMan", true);
		proman.setUsername("PM");
		proman.setPassword("emp");

		Buyer buyer = new Buyer();
		buyer.setUsername("BUY");
		buyer.setPassword("cus");

		Vendor vendor = new Vendor();
		vendor.setUsername("VD");
		vendor.setPassword("cus");

		BranchAdmin bradmin = new BranchAdmin("Branch Admin", true);
		bradmin.setUsername("BD");
		bradmin.setPassword("emp");

		RentalProperty prop01 = new RentalProperty(landlord.getUserID(), "120 Abbey road", "This house is not for sale",
				"Corney Island", 4, 2, 2, "house", 180, "12 months or more");
//		prop01.setStatusToAvailable();//for testing

		// hard code new sale for auction
		SaleProperty prop02 = new SaleProperty(vendor.getUserID(), "130 Rennie", "House for sale", "Melbourne", 3, 2, 1,
				"house");
		prop02.setStatusToAvailable();
		prop02.setSaleByAuction();

		re.getAllUsers().add(landlord);
		re.getAllUsers().add(tenant);
		re.getAllUsers().add(branman);
		re.getAllUsers().add(proman);
		re.getAllUsers().add(vendor);
		re.getAllUsers().add(buyer);
		re.getAllUsers().add(bradmin);

		// hard code new sale for auction 2
		SaleProperty prop04 = new SaleProperty("1234", "130 Rennie", "House for sale", "Melbourne", 3, 2, 1, "house");
		prop04.setStatusToAvailable();
		prop04.setSaleByAuction();

		// hardcode new sale by negotiation
		SaleProperty prop03 = new SaleProperty(vendor.getUserID(), "140 Danny", "House for nego", "Sydney", 3, 2, 1,
				"flat", 10000);
		prop03.setStatusToAvailable();

		// hardcode new sale by negotiation 2
		SaleProperty prop05 = new SaleProperty("abcd", "140 Danny", "House for nego", "Sydney", 3, 2, 1, "flat", 10000);
		prop05.setStatusToAvailable();

		re.getAllProperty().add(prop01);
		re.getAllProperty().add(prop02);
		re.getAllProperty().add(prop03);
		re.getAllProperty().add(prop04);// for testing multiple vendors handling
		re.getAllProperty().add(prop05);// for testing multiple vendors handling
		re.landingPageMenu();

//		System.out.println(re.getAllCustomers().size());
//		System.out.println(re.getAllCustomers().get(0));
	}

}
