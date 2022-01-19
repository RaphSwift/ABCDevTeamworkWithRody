package pieces;

import java.util.ArrayList;

import utils.Coordonees;
import utils.Mouvement;
import utils.Plateau;

public class Piece_Roi extends Piece{
	private boolean aBouge;
	
	public Piece_Roi(Piece from) {
		super(from);
		aBouge = false;
	}
	
	@Override
	public String toString() {
		return "[Roi" + position+"]";
	}
	
	public Piece_Roi(Coordonees position, boolean _isBlack, boolean _isDead, boolean _aBouge) {
		super(position,_isBlack,_isDead);
		aBouge = _aBouge;
	}
	
	public Piece_Roi(Coordonees position, boolean _isBlack, boolean _isDead) {
		super(position,_isBlack,_isDead);
		aBouge = false;
	}

	public Piece_Roi(Piece_Roi from) {
		this(from.position,from.isBlack,from.isDead,from.aBouge);
	}
	
	public Piece_Roi(Coordonees _position, boolean _isBlack) {
		this(_position,_isBlack,false,false);
	}
	
	public ArrayList<Mouvement> calculerMouvement(Plateau p){
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		
		return mouvementsPossibles;
	}
}
