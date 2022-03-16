package utils;

public class Ville {
	private String nom;
	private Coordonees coord;
	
	public Ville(String _nom, Coordonees _coord) {
		nom = _nom;
		coord = _coord;
	}
	
	public Ville(Ville from) {
		this(from.nom,new Coordonees(from.coord));
	}
	
	public Ville(String _nom, int _x, int _y) {
		nom = _nom;
		coord = new Coordonees(_x,_y);
	}
	
	public double getDstFrom(Ville from) {
		return coord.getDstFrom(from.coord);
	}
	
	public String getNom() { return nom;}
	
	public Coordonees getCoord() { return coord;}
	
	@Override
	public String toString() {
		return nom;
	}
}
