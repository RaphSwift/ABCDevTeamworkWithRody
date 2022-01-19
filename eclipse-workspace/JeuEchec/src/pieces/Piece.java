package pieces;

import java.util.ArrayList;

import utils.Coordonees;
import utils.Mouvement;
import utils.Plateau;

public abstract class Piece {
	protected boolean isBlack;
	protected Coordonees position;
	protected boolean isDead;
	
	public Piece(Piece from) {
		this(from.position, from.isBlack, from.isDead);
	}
	
	public Piece(Coordonees _position, boolean _isBlack, boolean _isDead) {
		position = _position;
		isBlack = _isBlack;
		isDead = _isDead;
	}
	
	public Piece (Coordonees _position, boolean _isBlack) {
		this(_position,_isBlack,false);
	}
	
	public boolean estNoir(){
		return isBlack;
	}
	
	public boolean estMorte() {
		return isDead;
	}
	
	public Coordonees getPosition() {
		return position;
	}
	
	@Override
	public abstract String toString();
	
	
	
	public abstract ArrayList<Mouvement> calculerMouvement(Plateau p);
	
	public ArrayList<Mouvement> estEnEchec(Plateau p) {
		ArrayList<Mouvement> mvtFinded = new ArrayList<Mouvement>();
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		pieces = p.getPieceFromColor(!isBlack);
		
		for (int i = 0; i < p.getPieces().size();i++) {
			if (p.getPieces().get(i).estNoir() != isBlack) {
				pieces.add(p.getPieces().get(i));
			}
		}
		ArrayList<Mouvement> mouvements = new ArrayList<Mouvement>();
		for (int i = 0; i < pieces.size();i++){
			if (pieces.get(i) instanceof Piece_Pion) {
				mouvements = ((Piece_Pion) (pieces.get(i))).calculerMouvementManger(p);
			} else {
				mouvements = pieces.get(i).calculerMouvement(p);
			}
			for (int j = 0; j < mouvements.size(); j++){
				if (mouvements.get(j).getTo().equals(this.position)) {
					mvtFinded.add(new Mouvement(new Coordonees(pieces.get(i).getPosition()), new Coordonees(this.position)));
				}
			}			
		}
		return mvtFinded;
	}
}
