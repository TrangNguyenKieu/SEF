package Utilities;
import java.util.Date;
public class DateTime {
	private static long advance; // keeps tracks of any time advance
	private long time;

	public DateTime() // constructor
	{
		time = System.currentTimeMillis() + advance;
	}

	public DateTime(long a) {
		time=a;
	}
	public long getTime() {
		return time;
	}
	// advances date/time by specified days, hours and mins for testing purpose
	public static void setAdvance(int days, int hours, int mins, int seconds)
	{ advance = (((((days * 24L + hours) * 60L)+mins) *60L)+ seconds) *1000L;
	}
	public String toString()
	{ long l = getTime();
	Date gct = new Date(l);
	return gct.toString();
	}
	public static String getCurrentTime() // returns current date/time
	{
	Date d = new Date(System.currentTimeMillis() + advance);
	return d.toString();
	}
	// returns difference in days
	public static int diffDays(DateTime d2, DateTime d1)
	{
	return (int) (1+ ( d2.getTime() - d1.getTime() )/(24L*60*60*1000));
	}
	
	// returns difference in hours
	public static int diffHours(DateTime d2, DateTime d1)
	{
	return (int) ( ( d2.getTime() - d1.getTime() )/(60L*60*1000));
	}

	// returns difference in mins
	public static int diffMins(DateTime d2, DateTime d1)
	{
	return (int) ( ( d2.getTime() - d1.getTime() )/(60L*1000));
	}

	// returns difference in seconds
	public static int diffSecond(DateTime d2, DateTime d1)
	{
	return (int) ( ( d2.getTime() - d1.getTime() )/(1000L));
	}
}
