package exceptions;
/**
 * date: 10/29/2025
 * @author Lee Abraham
 * @version 1.8
 * 
 */
public class QuequeException extends Exception {

	//Construction of QueQueException
	public QuequeException () {
		super();
	}

	//Construction of QueueException with custom message.
	public QuequeException(String Message) {
		super(Message);
	}

}
