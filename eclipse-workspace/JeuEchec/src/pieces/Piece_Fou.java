package pieces;

import java.util.ArrayList;

import utils.Coordonees;
import utils.Mouvement;
import utils.Plateau;

public class Piece_Fou extends Piece {
	public Piece_Fou(Piece from) {
		super(from);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return new Piece_Fou(this);
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
		/*Plateau tmpPlateau = null;
		Piece tmpRoi = null;
		Coordonees from, to;
		for (int i = 0; i < mouvementsPossibles.size(); i++) {
			tmpPlateau = new Plateau(p);
			from = new Coordonees(mouvementsPossibles.get(i).getFrom());
			to = new Coordonees(mouvementsPossibles.get(i).getTo());
			//tmpPlateau.deplacerPiece(this.position,mouvementsPossibles.get(i).getTo(),isBlack);
			tmpPlateau.getPieceFromCoord(this.position).setCoord(to,tmpPlateau);
			//tmpPlateau.deplacerPiece(this.position,mouvementsPossibles.get(i).getTo() , this.isBlack);
			//tmpPlateau.deplacerPiece(new Mouvement(this.position, coords.get(i)),isBlack);
			tmpRoi = (Piece_Roi)tmpPlateau.getRoi(isBlack);
			if (tmpRoi.estEnEchec(tmpPlateau).size() >0) {
				mouvementsPossibles.remove(i);
			}
			
			
		}*/
		return mouvementsPossibles;
	}
}
