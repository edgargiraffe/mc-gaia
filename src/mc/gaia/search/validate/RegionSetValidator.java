package mc.gaia.search.validate;

import java.util.HashMap;

import amidst.minecraft.Biome;
import mc.gaia.logging.Logger;
import mc.gaia.search.description.RegionDescription;
import mc.gaia.search.description.SearchDescription;

public class RegionSetValidator {
	
	private boolean valid;
	private SearchDescription searchDescription;
	private int totalArea;
	private int totalMinBlocks;
	private float totalMinPercent;
	private HashMap<String, Integer> numberOfTimesBiomeListed;
	
	public RegionSetValidator(SearchDescription searchDescription) {
		this.valid = true;
		this.searchDescription = searchDescription;
		
		this.totalArea = searchDescription.getSearchArea();

		totalMinBlocks = 0;
		totalMinPercent = 0;
		
		numberOfTimesBiomeListed = new HashMap<String, Integer>();
		for(String biome : Biome.biomeMap.keySet()) {
			numberOfTimesBiomeListed.put(biome, new Integer(0));
		}
	}
	
	public boolean isValid() {
		if(searchDescription.regions == null)
			return valid;
		
		// Loop over every region description.
		for(RegionDescription region : searchDescription.regions) {
				
			// Calculate variables used to check validity of set of regions.
			if(region.minBlocks != RegionDescription.UNSET)
				totalMinBlocks += region.minBlocks;
				
			if(region.minPercent != RegionDescription.UNSET)
				totalMinPercent += region.minPercent;
			
			for(String biome : region.biomes) {
				Integer count = numberOfTimesBiomeListed.get(biome);
				numberOfTimesBiomeListed.put(biome, new Integer(count + 1));
			}
				
			// Check validity of this region.
			if((new RegionValidator(region, totalArea)).isValid() == false) 
				valid = false;
			}	
		
		// Check the combination of region descriptions.
		checkTotalMinBlocksLessThanTotalArea();
		checkTotalMinPercentLessThanOneHundred();
		checkEachBiomeNotListedMoreThanOnce();
		
		return valid;
	}
	
	private void checkTotalMinBlocksLessThanTotalArea() {
		if(totalMinBlocks > totalArea) {
			valid = false;
			Logger.error("The sum of the \"minBlocks\" variables for all regions must be less than or equal to the total area \"" 
			    + totalArea + "m3\"");
		}
	}
	
	private void checkTotalMinPercentLessThanOneHundred() {
		if(totalMinPercent > 100) {
			valid = false;
			Logger.error("The sum of the \"minPercent\" variables for all regions must be less than or equal to 100%.");
		}
	}
	
	private void checkEachBiomeNotListedMoreThanOnce() {
		for(String biome : numberOfTimesBiomeListed.keySet()) {
			Integer count = numberOfTimesBiomeListed.get(biome);
			if(count > 1) {
				valid = false;
				Logger.error("The \"" + biome + "\" biome cannot be listed in more than one region.");
			}
		}
	}

}
