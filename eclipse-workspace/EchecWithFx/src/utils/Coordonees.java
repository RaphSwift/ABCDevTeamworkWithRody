package utils;

public class Coordonees implements java.io.Serializable{
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
	
	public Coordonees getDirection() {
		byte x=0;
		byte y=0;
		if (xPos != 0) {
			x = (byte)(xPos/Math.abs(xPos));
		}
		if (yPos != 0) {
			y = (byte)(yPos/Math.abs(yPos));
		}
		return new Coordonees(x,y);
	}

	public Coordonees toAbsolute(){
		return new Coordonees((byte)Math.abs(xPos),(byte)Math.abs(yPos));
	}
	@Override
	public String toString() {
		return ((char)(((int)'A')+xPos)) + "" + yPos;
	}
	
	public Coordonees getDistanceFrom(Coordonees c) {
		return new Coordonees((byte)(c.getX()-xPos),(byte)(c.getY()-yPos));
	}
}
