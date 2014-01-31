package mc.gaia.junit.search.validate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import mc.gaia.search.description.RegionDescription;
import mc.gaia.search.description.SearchDescription;
import mc.gaia.search.validate.RegionSetValidator;

import org.junit.Test;

public class RegionSetValidatorTest {

    public static final int MIN_X = 0;
    public static final int MIN_Y = 0;
    public static final int MAX_X = 512;
    public static final int MAX_Y = 512;
    public static final int TEST_AREA = (MAX_X - MIN_X) * (MAX_Y - MIN_Y);

    public static final float LESS_THAN_HALF_PERCENT = 49.9f;
    public static final float HALF_PERCENT = 50.0f;
    public static final float MORE_THAN_HALF_PERCENT = 50.1f;

    public static final String FIRST_BIOME = "Ocean";
    public static final String SECOND_BIOME = "Plains";

    public static final String[] ONE_FIRST_BIOME = { FIRST_BIOME };
    public static final String[] ONE_SECOND_BIOME = { SECOND_BIOME };
    public static final String[] TWO_DIFFERENT_BIOMES = { FIRST_BIOME, SECOND_BIOME };
    public static final String[] TWO_DUPLICATE_BIOMES = { FIRST_BIOME, FIRST_BIOME };

    public final RegionDescription regionWithOneFirstBiome;
    public final RegionDescription regionWithOneSecondBiome;
    public final RegionDescription regionWithTwoDifferentBiomes;
    public final RegionDescription regionWithTwoDuplicateBiomes;

    public final RegionDescription regionWithLessThanHalfMinBlocks;
    public final RegionDescription regionWithHalfMinBlocks;
    public final RegionDescription regionWithHalfMinBlocksAndSecondBiome;
    public final RegionDescription regionWithMoreThanHalfMinBlocks;

    public final RegionDescription regionWithLessThanHalfMinPercent;
    public final RegionDescription regionWithHalfMinPercent;
    public final RegionDescription regionWithHalfMinPercentAndSecondBiome;
    public final RegionDescription regionWithMoreThanHalfMinPercent;

    public final SearchDescription nullSearch;
    public final SearchDescription emptySearch;

    public final SearchDescription searchWithOneRegionWithDifferentBiomes;
    public final SearchDescription searchWithOneRegionWithDuplicateBiomes;
    public final SearchDescription searchWithTwoRegionsWithDifferentBiomes;
    public final SearchDescription searchWithTwoRegionsWithDuplicateBiomes;

    public final SearchDescription searchWithOneRegionWithLessThanHalfMinBlocks;
    public final SearchDescription searchWithOneRegionWithHalfMinBlocks;
    public final SearchDescription searchWithOneRegionWithHalfMinBlocksAndSecondBiome;
    public final SearchDescription searchWithOneRegionWithMoreThanHalfMinBlocks;
    public final SearchDescription searchWithTwoRegionsWithMinBlocksLessThanTestArea;
    public final SearchDescription searchWithTwoRegionsWithMinBlocksEqualToTestArea;
    public final SearchDescription searchWithTwoRegionsWithMinBlocksGreaterThanTestArea;

    public final SearchDescription searchWithOneRegionWithLessThanHalfMinPercent;
    public final SearchDescription searchWithOneRegionWithHalfMinPercent;
    public final SearchDescription searchWithOneRegionWithHalfMinPercentAndSecondBiome;
    public final SearchDescription searchWithOneRegionWithMoreThanHalfMinPercent;
    public final SearchDescription searchWithTwoRegionsWithMinPercentLessThanTestArea;
    public final SearchDescription searchWithTwoRegionsWithMinPercentEqualToTestArea;
    public final SearchDescription searchWithTwoRegionsWithMinPercentGreaterThanTestArea;

