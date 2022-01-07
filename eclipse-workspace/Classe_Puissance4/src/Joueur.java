
public class Joueur implements java.io.Serializable{
	private final String nom;
	private char couleur;
	private byte score;
	
	public Joueur(Joueur from) {
		this(from.nom,from.couleur,from.score);
	}
	
	public Joueur(String _nom, char _couleur) {
		nom = _nom;
		couleur = _couleur;
		score = 0;
	}
	
	public Joueur(String _nom, char _couleur, byte _score) {
		nom = _nom;
		couleur = _couleur;
		score = _score;
	}
	
	public final String getNom() {
		return nom;
	}
	
	public char getCouleur() {
		return couleur;
	}
	
	
	public boolean changerCouleur() {
		if (couleur == Winner.YELLOW_WINNER.getLetter()) {
			couleur = Winner.RED_WINNER.getLetter();
			return true;
		} else if (couleur == Winner.RED_WINNER.getLetter()) {
			couleur = Winner.YELLOW_WINNER.getLetter();
			return true;
		}
		return false;
	}
	

	public byte getScore(){
		return score;
	}

	public void donnerUnPoint() {
		score = (byte)(score +1);
	}
	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", couleur=" + couleur + ", score=" + score +"]";
	}
	
	
}
