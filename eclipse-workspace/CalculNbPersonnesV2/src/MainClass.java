import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int nbDePersonnes;
		int ageSaisie;
		int nbPersonnesMoins20Ans =0;
		int nbPersonnesOnt20Ans=0;
		int nbPersonnesPlus20Ans=0;
		final int nbMaxPersonnes = 20;
		for (nbDePersonnes = 1; nbDePersonnes <= nbMaxPersonnes; nbDePersonnes++) {
			System.out.println("Age de la personne n° " + nbDePersonnes);
			ageSaisie = sc.nextInt();
			if (ageSaisie < 20) {
				nbPersonnesMoins20Ans++;
			} else if (ageSaisie == 20) {
				nbPersonnesOnt20Ans++;
			} else {
				nbPersonnesPlus20Ans++;
			}
		}
		
		if (nbPersonnesPlus20Ans == nbMaxPersonnes) {
			System.out.println("Toutes les personnes ont plus de 20 ans");
		} else if (nbPersonnesMoins20Ans == nbMaxPersonnes) {
			System.out.println("Toutes les personnes ont moins de 20 ans");
		} else {
			System.out.println("Il y'a " + nbPersonnesMoins20Ans + " personnes qui ont moins de 20 ans");
			System.out.println("Il y'a " + nbPersonnesOnt20Ans + " personnes qui ont  20 ans");
			System.out.println("Il y'a " + nbPersonnesPlus20Ans + " personnes qui ont plus de 20 ans");
		}
		sc.close();
	}

}
