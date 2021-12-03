package calculNombreParfait;

import java.util.Scanner;

public class CalculNombreParfait {

	public static void main(String[] args) {
		// DECLARATION
		int nbDeNbParfaits;
		int sommeDiviseur;		
		int nbATester;
		int nbTesterCourant;
		int nbDeNbParfaitCourant;
		//SAISIE
		Scanner sc = new Scanner(System.in);
		System.out.println("Combien de nombre parfait voulez vous?");
		// TRAITEMENT
		nbDeNbParfaits = sc.nextInt();	
		nbATester = 0;
		nbDeNbParfaitCourant = 0;
		while(nbDeNbParfaits != nbDeNbParfaitCourant) {
			sommeDiviseur = 0;
			nbTesterCourant = 0;
			nbATester++;
			for (nbTesterCourant = 1; nbTesterCourant < nbATester; nbTesterCourant++) {
				if (nbATester % nbTesterCourant == 0)
					sommeDiviseur+= nbTesterCourant;
			}
			if (sommeDiviseur == nbATester) {
				System.out.println(nbATester + " est un nombre Parfait");
				nbDeNbParfaitCourant++;
			}
			//System.out.println("test pour " + nbATester + " n'est pas parfait");
		}		
		System.out.println("Fin du programme");
		
		sc.close();
	}

}
