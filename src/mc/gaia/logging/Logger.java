package mc.gaia.logging;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Logger {
	
	private static final String PREFIX = "[MC Gaia] ";
	private static final String ERROR = "ERROR: ";
	private static final String WARNING = "WARNING: ";
	private static final String INFO = "INFO: ";
	private static final String RESULT = "RESULT: ";
	
	private static final PrintStream consoleStdOut = System.out;
	private static final PrintStream consoleStdErr = System.err;
	
	private static final OutputStream toNullStream = new OutputStream() {
		@Override
		public void write(int arg0) throws IOException {}
	};
	
	private static final PrintStream toNull = new PrintStream(toNullStream);
	
	public static void log(String message) {
		System.out.println(PREFIX + message);
	}
	
	public static void error(String message) {
		log(ERROR + message);
	}
	
	public static void warning(String message) {
		log(WARNING + message);
	}
	
	public static void info(String message) {
		log(INFO + message);
	}
	
	public static void result(String message) {
		log(RESULT + message);
	}
	
	public static void disableConsole() {
		System.setOut(toNull);
		System.setErr(toNull);
	}
	
	public static void enableConsole() {
		System.setOut(consoleStdOut);
		System.setErr(consoleStdErr);
	}
	
}
