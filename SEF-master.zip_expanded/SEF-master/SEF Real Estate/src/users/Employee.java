package users;
public class Employee extends User{
	private static int employeeCount;
	private boolean isFulltime;
	private String name;
	private double salary;
	private String id;
	private float workingHours;
	private float hourlyRate;
	public Employee(String name,boolean isFulltime)
	{
		this.name=name;
		this.isFulltime=isFulltime;
		employeeCount++;
		//super.setUserID("EMP" + String.format("%0" + 3 + "d", employeeCount));
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public String getId()
	{
		return id;
	}

}
