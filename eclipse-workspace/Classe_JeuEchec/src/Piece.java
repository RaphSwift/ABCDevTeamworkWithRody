import java.util.ArrayList;

public abstract class Piece {
	protected boolean isBlack;
	protected boolean isDead;
	protected Coordonees position;
	
	public Piece(Piece from) {
		this(from.position,from.isBlack,from.isDead);
	}
	
	public Piece(Coordonees _position, boolean _estNoir) {
		this(_position,_estNoir,false);
	}
	
	public Piece(Coordonees _position, boolean _estNoir,  boolean _estMorte) {
		position = _position;
		isBlack = _estNoir;
		isDead = _estMorte;
	}
	
	public void tuer() {
		isDead = true;
	}
	
	public void setCoord(Coordonees _coord) {
		position = _coord;
	}
	
	public abstract ArrayList<Coordonees>calculerMouvement(Plateau p);
	
	public boolean estNoir() {
		return isBlack;
	}
	
	public boolean estMorte() {
		return isDead;
	}
	
	public Coordonees getPosition() {
		return position;
	}
	
}
