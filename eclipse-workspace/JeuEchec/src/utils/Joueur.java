package utils;

public class Joueur {
	private byte score;
	private String nom;
	private boolean isBlack;
	
	public Joueur(Joueur from) {
		this(from.nom, from.isBlack, from.score);
	}
	
	public Joueur(String _nom, boolean _isBlack, byte _score) {
		nom = _nom;
		isBlack = _isBlack;
		score = _score;
	}
	
	public Joueur(String _nom, boolean _isBlack) {
		this(_nom,_isBlack,(byte)0);
	}
	
	public byte getScore() {
		return score;
	}
	
	public String getNom() {
		return nom;
	}
	
	public boolean estNoir() {
		return isBlack;
	}
}
