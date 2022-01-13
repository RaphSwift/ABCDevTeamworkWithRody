import java.util.ArrayList;

public class Plateau {
	private final byte width;
	private final byte height;
	public ArrayList<Piece> pieces;
	public ArrayList<Mouvement> mouvements;
	
	public Plateau(Plateau from) {
		this(from.width, from.height);
	}
	
	public Plateau(byte _width, byte _height) {
		width = _width;
		height = _height;
		pieces = new ArrayList<Piece>();
		mouvements = new ArrayList<Mouvement>();
	}
	
	public void finalize() {
		pieces.clear();
		mouvements.clear();
	}
	
	/*public boolean verifierPlateau() {
		
	}*/
	
	public boolean deplacerPiece(Mouvement m, boolean joueurNoir) {
		Piece tmp= getPiece(m.from);
		if (tmp != null) {
			return deplacerPiece(tmp, m.to, joueurNoir);
		}
		return false;
	}
	
	public boolean deplacerPiece(Piece piece, Coordonees coord, boolean joueurNoir) {
		// LES COORDONNEES EXISTENT SUR LE PLATEAU
		if (coord.getX() >=  0 && coord.getX() < width &&
				coord.getY() >= 0 && coord.getY() < height) {
			if (piece != null) {
				
				ArrayList<Coordonees> mouvementsTmp = new ArrayList<Coordonees>();
				Piece pieceDestination = null;
				int finded = -1;
				int i = 0;
				
				if (piece.estNoir()==joueurNoir) {
				//-- LE JOUEUR QUI JOUE CORRESPOND AU POSSESSEUR DE LA PIECE
					if (piece instanceof Piece_Pion) {
						// LE CAS DU PION EST TRAITE
						pieceDestination = getPiece(coord);
						
						
						if (pieceDestination != null) {
							// IL Y'A UNE PIECE
							if (pieceDestination.estNoir() != piece.estNoir()) { // ET C'EST UNE PIECE ADVERSE
								mouvementsTmp = ((Piece_Pion) piece).calculerMouvementManger(this);
							} else {
								return false;
							}
						} else {
							// IL N'Y A PAS DE PIECE
							mouvementsTmp = piece.calculerMouvement(this);
						}
						// LE MOUVEMENT EST IL POSSIBLE
						while (finded<0 &&  i < mouvementsTmp.size()) {
							if (mouvementsTmp.get(i).equals(coord)) {
								finded = i;
							}
							i++;
						}
						// SI LE MOUVEMENT FAIT PARTIE DES POSSIBILITES
						if (finded >= 0) {
							if (pieceDestination != null) {
								// ON TUE LA PIECE ADVERSE SI IL Y'EN A UNE
								pieceDestination.tuer();
							}
							piece.setCoord(coord);
							mouvements.add(new Mouvement(new Coordonees(piece.getPosition()),coord));
						}
					} else {
						// CE N'EST PAS UN PION
						mouvementsTmp = piece.calculerMouvement(this);
						
						
						while (finded<0 &&  i < mouvementsTmp.size()) {
							if (mouvementsTmp.get(i).equals(coord)) {
								finded = i;
							}
							i++;
						}
						if (finded >= 0) {
							pieceDestination = getPiece(coord);
							if (pieceDestination != null) {
								if (pieceDestination.estNoir() != joueurNoir) {
									// ON MANGE LA PIECE
									if (!pieceDestination.estMorte()) {
										pieceDestination.tuer();
									}
									piece.setCoord(coord);
									mouvements.add(new Mouvement(new Coordonees(piece.getPosition()),coord));
									return true;
								}
							} else {
								return true;
							}
						}
					}
				}
				//
			}
		}
		return false;
	}
	
	public Piece getPiece(Coordonees coord) {
		int i = 0;
		int finded = -1;
		while(finded <0 || i < pieces.size()) {
			if (pieces.get(i).getPosition().equals(coord)) {
				finded = i;
			}
			i++;
		}
		if (finded >= 0) {
			return pieces.get(i);
		}
		return null;
	}
	
}
