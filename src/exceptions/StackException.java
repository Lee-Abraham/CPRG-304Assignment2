package exceptions;
/**
 * date: 11/25/2025
 * @author Lee Abraham, Benjamin Noel
 * @version 2.0
 * 
 */
public class StackException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	//Construction of StackException with message
	public StackException() {
		super();
	}
	
	//Construction of StackException with custom message.
	public StackException(String message) {
		super(message);
	}
	
}
