import java.util.ArrayList;

public class Parcours {
	private ArrayList<Ville> villes;
	
	public Parcours() {
		villes = new ArrayList<Ville>();
	}
	
	public boolean add(Ville v) {
		return villes.add(v);
	}
	
	
	
	public Parcours(Parcours p) {
		villes = new ArrayList<Ville>();
		for (int i = 0; i < p.villes.size();i++) {
			villes.add(new Ville(p.villes.get(i)));
		}
	}
	
	public double getTotalDst() {
		double dst = 0;
		if (villes.size() > 1) {			
			for (int i = 1; i < villes.size(); i++) {
				dst += villes.get(i-1).getDstFrom(villes.get(i));
			}				
			dst += villes.get(villes.size()-1).getDstFrom(villes.get(0));
		}
		return dst;
	}
	
	@Override
	public String toString() {
		String rt = "";
		if (villes.size() >= 1) {
			for (int i = 0; i < villes.size(); i++) {
				rt+= villes.get(i) + "->";
			}
			rt+=villes.get(0) + ": "+ getTotalDst();
		} else if (villes.size() == 1) {
			return villes.get(0).getNom();
		}
		
		return rt;
	}
	
	public int length() {
		return villes.size();
	}
}
