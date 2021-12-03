import java.util.Scanner;

public class Moyenne {
	public static void main(String[] args) {
		// Traduction du pseudo code en code JAVA
		float val1, val2;
		float resultat;

		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir la premiere valeur:");
		val1=sc.nextFloat();
		System.out.println("Veuillez saisir la deuxième valeur:");
		val2= sc.nextFloat();
		resultat = (val1 +  val2)/2.0f;
		System.out.println("La moyenne de " + val1 + " et " + val2 + " est de " + resultat);
		sc.close();
	}
}
