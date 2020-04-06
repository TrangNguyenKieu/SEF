package SystemExceptions;

public class StatusException extends Exception {
private String reason;
	
	public StatusException (String reason) {
		this.reason=reason;
	}
	
	public String getReason() {
			return reason;
			}
}
