package exceptions;
/**
 * date: 11/25/2025
 * @author Lee Abraham, Benjamin Noel
 * @version 2.0
 * 
 */
public class QueueException extends Exception {

	private static final long serialVersionUID = 1L;

	//Construction of QueueException
	public QueueException () {
		super();
	}

	//Construction of QueueException with custom message.
	public QueueException(String Message) {
		super(Message);
	}

}