    public RegionSetValidatorTest() {
        this.regionWithOneFirstBiome = new RegionDescription(ONE_FIRST_BIOME, null, null, null, null);
        this.regionWithOneSecondBiome = new RegionDescription(ONE_SECOND_BIOME, null, null, null, null);
        this.regionWithTwoDifferentBiomes = new RegionDescription(TWO_DIFFERENT_BIOMES, null, null, null, null);
        this.regionWithTwoDuplicateBiomes = new RegionDescription(TWO_DUPLICATE_BIOMES, null, null, null, null);

        this.regionWithLessThanHalfMinBlocks = new RegionDescription(ONE_FIRST_BIOME, null, TEST_AREA / 2 - 1, null, null);
        this.regionWithHalfMinBlocks = new RegionDescription(ONE_FIRST_BIOME, null, TEST_AREA / 2, null, null);
        this.regionWithHalfMinBlocksAndSecondBiome = new RegionDescription(ONE_SECOND_BIOME, null, TEST_AREA / 2, null, null);
        this.regionWithMoreThanHalfMinBlocks = new RegionDescription(ONE_FIRST_BIOME, null, TEST_AREA / 2 + 1, null, null);

        this.regionWithLessThanHalfMinPercent = new RegionDescription(ONE_FIRST_BIOME, null, null, null, LESS_THAN_HALF_PERCENT);
        this.regionWithHalfMinPercent = new RegionDescription(ONE_FIRST_BIOME, null, null, null, HALF_PERCENT);
        this.regionWithHalfMinPercentAndSecondBiome = new RegionDescription(ONE_SECOND_BIOME, null, null, null, HALF_PERCENT);
        this.regionWithMoreThanHalfMinPercent = new RegionDescription(ONE_FIRST_BIOME, null, null, null, MORE_THAN_HALF_PERCENT);

        final RegionDescription[] oneRegionWithDifferentBiomes = { this.regionWithTwoDifferentBiomes };
        final RegionDescription[] oneRegionWithDuplicateBiomes = { this.regionWithTwoDuplicateBiomes };
        final RegionDescription[] twoRegionsWithDifferentBiomes = { this.regionWithOneFirstBiome, this.regionWithOneSecondBiome };
        final RegionDescription[] twoRegionsWithDuplicateBiomes = { this.regionWithOneFirstBiome, this.regionWithOneFirstBiome };

        final RegionDescription[] oneRegionWithLessThanHalfMinBlocks = { this.regionWithLessThanHalfMinBlocks };
        final RegionDescription[] oneRegionWithHalfMinBlocks = { this.regionWithHalfMinBlocks };
        final RegionDescription[] oneRegionWithHalfMinBlocksAndSecondBiome = { this.regionWithHalfMinBlocksAndSecondBiome };
        final RegionDescription[] oneRegionWithMoreThanHalfMinBlocks = { this.regionWithMoreThanHalfMinBlocks };

        final RegionDescription[] twoRegionsWithMinBlocksLessThanTestArea = {
                this.regionWithLessThanHalfMinBlocks,
                this.regionWithHalfMinBlocksAndSecondBiome };

        final RegionDescription[] twoRegionsWithMinBlocksEqualToTestArea = {
                this.regionWithHalfMinBlocks,
                this.regionWithHalfMinBlocksAndSecondBiome };

        final RegionDescription[] twoRegionsWithMinBlocksGreaterThanTestArea = {
                this.regionWithMoreThanHalfMinBlocks,
                this.regionWithHalfMinBlocksAndSecondBiome };

        final RegionDescription[] oneRegionWithLessThanHalfMinPercent = { this.regionWithLessThanHalfMinPercent };
        final RegionDescription[] oneRegionWithHalfMinPercent = { this.regionWithHalfMinPercent };
        final RegionDescription[] oneRegionWithHalfMinPercentAndSecondBiome = { this.regionWithHalfMinPercentAndSecondBiome };
        final RegionDescription[] oneRegionWithMoreThanHalfMinPercent = { this.regionWithMoreThanHalfMinPercent };

        final RegionDescription[] twoRegionsWithMinPercentLessThanTestArea = {
                this.regionWithLessThanHalfMinPercent,
                this.regionWithHalfMinPercentAndSecondBiome };

        final RegionDescription[] twoRegionsWithMinPercentEqualToTestArea = { this.regionWithHalfMinPercent, this.regionWithHalfMinPercentAndSecondBiome };
        final RegionDescription[] twoRegionsWithMinPercentGreaterThanTestArea = {
                this.regionWithMoreThanHalfMinPercent,
                this.regionWithHalfMinPercentAndSecondBiome };

        this.nullSearch = null;
        this.emptySearch = new SearchDescription();

        this.searchWithOneRegionWithDifferentBiomes = createSearchDescription(oneRegionWithDifferentBiomes);
        this.searchWithOneRegionWithDuplicateBiomes = createSearchDescription(oneRegionWithDuplicateBiomes);
        this.searchWithTwoRegionsWithDifferentBiomes = createSearchDescription(twoRegionsWithDifferentBiomes);
        this.searchWithTwoRegionsWithDuplicateBiomes = createSearchDescription(twoRegionsWithDuplicateBiomes);

        this.searchWithOneRegionWithLessThanHalfMinBlocks = createSearchDescription(oneRegionWithLessThanHalfMinBlocks);
        this.searchWithOneRegionWithHalfMinBlocks = createSearchDescription(oneRegionWithHalfMinBlocks);
        this.searchWithOneRegionWithHalfMinBlocksAndSecondBiome = createSearchDescription(oneRegionWithHalfMinBlocksAndSecondBiome);
        this.searchWithOneRegionWithMoreThanHalfMinBlocks = createSearchDescription(oneRegionWithMoreThanHalfMinBlocks);
        this.searchWithTwoRegionsWithMinBlocksLessThanTestArea = createSearchDescription(twoRegionsWithMinBlocksLessThanTestArea);
        this.searchWithTwoRegionsWithMinBlocksEqualToTestArea = createSearchDescription(twoRegionsWithMinBlocksEqualToTestArea);
        this.searchWithTwoRegionsWithMinBlocksGreaterThanTestArea = createSearchDescription(twoRegionsWithMinBlocksGreaterThanTestArea);

        this.searchWithOneRegionWithLessThanHalfMinPercent = createSearchDescription(oneRegionWithLessThanHalfMinPercent);
        this.searchWithOneRegionWithHalfMinPercent = createSearchDescription(oneRegionWithHalfMinPercent);
        this.searchWithOneRegionWithHalfMinPercentAndSecondBiome = createSearchDescription(oneRegionWithHalfMinPercentAndSecondBiome);
        this.searchWithOneRegionWithMoreThanHalfMinPercent = createSearchDescription(oneRegionWithMoreThanHalfMinPercent);
        this.searchWithTwoRegionsWithMinPercentLessThanTestArea = createSearchDescription(twoRegionsWithMinPercentLessThanTestArea);
        this.searchWithTwoRegionsWithMinPercentEqualToTestArea = createSearchDescription(twoRegionsWithMinPercentEqualToTestArea);
        this.searchWithTwoRegionsWithMinPercentGreaterThanTestArea = createSearchDescription(twoRegionsWithMinPercentGreaterThanTestArea);
    }

