
public class Jeton implements java.io.Serializable{
	public final char couleur;
	
	public Jeton(char _couleur) {
		couleur = _couleur;
	}
	
	public final char getCouleur() {
		return couleur;
	}

	@Override
	public String toString() {
		return "Jeton [couleur=" + couleur + "]";
	}
	
	
}
