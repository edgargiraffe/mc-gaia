package mc.gaia.console;

import java.io.IOException;

import mc.gaia.logging.Logger;
import mc.gaia.search.description.SearchDescription;

public class ConsoleIO_eng implements IConsoleIO {
	
	public void DisplayStartup() {
		Logger.info("Welcome to MC-Gaia.");
		Logger.info("MC-Gaia searches for a seed given a search descriptions.");
	}
	
	public void DisplaySearchDescription(SearchDescription curdescription) {
		//TODO
	}
	
	public void DisplayBeginSearchMessage() {
		Logger.info("Enter 'q' at any time to quit.");
		Logger.info("Searching...");
	}
	
	public boolean CheckIfUserTerminated() {
		try
		{
			while(System.in.available() > 0) {
				if(System.in.read() == 'q') {
					return true;
				}
			}
		}
		catch(IOException e) {
			//catch an io exception
			//TODO: Add logging
		}
		return false;
	}
	
	public void DisplayTimeoutMessage() {
		Logger.info("MC-Gaia timed out trying to find seed with given search description.");
	}
	
	public void DisplayShutdown() {
		Logger.info("MC-Gaia exited");
	}

	public void DisplayDefaultConfigurationMessage() {
		Logger.warning("Couldn't find the config.json file. Using default configuration.");
	}
	
	public void DisplayUnknownLocalizationMessage() {
		Logger.warning("Unknown or unsupported localization found; defaulting to using 'eng'.");
	}
}
