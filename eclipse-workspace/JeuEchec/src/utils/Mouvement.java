package utils;

public class Mouvement implements java.io.Serializable{
	private Coordonees[] coords;
	private String moveType;
	
	public Mouvement(Mouvement from) {
		this(from.coords[0],from.coords[1],from.moveType);
	}
	
	public Mouvement(Coordonees from, Coordonees to) {
		this(from,to,"move");
	}
	
	public Mouvement(Coordonees from, Coordonees to, String _moveType) {
		coords = new Coordonees[2];
		coords[0] = from;
		coords[1] = to;
		moveType = _moveType;
	}
	
	public Coordonees getFrom() {
		return coords[0];
	}
	
	public Coordonees getTo() {
		return coords[1];
	}
	
	public String getType() {
		return moveType;
	}
	
	public boolean equals(Mouvement compare) {
		return (moveType.equals(compare.getType()) &&  coords[0].equals(compare.getFrom())) && (coords[1].equals(compare.getTo()));
	}
	
	@Override
	public String toString() {
		return moveType + " from ["+coords[0].getX()+";"+coords[0].getY()+"] to ["+coords[1].getX()+";"+coords[1].getY()+"]";
	}
	
}
