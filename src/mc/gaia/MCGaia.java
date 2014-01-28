package mc.gaia;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import mc.gaia.amidst.AmidstInterface;
import mc.gaia.console.ConsoleIOFactory;
import mc.gaia.console.IConsoleIO;
import mc.gaia.exception.AException;
import mc.gaia.logging.Logger;
import mc.gaia.search.SearchBuilder;
import mc.gaia.search.description.SearchDescription;
import mc.gaia.search.result.SearchResults;

public class MCGaia {
	
	final static long TIMEOUTMS = 60 * 1000; //maximum is one minute; 60 seconds
	final static String DEFAULT_LOCALIZATION = "eng";

	public static void main(String[] args) throws Exception {
		
		//declarations we know will not fail
		IConsoleIO localization = ConsoleIOFactory.GetIOForLocalization(DEFAULT_LOCALIZATION);
		
		try {
			Random random = new Random();
			Date starttime = new Date();
			long elapsedms = 0;
			boolean successful = false; // initially, we haven't found any seed
			boolean userexited = false;

			//attempt to get the configuration
			Configuration currentconfig = Configuration.GetFromFile();
			
			if(currentconfig == null) {
				//we couldn't find it; display a message and use default
				localization.DisplayDefaultConfigurationMessage();
				currentconfig = Configuration.GetDefault();
			}
			
			//get correct localization
			 localization = ConsoleIOFactory.GetIOForLocalization(currentconfig.Localization);
			
			if(localization == null) {
				localization = ConsoleIOFactory.GetIOForLocalization(DEFAULT_LOCALIZATION);
				localization.DisplayUnknownLocalizationMessage();
			}
			
			localization.DisplayStartup();
			
			if(args.length == 0) {
				Logger.error("Please input the search description configuration file as a command line argument.");
				return;
			}
			
			SearchDescription searchDescription = SearchBuilder.create(args[0]);
			AmidstInterface.initialize(searchDescription.minecraftVersion);
			
			localization.DisplayBeginSearchMessage();
			
			int numSeedsTried = 0;
			while(!userexited && !successful) {
				
				long seed = random.nextLong();
				
				AmidstInterface.createWorld(seed, searchDescription.worldType);
			
				int[] biomeData = AmidstInterface.getBiomeData(searchDescription.minX, 
							searchDescription.minY, 
							searchDescription.maxX, 
							searchDescription.maxY);
		
				SearchResults results = new SearchResults(searchDescription, biomeData);
				successful = results.searchSuccessful();
				
				//if we were successful, print out the seed
				if(successful) {
					Logger.result("Seed = \"" + seed + "\".");
				}
				
				numSeedsTried++;
				
				//update time elapsed
				elapsedms = new Date().getTime() - starttime.getTime();
				userexited = localization.CheckIfUserTerminated();
				if(elapsedms > currentconfig.SearchTimeout) {
					throw new TimeoutException();
				}
			}
			
			Logger.info("Number of seeds tried = " + numSeedsTried);
			
		}
		catch (AException e) {
			Logger.error(e.getMessage());
		}
		catch (TimeoutException e) {
			localization.DisplayTimeoutMessage();
		}
		catch (Exception e) {
			Logger.error("Unknown error. Please refer to stack trace.");
			e.printStackTrace();
		}
		finally {
			localization.DisplayShutdown();
		}
	}

}
