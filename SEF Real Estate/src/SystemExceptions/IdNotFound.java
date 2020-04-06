package SystemExceptions;

public class IdNotFound extends Exception{
private String reason;
	
	public IdNotFound (String reason) {
		this.reason=reason;
	}
	
	public String getReason() {
			return reason;
			}
}
