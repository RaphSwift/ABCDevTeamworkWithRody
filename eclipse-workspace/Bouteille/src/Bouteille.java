
public class Bouteille {
	public static final boolean OPENED = true;
	public static final boolean CLOSED = false;
	public static final boolean EMPTY = false;
	public static final boolean FILLED = true;
	
	private boolean openState;
	private boolean isFilled;
	//private float filledPercent;
	
	public Bouteille() {
		this(Bouteille.CLOSED,Bouteille.FILLED);
	}
	
	public Bouteille(boolean _opened, boolean _filled) {
		openState = _opened;
		isFilled = _filled;
	}
	
	public boolean ouvrir() {
		if (openState != Bouteille.OPENED) {
			openState = Bouteille.OPENED;
			return true;
			
		}
		return false;
	}
	
	public boolean fermer() {
		if (openState != Bouteille.CLOSED) {
			openState = Bouteille.CLOSED;
			return true;
		}
		return false;
	}
	
	public boolean remplir() {
		if (isFilled != Bouteille.FILLED) {
			isFilled = Bouteille.FILLED;
			return true;
		}
		return false;
	}
	
	
	public boolean vider() {
		if (isFilled != Bouteille.EMPTY) {
			isFilled = Bouteille.FILLED;
			return true;
		}
		return false;
	}
}
