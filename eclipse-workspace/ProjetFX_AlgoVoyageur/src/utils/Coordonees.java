package utils;

public class Coordonees {
	private int x;
	private int y;
	
	public Coordonees(int _x, int _y) {
		x = _x;
		y = _y;
	}
	
	public Coordonees(Coordonees from) {
		this(from.x, from.y);
	}
	
	public double getDstFrom(Coordonees from) {
		return Math.sqrt(Math.pow(x-from.x, 2) + Math.pow(y-from.y, 2));
	}
	
	public boolean equals(Coordonees from) {
		return (x==from.x)&&(y == from.y);
	}
	
	
}
