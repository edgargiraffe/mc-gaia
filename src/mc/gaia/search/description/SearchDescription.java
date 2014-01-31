package mc.gaia.search.description;

public class SearchDescription {

    // This way I can leave these as public without having to create accessors.
    // Gson can still fill these elements with the correct values.
    public final String worldType;
    public final String minecraftVersion;
    public final String spawn;

    public final Integer minX;
    public final Integer minY;
    public final Integer maxX;
    public final Integer maxY;

    public final RegionDescription[] regions;

    // public final int numVillages;
    // public final int maxDistanceVillages;

    // Default UNSET values for all variables.
    public SearchDescription() {
        this.worldType = null;
        this.minecraftVersion = null;
        this.spawn = null;
        this.minX = null;
        this.minY = null;
        this.maxX = null;
        this.maxY = null;
        this.regions = null;
    }

    public SearchDescription(
            final String worldType,
            final String minecraftVersion,
            final String spawn,
            final Integer minX,
            final Integer minY,
            final Integer maxX,
            final Integer maxY,
            final RegionDescription[] regions) {
        this.worldType = worldType;
        this.minecraftVersion = minecraftVersion;
        this.spawn = spawn;
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        this.regions = regions;
    }

    public Integer getSearchArea() {
        try {
            return (this.maxX - this.minX) * (this.maxY - this.minY);
        } catch(final NullPointerException e) {
            return null;
        }
    }

}
