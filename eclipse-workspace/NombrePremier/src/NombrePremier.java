import java.util.Scanner;

public class NombrePremier {
	
	/* JEU D'ESSAIS
	 -2/ 0 / 1 / 13 / 8
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int dividende;
		
		boolean estPremier;
		
		estPremier = true;
		Scanner sc = new Scanner(System.in);
		System.out.println("Quel est le nombre a tester?");
		dividende= sc.nextInt();
		if (estPremier(dividende)) {
			System.out.println(dividende + " est premier");
		} else {
			System.out.println(dividende + " n'est pas premier");
		}
		sc.close();
	}
	
	public static boolean estPremier(int dividende) {
		int diviseur;
		int maxBoucle;
		if (dividende > 1) {
			maxBoucle = (int)Math.sqrt(dividende);
			diviseur = 3;
			
			if (dividende%2 == 0) {
				if (dividende == 2)
					return true;
				return false;
			}
			while (diviseur<maxBoucle) {
				
				if (dividende%diviseur == 0) {
					return false;
		
				}
				diviseur+=2;
				
			}
			return true;
		} else {
			return false;
		}
	}

}