    public SearchDescription createSearchDescription(final RegionDescription[] regions) {
        return new SearchDescription(null, null, null, MIN_X, MIN_Y, MAX_X, MAX_Y, regions);
    }

    public boolean isValid(final SearchDescription search) {
        return new RegionSetValidator(search).isValid();
    }

    @Test
    public void testNullSearch() {
        assertFalse(isValid(this.nullSearch));
    }

    @Test
    public void testEmptySearch() {
        assertFalse(isValid(this.emptySearch));
    }

    @Test
    public void testCheckEachBiomeNotListedMoreThanOnce() {
        assertTrue(isValid(this.searchWithOneRegionWithDifferentBiomes));
        assertFalse(isValid(this.searchWithOneRegionWithDuplicateBiomes));
        assertTrue(isValid(this.searchWithTwoRegionsWithDifferentBiomes));
        assertFalse(isValid(this.searchWithTwoRegionsWithDuplicateBiomes));
    }

    @Test
    public void testCheckTotalMinBlocksLessThanTotalArea() {
        // Test each region individually to make sure all the regions
        // are valid separately.
        assertTrue(isValid(this.searchWithOneRegionWithLessThanHalfMinBlocks));
        assertTrue(isValid(this.searchWithOneRegionWithHalfMinBlocks));
        assertTrue(isValid(this.searchWithOneRegionWithHalfMinBlocksAndSecondBiome));
        assertTrue(isValid(this.searchWithOneRegionWithMoreThanHalfMinBlocks));

        // Now test the combinations
        assertTrue(isValid(this.searchWithTwoRegionsWithMinBlocksLessThanTestArea));
        assertTrue(isValid(this.searchWithTwoRegionsWithMinBlocksEqualToTestArea));
        assertFalse(isValid(this.searchWithTwoRegionsWithMinBlocksGreaterThanTestArea));
    }

    @Test
    public void testCheckTotalMinPercentLessThanOneHundred() {
        // Test each region individually to make sure all the regions
        // are valid separately.
        assertTrue(isValid(this.searchWithOneRegionWithLessThanHalfMinPercent));
        assertTrue(isValid(this.searchWithOneRegionWithHalfMinPercent));
        assertTrue(isValid(this.searchWithOneRegionWithHalfMinPercentAndSecondBiome));
        assertTrue(isValid(this.searchWithOneRegionWithMoreThanHalfMinPercent));

        // Now test the combinations
        assertTrue(isValid(this.searchWithTwoRegionsWithMinPercentLessThanTestArea));
        assertTrue(isValid(this.searchWithTwoRegionsWithMinPercentEqualToTestArea));
        assertFalse(isValid(this.searchWithTwoRegionsWithMinPercentGreaterThanTestArea));
    }

}
