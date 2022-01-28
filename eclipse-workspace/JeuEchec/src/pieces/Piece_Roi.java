package pieces;

import java.util.ArrayList;

import utils.Coordonees;
import utils.Mouvement;
import utils.Plateau;

public class Piece_Roi extends Piece{
	private boolean haveMoved;
	
	public Piece_Roi(Piece from) {
		super(from);
		haveMoved = false;
	}
	
	@Override
	public String toString() {
		return "[Roi" + position+"]";
	}
	
	public boolean aBouge() {
		return haveMoved;
	}
	
	@Override
	public boolean setCoord(Coordonees c, Plateau p){
		if (c.getX() >= 0 && c.getX() < p.getWidth() && c.getY() >= 0 && c.getY() < p.getHeight()) {
			position = c;
			haveMoved = true;
			return true;
		}
		return false;
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
			haveMoved=true;
		}
		return finded;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return new Piece_Roi(this);
	}
	
	public Piece_Roi(Coordonees position, boolean _isBlack, boolean _haveMoved) {
		super(position,_isBlack);
		haveMoved = _haveMoved;
	}
	

	public Piece_Roi(Piece_Roi from) {
		this(from.position,from.isBlack,from.haveMoved);
	}
	
	public Piece_Roi(Coordonees _position, boolean _isBlack) {
		this(_position,_isBlack,false);
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

		Plateau simulation;
		Piece tmpRoi;
		for (int i = mouvementsPossibles.size() -1 ; i >= 0; i--) {
			simulation = p.simulerMouvement(mouvementsPossibles.get(i));
			tmpRoi = simulation.getRoi(isBlack);
			if (tmpRoi.estEnEchec(simulation).size()>0) {
				mouvementsPossibles.remove(i);
			}
		}
		/* POUR LE ROQUE*/
		if (!haveMoved) {
			if (isBlack) {
				simulation = p.simulerRoque(isBlack, new Coordonees((byte)0,(byte)0));
				if (simulation != null) {
					if(simulation.getRoi(isBlack).estEnEchec(simulation).size()==0) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position),
								new Coordonees((byte)0,(byte)0),"roque"));
					}
				}
				simulation = p.simulerRoque(isBlack, new Coordonees((byte)7,(byte)0));
				if (simulation != null) {
					if(simulation.getRoi(isBlack).estEnEchec(simulation).size()==0) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position),
								new Coordonees((byte)7,(byte)0),"roque"));
					}
				}
			} else {
				simulation = p.simulerRoque(isBlack, new Coordonees((byte)0,(byte)7));
				if (simulation != null) {
					if(simulation.getRoi(isBlack).estEnEchec(simulation).size()==0) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position),
								new Coordonees((byte)0,(byte)7),"roque"));
					}
				}
				simulation = p.simulerRoque(isBlack, new Coordonees((byte)7,(byte)7));
				if (simulation != null) {
					if(simulation.getRoi(isBlack).estEnEchec(simulation).size()==0) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position),
								new Coordonees((byte)7,(byte)7),"roque"));
					}
				}
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
		
		//return (getPossiblesMouvements(p).size() ==0);
		if (getPossiblesMouvements(p).size()==0) {
			Plateau pTmp;
			if (isBlack) {
				pTmp = p.simulerRoque(isBlack, new Coordonees((byte)0,(byte)0));
				if (pTmp != null) {
					if(pTmp.getRoi(isBlack).estEnEchec(pTmp).size()==0) {
						return false;
					}
				}
				pTmp = p.simulerRoque(isBlack, new Coordonees((byte)7,(byte)0));
				if (pTmp != null) {
					if(pTmp.getRoi(isBlack).estEnEchec(pTmp).size()==0) {
						return false;
					}
				}
			} else {
				pTmp = p.simulerRoque(isBlack, new Coordonees((byte)0,(byte)7));
				if (pTmp != null) {
					if(pTmp.getRoi(isBlack).estEnEchec(pTmp).size()==0) {
						return false;
					}
				}
				pTmp = p.simulerRoque(isBlack, new Coordonees((byte)7,(byte)7));
				if (pTmp != null) {
					if(pTmp.getRoi(isBlack).estEnEchec(pTmp).size()==0) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
		
	}
}
