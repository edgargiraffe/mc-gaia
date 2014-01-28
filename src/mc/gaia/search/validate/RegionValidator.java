package mc.gaia.search.validate;

import mc.gaia.amidst.AmidstInterface;
import mc.gaia.logging.Logger;
import mc.gaia.search.description.RegionDescription;

public class RegionValidator {

	private static final int MAX_COORD = 2048;
	private static final int MAX_AREA = MAX_COORD * MAX_COORD;
	
	private boolean valid;
	private RegionDescription region;
	
	public RegionValidator(RegionDescription region) {
		this.valid = true;
		this.region = region;
	}
	
	public boolean isValid() {
		
		greaterThanZero(region.maxBlocks, "maxBlocks");
		greaterThanZero(region.minBlocks, "minBlocks");
		maxBlocksLessThanMaxArea();
		minBlocksLessThanMaxBlocks();
		
		hasAtLeastOneBiome();
		
		for(String biome : this.region.biomes) 
			isValidBiome(biome);
		
		return valid;
	}
	
	private void hasAtLeastOneBiome() {
		if(this.region.biomes.length == 0) {
			this.valid = false;
			Logger.error("Each region must have at least one biome.");
		}
	}
	
	private void isValidBiome(String biome) {
		if(AmidstInterface.isValidBiome(biome) == false) {
			this.valid = false;
			Logger.error("Biome \"" + biome + "\" is not a valid biome.");
		}
	}
	
	private void greaterThanZero(int area, String name) {
		if(area < RegionDescription.UNSET) {
			this.valid = false;
			Logger.error("Variable \"" + name + "\" must be greater than or equal to zero.");
		}
	}
	
	private void maxBlocksLessThanMaxArea() {
		if(this.region.maxBlocks > MAX_AREA) {
			this.valid = false;
			Logger.error("Variable \"maxBlocks\" must be less than or equal to the maximum allowed area \"" + MAX_AREA + "m3\".");
		}
	}
	
	private void minBlocksLessThanMaxBlocks() {
		if(this.region.minBlocks > this.region.maxBlocks) {
			this.valid = false;
			Logger.error("A region's \"minBlocks\" must be less than or equal to it's \"maxBlocks\".");
		}
	}
	
}
