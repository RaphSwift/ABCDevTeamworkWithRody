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
	
	
	@Override
	public boolean deplacerPiece(Coordonees coord, Plateau plateau) {
		ArrayList<Mouvement> mvt = calculerMouvement(plateau);
		int i = 0;
		boolean finded = false;
		while (!finded && i < mvt.size()) {
			if (coord.equals(mvt.get(i).getTo())) {				
				finded = true;
			}
			i++;
		}
		if (finded) {
			aBouge=true;
		}
		return finded;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return new Piece_Roi(this);
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
	
	@Override
	public ArrayList<Mouvement> calculerMouvement(Plateau p){
		ArrayList<Mouvement >mouvementsPossibles = new ArrayList<Mouvement>();
		Coordonees tmpCoords = null;
		Piece tmp = null;
		if (position.getY()>0) {
			tmpCoords = new Coordonees((byte)(position.getX()),(byte)(position.getY()-1));
			tmp = p.getPieceFromCoord(tmpCoords);
			if (tmp ==null || tmp.estNoir() != isBlack) {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(new Coordonees(tmpCoords))));
				tmp = null;
			}
		}
		if (position.getY()+1< p.getHeight()) {
			tmpCoords =new Coordonees((byte)(position.getX()),(byte)(position.getY()+1));
			tmp = p.getPieceFromCoord(tmpCoords);
			if (tmp ==null || tmp.estNoir() != isBlack) {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(new Coordonees(tmpCoords))));
				tmp = null;
			}
		}
		if (position.getX() > 0) {
			tmpCoords =new Coordonees((byte)(position.getX()-1),(byte)(position.getY()));
			tmp = p.getPieceFromCoord(tmpCoords);
			if (tmp ==null || tmp.estNoir() != isBlack) {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(new Coordonees(tmpCoords))));
				tmp = null;
			}
			if (position.getY()-1>=0) {
				tmpCoords =new Coordonees((byte)(position.getX()-1),(byte)(position.getY()-1));
				tmp = p.getPieceFromCoord(tmpCoords);
				if (tmp ==null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(new Coordonees(tmpCoords))));
					tmp = null;
				}
			}
			if (position.getY()+1< p.getHeight()) {
				tmpCoords =new Coordonees((byte)(position.getX()-1),(byte)(position.getY()+1));
				tmp = p.getPieceFromCoord(tmpCoords);
				if (tmp ==null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(new Coordonees(tmpCoords))));
					tmp = null;
				}
			}
		} 
		if (position.getX()+1 < p.getWidth()){
			tmpCoords =new Coordonees((byte)(position.getX()+1),(byte)(position.getY()));
			tmp = p.getPieceFromCoord(tmpCoords);
			if (tmp ==null || tmp.estNoir() != isBlack) {
				mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(new Coordonees(tmpCoords))));
				tmp = null;
			}
			if (position.getY()-1>=0) {
				tmpCoords =new Coordonees((byte)(position.getX()+1),(byte)(position.getY()-1));
				tmp = p.getPieceFromCoord(tmpCoords);
				if (tmp ==null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(new Coordonees(tmpCoords))));
					tmp = null;
				}
			}
			if (position.getY()+1< p.getHeight()) {
				tmpCoords = new Coordonees((byte)(position.getX()+1),(byte)(position.getY()+1));
				tmp = p.getPieceFromCoord(tmpCoords);
				if (tmp ==null || tmp.estNoir() != isBlack) {
					mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(new Coordonees(tmpCoords))));
					tmp = null;
				}
			}
		}
		/*Plateau tmpPlateau = null;
		Piece tmpRoi = null;
		Coordonees from, to;
		for (int i = 0; i < mouvementsPossibles.size(); i++) {
			tmpPlateau = new Plateau(p);
			//tmpPlateau.deplacerPiece(this.position,mouvementsPossibles.get(i).getTo(),isBlack);
			if(tmpPlateau.getRoi(this.isBlack).setCoord(mouvementsPossibles.get(i).getTo(), tmpPlateau)){
			//tmpPlateau.deplacerPiece(this.position,mouvementsPossibles.get(i).getTo() , this.isBlack);
			//tmpPlateau.deplacerPiece(new Mouvement(this.position, coords.get(i)),isBlack);
				if (tmpPlateau.getPieceFromCoord(mouvementsPossibles.get(i).getTo()).estEnEchec(tmpPlateau).size() >0) {
					mouvementsPossibles.remove(i);
				}
			}
			
		}*/
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
	
	public ArrayList<Mouvement> getPossiblesMouvements(Plateau p){
		ArrayList<Piece> piecesAlies = p.getPieceFromColor(isBlack);
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		for (int i = 0; i < piecesAlies.size(); i++) {
			mouvementsPossibles.addAll(piecesAlies.get(i).calculerMouvement(p));
		}
		piecesAlies.clear();
		return mouvementsPossibles;
	}
	
	public boolean estEchecEtMat(Plateau p) {
		return (getPossiblesMouvements(p).size() ==0);
		
		
	}
}
