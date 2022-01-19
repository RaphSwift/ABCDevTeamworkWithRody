package utils;

public class Coordonees {
	private byte xPos;
	private byte yPos;
	
	public Coordonees(Coordonees from) {
		this(from.xPos, from.yPos);
	}

	public Coordonees(byte x, byte y) {
		xPos = x;
		yPos = y;
	}
	
	public byte getX() { 
		return xPos;
	}
	
	public byte getY() {
		return yPos;
	}
	
	public boolean equals(Coordonees compare) {
		return (xPos == compare.getX())&&(yPos == compare.getY());
	}
	
	@Override
	public String toString() {
		return "[x:" + xPos + ";y:"+yPos+"]";
	}
}
