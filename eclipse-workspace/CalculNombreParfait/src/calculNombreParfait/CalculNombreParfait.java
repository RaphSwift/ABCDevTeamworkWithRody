package calculNombreParfait;

import java.util.Scanner;

public class CalculNombreParfait {

	public static void main(String[] args) {
		// DECLARATION
		int nbDeNbParfaits;

		//SAISIE
		Scanner sc = new Scanner(System.in);
		System.out.println("Combien de nombre parfait voulez vous?");
		// TRAITEMENT
		nbDeNbParfaits = sc.nextInt();	
		genererParfait(nbDeNbParfaits);

		
		System.out.println("Fin du programme");
		
		sc.close();
	}
	
	public static void genererParfait(int nb) {
		int nbDeParfaitCourant = 0;
		int nbATester = 0;
		int nbTesterCourant;
		int sommeDiviseur;
		while(nb != nbDeParfaitCourant) {
			sommeDiviseur = 0;
			nbTesterCourant = 0;
			nbATester++;
			for (nbTesterCourant = 1; nbTesterCourant < nbATester; nbTesterCourant++) {
				if (nbATester % nbTesterCourant == 0)
					sommeDiviseur+= nbTesterCourant;
			}
			if (sommeDiviseur == nbATester) {
				System.out.println(nbATester + " est un nombre Parfait");
				nbDeParfaitCourant++;
			}
			//System.out.println("test pour " + nbATester + " n'est pas parfait");
		}		
	}

}
