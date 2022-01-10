import java.util.Arrays;

public class Plateau implements java.io.Serializable, Cloneable{
	
	private final byte width;
	private final byte height;
	private Jeton[][] jetons;
	int nbCoups;
	byte lastX, lastY;
	
	public Plateau(Plateau p) {
		this(p.width,p.height,p.jetons,p.nbCoups,p.lastX,p.lastY);
	}
	
	public Object clone() throws CloneNotSupportedException {
		return new Plateau(this);
	}
	
	public Plateau(final byte _width, final byte _height, Jeton[][] _jetons, int _nbCoups, byte _lastX, byte _lastY) {
		width = _width;
		height = _height;
		jetons = new Jeton[width][height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (_jetons[i][j] != null) {
					jetons[i][j] = new Jeton((Jeton)_jetons[i][j].clone());
				} else {
					jetons[i][j] = null;
				}
			}
		}

		nbCoups = _nbCoups;
		lastX = _lastX;
		lastY = _lastY;
	}
	
	public final byte getWidth() { return width; }
	public final byte getHeight() { return height; }
	public final byte getLastX() { return lastX; }
	public final byte getLastY() { return lastY; }
	
	public Plateau(final byte _width, final byte _height) {
		this.nbCoups=0;
		this.width = _width;
		this.height = _height;
		this.jetons = new Jeton[width][height];
		
		
	}
	
	public void reset() {
		nbCoups = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				jetons[i][j] = null;
			}
		}
	}
	
	
	
	

	/* Cette fonction renvoie le jeton aux coordonnes x;y
	 * elle renvoie null si le jeton n'est pas trouve
	 */
	public Jeton getJeton(byte x, byte y) {
		Jeton rt = null;
		if (x >= 0 && x < width && y>= 0 && y < height)
			rt = jetons[x][y];
		return rt;
	}
	

	public boolean ajouterCoup(byte column, Joueur j) {
		try {
			byte y_axis = calculerHauteurJeton(column);
			jetons[column][y_axis] = new Jeton(j.getCouleur());
			nbCoups++;
			this.lastX = column;
			this.lastY = y_axis;
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	

	
	/* Cette fonction renvoie la position en Y du jeton qu'on ajouterais
	 * en fonction de l'axe x, elle peut rencontrer une UnknowColumnException si la colonne n'existe pas
	 * ou une FilledColumnException si la colonne est déjà pleine
	 */
	private byte calculerHauteurJeton(byte column) throws FilledColumnException, UnknowColumnException{
		byte rt = 0;
		if (column > width || column < 0)
			throw new UnknowColumnException("Impossible d'ajouter dans la colonne " + column + " inconnue dans le plateau");
		byte i = (byte)(height-1);
		boolean finded = false;
		while(i >= 0 && !finded) {
			if (jetons[column][i] != null) {
				finded = true;
			} else {
				i--;
			}
		}
		if (finded) {
			if (i+1>=height) {
				throw new FilledColumnException("Impossible d'ajouter dans la colonne " + column + ", elle est déjà pleine");
			}
			rt = (byte)(i+1);
		}
	
		return rt;
	}
	
	/* VERIFIE SI LE JEU EST GAGNE */
	public final char getWin() {
		if (nbCoups < 7) { // IL FAUT AU MOINS 7 JETONS
			return ' ';
		}		boolean finded = false;
		
		Jeton actualTest = getJeton(lastX,lastY);
		if (actualTest != null) {
			finded = verifierHorizontal(actualTest)>=4 || verifierVertical(actualTest)>=4 ||
					verifierBackDiag(actualTest)>=4 || verifierForwardDiag(actualTest)>=4;
		}
		if (finded) {
			if (actualTest.getCouleur() == Winner.RED_WINNER.getLetter()) {
					return Winner.RED_WINNER.getLetter();
			} else if (actualTest.getCouleur() == Winner.YELLOW_WINNER.getLetter()){
				return Winner.YELLOW_WINNER.getLetter();
				
			}
		}
		return Winner.NO_WINNER.getLetter();
	}
	// OOOO
	private byte verifierHorizontal(Jeton from) {
		boolean rt = true;
		int jtTotal = 1;
		byte xTest, yTest;
		xTest= (byte)(lastX-1);
		yTest = (byte)(lastY);
		Jeton comparateTest = from;
		// ON VERIFIE A GAUCHE
		while (xTest >= 0 && comparateTest != null && comparateTest.getCouleur() == from.getCouleur() && jtTotal < 4) {							
			comparateTest = getJeton(xTest,yTest);
			if (comparateTest != null && comparateTest.getCouleur() == from.getCouleur()) {
				jtTotal++;
			}
			xTest--;
		}
		
		xTest = (byte)(lastX+1);
		comparateTest = from;
		// A DROITE
		while (xTest < width && comparateTest != null && comparateTest.getCouleur() == from.getCouleur() && jtTotal < 4) {
			comparateTest = getJeton(xTest,yTest);
			if (comparateTest != null && comparateTest.getCouleur() == from.getCouleur()) {
				jtTotal++;
			}
			xTest++;
		}
		return (byte)jtTotal;
	}
	
	/* O---
	 * O---
	 * O---
	 * O---
	 */
	private byte verifierVertical(Jeton from) {
		boolean rt = true;
		int jtTotal = 1;
		byte xTest, yTest;
		xTest= (byte)(lastX);
		Jeton comparateTest = from;
		// ON VERIFIE EN BAS
		yTest= (byte)(lastY-1);					
		while (yTest >= 0 && comparateTest != null && comparateTest.getCouleur() == from.getCouleur() && jtTotal < 4) {							
			comparateTest = getJeton(xTest,yTest);
			if (comparateTest != null && comparateTest.getCouleur() == from.getCouleur()) {
				jtTotal++;

			}
			yTest--;
		}
		yTest = (byte)(lastY+1);
		comparateTest = from;
		// EN HAUT
		while (yTest < height && comparateTest != null && comparateTest.getCouleur() == from.getCouleur() && jtTotal < 4) {
			comparateTest = getJeton(xTest,yTest);
			if (comparateTest != null && comparateTest.getCouleur() == from.getCouleur()) {
				jtTotal++;

			}
			yTest++;
		}
		return (byte)jtTotal;
	}
	/* O---
	 * -O--
	 * --O-
	 * ---O
	 */
	private byte verifierBackDiag(Jeton from) {
		boolean rt = true;
		int jtTotal = 1;
		byte xTest, yTest;
		Jeton comparateTest = from;
		// ON VERIFIE SUPPERIEUR GAUCHE
		xTest = (byte)(lastX-1);
		yTest = (byte)(lastY+1);
		while (yTest < height && xTest >= 0 && comparateTest != null && comparateTest.getCouleur() == from.getCouleur() && jtTotal < 4) {							
			comparateTest = getJeton(xTest,yTest);
			if (comparateTest != null && comparateTest.getCouleur() == from.getCouleur()) {
				jtTotal++;
			}
			xTest--;
			yTest++;
		}
		// PARTIE INFERIEURE DROITE
		comparateTest = from;
		xTest = (byte)(lastX+1);
		yTest = (byte)(lastY-1);
		while (yTest >=0 && xTest < width && comparateTest != null && comparateTest.getCouleur() == from.getCouleur() && jtTotal < 4) {							
			comparateTest = getJeton(xTest,yTest);
			if (comparateTest != null && comparateTest.getCouleur() == from.getCouleur()) {
				jtTotal++;
			}
			xTest++;
			yTest--;
		}
		return (byte)jtTotal;
	}
	/* ---O
	 * --O-
	 * -O--
	 * O---
	 */
	private byte verifierForwardDiag(Jeton from) {
		boolean rt = true;
		int jtTotal = 1;
		byte xTest, yTest;
		Jeton comparateTest = from;
		// PARTIE SUPPERIEUR DROITE
		comparateTest = from;
		xTest = (byte)(lastX+1);
		yTest = (byte)(lastY+1);
		while (yTest < height && xTest < width && comparateTest != null && comparateTest.getCouleur() == from.getCouleur() && jtTotal < 4) {							
			comparateTest = getJeton(xTest,yTest);
			if (comparateTest != null && comparateTest.getCouleur() == from.getCouleur()) {
				jtTotal++;

			}
			xTest++;
			yTest++;
		}
		// PARTIE INFERIEURE GAUCHE
		comparateTest = from;
		xTest = (byte)(lastX-1);
		yTest = (byte)(lastY-1);
		while (yTest >=0 && xTest >= 0 && comparateTest != null && comparateTest.getCouleur() == from.getCouleur() && jtTotal < 4) {							
			comparateTest = getJeton(xTest,yTest);
			if (comparateTest != null && comparateTest.getCouleur() == from.getCouleur()) {
				jtTotal++;

			}
			xTest--;
			yTest--;
		}
		xTest = lastX;
		yTest = lastY;
		return (byte)jtTotal;
	}
	
	public byte verifierHorizontalWithSpace(Jeton from) {
		boolean rt = true;
		int jtTotal = 1;
		byte xTest, yTest;
		xTest= (byte)(lastX-1);
		yTest = (byte)(lastY);
		Jeton comparateTest = from;
		// ON VERIFIE A GAUCHE
		while (xTest >= 0 && (comparateTest == null || comparateTest.getCouleur() == from.getCouleur()) && jtTotal < 4) {							
			comparateTest = getJeton(xTest,yTest);
			if (comparateTest == null || comparateTest.getCouleur() == from.getCouleur()) {
				jtTotal++;
			}
			xTest--;
		}
		
		xTest = (byte)(lastX+1);
		comparateTest = from;
		// A DROITE
		while (xTest < width && (comparateTest == null || comparateTest.getCouleur() == from.getCouleur()) && jtTotal < 4) {
			comparateTest = getJeton(xTest,yTest);
			if (comparateTest == null || comparateTest.getCouleur() == from.getCouleur()) {
				jtTotal++;
			}
			xTest++;
		}
		return (byte)jtTotal;
	}
	
	/* O---
	 * O---
	 * O---
	 * O---
	 */
	public byte verifierVerticalWithSpace(Jeton from) {
		boolean rt = true;
		int jtTotal = 1;
		byte xTest, yTest;
		xTest= (byte)(lastX);
		Jeton comparateTest = from;
		// ON VERIFIE EN BAS
		yTest= (byte)(lastY-1);					
		while (yTest >= 0 && (comparateTest == null || comparateTest.getCouleur() == from.getCouleur()) && jtTotal < 4) {							
			comparateTest = getJeton(xTest,yTest);
			if (comparateTest == null || comparateTest.getCouleur() == from.getCouleur()) {
				jtTotal++;

			}
			yTest--;
		}
		yTest = (byte)(lastY+1);
		comparateTest = from;
		// EN HAUT
		while (yTest < height && (comparateTest == null || comparateTest.getCouleur() == from.getCouleur()) && jtTotal < 4) {
			comparateTest = getJeton(xTest,yTest);
			if (comparateTest == null || comparateTest.getCouleur() == from.getCouleur()) {
				jtTotal++;

			}
			yTest++;
		}
		return (byte)jtTotal;
	}
	/* O---
	 * -O--
	 * --O-
	 * ---O
	 */
	public byte verifierBackDiagWithSpace(Jeton from) {
		boolean rt = true;
		int jtTotal = 1;
		byte xTest, yTest;
		Jeton comparateTest = from;
		// ON VERIFIE SUPPERIEUR GAUCHE
		xTest = (byte)(lastX-1);
		yTest = (byte)(lastY+1);
		while (yTest < height && xTest >= 0 && (comparateTest == null || comparateTest.getCouleur() == from.getCouleur()) && jtTotal < 4) {							
			comparateTest = getJeton(xTest,yTest);
			if (comparateTest != null && comparateTest.getCouleur() == from.getCouleur()) {
				jtTotal++;
			}
			xTest--;
			yTest++;
		}
		// PARTIE INFERIEURE DROITE
		comparateTest = from;
		xTest = (byte)(lastX+1);
		yTest = (byte)(lastY-1);
		while (yTest >=0 && xTest < width && (comparateTest == null || comparateTest.getCouleur() == from.getCouleur()) && jtTotal < 4) {							
			comparateTest = getJeton(xTest,yTest);
			if (comparateTest != null && comparateTest.getCouleur() == from.getCouleur()) {
				jtTotal++;
			}
			xTest++;
			yTest--;
		}
		return (byte)jtTotal;
	}
	/* ---O
	 * --O-
	 * -O--
	 * O---
	 */
	public byte verifierForwardDiagWithSpace(Jeton from) {
		boolean rt = true;
		int jtTotal = 1;
		byte xTest, yTest;
		Jeton comparateTest = from;
		// PARTIE SUPPERIEUR DROITE
		comparateTest = from;
		xTest = (byte)(lastX+1);
		yTest = (byte)(lastY+1);
		while (yTest < height && xTest < width && (comparateTest == null || comparateTest.getCouleur() == from.getCouleur()) && jtTotal < 4) {							
			comparateTest = getJeton(xTest,yTest);
			if (comparateTest != null && comparateTest.getCouleur() == from.getCouleur()) {
				jtTotal++;

			}
			xTest++;
			yTest++;
		}
		// PARTIE INFERIEURE GAUCHE
		comparateTest = from;
		xTest = (byte)(lastX-1);
		yTest = (byte)(lastY-1);
		while (yTest >=0 && xTest >= 0 && (comparateTest == null || comparateTest.getCouleur() == from.getCouleur()) && jtTotal < 4) {							
			comparateTest = getJeton(xTest,yTest);
			if (comparateTest != null && comparateTest.getCouleur() == from.getCouleur()) {
				jtTotal++;

			}
			xTest--;
			yTest--;
		}
		xTest = lastX;
		yTest = lastY;
		return (byte)jtTotal;
	}
	
	
	public void afficherGrille() {
		System.out.println("Grille");
		for (int j = height-1; j >= 0; j--) {
			for (int i= 0; i < width; i++) {
				if (jetons[i][j] == null) {
					System.out.print("-");
				} else {
					System.out.print(jetons[i][j].getCouleur());
				}
				
			}
			System.out.print("\n");
		}
	}
	

}
