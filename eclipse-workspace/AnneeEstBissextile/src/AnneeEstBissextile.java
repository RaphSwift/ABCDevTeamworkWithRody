import java.util.Scanner;

public class AnneeEstBissextile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int anneeATester;
		Scanner sc = new Scanner(System.in);
		System.out.println("Bonjour ce programme va vous permettre de verifier si une annee est bissextile");
		System.out.println("Veuillez entree l'année à tester: ");
		anneeATester = sc.nextInt();
		if (anneeATester % 4 != 0 ||
				anneeATester % 100 == 0 && anneeATester % 400 != 0){
			System.out.println("L'année " + anneeATester + " n'est pas bissextile.");
		} 	else {
			System.out.println("L'année " + anneeATester + " est bissextile.");
		}

		sc.close();
	}

}
