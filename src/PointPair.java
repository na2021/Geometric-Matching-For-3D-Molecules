
public final class PointPair {
	final Point p1, p2;
	public PointPair(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Point getP1() {
		return this.p1;
	}
	
	public Point getP2() {
		return this.p2;
	}
	
	public Translation getTranslation(double granularity) {
		return new Translation(round(this.p2.getX() - this.p1.getX(), granularity), 
				round(this.p2.getY() - this.p1.getY(), granularity), 
				round(this.p2.getZ() - this.p1.getZ(), granularity), 
				granularity);
	}
	
	public static double round(double value, double granularity) {
		double multiplier = value/granularity;
		double newMultiplier = Math.round(multiplier);		
		return granularity*newMultiplier;
	}
	
	@Override
	public int hashCode() {
		return java.util.Arrays.deepHashCode(new Object[] {this.p1, this.p2});
	}
	
	@Override
	public String toString() {
		return "( " + this.p1.toString() + " , " + this.p2.toString() + " )";
	}

}
