import java.util.Random;
import java.util.Scanner;

public class MianClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int pointsOrdinateur;
		int pointsUtilisateur;
		int nbChoisiUtilisateur;
		int nbChoisiOrdinateur;
		int ecartValeur;
		Random rnd = new Random();
		Scanner sc = new Scanner(System.in);
		boolean veutQuitter;
		veutQuitter = false;
		pointsOrdinateur = 0;
		pointsUtilisateur = 0;
		do {
			nbChoisiOrdinateur = rnd.nextInt(2);
			
			System.out.println("saisisez un nombre entier entre 0 et 2 compris");
			nbChoisiUtilisateur = sc.nextInt();
			if (nbChoisiUtilisateur < 0) {
				veutQuitter = true;
			} else {
				ecartValeur = Math.abs(nbChoisiUtilisateur - nbChoisiOrdinateur);
				System.out.println("L'ordinateur à choisi : "+ nbChoisiOrdinateur);
				if (ecartValeur == 2) {
					if (nbChoisiUtilisateur > nbChoisiOrdinateur) {
						System.out.println("Vous gagnez un point");
						pointsUtilisateur++;
					} else {
						System.out.println("L'ordinateur gagne un point");
						pointsOrdinateur++;
					}
				} else if (ecartValeur == 1) {
					if (nbChoisiUtilisateur < nbChoisiOrdinateur) {
						System.out.println("Vous gagnez un point");
						pointsUtilisateur++;
					} else {
						System.out.println("L'ordinateur gagne un point");
						pointsOrdinateur++;
					}
				} else {
					System.out.println("Personne ne gagne de points");
				}
				System.out.println("Le score est de: Vous " + pointsUtilisateur + " - " + pointsOrdinateur + " Ordinateur");
			}
		} while (!veutQuitter && pointsOrdinateur != 10 && pointsUtilisateur != 10);
		if (!veutQuitter) {
			if (pointsOrdinateur == 10) {
				System.out.println("L'ordinateur a gagné");
			} else {
				System.out.println("Vous avez gagné");
			}
		}
		
	}

}
