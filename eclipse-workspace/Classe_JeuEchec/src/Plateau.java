import java.util.ArrayList;

public class Plateau {
	private final byte width;
	private final byte height;
	public ArrayList<Piece> pieces;
	
	public Plateau(Plateau from) {
		this(from.width, from.height);
	}
	
	public Plateau(byte _width, byte _height) {
		width = _width;
		height = _height;
		pieces = new ArrayList<Piece>();
	}
	
	/*public boolean verifierPlateau() {
		
	}*/
	
	public boolean deplacerPiece(Coordonees coord, boolean joueurNoir) {
		// LES COORDONNEES EXISTENT SUR LE PLATEAU
		if (coord.getX() >=  0 && coord.getX() < width &&
				coord.getY() >= 0 && coord.getY() < height) {
			
			Piece tmp = getPiece(coord);
			Piece tmp2 = null;
			if (tmp.estNoir()==joueurNoir) {
				//-- LE JOUEUR QUI JOUE CORRESPOND AU POSSESSEUR DE LA PIECE
				
	
				if (tmp instanceof Piece_Pion) {
					// LE CAS DU PION EST TRAITE 
				} else {
					// CE N'EST PAS UN PION
					ArrayList<Coordonees> mouvements = tmp.calculerMouvement(this);
					int finded = -1;
					int i = 0;
					while (finded<0 &&  i < mouvements.size()) {
						if (mouvements.get(i).equals(coord)) {
							finded = i;
						}
						i++;
					}
					if (finded >= 0) {
						tmp2 = getPiece(coord);
						if (tmp2 != null) {
							if (tmp2.estNoir() != joueurNoir) {
								// ON MANGE LA PIECE
								tmp2.tuer();
								return true;
							}
						} else {
							return true;
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
