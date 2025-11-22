package exceptions;
/**
 * date: 10/29/2025
 * @author Lee Abraham
 * @version 1.8
 * 
 */
public class QueueException extends Exception {

	//Construction of QueQueException
	public QueueException () {
		super();
	}

	//Construction of QueueException with custom message.
	public QueueException(String Message) {
		super(Message);
	}

}
