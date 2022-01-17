import java.util.ArrayList;

public abstract class Piece implements Cloneable {
	protected final boolean isBlack;
	protected boolean isDead;
	protected Coordonees position;
	
	public Piece(Piece from) {
		this(from.position,from.isBlack,from.isDead);
	}
	
	public abstract Object clone() throws CloneNotSupportedException;
	
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
	
	public ArrayList<Mouvement> estEnEchec(Plateau p) {
		ArrayList<Mouvement> mvtFinded = new ArrayList<Mouvement>();
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		for (int i = 0; i < p.getPieces().size();i++) {
			if (p.getPieces().get(i).estNoir() != isBlack) {
				pieces.add(p.getPieces().get(i));
			}
		}
		ArrayList<Coordonees> mouvements = new ArrayList<Coordonees>();
		for (int i = 0; i < pieces.size();i++){
			if (pieces.get(i) instanceof Piece_Pion) {
				mouvements = ((Piece_Pion) (pieces.get(i))).calculerMouvementManger(p);
			} else {
				mouvements = pieces.get(i).calculerMouvement(p);
			}
			for (int j = 0; j < pieces.size(); j++){
				if (mouvements.get(j).equals(this.position)) {
					mvtFinded.add(new Mouvement(new Coordonees(pieces.get(i).getPosition()), new Coordonees(this.position)));
				}
			}			
		}
		return mvtFinded;
	}
	
	
}
