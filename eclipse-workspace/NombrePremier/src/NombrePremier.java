import java.util.Scanner;

public class NombrePremier {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int diviseur;
		int dividende;
		int maxBoucle;
		boolean estPremier;
		
		estPremier = true;
		Scanner sc = new Scanner(System.in);
		System.out.println("Quel est le nombre a tester?");
		dividende= sc.nextInt();
		
		maxBoucle = (int)Math.sqrt(dividende);
		diviseur = 1;
		
		
		while (diviseur<maxBoucle && estPremier) {
			diviseur++;
			if (dividende%diviseur == 0) {
				estPremier=false;
	
			}
			
		}
		
		if (estPremier) {
			System.out.println(dividende + " est premier");
		} else {
			System.out.println(dividende + " n'est pas premier");
		}
		sc.close();
	}

}
