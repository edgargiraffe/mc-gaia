package mc.gaia.junit.search.validate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import mc.gaia.search.description.RegionDescription;
import mc.gaia.search.description.SearchDescription;
import mc.gaia.search.validate.SearchValidator;

import org.junit.Test;

public class SearchValidatorTest {

    public static final int SMALLER = 0;
    public static final int LARGER = 1;
    public static final int MIN = SMALLER;
    public static final int MAX = LARGER;
    public static final int MIN_COORD = SearchValidator.MIN_COORD;
    public static final int MAX_COORD = SearchValidator.MAX_COORD;

    public static final String WORLD_TXPE = "default";
    public static final String LOWERCASE_WORLD_TXPE = "default";
    public static final String MIXEDCASE_WORLD_TXPE = "DeFaUlT";
    public static final String UPPERCASE_WORLD_TXPE = "DEFAULT";
    public static final String INVALID_WORLD_TXPE = "Invalid World Type";

    public static final String VERSION = "1.7.2";

    public static final String VALID_SPAWN_BIOME = "Plains";
    public static final String INVALID_SPAWN_BIOME = "Ocean";
    public static final String INVALID_BIOME = "Invalid Biome";

    public final RegionDescription validRegion;

    public final SearchDescription nullSearch;
    public final SearchDescription emptySearch;
    public final SearchDescription minimumSearch;

    public final SearchDescription searchWithNullWorldType;
    public final SearchDescription searchWithLowerCaseWorldType;
    public final SearchDescription searchWithMixedCaseWorldType;
    public final SearchDescription searchWithUpperCaseWorldType;
    public final SearchDescription searchWithInvalidWorldType;

    public final SearchDescription searchWithNullMinecraftVersion;
    public final SearchDescription searchWithValidMinecraftVersion;

    public final SearchDescription searchWithNullSpawnRequest;
    public final SearchDescription searchWithValidSpawnBiomeForSpawnRequest;
    public final SearchDescription searchWithInvalidSpawnBiomeForSpawnRequest;
    public final SearchDescription searchWithInvalidBiomeForSpawnRequest;

    public final SearchDescription searchWithMinXLessThanMaxX;
    public final SearchDescription searchWithMinXEqualToMaxX;
    public final SearchDescription searchWithMinXGreaterThanMaxX;

    public final SearchDescription searchWithMinYLessThanMaxY;
    public final SearchDescription searchWithMinYEqualToMaxY;
    public final SearchDescription searchWithMinYGreaterThanMaxY;

    public final SearchDescription searchWithMinXLessThanMinCoord;
    public final SearchDescription searchWithMinXEqualToMinCoord;
    public final SearchDescription searchWithMinXGreaterThanMinCoord;
    public final SearchDescription searchWithMinYLessThanMinCoord;
    public final SearchDescription searchWithMinYEqualToMinCoord;
    public final SearchDescription searchWithMinYGreaterThanMinCoord;

    public final SearchDescription searchWithMaxXLessThanMaxCoord;
    public final SearchDescription searchWithMaxXEqualToMaxCoord;
    public final SearchDescription searchWithMaxXGreaterThanMaxCoord;
    public final SearchDescription searchWithMaxYLessThanMaxCoord;
    public final SearchDescription searchWithMaxYEqualToMaxCoord;
    public final SearchDescription searchWithMaxYGreaterThanMaxCoord;

