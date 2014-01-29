package mc.gaia.search.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import mc.gaia.logging.Logger;

public class BiomeResults implements Comparable<BiomeResults> {

	public String name;
	public int count;
	private static float total;
	
	public BiomeResults(String name, int count) {
		this.name = name;
		this.count = count;
	}
	
	@Override
	public int compareTo(BiomeResults other) {
		return other.count - this.count;
	}
	
	public static void printSortedBiomeResults(HashMap<String, Integer> biomeBlockCountMap) {
		List<BiomeResults> results = createSortedList(biomeBlockCountMap);
		
		for(BiomeResults result : results) {
			String fraction = String.format("%.1f", 100*result.count/total) + "%";
			Logger.result(String.format("%-32s%8s%10s", result.name, result.count + "m3", fraction));
		}
		
	}
	
	private static List<BiomeResults> createSortedList(HashMap<String, Integer> biomeBlockCountMap) {
		List<BiomeResults> results = new ArrayList<BiomeResults>();
		total = 0;
		
		for(String biomeName : biomeBlockCountMap.keySet()) {
			int count = biomeBlockCountMap.get(biomeName);
			results.add(new BiomeResults(biomeName, count));
			total += count;
		}
		
		Collections.sort(results);
		return results;
	}
	
}
