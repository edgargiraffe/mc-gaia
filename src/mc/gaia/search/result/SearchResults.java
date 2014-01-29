package mc.gaia.search.result;

import mc.gaia.amidst.AmidstInterface;
import mc.gaia.exception.SearchFailedException;
import mc.gaia.exception.UnableToFindSpawnException;
import mc.gaia.search.description.RegionDescription;
import mc.gaia.search.description.SearchDescription;

public class SearchResults {
	
	private SearchDescription searchDescription;
	public RegionResults regionResults;
	private boolean successful;
	
	private int maxBlocks;
	private int minBlocks;
	private static final int UNSET = RegionDescription.UNSET;
	
	public SearchResults(SearchDescription searchDescription, int[] biomeData) throws UnableToFindSpawnException {
		this.searchDescription = searchDescription;
		this.regionResults = new RegionResults(searchDescription, biomeData);
		
		// If we're searching for a spawn, exit early if the spawn is in the wrong location.
		if(isValidSpawn() == false)
		{
			this.successful = false;
			return;
		}
		
		try {
			// Check if this seed meets the search requirements.
			// Throws a SearchFailedException if we find a criteria the seed does not meet.
			processRegionCounts();
			this.successful = true;
		} 
		catch (SearchFailedException e) {
			// This seed does not meet search requirements.
			this.successful = false;
		}
	}
	
	private boolean isValidSpawn() {
		if(this.searchDescription.spawn == null)
			return true;
		
		try {
			String biome = AmidstInterface.getSpawnBiome();
			return biome.equals(this.searchDescription.spawn);
		}
		catch (UnableToFindSpawnException e) {
			return false;
		}
	}
	
	private void processRegionCounts() throws SearchFailedException {
		if(this.searchDescription.regions == null) 
			return;
		
		for(RegionDescription region : this.searchDescription.regions) {
			
			this.maxBlocks = region.maxBlocks;
			this.minBlocks = region.minBlocks;
			
			Integer count = regionResults.regionBlockCountMap.get(region);
			
			if(this.maxBlocks == UNSET && this.minBlocks == UNSET) {
				existsSearch(count);
			}
			else if(this.maxBlocks == UNSET && this.minBlocks != UNSET) {
				minSearch(count);
			}
			else if(this.maxBlocks != UNSET && this.minBlocks == UNSET) {
				maxSearch(count);
			}
			else {
				minMaxSearch(count);
			}
		}
	}
	
	private void existsSearch(Integer count) throws SearchFailedException {
		if(count <= 0) 
			throw new SearchFailedException();
	}
	
	private void minSearch(Integer count) throws SearchFailedException {
		if(count < this.minBlocks) 
			throw new SearchFailedException();
	}
	
	private void maxSearch(Integer count) throws SearchFailedException {
		if(count > this.maxBlocks) 
			throw new SearchFailedException();
	}
	
	private void minMaxSearch(Integer count) throws SearchFailedException {
		if(count < this.minBlocks || count > this.maxBlocks) 
			throw new SearchFailedException();
	}
	
	public boolean searchSuccessful() {
		return this.successful;
	}


}
