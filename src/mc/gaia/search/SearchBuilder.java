package mc.gaia.search;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import mc.gaia.exception.ConfigFileException;
import mc.gaia.logging.Logger;
import mc.gaia.search.description.SearchDescription;
import mc.gaia.search.validate.SearchValidator;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class SearchBuilder {
	
	// Creates a search description from JSON configuration file.
	// Throws FileNotFoundError if the given filename doesn't correspond to a valid file.
	// Throws ConfigFileException if the config file does not have valid parameters.
	public static SearchDescription create(String filename) throws ConfigFileException {
		SearchDescription search = fromJson(filename);
		
		if(isValid(search) == true) {
			return search;
		}
		else {
			throw new ConfigFileException();
		}
	}
	
	// Create a search description from JSON configuration file.
	// Throws FileNotFoundError if the given filename doesn't correspond to a valid file.
	private static SearchDescription fromJson(String filename) throws ConfigFileException {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			return (new Gson()).fromJson(br, SearchDescription.class);
		} 
		
		catch (FileNotFoundException e) {
			Logger.error("File \"" + filename + "\" not found.");
			throw new ConfigFileException();
		}
		
		catch (JsonSyntaxException e) {
			Logger.error("Json syntax error. " + e.getCause().getMessage() + ".");
			throw new ConfigFileException();
		}
		
	}

	// Check whether the search description parameters is valid.
	// Logs an error for each problem found in the description file.
	// Returns false if the search description is not valid.
	private static boolean isValid(SearchDescription search) {
		return (new SearchValidator(search)).isValid();
	}

	
}
