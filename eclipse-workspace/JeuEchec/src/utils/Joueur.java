package utils;

public class Joueur implements java.io.Serializable {
	private String nom;
	private boolean isBlack;
	
	public Joueur(Joueur from) {
		this(from.nom, from.isBlack);
	}
	
	public Joueur(String _nom, boolean _isBlack) {
		nom = _nom;
		isBlack = _isBlack;
	}
	
	
	public String getNom() {
		return nom;
	}
	
	public boolean estNoir() {
		return isBlack;
	}
}
