package mc.gaia.search.validate;

import java.util.HashMap;

import mc.gaia.logging.Logger;
import mc.gaia.search.description.RegionDescription;
import mc.gaia.search.description.SearchDescription;
import amidst.minecraft.Biome;

public class RegionSetValidator {

    private boolean valid;
    private SearchDescription searchDescription;
    private Integer searchArea;
    private int totalMinBlocks;
    private float totalMinPercent;
    private HashMap<String, Integer> numberOfTimesBiomeListed;

    public RegionSetValidator(final SearchDescription searchDescription) {
        this.valid = true;
        this.searchDescription = searchDescription;
        if(searchDescription == null) {
            return;
        }

        this.searchArea = searchDescription.getSearchArea();

        this.totalMinBlocks = 0;
        this.totalMinPercent = 0;

        this.numberOfTimesBiomeListed = new HashMap<String, Integer>();
        for(final String biome : Biome.biomeMap.keySet()) {
            this.numberOfTimesBiomeListed.put(biome, new Integer(0));
        }
    }

    public boolean isValid() {
        if(this.searchDescription == null) {
            Logger.error("Need to specify a search description.");
            return false;
        }

        if(this.searchDescription.regions == null) {
            Logger.error("Need to specify search regions.");
            return false;
        }

        // Loop over every region description.
        for(final RegionDescription region : this.searchDescription.regions) {

            // Calculate variables used to check validity of set of regions.
            if(region.minBlocks != null) {
                this.totalMinBlocks += region.minBlocks;
            }

            if(region.minPercent != null) {
                this.totalMinPercent += region.minPercent;
            }

            for(final String biome : region.biomes) {
                final Integer count = this.numberOfTimesBiomeListed.get(biome);
                this.numberOfTimesBiomeListed.put(biome, new Integer(count + 1));
            }

            // Check validity of this region.
            if(new RegionValidator(region, this.searchArea).isValid() == false) {
                this.valid = false;
            }
        }

        // Check the combination of region descriptions.
        checkTotalMinBlocksLessThanTotalArea();
        checkTotalMinPercentLessThanOneHundred();
        checkEachBiomeNotListedMoreThanOnce();

        return this.valid;
    }

    private void checkTotalMinBlocksLessThanTotalArea() {
        if(this.searchArea == null) {
            return;
        }

        if(this.totalMinBlocks > this.searchArea) {
            this.valid = false;
            Logger.error("The sum of the \"minBlocks\" variables for all regions must be less than or equal to the total area \""
                    + this.searchArea + "m3\"");
        }
    }

    private void checkTotalMinPercentLessThanOneHundred() {
        if(this.totalMinPercent > 100) {
            this.valid = false;
            Logger.error("The sum of the \"minPercent\" variables for all regions must be less than or equal to 100%.");
        }
    }

    private void checkEachBiomeNotListedMoreThanOnce() {
        for(final String biome : this.numberOfTimesBiomeListed.keySet()) {
            final Integer count = this.numberOfTimesBiomeListed.get(biome);
            if(count > 1) {
                this.valid = false;
                Logger.error("The \"" + biome + "\" biome cannot be listed in more than one region.");
            }
        }
    }

}
