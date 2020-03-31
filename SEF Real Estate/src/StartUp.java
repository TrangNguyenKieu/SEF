
public class StartUp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RealEstate re= new RealEstate();
		Landlord landlord= new Landlord();
		Tenant tenant=new Tenant();
		re.getAllCustomers().add(landlord);
		re.getAllCustomers().add(tenant);
		
		re.landingPageMenu();
		
		System.out.println(re.getAllCustomers().size());
		System.out.println(re.getAllCustomers().get(0));
	}

}
