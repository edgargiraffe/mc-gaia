package mc.gaia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;

public class Configuration {
	public static Configuration GetDefault() {
		return new Configuration();
	}
	
	public static Configuration GetFromFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("config.json"));
			return (new Gson()).fromJson(br, Configuration.class);
		}
		catch (FileNotFoundException e) {
			return null;
		}
	}
	
	public final long SearchTimeout;
	public final String Localization;
	
	private Configuration() {
		SearchTimeout = 60000;
		Localization = "eng";
	}
}
