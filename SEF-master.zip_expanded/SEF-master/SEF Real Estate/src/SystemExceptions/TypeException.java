package SystemExceptions;

public class TypeException extends Exception {
private String reason;
	
	public TypeException (String reason) {
		this.reason=reason;
	}
	
	public String getReason() {
			return reason;
			}
}
