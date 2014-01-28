package mc.gaia.exception;

public class UnableToFindSpawnException extends AException {

	private static final long serialVersionUID = 1L;

	public UnableToFindSpawnException() {
		super("Unable to find a valid spawn location.");
	}

}
