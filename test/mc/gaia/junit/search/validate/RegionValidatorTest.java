package mc.gaia.junit.search.validate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import mc.gaia.search.description.RegionDescription;
import mc.gaia.search.validate.RegionValidator;

import org.junit.Test;

public class RegionValidatorTest {

    public static final int TEST_AREA = 512 * 512;

    public static final String VALID_BIOME = "Ocean";
    public static final String INVALID_BIOME = "Invalid Biome";

    public static final int NEGATIVE_NUMBER = -2;
    public static final int ZERO = 0;
    public static final int POSITIVE_NUMBER = 1;
    public static final int LARGER_POSITIVE_NUMBER = 2;
    public static final float VALID_PERCENT = 99.9f;
    public static final float BORDER_VALID_PERCENT = 100;
    public static final float INVALID_PERCENT = 100.1f;

    public final RegionDescription nullRegion;
    public final RegionDescription emptyRegion;
    public final RegionDescription regionWithNoBiomes;

    public final RegionDescription regionWithOneBiome;
    public final RegionDescription regionWithInvalidBiome;

    public final RegionDescription regionWithNegativeMinBlocks;
    public final RegionDescription regionWithNegativeMaxBlocks;
    public final RegionDescription regionWithNegativeMinPercent;
    public final RegionDescription regionWithNegativeMaxPercent;

    public final RegionDescription regionWithZeroMinBlocks;
    public final RegionDescription regionWithZeroMaxBlocks;
    public final RegionDescription regionWithZeroMinPercent;
    public final RegionDescription regionWithZeroMaxPercent;

    public final RegionDescription regionWithPositiveMinBlocks;
    public final RegionDescription regionWithPositiveMaxBlocks;
    public final RegionDescription regionWithPositiveMinPercent;
    public final RegionDescription regionWithPositiveMaxPercent;

    public final RegionDescription regionWithMinBlockAndPercentSet;
    public final RegionDescription regionWithMaxBlockAndPercentSet;

    public final RegionDescription regionWithValidMinPercent;
    public final RegionDescription regionWithValidMaxPercent;
    public final RegionDescription regionWithBorderValidMinPercent;
    public final RegionDescription regionWithBorderValidMaxPercent;
    public final RegionDescription regionWithInvalidMinPercent;
    public final RegionDescription regionWithInvalidMaxPercent;

    public final RegionDescription regionWithMinBlocksLessThanArea;
    public final RegionDescription regionWithMinBlocksEqualToArea;
    public final RegionDescription regionWithMinBlocksGreaterThanArea;

    public final RegionDescription regionWithMinLessThanMaxBlocks;
    public final RegionDescription regionWithMinLessThanMaxPercent;
    public final RegionDescription regionWithMinEqualToMaxBlocks;
    public final RegionDescription regionWithMinEqualToMaxPercent;
    public final RegionDescription regionWithMinGreaterThanMaxBlocks;
    public final RegionDescription regionWithMinGreaterThanMaxPercent;

    public RegionValidatorTest() {
        this.nullRegion = null;
        this.emptyRegion = new RegionDescription();
        this.regionWithNoBiomes = new RegionDescription(new String[0], null, null, null, null);

        final String[] oneBiome = { VALID_BIOME };
        this.regionWithOneBiome = new RegionDescription(oneBiome, null, null, null, null);

        final String[] invalidBiome = { INVALID_BIOME };
        this.regionWithInvalidBiome = new RegionDescription(invalidBiome, null, null, null, null);

        this.regionWithNegativeMinBlocks = new RegionDescription(oneBiome, null, NEGATIVE_NUMBER, null, null);

        this.regionWithNegativeMaxBlocks = new RegionDescription(oneBiome, NEGATIVE_NUMBER, null, null, null);
        this.regionWithNegativeMinPercent = new RegionDescription(oneBiome, null, null, null, (float) NEGATIVE_NUMBER);
        this.regionWithNegativeMaxPercent = new RegionDescription(oneBiome, null, null, (float) NEGATIVE_NUMBER, null);

        this.regionWithZeroMinBlocks = new RegionDescription(oneBiome, null, ZERO, null, null);
        this.regionWithZeroMaxBlocks = new RegionDescription(oneBiome, ZERO, null, null, null);
        this.regionWithZeroMinPercent = new RegionDescription(oneBiome, null, null, null, (float) ZERO);
        this.regionWithZeroMaxPercent = new RegionDescription(oneBiome, null, null, (float) ZERO, null);

        this.regionWithPositiveMinBlocks = new RegionDescription(oneBiome, null, POSITIVE_NUMBER, null, null);
        this.regionWithPositiveMaxBlocks = new RegionDescription(oneBiome, POSITIVE_NUMBER, null, null, null);
        this.regionWithPositiveMinPercent = new RegionDescription(oneBiome, null, null, null, (float) POSITIVE_NUMBER);
        this.regionWithPositiveMaxPercent = new RegionDescription(oneBiome, null, null, (float) POSITIVE_NUMBER, null);

        this.regionWithMinBlockAndPercentSet = new RegionDescription(oneBiome, null, POSITIVE_NUMBER, null, (float) POSITIVE_NUMBER);
        this.regionWithMaxBlockAndPercentSet = new RegionDescription(oneBiome, POSITIVE_NUMBER, null, (float) POSITIVE_NUMBER, null);

        this.regionWithValidMinPercent = new RegionDescription(oneBiome, null, null, null, VALID_PERCENT);
        this.regionWithValidMaxPercent = new RegionDescription(oneBiome, null, null, VALID_PERCENT, null);
        this.regionWithBorderValidMinPercent = new RegionDescription(oneBiome, null, null, null, BORDER_VALID_PERCENT);
        this.regionWithBorderValidMaxPercent = new RegionDescription(oneBiome, null, null, BORDER_VALID_PERCENT, null);
        this.regionWithInvalidMinPercent = new RegionDescription(oneBiome, null, null, null, INVALID_PERCENT);
        this.regionWithInvalidMaxPercent = new RegionDescription(oneBiome, null, null, INVALID_PERCENT, null);

        this.regionWithMinBlocksLessThanArea = new RegionDescription(oneBiome, null, TEST_AREA - 1, null, null);
        this.regionWithMinBlocksEqualToArea = new RegionDescription(oneBiome, null, TEST_AREA, null, null);
        this.regionWithMinBlocksGreaterThanArea = new RegionDescription(oneBiome, null, TEST_AREA + 1, null, null);

        this.regionWithMinLessThanMaxBlocks = new RegionDescription(oneBiome, LARGER_POSITIVE_NUMBER, POSITIVE_NUMBER, null, null);
        this.regionWithMinLessThanMaxPercent = new RegionDescription(oneBiome, null, null, (float) LARGER_POSITIVE_NUMBER, (float) POSITIVE_NUMBER);
        this.regionWithMinEqualToMaxBlocks = new RegionDescription(oneBiome, POSITIVE_NUMBER, POSITIVE_NUMBER, null, null);
        this.regionWithMinEqualToMaxPercent = new RegionDescription(oneBiome, null, null, (float) POSITIVE_NUMBER, (float) POSITIVE_NUMBER);
        this.regionWithMinGreaterThanMaxBlocks = new RegionDescription(oneBiome, POSITIVE_NUMBER, LARGER_POSITIVE_NUMBER, null, null);
        this.regionWithMinGreaterThanMaxPercent = new RegionDescription(oneBiome, null, null, (float) POSITIVE_NUMBER, (float) LARGER_POSITIVE_NUMBER);
    }

