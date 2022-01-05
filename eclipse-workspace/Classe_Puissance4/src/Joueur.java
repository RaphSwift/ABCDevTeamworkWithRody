
public class Joueur {
	private final String nom;
	private final char couleur;
	
	public Joueur(String _nom, char _couleur) {
		nom = _nom;
		couleur = _couleur;
	}
	
	public final String getNom() {
		return nom;
	}
	
	public final char getCouleur() {
		return couleur;
	}
	
}
