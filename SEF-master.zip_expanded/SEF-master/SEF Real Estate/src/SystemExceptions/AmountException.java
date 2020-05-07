package SystemExceptions;

public class AmountException extends Exception {
	private String reason;

	public AmountException(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}
}
