
public final class RTPair {
	final Rotation r;
	final Translation t;
	
	public RTPair (Rotation r, Translation t) {
		this.r = r;
		this.t = t;
	}
	
	public Rotation getRotation() {
		return this.r;
	}
	
	public Translation getTranslation() {
		return this.t;
	}
	
	@Override
	public int hashCode() {
		return java.util.Arrays.deepHashCode(new Object[] {this.r, this.t});
	}
	
	@Override
	public String toString() {
		return "Rotation: " + r.toString() + ", Translation: " + t.toString();
	}
}
