package game;


public class IllegalMoveException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "Exception for moving";
	}

}
