package mc.gaia.exception;

public class ConfigFileException extends AException {

	private static final long serialVersionUID = 1L;

	public ConfigFileException() {
		super("Configuration file not valid.");
	}

}
