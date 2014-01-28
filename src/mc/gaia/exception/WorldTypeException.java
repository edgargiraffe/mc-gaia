package mc.gaia.exception;

public class WorldTypeException extends AException {

	private static final long serialVersionUID = 1L;
	
	public WorldTypeException(String worldType) {
		super("Invalid world type \"" + worldType + "\"");
	}

}
