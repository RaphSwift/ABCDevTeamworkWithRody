package utils;

public class Mouvement {
	private Coordonees[] coords;
	
	public Mouvement(Mouvement from) {
		this(from.coords[0],from.coords[1]);
	}
	
	public Mouvement(Coordonees from, Coordonees to) {
		coords = new Coordonees[2];
		coords[0] = from;
		coords[1] = to;
	}
	
	public Coordonees getFrom() {
		return coords[0];
	}
	
	public Coordonees getTo() {
		return coords[1];
	}
	
	public Coordonees getDirection() {
		return new Coordonees((byte)((int)coords[0].getX()/Math.abs((int)coords[0].getX())), 
				(byte)((int)coords[1].getY()/Math.abs((int)coords[1].getY())));
	}
	
	public boolean equals(Mouvement compare) {
		return (coords[0].equals(compare.getFrom())) && (coords[1].equals(compare.getTo()));
	}
	
}