    public boolean isValid(final RegionDescription region) {
        return new RegionValidator(region, TEST_AREA).isValid();
    }

    @Test
    public void testNullRegion() {
        assertFalse(isValid(this.nullRegion));
    }

    @Test
    public void testBlocksAndPercentBothSet() {
        // None are set
        assertTrue(isValid(this.regionWithOneBiome));

        // Only one is set
        assertTrue(isValid(this.regionWithPositiveMinBlocks));
        assertTrue(isValid(this.regionWithPositiveMaxBlocks));
        assertTrue(isValid(this.regionWithPositiveMinPercent));
        assertTrue(isValid(this.regionWithPositiveMaxPercent));

        // / Both are set
        assertFalse(isValid(this.regionWithMinBlockAndPercentSet));
        assertFalse(isValid(this.regionWithMaxBlockAndPercentSet));
    }

    @Test
    public void testGreaterThanZero() {
        // Test each min/max block/percent individually as a negative number.
        assertFalse(isValid(this.regionWithNegativeMinBlocks));
        assertFalse(isValid(this.regionWithNegativeMaxBlocks));
        assertFalse(isValid(this.regionWithNegativeMinPercent));
        assertFalse(isValid(this.regionWithNegativeMaxPercent));

        // All min/max block/percent are null.
        assertTrue(isValid(this.regionWithOneBiome));

        // Test each min/max block/percent individually as zero.
        assertTrue(isValid(this.regionWithZeroMinBlocks));
        assertTrue(isValid(this.regionWithZeroMaxBlocks));
        assertTrue(isValid(this.regionWithZeroMinPercent));
        assertTrue(isValid(this.regionWithZeroMaxPercent));

        // Test each min/max block/percent individually as a positive number.
        assertTrue(isValid(this.regionWithPositiveMinBlocks));
        assertTrue(isValid(this.regionWithPositiveMaxBlocks));
        assertTrue(isValid(this.regionWithPositiveMinPercent));
        assertTrue(isValid(this.regionWithPositiveMaxPercent));
    }

    @Test
    public void testHasAtLeastOneBiome() {
        assertFalse(isValid(this.emptyRegion));
        assertFalse(isValid(this.regionWithNoBiomes));
        assertTrue(isValid(this.regionWithOneBiome));
    }

    @Test
    public void testIsValidBiome() {
        assertTrue(isValid(this.regionWithOneBiome));
        assertFalse(isValid(this.regionWithInvalidBiome));
    }

    @Test
    public void testLessThanOneHundred() {
        assertTrue(isValid(this.regionWithValidMinPercent));
        assertTrue(isValid(this.regionWithValidMaxPercent));

        assertTrue(isValid(this.regionWithBorderValidMinPercent));
        assertTrue(isValid(this.regionWithBorderValidMaxPercent));

        assertFalse(isValid(this.regionWithInvalidMinPercent));
        assertFalse(isValid(this.regionWithInvalidMaxPercent));
    }

    @Test
    public void testMinBlocksLessThanMaxArea() {
        assertTrue(isValid(this.regionWithMinBlocksLessThanArea));
        assertTrue(isValid(this.regionWithMinBlocksEqualToArea));
        assertFalse(isValid(this.regionWithMinBlocksGreaterThanArea));
    }

    @Test
    public void testMinLessThanMax() {
        assertTrue(isValid(this.regionWithMinLessThanMaxBlocks));
        assertTrue(isValid(this.regionWithMinLessThanMaxPercent));
        assertTrue(isValid(this.regionWithMinEqualToMaxBlocks));
        assertTrue(isValid(this.regionWithMinEqualToMaxPercent));
        assertFalse(isValid(this.regionWithMinGreaterThanMaxBlocks));
        assertFalse(isValid(this.regionWithMinGreaterThanMaxPercent));
    }

}
