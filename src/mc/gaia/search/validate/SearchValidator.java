package mc.gaia.search.validate;

import java.util.ArrayList;

import mc.gaia.amidst.AmidstInterface;
import mc.gaia.logging.Logger;
import mc.gaia.search.description.RegionDescription;
import mc.gaia.search.description.SearchDescription;

public class SearchValidator {
	
	// Currently we can only search 2000 blocks from the origin
	// Avoids out of memory errors, negative index errors
	private static final int MAX_COORD = 2048;
	private static final int MIN_COORD = -MAX_COORD;
	
	private boolean valid; // this variable is updated in helper methods
	private SearchDescription searchDescription; // keep track of the search description
	
	public SearchValidator(SearchDescription searchDescription) {
		this.valid = true;
		this.searchDescription = searchDescription;
	}
	
	// This method will return all valid error messages before returning
	// This was if you have two problems with the json file, they'll both be displayed
	public boolean isValid() {
		
		isWorldTypeValid();
		hasMinecraftVersion();
		hasValidSpawnRequest();
		
		// TODO: refactor this so it looks a bit cleaner
		minimumCoordCheck(searchDescription.minX, "X");
		minimumCoordCheck(searchDescription.minY, "Y");
		maximumCoordCheck(searchDescription.maxX, "X");
		maximumCoordCheck(searchDescription.maxY, "Y");
		
		maxXGreaterThanMinX();
		maxYGreaterThanMinY();
		
		// TODO: refactor - do we need a region validator???
		if(searchDescription.regions != null) {
			for(RegionDescription region : searchDescription.regions) {
				if((new RegionValidator(region)).isValid() == false) 
					valid = false;
			}	
		}
		
		return valid;
	}
	
	private void isWorldTypeValid() {
		if(AmidstInterface.isValidWorldType(searchDescription.worldType) == false) {
			valid = false;
			Logger.error("World type \"" + searchDescription.worldType + "\" is not valid.");
		}
	}
	
	private void hasMinecraftVersion() {
		if(searchDescription.minecraftVersion == null) {
			valid = false;
			Logger.error("Please input the desired minecraft version.");
		}
	}
	
	private void hasValidSpawnRequest() {
		if(this.searchDescription.spawn == null) 
			return;
		
		ArrayList<String> validBiomes = AmidstInterface.getValidSpawnBiomes();
		if(validBiomes.contains(this.searchDescription.spawn) == false) {
			valid = false;
			Logger.error("Can only search for spawn in " + validBiomes.toString() + " biomes.");
		}
	}
	
	private void greaterThanOther(int greater, int smaller, String name) {
		if(smaller >= greater) {
			valid = false;
			Logger.error("Variable \"max" + name + "\" must be strictly greater than variable \"min" + name + "\".");
		}
	}
	
	private void maxXGreaterThanMinX() {
		greaterThanOther(searchDescription.maxX, searchDescription.minX, "X");
	}
	
	private  void maxYGreaterThanMinY() {
		greaterThanOther(searchDescription.maxY, searchDescription.minY, "Y");
	}
	
	private void minimumCoordCheck(int coord, String name) {
		if(coord < MIN_COORD) {
			valid = false;
			Logger.error("Coordinate \"min" + name + "\" is less than the minimum allowed coordinate \"" + MIN_COORD + "\"");
		}
	}
	
	private void maximumCoordCheck(int coord, String name) {
		if(coord > MAX_COORD) {
			valid = false;
			Logger.error("Coordinate \"max" + name + "\" is greater than the maximum allowed coordinate \"" + MAX_COORD + "\"");
		}
	}
	
}
