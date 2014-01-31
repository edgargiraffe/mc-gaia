package mc.gaia.search.description;

public class RegionDescription {

    public final String[] biomes;
    public final Integer maxBlocks;
    public final Integer minBlocks;
    public final Float maxPercent;
    public final Float minPercent;

    public RegionDescription() {
        this.biomes = null;
        this.maxBlocks = null;
        this.minBlocks = null;
        this.maxPercent = null;
        this.minPercent = null;
    }

    public RegionDescription(
            final String[] biomes,
            final Integer maxBlocks,
            final Integer minBlocks,
            final Float maxPercent,
            final Float minPercent) {
        this.biomes = biomes;
        this.maxBlocks = maxBlocks;
        this.minBlocks = minBlocks;
        this.maxPercent = maxPercent;
        this.minPercent = minPercent;
    }

}
