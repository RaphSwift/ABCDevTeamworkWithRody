import java.util.Random;
import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		Random random = new Random();
		Scanner sc = new Scanner(System.in);

		int nbATrouver;
		int fourchetteMin;
		int fourchetteMax;
		int nbEssais = 0;
		int nbSaisieUtilisateur = 0;
		
		fourchetteMin = 0;
		fourchetteMax = 100;		
		nbATrouver = random.nextInt(fourchetteMax+fourchetteMin)+fourchetteMin;
		do
		{
			nbEssais++;
			System.out.println("Veuillez saisir un nombre entre " + fourchetteMin + " et " + fourchetteMax);
			nbSaisieUtilisateur = sc.nextInt();
			if (nbSaisieUtilisateur > fourchetteMin &&
					nbSaisieUtilisateur < nbATrouver) {
				fourchetteMin = nbSaisieUtilisateur;
			} else if (nbSaisieUtilisateur < fourchetteMax && 
					nbSaisieUtilisateur > nbATrouver) {
				fourchetteMax = nbSaisieUtilisateur;
			}
				
				
		} while (nbSaisieUtilisateur != nbATrouver);
		System.out.println("Bravo, " + nbSaisieUtilisateur + " était le bon nombre vous avez mis " + nbEssais + " essais");
	}
}
