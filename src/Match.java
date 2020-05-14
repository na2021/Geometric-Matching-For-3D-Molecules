import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class Match {

	public static void main(String[] args) {
		String filename1, filename2, sectorString, granularityString, thresholdString;
		filename1 = args[0];
		filename2 = args[1];
		sectorString = args[2];
		granularityString = args[3];
		thresholdString = args[4];
		Set<Point> set1 = new ReadingSet(filename1).getPoints();
		Set<Point> set2 = new ReadingSet(filename2).getPoints();
		PointSet A = new PointSet(set1);
		PointSet B = new PointSet(set2);
		System.out.println("Files read into point sets...");
		int sectors = Integer.parseInt(sectorString);
		double granularity = Double.parseDouble(granularityString);
		int threshold = Integer.parseInt(thresholdString);
		Map<RTPair, Integer> results = match(A, B, sectors, granularity);
		Map<RTPair, Integer> filteredResults = filterByOccurance(results, threshold);
		Iterator<RTPair> RTIterator = filteredResults.keySet().iterator();
		System.out.println("Results: ");
		int max = 0;
		while(RTIterator.hasNext()) {
			RTPair rt = RTIterator.next();
			int freq = filteredResults.get(rt);
			if (freq > max) max = freq;
			System.out.println(rt.toString() + " : " + filteredResults.get(rt));
		}
		System.out.println("max: " + max);
	}

	public static Map<RTPair, Integer> match(PointSet A, PointSet B, int sectors, double granularity) {
		Map<RTPair, Integer> occuranceMap = new HashMap<RTPair, Integer>();
		Set<Rotation> rotations = generateRotations(sectors);
		System.out.println("Rotations Generated...");
		Iterator<Rotation> iterRot = rotations.iterator();
		while(iterRot.hasNext()) {
			Rotation curR = iterRot.next();
			PointSet Aprime = A.rotate(curR);
			Set<PointPair> pairs = generateAllPairs(Aprime, B);
			Iterator<PointPair> iterPair = pairs.iterator();
			while(iterPair.hasNext()) {
				PointPair curPair = iterPair.next();
				Translation tempTranslation = curPair.getTranslation(granularity);
				RTPair tempRTPair = new RTPair(curR, tempTranslation);
				int frequency = occuranceMap.getOrDefault(tempRTPair, 0);
				occuranceMap.put(tempRTPair, frequency + 1);
			}
		}
		return occuranceMap;
	}
	
	public static Map<RTPair, Integer> filterByOccurance(Map<RTPair, Integer> map, int threshold) {
		Map<RTPair, Integer> newMap = new HashMap<RTPair, Integer>();
		Set<RTPair> keys = map.keySet();
		Iterator<RTPair> iterKeys = keys.iterator();
		while(iterKeys.hasNext()) {
			RTPair key = iterKeys.next();
			Integer frequency = map.get(key);
			if (frequency >= threshold) {
				newMap.put(key, frequency);
			}
		}
		return newMap;
	}
	
	// TODO move to point pair
	public static Translation generateTranslation(Point p1, Point p2, double granularity) {
		return generateTranslation(p2.getX()-p1.getX(), p2.getY()-p1.getY(), p2.getZ() - p1.getZ(), granularity);
	}
	
	public static Translation generateTranslation(double x, double y, double z, double granularity) {
		return new Translation(round(x, granularity), round(y, granularity), round(z, granularity), granularity);
	}
	
	// TODO finish
	public static Set<PointPair> generateAllPairs(PointSet A, PointSet B) {
		Set<PointPair> pairs = new HashSet<PointPair>();
		Iterator<Point> iterA = A.getPoints().iterator();
		Iterator<Point> iterB;
		Point pointA, pointB;
		while (iterA.hasNext()) {
			pointA = iterA.next();
			iterB = B.getPoints().iterator();
			while (iterB.hasNext() ) {
				pointB = iterB.next();
				pairs.add(new PointPair(pointA, pointB));
			}
		}
		return pairs;
	}
	
	// TODO: test
	public static double round(double num, double granularity) {
		double multiplier = num/granularity;
		double newMultiplier = Math.round(multiplier);		
		return granularity*newMultiplier;
	}
	
	public static Set<Rotation> generateRotations(int sectors) {
		Set<Rotation> rs = new HashSet<Rotation>();
		double sectorSize = 2*Math.PI/sectors;
		for (int ix = 0; ix < sectors; ix++) {
			for (int iy = 0; iy < sectors; iy++) {
				for (int iz = 0; iz < sectors; iz++) {
					rs.add(new Rotation(ix*sectorSize, iy*sectorSize, iz*sectorSize));
				}
			}
		}
		return rs;
	}
}
