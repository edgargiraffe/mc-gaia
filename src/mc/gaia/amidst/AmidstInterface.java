package mc.gaia.amidst;

import java.awt.Point;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Random;

import mc.gaia.exception.MinecraftVersionException;
import mc.gaia.exception.UnableToFindSpawnException;
import mc.gaia.exception.WorldTypeException;
import mc.gaia.logging.Logger;

import amidst.Options;
import amidst.Util;
import amidst.map.layers.SpawnLayer;
import amidst.minecraft.Biome;
import amidst.minecraft.Minecraft;
import amidst.minecraft.MinecraftUtil;

import MoF.SaveLoader.Type;

public class AmidstInterface {

	public static void initialize(String version) throws MalformedURLException, MinecraftVersionException {
		// Disable console output from amidst
		Logger.disableConsole();
		
		// Needed for some reason - I don't really know why.
		Util.setMinecraftDirectory();
		
		// TODO: Make this windows safe
		String jarFilename = Util.minecraftDirectory.getAbsolutePath();
		jarFilename += "/versions/";
		jarFilename += version;
		jarFilename += "/" + version + ".jar";
		
		File minecraftJar = new File(jarFilename);
		if(minecraftJar.exists() == false || minecraftJar.isDirectory() == true) {
			String message = "Cannot find Minecraft version \"" + version + "\" in directory \"" + 
					Util.minecraftDirectory.getAbsolutePath() + "\".";
			throw new MinecraftVersionException(message);
		}
		
		try {
			Minecraft minecraft = new Minecraft(minecraftJar);
			minecraft.use();
		}
		catch (Exception e) {
			String message = "Minecraft version \"" + version + "\" in directory \"" + 
					Util.minecraftDirectory.getAbsolutePath() + "\" is not valid.";
			throw new MinecraftVersionException(message);
		}
		
		// Re-enable console output
		Logger.enableConsole();
	}

	private static Type getWorldType(String worldType) throws WorldTypeException {
		// Check the AMIDST list of valid world types.
		for(Type type : Type.values()) {
			if(type.name().toLowerCase().equals(worldType.toLowerCase())) {
				return type;
			}
		}
		
		// This case should never happen if we're checking the world type when
		// creating the search description.
		throw new WorldTypeException(worldType);
	}
	
	public static void createWorld(long seed, String worldType) throws WorldTypeException {
		// Disable console output from amidst
		Logger.disableConsole();
		
		MinecraftUtil.createBiomeGenerator(seed, getWorldType(worldType));
		
		// Re-enable console output
		Logger.enableConsole();
	}

	// Return the biome indices for this area.
	// TODO: Am I actually returning the correct areas?
	public static int[] getBiomeData(int minX, int minY, int maxX, int maxY) {
		// Disable console output from amidst
		Logger.disableConsole();
		
		int x = minX >> 2;
		int y = minY >> 2;
		int width = (maxX - minX)/4;
		int height = (maxY - minY)/4;
		
		int[] amidstBiomeData = MinecraftUtil.getBiomeData(x, y, width, height);
		
		int[] biomeData = new int[width * height];
		for(int i = 0; i < width * height; i++) {
			biomeData[i] = amidstBiomeData[i];
		}
		
		// Re-enable console output
		Logger.enableConsole();
		
		return biomeData;
	}
	
	// Return the name of the biome with the given index.
	public static String getBiomeName(int biomeIndex) {
		return Biome.biomes[biomeIndex].name;
	}
	
	// Returns true if this is a valid world type.
	// Current options: "default", "large biomes", "amplified".
	public static boolean isValidWorldType(String worldType) {
		String thisWorldType = worldType.toLowerCase();
		
		for(Type type : Type.values()) {
			if(type.name().toLowerCase().equals(thisWorldType)) {
				return true;
			}
		}
		
		return false;

	}
	
	// Returns true if this is a valid biome.
	public static boolean isValidBiome(String biome) {
		return Biome.biomeMap.containsKey(biome);
	}
	
	// Based off from AMIDST amidst.map.layers.SpawnLayer.java
	// Note: Needs to be updated when we update AMIDST versions.
	public static String getSpawnBiome() throws UnableToFindSpawnException {
		Random random = new Random(Options.instance.seed);
		
		Logger.disableConsole(); // Disable console output from amidst
		Point location = MinecraftUtil.findValidLocation(0, 0, 256, SpawnLayer.validBiomes, random);
		
		int x = 0;
		int y = 0;
		if (location != null) {
			x = location.x;
			y = location.y;
		} else {
			Logger.enableConsole(); // Re-enable console output
			throw new UnableToFindSpawnException();
		}
		
		int[] biomeData = getBiomeData(x, y, x+4, y+4);
		String biome = getBiomeName(biomeData[0]);
		
		Logger.enableConsole(); // Re-enable console output
		return biome;
	}
	
	// Returns a list of the AMIDST valid spawn biomes.
	public static ArrayList<String> getValidSpawnBiomes() {
		ArrayList<String> validBiomes = new ArrayList<String>();
		
		for(Biome biome : SpawnLayer.validBiomes) {
			validBiomes.add(biome.name);
		}
		
		return validBiomes;
	}

}
