package pieces;

import java.util.ArrayList;

import utils.Coordonees;
import utils.Mouvement;
import utils.Plateau;

public class Piece_Fou extends Piece{
	public Piece_Fou(Piece from) {
		super(from);
	}
	
	@Override
	public String toString() {
		return "[Fou" + position+"]";
	}
	
	public Piece_Fou(Coordonees position, boolean _isBlack, boolean _isDead) {
		super(position,_isBlack,_isDead);
	}

	public Piece_Fou(Piece_Fou from) {
		this(from.position,from.isBlack,from.isDead);
	}
	
	public Piece_Fou(Coordonees _position, boolean _isBlack) {
		this(_position,_isBlack,false);
	}
	
	public ArrayList<Mouvement> calculerMouvement(Plateau p){
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		
		return mouvementsPossibles;
	}
}
