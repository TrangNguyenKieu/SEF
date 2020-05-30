package users;

import java.io.Serializable;
import java.util.Map;
import Utilities.PropertyStatus;
import Utilities.TimeSheet;
import Utilities.TimeSheetStatus;
import properties.Property;
import properties.SaleProperty;
import startUp.RealEstate;

public class BranchAdmin extends Employee implements Serializable{

	private RealEstate re;
	private final static int hourlySalary = 25;

	public BranchAdmin(String name, boolean isFulltime, RealEstate re) {
		super(name, isFulltime);
		this.re = re;
	}

	public void paySalary() {
		Employee emp;
		SaleProperty saleProperty;
		double totalHours = 0.0d;
		Map<String, TimeSheet> timeSheets = re.getTimeSheets();
		for (User user : re.getAllUsers()) {
			if (user instanceof Employee) {
				emp = (Employee) user;
				if (!emp.isFulltime()) {
					for (TimeSheet timeSheet : timeSheets.values()) {
						if (timeSheet.getStatus().equals(TimeSheetStatus.APPROVED)
								&& timeSheet.getEmployeeId().equals(emp.getId())) {
							totalHours += timeSheet.getHours();
						}
					}
					emp.setSalary(totalHours * hourlySalary);
				} else if (emp instanceof SaleConsultant) {
					double commission = 0.0d;
					for (Property property : re.getAllProperty()) {
						if(property instanceof SaleProperty) {
							saleProperty = (SaleProperty) property;
							if (property.getEmployee() != null && property.getEmployee().getId().equals(emp.getId())
									&& property.getStatus().equals(PropertyStatus.Sold)
									&& saleProperty.getCommisionRate() != 0.0d) {
								commission += .4 * (saleProperty.getPropertyValue() * (saleProperty.getCommisionRate()/100));
							}
						}
					}
					emp.setSalary(Employee.BASE_SALARY + commission);
				} else {
					emp.setSalary(Employee.BASE_SALARY);
				}
				System.out.println("Salary of " + emp.getId() + " is " + emp.getSalary());
			}
		}
	}
}
