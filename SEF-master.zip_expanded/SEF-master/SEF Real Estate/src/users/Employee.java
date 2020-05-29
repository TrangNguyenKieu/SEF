package users;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import Utilities.TimeSheet;
import properties.Property;

public class Employee extends User{
	protected static final double BASE_SALARY=1000;
	private static int employeeCount;
	private boolean isFulltime;
	private String name;
	private double salary;
	private String id;
	private float workingHours;
	private float hourlyRate;
	TimeSheet timeSheet;
	public Employee(String name,boolean isFulltime)
	{
		this.name=name;
		this.isFulltime=isFulltime;
		employeeCount++;
		this.id="EMP" + String.format("%0" + 3 + "d", employeeCount);
	}
	
	

	public boolean isFulltime() {
		return isFulltime;
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
	
	/*
	 * Creates time sheet for the User
	 * Returns timeSheet if the user is part time else returns null
	 */
	public TimeSheet submitTimeSheet()
	{
		if(this.isFulltime)
		{
			return null;
		}
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter week number");
		String week=sc.next();
		System.out.println("Enter hours worked");
		double hours=sc.nextDouble();
		
		timeSheet=new TimeSheet(this,hours,week);
		return timeSheet;
	}
	
	public String inspectProperty(ArrayList<Property> properties,Set<String> props)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the propertyId");
		String propId=sc.nextLine();
		System.out.println("Enter the date and time of inspection");
		String insDate=sc.nextLine();
		
		if(!props.contains(propId))
		{
			return "Wrong property";
		}
		return "success";
		
	}

}
