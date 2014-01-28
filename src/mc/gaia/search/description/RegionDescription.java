package mc.gaia.search.description;

public class RegionDescription {
	// Unset integer variables will be set to -1.
	public static final int UNSET = -1;
	
	public final String[] biomes;
	public final int maxBlocks;
	public final int minBlocks;
	
	private RegionDescription() {
		biomes = null;
		maxBlocks = UNSET;
		minBlocks = UNSET;
	}
	
}
