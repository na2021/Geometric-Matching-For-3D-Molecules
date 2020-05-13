import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class PointSet {
	final Set<Point> points;
	
	public PointSet(Set<Point> points) {
		this.points = points;
	}
	
	public Set<Point> getPoints() {
		return this.points;
	}
	
	public PointSet rotate(Rotation r) {
		Set<Point> rotatedPoints = new HashSet<Point>();
		Iterator<Point> iter = this.points.iterator();
		while(iter.hasNext()) {
			rotatedPoints.add(iter.next().rotate(r));
		}
		return new PointSet(rotatedPoints);
	}

	public PointSet translate(Translation t) {
		Set<Point> translatedPoints = new HashSet<Point>();
		Iterator<Point> iter = this.points.iterator();
		while(iter.hasNext()) {
			translatedPoints.add(iter.next().translate(t));
		}
		return new PointSet(translatedPoints);
	}

}
