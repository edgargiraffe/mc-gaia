package mc.gaia.console;

import mc.gaia.search.description.SearchDescription;

public interface IConsoleIO {
	void DisplayDefaultConfigurationMessage();
	void DisplayUnknownLocalizationMessage();
	void DisplayStartup();
	void DisplaySearchDescription(SearchDescription curdesc);
	void DisplayBeginSearchMessage();
	boolean CheckIfUserTerminated();
	void DisplayTimeoutMessage();
	void DisplayShutdown();
}
