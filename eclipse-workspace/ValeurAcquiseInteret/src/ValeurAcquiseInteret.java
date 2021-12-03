import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.Scanner;

public class ValeurAcquiseInteret {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		

		int nbAnnees;
		float sommePlace;
		float tauxInteret;
		BigDecimal bd;
		double sommeFinaleSimple;
		double sommeFinaleCompose;
		
		System.out.println("Quelle est la somme initiale pour laquelle vous désirez calculer les interets ? ");
		sommePlace = sc.nextFloat();

		System.out.println("Quelle est son taux d'interet annuel? (ex: pour 1 % rentrez 0.01) ");
		tauxInteret = sc.nextFloat();
		System.out.println("Pendant combien de temps la simulation a lieu en années? ");
		nbAnnees = sc.nextInt();
		
		
		sommeFinaleSimple = sommePlace * (1+nbAnnees*tauxInteret);
		bd = new BigDecimal(sommeFinaleSimple).setScale(2,RoundingMode.HALF_UP);
		sommeFinaleSimple = bd.doubleValue();
		sommeFinaleCompose = sommePlace * Math.pow((1+tauxInteret),nbAnnees);
		bd = new BigDecimal(sommeFinaleCompose).setScale(2,RoundingMode.HALF_UP);
		sommeFinaleCompose= bd.doubleValue();
		
		System.out.println("La somme simple sera " + sommeFinaleSimple +  " la somme compose sera " + sommeFinaleCompose);
		
		sc.close();
	}

}
