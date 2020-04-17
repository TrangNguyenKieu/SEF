import Utilities.PropertyStatus;

public abstract class SaleProperty extends Property {

	public SaleProperty(String creatorID, String address, String des, String surbub, int bed, int bath, int cars, String type) {
		super(creatorID, address, des, surbub,bed,bath,cars,type);
		
	}
	
	public boolean assignEmployee(Employee emp) {
		if (super.getStatus()==PropertyStatus.Pending) {
			if(emp instanceof SaleConsultant) {
				
				super.setEmployee(emp);
				super.setStatusToAvailable();
				System.out.println("Successfully assign employee to the property");
						
				return true;
			} else {
				System.out.println("Cannot assign someone who is not a sale consultant to a sale property");
				return false;
			}
			
		}else return false;
		
	}
}
