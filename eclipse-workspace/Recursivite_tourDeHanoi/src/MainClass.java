
public class MainClass {
	
	public static final int MAX_PLATEAU = 5;
	
	public static void main(String[] args) {
		int[][] toursHanoi = new int[3][MAX_PLATEAU];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < MAX_PLATEAU; j++) {
				if (i == 0) {
					toursHanoi[i][j] = MAX_PLATEAU-j;
				}else {
					toursHanoi[i][j] = 0;
				}
			}
		}
		System.out.println("Tour de hanoi par recursivité");
		deplacer2DisqueVersArrivee(toursHanoi);
	}
	
	/*public static int[][] deplacerDisque(int[][] tours){
		
	}*/
	
	public static int[][] deplacer2DisqueVersArrivee(int[][] tours){
		int[][] retour = tours;
		int[] hauteurTour = calculerHauteurTour(tours);
		
		
		System.out.println("On deplace le disque en haut de la tour 1 a la tour 2");
		tours[1][hauteurTour[1]] = tours[0][hauteurTour[0]];
		tours[0][hauteurTour[0]]=0;
		calculerHauteurTour(tours);
		afficherContenuDesTours(tours);
		System.out.println("On deplace le dique en haut de la tour 1 a la tour 3");
		tours[2][hauteurTour[2]] = tours[0][hauteurTour[0]];
		tours[0][hauteurTour[0]]=0;
		calculerHauteurTour(tours);
		afficherContenuDesTours(tours);
		System.out.println("On deplace le disque en haut de la tour 2 a la tour 3");
		tours[2][hauteurTour[2]] = tours[1][hauteurTour[1]];
		tours[1][hauteurTour[1]]=0;
		calculerHauteurTour(tours);
		afficherContenuDesTours(tours);
		/*for (int i = MAX_PLATEAU-1; i>=0; i++) {
			if(retour[2][i] != 0) {
				 
			}
		}
		System.out.println("On deplace le plateau qui se trouve en tour d'arrivé ");*/
			return retour;
	}
	

	
	public static int[][] deplacer2DisqueArriveeDepart(int[][] tours){
		int[][] retour = tours;
		int[] hauteurTour = calculerHauteurTour(tours);
		
		afficherContenuDesTours(tours);
		/*for (int i = MAX_PLATEAU-1; i>=0; i++) {
			if(retour[2][i] != 0) {
				 
			}
		}
		System.out.println("On deplace le plateau qui se trouve en tour d'arrivé ");*/
			return retour;
	}
	
	public static void afficherContenuDesTours(int[][] tours) {
		int[] hauteurTour = calculerHauteurTour(tours);
		for (int i = 0; i < 3; i++) {
			System.out.print("La tour " + (i+1) + " contient:{");
			if (hauteurTour[i]>0) {
				for (int j = 0; j<= hauteurTour[i]-1; j++) {
					System.out.print(tours[i][j]);
					if (j+1 != hauteurTour[i])
						System.out.print(",");
				}
			}
			System.out.println("}");

		}
	}
	
	public static int[] calculerHauteurTour(int[][] tours) {
		int i,j;
		int[] hauteur = {0,0,0};
		for (i = 0 ; i < 3; i++) {
			j=0;
			while (tours[i][j] != 0 && j<MAX_PLATEAU-1) {
				hauteur[i]++;
				j++;
			}
		}
		return hauteur;
	}
	
	
}
