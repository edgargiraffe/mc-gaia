package mc.gaia.junit.search.validate;

import static org.junit.Assert.assertEquals;
import mc.gaia.search.description.RegionDescription;
import mc.gaia.search.validate.RegionValidator;

import org.junit.BeforeClass;
import org.junit.Test;

public class RegionValidatorTest {
	
	public static int UNSET = RegionDescription.UNSET;
	public static int TEST_AREA = 512 * 512;
	
	public static String VALID_BIOME = "Ocean";
	public static String INVALID_BIOME = "Invalid Biome";
	
	
	public static int NEGATIVE_NUMBER = -2;
	public static int ZERO = 0;
	public static int POSITIVE_NUMBER = 1;
	public static int LARGER_POSITIVE_NUMBER = 2;
	public static float VALID_PERCENT = 99.9f;
	public static float BORDER_VALID_PERCENT = 100;
	public static float INVALID_PERCENT = 100.1f;
	
	public static RegionDescription nullRegion;
	public static RegionDescription emptyRegion;
	public static RegionDescription regionWithNoBiomes;
	
	public static RegionDescription regionWithOneBiome;
	public static RegionDescription regionWithInvalidBiome;
	
	public static RegionDescription regionWithNegativeMinBlocks;
	public static RegionDescription regionWithNegativeMaxBlocks;
	public static RegionDescription regionWithNegativeMinPercent;
	public static RegionDescription regionWithNegativeMaxPercent;
	
	public static RegionDescription regionWithZeroMinBlocks;
	public static RegionDescription regionWithZeroMaxBlocks;
	public static RegionDescription regionWithZeroMinPercent;
	public static RegionDescription regionWithZeroMaxPercent;
	
	public static RegionDescription regionWithPositiveMinBlocks;
	public static RegionDescription regionWithPositiveMaxBlocks;
	public static RegionDescription regionWithPositiveMinPercent;
	public static RegionDescription regionWithPositiveMaxPercent;
	
	public static RegionDescription regionWithMinBlockAndPercentSet;
	public static RegionDescription regionWithMaxBlockAndPercentSet;
	
	public static RegionDescription regionWithValidMinPercent;
	public static RegionDescription regionWithValidMaxPercent;
	public static RegionDescription regionWithBorderValidMinPercent;
	public static RegionDescription regionWithBorderValidMaxPercent;
	public static RegionDescription regionWithInvalidMinPercent;
	public static RegionDescription regionWithInvalidMaxPercent;
	
	public static RegionDescription regionWithMinBlocksLessThanArea;
	public static RegionDescription regionWithMinBlocksEqualToArea;
	public static RegionDescription regionWithMinBlocksGreaterThanArea;
	
	public static RegionDescription regionWithMinLessThanMaxBlocks;
	public static RegionDescription regionWithMinLessThanMaxPercent;
	public static RegionDescription regionWithMinEqualToMaxBlocks;
	public static RegionDescription regionWithMinEqualToMaxPercent;
	public static RegionDescription regionWithMinGreaterThanMaxBlocks;
	public static RegionDescription regionWithMinGreaterThanMaxPercent;
	
