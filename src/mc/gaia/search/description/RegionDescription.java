package mc.gaia.search.description;

public class RegionDescription {
	// Unset integer variables will be set to -1.
	public static final int UNSET = -1;
	
	public final String[] biomes;
	public final int maxBlocks;
	public final int minBlocks;
	public final float maxPercent;
	public final float minPercent;
	
	public RegionDescription() {
		biomes = null;
		maxBlocks = UNSET;
		minBlocks = UNSET;
		maxPercent = UNSET;
		minPercent = UNSET;
	}
	
	public RegionDescription(
			String[] biomes, 
			int maxBlocks, 
			int minBlocks, 
			float maxPercent, 
			float minPercent) {
		this.biomes = biomes;
		this.maxBlocks = maxBlocks;
		this.minBlocks = minBlocks;
		this.maxPercent = maxPercent;
		this.minPercent = minPercent;
	}
	
}
