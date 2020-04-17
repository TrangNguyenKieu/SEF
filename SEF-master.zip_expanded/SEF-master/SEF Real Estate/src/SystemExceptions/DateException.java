package SystemExceptions;

public class DateException extends Exception {
private String reason;
	
	public DateException (String reason) {
		this.reason=reason;
	}
	
	public String getReason() {
			return reason;
			}
}
