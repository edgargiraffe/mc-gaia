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

    private Integer maxBlocks;
    private Integer minBlocks;

    public SearchResults(final SearchDescription searchDescription, final int[] biomeData) throws UnableToFindSpawnException {
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
        } catch(final SearchFailedException e) {
            // This seed does not meet search requirements.
            this.successful = false;
        }
    }

    private boolean isValidSpawn() {
        if(this.searchDescription.spawn == null) {
            return true;
        }

        try {
            final String biome = AmidstInterface.getSpawnBiome();
            return biome.equals(this.searchDescription.spawn);
        } catch(final UnableToFindSpawnException e) {
            return false;
        }
    }

    private void processRegionCounts() throws SearchFailedException {
        if(this.searchDescription.regions == null) {
            return;
        }

        for(final RegionDescription region : this.searchDescription.regions) {

            this.maxBlocks = region.maxBlocks;
            this.minBlocks = region.minBlocks;
            final float multiplier = this.searchDescription.getSearchArea() / 100;
            if(region.maxPercent != null) {
                this.maxBlocks = new Integer((int) (region.maxPercent * multiplier));
            }
            if(region.minPercent != null) {
                this.minBlocks = new Integer((int) (region.minPercent * multiplier));
            }

            final Integer count = this.regionResults.regionBlockCountMap.get(region);

            if(this.maxBlocks == null && this.minBlocks == null) {
                existsSearch(count);
            }
            else if(this.maxBlocks == null && this.minBlocks != null) {
                minSearch(count);
            }
            else if(this.maxBlocks != null && this.minBlocks == null) {
                maxSearch(count);
            }
            else {
                minMaxSearch(count);
            }
        }
    }

    private void existsSearch(final Integer count) throws SearchFailedException {
        if(count <= 0) {
            throw new SearchFailedException();
        }
    }

    private void minSearch(final Integer count) throws SearchFailedException {
        if(count < this.minBlocks) {
            throw new SearchFailedException();
        }
    }

    private void maxSearch(final Integer count) throws SearchFailedException {
        if(count > this.maxBlocks) {
            throw new SearchFailedException();
        }
    }

    private void minMaxSearch(final Integer count) throws SearchFailedException {
        if(count < this.minBlocks || count > this.maxBlocks) {
            throw new SearchFailedException();
        }
    }

    public boolean searchSuccessful() {
        return this.successful;
    }

}
