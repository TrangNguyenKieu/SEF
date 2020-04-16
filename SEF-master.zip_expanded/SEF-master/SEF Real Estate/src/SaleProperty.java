
public abstract class SaleProperty extends Property {

	public SaleProperty(String creatorID, String address, String des, String surbub, int bed, int bath, int cars, String type) {
		super(creatorID, address, des, surbub,bed,bath,cars,type);
		
	}
	
	public abstract boolean assignEmployee(Employee emp);
}
