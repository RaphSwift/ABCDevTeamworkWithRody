package pieces;

import java.util.ArrayList;

import utils.Coordonees;
import utils.Mouvement;
import utils.Plateau;

public class Piece_Pion extends Piece{
	private boolean aBouge;
	
	public Piece_Pion(Piece from) {
		super(from);
		aBouge = false;
	}
	
	@Override
	public String toString() {
		return "[Pion" + position+"]";
	}
	
	public Piece_Pion(Coordonees position, boolean _isBlack, boolean _isDead, boolean _aBouge) {
		super(position,_isBlack,_isDead);
		aBouge = _aBouge;
	}
	
	public Piece_Pion(Coordonees position, boolean _isBlack, boolean _isDead) {
		super(position,_isBlack,_isDead);
		aBouge = false;
	}

	public Piece_Pion(Piece_Pion from) {
		this(from.position,from.isBlack,from.isDead,from.aBouge);
	}
	
	public Piece_Pion(Coordonees _position, boolean _isBlack) {
		this(_position,_isBlack,false,false);
	}
	
	public ArrayList<Mouvement> calculerMouvement(Plateau p){
		
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		
		return mouvementsPossibles;
	}
	
	public ArrayList<Mouvement> calculerMouvementManger(Plateau p){
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		
		return mouvementsPossibles;
	}
}
