package Utilities;

import users.Employee;

public class TimeSheet {
	
	private String comments;
	private static int timeSheetCount;
	private String id;
	private TimeSheetStatus status;
	private String week;
	private double hours;
	private Employee emp;
	
	public TimeSheet(Employee emp,double hours,String week)
	{
		++timeSheetCount;
		this.emp=emp;
		this.hours=hours;
		this.week=week;
		this.status=TimeSheetStatus.NEW;
		this.id="TMS" + String.format("%0" + 3 + "d", timeSheetCount);
	}
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getEmployeeId()
	{
		return this.emp.getId();
	}
	
	public double getHours()
	{
		return this.hours;
	}
	public String getWeek()
	{
		return this.week;
	}
	public TimeSheetStatus getStatus()
	{
		return this.status;
	}

	public void setStatus(TimeSheetStatus status) {
		this.status = status;
	}

	public String getId() {
		return this.id;
	}

}
