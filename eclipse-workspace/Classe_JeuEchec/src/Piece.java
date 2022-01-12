import java.util.ArrayList;

public abstract class Piece {
	protected boolean estNoir;
	protected boolean estMorte;
	protected Coordonees position;
	
	public Piece(Piece from) {
		this(from.position,from.estNoir,from.estMorte);
	}
	
	public Piece(Coordonees _position, boolean _estNoir) {
		this(_position,_estNoir,false);
	}
	
	public Piece(Coordonees _position, boolean _estNoir,  boolean _estMorte) {
		position = _position;
		estNoir = _estNoir;
		estMorte = _estMorte;
	}
	
	public void tuer() {
		estMorte = true;
	}
	
	public abstract ArrayList<Coordonees>calculerMouvement(Plateau p);
	
	public boolean estNoir() {
		return estNoir;
	}
	
	public boolean estMort() {
		return estMorte;
	}
	
	public Coordonees getPosition() {
		return position;
	}
	
}
