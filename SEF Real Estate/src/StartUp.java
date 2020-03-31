
public class StartUp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RealEstate re= new RealEstate();
		
		//hard-coded data
		Landlord landlord= new Landlord();
		Tenant tenant=new Tenant();
		BranchManager branman= new BranchManager();
		PropertyManager proman= new PropertyManager();
		re.getAllUsers().add(landlord);
		re.getAllUsers().add(tenant);
		re.getAllUsers().add(branman);
		re.getAllUsers().add(proman);
		
		re.landingPageMenu();
		
//		System.out.println(re.getAllCustomers().size());
//		System.out.println(re.getAllCustomers().get(0));
	}

}
