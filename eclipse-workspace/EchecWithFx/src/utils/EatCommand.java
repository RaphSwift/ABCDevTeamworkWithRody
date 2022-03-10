package utils;

import pieces.Piece;

public class EatCommand implements Command, java.io.Serializable{
	Plateau plateau;
	Mouvement mouvement;
	boolean estNoir;
	Piece relatedPiece;
	byte pieceType;
	
	public EatCommand(EatCommand c) {
		this(c.plateau, c.mouvement, c.estNoir, c.relatedPiece);
	}
	
	public EatCommand(Plateau _plateau, Mouvement _mouvement, boolean _estNoir, Piece _relatedPiece) {
		this(_plateau,_mouvement,_estNoir,_relatedPiece,(byte)-1);
	}
	
	public EatCommand(Plateau _plateau, Mouvement _mouvement, boolean _estNoir, Piece _relatedPiece, byte _pieceType) {
		plateau = _plateau;
		mouvement = _mouvement;
		estNoir = _estNoir;
		try {
			relatedPiece = (Piece) _relatedPiece.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pieceType = _pieceType;
	}
	
	@Override
	public boolean executer() {
		relatedPiece = plateau.getPieceFromCoord(mouvement.getTo());
		if (relatedPiece != null) {
			try {
				relatedPiece = (Piece) relatedPiece.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				return false;
			}
		}
		boolean rt = plateau.deplacerPiece(mouvement, estNoir);
		if (rt) {
			pieceType = plateau.getPieceFromCoord(mouvement.getTo()).getTypeId();
		}
		return rt;
	}
	
	@Override
	public String toString() {
		return  "[estNoir: " + estNoir + " bouge : " + mouvement + " et mange : " + relatedPiece +"]";
	}
	
	public byte getType() {
		return pieceType;
	}
	
	public final Mouvement getMove() {
		return mouvement;
	}
	
	public final Piece getRelatedPiece() {
		return relatedPiece;
	}
	
	public boolean isBlack() {
		return estNoir;
	}
	
	
}