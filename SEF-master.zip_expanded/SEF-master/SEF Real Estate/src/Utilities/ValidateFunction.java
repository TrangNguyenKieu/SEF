package Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import SystemExceptions.DateException;
import SystemExceptions.FormatException;
import startUp.RealEstate;

public class ValidateFunction {
	//to keep all input validation functions and supporting functions 
	
	static Scanner scan=new Scanner(System.in);
	

	public static double addMonetaryInfo(String title) {
		boolean infoOk = false;
		double rent = 0;
		while (!infoOk) {
			try {
				System.out.print(title);
				rent = Double.parseDouble(scan.nextLine());
				infoOk = true;
				return rent;
			} catch (Exception e) {
				System.out.println("Error. Re-enter " + title);

			}
		}
		return rent;
	}
	

	public static int addCapacity(String title) {
		boolean infoOk = false;
		int capa = 0;
		while (!infoOk) {
			try {
				System.out.print(title);
				capa = Integer.parseInt(scan.nextLine());
				infoOk = true;
				return capa;
			} catch (Exception e) {
				System.out.println("Error. Re-enter " + title);

			}
		}
		return capa;
	}
	public static String addTextInfo(String title) {
		boolean infoOk = false;
		String info = null;
		while (!infoOk) {
			try {
				System.out.print(title);
				info = scan.nextLine();

				if (info.trim().length() == 0) {
					throw new FormatException("The field must not be blank");

				}

				infoOk = true;
				return info;

			} catch (FormatException fe) {
				System.out.println(fe.getReason());

			} catch (Exception e) {
				System.out.println("Error. Re-enter " + title);
			}
		}
		return info;
	}
	
	
	public static DateTime addDate() {
		boolean infoOk = false;
		DateTime aucDate = new DateTime();
		while (!infoOk) {
			try {
				System.out.print("Date (dd/mm/yyyy):");
				String date = scan.nextLine();

				if (date.length() == 0) {
					throw new FormatException("You must enter a text value");
				}

				if (date.trim().length() == 0) {
					throw new FormatException("The field must not be blank");
				}

				if (!checkDateFormat(date)) {
					throw new FormatException("Date must be in correct format: dd/mm/yyyy");
				}

				SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
				Date d = (Date) formatter1.parse(date);
				long time = d.getTime();
				long c_time = RealEstate.currentDate.getTime();

				if (time <= c_time) {
					throw new DateException("Auction date cannot be prior to current date");
				} else {
					aucDate = new DateTime(time, 0);
					infoOk = true;
					return aucDate;
				}

			} catch (FormatException fe) {
				System.out.println(fe.getReason());
			} catch (DateException e) {
				System.out.println(e.getReason());
			} catch (Exception e) {
				System.out.println("Error. Re-enter date");
				e.printStackTrace();
			}
		}
		return aucDate;
	}

	public static boolean checkDateFormat(String str) {
		if (str.matches("\\d{2}+/\\d{2}+/\\d{4}")) {
//			System.out.println("true");
			return true;

		} else {
//			System.out.println("false");
			return false;
		}
	}
	

}
