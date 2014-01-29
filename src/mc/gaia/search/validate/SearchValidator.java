package mc.gaia.search.validate;

import java.util.ArrayList;

import mc.gaia.amidst.AmidstInterface;
import mc.gaia.logging.Logger;
import mc.gaia.search.description.SearchDescription;

public class SearchValidator {

    // Currently we can only search 2000 blocks from the origin
    // Avoids out of memory errors, negative index errors
    public static final int MAX_COORD = 2048;
    public static final int MIN_COORD = -MAX_COORD;

    private boolean valid; // this variable is updated in helper methods
    private final SearchDescription searchDescription; // keep track of the
                                                       // search description

    public SearchValidator(final SearchDescription searchDescription) {
        this.valid = true;
        this.searchDescription = searchDescription;
    }

    // This method will return all valid error messages before returning
    // This was if you have two problems with the json file, they'll both be
    // displayed
    public boolean isValid() {
        if(this.searchDescription == null) {
            Logger.error("Need to specify a search description.");
            return false;
        }

        isWorldTypeValid();
        hasMinecraftVersion();
        hasValidSpawnRequest();

        // TODO: refactor this so it looks a bit cleaner
        minimumCoordCheck(this.searchDescription.minX, "X");
        minimumCoordCheck(this.searchDescription.minY, "Y");
        maximumCoordCheck(this.searchDescription.maxX, "X");
        maximumCoordCheck(this.searchDescription.maxY, "Y");

        maxXGreaterThanMinX();
        maxYGreaterThanMinY();

        if(new RegionSetValidator(this.searchDescription).isValid() == false) {
            this.valid = false;
        }

        return this.valid;
    }

    private void isWorldTypeValid() {
        if(this.searchDescription.worldType == null || AmidstInterface.isValidWorldType(this.searchDescription.worldType) == false) {
            this.valid = false;
            Logger.error("World type \"" + this.searchDescription.worldType + "\" is not valid.");
        }
    }

    private void hasMinecraftVersion() {
        if(this.searchDescription.minecraftVersion == null) {
            this.valid = false;
            Logger.error("Please input the desired minecraft version.");
        }
    }

    private void hasValidSpawnRequest() {
        if(this.searchDescription.spawn == null) {
            return;
        }

        final ArrayList<String> validBiomes = AmidstInterface.getValidSpawnBiomes();
        if(validBiomes.contains(this.searchDescription.spawn) == false) {
            this.valid = false;
            Logger.error("Can only search for spawn in " + validBiomes.toString() + " biomes.");
        }
    }

    private void greaterThanOther(final int greater, final int smaller, final String name) {
        if(smaller >= greater) {
            this.valid = false;
            Logger.error("Variable \"max" + name + "\" must be strictly greater than variable \"min" + name + "\".");
        }
    }

    private void maxXGreaterThanMinX() {
        greaterThanOther(this.searchDescription.maxX, this.searchDescription.minX, "X");
    }

    private void maxYGreaterThanMinY() {
        greaterThanOther(this.searchDescription.maxY, this.searchDescription.minY, "Y");
    }

    private void minimumCoordCheck(final int coord, final String name) {
        if(coord < MIN_COORD) {
            this.valid = false;
            Logger.error("Coordinate \"min" + name + "\" is less than the minimum allowed coordinate \"" + MIN_COORD + "\"");
        }
    }

    private void maximumCoordCheck(final int coord, final String name) {
        if(coord > MAX_COORD) {
            this.valid = false;
            Logger.error("Coordinate \"max" + name + "\" is greater than the maximum allowed coordinate \"" + MAX_COORD + "\"");
        }
    }

}
