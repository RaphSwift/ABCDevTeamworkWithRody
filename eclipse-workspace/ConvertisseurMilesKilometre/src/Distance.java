
public class Distance {
	public Distance(float dst, String dstu){
		distance = dst;
		unite = dstu;
	}
	
	public float getDistance() {
		return this.distance;
	}
	
	public String getUnite() {
		return this.unite;
	}
	
	public void convertir() {
		if (unite.equals("km")) {
			distance= distance/1.609f;
			unite = "mi";
		} else {
			distance = distance*1.609f;
			unite = "km";
		}
	}
	
	private float distance;
	private String unite;
}