    public SearchValidatorTest() {
        final String[] validRegionList = { SearchValidatorTest.VALID_SPAWN_BIOME };
        this.validRegion = new RegionDescription(validRegionList, null, null, null, null);
        final RegionDescription[] validRegions = { this.validRegion };

        this.nullSearch = null;
        this.emptySearch = new SearchDescription();
        this.minimumSearch = new SearchDescription(WORLD_TXPE, VERSION, null, MIN, MIN, MAX, MAX, validRegions);

        this.searchWithNullWorldType = new SearchDescription(null, VERSION, null, MIN, MIN, MAX, MAX, validRegions);
        this.searchWithLowerCaseWorldType = new SearchDescription(LOWERCASE_WORLD_TXPE, VERSION, null, MIN, MIN, MAX, MAX, validRegions);
        this.searchWithMixedCaseWorldType = new SearchDescription(MIXEDCASE_WORLD_TXPE, VERSION, null, MIN, MIN, MAX, MAX, validRegions);
        this.searchWithUpperCaseWorldType = new SearchDescription(UPPERCASE_WORLD_TXPE, VERSION, null, MIN, MIN, MAX, MAX, validRegions);
        this.searchWithInvalidWorldType = new SearchDescription(INVALID_WORLD_TXPE, VERSION, null, MIN, MIN, MAX, MAX, validRegions);

        this.searchWithNullMinecraftVersion = new SearchDescription(WORLD_TXPE, null, null, MIN, MIN, MAX, MAX, validRegions);
        this.searchWithValidMinecraftVersion = new SearchDescription(WORLD_TXPE, VERSION, null, MIN, MIN, MAX, MAX, validRegions);

        this.searchWithNullSpawnRequest = new SearchDescription(WORLD_TXPE, VERSION, null, MIN, MIN, MAX, MAX, validRegions);
        this.searchWithValidSpawnBiomeForSpawnRequest = new SearchDescription(WORLD_TXPE, VERSION, VALID_SPAWN_BIOME, MIN, MIN, MAX, MAX, validRegions);
        this.searchWithInvalidSpawnBiomeForSpawnRequest = new SearchDescription(WORLD_TXPE, VERSION, INVALID_SPAWN_BIOME, MIN, MIN, MAX, MAX, validRegions);
        this.searchWithInvalidBiomeForSpawnRequest = new SearchDescription(WORLD_TXPE, VERSION, INVALID_BIOME, MIN, MIN, MAX, MAX, validRegions);

        this.searchWithMinXLessThanMaxX = new SearchDescription(WORLD_TXPE, VERSION, null, SMALLER, MIN, LARGER, MAX, validRegions);
        this.searchWithMinXEqualToMaxX = new SearchDescription(WORLD_TXPE, VERSION, null, SMALLER, MIN, SMALLER, MAX, validRegions);
        this.searchWithMinXGreaterThanMaxX = new SearchDescription(WORLD_TXPE, VERSION, null, LARGER, MIN, SMALLER, MAX, validRegions);

        this.searchWithMinYLessThanMaxY = new SearchDescription(WORLD_TXPE, VERSION, null, MIN, SMALLER, MAX, LARGER, validRegions);
        this.searchWithMinYEqualToMaxY = new SearchDescription(WORLD_TXPE, VERSION, null, MIN, SMALLER, MAX, SMALLER, validRegions);
        this.searchWithMinYGreaterThanMaxY = new SearchDescription(WORLD_TXPE, VERSION, null, MIN, LARGER, MIN, SMALLER, validRegions);

        this.searchWithMinXLessThanMinCoord = new SearchDescription(WORLD_TXPE, VERSION, null, MIN_COORD - 1, MIN, MAX, MAX, validRegions);
        this.searchWithMinXEqualToMinCoord = new SearchDescription(WORLD_TXPE, VERSION, null, MIN_COORD, MIN, MAX, MAX, validRegions);
        this.searchWithMinXGreaterThanMinCoord = new SearchDescription(WORLD_TXPE, VERSION, null, MIN_COORD + 1, MIN, MAX, MAX, validRegions);
        this.searchWithMinYLessThanMinCoord = new SearchDescription(WORLD_TXPE, VERSION, null, MIN, MIN_COORD - 1, MAX, MAX, validRegions);
        this.searchWithMinYEqualToMinCoord = new SearchDescription(WORLD_TXPE, VERSION, null, MIN, MIN_COORD, MAX, MAX, validRegions);
        this.searchWithMinYGreaterThanMinCoord = new SearchDescription(WORLD_TXPE, VERSION, null, MIN, MIN_COORD + 1, MAX, MAX, validRegions);

        this.searchWithMaxXLessThanMaxCoord = new SearchDescription(WORLD_TXPE, VERSION, null, MIN, MIN, MAX_COORD - 1, MAX, validRegions);
        this.searchWithMaxXEqualToMaxCoord = new SearchDescription(WORLD_TXPE, VERSION, null, MIN, MIN, MAX_COORD, MAX, validRegions);
        this.searchWithMaxXGreaterThanMaxCoord = new SearchDescription(WORLD_TXPE, VERSION, null, MIN, MIN, MAX_COORD + 1, MAX, validRegions);
        this.searchWithMaxYLessThanMaxCoord = new SearchDescription(WORLD_TXPE, VERSION, null, MIN, MIN, MAX, MAX_COORD - 1, validRegions);
        this.searchWithMaxYEqualToMaxCoord = new SearchDescription(WORLD_TXPE, VERSION, null, MIN, MIN, MAX, MAX_COORD, validRegions);
        this.searchWithMaxYGreaterThanMaxCoord = new SearchDescription(WORLD_TXPE, VERSION, null, MIN, MIN, MAX, MAX_COORD + 1, validRegions);

    }

