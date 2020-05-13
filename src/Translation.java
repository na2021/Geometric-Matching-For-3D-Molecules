
public final class Translation {
	final double x, y ,z;
	
	public Translation(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
