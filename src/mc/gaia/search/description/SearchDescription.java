package mc.gaia.search.description;

public class SearchDescription {

    // Unset integer variables will be set to 0.
    public static final int UNSET = 0;

    // This way I can leave these as public without having to create accessors.
    // Gson can still fill these elements with the correct values.
    public final String worldType;
    public final String minecraftVersion;
    public final String spawn;

    public final int minX;
    public final int minY;
    public final int maxX;
    public final int maxY;

    public final RegionDescription[] regions;

    // Default UNSET values for all variables.
    public SearchDescription() {
        this.worldType = null;
        this.minecraftVersion = null;
        this.spawn = null;
        this.minX = UNSET;
        this.minY = UNSET;
        this.maxX = UNSET;
        this.maxY = UNSET;
        this.regions = null;
    }

    public SearchDescription(
            final String worldType,
            final String minecraftVersion,
            final String spawn,
            final int minX,
            final int minY,
            final int maxX,
            final int maxY,
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

    public int getSearchArea() {
        return (this.maxX - this.minX) * (this.maxY - this.minY);
    }

}