	@BeforeClass
	public static void testSetup() {
		nullRegion = null;
		emptyRegion = new RegionDescription();
		regionWithNoBiomes = new RegionDescription(new String[0], UNSET, UNSET, UNSET, UNSET);
		
		String[] oneBiome = {VALID_BIOME};
		regionWithOneBiome = new RegionDescription(oneBiome, UNSET, UNSET, UNSET, UNSET);
		
		String[] invalidBiome = {INVALID_BIOME};
		regionWithInvalidBiome = new RegionDescription(invalidBiome, UNSET, UNSET, UNSET, UNSET);
		
		regionWithNegativeMinBlocks = new RegionDescription(oneBiome, UNSET, NEGATIVE_NUMBER, UNSET, UNSET);
		regionWithNegativeMaxBlocks = new RegionDescription(oneBiome, NEGATIVE_NUMBER, UNSET, UNSET, UNSET);
		regionWithNegativeMinPercent = new RegionDescription(oneBiome, UNSET, UNSET, UNSET, NEGATIVE_NUMBER);
		regionWithNegativeMaxPercent = new RegionDescription(oneBiome, UNSET, UNSET, NEGATIVE_NUMBER, UNSET);
		
		regionWithZeroMinBlocks = new RegionDescription(oneBiome, UNSET, ZERO, UNSET, UNSET);
		regionWithZeroMaxBlocks = new RegionDescription(oneBiome, ZERO, UNSET, UNSET, UNSET);
		regionWithZeroMinPercent = new RegionDescription(oneBiome, UNSET, UNSET, UNSET, ZERO);
		regionWithZeroMaxPercent = new RegionDescription(oneBiome, UNSET, UNSET, ZERO, UNSET);
		
		regionWithPositiveMinBlocks = new RegionDescription(oneBiome, UNSET, POSITIVE_NUMBER, UNSET, UNSET);
		regionWithPositiveMaxBlocks = new RegionDescription(oneBiome, POSITIVE_NUMBER, UNSET, UNSET, UNSET);
		regionWithPositiveMinPercent = new RegionDescription(oneBiome, UNSET, UNSET, UNSET, POSITIVE_NUMBER);
		regionWithPositiveMaxPercent = new RegionDescription(oneBiome, UNSET, UNSET, POSITIVE_NUMBER, UNSET);
		
		regionWithMinBlockAndPercentSet = new RegionDescription(oneBiome, UNSET, POSITIVE_NUMBER, UNSET, POSITIVE_NUMBER);
		regionWithMaxBlockAndPercentSet = new RegionDescription(oneBiome, POSITIVE_NUMBER, UNSET, POSITIVE_NUMBER, UNSET);
		
		regionWithValidMinPercent = new RegionDescription(oneBiome, UNSET, UNSET, UNSET, VALID_PERCENT);
		regionWithValidMaxPercent = new RegionDescription(oneBiome, UNSET, UNSET, VALID_PERCENT, UNSET);
		regionWithBorderValidMinPercent = new RegionDescription(oneBiome, UNSET, UNSET, UNSET, BORDER_VALID_PERCENT);
		regionWithBorderValidMaxPercent = new RegionDescription(oneBiome, UNSET, UNSET, BORDER_VALID_PERCENT, UNSET);
		regionWithInvalidMinPercent = new RegionDescription(oneBiome, UNSET, UNSET, UNSET, INVALID_PERCENT);
		regionWithInvalidMaxPercent = new RegionDescription(oneBiome, UNSET, UNSET, INVALID_PERCENT, UNSET);
		
		regionWithMinBlocksLessThanArea = new RegionDescription(oneBiome, UNSET, TEST_AREA - 1, UNSET, UNSET);
		regionWithMinBlocksEqualToArea = new RegionDescription(oneBiome, UNSET, TEST_AREA, UNSET, UNSET);
		regionWithMinBlocksGreaterThanArea = new RegionDescription(oneBiome, UNSET, TEST_AREA + 1, UNSET, UNSET);
		
		regionWithMinLessThanMaxBlocks = new RegionDescription(oneBiome, LARGER_POSITIVE_NUMBER, POSITIVE_NUMBER, UNSET, UNSET);
		regionWithMinLessThanMaxPercent = new RegionDescription(oneBiome, UNSET, UNSET, LARGER_POSITIVE_NUMBER, POSITIVE_NUMBER);
		regionWithMinEqualToMaxBlocks = new RegionDescription(oneBiome, POSITIVE_NUMBER, POSITIVE_NUMBER, UNSET, UNSET);
		regionWithMinEqualToMaxPercent = new RegionDescription(oneBiome, UNSET, UNSET, POSITIVE_NUMBER, POSITIVE_NUMBER);
		regionWithMinGreaterThanMaxBlocks = new RegionDescription(oneBiome, POSITIVE_NUMBER, LARGER_POSITIVE_NUMBER, UNSET, UNSET);
		regionWithMinGreaterThanMaxPercent = new RegionDescription(oneBiome, UNSET, UNSET, POSITIVE_NUMBER, LARGER_POSITIVE_NUMBER);
	}
	
	private boolean isValid(RegionDescription region) {
		return (new RegionValidator(region, TEST_AREA)).isValid();
	}
	
