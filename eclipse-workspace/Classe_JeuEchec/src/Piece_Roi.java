import java.util.ArrayList;

public class Piece_Roi extends Piece{
	private boolean aBouge;
	
	public Piece_Roi(Piece_Roi from){
		this(from.position,from.isBlack,from.isDead,from.aBouge);
	}
	
	public Piece_Roi(Coordonees _position, boolean _estNoir, boolean _estMorte, boolean _aBouger) {
		super(_position,_estNoir,_estMorte);
		aBouge = _aBouger;
		
	}
	
	public Piece_Roi(Piece from) {
		super(from);
		aBouge = false;
	}
	
	public Piece_Roi(Coordonees _position, boolean _estNoir, boolean _estMorte) {
		this(_position,_estNoir,_estMorte, false);
	}
	
	public Piece_Roi(Coordonees _position, boolean _estNoir) {
		this(_position,_estNoir,false,false);
	}
	
	public Object clone() throws CloneNotSupportedException {
		return new Piece_Roi(this);
	}
	
	@Override
	public boolean deplacer(Plateau p,Coordonees to) {
		int i = 0;
		boolean finded = false;
		ArrayList<Coordonees> coordsPossible = calculerMouvement(p);
		while(i < coordsPossible.size() && !finded) {
			if (to.equals(coordsPossible.get(i))) {
				finded = true;
			}
		}
		if (finded) {
			this.position = to;
			aBouge = true;
		}
		return finded;
	}
	

	@Override
	public ArrayList<Coordonees> calculerMouvement(Plateau p) {
		Plateau tmp = null;
		ArrayList<Coordonees> coords = new ArrayList<Coordonees>();
		Coordonees tmpCoords = null;
		if (position.getY()-1>=0) {
			tmpCoords = new Coordonees((byte)(position.getX()),(byte)(position.getY()-1));
			tmp = (Plateau)p.clone();
			tmp.deplacerPiece(new Mouvement(this.position,tmpCoords), isBlack);
			if (tmp.getPiece(tmpCoords) != null && tmp.getPiece(tmpCoords).estEnEchec(p).size() == 0) {
				coords.add(new Coordonees(tmpCoords));
			}
		}
		if (position.getY()+1< p.getHeight()) {
			tmpCoords =new Coordonees((byte)(position.getX()),(byte)(position.getY()+1));
			tmp = (Plateau)p.clone();
			tmp.deplacerPiece(new Mouvement(this.position,tmpCoords), isBlack);
			if (tmp.getPiece(tmpCoords) != null && tmp.getPiece(tmpCoords).estEnEchec(p).size() == 0) {
				coords.add(new Coordonees(tmpCoords));
			}
		}
		if (position.getX()-1 >= 0) {
			tmpCoords =new Coordonees((byte)(position.getX()-1),(byte)(position.getY()));
			tmp = (Plateau)p.clone();
			tmp.deplacerPiece(new Mouvement(this.position,tmpCoords), isBlack);
			if (tmp.getPiece(tmpCoords) != null && tmp.getPiece(tmpCoords).estEnEchec(p).size() == 0) {
				coords.add(new Coordonees(tmpCoords));
			}
			if (position.getY()-1>=0) {
				tmpCoords =new Coordonees((byte)(position.getX()-1),(byte)(position.getY()-1));
				tmp = (Plateau)p.clone();
				tmp.deplacerPiece(new Mouvement(this.position,tmpCoords), isBlack);
				if (tmp.getPiece(tmpCoords) != null && tmp.getPiece(tmpCoords).estEnEchec(p).size() == 0) {
					coords.add(new Coordonees(tmpCoords));
				}
			}
			if (position.getY()+1< p.getHeight()) {
				tmpCoords =new Coordonees((byte)(position.getX()-1),(byte)(position.getY()+1));
				tmp = (Plateau)p.clone();
				tmp.deplacerPiece(new Mouvement(this.position,tmpCoords), isBlack);
				if (tmp.getPiece(tmpCoords) != null && tmp.getPiece(tmpCoords).estEnEchec(p).size() == 0) {
					coords.add(new Coordonees(tmpCoords));
				}
			}
		} else if (position.getX()+1 < p.getWidth()){
			tmpCoords =new Coordonees((byte)(position.getX()+1),(byte)(position.getY()));
			tmp = (Plateau)p.clone();
			tmp.deplacerPiece(new Mouvement(this.position,tmpCoords), isBlack);
			if (tmp.getPiece(tmpCoords) != null && tmp.getPiece(tmpCoords).estEnEchec(p).size() == 0) {
				coords.add(new Coordonees(tmpCoords));
			}
			if (position.getY()-1>=0) {
				tmpCoords =new Coordonees((byte)(position.getX()+1),(byte)(position.getY()-1));
				tmp = (Plateau)p.clone();
				tmp.deplacerPiece(new Mouvement(this.position,tmpCoords), isBlack);
				if (tmp.getPiece(tmpCoords) != null && tmp.getPiece(tmpCoords).estEnEchec(p).size() == 0) {
					coords.add(new Coordonees(tmpCoords));
				}
			}
			if (position.getY()+1< p.getHeight()) {
				tmpCoords = new Coordonees((byte)(position.getX()+1),(byte)(position.getY()+1));
				tmp = (Plateau)p.clone();
				tmp.deplacerPiece(new Mouvement(this.position,tmpCoords), isBlack);
				if (tmp.getPiece(tmpCoords) != null && tmp.getPiece(tmpCoords).estEnEchec(p).size() == 0) {
					coords.add(new Coordonees(tmpCoords));
				}
			}
		}
		return coords;
	}
	
