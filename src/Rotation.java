
public final class Rotation {
	final double x, y, z;
	
	public Rotation(double x, double y, double z) {
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
	public boolean equals(Object other) {
		return other.getClass() == this.getClass() && 
				Math.abs(((Rotation)other).getX() - (this.getX())) < .01 &&  
				Math.abs(((Rotation)other).getY() - (this.getY())) < .01 && 
				Math.abs(((Rotation)other).getZ() - (this.getZ())) < .01;
	}
	
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
}
