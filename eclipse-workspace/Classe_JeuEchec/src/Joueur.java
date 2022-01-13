
public class Joueur {
	private byte score;
	private String nom;
	private boolean estNoir;
	
	public Joueur(Joueur p){
		this(p.score,p.nom,p.estNoir);
	}
	
	public Joueur(String _nom, boolean _estNoir){
		score = 0;
		nom = _nom;
		estNoir =_estNoir;
	}
	
	public Joueur(byte _score, String _nom, boolean _estNoir) {
		score = _score;
		nom = _nom;
		estNoir = _estNoir;
	}
	
	public byte getScore() {
		return score;
	}
	
	public String getNom() {
		return nom;
	}
	
	public boolean isNoir() {
		return estNoir;
	}
}
