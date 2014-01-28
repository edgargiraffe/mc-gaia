package mc.gaia.console;

public class ConsoleIOFactory {
	public static IConsoleIO GetIOForLocalization(String localizationcode) {
		switch(localizationcode) {
		case "eng":
			return new ConsoleIO_eng();
		default:
			return null;
		}
	}
}
