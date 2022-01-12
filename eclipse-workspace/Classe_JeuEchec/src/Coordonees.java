
public class Coordonees {
	private byte posX;
	private byte posY;
	
	public Coordonees(Coordonees from) {
		this(from.posX, from.posY);
	}
	
	public Coordonees(byte _posX, byte _posY) {
		posX = _posX;
		posY = _posY;
	}
	
	public byte getX() {
		return posX;
	}
	
	public byte getY() {
		return posY;
	}
	
	public boolean equals(Coordonees c) {
		return (c.posX == posX) && (c.posY == posY);
	}
}
