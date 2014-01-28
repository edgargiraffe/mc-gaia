package mc.gaia.search.description;


public class SearchDescription {
	
	// Unset integer variables will be set to 0.
	private static final int UNSET = 0;
	
	// This way I can leave these as public without having to create accessors.
	// Gson can still fill these elements with the correct values.
	public final String worldType;
	public final String minecraftVersion;
	public final String spawn;
	
	public final int minX;
	public final int minY;
	public final int maxX;
	public final int maxY;
	
	public final RegionDescription[] regions;
	
	// Default UNSET values for all variables.
	public SearchDescription() {
		worldType = null;
		minecraftVersion = null;
		spawn = null;
		minX = UNSET;
		minY = UNSET;
		maxX = UNSET;
		maxY = UNSET;
		regions = null;
	}

}
