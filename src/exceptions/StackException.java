package exceptions;
/**
 * date: 10/29/2025
 * @author Lee Abraham
 * @version 1.8
 * 
 */
public class StackException extends RuntimeException{
	
	//Construction of StackException with message
	public StackException() {
		super();
	}
	
	//Construction of StackException with custom message.
	public StackException(String message) {
		super(message);
	}
	
}