	public boolean estEchecEtMat(Plateau p) {
		// SOUS QUEL CAS LA PIECE EST EN ECHEC ET MAT
		ArrayList<Mouvement> misEnEchec = estEnEchec(p);
		ArrayList<Coordonees> coordoneesPossible = new ArrayList<Coordonees>();
		if (misEnEchec.size() == 0) {
			return false;
		} else {
			if (misEnEchec.size() == 1) {
				// PEUT-ON MANGER LA PIECE QUI PLACE LE ROI EN ECHEC
				Piece tmp = p.getPiece(misEnEchec.get(0).getFrom());
				if (tmp != null) {
					if (tmp.estEnEchec(p).size() >0) {
						return false;
					} else {
						if (!(tmp instanceof Piece_Cavalier)) {
							// SI CE N'EST PAS UN CAVALIER ET QUE LA PIECE MENACANTE N'EST PAS EN ECHEC
							Coordonees from = new Coordonees((byte)(this.position.getX()-misEnEchec.get(0).getFrom().getX()),
											(byte)(this.position.getY()-misEnEchec.get(0).getFrom().getY()));
							Coordonees vector = new Coordonees((byte)(from.getX()/Math.abs(from.getX())),
									(byte)(from.getY()/Math.abs(from.getY())));
							int nbRep = Math.max(Math.abs(from.getX()), Math.abs(from.getY()));
							int i;
							for (i = 0; i < nbRep; i++) {
								coordoneesPossible.add(new Coordonees((byte)(position.getX()+i*vector.getX()),
										(byte)(position.getX()+i*vector.getY())));
							}
							i = 0;
							int j,k;
							ArrayList<Piece> pieces = p.getPieceFromColor(isBlack);
							ArrayList<Coordonees> coords= null;
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
				ArrayList<Coordonees> mvt = calculerMouvement(p);
				if (mvt.size() > 0) {
					return false;
				}
			} else {
				// UN DEPLACEMENT DU ROI N'EST PAS EN ECHEC
				ArrayList<Coordonees> mvt = calculerMouvement(p);
				if (mvt.size() > 0) {
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
