package mc.gaia.exception;

public class SearchFailedException extends AException {

	private static final long serialVersionUID = 1L;

	public SearchFailedException() {
		super("This seed does not meet the search requirements.");
	}

}