    private boolean isValid(final SearchDescription search) {
        return new SearchValidator(search).isValid();
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
    public void testMinimum() {
        assertTrue(isValid(this.minimumSearch));
    }

    @Test
    public void testIsWorldTypeValid() {
        // Null world type case
        assertFalse(isValid(this.searchWithNullWorldType));

        // All cases are valid so long as the world type is valid
        assertTrue(isValid(this.searchWithLowerCaseWorldType));
        assertTrue(isValid(this.searchWithMixedCaseWorldType));
        assertTrue(isValid(this.searchWithUpperCaseWorldType));

        // Invalid world type
        assertFalse(isValid(this.searchWithInvalidWorldType));
    }

    @Test
    public void testHasMinecraftVersion() {
        assertFalse(isValid(this.searchWithNullMinecraftVersion));
        assertTrue(isValid(this.searchWithValidMinecraftVersion));
    }

    @Test
    public void testHasValidSpawnRequest() {
        assertTrue(isValid(this.searchWithNullSpawnRequest)); // No spawn request
        assertTrue(isValid(this.searchWithValidSpawnBiomeForSpawnRequest)); // Valid request
        assertFalse(isValid(this.searchWithInvalidSpawnBiomeForSpawnRequest)); // valid biome, but not for spawn
        assertFalse(isValid(this.searchWithInvalidBiomeForSpawnRequest)); // invalid biome
    }

    @Test
    public void testMaxXGreaterThanMinX() {
        assertTrue(isValid(this.searchWithMinXLessThanMaxX));
        assertFalse(isValid(this.searchWithMinXEqualToMaxX));
        assertFalse(isValid(this.searchWithMinXGreaterThanMaxX));
    }

    @Test
    public void testMaxYGreaterThanMinY() {
        assertTrue(isValid(this.searchWithMinYLessThanMaxY));
        assertFalse(isValid(this.searchWithMinYEqualToMaxY));
        assertFalse(isValid(this.searchWithMinYGreaterThanMaxY));
    }

    @Test
    public void testMinimumCoordCheck() {
        assertFalse(isValid(this.searchWithMinXLessThanMinCoord));
        assertTrue(isValid(this.searchWithMinXEqualToMinCoord));
        assertTrue(isValid(this.searchWithMinXGreaterThanMinCoord));

        assertFalse(isValid(this.searchWithMinYLessThanMinCoord));
        assertTrue(isValid(this.searchWithMinYEqualToMinCoord));
        assertTrue(isValid(this.searchWithMinYGreaterThanMinCoord));
    }

    @Test
    public void testMaximumCoordCheck() {
        assertTrue(isValid(this.searchWithMaxXLessThanMaxCoord));
        assertTrue(isValid(this.searchWithMaxXEqualToMaxCoord));
        assertFalse(isValid(this.searchWithMaxXGreaterThanMaxCoord));

        assertTrue(isValid(this.searchWithMaxYLessThanMaxCoord));
        assertTrue(isValid(this.searchWithMaxYEqualToMaxCoord));
        assertFalse(isValid(this.searchWithMaxYGreaterThanMaxCoord));
    }

}
