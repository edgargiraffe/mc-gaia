package mc.gaia.search.validate;

import mc.gaia.amidst.AmidstInterface;
import mc.gaia.logging.Logger;
import mc.gaia.search.description.RegionDescription;

public class RegionValidator {

    private boolean valid;
    private final RegionDescription region;
    private final Integer searchArea;

    public RegionValidator(final RegionDescription region, final Integer searchArea) {
        this.valid = true;
        this.region = region;
        this.searchArea = searchArea;
    }

    public boolean isValid() {
        if(this.region == null) {
            Logger.error("Region is null. Do you have an extra comma somewhere?");
            return false;
        }

        blocksAndPercentBothSet(this.region.minBlocks, this.region.minPercent, "min");
        blocksAndPercentBothSet(this.region.maxBlocks, this.region.maxPercent, "max");

        greaterThanZero(this.region.maxBlocks, 0, "maxBlocks");
        greaterThanZero(this.region.minBlocks, 0, "minBlocks");

        greaterThanZero(this.region.maxPercent, 0f, "maxPercent");
        greaterThanZero(this.region.minPercent, 0f, "minPercent");
        lessThanOneHundred(this.region.maxPercent, "maxPercent");
        lessThanOneHundred(this.region.minPercent, "minPercent");

        minBlocksLessThanMaxArea();
        minLessThanMax(this.region.minBlocks, this.region.maxBlocks, "Blocks");
        minLessThanMax(this.region.minPercent, this.region.maxPercent, "Percent");

        hasAtLeastOneBiome();

        if(this.region.biomes != null) {
            for(final String biome : this.region.biomes) {
                isValidBiome(biome);
            }
        }

        return this.valid;
    }

    private void blocksAndPercentBothSet(final Number blocks, final Number percent, final String name) {
        if(blocks != null && percent != null) {
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

    private void isValidBiome(final String biome) {
        if(AmidstInterface.isValidBiome(biome) == false) {
            this.valid = false;
            Logger.error("Biome \"" + biome + "\" is not a valid biome.");
        }
    }

    private <T extends Comparable<T>> void greaterThanZero(final T value, final T zero, final String name) {
        if(value == null) {
            return;
        }

        if(value.compareTo(zero) < 0) {
            this.valid = false;
            Logger.error("Variable \"" + name + "\" must be greater than or equal to zero.");
        }
    }

    private void lessThanOneHundred(final Float percentArea, final String name) {
        if(percentArea == null) {
            return;
        }

        if(percentArea > 100) {
            this.valid = false;
            Logger.error(String.format("Variable \"%s\" cannot be greater than 100", name) + "%.");
        }
    }

    private void minBlocksLessThanMaxArea() {
        if(this.region.minBlocks == null || this.searchArea == null) {
            return;
        }

        if(this.region.minBlocks > this.searchArea) {
            this.valid = false;
            Logger.error("Variable \"minBlocks\" must be less than or equal to the total searched area \"" + this.searchArea + "m3\".");
        }
    }

    private <T extends Comparable<T>> void minLessThanMax(final T min, final T max, final String name) {
        if(min == null || max == null) {
            return;
        }

        if(max.compareTo(min) < 0) {
            this.valid = false;
            Logger.error(String.format("A region's \"min%s\" must be less than or equal to it's \"max%s\".", name, name));
        }
    }

}
