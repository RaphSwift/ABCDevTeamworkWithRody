
public class Jeton extends Coordonees{
	public static final boolean JETON_ROUGE = true;
	public static final boolean JETON_JAUNE = false;
	boolean couleurJeton;
	
	public Jeton(Jeton from) {
		this(from.couleurJeton, from.posX, from.posY);
	}
	
	public Jeton(boolean _couleurJeton, byte _posX, byte _posY) {
		super(_posX,_posY);
		couleurJeton = _couleurJeton;		
	}
	
	public boolean equals(Jeton j) {
		return (j.couleurJeton == couleurJeton) && (j.posX == posX) && (j.posY == posY);
	}
	
	
}
