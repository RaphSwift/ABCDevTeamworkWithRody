import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String estEtudiant;
		String jourSemaine;
		Scanner sc = new Scanner(System.in);
		System.out.println("�tes-vous �tudiant?");
		estEtudiant = sc.next().toLowerCase();
		if (estEtudiant.equals("oui")) {
			System.out.println("Quel jour de la semaine sera la coupe?");
			jourSemaine = sc.next().toLowerCase();
			if (jourSemaine.equals("jeudi")) {
				System.out.println("Vous avez droit � 20% de r�duction");
			} else if (jourSemaine.equals("mercredi")) {
				System.out.println("Vous avez droit � 50% de r�duction");
			} else {
				System.out.println("Vous n'avez pas droit � une r�duction aujourd'hui");
			}
		} else {
			System.out.println("Seul les �tudiants ont des r�ductions");
		}
		sc.close();
	}

}
