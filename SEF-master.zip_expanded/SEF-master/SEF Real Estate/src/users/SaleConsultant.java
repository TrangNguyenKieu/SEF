package users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import properties.Property;
import properties.SaleProperty;

public class SaleConsultant extends Employee implements Serializable{
	private static int count;

	public SaleConsultant(String name, boolean fulltime) {
		super(name, fulltime);
		count++;
		// super.setUserID("SCON" + String.format("%0" + 3 + "d", count));
	}

	public String setComissionRate(ArrayList<Property> properties, Set<String> props) {
		SaleProperty saleProp;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the propertyId");
		String propId = sc.nextLine();

		if (!props.contains(propId)) {
			return "Wrong property";
		} else {
			System.out.println("Enter the commission rate");
			double rate = sc.nextDouble();
			for (Property property : properties) {
				if (property instanceof SaleProperty) {
					saleProp = (SaleProperty) property;
					if (saleProp.getPropertyID().equals(propId)) {
						saleProp.setCommisionRate(rate);
					}
				}
			}
			return "success";
		}
		
	}
}
