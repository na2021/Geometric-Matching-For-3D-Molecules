import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ReadingSet {
	Set<Point> points;
	
	public ReadingSet(String filename) {
		points = new HashSet<Point>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			while(in.ready()) {
				String line = in.readLine();
				points.add(stringToPoint(line));
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("File : " + filename + " is not available");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Issue reading file: " + filename);
		}
	}
	
	// x y z
	private Point stringToPoint(String pointStr) {
		String[] components = pointStr.split("\\s+");
		double x, y, z;
		x = Double.parseDouble(components[1]);
		y = Double.parseDouble(components[2]);
		z = Double.parseDouble(components[3]);
		return new Point(x, y, z);
	}
	
	public Set<Point> getPoints() {
		return this.points;
	}

}
