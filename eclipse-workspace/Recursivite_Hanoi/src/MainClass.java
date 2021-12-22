
public class MainClass {
	public static final int MAX_PLATEAU = 5;
	
	public static void main(String[] args) {
		int toursHanoi[][] = new int[3][MAX_PLATEAU];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < MAX_PLATEAU;j++) {
				if (i == 0)
					toursHanoi[i][j] = MAX_PLATEAU-j;
				else
					toursHanoi[i][j] = 0;
			}
		}
		afficherValeurs(toursHanoi);
		toursHanoi = effectuerNiveau(toursHanoi,MAX_PLATEAU,0,1,2);
		afficherValeurs(toursHanoi);
	}
	
	public static int[] calculerHauteur(int tours[][]) {
		int[] hauteurs = {0,0,0};
		boolean finded;
		int j;
		for (int i = 0; i < 3; i++) {
			finded =false;
			j = MAX_PLATEAU-1;
			while (!finded && j>=0) {
				if (tours[i][j] != 0) 
					finded = true;
				else
					j--;
			}
			hauteurs[i] = j;				
		}
		return hauteurs;
	}
	
	public static void afficherValeurs(int tours[][]) {
		System.out.println("contenu des tours");
		int[] hauteursTours = calculerHauteur(tours);
		for (int i = 0; i < 3; i++) {
			System.out.print("{");
			for (int j=0; j <= hauteursTours[i]; j++) {
				if (tours[i][j] != 0) {
					System.out.print(tours[i][j]);
				}
				if ((j+1) <= hauteursTours[i])
					System.out.print(",");
			}
			System.out.println("}");
		}
	}
	
	public static int[][] decalerDepuisLaTour(int tours[][], int depart, int arrive) throws Exception{
		int retour[][] = tours;
		int hauteur[] = calculerHauteur(retour);

		if (hauteur[depart]<0)
			throw new Exception("Cannot move from empty tower ("+ depart + " to " + arrive + ")");
		if (hauteur[arrive] >= MAX_PLATEAU)
			throw new Exception("Destination are filled");
		int itMaxArrive = hauteur[arrive] + 1;
		if ((retour[depart][hauteur[depart]]>= retour[arrive][itMaxArrive]) &&
				retour[arrive][itMaxArrive] != 0) {
			throw new Exception("Cannot place taller piece on smaller one ("+ depart + " to " + arrive + ")");
		}
		
		retour[arrive][itMaxArrive] = retour[depart][hauteur[depart]];
		retour[depart][hauteur[depart]] = 0;
		System.out.println("On a deplacer la piece au sommet de la tour " + depart + " a la tour " + arrive);
		return retour;
	}
	
	/*public static int[][] effectuerNiveau(int[][] tours, int n){
		int[][] rt = tours;
		if (n >= 3) {
			try {
				rt = effectuerNiveau(rt, n-1);
				rt = decalerDepuisLaTour(rt,0,1);
				rt = inverserNiveau(rt,n-1);
				rt = decalerDepuisLaTour(rt,1,2);
				rt = effectuerNiveau(rt, n-1);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (n == 2) {
			try {
				rt= decalerDepuisLaTour(rt,0,1);
				rt = decalerDepuisLaTour(rt,0,2);
				rt = decalerDepuisLaTour(rt,1,2);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			try {
				rt = decalerDepuisLaTour(rt,0,2);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return rt;
	}*/
	
	public static int[][] effectuerNiveau(int[][] tours, int n, int debut, int intermediaire, int fin){
		int[][] rt = tours;
		if (n >= 3) {
			try {
				rt = effectuerNiveau(rt, n-1, debut, intermediaire, fin);
				rt = decalerDepuisLaTour(rt,debut,intermediaire);
				rt = effectuerNiveau(rt,n-1,fin,intermediaire,debut);
				rt = decalerDepuisLaTour(rt,intermediaire,fin);
				rt = effectuerNiveau(rt, n-1,debut,intermediaire,fin);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (n == 2) {
			try {
				rt= decalerDepuisLaTour(rt,debut,intermediaire);
				rt = decalerDepuisLaTour(rt,debut,fin);
				rt = decalerDepuisLaTour(rt,intermediaire,fin);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			try {
				rt = decalerDepuisLaTour(rt,debut,fin);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return rt;
	}
	
	/*public static int[][] inverserNiveau(int[][] tours, int n){
		int[][] rt = tours;
		if (n >= 3) {
			try {
				rt = inverserNiveau(rt, n-1);
				rt = decalerDepuisLaTour(rt,2,1);
				rt = effectuerNiveau(rt,n-1);
				rt = decalerDepuisLaTour(rt,1,0);
				rt = inverserNiveau(rt, n-1);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (n == 2) {
			try {
				rt= decalerDepuisLaTour(rt,2,1);
				rt = decalerDepuisLaTour(rt,2,0);
				rt = decalerDepuisLaTour(rt,1,0);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			try {
				rt = decalerDepuisLaTour(rt,2,0);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return rt;
	}*/
	
	/*public static int[][] resoudreHanoi(int[][] tours){
		int[][] retour = tours;
		int hauteurs[] = calculerHauteur(retour);
		int totalHauteur = (hauteurs[0]+1)+(hauteurs[1]+1)+(hauteurs[2]+1);
		boolean decalerIntermediaire = false;
		if (totalHauteur < 1 ) {
			return retour;
		}
		else if (totalHauteur == 1 && hauteurs[0] == 1) {
			try {
				retour = decalerDepuisLaTour(retour,0,2);
				afficherValeurs(retour);
				return retour;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			if (hauteurs[2] > -1) {
				// IL Y'A DES DISQUES EN ARRIVE
				
				if (hauteurs[0] > -1) {
					try {
						// ON BOUGE UN DISQUE DEPUIS L'ARRIVEE A L'INTERMEDIAIRE
						retour = decalerDepuisLaTour(retour,0,1);
						hauteurs[0]--;
						hauteurs[1]++;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
				}
				retour = resoudreHanoi(retour);
				if (hauteurs[1] == 0) {
					try {
						// ON BOUGE UN DISQUE DEPUIS L'ARRIVEE A L'INTERMEDIAIRE
						retour = decalerDepuisLaTour(retour,1,2);
						hauteurs[1]--;
						hauteurs[2]++;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						
				}
				hauteurs = calculerHauteur(retour);
				
				
			} else {
				if (hauteurs[0]> -1) {
					if (hauteurs[0]> 0) {
						try {
							retour = decalerDepuisLaTour(retour,0,1);
							hauteurs[0]--;
							hauteurs[1]++;
							retour = decalerDepuisLaTour(retour,0,2);
							hauteurs[2]++;
							hauteurs[0]--;
							retour = decalerDepuisLaTour(retour,0,2);
							hauteurs[2]++;
							hauteurs[0]--;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else {
						
					}
				
				}
			}
			if (totalHauteur != hauteurs[2]+1) {
				retour = resoudreHanoi(retour);
			}
			
		}
		return retour;
	}*/
	

	
}
