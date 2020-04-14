import java.util.Scanner;

public class StartUp {

	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RealEstate re= new RealEstate();
		
		//hard-coded data
		Landlord landlord= new Landlord();
		Tenant tenant=new Tenant();
		BranchManager branman= new BranchManager();
		PropertyManager proman= new PropertyManager();
		
		RentalProperty prop01= new RentalProperty(landlord.getUserID(), "120 Abbey road", "This house is not for sale", "Corney Island", 4, 2, 2, "house", 180, "12 months or more");
		prop01.setStatusToAvailable();//for testing
		
		re.getAllUsers().add(landlord);
		re.getAllUsers().add(tenant);
		re.getAllUsers().add(branman);
		re.getAllUsers().add(proman);
		
		re.getAllProperty().add(prop01);
//		landlord.getAllProperties().add(prop01);
		
		re.landingPageMenu();
		
//		System.out.println(re.getAllCustomers().size());
//		System.out.println(re.getAllCustomers().get(0));
	}

}
