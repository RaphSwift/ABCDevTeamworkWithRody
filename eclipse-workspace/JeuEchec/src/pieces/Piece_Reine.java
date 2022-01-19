package pieces;

import java.util.ArrayList;

import utils.Coordonees;
import utils.Mouvement;
import utils.Plateau;

public class Piece_Reine extends Piece{
	
	public Piece_Reine(Piece from) {
		super(from);
	}
	
	@Override
	public String toString() {
		return "[Reine" + position+"]";
	}
	
	public Piece_Reine(Coordonees position, boolean _isBlack, boolean _isDead) {
		super(position,_isBlack,_isDead);
	}

	public Piece_Reine(Piece_Reine from) {
		this(from.position,from.isBlack,from.isDead);
	}
	
	public Piece_Reine(Coordonees _position, boolean _isBlack) {
		this(_position,_isBlack,false);
	}
	
	public ArrayList<Mouvement> calculerMouvement(Plateau p){
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		
		return mouvementsPossibles;
	}
}
