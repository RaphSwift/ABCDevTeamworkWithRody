
public class Plateau {
	
	private final byte width;
	private final byte height;
	private Jeton[][] jetons;
	int nbCoups;
	byte lastX, lastY;
	
	public Plateau(byte _width, byte _height) {
		nbCoups=0;
		width = _width;
		height = _height;
		jetons = new Jeton[width][height];
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
			lastX = column;
			lastY = y_axis;
			return true;
		} catch (Exception e) {
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
	public char getWin() {
		if (nbCoups < 7) { // IL FAUT AU MOINS 7 PIECES
			return ' ';
		}
		// DIRECTION
		char rt = ' ';



		boolean finded = false;



		byte xTest = lastX , yTest = lastY;
		Jeton actualTest = null;
		Jeton comparateTest = null;
		int jtTotal;

		actualTest = getJeton(xTest,yTest);
		if (actualTest != null) {
			jtTotal = 1;

			// ON TESTE EN HORIZONAL
	
				xTest= (byte)(lastX-1);
				comparateTest = actualTest;
				while (xTest >= 0 && comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur() && jtTotal < 4) {							
					comparateTest = getJeton(xTest,yTest);
					if (comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur()) {
						jtTotal++;
					}
					xTest--;
				}
				xTest = (byte)(lastX+1);
				comparateTest = actualTest;
				// A DROITE
				while (xTest < width && comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur() && jtTotal < 4) {
					comparateTest = getJeton(xTest,yTest);
					if (comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur()) {
						jtTotal++;
						
					}
					xTest++;
				}
				xTest = lastX;

			if (jtTotal < 4) {
				xTest = lastX;
				jtTotal = 1;
				comparateTest = actualTest;
				// ON TESTE EN VERTICAL
				// EN BAS

				yTest= (byte)(lastY-1);					
				while (yTest >= 0 && comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur() && jtTotal < 4) {							
					comparateTest = getJeton(xTest,yTest);
					if (comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur()) {
						jtTotal++;

					}
					yTest--;
				}
				yTest = (byte)(lastY+1);
				comparateTest = actualTest;
				// EN HAUT
				while (yTest < height && comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur() && jtTotal < 4) {
					comparateTest = getJeton(xTest,yTest);
					if (comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur()) {
						jtTotal++;

					}
					yTest++;
				}
				yTest = lastY;

			}

			if (jtTotal < 4) {
				// ON TEST EN FORWARD_DIAG
				jtTotal = 1;
				comparateTest = actualTest;
				// PARTIE SUPPERIEUR GAUCHE
				xTest = (byte)(lastX-1);
				yTest = (byte)(lastY+1);
				while (yTest < height && xTest >= 0 && comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur() && jtTotal < 4) {							
					comparateTest = getJeton(xTest,yTest);
					if (comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur()) {
						jtTotal++;
					}
					xTest--;
					yTest++;
				}
				// PARTIE INFERIEURE DROITE
				comparateTest = actualTest;
				xTest = (byte)(lastX-1);
				yTest = (byte)(lastY-1);
				while (yTest >=0 && xTest < width && comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur() && jtTotal < 4) {							
					comparateTest = getJeton(xTest,yTest);
					if (comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur()) {
						jtTotal++;
					}
					yTest--;
					xTest--;
				}
				xTest = lastX;
				yTest = lastY;
			}

			if (jtTotal < 4) {
				jtTotal = 1;
				// PARTIE SUPPERIEUR DROITE
				comparateTest = actualTest;
				xTest = (byte)(lastX+1);
				yTest = (byte)(lastY+1);
				while (yTest+1 < height && xTest+1 < width && comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur() && jtTotal < 4) {							
					comparateTest = getJeton(xTest,yTest);
					if (comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur()) {
						jtTotal++;

					}
					xTest++;
					yTest++;
				}
				// PARTIE INFERIEURE GAUCHE
				comparateTest = actualTest;
				xTest = (byte)(lastX+1);
				yTest = (byte)(lastY-1);
				while (yTest-1 >=0 && xTest-1 >= 0 && comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur() && jtTotal < 4) {							
					comparateTest = getJeton(xTest,yTest);
					if (comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur()) {
						jtTotal++;

					}
					xTest++;
					yTest--;
				}
				xTest = lastX;
				yTest = lastY;
			}
			if (jtTotal >= 4) {
				finded = true;
			}
		}
		if (finded)
			return actualTest.getCouleur();
		return rt;
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
