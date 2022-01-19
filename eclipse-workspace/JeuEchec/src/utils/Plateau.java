package utils;
import java.util.ArrayList;

import pieces.*;

public class Plateau {
	private final byte width;
	private final byte height;
	private ArrayList<Piece> pieces;
	private ArrayList<Mouvement> mouvements;
	private short nbCoups;
	
	public Plateau(Plateau from) {
		this(from.width, from.height, from.pieces, from.mouvements, from.nbCoups);
	}
	
	public Plateau(byte _width, byte _height) {
		width = _width;
		height = _height;
		mouvements = new ArrayList<Mouvement>();
		pieces = new ArrayList<Piece>();
		reset_alternatif();
	}
	
	public final byte getWidth() {
		return width;
	}
	
	public final byte getHeight() {
		return height;
	}
	
	
	
	@Override
	public String toString() {
		Piece pcs[][] = new Piece[width][height];
		for (int i = 0; i < pieces.size();i++) {
			pcs[pieces.get(i).getPosition().getX()][pieces.get(i).getPosition().getY()] = pieces.get(i);
		}
		String str = "";
		Piece tmp= null;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (pcs[j][i] != null) {
					if (pcs[j][i] instanceof Piece_Roi) {
						str+="K";
					} else if (pcs[j][i] instanceof Piece_Reine) {
						str+="Q";
					} else if (pcs[j][i] instanceof Piece_Fou) {
						str+="F";
					} else if (pcs[j][i] instanceof Piece_Tour) {
						str+="T";
					} else if (pcs[j][i] instanceof Piece_Cavalier) {
						str+="B";
					} else {
						str+="P";
					}
					if (pcs[j][i].estNoir()) {
						str+="b";
					} else {
						str+="w";
					}
					
				} else {
					str += "--";
				}
				str += " ";
			}
			str+="\n";
		}
		return str;
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
		pieces.add(new Piece_Pion(new Coordonees((byte)0,(byte)7), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)1,(byte)7), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)2,(byte)7), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)3,(byte)7), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)4,(byte)7), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)5,(byte)7), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)6,(byte)7), false));
		pieces.add(new Piece_Pion(new Coordonees((byte)7,(byte)7), false));
		pieces.add(new Piece_Tour(new Coordonees((byte)0,(byte)8), false));
		pieces.add(new Piece_Cavalier(new Coordonees((byte)1,(byte)8), false));
		pieces.add(new Piece_Fou(new Coordonees((byte)2,(byte)8), false));
		pieces.add(new Piece_Reine(new Coordonees((byte)3,(byte)8), false));
		pieces.add(new Piece_Roi(new Coordonees((byte)4,(byte)8), false));
		pieces.add(new Piece_Fou(new Coordonees((byte)5,(byte)8), false));
		pieces.add(new Piece_Cavalier(new Coordonees((byte)6,(byte)8), false));
		pieces.add(new Piece_Tour(new Coordonees((byte)7,(byte)8), false));
	}
	
	public void reset_alternatif() {
		if (pieces.size() > 0) {
			pieces.clear();
		}
		pieces.add(new Piece_Tour(new Coordonees((byte)0,(byte)0), true));
		pieces.add(new Piece_Tour(new Coordonees((byte)0,(byte)8), false));
		pieces.add(new Piece_Fou(new Coordonees((byte)3,(byte)3), false));
		pieces.add(new Piece_Cavalier(new Coordonees((byte)1,(byte)2), false));
	}
	
	public Plateau(byte _width, byte _height, ArrayList<Piece> _pieces, ArrayList<Mouvement> _mouvements, short _nbCoups) {
		width = _width;
		height = _height;
		mouvements = new ArrayList<Mouvement>();
		for (int i = 0; i < _mouvements.size(); i++) {
			mouvements.add(new Mouvement(_mouvements.get(i)));
		}
		pieces = new ArrayList<Piece>();
		for (int i = 0; i < _pieces.size(); i++) {
			if (_pieces.get(i) instanceof Piece_Pion) {
				pieces.add(new Piece_Pion((Piece_Pion)_pieces.get(i)));
			} else if (_pieces.get(i) instanceof Piece_Tour) {
				pieces.add(new Piece_Tour((Piece_Tour)_pieces.get(i)));
			} else if (_pieces.get(i) instanceof Piece_Cavalier) {
				pieces.add(new Piece_Cavalier((Piece_Cavalier)_pieces.get(i)));
			} else if (_pieces.get(i) instanceof Piece_Roi) {
				pieces.add(new Piece_Roi((Piece_Roi)_pieces.get(i)));
			} else if (_pieces.get(i) instanceof Piece_Reine) {
				pieces.add(new Piece_Reine((Piece_Reine)_pieces.get(i)));
			} else {
				// FOU
				pieces.add(new Piece_Fou((Piece_Fou)_pieces.get(i)));
			}
		}
	}
	
	public ArrayList<Piece> getPieces(){
		return pieces;
	}
	
	public ArrayList<Piece> getPieceFromColor(boolean isBlack){
		ArrayList<Piece> rt = new ArrayList<Piece>();
		for (int i = 0; i < pieces.size(); i++) {
			if (pieces.get(i).estNoir() == isBlack) {
				rt.add(pieces.get(i));
			}
		}
		return rt;
	}
	
	public Piece getPieceFromCoord(Coordonees coord) {
		Piece piece = null;
		int i = 0;
		while (i < pieces.size() && piece == null) {
			if (pieces.get(i).getPosition().equals(coord)) {
				piece = pieces.get(i);
			}
			i++;
		}		
		return piece;
	}
}
