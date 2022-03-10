package utils;

public class RoqueCommand implements Command, java.io.Serializable{
	Plateau plateau;
	Mouvement mouvement;
	boolean estNoir;
	
	public RoqueCommand(RoqueCommand c) {
		this(c.plateau, c.mouvement, c.estNoir);
	}

	public RoqueCommand(Plateau _plateau, Mouvement _mouvement, boolean _estNoir) {
		plateau = _plateau;
		mouvement = _mouvement;
		estNoir = _estNoir;
	}
	
	@Override
	public boolean executer() {
		return plateau.roquerPiece(mouvement, estNoir);
	}
	
	@Override
	public String toString() {
		return  "[estNoir: " + estNoir + " mouvement: " + mouvement + "]";
	}
	
	public final Mouvement getMove() {
		return mouvement;
	}
	
	public boolean isBlack() {
		return estNoir;
	}
	
}
