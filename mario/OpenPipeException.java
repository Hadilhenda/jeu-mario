package mario;

public class OpenPipeException extends Exception {
	private Wall _pipe;
	
	public OpenPipeException(Wall pipe) {
		_pipe = pipe;
	}
	
	public Wall getPipe() {
		return _pipe;
	}
}
