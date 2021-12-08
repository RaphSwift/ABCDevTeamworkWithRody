import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		int nbDePersonnes =0;
		int agePersonne;
		int nbPersonnesJeunes=0;
		Scanner sc = new Scanner(System.in);
		for (nbDePersonnes = 1; nbDePersonnes <= 20; nbDePersonnes++) {
			System.out.println("age de la personne n° " + nbDePersonnes);
			agePersonne = sc.nextInt();
			if (agePersonne < 20) {
				nbPersonnesJeunes++;
			}
		}
		System.out.println("il y'a " + nbPersonnesJeunes + " personnes de moins de 20 ans dans la liste saisie");
		sc.close();
	}
}
