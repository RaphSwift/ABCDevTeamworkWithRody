package pieces;

import java.util.ArrayList;

import utils.Coordonees;
import utils.Mouvement;
import utils.Plateau;

public class Piece_Fou extends Piece {
	public Piece_Fou(Piece from) {
		super(from);
	}
	
	public Piece_Fou(Coordonees position, boolean _isBlack) {
		super(position,_isBlack);
	}

	public Piece_Fou(Piece_Fou from) {
		this(from.position,from.isBlack);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return new Piece_Fou(this);
	}
	
	@Override
	public String toString() {
		return "[Fou" + position+"]";
	}
	


	
	@Override
	public ArrayList<Mouvement> calculerMouvement(Plateau p) {
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		Piece tmp=null;
		Coordonees test = new Coordonees(position);
		// SUPPERIEUR DROITE
		while (tmp==null && test.getX()>0 && test.getY()+1< p.getHeight()) {
			test = (new Coordonees((byte)(test.getX()-1),(byte)(test.getY()+1)));
			try {
				tmp = ((Piece)p.getPieceFromCoord(test).clone());
			} catch (Exception e) {
				tmp = null;
			}

			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
			}
			
		}
		tmp = null;
		test = new Coordonees(position);
		// SUPPERIEUR GAUCHE
		while (tmp==null && test.getX()+1 < p.getWidth() && test.getY()+1 < p.getHeight()) {
			test = (new Coordonees((byte)(test.getX()+1),(byte)(test.getY()+1)));
			try {
				tmp = ((Piece)p.getPieceFromCoord(test).clone());
			} catch (Exception e) {
				tmp = null;
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
			}
			
		}
		tmp = null;
		test = new Coordonees(position);
		// INFERIEUR GAUCHE
		while (tmp==null && test.getY() >0 && test.getX() > 0) {
			test = (new Coordonees((byte)(test.getX()-1),(byte)(test.getY()-1)));
			try {
				tmp = ((Piece)p.getPieceFromCoord(test).clone());
			} catch (Exception e) {
				tmp = null;
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
			}
			
		}
		tmp = null;
		test = new Coordonees(position);
		// INFERIEUR DROITE
		while (tmp==null && test.getY() > 0 && test.getX()+1<p.getWidth()) {
			test = (new Coordonees((byte)(test.getX()+1),(byte)(test.getY()-1)));
			try {
				tmp = ((Piece)p.getPieceFromCoord(test).clone());
			} catch (Exception e) {
				tmp = null;
			}
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
			}
			
		}
		Plateau simulation;
		Piece tmpRoi;
		for (int i = mouvementsPossibles.size() -1 ; i >= 0; i--) {
			simulation = p.simulerMouvement(mouvementsPossibles.get(i));
			tmpRoi = simulation.getRoi(isBlack);
			if (tmpRoi.estEnEchec(simulation).size()>0) {
				mouvementsPossibles.remove(i);
			}
		}
		return mouvementsPossibles;
	}
}
