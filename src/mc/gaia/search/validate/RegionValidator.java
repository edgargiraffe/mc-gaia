package mc.gaia.search.validate;

import mc.gaia.amidst.AmidstInterface;
import mc.gaia.logging.Logger;
import mc.gaia.search.description.RegionDescription;

public class RegionValidator {
	
	private boolean valid;
	private RegionDescription region;
	private final int totalArea;
	
	public RegionValidator(RegionDescription region, int totalArea) {
		this.valid = true;
		this.region = region;
		this.totalArea = totalArea;
	}
	
	public boolean isValid() {
		if(this.region == null) {
			Logger.error("Region is null. Do you have an extra comma somewhere?");
			return false;
		}
		
		blocksAndPercentBothSet(region.minBlocks, region.minPercent, "min");
		blocksAndPercentBothSet(region.maxBlocks, region.maxPercent, "max");
		
		greaterThanZero(region.maxBlocks, "maxBlocks");
		greaterThanZero(region.minBlocks, "minBlocks");
		
		greaterThanZero(region.maxPercent, "maxPercent");
		greaterThanZero(region.minPercent, "minPercent");
		lessThanOneHundred(region.maxPercent, "maxPercent");
		lessThanOneHundred(region.minPercent, "minPercent");
		
		minBlocksLessThanMaxArea();
		minLessThanMax(region.minBlocks, region.maxBlocks, "Blocks");
		minLessThanMax(region.minPercent, region.maxPercent, "Percent");
		
		hasAtLeastOneBiome();
		
		if(this.region.biomes != null) {
			for(String biome : this.region.biomes) 
				isValidBiome(biome);
		}
		
		return valid;
	}
	
	private void blocksAndPercentBothSet(int blocks, float percent, String name) {
		if(blocks != RegionDescription.UNSET && percent != RegionDescription.UNSET) {
			this.valid = false;
			Logger.error(String.format("Variables \"%sBlocks\" and \"%sPercent\" cannot both be set.", name, name));
		}
		
	}

	private void hasAtLeastOneBiome() {
		if(this.region.biomes == null || this.region.biomes.length == 0) {
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
	
	private void greaterThanZero(float area, String name) {
		if(area < RegionDescription.UNSET) {
			this.valid = false;
			Logger.error("Variable \"" + name + "\" must be greater than or equal to zero.");
		}
	}
	
	private void lessThanOneHundred(float percentArea, String name) {
		if(percentArea > 100) {
			this.valid = false;
			Logger.error(String.format("Variable \"%s\" cannot be greater than 100", name) + "%.");
		}
	}
	
	private void minBlocksLessThanMaxArea() {
		if(this.region.minBlocks > this.totalArea) {
			this.valid = false;
			Logger.error("Variable \"minBlocks\" must be less than or equal to the total searched area \"" + this.totalArea + "m3\".");
		}
	}
	
	private void minLessThanMax(float min, float max, String name) {
		if(max == RegionDescription.UNSET)
			return;
		
		if(min > max) {
			this.valid = false;
			Logger.error(String.format("A region's \"min%s\" must be less than or equal to it's \"max%s\".", name, name));
		}
	}
	
}
