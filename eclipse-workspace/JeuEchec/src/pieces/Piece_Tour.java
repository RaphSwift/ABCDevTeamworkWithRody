package pieces;

import java.util.ArrayList;

import utils.Coordonees;
import utils.Mouvement;
import utils.Plateau;

public class Piece_Tour extends Piece {
	private boolean aBouge;
	
	public Piece_Tour(Piece from) {
		super(from);
		aBouge = false;
	}
	
	@Override
	public String toString() {
		return "[Tour" + position+"]";
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Piece_Tour(this);
	}
	
	public Piece_Tour(Coordonees position, boolean _isBlack, boolean _isDead, boolean _aBouge) {
		super(position,_isBlack,_isDead);
		aBouge = _aBouge;
	}
	
	public Piece_Tour(Coordonees position, boolean _isBlack, boolean _isDead) {
		super(position,_isBlack,_isDead);
		aBouge = false;
	}

	public Piece_Tour(Piece_Tour from) {
		this(from.position,from.isBlack,from.isDead,from.aBouge);
	}
	
	public Piece_Tour(Coordonees _position, boolean _isBlack) {
		this(_position,_isBlack,false,false);
	}
	
	@Override
	public ArrayList<Mouvement> calculerMouvement(Plateau p){
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		Piece tmp=null;
		Coordonees coordoneesTestes = new Coordonees(position);
		// A GAUCHE
		while (tmp==null && coordoneesTestes.getX()>0) {
			coordoneesTestes = (new Coordonees((byte)(coordoneesTestes.getX()-1),coordoneesTestes.getY()));
			tmp =p.getPieceFromCoord(coordoneesTestes);
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
			}
			
		}
		tmp = null;
		coordoneesTestes = new Coordonees(position);
		// A DROITE
		while (tmp==null && coordoneesTestes.getX()+1 < p.getWidth()) {
			coordoneesTestes = (new Coordonees((byte)(coordoneesTestes.getX()+1),coordoneesTestes.getY()));
			tmp = p.getPieceFromCoord(coordoneesTestes);
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
			}
			
		}
		tmp = null;
		coordoneesTestes = new Coordonees(position);
		// EN HAUT
		while (tmp==null && coordoneesTestes.getY()+1 < p.getHeight()) {
			coordoneesTestes = (new Coordonees((byte)(coordoneesTestes.getX()),(byte)(coordoneesTestes.getY()+1)));
			tmp = p.getPieceFromCoord(coordoneesTestes);
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
			}
			
		}
		tmp = null;
		coordoneesTestes = new Coordonees(position);
		// EN BAS
		while (tmp==null && coordoneesTestes.getY() > 0) {
			coordoneesTestes = (new Coordonees((byte)(coordoneesTestes.getX()),(byte)(coordoneesTestes.getY()-1)));
			tmp = p.getPieceFromCoord(coordoneesTestes);
			if (tmp != null) {
				if (tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
				}
			} else {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(coordoneesTestes)));
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
