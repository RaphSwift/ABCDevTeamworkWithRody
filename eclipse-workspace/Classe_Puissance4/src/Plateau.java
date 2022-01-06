import java.util.Arrays;

public class Plateau {
	
	private final byte width;
	private final byte height;
	private Jeton[][] jetons;
	int nbCoups;
	byte lastX, lastY;
	
	
	public Plateau(final byte _width, final byte _height) {
		nbCoups=0;
		width = _width;
		height = _height;
		jetons = new Jeton[width][height];
		
		
	}
	
	public void reset() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				jetons[i][j] = null;
			}
		}
	}
	
	
	
	
	@Override
	public String toString() {
		String str = "Plateau [width=" + width + ", height=" + height + ", jetons=[";
		byte jetonsCompte = 0;
		byte i = 0;
		byte j = 0;
		while (i < width && jetonsCompte < nbCoups) {
			j=0;
			while (j < height && jetonsCompte < nbCoups) {
				if (jetons[i][j] != null) {
					jetonsCompte++;
					str+="jetons["+i+"]["+j+"]=[couleur="+jetons[i][j].getCouleur()+"]";
					if (jetonsCompte+1 < nbCoups)
						str+=", ";
				}
				
				j++;
			}
			i++;
		}
		str+= "], nbCoups="
				+ nbCoups + ", lastX=" + lastX + ", lastY=" + lastY + "]";
		return str;
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
	
	/*@Override
	public String toString() {
		return "Plateau [width=" + width + ", height=" + height + ", jetons=" + Arrays.toString(jetons) + ", nbCoups="
				+ nbCoups + ", lastX=" + lastX + ", lastY=" + lastY + "]";
	}*/

	public boolean ajouterCoup(byte column, Joueur j) {
		try {
			byte y_axis = calculerHauteurJeton(column);
			jetons[column][y_axis] = new Jeton(j.getCouleur());
			nbCoups++;
			lastX = column;
			lastY = y_axis;
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
		if (nbCoups < 7) { // IL FAUT AU MOINS 7 PIECES
			return ' ';
		}
		// DIRECTION
		



		boolean finded = false;



		byte xTest = lastX , yTest = lastY;
		Jeton actualTest = null;
		Jeton comparateTest = null;
		int jtTotal;

		actualTest = getJeton(xTest,yTest);
		if (actualTest != null) {
			/*jtTotal = 1;

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
				xTest = (byte)(lastX+1);
				yTest = (byte)(lastY-1);
				while (yTest >=0 && xTest < width && comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur() && jtTotal < 4) {							
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

			if (jtTotal < 4) {
				jtTotal = 1;
				// PARTIE SUPPERIEUR DROITE
				comparateTest = actualTest;
				xTest = (byte)(lastX+1);
				yTest = (byte)(lastY+1);
				while (yTest < height && xTest < width && comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur() && jtTotal < 4) {							
					comparateTest = getJeton(xTest,yTest);
					if (comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur()) {
						jtTotal++;

					}
					xTest++;
					yTest++;
				}
				// PARTIE INFERIEURE GAUCHE
				comparateTest = actualTest;
				xTest = (byte)(lastX-1);
				yTest = (byte)(lastY-1);
				while (yTest >=0 && xTest >= 0 && comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur() && jtTotal < 4) {							
					comparateTest = getJeton(xTest,yTest);
					if (comparateTest != null && comparateTest.getCouleur() == actualTest.getCouleur()) {
						jtTotal++;

					}
					xTest--;
					yTest--;
				}
				xTest = lastX;
				yTest = lastY;
			}
			if (jtTotal >= 4) {
				finded = true;
			}*/
			finded = verifierHorizontal(actualTest) || verifierVertical(actualTest) ||
					verifierBackDiag(actualTest) || verifierForwardDiag(actualTest);
		}
		/*if (finded)
			return actualTest.getCouleur();*/
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
	private boolean verifierHorizontal(Jeton from) {
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
		return (jtTotal >= 4);
	}
	
	/* O---
	 * O---
	 * O---
	 * O---
	 */
	private boolean verifierVertical(Jeton from) {
		boolean rt = true;
		int jtTotal = 1;
		byte xTest, yTest;
		xTest= (byte)(lastX);
		Jeton comparateTest = from;
		// ON VERIFIE EN HAUT
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
		return (jtTotal >= 4);
	}
	/* O---
	 * -O--
	 * --O-
	 * ---O
	 */
	private boolean verifierBackDiag(Jeton from) {
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
		return (jtTotal >= 4);
	}
	/* ---O
	 * --O-
	 * -O--
	 * O---
	 */
	private boolean verifierForwardDiag(Jeton from) {
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
		return (jtTotal >= 4);
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
