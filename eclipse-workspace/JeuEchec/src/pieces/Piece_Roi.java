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
		return mouvementsPossibles;
	}
	
	public boolean estEchecEtMat(Plateau p) {
		// SOUS QUEL CAS LA PIECE EST EN ECHEC ET MAT
		ArrayList<Mouvement> misEnEchec = estEnEchec(p);
		ArrayList<Mouvement> coordoneesPossible = new ArrayList<Mouvement>();
		if (misEnEchec.size() == 0) {
			return false;
		} else {
			if (misEnEchec.size() == 1) {
				// PEUT-ON MANGER LA PIECE QUI PLACE LE ROI EN ECHEC
				Piece tmp = p.getPieceFromCoord(misEnEchec.get(0).getFrom());
				if (tmp != null) {
					if (tmp.estEnEchec(p).size() >0) {
						return false;
					} else {
						if (!(tmp instanceof Piece_Cavalier)) {
							// SI CE N'EST PAS UN CAVALIER ET QUE LA PIECE MENACANTE N'EST PAS EN ECHEC
							Coordonees from = new Coordonees((byte)(this.position.getX()-misEnEchec.get(0).getFrom().getX()),
											(byte)(this.position.getY()-misEnEchec.get(0).getFrom().getY()));
							Coordonees vector = from.getDirection();
							int nbRep = Math.max(Math.abs(from.getX()), Math.abs(from.getY()));
							int i;
							for (i = 0; i < nbRep; i++) {
								coordoneesPossible.add(new Mouvement(new Coordonees(this.position), new Coordonees((byte)(position.getX()+i*vector.getX()),
										(byte)(position.getX()+i*vector.getY()))));
							}
							i = 0;
							int j,k;
							ArrayList<Piece> pieces = p.getPieceFromColor(isBlack);
							ArrayList<Mouvement> coords= null;
							boolean finded = false;
							while (i < coordoneesPossible.size() && !finded) {
								j=0;
								
								while (j < pieces.size() && !finded) {
									k=0;
									coords = pieces.get(j).calculerMouvement(p);
									while (k < coords.size() && !finded) {
										if (coordoneesPossible.get(i).equals(coords.get(k)))
											finded = true;
										k++;
									}
									j++;
								}
								i++;
							}
							if (pieces != null)
								pieces.clear();
							if (coords != null)
								coords.clear();
							if (finded) {
								return false;
							}
							
						}
					}
				}
				
				// UN DEPLACEMENT DU ROI LE SORT IL DE SA POSITION D'ECHEC
				ArrayList<Mouvement> mvt = calculerMouvement(p);
				ArrayList <Mouvement> mvtAdverse = new ArrayList<Mouvement>();
				ArrayList <Piece> pieces = p.getPieceFromColor(!isBlack);
				int i = 0;
				int j = 0;
				for (i = 0; i<pieces.size(); i++) {
					mvtAdverse.addAll(pieces.get(i).calculerMouvement(p));
				}
				i=0;
				j=0;
				boolean finded;
				while (i< mvt.size()) {
					finded = false;
					while (j < mvtAdverse.size() && !finded) {
						if (mvt.get(i).getTo().equals(mvtAdverse.get(j).getTo())) {
							finded = true;
							mvt.remove(i);
						}
						j++;
					}
					i++;
				}
				if (mvt.size() > 0) {
					if (misEnEchec != null)
						misEnEchec.clear();
					if (coordoneesPossible != null)
						coordoneesPossible.clear();
					mvt.clear();
					mvtAdverse.clear();
					return false;
				}
			} else {
				// UN DEPLACEMENT DU ROI LE SORT IL DE SA POSITION D'ECHEC
				ArrayList<Mouvement> mvt = calculerMouvement(p);
				ArrayList <Mouvement> mvtAdverse = new ArrayList<Mouvement>();
				ArrayList <Piece> pieces = p.getPieceFromColor(!isBlack);
				int i = 0;
				int j = 0;
				for (i = 0; i<pieces.size(); i++) {
					mvtAdverse.addAll(pieces.get(i).calculerMouvement(p));
				}
				i=0;
				j=0;
				boolean finded;
				while (i< mvt.size()) {
					finded = false;
					while (j < mvtAdverse.size() && !finded) {
						if (mvt.get(i).getTo().equals(mvtAdverse.get(j).getTo())) {
							finded = true;
							mvt.remove(i);
						}
						j++;
					}
					i++;
				}
				if (mvt.size() > 0) {
					if (misEnEchec != null)
						misEnEchec.clear();
					if (coordoneesPossible != null)
						coordoneesPossible.clear();
					mvt.clear();
					mvtAdverse.clear();
					return false;
				}
				// LE SACRIFICE D'UNE PIECE PEUT IL MODIFIER CELA
				
				return true;
			}
		}
		if (misEnEchec != null)
			misEnEchec.clear();
		if (coordoneesPossible != null)
			coordoneesPossible.clear();
		return true;
		
	}
}
