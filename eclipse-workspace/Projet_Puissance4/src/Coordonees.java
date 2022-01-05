
public class Coordonees {
	protected byte posX;
	protected byte posY;
	
	public Coordonees(byte _posX, byte _posY) {
		posX = _posX;
		posY = _posY;
	}
	
	public byte getXPos() {
		return posX;
	}
	
	public byte getYPos() {
		return posY;
	}
	
	public boolean equals(Coordonees c) {
		return (posX == c.posX ) && (posY == c.posY);
	}
	
	public boolean isNextTo(Coordonees c) {
		return (Math.abs(posX-c.posX) == 1)||(Math.abs(posY-c.posY) == 1);
	}
}