	@Test
	public void testNullRegion() {
		assertEquals(isValid(nullRegion), false);
	}
	
	@Test
	public void testBlocksAndPercentBothSet() {
		// None are set
		assertEquals(isValid(regionWithOneBiome), true);
		
		// Only one is set
		assertEquals(isValid(regionWithPositiveMinBlocks), true);
		assertEquals(isValid(regionWithPositiveMaxBlocks), true);
		assertEquals(isValid(regionWithPositiveMinPercent), true);
		assertEquals(isValid(regionWithPositiveMaxPercent), true);
		
		/// Both are set
		assertEquals(isValid(regionWithMinBlockAndPercentSet), false);
		assertEquals(isValid(regionWithMaxBlockAndPercentSet), false);
	}
	
	@Test
	public void testHasAtLeastOneBiome() {
		assertEquals(isValid(emptyRegion), false);
		assertEquals(isValid(regionWithNoBiomes), false);
		assertEquals(isValid(regionWithOneBiome), true);
	}
	
	@Test
	public void testIsValidBiome() {
		assertEquals(isValid(regionWithOneBiome), true);
		assertEquals(isValid(regionWithInvalidBiome), false);
	}
	
	@Test
	public void testGreaterThanZero() {
		// Test each min/max block/percent individually as a negative number.
		assertEquals(isValid(regionWithNegativeMinBlocks), false);
		assertEquals(isValid(regionWithNegativeMaxBlocks), false);
		assertEquals(isValid(regionWithNegativeMinPercent), false);
		assertEquals(isValid(regionWithNegativeMaxPercent), false);
		
		// All min/max block/percent are UNSET.
		assertEquals(isValid(regionWithOneBiome), true);
		
		// Test each min/max block/percent individually as zero.
		assertEquals(isValid(regionWithZeroMinBlocks), true);
		assertEquals(isValid(regionWithZeroMaxBlocks), true);
		assertEquals(isValid(regionWithZeroMinPercent), true);
		assertEquals(isValid(regionWithZeroMaxPercent), true);
		
		// Test each min/max block/percent individually as a positive number.
		assertEquals(isValid(regionWithPositiveMinBlocks), true);
		assertEquals(isValid(regionWithPositiveMaxBlocks), true);
		assertEquals(isValid(regionWithPositiveMinPercent), true);
		assertEquals(isValid(regionWithPositiveMaxPercent), true);
	}
	
	@Test
	public void testLessThanOneHundred() {
		assertEquals(isValid(regionWithValidMinPercent), true);
		assertEquals(isValid(regionWithValidMaxPercent), true);
		
		assertEquals(isValid(regionWithBorderValidMinPercent), true);
		assertEquals(isValid(regionWithBorderValidMaxPercent), true);
		
		assertEquals(isValid(regionWithInvalidMinPercent), false);
		assertEquals(isValid(regionWithInvalidMaxPercent), false);
	}
	
	@Test
	public void testMinBlocksLessThanMaxArea() {
		assertEquals(isValid(regionWithMinBlocksLessThanArea), true);
		assertEquals(isValid(regionWithMinBlocksEqualToArea), true);
		assertEquals(isValid(regionWithMinBlocksGreaterThanArea), false);
	}
	
	@Test
	public void testMinLessThanMax() {
		assertEquals(isValid(regionWithMinLessThanMaxBlocks), true);
		assertEquals(isValid(regionWithMinLessThanMaxPercent), true);
		assertEquals(isValid(regionWithMinEqualToMaxBlocks), true);
		assertEquals(isValid(regionWithMinEqualToMaxPercent), true);
		assertEquals(isValid(regionWithMinGreaterThanMaxBlocks), false);
		assertEquals(isValid(regionWithMinGreaterThanMaxPercent), false);
	}
	
	public static void main(String[] args) {
		RegionValidatorTest test = new RegionValidatorTest();
		RegionValidatorTest.testSetup();
		
		test.testNullRegion();
		test.testBlocksAndPercentBothSet();
		test.testHasAtLeastOneBiome();
		test.testIsValidBiome();
		test.testGreaterThanZero();
		test.testLessThanOneHundred();
		test.testMinBlocksLessThanMaxArea();
		test.testMinLessThanMax();
	}

}
