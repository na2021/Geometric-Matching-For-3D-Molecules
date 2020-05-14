
// Immutable point class to represent a point in 3D space
public final class Point {
	final double x, y, z;
		
	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	// Rotates the point theta radians about the z axis
	public Point rotateZ(double theta) {
		double cos = Math.cos(theta);
		double sin = Math.sin(theta);
		double newx = this.x*cos - this.y*sin;
		double newy = this.x*sin + this.y*cos;
		return new Point(newx, newy, this.z);
	}
	
	// Rotates the point theta radians about the y axis
	public Point rotateY(double theta) {
		double cos = Math.cos(theta);
		double sin = Math.sin(theta);
		double newz = this.z*cos - this.x*sin;
		double newx = this.z*sin + this.x*cos;
		return new Point(newx, this.y, newz);
	}
	
	// Rotates the point theta radians about the x axis
	public Point rotateX(double theta) {
		double cos = Math.cos(theta);
		double sin = Math.sin(theta);
		double newy = this.y*cos - this.z*sin;
		double newz = this.y*sin + this.z*cos;
		return new Point(this.x, newy, newz);
	}
	
	// Rotates the point according to the given rotation
	public Point rotate(Rotation r) {
		Point tempPoint = this.rotateX(r.getX());
		tempPoint = tempPoint.rotateY(r.getY());
		return tempPoint.rotateZ(r.getZ());
	}
	
	// Translates a point according to the given translation
	public Point translate(Translation t) {
		return new Point(this.x + t.getX(), this.y + t.getY(), this.z + t.getZ());
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public double getZ() {
		return this.z;
	}
	
	@Override
	public int hashCode() {
		return java.util.Arrays.deepHashCode(new Object[] {this.x, this.y, this.z});
	}
	
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
}
