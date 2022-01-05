import java.util.ArrayList;

public class Plateau {
	
	private final byte width;
	private final byte height;
	private Jeton[][] jetons;
	private byte[][][] jetons_nextTo;
	private boolean currentColor;
	private ArrayList<JetonGroup> groupeJeton;
	
	
	public Plateau(byte _width, byte _height) {
		width = _width;
		height = _height;
		jetons = new Jeton[width][height];
		currentColor = Jeton.JETON_ROUGE;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				jetons[width][height] = null;
			}
		}
		groupeJeton = new ArrayList<JetonGroup>();
	}
	
	public boolean ajouterJeton(int column) {
		int i;
		int j;
		int hauteurCalcule = 0;
		boolean finded = false;
		boolean findedGroup;
		if (column > width-1) {
			i = height-1;
			do {
				if (jetons[column][i] != null) {
					finded=true;
					hauteurCalcule = i+1;
				}
				i--;
			} while (i >= 0 && !finded);
			if (hauteurCalcule >= height) {
				return false;
			} else {
				jetons[column][hauteurCalcule] = new Jeton(currentColor, (byte)column, (byte)hauteurCalcule);
				
				Coordonees[] coordTmp;
				for (i = 0; i < groupeJeton.size(); i++){
					groupeJeton.get(i).ajouterJeton(jetons[column][hauteurCalcule]);
				}
				// ON VERIFIE SI ON CREER UN GROUPE
				if () {
					
				} else {
					
				}
				
				 // CHANGEMENT DE JOUEUR
				if (currentColor == Jeton.JETON_ROUGE) {
					currentColor = Jeton.JETON_JAUNE;
				} else {
					currentColor = Jeton.JETON_ROUGE;
				}
				return true;				
			}
		}
		return false;
	}

	
	
	public final byte getWidth() {
		return width;
	}
	
	public final byte getHeight() {
		return height;
	}
	
	
}
