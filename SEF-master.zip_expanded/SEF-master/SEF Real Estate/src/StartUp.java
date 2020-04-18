import java.util.Scanner;

import Utilities.EmployeeType;

public class StartUp {

	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RealEstate re= new RealEstate();
		
		//hard-coded data
		Landlord landlord= new Landlord();
		Tenant tenant=new Tenant();

		BranchManager branman= new BranchManager("Kriti",true);
		PropertyManager proman= new PropertyManager("PropertyMan",true);
		Buyer buyer= new Buyer();
		Vendor vendor= new Vendor();
		BranchAdmin bradmin= new BranchAdmin();
		
		RentalProperty prop01= new RentalProperty(landlord.getUserID(), "120 Abbey road", "This house is not for sale", "Corney Island", 4, 2, 2, "house", 180, "12 months or more");
		prop01.setStatusToAvailable();//for testing
		SalebyAuction prop02= new SalebyAuction(vendor.getUserID(), "130 Rennie", "House for sale", "Melbourne", 3, 2, 1, "house");
		prop02.setStatusToAvailable();

		
		re.getAllUsers().add(landlord);
		re.getAllUsers().add(tenant);
		re.getAllUsers().add(branman);
		re.getAllUsers().add(proman);
		re.getAllUsers().add(vendor);
		re.getAllUsers().add(buyer);
		re.getAllUsers().add(bradmin);
		
		re.getAllProperty().add(prop01);
		re.getAllProperty().add(prop02);
//		landlord.getAllProperties().add(prop01);
		
		re.landingPageMenu();
		
//		System.out.println(re.getAllCustomers().size());
//		System.out.println(re.getAllCustomers().get(0));
	}

}
