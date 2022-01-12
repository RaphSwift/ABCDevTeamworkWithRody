import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String estEtudiant;
		String jourSemaine;
		Scanner sc = new Scanner(System.in);
		System.out.println("êtes-vous étudiant?");
		estEtudiant = sc.next().toLowerCase();
		if (estEtudiant.equals("oui")) {
			System.out.println("Quel jour de la semaine sera la coupe?");
			jourSemaine = sc.next().toLowerCase();
			if (jourSemaine.equals("jeudi")) {
				System.out.println("Vous avez droit à 20% de réduction");
			} else if (jourSemaine.equals("mercredi")) {
				System.out.println("Vous avez droit à 50% de réduction");
			} else {
				System.out.println("Vous n'avez pas droit à une réduction aujourd'hui");
			}
		} else {
			System.out.println("Seul les étudiants ont des réductions");
		}
		sc.close();
	}

}
