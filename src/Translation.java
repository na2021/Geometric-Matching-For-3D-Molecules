
public final class Translation {
	final double x, y ,z, granularity;
	
	public Translation(double x, double y, double z, double granularity) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.granularity = granularity;
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
	
	public double getGranularity() {
		return this.granularity;
	}
	
	@Override
	public int hashCode() {
		return java.util.Arrays.deepHashCode(new Object[] {this.x, this.y, this.z});
	}
	
	@Override
	public boolean equals(Object other) {
		return other.getClass() == this.getClass() && 
				Math.abs(((Translation)other).getX() - (this.getX())) < this.granularity/2 &&  
				Math.abs(((Translation)other).getY() - (this.getY())) < this.granularity/2 && 
				Math.abs(((Translation)other).getZ() - (this.getZ())) < this.granularity/2;
	}
	
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
}
