import java.util.ArrayList;

public class Plateau {
	private final byte width;
	private final byte height;
	private ArrayList<Piece> pieces;
	private ArrayList<Mouvement> mouvements;
	private short nbCoups;
	
	public Plateau(Plateau from) {
		this(from.width, from.height,from.pieces,from.mouvements,from.nbCoups);
	}
	
	public Plateau(byte _width, byte _height) {
		width = _width;
		height = _height;
		pieces = new ArrayList<Piece>();
		mouvements = new ArrayList<Mouvement>();
		nbCoups = 0;
		reset();
	}
	
	public Plateau(byte _width, byte _height, ArrayList<Piece> _pieces,ArrayList<Mouvement> _mouvements, short _nbCoups) {
		width = _width;
		height = _height;
		pieces = _pieces;
		mouvements = _mouvements;
		nbCoups = _nbCoups;
	}
	
	
	public void reset() {
		if (pieces.size() > 0) {
			pieces.clear();
		}
		pieces.add(new Piece_Tour(new Coordonees((byte)0,(byte)0), true));
		pieces.add(new Piece_Cavalier(new Coordonees((byte)1,(byte)0), true));
		pieces.add(new Piece_Fou(new Coordonees((byte)2,(byte)0), true));
		pieces.add(new Piece_Reine(new Coordonees((byte)3,(byte)0), true));
		pieces.add(new Piece_Roi(new Coordonees((byte)4,(byte)0), true));
		pieces.add(new Piece_Fou(new Coordonees((byte)5,(byte)0), true));
		pieces.add(new Piece_Cavalier(new Coordonees((byte)6,(byte)0), true));
		pieces.add(new Piece_Tour(new Coordonees((byte)7,(byte)0), true));
		pieces.add(new Piece_Pion(new Coordonees((byte)0,(byte)1), true));
		pieces.add(new Piece_Pion(new Coordonees((byte)1,(byte)1), true));
		pieces.add(new Piece_Pion(new Coordonees((byte)2,(byte)1), true));
		pieces.add(new Piece_Pion(new Coordonees((byte)3,(byte)1), true));
		pieces.add(new Piece_Pion(new Coordonees((byte)4,(byte)1), true));
		pieces.add(new Piece_Pion(new Coordonees((byte)5,(byte)1), true));
		pieces.add(new Piece_Pion(new Coordonees((byte)6,(byte)1), true));
		pieces.add(new Piece_Pion(new Coordonees((byte)7,(byte)1), true));
		pieces.add(new Piece_Pion(new Coordonees((byte)0,(byte)6), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)1,(byte)6), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)2,(byte)6), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)3,(byte)6), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)4,(byte)6), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)5,(byte)6), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)6,(byte)6), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)7,(byte)6), false));
		pieces.add(new Piece_Tour(new Coordonees((byte)0,(byte)0), false));
		pieces.add(new Piece_Cavalier(new Coordonees((byte)1,(byte)7), false));
		pieces.add(new Piece_Fou(new Coordonees((byte)2,(byte)7), false));
		pieces.add(new Piece_Reine(new Coordonees((byte)3,(byte)7), false));
		pieces.add(new Piece_Roi(new Coordonees((byte)4,(byte)7), false));
		pieces.add(new Piece_Fou(new Coordonees((byte)5,(byte)7), false));
		pieces.add(new Piece_Cavalier(new Coordonees((byte)6,(byte)7), false));
		pieces.add(new Piece_Tour(new Coordonees((byte)7,(byte)7), false));
		
		
	}
	
	
	public GAMESTATUS verifierPlateau() {
		return GAMESTATUS.NOTHING;
	}
	
	public byte getWidth() { 
		return width; 
	}
	
	public byte getHeight() {
		return height; 
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
							nbCoups++;
							mouvements.add(new Mouvement(new Coordonees(piece.getPosition()),coord));
							return true;
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
									nbCoups++;
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
