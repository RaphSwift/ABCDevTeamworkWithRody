package pieces;

import java.util.ArrayList;

import utils.Coordonees;
import utils.Mouvement;
import utils.Plateau;

public class Piece_Pion extends Piece{
	private boolean haveMoved;
	
	public Piece_Pion(Piece from) {
		super(from);
		haveMoved = false;
	}
	
	public boolean aBouge() {
		return haveMoved;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return new Piece_Pion(this);
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
	public String toString() {
		return "[Pion" + position+"]";
	}
	
	public Piece_Pion(Coordonees position, boolean _isBlack, boolean _isDead, boolean _haveMoved) {
		super(position,_isBlack,_isDead);
		haveMoved = _haveMoved;
	}
	
	public Piece_Pion(Coordonees position, boolean _isBlack, boolean _isDead) {
		super(position,_isBlack,_isDead);
		haveMoved = false;
	}

	public Piece_Pion(Piece_Pion from) {
		this(from.position,from.isBlack,from.isDead,from.haveMoved);
	}
	
	public Piece_Pion(Coordonees _position, boolean _isBlack) {
		this(_position,_isBlack,false,false);
	}
	
	@Override
	public boolean deplacerPiece(Coordonees coord, Plateau plateau) {
		Piece tmp = plateau.getPieceFromCoord(coord);
		ArrayList<Mouvement> mvt = new ArrayList<Mouvement>();
		if (tmp != null && tmp.estNoir() != isBlack) {
			mvt = calculerMouvementManger(plateau);
		} else {
			mvt = calculerMouvement(plateau);
		}
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
	public ArrayList<Mouvement> calculerMouvement(Plateau p){
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		Coordonees test;
		Coordonees test2;

		Piece tmp = null;
		Piece tmp2 = null;
		if (isBlack) {
			if (!haveMoved) {
				if (position.getY()+2 >= 0) {
					test = new Coordonees((byte)(position.getX()),(byte)(position.getY()+1));
					tmp = p.getPieceFromCoord(test);
					test2 = new Coordonees((byte)(position.getX()),(byte)(position.getY()+2));
					tmp2 = p.getPieceFromCoord(test);					
					if (tmp == null) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
						if (tmp2 == null) {
							mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test2)));
						}
					}
				} else if (position.getY()+1 >= 0) {
					test = new Coordonees((byte)(position.getX()),(byte)(position.getY()+1));
					tmp = p.getPieceFromCoord(test);
					if (tmp == null) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
					}
				}
			} else {
				if (position.getY()+1 >= 0) {
					test = new Coordonees((byte)(position.getX()),(byte)(position.getY()+1));
					tmp = p.getPieceFromCoord(test);
					if (tmp == null) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
					}
				}
			}
		} else {
			//Y +2
			if (!haveMoved) {
				if (position.getY()-2 < p.getHeight()) {
					test = new Coordonees((byte)(position.getX()),(byte)(position.getY()-1));
					tmp = p.getPieceFromCoord(test);
					test2 = new Coordonees((byte)(position.getX()),(byte)(position.getY()-2));
					tmp2 = p.getPieceFromCoord(test);					
					if (tmp == null) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
						if (tmp2 == null) {
							mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test2)));
						}
					}
				} else if (position.getY()-1 < p.getHeight()) {
					test = new Coordonees((byte)(position.getX()),(byte)(position.getY()-1));
					tmp = p.getPieceFromCoord(test);
					if (tmp == null) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
					}
				}
			} else {
				if (position.getY()-1 < p.getHeight()) {
					test = new Coordonees((byte)(position.getX()),(byte)(position.getY()-1));
					tmp = p.getPieceFromCoord(test);
					if (tmp == null) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(test)));
					}
				}
			}
			
		}
		Plateau simulation;
		Piece tmpRoi;
		ArrayList<Mouvement> roiMv = new ArrayList<Mouvement>();
		for (int i = mouvementsPossibles.size() -1 ; i >= 0; i--) {
			simulation = p.simulerMouvement(mouvementsPossibles.get(i));
			if (simulation != null) {
				tmpRoi = simulation.getRoi(isBlack);
				roiMv = tmpRoi.estEnEchec(simulation);
				
				if (roiMv.size()>0) {
					
					mouvementsPossibles.remove(i);
				}
				roiMv.clear();
			}
		}
		return mouvementsPossibles;
	}
	
	public ArrayList<Mouvement> calculerMouvementManger(Plateau p){
		ArrayList<Mouvement> mouvementsPossibles = new ArrayList<Mouvement>();
		Coordonees test;
		Piece tmp = null;
		if (position.getX()-1 >= 0) {
			// A GAUCHE C'EST POSSIBLE
			if (isBlack) {
				if (position.getY()+1 >= 0) {
					// VERS LE BAS SI NOIR AUSSI
					test = new Coordonees((byte)(position.getX()-1),(byte)(position.getY()+1));
					tmp = p.getPieceFromCoord(test);
					if (tmp != null) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(new Coordonees(test))));
					}
				}
			} else {
				// VERS LE HAUT SI BLANC
				if (position.getY()-1 < p.getHeight()) {
					test = new Coordonees((byte)(position.getX()-1),(byte)(position.getY()-1));
					tmp = p.getPieceFromCoord(test);
					if (tmp != null) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(new Coordonees(test))));
					}
				}
			}
		}
		if (position.getX()+1 < p.getWidth()) {
			if (isBlack) {
				if (position.getY()+1 >= 0) {
					// VERS LE BAS SI NOIR AUSSI
					test = new Coordonees((byte)(position.getX()+1),(byte)(position.getY()+1));
					tmp = p.getPieceFromCoord(test);
					if (tmp != null) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(new Coordonees(test))));
					}
				}
			} else {
				if (position.getY()-1 < p.getHeight()) {
					// VERS LE HAUT SI BLANCE
					test = new Coordonees((byte)(position.getX()+1),(byte)(position.getY()-1));
					tmp = p.getPieceFromCoord(test);
					if (tmp != null) {
						mouvementsPossibles.add(new Mouvement(new Coordonees(this.position), new Coordonees(new Coordonees(test))));
					}
				}
			}
		}
		/*Plateau tmpPlateau = null;
		Piece tmpRoi = null;
		Coordonees from, to;*/
		/*for (int i = 0; i < mouvementsPossibles.size(); i++) {
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
		Plateau plateauSimule = null;
		Piece roi = null;
		for (int i = mouvementsPossibles.size()-1; i >= 0;i--) {
			plateauSimule = p.simulerMouvement(mouvementsPossibles.get(i));
			if (plateauSimule != null) {
				roi = plateauSimule.getRoi(isBlack);
				if (roi.estEnEchec(plateauSimule).size() > 0) {
					mouvementsPossibles.remove(i);
				}
			}
		}
		
		return mouvementsPossibles;
	}
}
