package mc.gaia.search.result;

import java.util.HashMap;

import mc.gaia.amidst.AmidstInterface;
import mc.gaia.search.description.RegionDescription;
import mc.gaia.search.description.SearchDescription;

public class RegionResults {
	
	public HashMap<String, RegionDescription> regionDescriptionMap;
	public HashMap<RegionDescription, Integer> regionBlockCountMap;
	
	public RegionResults(SearchDescription searchDescription, int[] biomeData) {
		if(searchDescription.regions == null) 
			return;
		
		// Initialize maps used to store data
		initializeRegionDescriptionMap(searchDescription);
		initializeRegionBlockCountMap(searchDescription);
		
		// Process the data so that we can query it later
		process(biomeData);
	}

	// Initialize and fill the map from biome names to search region.
	private void initializeRegionDescriptionMap(SearchDescription searchDescription) {
		regionDescriptionMap = new HashMap<String, RegionDescription>();
		
		for(RegionDescription region : searchDescription.regions) {
			for(String biome : region.biomes) {
				regionDescriptionMap.put(biome, region);
			}
		}
	}
	
	// Initialize and fill the map from region to number of blocks found.
	private void initializeRegionBlockCountMap(SearchDescription searchDescription) {
		regionBlockCountMap = new HashMap<RegionDescription, Integer>();
		
		for(RegionDescription region : searchDescription.regions) {
			regionBlockCountMap.put(region, new Integer(0));
		}
	}
	
	// Count how many blocks exist inside each region.
	private void process(int[] biomeData) {
		for(int biomeIndex : biomeData) {
			String biomeName = AmidstInterface.getBiomeName(biomeIndex);
			
			if(regionDescriptionMap.containsKey(biomeName)) {
				RegionDescription region = regionDescriptionMap.get(biomeName);
				Integer count = regionBlockCountMap.get(region);
				regionBlockCountMap.put(region, new Integer(count + 4));
			}
		}
	}

}
