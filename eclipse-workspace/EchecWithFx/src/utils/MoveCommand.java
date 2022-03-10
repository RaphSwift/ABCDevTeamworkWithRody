package utils;

import pieces.Piece;

public class MoveCommand implements Command, java.io.Serializable{
	Plateau plateau;
	Mouvement mouvement;
	boolean estNoir;
	byte pieceType;
	
	public MoveCommand(MoveCommand c) {
		this(c.plateau, c.mouvement, c.estNoir, c.pieceType);
	}
	
	public MoveCommand(Plateau _plateau, Mouvement _mouvement, boolean _estNoir) {
		this(_plateau,_mouvement,_estNoir,(byte)-1);
	}
	
	public MoveCommand(Plateau _plateau, Mouvement _mouvement, boolean _estNoir, byte _pieceType) {
		plateau = _plateau;
		mouvement = _mouvement;
		estNoir = _estNoir;
		pieceType = _pieceType;
	}
	
	@Override
	public boolean executer() {
		boolean rt =  plateau.deplacerPiece(mouvement, estNoir);
		if (rt) {
			pieceType = plateau.getPieceFromCoord(mouvement.getTo()).getTypeId();
		}
		return rt;
	}
	
	@Override
	public String toString() {
		return  "[estNoir: " + estNoir + " mouvement: " + mouvement + "]";
	}
	
	public byte getType() {
		return pieceType;
	}
	
	public final Mouvement getMove() {
		return mouvement;
	}
	
	public boolean isBlack() {
		return estNoir;
	}
	
}
